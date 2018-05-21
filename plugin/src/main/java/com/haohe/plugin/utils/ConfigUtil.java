package com.haohe.plugin.utils;


import java.io.IOException;
import java.util.Properties;


@Deprecated
public class ConfigUtil {
    private static Properties config;
    private static Properties alias;
    public static void init() throws IOException {

        config = new Properties();
        alias = new Properties();
        try {
            config.load(ConfigUtil.class.getResourceAsStream("/config.properties"));
            alias.load(ConfigUtil.class.getResourceAsStream("/alias.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static Properties getConfig() {
        return config;
    }

    public static void setConfig(Properties config) {
        ConfigUtil.config = config;
    }

    public static Properties getAlias() {
        return alias;
    }

    public static void setAlias(Properties alias) {
        ConfigUtil.alias = alias;
    }




}
