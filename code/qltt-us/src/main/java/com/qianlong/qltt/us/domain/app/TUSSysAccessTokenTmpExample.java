package com.qianlong.qltt.us.domain.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TUSSysAccessTokenTmpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TUSSysAccessTokenTmpExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andFsAccesstokenIsNull() {
            addCriterion("Fs_accesstoken is null");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenIsNotNull() {
            addCriterion("Fs_accesstoken is not null");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenEqualTo(String value) {
            addCriterion("Fs_accesstoken =", value, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenNotEqualTo(String value) {
            addCriterion("Fs_accesstoken <>", value, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenGreaterThan(String value) {
            addCriterion("Fs_accesstoken >", value, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_accesstoken >=", value, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenLessThan(String value) {
            addCriterion("Fs_accesstoken <", value, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenLessThanOrEqualTo(String value) {
            addCriterion("Fs_accesstoken <=", value, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenLike(String value) {
            addCriterion("Fs_accesstoken like", value, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenNotLike(String value) {
            addCriterion("Fs_accesstoken not like", value, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenIn(List<String> values) {
            addCriterion("Fs_accesstoken in", values, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenNotIn(List<String> values) {
            addCriterion("Fs_accesstoken not in", values, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenBetween(String value1, String value2) {
            addCriterion("Fs_accesstoken between", value1, value2, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFsAccesstokenNotBetween(String value1, String value2) {
            addCriterion("Fs_accesstoken not between", value1, value2, "fsAccesstoken");
            return (Criteria) this;
        }

        public Criteria andFtTimestampIsNull() {
            addCriterion("Ft_timestamp is null");
            return (Criteria) this;
        }

        public Criteria andFtTimestampIsNotNull() {
            addCriterion("Ft_timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andFtTimestampEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_timestamp =", value, "ftTimestamp");
            return (Criteria) this;
        }

        public Criteria andFtTimestampNotEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_timestamp <>", value, "ftTimestamp");
            return (Criteria) this;
        }

        public Criteria andFtTimestampGreaterThan(Date value) {
            addCriterionForJDBCDate("Ft_timestamp >", value, "ftTimestamp");
            return (Criteria) this;
        }

        public Criteria andFtTimestampGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_timestamp >=", value, "ftTimestamp");
            return (Criteria) this;
        }

        public Criteria andFtTimestampLessThan(Date value) {
            addCriterionForJDBCDate("Ft_timestamp <", value, "ftTimestamp");
            return (Criteria) this;
        }

        public Criteria andFtTimestampLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_timestamp <=", value, "ftTimestamp");
            return (Criteria) this;
        }

        public Criteria andFtTimestampIn(List<Date> values) {
            addCriterionForJDBCDate("Ft_timestamp in", values, "ftTimestamp");
            return (Criteria) this;
        }

        public Criteria andFtTimestampNotIn(List<Date> values) {
            addCriterionForJDBCDate("Ft_timestamp not in", values, "ftTimestamp");
            return (Criteria) this;
        }

        public Criteria andFtTimestampBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("Ft_timestamp between", value1, value2, "ftTimestamp");
            return (Criteria) this;
        }

        public Criteria andFtTimestampNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("Ft_timestamp not between", value1, value2, "ftTimestamp");
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