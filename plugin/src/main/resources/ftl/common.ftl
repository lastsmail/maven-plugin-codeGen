<#macro createId idName idType pkType>
    <#assign idName=idName>
    <#assign idType=idType>
    <#assign pkType =pkType>

</#macro>
<!-- 蛇形命名转驼峰命名-->
<#macro coloum2Field name >
    <#assign newVal ="">
    <#list name?split("_") as val>
        <#assign newVal = newVal+val?cap_first>
    </#list>
${newVal?trim?uncap_first}</#macro>

<!-- 蛇形命名转驼峰命名-->
<#macro methodName name >
    <#assign newVal ="">
    <#list name?split("_") as val>
        <#assign newVal = newVal+val?cap_first>
    </#list>
${newVal?trim?cap_first}</#macro>

<!-- lower_case >> uncap_first-->
<!-- 条件判定 名称部分 -->
<#macro conditionKey fields>
    <#list fields as f>
                <if test="<@coloum2Field f.name />  != null">
                     ${f.name},
                </if>
    </#list>
</#macro>
<!-- 条件判定 值部分-->
<#macro conditionVal fields>
    <#list fields as f>
            <if test="<@coloum2Field f.name />  != null">
                <#noparse>#{</#noparse> <@coloum2Field f.name /> ,jdbcType=${f.type.lable}<#noparse>}</#noparse>,
            </if>
    </#list>
</#macro>

<#macro updateKey fields >
    <#list fields as f>
            <if test="record.<@coloum2Field f.name />  != null">
                ${f.name} = <#noparse>#{record.</#noparse><@coloum2Field f.name /> ,jdbcType=${f.type.lable}<#noparse>}</#noparse>,
            </if>
    </#list>
</#macro>
<#macro updateKeyNotComma fields>
    <#list fields as f>
            ${f.name} =<#noparse>#{record.</#noparse><@coloum2Field f.name /> ,jdbcType=${f.type.lable}<#noparse>}</#noparse><#if (f_has_next)>,</#if>
    </#list>
</#macro>

<#macro jdbcVal targetList>
    <#list targetList as f >
            <#noparse>#{</#noparse><@coloum2Field f.name /> ,jdbcType=${f.type.lable}<#noparse>}</#noparse><#if (f_has_next)>,</#if>
    </#list>
</#macro>

<#macro idCondition>
    ${idName?uncap_first} = <#noparse> #{</#noparse>${idName},jdbcType=${pkType}<#noparse>}</#noparse>
</#macro>

<#macro recordIdCondition>
${idName?uncap_first} = <#noparse> #{</#noparse>record.${idName},jdbcType=${pkType}<#noparse>}</#noparse>
</#macro>

<#macro selectiveID >
${idName?uncap_first} = <#noparse> #{</#noparse>${idName},jdbcType=${pkType}<#noparse>}</#noparse>
</#macro>



