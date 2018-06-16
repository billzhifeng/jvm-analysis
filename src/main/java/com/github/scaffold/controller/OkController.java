package com.github.scaffold.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.java.common.utils.DateUtil;
import com.github.scaffold.dal.dao.AcctDOMapper;
import com.github.scaffold.dal.dataobject.AcctDO;
import com.github.scaffold.pressure.DataWarehouse;
import com.github.scaffold.pressure.enums.AcctTypeEnum;

/**
 * ok
 * 
 * @author wangzhifeng
 */
@Controller
public class OkController {
    final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/ok", method = { RequestMethod.GET })
    public String ok() {
        return "/OK";
    }

    @RequestMapping(value = "/showView", method = { RequestMethod.GET })
    public String view(HttpServletRequest req, Model map) {
        return "/TPS";
    }

    @Autowired
    private AcctDOMapper acctDOMapper;

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    @ResponseBody
    public AcctDO db() {
        return acctDOMapper.selectByPrimaryKey(1L);
    }

    //加载数据库数据
    @RequestMapping(value = "/initData", method = RequestMethod.GET)
    @ResponseBody
    public String initData() {
        dataWarehouse.initData();
        return "DONE";
    }

    @Autowired
    DataWarehouse dataWarehouse;

    @RequestMapping(value = "/show/payer", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> showPayer() {
        return dataWarehouse.payerAcctIds;
    }

    @RequestMapping(value = "/show/payee", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> showPayee() {
        return dataWarehouse.payeeAcctIds;
    }

    /**
     * 创建付款账户
     * 
     * @return
     */
    @RequestMapping(value = "/createPayAcct/{num}", method = RequestMethod.GET)
    @ResponseBody
    public String createPayAcct(@PathVariable int num) {
        Long baseBalance = 10000000L;
        Date now = new Date();
        //最少创建一个账户
        num = 0 == num ? 1 : num;
        String today = DateUtil.format(now, DateUtil.longFormat);

        for (int count = 0; count < num; count++) {
            AcctDO acc = new AcctDO();
            acc.setAcctType(AcctTypeEnum.PAYER.name());
            acc.setContent("付款账户");
            acc.setCreatedTime(now);
            acc.setName("付款账户_" + today + "_" + count);
            acc.setBalance(baseBalance);
            acc.setVersion(0);
            acc.setUpdatedTime(now);
            acctDOMapper.insertSelective(acc);
        }
        return "创建付款账户  " + num + " 成功";
    }

    /**
     * 创建收款账户
     * 
     * @return
     */
    @RequestMapping(value = "/createPayeeAcct/{num}", method = RequestMethod.GET)
    @ResponseBody
    public String createPayeeAcct(@PathVariable int num) {
        Long baseBalance = 600000L;
        Date now = new Date();
        //最少创建一个账户
        num = 0 == num ? 1 : num;
        String today = DateUtil.format(now, DateUtil.longFormat);

        for (int count = 0; count < num; count++) {
            AcctDO acc = new AcctDO();
            acc.setAcctType(AcctTypeEnum.PAYEE.name());
            acc.setContent("收款账户");
            acc.setCreatedTime(now);
            acc.setName("收款账户_" + today + "_" + count);
            acc.setBalance(baseBalance);
            acc.setVersion(0);
            acc.setUpdatedTime(now);
            acctDOMapper.insertSelective(acc);
        }
        return "创建收款账户 " + num + " 成功";
    }

}
