<#if targetPackage??>
package ${targetPackage}.service;
</#if>

import com.haohe.database.service.AbsBaseService;
import ${targetPackage}.mapper.${clsName}Mapper;
import ${targetPackage}.model.${clsName};
import ${targetPackage}.service.${clsName}Service;
import org.springframework.stereotype.Service;

@Service
public class ${clsName}ServiceImpl extends AbsBaseService<${clsName},${clsName}Mapper> implements ${clsName}Service   {



}
