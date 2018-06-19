package com.github.scaffold.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.scaffold.high.cpu.HighCPU;
import com.github.scaffold.high.memory.HignMemory;

/**
 * 高cpu 和 高内存占用,系统缓慢
 * 
 * @author wangzhifeng
 */
@RestController
public class HighController {
    final Logger       log = LoggerFactory.getLogger(getClass());

    @Autowired
    private HighCPU    highCPU;

    @Autowired
    private HignMemory hignMemory;

    @RequestMapping(value = "/callCPU/{millisSeconds}", method = RequestMethod.GET)
    @ResponseBody
    public String callCPU(@PathVariable("millisSeconds") Long millisSeconds) {
        log.info("start callCPU req:{}毫秒", millisSeconds);
        if (null == millisSeconds || 0 == millisSeconds) {
            millisSeconds = 20L;
        }
        final Long sleepMillisSeconds = millisSeconds;
        new Thread(() -> highCPU.callOutService(sleepMillisSeconds)).start();

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "callCPU STARTED");
        return json.toJSONString();
    }

    @RequestMapping(value = "/stopCPU", method = RequestMethod.GET)
    @ResponseBody
    public String stopCPU() {
        Long callCount = highCPU.endCallOutService();

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "callCPU STOPED 本次已经调用次数=" + callCount);
        return json.toJSONString();
    }

    @RequestMapping(value = "/viewCpuMem", method = RequestMethod.GET)
    @ResponseBody
    public String viewCpuMem() {
        Long callCount = highCPU.viewCallOutService();
        String result = hignMemory.viewBigData();

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "当前测试cpu接口已经调用次数=" + callCount + "; \n测试内存接口" + result);
        return json.toJSONString();
    }

    //--内存
    @RequestMapping(value = "/callMem", method = RequestMethod.GET)
    @ResponseBody
    public String callMem() {
        log.info("start callMem");
        new Thread(() -> hignMemory.callOutServiceWithBigData()).start();

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "callMem STARTED");
        return json.toJSONString();
    }

    @RequestMapping(value = "/stopMem", method = RequestMethod.GET)
    @ResponseBody
    public String stopMem() {
        String result = hignMemory.endCallOutServiceWithBigData();

        JSONObject json = new JSONObject();
        json.put("status", "Y");
        json.put("msg", "callMem STOPED, " + result);
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
}
