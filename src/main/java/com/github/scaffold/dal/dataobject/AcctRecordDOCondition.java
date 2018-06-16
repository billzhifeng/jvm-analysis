package com.github.scaffold.dal.dataobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AcctRecordDOCondition {
    protected String orderByClause;

    protected int offSet;

    protected int length;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AcctRecordDOCondition() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
         return length;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdIsNull() {
            addCriterion("source_acct_id is null");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdIsNotNull() {
            addCriterion("source_acct_id is not null");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdEqualTo(Long value) {
            addCriterion("source_acct_id =", value, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdNotEqualTo(Long value) {
            addCriterion("source_acct_id <>", value, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdGreaterThan(Long value) {
            addCriterion("source_acct_id >", value, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdGreaterThanOrEqualTo(Long value) {
            addCriterion("source_acct_id >=", value, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdLessThan(Long value) {
            addCriterion("source_acct_id <", value, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdLessThanOrEqualTo(Long value) {
            addCriterion("source_acct_id <=", value, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdIn(List<Long> values) {
            addCriterion("source_acct_id in", values, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdNotIn(List<Long> values) {
            addCriterion("source_acct_id not in", values, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdBetween(Long value1, Long value2) {
            addCriterion("source_acct_id between", value1, value2, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andSourceAcctIdNotBetween(Long value1, Long value2) {
            addCriterion("source_acct_id not between", value1, value2, "sourceAcctId");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceIsNull() {
            addCriterion("before_balance is null");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceIsNotNull() {
            addCriterion("before_balance is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceEqualTo(Long value) {
            addCriterion("before_balance =", value, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceNotEqualTo(Long value) {
            addCriterion("before_balance <>", value, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceGreaterThan(Long value) {
            addCriterion("before_balance >", value, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("before_balance >=", value, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceLessThan(Long value) {
            addCriterion("before_balance <", value, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceLessThanOrEqualTo(Long value) {
            addCriterion("before_balance <=", value, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceIn(List<Long> values) {
            addCriterion("before_balance in", values, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceNotIn(List<Long> values) {
            addCriterion("before_balance not in", values, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceBetween(Long value1, Long value2) {
            addCriterion("before_balance between", value1, value2, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andBeforeBalanceNotBetween(Long value1, Long value2) {
            addCriterion("before_balance not between", value1, value2, "beforeBalance");
            return (Criteria) this;
        }

        public Criteria andTransAmountIsNull() {
            addCriterion("trans_amount is null");
            return (Criteria) this;
        }

        public Criteria andTransAmountIsNotNull() {
            addCriterion("trans_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTransAmountEqualTo(Long value) {
            addCriterion("trans_amount =", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotEqualTo(Long value) {
            addCriterion("trans_amount <>", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountGreaterThan(Long value) {
            addCriterion("trans_amount >", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("trans_amount >=", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountLessThan(Long value) {
            addCriterion("trans_amount <", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountLessThanOrEqualTo(Long value) {
            addCriterion("trans_amount <=", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountIn(List<Long> values) {
            addCriterion("trans_amount in", values, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotIn(List<Long> values) {
            addCriterion("trans_amount not in", values, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountBetween(Long value1, Long value2) {
            addCriterion("trans_amount between", value1, value2, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotBetween(Long value1, Long value2) {
            addCriterion("trans_amount not between", value1, value2, "transAmount");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceIsNull() {
            addCriterion("after_balance is null");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceIsNotNull() {
            addCriterion("after_balance is not null");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceEqualTo(Long value) {
            addCriterion("after_balance =", value, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceNotEqualTo(Long value) {
            addCriterion("after_balance <>", value, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceGreaterThan(Long value) {
            addCriterion("after_balance >", value, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("after_balance >=", value, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceLessThan(Long value) {
            addCriterion("after_balance <", value, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceLessThanOrEqualTo(Long value) {
            addCriterion("after_balance <=", value, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceIn(List<Long> values) {
            addCriterion("after_balance in", values, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceNotIn(List<Long> values) {
            addCriterion("after_balance not in", values, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceBetween(Long value1, Long value2) {
            addCriterion("after_balance between", value1, value2, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andAfterBalanceNotBetween(Long value1, Long value2) {
            addCriterion("after_balance not between", value1, value2, "afterBalance");
            return (Criteria) this;
        }

        public Criteria andTransNoIsNull() {
            addCriterion("trans_no is null");
            return (Criteria) this;
        }

        public Criteria andTransNoIsNotNull() {
            addCriterion("trans_no is not null");
            return (Criteria) this;
        }

        public Criteria andTransNoEqualTo(Long value) {
            addCriterion("trans_no =", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotEqualTo(Long value) {
            addCriterion("trans_no <>", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoGreaterThan(Long value) {
            addCriterion("trans_no >", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoGreaterThanOrEqualTo(Long value) {
            addCriterion("trans_no >=", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLessThan(Long value) {
            addCriterion("trans_no <", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLessThanOrEqualTo(Long value) {
            addCriterion("trans_no <=", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoIn(List<Long> values) {
            addCriterion("trans_no in", values, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotIn(List<Long> values) {
            addCriterion("trans_no not in", values, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoBetween(Long value1, Long value2) {
            addCriterion("trans_no between", value1, value2, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotBetween(Long value1, Long value2) {
            addCriterion("trans_no not between", value1, value2, "transNo");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andFundDircIsNull() {
            addCriterion("fund_dirc is null");
            return (Criteria) this;
        }

        public Criteria andFundDircIsNotNull() {
            addCriterion("fund_dirc is not null");
            return (Criteria) this;
        }

        public Criteria andFundDircEqualTo(String value) {
            addCriterion("fund_dirc =", value, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircNotEqualTo(String value) {
            addCriterion("fund_dirc <>", value, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircGreaterThan(String value) {
            addCriterion("fund_dirc >", value, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircGreaterThanOrEqualTo(String value) {
            addCriterion("fund_dirc >=", value, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircLessThan(String value) {
            addCriterion("fund_dirc <", value, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircLessThanOrEqualTo(String value) {
            addCriterion("fund_dirc <=", value, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircLike(String value) {
            addCriterion("fund_dirc like", value, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircNotLike(String value) {
            addCriterion("fund_dirc not like", value, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircIn(List<String> values) {
            addCriterion("fund_dirc in", values, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircNotIn(List<String> values) {
            addCriterion("fund_dirc not in", values, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircBetween(String value1, String value2) {
            addCriterion("fund_dirc between", value1, value2, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircNotBetween(String value1, String value2) {
            addCriterion("fund_dirc not between", value1, value2, "fundDirc");
            return (Criteria) this;
        }

        public Criteria andFundDircLikeInsensitive(String value) {
            addCriterion("upper(fund_dirc) like", value.toUpperCase(), "fundDirc");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}