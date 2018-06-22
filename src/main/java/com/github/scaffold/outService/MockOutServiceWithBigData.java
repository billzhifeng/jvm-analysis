package com.github.scaffold.outService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 模拟一个外部接口,返回占用内存较多数据
 * 
 * @author wangzhifeng
 */
@Service
public class MockOutServiceWithBigData {
    final Logger log = LoggerFactory.getLogger(getClass());

    public ReturnBigData queryBigData() {

        try {
            Thread.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("收到请求,返回大数据");
        return ReturnBigData.buildBigData();
    }

}
