package com.qianlong.qltt.us.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TUsSysPtcCallTmpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TUsSysPtcCallTmpExample() {
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

        public Criteria andFsAppidIsNull() {
            addCriterion("Fs_appid is null");
            return (Criteria) this;
        }

        public Criteria andFsAppidIsNotNull() {
            addCriterion("Fs_appid is not null");
            return (Criteria) this;
        }

        public Criteria andFsAppidEqualTo(String value) {
            addCriterion("Fs_appid =", value, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidNotEqualTo(String value) {
            addCriterion("Fs_appid <>", value, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidGreaterThan(String value) {
            addCriterion("Fs_appid >", value, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_appid >=", value, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidLessThan(String value) {
            addCriterion("Fs_appid <", value, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidLessThanOrEqualTo(String value) {
            addCriterion("Fs_appid <=", value, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidLike(String value) {
            addCriterion("Fs_appid like", value, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidNotLike(String value) {
            addCriterion("Fs_appid not like", value, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidIn(List<String> values) {
            addCriterion("Fs_appid in", values, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidNotIn(List<String> values) {
            addCriterion("Fs_appid not in", values, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidBetween(String value1, String value2) {
            addCriterion("Fs_appid between", value1, value2, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsAppidNotBetween(String value1, String value2) {
            addCriterion("Fs_appid not between", value1, value2, "fsAppid");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoIsNull() {
            addCriterion("Fs_ptlno is null");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoIsNotNull() {
            addCriterion("Fs_ptlno is not null");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoEqualTo(String value) {
            addCriterion("Fs_ptlno =", value, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoNotEqualTo(String value) {
            addCriterion("Fs_ptlno <>", value, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoGreaterThan(String value) {
            addCriterion("Fs_ptlno >", value, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_ptlno >=", value, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoLessThan(String value) {
            addCriterion("Fs_ptlno <", value, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoLessThanOrEqualTo(String value) {
            addCriterion("Fs_ptlno <=", value, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoLike(String value) {
            addCriterion("Fs_ptlno like", value, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoNotLike(String value) {
            addCriterion("Fs_ptlno not like", value, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoIn(List<String> values) {
            addCriterion("Fs_ptlno in", values, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoNotIn(List<String> values) {
            addCriterion("Fs_ptlno not in", values, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoBetween(String value1, String value2) {
            addCriterion("Fs_ptlno between", value1, value2, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsPtlnoNotBetween(String value1, String value2) {
            addCriterion("Fs_ptlno not between", value1, value2, "fsPtlno");
            return (Criteria) this;
        }

        public Criteria andFsRequrlIsNull() {
            addCriterion("Fs_requrl is null");
            return (Criteria) this;
        }

        public Criteria andFsRequrlIsNotNull() {
            addCriterion("Fs_requrl is not null");
            return (Criteria) this;
        }

        public Criteria andFsRequrlEqualTo(String value) {
            addCriterion("Fs_requrl =", value, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlNotEqualTo(String value) {
            addCriterion("Fs_requrl <>", value, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlGreaterThan(String value) {
            addCriterion("Fs_requrl >", value, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_requrl >=", value, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlLessThan(String value) {
            addCriterion("Fs_requrl <", value, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlLessThanOrEqualTo(String value) {
            addCriterion("Fs_requrl <=", value, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlLike(String value) {
            addCriterion("Fs_requrl like", value, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlNotLike(String value) {
            addCriterion("Fs_requrl not like", value, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlIn(List<String> values) {
            addCriterion("Fs_requrl in", values, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlNotIn(List<String> values) {
            addCriterion("Fs_requrl not in", values, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlBetween(String value1, String value2) {
            addCriterion("Fs_requrl between", value1, value2, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFsRequrlNotBetween(String value1, String value2) {
            addCriterion("Fs_requrl not between", value1, value2, "fsRequrl");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeIsNull() {
            addCriterion("Ft_ptllastcalltime is null");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeIsNotNull() {
            addCriterion("Ft_ptllastcalltime is not null");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeEqualTo(Timestamp value) {
            addCriterion("Ft_ptllastcalltime =", value, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeNotEqualTo(Timestamp value) {
            addCriterion("Ft_ptllastcalltime <>", value, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeGreaterThan(Timestamp value) {
            addCriterion("Ft_ptllastcalltime >", value, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("Ft_ptllastcalltime >=", value, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeLessThan(Timestamp value) {
            addCriterion("Ft_ptllastcalltime <", value, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("Ft_ptllastcalltime <=", value, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeIn(List<Timestamp> values) {
            addCriterion("Ft_ptllastcalltime in", values, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeNotIn(List<Timestamp> values) {
            addCriterion("Ft_ptllastcalltime not in", values, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("Ft_ptllastcalltime between", value1, value2, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFtPtllastcalltimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("Ft_ptllastcalltime not between", value1, value2, "ftPtllastcalltime");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumIsNull() {
            addCriterion("Fi_ptlcallnum is null");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumIsNotNull() {
            addCriterion("Fi_ptlcallnum is not null");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumEqualTo(Integer value) {
            addCriterion("Fi_ptlcallnum =", value, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumNotEqualTo(Integer value) {
            addCriterion("Fi_ptlcallnum <>", value, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumGreaterThan(Integer value) {
            addCriterion("Fi_ptlcallnum >", value, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_ptlcallnum >=", value, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumLessThan(Integer value) {
            addCriterion("Fi_ptlcallnum <", value, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_ptlcallnum <=", value, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumIn(List<Integer> values) {
            addCriterion("Fi_ptlcallnum in", values, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumNotIn(List<Integer> values) {
            addCriterion("Fi_ptlcallnum not in", values, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumBetween(Integer value1, Integer value2) {
            addCriterion("Fi_ptlcallnum between", value1, value2, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlcallnumNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_ptlcallnum not between", value1, value2, "fiPtlcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumIsNull() {
            addCriterion("Fi_ptlmaxCallNum is null");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumIsNotNull() {
            addCriterion("Fi_ptlmaxCallNum is not null");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumEqualTo(Integer value) {
            addCriterion("Fi_ptlmaxCallNum =", value, "fiPtlmaxcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumNotEqualTo(Integer value) {
            addCriterion("Fi_ptlmaxCallNum <>", value, "fiPtlmaxcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumGreaterThan(Integer value) {
            addCriterion("Fi_ptlmaxCallNum >", value, "fiPtlmaxcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_ptlmaxCallNum >=", value, "fiPtlmaxcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumLessThan(Integer value) {
            addCriterion("Fi_ptlmaxCallNum <", value, "fiPtlmaxcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_ptlmaxCallNum <=", value, "fiPtlmaxcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumIn(List<Integer> values) {
            addCriterion("Fi_ptlmaxCallNum in", values, "fiPtlmaxcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumNotIn(List<Integer> values) {
            addCriterion("Fi_ptlmaxCallNum not in", values, "fiPtlmaxcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumBetween(Integer value1, Integer value2) {
            addCriterion("Fi_ptlmaxCallNum between", value1, value2, "fiPtlmaxcallnum");
            return (Criteria) this;
        }

        public Criteria andFiPtlmaxcallnumNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_ptlmaxCallNum not between", value1, value2, "fiPtlmaxcallnum");
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