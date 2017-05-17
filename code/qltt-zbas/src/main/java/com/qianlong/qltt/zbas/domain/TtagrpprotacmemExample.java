package com.qianlong.qltt.zbas.domain;

import java.util.ArrayList;
import java.util.List;

public class TtagrpprotacmemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TtagrpprotacmemExample() {
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

        public Criteria andFiIndexIsNull() {
            addCriterion("Fi_index is null");
            return (Criteria) this;
        }

        public Criteria andFiIndexIsNotNull() {
            addCriterion("Fi_index is not null");
            return (Criteria) this;
        }

        public Criteria andFiIndexEqualTo(Integer value) {
            addCriterion("Fi_index =", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexNotEqualTo(Integer value) {
            addCriterion("Fi_index <>", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexGreaterThan(Integer value) {
            addCriterion("Fi_index >", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_index >=", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexLessThan(Integer value) {
            addCriterion("Fi_index <", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_index <=", value, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexIn(List<Integer> values) {
            addCriterion("Fi_index in", values, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexNotIn(List<Integer> values) {
            addCriterion("Fi_index not in", values, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexBetween(Integer value1, Integer value2) {
            addCriterion("Fi_index between", value1, value2, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFiIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_index not between", value1, value2, "fiIndex");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupIsNull() {
            addCriterion("Fs_tacgroup is null");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupIsNotNull() {
            addCriterion("Fs_tacgroup is not null");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupEqualTo(String value) {
            addCriterion("Fs_tacgroup =", value, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupNotEqualTo(String value) {
            addCriterion("Fs_tacgroup <>", value, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupGreaterThan(String value) {
            addCriterion("Fs_tacgroup >", value, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_tacgroup >=", value, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupLessThan(String value) {
            addCriterion("Fs_tacgroup <", value, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupLessThanOrEqualTo(String value) {
            addCriterion("Fs_tacgroup <=", value, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupLike(String value) {
            addCriterion("Fs_tacgroup like", value, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupNotLike(String value) {
            addCriterion("Fs_tacgroup not like", value, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupIn(List<String> values) {
            addCriterion("Fs_tacgroup in", values, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupNotIn(List<String> values) {
            addCriterion("Fs_tacgroup not in", values, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupBetween(String value1, String value2) {
            addCriterion("Fs_tacgroup between", value1, value2, "fsTacgroup");
            return (Criteria) this;
        }

        public Criteria andFsTacgroupNotBetween(String value1, String value2) {
            addCriterion("Fs_tacgroup not between", value1, value2, "fsTacgroup");
            return (Criteria) this;
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