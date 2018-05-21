package com.haohe.plugin.jdbc.mysql;

import com.haohe.plugin.config.ConfigHelper;
import com.haohe.plugin.dom.enums.FieldType;
import com.haohe.plugin.dom.model.ClassModel;
import com.haohe.plugin.dom.model.Field;
import com.haohe.plugin.dom.model.PkItem;
import com.haohe.plugin.jdbc.AbsConnectionFactory;
import com.haohe.plugin.job.JobQueue;
import com.haohe.plugin.utils.AliasUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  表转类
 *  @TODO COLUMN_SIZE TINYINT -> BOOLEAN
 */
@SuppressWarnings("Since15")
public class Table2BeanConverter extends AbsConnectionFactory {


    /**
     * 转换
     */
    public static void convert(){
        try (Connection con = AbsConnectionFactory.getConnection()) {
            // 2016年12月21日17:38:51 新的方案
            DatabaseMetaData databaseMetaData = con.getMetaData();
            ResultSet tableRet = databaseMetaData.getTables(con.getCatalog(), databaseMetaData.getUserName(),"%",new String[]{"TABLE"});
            ClassModel classModel;
            List<Field> allField;
            Set<String> imports;
            String clsName;
            String tableName;
            while(tableRet.next()) {
                classModel = new ClassModel();
                allField = new ArrayList<>();
                imports = new HashSet<>();
                tableName=tableRet.getString("TABLE_NAME");
                classModel.setTableName(tableName);
                clsName=AliasUtil.getClsName(tableName);
                if(clsName == null || "".equals(clsName)){
                    continue;
                }
                classModel.setClsName(clsName);
                if(ConfigHelper.getInstance().getUseModelPackage()){
                    classModel.setTargetPackage(ConfigHelper.getInstance().getCodeGen().getTargetPackage()+"."+classModel.getClsName().toLowerCase());
                }else{
                    classModel.setTargetPackage(ConfigHelper.getInstance().getCodeGen().getTargetPackage());
                }
                // 主键处理
                PkItem pkItem = processPk(con.getCatalog(),databaseMetaData,tableName);
                classModel.setPkItem(pkItem);

                ResultSet colRet = databaseMetaData.getColumns(con.getCatalog(),"%", tableName,"%");
                Field field;
                while(colRet.next()) {
                    field = new Field();
                    field.setIsPk(completePk(pkItem,colRet.getString("COLUMN_NAME"),colRet.getBoolean("IS_AUTOINCREMENT")));
                    field.setName(colRet.getString("COLUMN_NAME"));
                    field.setType(getType(colRet.getString("TYPE_NAME")));
                    field.setMemo(colRet.getString("REMARKS"));
                    allField.add(field);
                    selectImport(field.getType(),imports);
                }
                classModel.setFields(allField);
                classModel.setImports(imports);
                JobQueue.add(classModel);
            }

//            ResultSet rs = con.createStatement().executeQuery("show tables");
//            while(rs.next()){
//                ClassModel classModel = new ClassModel();
//                String tableName= rs.getString(1);
//                classModel.setTableName(tableName);
//                classModel.setClsName(AliasUtil.getClsName(tableName));
//
//                if(ConfigHelper.getInstance().getUseModelPackage()){
//                    classModel.setTargetPackage(ConfigHelper.getInstance().getCodeGen().getTargetPackage()+"."+classModel.getClsName().toLowerCase());
//                }else{
//                    classModel.setTargetPackage(ConfigHelper.getInstance().getCodeGen().getTargetPackage());
//                }
//                ResultSet coloms = con.createStatement().executeQuery("SHOW FULL COLUMNS FROM " + tableName);
//                Field tmpField = null;
//                List<Field> allField = new ArrayList<>();
//                Set<String> imports = new HashSet<>();
//                while(coloms.next()){
//                    tmpField = new Field();
//                    tmpField.setName(coloms.getString("Field"));
//                    tmpField.setType(getType(coloms.getString("Type")));
//                    tmpField.setIsPk("PRI".equals(coloms.getString("Key")));
//                    tmpField.setMemo(coloms.getString("Comment"));
//                    allField.add(tmpField);
//                    selectImport(tmpField.getType(),imports);
//                }

//                classModel.setFields(allField);
//                classModel.setImports(imports);
//                JobQueue.add(classModel);
//            }

        }catch (SQLException e){
            logger.error("Sql执行异常:{}",e);
        }
    }

    private static Boolean completePk(PkItem pkItem, String column_name, boolean isAuto) {
        if(pkItem.getIsCom()){
            return false;
        }
        if(column_name.equals(pkItem.getSinglePk())){
            pkItem.setIsAuto(isAuto);
            return true;
        }
        return false;
    }

    private static PkItem processPk(String catalog, DatabaseMetaData databaseMetaData, String tableName) throws SQLException {
        ResultSet pk = databaseMetaData.getPrimaryKeys(catalog, databaseMetaData.getUserName(), tableName);
        PkItem pkItem = new PkItem();
        List<String> pks = new ArrayList<>();
        while (pk.next()){
            String pkName= pk.getString("COLUMN_NAME");
            pkItem.setSinglePk(pkName);
            pks.add(pkName);
        }
        if(pks.size() == 1){
            pkItem.setIsCom(false);
        }else{
            pkItem.setIsCom(true);
            pkItem.setColoms(pks);
        }
        return pkItem;
    }

    /**
     * 筛选是否导入
     * @param type    类型
     * @param imports 导入列表
     */
    private static void selectImport(FieldType type, Set<String> imports) {
        if(!"java.lang.".equals(type.getFullName().substring(0,10))){
            imports.add("import " + type.getFullName() +";");
        }
    }

    /**
     * 获取JDBCType
     * @param type
     * @return
     */
    private static FieldType getType(String type) {
        if(type.indexOf("(") > 1){
            type = type.substring(0,type.indexOf("("));
        }
        if(FieldType.valueOf(type.toUpperCase()).equals(FieldType.INT)){
            return FieldType.INTEGER;
        }
        if(FieldType.valueOf(type.toUpperCase()).equals(FieldType.TEXT)){
            return FieldType.VARCHAR;
        }
        if(FieldType.valueOf(type.toUpperCase()).equals(FieldType.DATETIME)){
            return FieldType.DATE;
        }
        return FieldType.valueOf(type.toUpperCase());
    }






}
