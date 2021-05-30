<#import "../common-package.ftl" as commonPackage>
<#import "../common-macro.ftl" as commonMacro>
package ${classPath}.model;

import ${commonPackage.AllArgsConstructor};
import ${commonPackage.Data};
import ${commonPackage.NoArgsConstructor};
import ${commonPackage.List};

/**
 * ${inition.description !"消息请求参数"}
 *
 * @author: Ma_wei
 * @className: ${className}.class
 * @since: ${.now ? datetime}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${className} {

    ${"// aaaaaa"}
    <#if inition.root ! false>
        <@commonMacro.paramDef2Field inition false/>
    </#if>
}