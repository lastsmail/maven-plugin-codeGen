package com.haohe.plugin.dom.model;

import com.haohe.plugin.dom.enums.FieldType;

/**
 *  属性
 */
public class Field {
    /**
     * 属性名
     */
    private String name;
    /**
     * 类型
     */
    private FieldType type;
    /**
     * 注释
     */
    private String memo;
    /**
     * 是否主键
     */
    private Boolean isPk;



    public Boolean getIsPk() {
        return isPk;
    }

    public void setIsPk(Boolean pk) {
        isPk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", memo='" + memo + '\'' +
                ", isPk=" + isPk +
                '}';
    }
}
