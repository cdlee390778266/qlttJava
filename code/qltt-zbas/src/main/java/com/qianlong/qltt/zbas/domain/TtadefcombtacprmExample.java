package com.qianlong.qltt.zbas.domain;

import java.util.ArrayList;
import java.util.List;

public class TtadefcombtacprmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TtadefcombtacprmExample() {
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

        public Criteria andFsTacticIsNull() {
            addCriterion("Fs_tactic is null");
            return (Criteria) this;
        }

        public Criteria andFsTacticIsNotNull() {
            addCriterion("Fs_tactic is not null");
            return (Criteria) this;
        }

        public Criteria andFsTacticEqualTo(String value) {
            addCriterion("Fs_tactic =", value, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticNotEqualTo(String value) {
            addCriterion("Fs_tactic <>", value, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticGreaterThan(String value) {
            addCriterion("Fs_tactic >", value, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_tactic >=", value, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticLessThan(String value) {
            addCriterion("Fs_tactic <", value, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticLessThanOrEqualTo(String value) {
            addCriterion("Fs_tactic <=", value, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticLike(String value) {
            addCriterion("Fs_tactic like", value, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticNotLike(String value) {
            addCriterion("Fs_tactic not like", value, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticIn(List<String> values) {
            addCriterion("Fs_tactic in", values, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticNotIn(List<String> values) {
            addCriterion("Fs_tactic not in", values, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticBetween(String value1, String value2) {
            addCriterion("Fs_tactic between", value1, value2, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsTacticNotBetween(String value1, String value2) {
            addCriterion("Fs_tactic not between", value1, value2, "fsTactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticIsNull() {
            addCriterion("Fs_srctactic is null");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticIsNotNull() {
            addCriterion("Fs_srctactic is not null");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticEqualTo(String value) {
            addCriterion("Fs_srctactic =", value, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticNotEqualTo(String value) {
            addCriterion("Fs_srctactic <>", value, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticGreaterThan(String value) {
            addCriterion("Fs_srctactic >", value, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_srctactic >=", value, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticLessThan(String value) {
            addCriterion("Fs_srctactic <", value, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticLessThanOrEqualTo(String value) {
            addCriterion("Fs_srctactic <=", value, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticLike(String value) {
            addCriterion("Fs_srctactic like", value, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticNotLike(String value) {
            addCriterion("Fs_srctactic not like", value, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticIn(List<String> values) {
            addCriterion("Fs_srctactic in", values, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticNotIn(List<String> values) {
            addCriterion("Fs_srctactic not in", values, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticBetween(String value1, String value2) {
            addCriterion("Fs_srctactic between", value1, value2, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFsSrctacticNotBetween(String value1, String value2) {
            addCriterion("Fs_srctactic not between", value1, value2, "fsSrctactic");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmIsNull() {
            addCriterion("Fi_srctacticprm is null");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmIsNotNull() {
            addCriterion("Fi_srctacticprm is not null");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmEqualTo(Integer value) {
            addCriterion("Fi_srctacticprm =", value, "fiSrctacticprm");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmNotEqualTo(Integer value) {
            addCriterion("Fi_srctacticprm <>", value, "fiSrctacticprm");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmGreaterThan(Integer value) {
            addCriterion("Fi_srctacticprm >", value, "fiSrctacticprm");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_srctacticprm >=", value, "fiSrctacticprm");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmLessThan(Integer value) {
            addCriterion("Fi_srctacticprm <", value, "fiSrctacticprm");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_srctacticprm <=", value, "fiSrctacticprm");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmIn(List<Integer> values) {
            addCriterion("Fi_srctacticprm in", values, "fiSrctacticprm");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmNotIn(List<Integer> values) {
            addCriterion("Fi_srctacticprm not in", values, "fiSrctacticprm");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmBetween(Integer value1, Integer value2) {
            addCriterion("Fi_srctacticprm between", value1, value2, "fiSrctacticprm");
            return (Criteria) this;
        }

        public Criteria andFiSrctacticprmNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_srctacticprm not between", value1, value2, "fiSrctacticprm");
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