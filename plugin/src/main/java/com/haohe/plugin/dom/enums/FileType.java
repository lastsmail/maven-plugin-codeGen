package com.haohe.plugin.dom.enums;

/**
 *  生成文件目录与类名
 *  如果要移到别处需要自己定义
 */
@Deprecated
public enum  FileType {

    MODEL("/model/",".java"),
    EXAMPLE("/model/","Example.java"),
    DAO("/dao/","Dao.java"),
    XMLMAPPER("/mapper/","Mapper.xml"),
    MAPPER("/mapper/","Mapper.java"),
    SERVICE("/service/","Service.java"),
    SERVICEIMPL("/service/","ServiceImpl.java"),
    CONTROLLER("/controller/","Controller.java"),
    JAVASCRIPT("/js/",".js");

    private String packageName;
    private String stamp;
    FileType(String packageName, String stamp) {
        this.packageName = packageName;
        this.stamp = stamp;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getStamp() {
        return stamp;
    }

}
