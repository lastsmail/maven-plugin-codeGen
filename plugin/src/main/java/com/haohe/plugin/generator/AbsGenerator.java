package com.haohe.plugin.generator;

import com.haohe.plugin.config.ConfigHelper;
import com.haohe.plugin.dom.model.ClassModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


/**
 *  抽象生成器
 *  提供一些基本属性
 *  提供基本方法
 */
public  abstract  class AbsGenerator  implements Generator{

    /**
     *  配置文件
     */
    private static Configuration cfg;

    public Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 模板
     */
    private Template template;
    /**
     * 包路径
     */
    private String targetPackage;
    /**
     * 使用模板
     */
    private String useTemplate;
    /**
     * 输出目录
     */
    private String outDir;
    /**
     * 模板根目录
     */
    private String templateHome;
    /**
     * 是否使用子包路径
     */
    private Boolean useModelPackage;
    /**
     * 来自父亲的爱--送给子类的惊喜
     * @param clsModel
     */
    public abstract void generator(ClassModel clsModel);

    /**
     * 来自父亲的爱--创建文件名
     * @param clsModel
     * @return
     */
    protected abstract String createFileName(ClassModel clsModel);

    /**
     * 初始化
     * @param outPutDir
     * @param templateHome
     */
    public  void init(String outPutDir, String templateHome){
        this.setOutDir(outPutDir);

        if(ConfigHelper.getInstance().getUseLocalTemplate() && templateHome!=null && !"".equals(templateHome)){
            this.setTemplateHome(templateHome);
        }else{
            logger.warn("模板根目录未找到,采用默认值");
            this.setTemplateHome("/ftl");
        }
        this.setTargetPackage(ConfigHelper.getInstance().getCodeGen().getTargetPackage());
        this.setUseModelPackage(ConfigHelper.getInstance().getUseModelPackage());
    }

    public Configuration getCfg() throws IOException {
        if(cfg ==null){
            cfg = new Configuration(Configuration.VERSION_2_3_25);
            // 设定去哪里读取相应的ftl模板
            if(ConfigHelper.getInstance().getUseLocalTemplate()){
                cfg.setDirectoryForTemplateLoading(new File(getTemplateHome()));
            }else{
                cfg.setClassForTemplateLoading(AbsGenerator.class, getTemplateHome());
            }
            cfg.setDefaultEncoding("UTF-8");
        }
        return cfg;
    }


    public String getTemplateHome() {
        return templateHome;
    }

    public void setTemplateHome(String templateHome) {
        this.templateHome = templateHome;
    }

    public void setCfg(Configuration cfg) {
        this.cfg = cfg;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getUseTemplate() {
        return useTemplate;
    }

    public void setUseTemplate(String useTemplate) {
        this.useTemplate = useTemplate;
    }

    public String getOutDir() {
        return outDir;
    }

    public void setOutDir(String outDir) {
        this.outDir = outDir;
    }
    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Boolean getUseModelPackage() {
        return useModelPackage;
    }

    public void setUseModelPackage(Boolean useModelPackage) {
        this.useModelPackage = useModelPackage;
    }
}
