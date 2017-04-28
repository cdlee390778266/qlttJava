package com.qianlong.qltt.us.domain;

import java.util.ArrayList;
import java.util.List;

public class TUsAttnStockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TUsAttnStockExample() {
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

        public Criteria andFiPoolindexIsNull() {
            addCriterion("Fi_poolindex is null");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexIsNotNull() {
            addCriterion("Fi_poolindex is not null");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexEqualTo(Integer value) {
            addCriterion("Fi_poolindex =", value, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexNotEqualTo(Integer value) {
            addCriterion("Fi_poolindex <>", value, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexGreaterThan(Integer value) {
            addCriterion("Fi_poolindex >", value, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_poolindex >=", value, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexLessThan(Integer value) {
            addCriterion("Fi_poolindex <", value, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_poolindex <=", value, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexIn(List<Integer> values) {
            addCriterion("Fi_poolindex in", values, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexNotIn(List<Integer> values) {
            addCriterion("Fi_poolindex not in", values, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexBetween(Integer value1, Integer value2) {
            addCriterion("Fi_poolindex between", value1, value2, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiPoolindexNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_poolindex not between", value1, value2, "fiPoolindex");
            return (Criteria) this;
        }

        public Criteria andFiStockorderIsNull() {
            addCriterion("Fi_stockorder is null");
            return (Criteria) this;
        }

        public Criteria andFiStockorderIsNotNull() {
            addCriterion("Fi_stockorder is not null");
            return (Criteria) this;
        }

        public Criteria andFiStockorderEqualTo(Integer value) {
            addCriterion("Fi_stockorder =", value, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFiStockorderNotEqualTo(Integer value) {
            addCriterion("Fi_stockorder <>", value, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFiStockorderGreaterThan(Integer value) {
            addCriterion("Fi_stockorder >", value, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFiStockorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fi_stockorder >=", value, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFiStockorderLessThan(Integer value) {
            addCriterion("Fi_stockorder <", value, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFiStockorderLessThanOrEqualTo(Integer value) {
            addCriterion("Fi_stockorder <=", value, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFiStockorderIn(List<Integer> values) {
            addCriterion("Fi_stockorder in", values, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFiStockorderNotIn(List<Integer> values) {
            addCriterion("Fi_stockorder not in", values, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFiStockorderBetween(Integer value1, Integer value2) {
            addCriterion("Fi_stockorder between", value1, value2, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFiStockorderNotBetween(Integer value1, Integer value2) {
            addCriterion("Fi_stockorder not between", value1, value2, "fiStockorder");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeIsNull() {
            addCriterion("Fs_stockcode is null");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeIsNotNull() {
            addCriterion("Fs_stockcode is not null");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeEqualTo(String value) {
            addCriterion("Fs_stockcode =", value, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeNotEqualTo(String value) {
            addCriterion("Fs_stockcode <>", value, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeGreaterThan(String value) {
            addCriterion("Fs_stockcode >", value, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_stockcode >=", value, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeLessThan(String value) {
            addCriterion("Fs_stockcode <", value, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeLessThanOrEqualTo(String value) {
            addCriterion("Fs_stockcode <=", value, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeLike(String value) {
            addCriterion("Fs_stockcode like", value, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeNotLike(String value) {
            addCriterion("Fs_stockcode not like", value, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeIn(List<String> values) {
            addCriterion("Fs_stockcode in", values, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeNotIn(List<String> values) {
            addCriterion("Fs_stockcode not in", values, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeBetween(String value1, String value2) {
            addCriterion("Fs_stockcode between", value1, value2, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStockcodeNotBetween(String value1, String value2) {
            addCriterion("Fs_stockcode not between", value1, value2, "fsStockcode");
            return (Criteria) this;
        }

        public Criteria andFsStocknameIsNull() {
            addCriterion("Fs_stockname is null");
            return (Criteria) this;
        }

        public Criteria andFsStocknameIsNotNull() {
            addCriterion("Fs_stockname is not null");
            return (Criteria) this;
        }

        public Criteria andFsStocknameEqualTo(String value) {
            addCriterion("Fs_stockname =", value, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameNotEqualTo(String value) {
            addCriterion("Fs_stockname <>", value, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameGreaterThan(String value) {
            addCriterion("Fs_stockname >", value, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameGreaterThanOrEqualTo(String value) {
            addCriterion("Fs_stockname >=", value, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameLessThan(String value) {
            addCriterion("Fs_stockname <", value, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameLessThanOrEqualTo(String value) {
            addCriterion("Fs_stockname <=", value, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameLike(String value) {
            addCriterion("Fs_stockname like", value, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameNotLike(String value) {
            addCriterion("Fs_stockname not like", value, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameIn(List<String> values) {
            addCriterion("Fs_stockname in", values, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameNotIn(List<String> values) {
            addCriterion("Fs_stockname not in", values, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameBetween(String value1, String value2) {
            addCriterion("Fs_stockname between", value1, value2, "fsStockname");
            return (Criteria) this;
        }

        public Criteria andFsStocknameNotBetween(String value1, String value2) {
            addCriterion("Fs_stockname not between", value1, value2, "fsStockname");
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