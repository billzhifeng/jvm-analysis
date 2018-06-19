package com.github.scaffold.high.cpu;

import java.util.concurrent.atomic.LongAdder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.scaffold.outService.MockOutServiceWithSleep;
import com.github.scaffold.outService.ReturnData;

/**
 * @author wangzhifeng
 * @date 2018年6月16日 下午5:28:58
 */
@Service
public class HighCPU {

    final Logger                    log          = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockOutServiceWithSleep outService;

    /**
     * 调用标记
     */
    private boolean                 callFlag;

    /**
     * 计数器
     */
    private LongAdder               count        = new LongAdder();

    /**
     * 10万次调用，防止死循环
     */
    private final long              maxCallCount = 100000;

    public void callOutService(Long millisSeconds) {
        log.info("reveive call outServiceWithSleep request,req millisSeconds={}", millisSeconds);
        long startTime = System.currentTimeMillis();
        boolean logFlag = true;
        callFlag = true;
        while (callFlag && count.longValue() < maxCallCount) {

            ReturnData data = outService.queryDataWithSleep(millisSeconds);
            count.increment();

            //为了减少无效log,每次仅调用打印一次
            if (logFlag) {
                log.info("call outServiceWithSleep resp:{}", data);
            }
            logFlag = false;
        }

        callFlag = false;
        Long totalCallCount = count.longValue();
        count.reset();
        long seconds = (System.currentTimeMillis() - startTime) / 1000;
        log.info("outServiceWithSleep is stoped,运行时间 ={}秒,总调用次数：{}", seconds, totalCallCount);
    }

    public Long endCallOutService() {
        log.info("try to stop outServiceWithSleep");
        callFlag = false;
        Long countLong = count.longValue();
        count.reset();
        return countLong;
    }

    public Long viewCallOutService() {
        return count.longValue();
    }
}
