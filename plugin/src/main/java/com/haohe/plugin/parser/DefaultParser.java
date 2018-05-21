package com.haohe.plugin.parser;

import com.haohe.plugin.dom.model.ClassModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/17.
 */

public class DefaultParser {

    public void parser(ClassModel classModel){
        Template temp = null;
        try {
            // 通过Freemarker的Configuration读取相应的Ftl
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
            // 设定去哪里读取相应的ftl模板
//            cfg.setDirectoryForTemplateLoading(new File("ftl"));
//            cfg.setClassForTemplateLoading(FreeMarkTest.class, "/");
//            cfg.setObjectWrapper(new DefaultObjectWrapper());
            cfg.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码
            // 在模板文件目录中寻找名称为name的模板文件
            temp = cfg.getTemplate("test.ftl");
            Writer writer  = new OutputStreamWriter(new FileOutputStream("success.html"),"UTF-8");
            Map<String,Object> map = new HashMap() ;
//            map.put("baseDir","C:/");
            System.out.println(classModel.getFields().get(0).getType());
            temp.process(classModel,writer);
            System.out.println(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();

        }
    }
}
