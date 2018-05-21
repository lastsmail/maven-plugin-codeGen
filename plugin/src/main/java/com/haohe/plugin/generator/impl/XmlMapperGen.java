package com.haohe.plugin.generator.impl;

import com.haohe.plugin.config.ConfigHelper;
import com.haohe.plugin.dom.enums.FileType;
import com.haohe.plugin.dom.model.ClassModel;
import com.haohe.plugin.generator.AbsGenerator;
import com.haohe.plugin.generator.utils.FileDirUtil;
import freemarker.template.TemplateException;

import java.io.*;

/**
 *  XmlMapper文件生成器
 */
public class XmlMapperGen extends AbsGenerator {

    @Override
    public void generator(ClassModel clsModel) {
        try {
            setTemplate(getCfg().getTemplate(getUseTemplate()));
            Writer writer  = new OutputStreamWriter(new FileOutputStream(createFileName(clsModel)),"UTF-8");
            getTemplate().process(clsModel,writer);
        } catch (IOException e) {
            logger.error("XMLMapper文件未找到",e);
        } catch (TemplateException e) {
            logger.error("XMLMapper模板未找到",e);
        }
    }

    @Override
    public void init(String outPutDir, String templateHome) {
        super.init(outPutDir, templateHome);
        if(ConfigHelper.getInstance().getCodeGen().getMapperXmlTemplate() == null){
            logger.warn("XmlMapper未指定使用模板,采用默认模板");
            setUseTemplate("xmlMapper.ftl");
        }else {
            this.setUseTemplate(ConfigHelper.getInstance().getCodeGen().getMapperXmlTemplate());
        }

    }

    @Override
    protected String createFileName(ClassModel clsModel) {
        StringBuilder sb  = new StringBuilder();
        sb.append(getOutDir());
        sb.append(getTargetPackage().replace(".", File.separator));
        if(getUseModelPackage()){
            sb.append(File.separator).append(clsModel.getClsName().toLowerCase()).append(File.separator);
            FileDirUtil.createFileDir(sb.toString()+ FileType.XMLMAPPER.getPackageName());
        }
        sb.append(FileType.XMLMAPPER.getPackageName());
        sb.append(clsModel.getClsName());
        sb.append(FileType.XMLMAPPER.getStamp());
        logger.info("XMLMapper文件名:{}",sb.toString());
        return sb.toString();
    }


}
