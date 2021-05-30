<#import "json/type.ftl" as JsonType>
<#assign undefined = "未知"/>
<#assign undefinedArr = []/>
<#-- 自定义函数-->
<#macro fieldDescPrint desc printT>
    ${printT ? string("\t","")}/**
    ${printT ? string("\t","")} * ${desc}
    ${printT ? string("\t","")} */
</#macro>
<#macro classDescPrint desc name>
    ${"/**"}
    ${" * ${desc}内部类"}
    ${" * "}
    ${" * @author Ma_wei"}
    ${" * @version 1.0"}
    ${" * @className ${name ? cap_first}.class"}
    ${" * @since ${.now ? datetime}"}
    ${" */"}
</#macro>
<#macro methodDescPrint desc>
    ${"/**"}
    ${" * ${desc}"}
    ${" */"}
</#macro>

<#-- 属性打印-->
<#macro stringFieldPrint desc name printT>
    <#if desc != "">
        <@fieldDescPrint desc printT/>
    </#if>
    ${printT ? string("\t","")}private String ${name};${"\n"}
</#macro>
<#macro integerFieldPrint desc name printT>
    <#if desc != "">
        <@fieldDescPrint desc printT/>
    </#if>
    ${printT ? string("\t","")}private Integer ${name};${"\n"}
</#macro>
<#macro booleanFieldPrint desc name printT>
    <#if desc != "">
        <@fieldDescPrint desc printT/>
    </#if>
    ${printT ? string("\t","")}private Boolean ${name};${"\n"}
</#macro>

<#macro objFieldPrint desc name printT>
    <#if desc != "">
        <@fieldDescPrint desc printT/>
    </#if>
    ${printT ? string("\t","")}private ${name ? cap_first} ${name};${"\n"}
</#macro>
<#macro listObjFieldPrint desc name printT>
    <#if desc != "">
        <@fieldDescPrint desc printT/>
    </#if>
    ${printT ? string("\t","")}private List<${name ? cap_first}> ${name};${"\n"}
</#macro>
<#macro objClassPrint desc name subParams>
    <#if desc != "" && name != "">
        <@classDescPrint desc name/>
    </#if>
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ${name ? cap_first} {${"\n"}
        <#list subParams as subParam>
            <#if subParam.isArrayItems !false>
                <#list subParam.subParams as sonSubParam>
                    <@paramDef2Field sonSubParam true/>
                </#list>
            <#else>
                <@paramDef2Field subParam true/>
            </#if>
        </#list>
    }${"\n"}
</#macro>
<#-- 生成Model Param 等实体类函数-->
<#macro paramDef2Field paramDef isInnerClass>
    <#assign type = paramDef.type/>
    <#-- 根节点开始-->
    <#if paramDef.root !false>
        <#assign subParams = paramDef.subParams/>
        <#list subParams as subParam>
            <@paramDef2Field subParam isInnerClass/>
        </#list>
    <#else>
        <#assign printT = isInnerClass/>
        <#switch type>
            <#case JsonType.OBJECT>
                <#-- 先生成字段名-->
                <@objFieldPrint paramDef.description !undefined paramDef.name !undefined printT/>
                <#-- 后创建内部类-->
                <@objClassPrint paramDef.description !undefined paramDef.name !undefined paramDef.subParams ! undefinedArr/>
                <#break >
            <#case JsonType.ARRAY>
                <#-- 先生成字段名-->
                <@listObjFieldPrint paramDef.description !undefined paramDef.name !undefined printT/>
                <#-- 后创建内部类-->
                <@objClassPrint paramDef.description !undefined paramDef.name !undefined paramDef.subParams ! undefinedArr/>
                <#break >
            <#case JsonType.STRING>
                <@stringFieldPrint paramDef.description !undefined paramDef.name !undefined printT/>
                <#break >
            <#case JsonType.INTEGER>
                <@integerFieldPrint paramDef.description !undefined paramDef.name !undefined printT/>
                <#break >
            <#case JsonType.BOOLEAN>
                <@booleanFieldPrint paramDef.description !undefined paramDef.name !undefined printT/>
                <#break >
        </#switch>
    </#if>
</#macro>