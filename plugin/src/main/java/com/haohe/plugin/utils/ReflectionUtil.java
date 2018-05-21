package com.haohe.plugin.utils;

import com.haohe.plugin.generator.AbsGenerator;

/**
 * Created by lenovo on 2016/12/21.
 */
public class ReflectionUtil {

    public static String DEFAULT_GEN_STAMP = "Gen";
    public static String convertClsName(String name){
        Package packageName = AbsGenerator.class.getPackage();
        return packageName.getName()+".impl."+name.substring(0,1).toUpperCase()+name.substring(1)+DEFAULT_GEN_STAMP;
    }

    public static String first2Upper(String str){
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

}
