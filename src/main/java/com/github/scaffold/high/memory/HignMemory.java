package com.github.scaffold.high.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.scaffold.outService.MockOutServiceWithBigData;
import com.github.scaffold.outService.ReturnBigData;

/**
 * @author wangzhifeng
 * @date 2018年6月16日 下午5:30:41
 */
@Service
public class HignMemory {

    final Logger                      log          = LoggerFactory.getLogger(getClass());
    @Autowired
    private MockOutServiceWithBigData outService;

    /**
     * 调用标记
     */
    private boolean                   callFlag;

    /**
     * 计数器
     */
    private LongAdder                 count        = new LongAdder();
    /**
     * 10万次调用，防止死循环
     */
    private final long                maxCallCount = 100000;

    private List<ReturnBigData>       list         = new ArrayList<ReturnBigData>();

    public void callOutServiceWithBigData() {
        callFlag = true;
        long startTime = System.currentTimeMillis();
        while (callFlag && count.longValue() < maxCallCount) {

            ReturnBigData data = outService.queryBigData();
            list.add(data);
            count.increment();

            //减少无效log
            if (count.longValue() % 20 == 0) {
                log.info("call outServiceWithBigData resp:{},list.size={}", data, list.size());
            }
        }

        callFlag = false;
        Long totalCallCount = count.longValue();
        count.reset();
        long seconds = (System.currentTimeMillis() - startTime) / 1000;
        log.info("outServiceWithBigData is stoped,运行时间 ={}秒,调用次数：{},list.size()", seconds, totalCallCount, list.size());
    }

    public String endCallOutServiceWithBigData() {
        log.info("try to stop outServiceWithBigData");
        callFlag = false;
        return "总调用次数=" + count.longValue() + ",list.size=" + list.size();
    }

    /**
     * 重置list为空
     * 
     * @return
     */
    public String resetBigData() {
        log.info("try to reset resetBigData,list.size={}", list.size());
        int size = list.size();
        log.info("success reset resetBigData.list.size={}", list.size());
        return "总调用次数=" + count.longValue() + ",重置前list.size=" + size;
    }

    /**
     * 查看
     * 
     * @return
     */
    public String viewBigData() {
        int size = list.size();
        return "总调用次数=" + count.longValue() + ",list.size=" + size;
    }

}
