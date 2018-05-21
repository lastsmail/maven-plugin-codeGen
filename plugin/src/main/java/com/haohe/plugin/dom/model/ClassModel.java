package com.haohe.plugin.dom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *  类对象
 *  表载体
 */
public class ClassModel {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 类名
     */
    private String clsName;
    /**
     * 包名
     */
    private String targetPackage;
    /**
     * 主键
     */
    private PkItem pkItem;

    /**
     * 导入类
     */
    private Set<String> imports;
    /**
     * 属性
     */
    private List<Field> fields = new ArrayList<>();

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
    public Set<String> getImports() {
        return imports;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
    }
    public PkItem getPkItem() {
        return pkItem;
    }

    public void setPkItem(PkItem pkItem) {
        this.pkItem = pkItem;
    }

    @Override
    public String toString() {
        return "ClassModel{" +
                "tableName='" + tableName + '\'' +
                ", clsName='" + clsName + '\'' +
                ", targetPackage='" + targetPackage + '\'' +
                ", pkItem=" + pkItem +
                ", imports=" + imports +
                ", fields=" + fields +
                '}';
    }
}
