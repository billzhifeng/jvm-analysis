package com.github.scaffold.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.scaffold.high.memory.HighCPU;
import com.github.scaffold.high.memory.HignMemory;
import com.github.scaffold.outService.MockOutServiceWithSleep;

/**
 * 高cpu 和 高内存占用,系统缓慢
 * 
 * @author wangzhifeng
 */
@RestController
public class HighController {
    final Logger                      log               = LoggerFactory.getLogger(getClass());

    @Autowired
    private HignMemory                hignMemory;

    private static int                corePoolSize      = 5;
    private static int                maxPoolSize       = 20;
    //线程池维护线程所允许的空闲时间
    private static int                keepAliveSeconds  = 10;
    private static int                queueCapacity     = 900000;
    private static ThreadPoolExecutor threadPoolExecutor;

    /**
     * 调用系统阻塞标记
     */
    private boolean                   callBolckFlag     = false;

    /**
     * 系统阻塞 1亿次调用，防止死循环
     */
    private final long                maxCallBolckCount = 100000000;

    /**
     * 系统阻塞计数器
     */
    private LongAdder                 blockCount        = new LongAdder();

    /**
     * 调用标记
     */
    private static boolean            callMemFlag       = false;

    /**
     * 计数器
     */
    private LongAdder                 countMem          = new LongAdder();
    /**
     * 10万次调用，防止死循环
     */
    private final long                maxMemCallCount   = 1000000;

