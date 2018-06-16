package com.github.scaffold.pressure.req;

import com.github.java.common.base.Printable;

/**
 * @author wangzhifeng
 * @date 2018年6月14日 上午11:11:03
 */
public class PayReq extends Printable {

    private static final long serialVersionUID = 1325747321384534836L;

    Long                      srcAcctId;
    Long                      tarAcctId;
    Long                      transAmount;
    Long                      transNo;

    public PayReq() {

    }

    public Long getTransNo() {
        return transNo;
    }

    public PayReq(Long srcAcctId, Long tarAcctId, Long transAmount, Long transNo) {
        this.srcAcctId = srcAcctId;
        this.tarAcctId = tarAcctId;
        this.transAmount = transAmount;
        this.transNo = transNo;
    }

    public Long getSrcAcctId() {
        return srcAcctId;
    }

    public void setSrcAcctId(Long srcAcctId) {
        this.srcAcctId = srcAcctId;
    }

    public Long getTarAcctId() {
        return tarAcctId;
    }

    public void setTarAcctId(Long tarAcctId) {
        this.tarAcctId = tarAcctId;
    }

    public Long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Long transAmount) {
        this.transAmount = transAmount;
    }

}
