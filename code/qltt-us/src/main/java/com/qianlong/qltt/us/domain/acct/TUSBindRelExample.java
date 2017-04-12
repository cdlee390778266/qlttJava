package com.qianlong.qltt.us.domain.acct;

import java.util.ArrayList;
import java.util.List;

public class TUSBindRelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TUSBindRelExample() {
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

        public Criteria andFiSvcchnlIsNull() {
            addCriterion("Fi_svcchnl is null");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlIsNotNull() {
            addCriterion("Fi_svcchnl is not null");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlEqualTo(Integer value) {
            addCriterion("Fi_svcchnl =", value, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlNotEqualTo(Integer value) {
            addCriterion("Fi_svcchnl <>", value, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlGreaterThan(Integer value) {
            addCriterion("Fi_svcchnl >", value, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_svcchnl >=", value, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlLessThan(Integer value) {
            addCriterion("Fi_svcchnl <", value, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_svcchnl <=", value, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlIn(List<Integer> values) {
            addCriterion("Fi_svcchnl in", values, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlNotIn(List<Integer> values) {
            addCriterion("Fi_svcchnl not in", values, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlBetween(Integer value1, Integer value2) {
            addCriterion("Fi_svcchnl between", value1, value2, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFiSvcchnlNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_svcchnl not between", value1, value2, "fiSvcchnl");
            return (Criteria) this;
        }

        public Criteria andFsBindacctIsNull() {
            addCriterion("Fs_bindacct is null");
            return (Criteria) this;
        }

        public Criteria andFsBindacctIsNotNull() {
            addCriterion("Fs_bindacct is not null");
            return (Criteria) this;
        }

        public Criteria andFsBindacctEqualTo(String value) {
            addCriterion("Fs_bindacct =", value, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctNotEqualTo(String value) {
            addCriterion("Fs_bindacct <>", value, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctGreaterThan(String value) {
            addCriterion("Fs_bindacct >", value, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_bindacct >=", value, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctLessThan(String value) {
            addCriterion("Fs_bindacct <", value, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctLessThanOrEqualTo(String value) {
            addCriterion("Fs_bindacct <=", value, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctLike(String value) {
            addCriterion("Fs_bindacct like", value, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctNotLike(String value) {
            addCriterion("Fs_bindacct not like", value, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctIn(List<String> values) {
            addCriterion("Fs_bindacct in", values, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctNotIn(List<String> values) {
            addCriterion("Fs_bindacct not in", values, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctBetween(String value1, String value2) {
            addCriterion("Fs_bindacct between", value1, value2, "fsBindacct");
            return (Criteria) this;
        }

        public Criteria andFsBindacctNotBetween(String value1, String value2) {
            addCriterion("Fs_bindacct not between", value1, value2, "fsBindacct");
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