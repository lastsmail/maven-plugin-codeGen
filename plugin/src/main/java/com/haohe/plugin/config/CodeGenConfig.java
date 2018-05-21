package com.haohe.plugin.config;


import java.util.List;

/**
 * 配置文件载体
 */
public class CodeGenConfig {
    /**
     * JDBC相关配置
     */
    public class JdbcConfig{
        private String driverName;
        private String url;
        private String user;
        private String pwd;

        @Override
        public String toString() {
            return "JdbcConfig{" +
                    "driverName='" + driverName + '\'' +
                    ", url='" + url + '\'' +
                    ", user='" + user + '\'' +
                    ", pwd='" + pwd + '\'' +
                    '}';
        }

        public JdbcConfig() {
        }

        public JdbcConfig(String driverName, String url, String user, String pwd) {
            this.driverName = driverName;
            this.url = url;
            this.user = user;
            this.pwd = pwd;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }

    /**
     * 代码生成器相关配置
     */
    public class CodeGen{
        /**
         * 包
         */
        private String targetPackage;
        /**
         * Model生成器使用的模板
         */
        private String modelTemplate;
        private String pageTemplate;
        private String daoTemplate;
        private String serviceTemplate;
        private String controllerTemplate;
        private String mapperTemplate;
        private String mapperXmlTemplate;
        private String javaScriptTemplate;

        public String getPageTemplate() {
            return pageTemplate;
        }

        public void setPageTemplate(String pageTemplate) {
            this.pageTemplate = pageTemplate;
        }

        public String getDaoTemplate() {
            return daoTemplate;
        }

        public void setDaoTemplate(String daoTemplate) {
            this.daoTemplate = daoTemplate;
        }

        public String getServiceTemplate() {
            return serviceTemplate;
        }

        public void setServiceTemplate(String serviceTemplate) {
            this.serviceTemplate = serviceTemplate;
        }

        public String getControllerTemplate() {
            return controllerTemplate;
        }

        public void setControllerTemplate(String controllerTemplate) {
            this.controllerTemplate = controllerTemplate;
        }

        public String getMapperTemplate() {
            return mapperTemplate;
        }

        public void setMapperTemplate(String mapperTemplate) {
            this.mapperTemplate = mapperTemplate;
        }

        public String getMapperXmlTemplate() {
            return mapperXmlTemplate;
        }

        public void setMapperXmlTemplate(String mapperXmlTemplate) {
            this.mapperXmlTemplate = mapperXmlTemplate;
        }


        public CodeGen() {
        }

        public CodeGen(String targetPackage, String modelTemplate) {
            this.targetPackage = targetPackage;
            this.modelTemplate = modelTemplate;
        }

        public String getTargetPackage() {
            return targetPackage;
        }

        public void setTargetPackage(String targetPackage) {
            this.targetPackage = targetPackage;
        }

        public String getModelTemplate() {
            return modelTemplate;
        }

        public void setModelTemplate(String modelTemplate) {
            this.modelTemplate = modelTemplate;
        }



        public String getJavaScriptTemplate() {
            return javaScriptTemplate;
        }

        public void setJavaScriptTemplate(String javaScriptTemplate) {
            this.javaScriptTemplate = javaScriptTemplate;
        }

        @Override
        public String toString() {
            return "CodeGen{" +
                    "targetPackage='" + targetPackage + '\'' +
                    ", modelTemplate='" + modelTemplate + '\'' +
                    ", pageTemplate='" + pageTemplate + '\'' +
                    ", daoTemplate='" + daoTemplate + '\'' +
                    ", serviceTemplate='" + serviceTemplate + '\'' +
                    ", controllerTemplate='" + controllerTemplate + '\'' +
                    ", mapperTemplate='" + mapperTemplate + '\'' +
                    ", mapperXmlTemplate='" + mapperXmlTemplate + '\'' +
                    ", javaScriptTemplate='" + javaScriptTemplate + '\'' +
                    '}';
        }
    }

    /**
     * 别名相关配置
     */
    public class Alias{
        private String tableName;
        private String clsName;

        @Override
        public String toString() {
            return "Alias{" +
                    "tableName='" + tableName + '\'' +
                    ", clsName='" + clsName + '\'' +
                    '}';
        }

        public Alias() {
        }

        public Alias(String tableName, String clsName) {
            this.tableName = tableName;
            this.clsName = clsName;
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

        
    }

    /**
     * 是否使用子包命名
     */
    private Boolean useModelPackage = false;
    /**
     * 是否使用本地模板
     */
    private Boolean useLocalTemplate= false;

    private JdbcConfig jdbc;
    private CodeGen codeGen;
    private List<Alias> alias;

    public Boolean getUseLocalTemplate() {
        return useLocalTemplate;
    }

    public void setUseLocalTemplate(Boolean useLocalTemplate) {
        this.useLocalTemplate = useLocalTemplate;
    }


    public Boolean getUseModelPackage() {
        return useModelPackage;
    }

    public void setUseModelPackage(Boolean useModelPackage) {
        this.useModelPackage = useModelPackage;
    }

    public CodeGen getCodeGen() {
        return codeGen;
    }

    public void setCodeGen(CodeGen codeGen) {
        this.codeGen = codeGen;
    }

    public List<Alias> getAlias() {
        return alias;
    }

    public void setAlias(List<Alias> alias) {
        this.alias = alias;
    }

    public JdbcConfig getJdbc() {
        return jdbc;
    }

    public void setJdbc(JdbcConfig jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public String toString() {
        return "CodeGenConfig{" +
                "jdbc=" + jdbc +
                ", codeGen=" + codeGen +
                ", useModelPackage=" + useModelPackage +
                ", alias=" + alias +
                ", useLocalTemplate=" + useLocalTemplate +
                '}';
    }
}
