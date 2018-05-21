package com.haohe.plugin.generator;


import com.haohe.plugin.dom.model.ClassModel;

/**
 *  生成器
 */
public interface Generator {

     /**
      * 根据对象生成
      * @param clsModel
      */
     void generator(ClassModel clsModel);

     /**
      * 初始化
      * @param outDir
      * @param templateHome
      */
     void init(String outDir, String templateHome);
}
