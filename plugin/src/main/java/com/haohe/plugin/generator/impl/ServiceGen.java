package com.haohe.plugin.generator.impl;

import com.haohe.plugin.config.ConfigHelper;
import com.haohe.plugin.dom.enums.FileType;
import com.haohe.plugin.dom.model.ClassModel;
import com.haohe.plugin.generator.AbsGenerator;
import com.haohe.plugin.generator.utils.FileDirUtil;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * Service生成器
 * ServiceImpl触发接口
 */
public class ServiceGen extends AbsGenerator {
    @Override
    public void generator(ClassModel clsModel) {
        try {
            setTemplate(getCfg().getTemplate(getUseTemplate()));
            Writer writer  = new OutputStreamWriter(new FileOutputStream(createFileName(clsModel)),"UTF-8");
            getTemplate().process(clsModel,writer);

            // Service 附带实现
            ServiceImplGen serviceImplGen = new ServiceImplGen();
            serviceImplGen.init(this.getOutDir(),this.getTemplateHome());
            serviceImplGen.generator(clsModel);
        } catch (IOException e) {
            logger.error("Service文件未找到",e);
        } catch (TemplateException e) {
            logger.error("Service模板未找到",e);
        }
    }

    @Override
    public void init(String outPutDir, String templateHome) {
        super.init(outPutDir, templateHome);
        if(ConfigHelper.getInstance().getCodeGen().getServiceTemplate() == null){
            logger.warn("Service未指定使用模板,采用默认模板");
            setUseTemplate("service.ftl");
        }else {
            this.setUseTemplate(ConfigHelper.getInstance().getCodeGen().getServiceTemplate());
        }

    }

    @Override
    protected String createFileName(ClassModel clsModel) {
        StringBuilder sb  = new StringBuilder();
        sb.append(getOutDir());
        sb.append(getTargetPackage().replace(".", File.separator));
        if(getUseModelPackage()){
            sb.append(File.separator).append(clsModel.getClsName().toLowerCase()).append(File.separator);
            FileDirUtil.createFileDir(sb.toString()+ FileType.SERVICE.getPackageName());
        }
        sb.append(FileType.SERVICE.getPackageName());
        sb.append(clsModel.getClsName());
        sb.append(FileType.SERVICE.getStamp());
        logger.info("Service文件创建名字:{}",sb.toString());
        return sb.toString();
    }
}
