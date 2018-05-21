package com.haohe.plugin.utils;


import com.haohe.plugin.config.CodeGenConfig;
import com.haohe.plugin.config.ConfigHelper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2016/12/19.
 */
public class AliasUtil {


    public static String getClsNameByTableName(String tableName){
        return ConfigUtil.getAlias().getProperty(tableName,null);
    }
    public static String getClsName(String tableName){
        if(tableName == null || "".equals(tableName)){
            return "";
        }
        List<CodeGenConfig.Alias> alias = ConfigHelper.getInstance().getAlias();
        for(CodeGenConfig.Alias a :alias){
            if(tableName.equals(a.getTableName())){
                return a.getClsName();
            }
        }
        return "";
    }


}
