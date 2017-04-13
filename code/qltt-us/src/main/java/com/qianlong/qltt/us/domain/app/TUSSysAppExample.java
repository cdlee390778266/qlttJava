package com.qianlong.qltt.us.domain.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TUSSysAppExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TUSSysAppExample() {
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

        public Criteria andFsApptokenIsNull() {
            addCriterion("Fs_apptoken is null");
            return (Criteria) this;
        }

        public Criteria andFsApptokenIsNotNull() {
            addCriterion("Fs_apptoken is not null");
            return (Criteria) this;
        }

        public Criteria andFsApptokenEqualTo(String value) {
            addCriterion("Fs_apptoken =", value, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenNotEqualTo(String value) {
            addCriterion("Fs_apptoken <>", value, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenGreaterThan(String value) {
            addCriterion("Fs_apptoken >", value, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_apptoken >=", value, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenLessThan(String value) {
            addCriterion("Fs_apptoken <", value, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenLessThanOrEqualTo(String value) {
            addCriterion("Fs_apptoken <=", value, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenLike(String value) {
            addCriterion("Fs_apptoken like", value, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenNotLike(String value) {
            addCriterion("Fs_apptoken not like", value, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenIn(List<String> values) {
            addCriterion("Fs_apptoken in", values, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenNotIn(List<String> values) {
            addCriterion("Fs_apptoken not in", values, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenBetween(String value1, String value2) {
            addCriterion("Fs_apptoken between", value1, value2, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsApptokenNotBetween(String value1, String value2) {
            addCriterion("Fs_apptoken not between", value1, value2, "fsApptoken");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretIsNull() {
            addCriterion("Fs_appsecret is null");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretIsNotNull() {
            addCriterion("Fs_appsecret is not null");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretEqualTo(String value) {
            addCriterion("Fs_appsecret =", value, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretNotEqualTo(String value) {
            addCriterion("Fs_appsecret <>", value, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretGreaterThan(String value) {
            addCriterion("Fs_appsecret >", value, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_appsecret >=", value, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretLessThan(String value) {
            addCriterion("Fs_appsecret <", value, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretLessThanOrEqualTo(String value) {
            addCriterion("Fs_appsecret <=", value, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretLike(String value) {
            addCriterion("Fs_appsecret like", value, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretNotLike(String value) {
            addCriterion("Fs_appsecret not like", value, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretIn(List<String> values) {
            addCriterion("Fs_appsecret in", values, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretNotIn(List<String> values) {
            addCriterion("Fs_appsecret not in", values, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretBetween(String value1, String value2) {
            addCriterion("Fs_appsecret between", value1, value2, "fsAppsecret");
            return (Criteria) this;
        }

        public Criteria andFsAppsecretNotBetween(String value1, String value2) {
            addCriterion("Fs_appsecret not between", value1, value2, "fsAppsecret");
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

        public Criteria andFtRegtimeEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_regtime =", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_regtime <>", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("Ft_regtime >", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_regtime >=", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeLessThan(Date value) {
            addCriterionForJDBCDate("Ft_regtime <", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_regtime <=", value, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeIn(List<Date> values) {
            addCriterionForJDBCDate("Ft_regtime in", values, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("Ft_regtime not in", values, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("Ft_regtime between", value1, value2, "ftRegtime");
            return (Criteria) this;
        }

        public Criteria andFtRegtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("Ft_regtime not between", value1, value2, "ftRegtime");
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

        public Criteria andFtUpdtimeEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_updtime =", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_updtime <>", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("Ft_updtime >", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_updtime >=", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeLessThan(Date value) {
            addCriterionForJDBCDate("Ft_updtime <", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("Ft_updtime <=", value, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeIn(List<Date> values) {
            addCriterionForJDBCDate("Ft_updtime in", values, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("Ft_updtime not in", values, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("Ft_updtime between", value1, value2, "ftUpdtime");
            return (Criteria) this;
        }

        public Criteria andFtUpdtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("Ft_updtime not between", value1, value2, "ftUpdtime");
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