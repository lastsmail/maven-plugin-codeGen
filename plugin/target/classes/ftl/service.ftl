<#if targetPackage??>
package ${targetPackage}.service;
</#if>

import com.haohe.database.service.BaseService;
import ${targetPackage}.model.${clsName};


public interface ${clsName}Service  extends BaseService<${clsName}>   {
}
