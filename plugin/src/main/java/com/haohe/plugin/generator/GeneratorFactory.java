package com.haohe.plugin.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * 生成器抽象工厂
 */
public class GeneratorFactory {
    static  Logger logger = LoggerFactory.getLogger(GeneratorFactory.class);

    /**
     * 根据类名加载生成器
     * @param clsName  类全名
     * @return
     */
    public static Generator createGenerator(String clsName){
        try {
            logger.info("准备获取代码生成器:{}",clsName);
            Class<? extends Generator> generator = (Class<? extends Generator>) Class.forName(clsName);
            return   generator.getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            logger.error("获取生成器失败:{}",e.toString());
        } catch (IllegalAccessException e) {
            logger.error("获取生成器失败:{}",e.toString());
        } catch (InstantiationException e) {
            logger.error("获取生成器失败:{}",e.toString());
        } catch (NoSuchMethodException e) {
            logger.error("获取生成器失败:{}",e.toString());
        } catch (InvocationTargetException e) {
            logger.error("获取生成器失败:{}",e.toString());
        }
        return null;
    }
}
