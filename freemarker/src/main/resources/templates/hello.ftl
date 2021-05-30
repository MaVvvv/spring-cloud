package ${classPath}.clas;
<#assign idS = ids/>

public class ${className} {

    public static void main(String[] args) {
        System.out.println("${content}");
        <#list idS as id>
            ${id};
        </#list>
    }
}