    static {
        ThreadFactory threadFactory = new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "HighCpuTestThread-" + count.incrementAndGet());
            }
        };

        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity), threadFactory);

        //CallerRunsPolicy含义为当线程池繁忙时让主线程执行
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @RequestMapping(value = "/cpu/setThreadPoolSize/{poolSize}", method = { RequestMethod.GET })
    @ResponseBody
    public String setThreadPoolSize(@PathVariable String poolSize) {
        JSONObject json = new JSONObject();
        json.put("before", this.getThreadPoolExecutorInfo());
        threadPoolExecutor.setCorePoolSize(Integer.parseInt(poolSize));
        threadPoolExecutor.setMaximumPoolSize(Integer.parseInt(poolSize));
        json.put("after", this.getThreadPoolExecutorInfo());
        json.put("status", "Y");
        return json.toJSONString();
    }

    @Autowired
    private MockOutServiceWithSleep sleepService;

    @RequestMapping(value = "/callSysBlock/{millisSeconds}/{clientCount}", method = RequestMethod.GET)
    public String callSysBlock(@PathVariable("millisSeconds") Long millisSeconds,
                               @PathVariable("clientCount") Integer clientCount) {
        log.info("start callSysBlock req 阻塞{}毫秒, 线程数:{},客户端数量{}", millisSeconds, threadPoolExecutor.getCorePoolSize(),
                clientCount);

        if (null == clientCount || 0 == clientCount) {
            clientCount = 2;
        }
        final Long sleepMillisSeconds = millisSeconds;

        callBolckFlag = true;
        for (int i = 0; i < clientCount; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (callBolckFlag) {
                            //最大执行1亿次
                            if (blockCount.longValue() >= maxCallBolckCount) {
                                break;
                            }
                            sleepService.queryDataWithSleep(sleepMillisSeconds);
                            blockCount.increment();

                            //减少无效log,每20次打印一次log
                            if (blockCount.longValue() % 20 == 0) {
                                log.info("系统阻塞执行标记 callBolckFlag:{},当前累计次数={}", callBolckFlag, blockCount.longValue());
                            }
                        }

                    } catch (Throwable e) {
                        log.error("系统阻塞执行异常", e);
                    }
                }
            });
        }

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "callSysBlock STARTED");
        return json.toJSONString();
    }

    @RequestMapping(value = "/stopSysBlock", method = RequestMethod.GET)
    public String stopSysBlock() {
        callBolckFlag = false;

        //执行总次数
        Long totalCounts = blockCount.longValue();
        blockCount.reset();
        log.info("stoped bolck outServiceWithSleep 执行总次数：{}", totalCounts);

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "bolck STOPED 本次已经调用次数=" + blockCount.longValue());
        return json.toJSONString();
    }

    @Autowired
    private HighCPU highCpu;

    @RequestMapping(value = "/callCPU/{maxCount}", method = RequestMethod.GET)
    @ResponseBody
    public String callCPU(@PathVariable("maxCount") long maxCount) {

        log.info("start callCPU ");

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    highCpu.loop(maxCount);
                }
            });
        }

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "callCPU STARTED");
        return json.toJSONString();
    }

    @RequestMapping(value = "/viewCpuMem", method = RequestMethod.GET)
    @ResponseBody
    public String viewCpuMem() {
        long countMemCount = countMem.longValue();
        int queueSize = hignMemory.viewBigData();

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("before", this.getThreadPoolExecutorInfo());
        json.put("msg", "当前内存接口调用次数=" + countMemCount + ",queueSize=" + queueSize);
        return json.toJSONString();
    }

    //--内存
    @RequestMapping(value = "/callMem/{clientCount}", method = RequestMethod.GET)
    @ResponseBody
    public String callMem(@PathVariable("clientCount") Integer clientCount) {
        log.info("start callMem,线程数：{},客户端数量{}", threadPoolExecutor.getCorePoolSize(), clientCount);

        if (null == clientCount || 0 == clientCount) {
            clientCount = 2;
        }

        callMemFlag = true;

        for (int i = 0; i < clientCount; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (callMemFlag) {
                            //最大执行10万次
                            if (countMem.longValue() >= maxMemCallCount) {
                                break;
                            }

                            //模拟调用外部服务响应，并在内存中保存大量返回结果
                            int queueSize = hignMemory.callOutServiceWithBigData();
                            countMem.increment();

                            //减少无效log,每20次打印一次log
                            if (countMem.longValue() % 20 == 0) {
                                log.info("调用 callOutServiceWithBigData callMemFlag:{},当前累计次数={},queueSize:{}",
                                        callMemFlag, countMem.longValue(), queueSize);
                            }
                        }

                    } catch (Throwable e) {
                        log.error("callMem 异常", e);
                    }
                }
            });
        }

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "callMem STARTED 线程数=" + threadPoolExecutor.getCorePoolSize() + "客户端数量=" + clientCount);
        return json.toJSONString();
    }

    @RequestMapping(value = "/stopMem", method = RequestMethod.GET)
    @ResponseBody
    public String stopMem() {
        callMemFlag = false;

        //执行总次数
        Long totalCounts = countMem.longValue();
        countMem.reset();
        int queueSize = hignMemory.viewBigData();
        log.info("stoped callMem callOutServiceWithBigData 执行总次数：{},队列总长度：{}", totalCounts, queueSize);

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "callMem STOPED,本次已经调用次数= " + totalCounts + ",队列总长度=" + queueSize);
        return json.toJSONString();
    }

    @RequestMapping(value = "/resetMem", method = RequestMethod.GET)
    @ResponseBody
    public String resetMem() {
        String result = hignMemory.resetBigData();

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "RESET callMem SUCCESS, " + result);
        return json.toJSONString();
    }

    private String getThreadPoolExecutorInfo() {
        long poolSize = threadPoolExecutor.getPoolSize();
        long activeCount = threadPoolExecutor.getActiveCount();
        long taskCount = threadPoolExecutor.getTaskCount();
        long complete = threadPoolExecutor.getCompletedTaskCount();

        long queueSize = threadPoolExecutor.getQueue().size();
        long corePoolSize = threadPoolExecutor.getCorePoolSize();
        long maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();

        StringBuffer sb = new StringBuffer();
        sb.append("poolSize = ").append(poolSize).append("\n");
        sb.append("activeCount = ").append(activeCount).append("\n");
        sb.append("taskCount = ").append(taskCount).append("\n");
        sb.append("complete = ").append(complete).append("\n");
        sb.append("queueSize = ").append(queueSize).append("\n");
        sb.append("corePoolSize = ").append(corePoolSize).append("\n");
        sb.append("maximumPoolSize = ").append(maximumPoolSize).append("\n");
        return sb.toString();
    }
}
