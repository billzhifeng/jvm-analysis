package com.github.scaffold.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
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
import com.github.scaffold.pressure.DataWarehouse;
import com.github.scaffold.pressure.TransactionWithoutLock;
import com.github.scaffold.pressure.req.PayReq;

/**
 * 交易压力测试
 * 
 * @author wangzhifeng
 */
@RestController
public class AccountPay {

    final Logger                      logger           = LoggerFactory.getLogger(getClass());

    //默认付款账户数量
    private int                       payerNum         = 10;

    //默认收款账户数量
    private int                       payeeNum         = 1;

    private static int                corePoolSize     = 10;
    private static int                maxPoolSize      = 10;
    //线程池维护线程所允许的空闲时间
    private static int                keepAliveSeconds = 10;
    private static int                queueCapacity    = 900000;
    private static ThreadPoolExecutor threadPoolExecutor;
    private static boolean            isRun            = false;

    //最大入账TPS
    private long                      maxAccountingTPS;
    private long                      minAccountingTPS;

    //统计计数 入账次数
    private LongAdder                 accountCount     = new LongAdder();
    //统计计数 成功次数
    private LongAdder                 successCount     = new LongAdder();
    //统计计数 失败次数
    private LongAdder                 failCount        = new LongAdder();
    //统计开始时间
    private Long                      startTime        = 0L;

    //模拟客户端数量
    private String                    userCount        = "1";

    static {
        ThreadFactory threadFactory = new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "TransactionThread-" + count.incrementAndGet());
            }
        };

        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity), threadFactory);

        //CallerRunsPolicy含义为当线程池繁忙时让主线程执行
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @RequestMapping(value = "/changeAcctNums/{payerNum}/{payeeNum}", method = { RequestMethod.GET })
    @ResponseBody
    public String changePayeeNumAndPayerNum(@PathVariable("payerNum") int payerNum,
                                            @PathVariable("payeeNum") int payeeNum) {
        JSONObject json = new JSONObject();
        json.put("before", "payerNum=" + this.payerNum + ",payeeNum=" + this.payeeNum);
        this.payeeNum = payeeNum;
        this.payerNum = payerNum;
        json.put("after", "payerNum=" + this.payerNum + ",payeeNum=" + this.payeeNum);
        json.put("status", "Y");
        return json.toJSONString();
    }

    @RequestMapping(value = "/account/pay/viewThreadPool", method = { RequestMethod.GET })
    @ResponseBody
    public String viewThreadPool() {
        JSONObject json = new JSONObject();
        json.put("before", this.getThreadPoolExecutorInfo());
        json.put("status", "Y");
        return json.toJSONString();
    }

    @RequestMapping(value = "/account/pay/setThreadPoolSize/{poolSize}", method = { RequestMethod.GET })
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

    @RequestMapping(value = "/account/pay/changeUserCount/{userCount}", method = { RequestMethod.GET })
    @ResponseBody
    public String changeUserCount(@PathVariable String userCount) {
        JSONObject json = new JSONObject();
        int setUserCount = Integer.parseInt(userCount);
        if (0 >= setUserCount) {
            setUserCount = 1;
        }
        this.userCount = setUserCount + "";
        json.put("status", "Y");
        return json.toJSONString();
    }

    @RequestMapping(value = "/account/pay/viewTPS", method = { RequestMethod.GET })
    @ResponseBody
    public String viewTPS() {

        long accountCountValue = accountCount.longValue();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        Long now = System.currentTimeMillis();
        //单位秒
        long periodTime = (now - startTime) / 1000;
        periodTime = periodTime == 0L ? 1 : periodTime;

        JSONObject json = new JSONObject();
        json.put("period", periodTime);

        //成功数量 
        json.put("successCount", successCount.longValue());
        //失败数量
        json.put("failCount", failCount.longValue());

        long accountCountValueNow = accountCount.longValue();

        long currentAccountTPS = 10 * (accountCountValueNow - accountCountValue);
        if (maxAccountingTPS < currentAccountTPS) {
            maxAccountingTPS = currentAccountTPS;
        }

        if (minAccountingTPS == 0 || minAccountingTPS > currentAccountTPS) {
            minAccountingTPS = currentAccountTPS;
        }

        json.put("currentAccountTPS", currentAccountTPS);

        json.put("maxAccountingTPS", maxAccountingTPS);

        json.put("minAccountingTPS", minAccountingTPS);

        long accountTPS = accountCountValueNow / periodTime;
        json.put("accountTPS", accountTPS);

        json.put("accountCount", accountCountValueNow);

        json.put("isRun", isRun);
        json.put("userCount", userCount);

        json.put("threadPoolExecutorInfo", this.getThreadPoolExecutorInfo());

        json.put("acctInfo", "payerNum=" + this.payerNum + ",payeeNum=" + this.payeeNum);

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

    @RequestMapping(value = "/account/pay/stop", method = { RequestMethod.GET })
    @ResponseBody
    public String stop() {
        isRun = false;
        backToZero();

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        return json.toJSONString();
    }

    private void backToZero() {
        accountCount = new LongAdder();
        successCount = new LongAdder();
        failCount = new LongAdder();

        maxAccountingTPS = 0;
        minAccountingTPS = 0;

        startTime = 0L;
    }

    /**
     * 入账压测
     * 
     * @return
     */
    @RequestMapping(value = "/account/pay/start", method = { RequestMethod.GET })
    @ResponseBody
    public String start() {

        isRun = true;
        startTime = System.currentTimeMillis();

        JSONObject json = new JSONObject();

        for (int i = 0; i < Integer.parseInt(userCount); i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (isRun) {
                            boolean result = pay();
                            accountCount.increment();
                            if (result) {
                                successCount.increment();
                            } else {
                                failCount.increment();
                            }
                        }
                    } catch (Throwable e) {
                        failCount.increment();
                        logger.error("支付压测 异常", e);
                    }
                }
            });
        }

        json.put("status", "Y");
        return json.toJSONString();
    }

    @Autowired
    TransactionWithoutLock transactionService;

    private AtomicLong transNo = new AtomicLong(System.currentTimeMillis());

    private boolean pay() {
        int srcAcctIndex = (int) (Math.random() * payerNum);
        Long srcAcctId = DataWarehouse.payerAcctIds.get(srcAcctIndex);
        int tarAcctIndex = (int) (Math.random() * payeeNum);
        tarAcctIndex = (tarAcctIndex < 1 ? 1 : tarAcctIndex);
        Long tarAcctId = DataWarehouse.payeeAcctIds.get(tarAcctIndex);
        PayReq req = new PayReq(srcAcctId, tarAcctId, 2L, transNo.incrementAndGet());
        try {
            transactionService.doTrans(req);
        } catch (Exception e) {
            logger.error("支付异常,req:{}", req, e);
            return false;
        }
        return true;
    }

}
