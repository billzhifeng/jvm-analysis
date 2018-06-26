package com.github.scaffold.high.cpu;

import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.java.common.utils.MD5;

/**
 * @author wangzhifeng
 * @date 2018年6月21日 下午8:46:16
 */
@Service
public class HighCPU {
    final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @param maxCount 最大循环次数，防止死循环
     */
    public void loop(long maxCount) {
        LongAdder countCPU = new LongAdder();
        while (countCPU.longValue() < maxCount) {

            MD5.getMD5(UUID.randomUUID().toString(), "UTF-8");

            countCPU.increment();
            if (countCPU.longValue() % 500 == 0) {
                log.info("Thread-{} 死循环调用,当前次数：{}，最大次数：{}", Thread.currentThread().getName(), countCPU.longValue(),
                        maxCount);
            }
        }

        //计数器重置
        countCPU.reset();
    }
}
