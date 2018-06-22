package com.github.scaffold.high.memory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

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

    final Logger                               log    = LoggerFactory.getLogger(getClass());
    @Autowired
    private MockOutServiceWithBigData          outService;

    private LinkedBlockingQueue<ReturnBigData> queue  = new LinkedBlockingQueue<ReturnBigData>();

    private ArrayBlockingQueue<ReturnBigData>  aqueue = new ArrayBlockingQueue<ReturnBigData>(200);

    /**
     * 开始增加内存占用
     * 
     * @return
     */
    public int callOutServiceWithBigData() {
        ReturnBigData data = outService.queryBigData();

        if (aqueue.size() <= 200) {
            aqueue.offer(data);
        }

        //Eden 开辟内存 快速回收
        LinkedBlockingDeque<ReturnBigData> q = new LinkedBlockingDeque<ReturnBigData>();
        q.offer(data);

        queue.addAll(aqueue);

        return queue.size();
    }

    public Integer viewBigData() {
        int size = queue.size();
        return size;
    }

    /**
     * 重置list为空
     * 
     * @return
     */
    public String resetBigData() {
        log.info("try to reset resetBigData,queue.size={}", queue.size());
        int size = queue.size();
        queue.clear();
        log.info("success reset resetBigData.queue.size={}", queue.size());
        return "重置前queue.size=" + size;
    }

}
