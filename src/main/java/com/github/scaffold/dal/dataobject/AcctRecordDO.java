package com.github.scaffold.dal.dataobject;

import java.util.Date;

public class AcctRecordDO {
    private Long id;

    private Long sourceAcctId;

    private Long beforeBalance;

    private Long transAmount;

    private Long afterBalance;

    private Long transNo;

    private Date createdTime;

    private String fundDirc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceAcctId() {
        return sourceAcctId;
    }

    public void setSourceAcctId(Long sourceAcctId) {
        this.sourceAcctId = sourceAcctId;
    }

    public Long getBeforeBalance() {
        return beforeBalance;
    }

    public void setBeforeBalance(Long beforeBalance) {
        this.beforeBalance = beforeBalance;
    }

    public Long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Long transAmount) {
        this.transAmount = transAmount;
    }

    public Long getAfterBalance() {
        return afterBalance;
    }

    public void setAfterBalance(Long afterBalance) {
        this.afterBalance = afterBalance;
    }

    public Long getTransNo() {
        return transNo;
    }

    public void setTransNo(Long transNo) {
        this.transNo = transNo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getFundDirc() {
        return fundDirc;
    }

    public void setFundDirc(String fundDirc) {
        this.fundDirc = fundDirc == null ? null : fundDirc.trim();
    }
}