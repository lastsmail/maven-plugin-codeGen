package com.haohe.plugin;


import com.haohe.plugin.config.ConfigHelper;
import com.haohe.plugin.generator.Generator;
import com.haohe.plugin.generator.GeneratorFactory;
import com.haohe.plugin.jdbc.mysql.Table2BeanConverter;
import com.haohe.plugin.job.JobQueue;
import com.haohe.plugin.utils.ReflectionUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Haohe CodeGen
 * @goal codeGen
 *
 */
public class GeneratorPlugin extends AbstractMojo {



    /**
     * 模板目录
     * @parameter
     */
    public String templateHome ="/ftl";

    /**
     * 输出目录
     * @parameter
     */
    public String outDir;

    /**
     * 配置文件
     * @parameter
     */
    public String configFile;

    /**
     * 生成域
     *  model
     *  xmlMapper
     *  mapper
     *  dao
     *  service
     *  controller
     * @parameter
     */
    public String scope;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
//        ExecutorService service = Executors.newCachedThreadPool();
        ConfigHelper.init(configFile);

        Table2BeanConverter.convert();

        if (!ConfigHelper.getInstance().getUseModelPackage()){
            initDir(outDir,ConfigHelper.getInstance().getCodeGen().getTargetPackage(),scope);
        }
        String[] needCreateScope=scope.split(",");
        for(String model:needCreateScope){
            Generator generator = GeneratorFactory.createGenerator(ReflectionUtil.convertClsName(model));
            generator.init(outDir,templateHome);
//            service.submit(()->{
                JobQueue.getJobs().forEach((j)-> {
                    getLog().info("准备生成:"+j);
                    generator.generator(j);
                    getLog().info(j+" 生成完成");
                });
//            });
        }

//            service.shutdown();


    }

    public static void main(String[] args) throws MojoFailureException, MojoExecutionException {
        ExecutorService service = Executors.newCachedThreadPool();
//        try {
        ConfigHelper.init("C:\\Users\\lenovo\\Desktop\\config\\config.json");
        Table2BeanConverter.convert();
        String scopec ="javaScript";//model,dao,mapper,service,xmlMapper,controller,javaScript";
        System.out.println(ConfigHelper.getInstance().getUseModelPackage());
        if (!ConfigHelper.getInstance().getUseModelPackage()) {
            new GeneratorPlugin().initDir("E:\\renshen\\", ConfigHelper.getInstance().getCodeGen().getTargetPackage(), scopec);
        }
        String[] sc=scopec.split(",");

        for(String s:sc){
            Generator  generator = GeneratorFactory.createGenerator(ReflectionUtil.convertClsName(s));
            generator.init("E:\\renshen\\","/ftl");
            service.submit(()->{
                JobQueue.getJobs().forEach((j)-> {
                    System.out.println(j);
                    generator.generator(j);
                });
            });
        }
        service.shutdown();
//        ModelGen modelGen = new ModelGen();
//        modelGen.setTemplateHome("/ftl");
//        modelGen.setOutDir("E:\\fromCache\\test\\src\\main\\java\\");
//        modelGen.setTargetPackage(ConfigHelper.getInstance().getCodeGen().getTargetPackage());
//        modelGen.setUseTemplate(ConfigHelper.getInstance().getCodeGen().getModelTemplate());
//        modelGen.setUseModelPackage(ConfigHelper.getInstance().getUseModelPackage());
//        XmlMapperGen mapperGen = new XmlMapperGen();
//        mapperGen.setTemplateHome("/ftl");
//        mapperGen.setUseTemplate("xmlMapper");
//        mapperGen.setUseModelPackage(ConfigHelper.getInstance().getUseModelPackage());
//        mapperGen.setOutDir("E:\\fromCache\\test\\src\\main\\java\\");
//        mapperGen.setTargetPackage(ConfigHelper.getInstance().getCodeGen().getTargetPackage());
//        mapperGen.setUseTemplate(ConfigHelper.getInstance().getCodeGen().getMapperTemplate());
//        ExampleGen exampleGen = new ExampleGen();
//        exampleGen.setTemplateHome("/ftl");
//        exampleGen.setOutDir("E:\\fromCache\\test\\src\\main\\java\\");
//        exampleGen.setTargetPackage(ConfigHelper.getInstance().getCodeGen().getTargetPackage());
//        exampleGen.setUseTemplate(ConfigHelper.getInstance().getCodeGen().getModelTemplate());
//        exampleGen.setUseModelPackage(ConfigHelper.getInstance().getUseModelPackage());
//        JobQueue.getJobs().forEach((j)-> {
////            modelGen.generator(j);
////            mapperGen.generator(j);
//            exampleGen.generator(j);
//        });

//        } catch (IOException e) {
//           e.printStackTrace();
//        }
    }


    private   void initDir(String outDir, String targetPackage, String codeScope) {
       StringBuffer sb = new StringBuffer(outDir);
       String[] packages= targetPackage.split("\\.");
       for(String packageName :packages){
           sb.append(File.separator+packageName);
       }
       String[] scopes = codeScope.split(",");
       for(String scope:scopes){
           boolean isExists = new File(sb.toString() + File.separator + scope).exists();
           if (!isExists){
               new File(sb.toString()+ File.separator+scope).mkdirs();
           }
       }

    }

}
