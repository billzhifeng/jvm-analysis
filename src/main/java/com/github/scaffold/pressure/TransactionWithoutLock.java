package com.github.scaffold.pressure;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.scaffold.dal.dao.AcctDOMapper;
import com.github.scaffold.dal.dao.AcctRecordDOMapper;
import com.github.scaffold.dal.dao.TransactionRecordDOMapper;
import com.github.scaffold.dal.dataobject.AcctDO;
import com.github.scaffold.dal.dataobject.AcctRecordDO;
import com.github.scaffold.dal.dataobject.TransactionRecordDO;
import com.github.scaffold.pressure.enums.FundDircEnum;
import com.github.scaffold.pressure.req.PayReq;

/**
 * 交易:A B两账户，A付款 B收款，保存交易记录，保存A 、B的账户变化记录，在一个事务内完成 <br>
 * 并发时候，账户不安全。
 * 
 * @author wangzhifeng
 * @date 2018年6月13日 下午3:18:39
 */
@Service
public class TransactionWithoutLock {

    final Logger              log = LoggerFactory.getLogger(getClass());

    @Autowired
    AcctDOMapper              acctDOMapper;

    @Autowired
    AcctRecordDOMapper        acctRecordDOMapper;

    @Autowired
    TransactionRecordDOMapper transactionRecordDOMapper;

    @Transactional(rollbackFor = Throwable.class)
    public void doTrans(PayReq req) {

        log.info("交易开始,transNo:{},req:{}", req.getTransNo(), req);
        Long srcAcctId = req.getSrcAcctId();
        Long tarAcctId = req.getTarAcctId();
        Long transAmount = req.getTransAmount();
        Long transNo = req.getTransNo();
        Date now = new Date();

        AcctDO src = acctDOMapper.selectByPrimaryKey(srcAcctId);
        Assert.isTrue(null != src, "acct_id=" + srcAcctId + "不存在");
        AcctDO tar = acctDOMapper.selectByPrimaryKey(tarAcctId);
        Assert.isTrue(null != tar, "acct_id=" + tarAcctId + "不存在");

        //交易双方记录
        TransactionRecordDO tr = new TransactionRecordDO();
        tr.setCreatedTime(now);
        tr.setSrcAcctId(srcAcctId);
        tr.setTargetAcctId(tarAcctId);
        tr.setTransAmount(transAmount);
        tr.setTransNo(transNo);
        tr.setVersion(1);
        tr.setUpdatedTime(now);

        //付款方账户变更记录
        AcctRecordDO sad = new AcctRecordDO();
        sad.setAfterBalance(src.getBalance() - transAmount);
        sad.setBeforeBalance(src.getBalance());
        sad.setFundDirc(FundDircEnum.OUT.name());
        sad.setSourceAcctId(srcAcctId);
        sad.setTransAmount(transAmount);
        sad.setTransNo(transNo);
        sad.setCreatedTime(now);
        //付款方账户变更余额
        AcctDO srcForUpdate = new AcctDO();
        srcForUpdate.setId(srcAcctId);
        srcForUpdate.setBalance(sad.getAfterBalance());
        srcForUpdate.setVersion(src.getVersion() + 1);
        srcForUpdate.setUpdatedTime(now);

        ///----收款方账户变更记录
        AcctRecordDO tad = new AcctRecordDO();
        tad.setAfterBalance(tar.getBalance() + transAmount);
        tad.setBeforeBalance(tar.getBalance());
        tad.setFundDirc(FundDircEnum.IN.name());
        tad.setSourceAcctId(tarAcctId);
        tad.setTransAmount(transAmount);
        tad.setTransNo(transNo);
        tad.setCreatedTime(now);
        //收款方余额变更
        AcctDO tarForUpdate = new AcctDO();
        tarForUpdate.setId(tarAcctId);
        tarForUpdate.setBalance(tad.getAfterBalance());
        tarForUpdate.setVersion(src.getVersion() + 1);
        tarForUpdate.setUpdatedTime(now);

        //数据库操作-----
        int trCount = transactionRecordDOMapper.insert(tr);
        if (1 != trCount) {
            throw new RuntimeException("TransactionRecordDO 保存失败");
        }
        //---
        int srcUpCount = acctDOMapper.updateByPrimaryKeySelective(srcForUpdate);
        if (1 != srcUpCount) {
            throw new RuntimeException("付款方 Acct 修改失败,acctId=" + srcForUpdate.getId());
        }

        int sarCount = acctRecordDOMapper.insert(sad);
        if (1 != sarCount) {
            throw new RuntimeException("付款方 AcctRecord 保存失败");
        }
        //---
        int tarUpCount = acctDOMapper.updateByPrimaryKeySelective(tarForUpdate);
        if (1 != tarUpCount) {
            throw new RuntimeException("收款方 Acct 修改失败,acctId=" + tarForUpdate.getId());
        }

        int tarCount = acctRecordDOMapper.insert(tad);
        if (1 != tarCount) {
            throw new RuntimeException("收款方 AcctRecord 保存失败");
        }
        log.info("交易结束,transNo:{},req:{}", req.getTransNo(), req);
    }
}
