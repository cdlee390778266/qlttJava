package com.qianlong.qltt.us.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TUSAcctCNRegExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TUSAcctCNRegExample() {
        oredCriteria = new ArrayList<Criteria>();
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

        public Criteria andFsTtacctIsNull() {
            addCriterion("Fs_ttacct is null");
            return (Criteria) this;
        }

        public Criteria andFsTtacctIsNotNull() {
            addCriterion("Fs_ttacct is not null");
            return (Criteria) this;
        }

        public Criteria andFsTtacctEqualTo(String value) {
            addCriterion("Fs_ttacct =", value, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctNotEqualTo(String value) {
            addCriterion("Fs_ttacct <>", value, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctGreaterThan(String value) {
            addCriterion("Fs_ttacct >", value, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_ttacct >=", value, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctLessThan(String value) {
            addCriterion("Fs_ttacct <", value, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctLessThanOrEqualTo(String value) {
            addCriterion("Fs_ttacct <=", value, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctLike(String value) {
            addCriterion("Fs_ttacct like", value, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctNotLike(String value) {
            addCriterion("Fs_ttacct not like", value, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctIn(List<String> values) {
            addCriterion("Fs_ttacct in", values, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctNotIn(List<String> values) {
            addCriterion("Fs_ttacct not in", values, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctBetween(String value1, String value2) {
            addCriterion("Fs_ttacct between", value1, value2, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsTtacctNotBetween(String value1, String value2) {
            addCriterion("Fs_ttacct not between", value1, value2, "fsTtacct");
            return (Criteria) this;
        }

        public Criteria andFsCnIsNull() {
            addCriterion("Fs_CN is null");
            return (Criteria) this;
        }

        public Criteria andFsCnIsNotNull() {
            addCriterion("Fs_CN is not null");
            return (Criteria) this;
        }

        public Criteria andFsCnEqualTo(String value) {
            addCriterion("Fs_CN =", value, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnNotEqualTo(String value) {
            addCriterion("Fs_CN <>", value, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnGreaterThan(String value) {
            addCriterion("Fs_CN >", value, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_CN >=", value, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnLessThan(String value) {
            addCriterion("Fs_CN <", value, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnLessThanOrEqualTo(String value) {
            addCriterion("Fs_CN <=", value, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnLike(String value) {
            addCriterion("Fs_CN like", value, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnNotLike(String value) {
            addCriterion("Fs_CN not like", value, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnIn(List<String> values) {
            addCriterion("Fs_CN in", values, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnNotIn(List<String> values) {
            addCriterion("Fs_CN not in", values, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnBetween(String value1, String value2) {
            addCriterion("Fs_CN between", value1, value2, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFsCnNotBetween(String value1, String value2) {
            addCriterion("Fs_CN not between", value1, value2, "fsCn");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeIsNull() {
            addCriterion("Ft_regtime is null");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeIsNotNull() {
            addCriterion("Ft_regtime is not null");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeEqualTo(Timestamp value) {
            addCriterion("Ft_regtime =", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeNotEqualTo(Timestamp value) {
            addCriterion("Ft_regtime <>", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeGreaterThan(Timestamp value) {
            addCriterion("Ft_regtime >", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("Ft_regtime >=", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeLessThan(Timestamp value) {
            addCriterion("Ft_regtime <", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("Ft_regtime <=", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeIn(List<Timestamp> values) {
            addCriterion("Ft_regtime in", values, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeNotIn(List<Timestamp> values) {
            addCriterion("Ft_regtime not in", values, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("Ft_regtime between", value1, value2, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("Ft_regtime not between", value1, value2, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeIsNull() {
            addCriterion("Ft_updtime is null");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeIsNotNull() {
            addCriterion("Ft_updtime is not null");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeEqualTo(Timestamp value) {
            addCriterion("Ft_updtime =", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeNotEqualTo(Timestamp value) {
            addCriterion("Ft_updtime <>", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeGreaterThan(Timestamp value) {
            addCriterion("Ft_updtime >", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("Ft_updtime >=", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeLessThan(Timestamp value) {
            addCriterion("Ft_updtime <", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("Ft_updtime <=", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeIn(List<Timestamp> values) {
            addCriterion("Ft_updtime in", values, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeNotIn(List<Timestamp> values) {
            addCriterion("Ft_updtime not in", values, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("Ft_updtime between", value1, value2, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("Ft_updtime not between", value1, value2, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFiStatusIsNull() {
            addCriterion("Fi_status is null");
            return (Criteria) this;
        }

        public Criteria andFiStatusIsNotNull() {
            addCriterion("Fi_status is not null");
            return (Criteria) this;
        }

        public Criteria andFiStatusEqualTo(Integer value) {
            addCriterion("Fi_status =", value, "fiStatus");
            return (Criteria) this;
        }

        public Criteria andFiStatusNotEqualTo(Integer value) {
            addCriterion("Fi_status <>", value, "fiStatus");
            return (Criteria) this;
        }

        public Criteria andFiStatusGreaterThan(Integer value) {
            addCriterion("Fi_status >", value, "fiStatus");
            return (Criteria) this;
        }

        public Criteria andFiStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_status >=", value, "fiStatus");
            return (Criteria) this;
        }

        public Criteria andFiStatusLessThan(Integer value) {
            addCriterion("Fi_status <", value, "fiStatus");
            return (Criteria) this;
        }

        public Criteria andFiStatusLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_status <=", value, "fiStatus");
            return (Criteria) this;
        }

        public Criteria andFiStatusIn(List<Integer> values) {
            addCriterion("Fi_status in", values, "fiStatus");
            return (Criteria) this;
        }

        public Criteria andFiStatusNotIn(List<Integer> values) {
            addCriterion("Fi_status not in", values, "fiStatus");
            return (Criteria) this;
        }

        public Criteria andFiStatusBetween(Integer value1, Integer value2) {
            addCriterion("Fi_status between", value1, value2, "fiStatus");
            return (Criteria) this;
        }

        public Criteria andFiStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_status not between", value1, value2, "fiStatus");
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