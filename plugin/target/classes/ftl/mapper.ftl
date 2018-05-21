<#if targetPackage??>
package ${targetPackage}.mapper;
</#if>

import com.haohe.database.mapper.BaseMapper;
import ${targetPackage}.model.${clsName};
import ${targetPackage}.model.${clsName}Example;


public interface ${clsName}Mapper extends BaseMapper<${clsName},${clsName}Example> {

}