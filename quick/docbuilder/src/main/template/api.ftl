<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style media="screen">
        body {
            color: #333333;
        }

        table {
            border: 1px solid #aaaaaa;
            border-collapse: collapse;
        }

        th,
        td {
            border: 1px solid #aaaaaa;
            padding: 5px 10px;
        }
    </style>
</head>

<body>
    <#escape x as x?html>
    <ul>
        <#list functions as function>
            <li><a href="#${function.name?if_exists}"><#if (function.remark?? && function.remark != '')>${function.remark}<#else>没注释方法</#if></a></li>
        </#list>
    </ul>

    <pre>
&lt;!-- dubbo客户端配置 --&gt;
&lt;dubbo:registry address="zookeeper://192.168.10.31:2181?backup=192.168.10.32:2181,192.168.10.33:2181"&gt;&lt;/dubbo:registry&gt;
&lt;dubbo:reference id="${className?uncap_first}" check="false" group="${properties.dubboConfigGroupInUse}"
    interface="${packageName}.${className}" version="1.0.0" /&gt;
    </pre>

    <#list functions as function>
        <h2 id="${function.name?if_exists}"><#if (function.remark?? && function.remark != '')>${function.remark}<#else>没注释方法</#if></h2>
        服务支持：${function.author?if_exists}
        <pre>
${function.desc}
        </pre>
        <#if (function.params)??>
            <#list function.params as param>
                <#if (param.parameter)??>
                <h4 id="${param.parameter.className}">入参 ${param.parameter.className}</h4>
                <table>
                    <tr>
                        <th>字段</th>
                        <th>类型</th>
                        <th>空</th>
                        <th>默认</th>
                        <th>注释</th>
                    </tr>
                    <#list param.parameter.fields as field>
                        <#if (function.type?? && function.type == "add" && field.fieldName == "id")>

                        <#elseif (function.type?? && (function.type == "add" || function.type == "update") && (field.fieldName == "createTime" || field.fieldName == "updateTime" || field.fieldName == "modifyTime"))>

                        <#else>
                            <tr>
                                <td>${field.fieldName?if_exists}</td>
                                <td>${field.fieldType?if_exists}</td>
                                <td>
                                    <#if function.type??>
                                        <#if (function.type == "add")>
                                            <#if (function.insertNotNull?? && function.insertNotNull)>否<#else>是</#if>
                                        <#elseif (function.type == "update")>
                                            <#if (function.updateNotNull?? && function.updateNotNull)>否<#else>是</#if>
                                        </#if>
                                    </#if>
                                </td>
                                <td></td>
                                <td>${field.remark?if_exists}</td>
                            </tr>
                        </#if>
                    </#list>
                </table>
                <br/>
                </#if>
            </#list>
        </#if>

        <#if (function.returnType.parameter)??>
        <h4 id="${function.returnType.parameter.className}">出参 ${function.returnType.parameter.className}</h4>
        <table>
            <tr>
                <th>字段</th>
                <th>类型</th>
                <th>空</th>
                <th>默认</th>
                <th>注释</th>
            </tr>
            <#list function.returnType.parameter.fields as field>
                <tr>
                    <td>${field.fieldName?if_exists}</td>
                    <td>${field.fieldType?if_exists}</td>
                    <td></td>
                    <td></td>
                    <td>${field.remark?if_exists}</td>
                </tr>
            </#list>
        </table>
        <br/>
        </#if>

    </#list>
    </#escape>
</body>

</html>
