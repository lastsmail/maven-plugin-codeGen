package com.haohe.plugin.jdbc;



import com.haohe.plugin.config.ConfigHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 抽象链接工厂
 */
public abstract class AbsConnectionFactory implements AutoCloseable {

    public static Logger logger = LoggerFactory.getLogger(AbsConnectionFactory.class);
    public static Connection connection;
//    public static Properties config;
//    static {
//        config = new Properties();
//        try {
//            config.load(AbsConnectionFactory.class.getResourceAsStream("/config.properties"));
//            Class.forName(config.getProperty("DriverName","com.mysql.jdbc.Driver"));
//        } catch (IOException e) {
//            logger.error("载入配置文件错误:{}",e);
//        } catch (ClassNotFoundException e) {
//            logger.error("加载驱动失败:{}",e);
//        }
//    }

    public static Connection getConnection(){
//        String url = ConfigUtil.getConfig().getProperty("url");
//        String user = ConfigUtil.getConfig().getProperty("user");
//        String pwd = ConfigUtil.getConfig().getProperty("pwd");
       String url = ConfigHelper.getInstance().getJdbc().getUrl();
       String user = ConfigHelper.getInstance().getJdbc().getUser();
       String pwd =ConfigHelper.getInstance().getJdbc().getPwd();
       String driver=ConfigHelper.getInstance().getJdbc().getDriverName();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("加载驱动失败",e);
        }
        if(connection == null ){
            try {
                connection = DriverManager.getConnection(url,user,pwd);
            } catch (SQLException e) {
                logger.error("获取驱动失败",e);
            }
        }
        return connection;

    }

    public void close() throws Exception {
        if(connection != null){
            connection.close();
        }
    }

}
