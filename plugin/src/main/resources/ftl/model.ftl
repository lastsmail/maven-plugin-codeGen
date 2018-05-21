<#if targetPackage??>
package ${targetPackage}.model;
</#if>
<#list imports as i>
${i}
</#list>
import com.haohe.base.model.BaseModel;
public class ${clsName!tableName} extends BaseModel {

<#import "common.ftl" as com>
<#list fields as f>
    /**
    * ${f.memo}
    */
    private ${f.type.type}  <@com.coloum2Field f.name />;
</#list>

<#list fields as f>
    public ${f.type.type} get<@com.methodName f.name />(){
        return <@com.coloum2Field f.name />;
    }

    public void set<@com.methodName f.name />(${f.type.type} <@com.coloum2Field f.name />){
        this.<@com.coloum2Field f.name /> = <@com.coloum2Field f.name />;
    }

    </#list>

}