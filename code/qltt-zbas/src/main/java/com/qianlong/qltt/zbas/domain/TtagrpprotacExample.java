package com.qianlong.qltt.zbas.domain;

import java.util.ArrayList;
import java.util.List;

public class TtagrpprotacExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TtagrpprotacExample() {
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

        public Criteria andFiLevelIsNull() {
            addCriterion("Fi_level is null");
            return (Criteria) this;
        }

        public Criteria andFiLevelIsNotNull() {
            addCriterion("Fi_level is not null");
            return (Criteria) this;
        }

        public Criteria andFiLevelEqualTo(Integer value) {
            addCriterion("Fi_level =", value, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFiLevelNotEqualTo(Integer value) {
            addCriterion("Fi_level <>", value, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFiLevelGreaterThan(Integer value) {
            addCriterion("Fi_level >", value, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFiLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_level >=", value, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFiLevelLessThan(Integer value) {
            addCriterion("Fi_level <", value, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFiLevelLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_level <=", value, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFiLevelIn(List<Integer> values) {
            addCriterion("Fi_level in", values, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFiLevelNotIn(List<Integer> values) {
            addCriterion("Fi_level not in", values, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFiLevelBetween(Integer value1, Integer value2) {
            addCriterion("Fi_level between", value1, value2, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFiLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_level not between", value1, value2, "fiLevel");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupIsNull() {
            addCriterion("Fs_ptacgroup is null");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupIsNotNull() {
            addCriterion("Fs_ptacgroup is not null");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupEqualTo(String value) {
            addCriterion("Fs_ptacgroup =", value, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupNotEqualTo(String value) {
            addCriterion("Fs_ptacgroup <>", value, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupGreaterThan(String value) {
            addCriterion("Fs_ptacgroup >", value, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_ptacgroup >=", value, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupLessThan(String value) {
            addCriterion("Fs_ptacgroup <", value, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupLessThanOrEqualTo(String value) {
            addCriterion("Fs_ptacgroup <=", value, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupLike(String value) {
            addCriterion("Fs_ptacgroup like", value, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupNotLike(String value) {
            addCriterion("Fs_ptacgroup not like", value, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupIn(List<String> values) {
            addCriterion("Fs_ptacgroup in", values, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupNotIn(List<String> values) {
            addCriterion("Fs_ptacgroup not in", values, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupBetween(String value1, String value2) {
            addCriterion("Fs_ptacgroup between", value1, value2, "fsPtacgroup");
            return (Criteria) this;
        }

        public Criteria andFsPtacgroupNotBetween(String value1, String value2) {
            addCriterion("Fs_ptacgroup not between", value1, value2, "fsPtacgroup");
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