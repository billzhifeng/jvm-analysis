package com.github.scaffold.outService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 模拟一个外部接口
 * 
 * @author wangzhifeng
 */
@Service
public class MockOutServiceWithSleep {
    final Logger log = LoggerFactory.getLogger(getClass());

    public ReturnData queryDataWithSleep(Long millisSeconds) {

        log.info("收到请求,开始sleep millisSeconds={}", millisSeconds);
        try {
            Thread.sleep(millisSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ReturnData.buildData();
    }

    public ReturnBigData queryBigData() {

        log.info("收到请求,返回大数据");
        return ReturnBigData.buildBigData();
    }

}
