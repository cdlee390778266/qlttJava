package com.qianlong.qltt.us.domain;

import java.util.ArrayList;
import java.util.List;

public class TUsGSetPushScopeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TUsGSetPushScopeExample() {
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

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
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

        public Criteria andFiPushscopeIsNull() {
            addCriterion("Fi_pushscope is null");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeIsNotNull() {
            addCriterion("Fi_pushscope is not null");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeEqualTo(Integer value) {
            addCriterion("Fi_pushscope =", value, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeNotEqualTo(Integer value) {
            addCriterion("Fi_pushscope <>", value, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeGreaterThan(Integer value) {
            addCriterion("Fi_pushscope >", value, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_pushscope >=", value, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeLessThan(Integer value) {
            addCriterion("Fi_pushscope <", value, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_pushscope <=", value, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeIn(List<Integer> values) {
            addCriterion("Fi_pushscope in", values, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeNotIn(List<Integer> values) {
            addCriterion("Fi_pushscope not in", values, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeBetween(Integer value1, Integer value2) {
            addCriterion("Fi_pushscope between", value1, value2, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiPushscopeNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_pushscope not between", value1, value2, "fiPushscope");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmIsNull() {
            addCriterion("Fi_scopeprm is null");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmIsNotNull() {
            addCriterion("Fi_scopeprm is not null");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmEqualTo(Integer value) {
            addCriterion("Fi_scopeprm =", value, "fiScopeprm");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmNotEqualTo(Integer value) {
            addCriterion("Fi_scopeprm <>", value, "fiScopeprm");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmGreaterThan(Integer value) {
            addCriterion("Fi_scopeprm >", value, "fiScopeprm");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_scopeprm >=", value, "fiScopeprm");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmLessThan(Integer value) {
            addCriterion("Fi_scopeprm <", value, "fiScopeprm");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_scopeprm <=", value, "fiScopeprm");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmIn(List<Integer> values) {
            addCriterion("Fi_scopeprm in", values, "fiScopeprm");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmNotIn(List<Integer> values) {
            addCriterion("Fi_scopeprm not in", values, "fiScopeprm");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmBetween(Integer value1, Integer value2) {
            addCriterion("Fi_scopeprm between", value1, value2, "fiScopeprm");
            return (Criteria) this;
        }

        public Criteria andFiScopeprmNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_scopeprm not between", value1, value2, "fiScopeprm");
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