package com.qianlong.qltt.us.domain;

import java.util.ArrayList;
import java.util.List;

public class TUSAcctBaseInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TUSAcctBaseInfoExample() {
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

        public Criteria andFsNameIsNull() {
            addCriterion("Fs_name is null");
            return (Criteria) this;
        }

        public Criteria andFsNameIsNotNull() {
            addCriterion("Fs_name is not null");
            return (Criteria) this;
        }

        public Criteria andFsNameEqualTo(String value) {
            addCriterion("Fs_name =", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameNotEqualTo(String value) {
            addCriterion("Fs_name <>", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameGreaterThan(String value) {
            addCriterion("Fs_name >", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_name >=", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameLessThan(String value) {
            addCriterion("Fs_name <", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameLessThanOrEqualTo(String value) {
            addCriterion("Fs_name <=", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameLike(String value) {
            addCriterion("Fs_name like", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameNotLike(String value) {
            addCriterion("Fs_name not like", value, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameIn(List<String> values) {
            addCriterion("Fs_name in", values, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameNotIn(List<String> values) {
            addCriterion("Fs_name not in", values, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameBetween(String value1, String value2) {
            addCriterion("Fs_name between", value1, value2, "fsName");
            return (Criteria) this;
        }

        public Criteria andFsNameNotBetween(String value1, String value2) {
            addCriterion("Fs_name not between", value1, value2, "fsName");
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