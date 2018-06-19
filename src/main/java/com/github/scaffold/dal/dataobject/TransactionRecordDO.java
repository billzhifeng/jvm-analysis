package com.github.scaffold.dal.dataobject;

import java.util.Date;

public class TransactionRecordDO {
    private Long id;

    private Long srcAcctId;

    private Long targetAcctId;

    private Long transNo;

    private Long transAmount;

    private String transStatus;

    private Integer version;

    private Date createdTime;

    private Date updatedTime;

    private String transType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSrcAcctId() {
        return srcAcctId;
    }

    public void setSrcAcctId(Long srcAcctId) {
        this.srcAcctId = srcAcctId;
    }

    public Long getTargetAcctId() {
        return targetAcctId;
    }

    public void setTargetAcctId(Long targetAcctId) {
        this.targetAcctId = targetAcctId;
    }

    public Long getTransNo() {
        return transNo;
    }

    public void setTransNo(Long transNo) {
        this.transNo = transNo;
    }

    public Long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Long transAmount) {
        this.transAmount = transAmount;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus == null ? null : transStatus.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
    }
}