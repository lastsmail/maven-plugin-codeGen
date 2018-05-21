package com.haohe.plugin.generator.impl;

import com.haohe.plugin.dom.enums.FileType;
import com.haohe.plugin.dom.model.ClassModel;
import com.haohe.plugin.generator.AbsGenerator;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * 查询条件体生成器
 */
public class ExampleGen extends AbsGenerator {

    @Override
    public void generator(ClassModel clsModel) {
        Configuration exampleCfg = new Configuration(Configuration.VERSION_2_3_25);
        exampleCfg.setClassForTemplateLoading(AbsGenerator.class, "/ftl");
        exampleCfg.setDefaultEncoding("UTF-8");
        try {
            setTemplate(exampleCfg.getTemplate("example.ftl"));
            Writer writer  = new OutputStreamWriter(new FileOutputStream(createFileName(clsModel)),"UTF-8");
            getTemplate().process(clsModel,writer);
        } catch (IOException e) {
            logger.error("Example文件未找到",e);
        } catch (TemplateException e) {
            logger.error("Example模板未找到",e);
        }

    }



    @Override
    protected String createFileName(ClassModel clsModel) {
        StringBuilder sb  = new StringBuilder();
        sb.append(getOutDir());
        sb.append(getTargetPackage().replace(".", File.separator));
        if(getUseModelPackage()){
            sb.append(File.separator).append(clsModel.getClsName().toLowerCase()).append(File.separator);
        }
        sb.append(FileType.EXAMPLE.getPackageName());
        sb.append(clsModel.getClsName());
        sb.append(FileType.EXAMPLE.getStamp());
        logger.info("Example文件创建名字:{}",sb.toString());
        return sb.toString();
    }

}
