package com.haohe.plugin.dom.enums;

/**
 * 表属性转JAVA属性
 * @TODO VARBINARY Byte[] 数组类型需要处理
 */
public enum  FieldType {
    INT("Integer","java.lang.Integer"),
    TINYINT("Byte","java.lang.Byte"),
    SMALLINT("Integer","java.lang.Integer"),
    BIT("Byte","java.lang.Byte"),
    DECIMAL("BigDecimal","java.math.BigDecimal"),
    NUMERIC("Long","java.lang.Long"),
    CHAR("String","java.lang.String"),
    VARCHAR("String","java.lang.String"),
    DATE("Date","java.util.Date"),
    TIME("Date","java.util.Date"),
    DATETIME("Date","java.util.Date"),
    TIMESTAMP("Date","java.util.Date"),
    TEXT("String","java.lang.String"),
    LONGTEXT("String","java.lang.String"),
    BLOB("String","java.lang.String"),
    CLOB("String","java.lang.String"),
    BOOLEAN("Boolean","java.lang.Boolean"),
    FLOAT("BigDecimal","java.math.BigDecimal"),
    DOUBLE("BigDecimal","java.math.BigDecimal"),
    INTEGER("Integer","java.lang.Integer");


    private String type;
    private String lable;
    private String fullName;

    public String getFullName() {
        return fullName;
    }
    public String getType() {
        return type;
    }
    public String getLable() {
        return lable;
    }

    @Deprecated
    FieldType(String type){
        this.type = type;
        this.lable=this.toString();
    }
    FieldType(String type, String fullName){
        this.type = type;
        this.fullName = fullName;
        this.lable=this.toString();
    }

}
