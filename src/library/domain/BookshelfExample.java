package library.domain;

import java.util.ArrayList;
import java.util.List;

public class BookshelfExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BookshelfExample() {
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

        public Criteria andIdBookshelfIsNull() {
            addCriterion("id_bookshelf is null");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfIsNotNull() {
            addCriterion("id_bookshelf is not null");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfEqualTo(Integer value) {
            addCriterion("id_bookshelf =", value, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfNotEqualTo(Integer value) {
            addCriterion("id_bookshelf <>", value, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfGreaterThan(Integer value) {
            addCriterion("id_bookshelf >", value, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_bookshelf >=", value, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfLessThan(Integer value) {
            addCriterion("id_bookshelf <", value, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfLessThanOrEqualTo(Integer value) {
            addCriterion("id_bookshelf <=", value, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfIn(List<Integer> values) {
            addCriterion("id_bookshelf in", values, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfNotIn(List<Integer> values) {
            addCriterion("id_bookshelf not in", values, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfBetween(Integer value1, Integer value2) {
            addCriterion("id_bookshelf between", value1, value2, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdBookshelfNotBetween(Integer value1, Integer value2) {
            addCriterion("id_bookshelf not between", value1, value2, "idBookshelf");
            return (Criteria) this;
        }

        public Criteria andIdLibraryIsNull() {
            addCriterion("id_library is null");
            return (Criteria) this;
        }

        public Criteria andIdLibraryIsNotNull() {
            addCriterion("id_library is not null");
            return (Criteria) this;
        }

        public Criteria andIdLibraryEqualTo(Integer value) {
            addCriterion("id_library =", value, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andIdLibraryNotEqualTo(Integer value) {
            addCriterion("id_library <>", value, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andIdLibraryGreaterThan(Integer value) {
            addCriterion("id_library >", value, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andIdLibraryGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_library >=", value, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andIdLibraryLessThan(Integer value) {
            addCriterion("id_library <", value, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andIdLibraryLessThanOrEqualTo(Integer value) {
            addCriterion("id_library <=", value, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andIdLibraryIn(List<Integer> values) {
            addCriterion("id_library in", values, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andIdLibraryNotIn(List<Integer> values) {
            addCriterion("id_library not in", values, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andIdLibraryBetween(Integer value1, Integer value2) {
            addCriterion("id_library between", value1, value2, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andIdLibraryNotBetween(Integer value1, Integer value2) {
            addCriterion("id_library not between", value1, value2, "idLibrary");
            return (Criteria) this;
        }

        public Criteria andCodeIdIsNull() {
            addCriterion("code_id is null");
            return (Criteria) this;
        }

        public Criteria andCodeIdIsNotNull() {
            addCriterion("code_id is not null");
            return (Criteria) this;
        }

        public Criteria andCodeIdEqualTo(String value) {
            addCriterion("code_id =", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotEqualTo(String value) {
            addCriterion("code_id <>", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdGreaterThan(String value) {
            addCriterion("code_id >", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdGreaterThanOrEqualTo(String value) {
            addCriterion("code_id >=", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLessThan(String value) {
            addCriterion("code_id <", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLessThanOrEqualTo(String value) {
            addCriterion("code_id <=", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLike(String value) {
            addCriterion("code_id like", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotLike(String value) {
            addCriterion("code_id not like", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdIn(List<String> values) {
            addCriterion("code_id in", values, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotIn(List<String> values) {
            addCriterion("code_id not in", values, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdBetween(String value1, String value2) {
            addCriterion("code_id between", value1, value2, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotBetween(String value1, String value2) {
            addCriterion("code_id not between", value1, value2, "codeId");
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