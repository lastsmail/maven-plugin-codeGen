package com.haohe.plugin.dom.model;

import java.util.List;

/**
 * 主键
 */
public class PkItem {

    /**
     * 是否组合主键
     */
    private Boolean isCom;

    /**
     * 单列主键
     */
    private String singlePk;

    /**
     * 主键列
     */
    private List<String> coloms;
    /**
     * 是否自增
     */
    private Boolean isAuto;

    public Boolean getIsCom() {
        return isCom;
    }

    public void setIsCom(Boolean com) {
        isCom = com;
    }

    public List<String> getColoms() {
        return coloms;
    }

    public void setColoms(List<String> coloms) {
        this.coloms = coloms;
    }

    public Boolean getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(Boolean auto) {
        isAuto = auto;
    }

    public String getSinglePk() {
        return singlePk;
    }

    public void setSinglePk(String singlePk) {
        this.singlePk = singlePk;
    }

    @Override
    public String toString() {
        return "PkItem{" +
                "isCom=" + isCom +
                ", singlePk=" + singlePk +
                ", coloms=" + coloms +
                ", isAuto=" + isAuto +
                '}';
    }
}
