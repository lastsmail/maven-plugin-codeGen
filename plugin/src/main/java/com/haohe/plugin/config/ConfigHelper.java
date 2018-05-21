package com.haohe.plugin.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *  配置文件辅助类
 */
public class ConfigHelper {

    static Logger  logger = LoggerFactory.getLogger(ConfigHelper.class);



    private static String configFile;
    private static CodeGenConfig instance ;

    public static CodeGenConfig getInstance(){
        if(instance == null){
            instance = initConfig();
        }
        return instance;
    }
    private static CodeGenConfig initConfig(){
//        ConfigUtil.class.getResourceAsStream(configFile)
        try(Reader reader = new InputStreamReader(new FileInputStream(new File(getConfigFile())), "UTF-8")){
            JsonElement elem = new JsonParser().parse(reader);
            Gson gson  = new GsonBuilder().create();
            CodeGenConfig config = gson.fromJson(elem, CodeGenConfig.class);
            if(config.getCodeGen().getTargetPackage()==null){
                throw new RuntimeException("配置文件目标包路径缺失:targetPackage");
            }else if(config.getAlias()== null ||config.getAlias().size()==0){
                throw new RuntimeException("配置文件别名缺失:alias");
            }
            return config;
        }catch (Exception e){
            logger.error("配置文件解析失败",e);
        }
        return  null;
    }

    public static void init(String configFile) {
        setConfigFile(configFile);
        instance = initConfig();
    }

    private static String getConfigFile() {
        return configFile;
    }

    private static void setConfigFile(String configFile) {
        ConfigHelper.configFile = configFile;
    }
}
