<#if targetPackage??>
package ${targetPackage}.controller;
</#if>

import com.github.pagehelper.PageInfo;
import ${targetPackage}.model.${clsName};
import ${targetPackage}.model.${clsName}Example;
import ${targetPackage}.service.${clsName}Service;
import com.haohe.web.controller.AbsBaseController;
import com.haohe.web.page.PageRequest;
import com.haohe.web.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ${clsName}Controller extends AbsBaseController{


    @Autowired
    ${clsName}Service ${clsName?uncap_first}Service;



    @RequestMapping(value = "/${clsName?lower_case}",method = RequestMethod.POST)
    public String save${clsName}(@RequestBody ${clsName} ${clsName?uncap_first}){
        ${clsName?uncap_first}Service.save(${clsName?uncap_first});
        return "SUCCESS";
    }

    @RequestMapping(value = "/${clsName?lower_case}",method = RequestMethod.GET)
    public ${clsName} find${clsName}(@RequestParam("id") String id){
        ${clsName}Example example = new ${clsName}Example();
        example.setPk(id);
        ${clsName} ${clsName?uncap_first} = ${clsName?uncap_first}Service.findByID(example);
        return ${clsName?uncap_first};
    }

    @RequestMapping(value = "/${clsName?lower_case}s",method = RequestMethod.POST)
    public List<${clsName}> list${clsName}(@RequestBody  ${clsName} ${clsName?uncap_first}){
        ${clsName}Example example = new ${clsName}Example();
        List<${clsName}> list = ${clsName?uncap_first}Service.listByCondtion(example);
        return list;
    }

    @RequestMapping(value = "/page${clsName?lower_case}",method = RequestMethod.POST)
    public PageInfo<${clsName}> page${clsName}(@RequestBody PageRequest<${clsName}> request ){
        ${clsName}Example example = new ${clsName}Example();
        PageInfo<${clsName}> list = ${clsName?uncap_first}Service.pageByCondtion(example,request.getPageInfo());
        return list;
    }

    @RequestMapping(value = "/update${clsName?lower_case}",method = RequestMethod.POST)
    public String update${clsName}(@RequestBody ${clsName} ${clsName?uncap_first},${clsName}Example ${clsName?uncap_first}Example){
        ${clsName?uncap_first}Service.update(${clsName?uncap_first}, ${clsName?uncap_first}Example);
        return "SUCCESS";
    }

    @RequestMapping(value = "/${clsName?lower_case}",method = RequestMethod.DELETE)
    public String delete${clsName}(@RequestParam("id") String id){
        ${clsName}Example  ${clsName?uncap_first}Example = new ${clsName}Example();
        ${clsName?uncap_first}Example.setPk(id);
        ${clsName?uncap_first}Service.delete(${clsName?uncap_first}Example);
        return "SUCCESS";
    }

}
