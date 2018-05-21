package com.haohe.plugin.generator.impl;

import com.haohe.plugin.config.ConfigHelper;
import com.haohe.plugin.dom.enums.FileType;
import com.haohe.plugin.dom.model.ClassModel;
import com.haohe.plugin.generator.AbsGenerator;
import com.haohe.plugin.generator.utils.FileDirUtil;
import freemarker.template.TemplateException;

import java.io.*;

/**
 *  Model生成器
 *  Example触发接口
 */
public class ModelGen extends AbsGenerator {
    @Override
    public void generator(ClassModel clsModel) {
        try {
            logger.info("准备获取配置模板:{}",getUseTemplate());
            setTemplate(getCfg().getTemplate(getUseTemplate()));
            Writer writer  = new OutputStreamWriter(new FileOutputStream(createFileName(clsModel)),"UTF-8");
            getTemplate().process(clsModel,writer);
            // Model 附带查询体
            ExampleGen exampleGen = new ExampleGen();
            exampleGen.init(this.getOutDir(),this.getTemplateHome());
            exampleGen.generator(clsModel);
        } catch (IOException e) {
           logger.error("Model文件未找到",e);
        } catch (TemplateException e) {
            logger.error("Model模板未找到",e);
        }
    }

    @Override
    public void init(String outPutDir, String templateHome) {
        super.init(outPutDir, templateHome);
        if(ConfigHelper.getInstance().getCodeGen().getModelTemplate() == null){
            logger.warn("Model未指定使用模板,采用默认模板");
            setUseTemplate("model.ftl");
        }else {
            this.setUseTemplate(ConfigHelper.getInstance().getCodeGen().getModelTemplate());
        }
    }

    @Override
    protected String createFileName(ClassModel clsModel) {
        StringBuilder sb  = new StringBuilder();
        sb.append(getOutDir());
        sb.append(getTargetPackage().replace(".", File.separator));
        if(getUseModelPackage()){
            sb.append(File.separator).append(clsModel.getClsName().toLowerCase()).append(File.separator);
            FileDirUtil.createFileDir(sb.toString()+ FileType.MODEL.getPackageName());
        }
        sb.append(FileType.MODEL.getPackageName());
        sb.append(clsModel.getClsName());
        sb.append(FileType.MODEL.getStamp());
        logger.info("Model文件创建名字:{}",sb.toString());
        return sb.toString();
    }


}
