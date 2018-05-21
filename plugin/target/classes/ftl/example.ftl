<#if targetPackage??>
package ${targetPackage}.model;
</#if>

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
<#list imports as i>
${i}
</#list>
import com.haohe.base.model.BaseExample;

public class ${clsName!tableName}Example extends BaseExample{

    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public ${clsName!tableName}Example() {
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
<#import "common.ftl" as com>
<#list fields as f>
        public Criteria and<@com.methodName f.name />IsNull() {
            addCriterion("${f.name} is null");
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />IsNotNull() {
            addCriterion("${f.name} is not null");
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />EqualTo(${f.type.type} value) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} =", value, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} =", value, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />NotEqualTo(${f.type.type} value) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} <>", value, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} <>", value, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />GreaterThan(${f.type.type} value) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} >", value, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} >", value, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />GreaterThanOrEqualTo(${f.type.type} value) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} >=", value, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} >=", value, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />LessThan(${f.type.type} value) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} <", value, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} <", value, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />LessThanOrEqualTo(${f.type.type} value) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} <=", value, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} <=", value, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />Like(${f.type.type} value) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} like", value, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} like", value, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />NotLike(${f.type.type} value) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} not like", value, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} not like", value, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />In(List<${f.type.type}> values) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} in", values, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} in", values, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />NotIn(List<${f.type.type}> values) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} not in", values, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} not in", values, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />Between(${f.type.type} value1, ${f.type.type} value2) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} between", value1, value2, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name}  between", value1, value2, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
        public Criteria and<@com.methodName f.name />NotBetween(${f.type.type} value1, ${f.type.type} value2) {
            <#if (f.type.fullName=="java.util.Date")>
            addCriterionForJDBCDate("${f.name} not between", value1, value2, "<@com.coloum2Field f.name />");
            <#else >
            addCriterion("${f.name} not between", value1, value2, "<@com.coloum2Field f.name />");
            </#if>
            return (Criteria) this;
        }
</#list>

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