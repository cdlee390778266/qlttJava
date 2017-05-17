package com.qianlong.qltt.zbas.domain;

import java.util.ArrayList;
import java.util.List;

public class TtadefprotacticExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TtadefprotacticExample() {
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

        public Criteria andFiTypeIsNull() {
            addCriterion("Fi_type is null");
            return (Criteria) this;
        }

        public Criteria andFiTypeIsNotNull() {
            addCriterion("Fi_type is not null");
            return (Criteria) this;
        }

        public Criteria andFiTypeEqualTo(Integer value) {
            addCriterion("Fi_type =", value, "fiType");
            return (Criteria) this;
        }

        public Criteria andFiTypeNotEqualTo(Integer value) {
            addCriterion("Fi_type <>", value, "fiType");
            return (Criteria) this;
        }

        public Criteria andFiTypeGreaterThan(Integer value) {
            addCriterion("Fi_type >", value, "fiType");
            return (Criteria) this;
        }

        public Criteria andFiTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_type >=", value, "fiType");
            return (Criteria) this;
        }

        public Criteria andFiTypeLessThan(Integer value) {
            addCriterion("Fi_type <", value, "fiType");
            return (Criteria) this;
        }

        public Criteria andFiTypeLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_type <=", value, "fiType");
            return (Criteria) this;
        }

        public Criteria andFiTypeIn(List<Integer> values) {
            addCriterion("Fi_type in", values, "fiType");
            return (Criteria) this;
        }

        public Criteria andFiTypeNotIn(List<Integer> values) {
            addCriterion("Fi_type not in", values, "fiType");
            return (Criteria) this;
        }

        public Criteria andFiTypeBetween(Integer value1, Integer value2) {
            addCriterion("Fi_type between", value1, value2, "fiType");
            return (Criteria) this;
        }

        public Criteria andFiTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_type not between", value1, value2, "fiType");
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

        public Criteria andFsDetailIsNull() {
            addCriterion("Fs_detail is null");
            return (Criteria) this;
        }

        public Criteria andFsDetailIsNotNull() {
            addCriterion("Fs_detail is not null");
            return (Criteria) this;
        }

        public Criteria andFsDetailEqualTo(String value) {
            addCriterion("Fs_detail =", value, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailNotEqualTo(String value) {
            addCriterion("Fs_detail <>", value, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailGreaterThan(String value) {
            addCriterion("Fs_detail >", value, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_detail >=", value, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailLessThan(String value) {
            addCriterion("Fs_detail <", value, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailLessThanOrEqualTo(String value) {
            addCriterion("Fs_detail <=", value, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailLike(String value) {
            addCriterion("Fs_detail like", value, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailNotLike(String value) {
            addCriterion("Fs_detail not like", value, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailIn(List<String> values) {
            addCriterion("Fs_detail in", values, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailNotIn(List<String> values) {
            addCriterion("Fs_detail not in", values, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailBetween(String value1, String value2) {
            addCriterion("Fs_detail between", value1, value2, "fsDetail");
            return (Criteria) this;
        }

        public Criteria andFsDetailNotBetween(String value1, String value2) {
            addCriterion("Fs_detail not between", value1, value2, "fsDetail");
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