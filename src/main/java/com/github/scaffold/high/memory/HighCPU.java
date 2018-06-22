package com.github.scaffold.high.memory;

import java.util.concurrent.atomic.LongAdder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangzhifeng
 * @date 2018年6月21日 下午8:46:16
 */
@Service
public class HighCPU {
    final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @param maxCount
     * @param countCPU
     */
    public void loop(long maxCount) {
        LongAdder countCPU = new LongAdder();
        while (countCPU.longValue() < maxCount) {
            countCPU.increment();
            if (countCPU.longValue() % 20 == 0) {
                log.info("Thread-{} 死循环调用,当前次数：{}，最大次数：{}", Thread.currentThread().getName(), countCPU.longValue(),
                        maxCount);
            }
        }

        countCPU.reset();

    }
}
