<#include "/layout/layout.ftl"/>
<#assign title="登陆" />
<@spring.bind "model" />

<#macro styles>
<link rel="stylesheet" href="${contextPath}/resources/css/account.css"/>
</#macro>

<@layout>
<div class="login-content">
    <section class="title">登陆</section>
    <form method="POST">
        <#if spring.status.error>
            <div class="errors">
                There were problems with the data you entered:
                <ul>
                    <#list spring.status.errorMessages as error>
                        <li>${error}</li>
                    </#list>
                </ul>
            </div>
        </#if>
        <!--/*展示所有错误-->
        <div class="error">
            <ul>
                <li></li>
            </ul>
        </div>
        <!--*/-->
        <input type="hidden" id="re" name="re" value="${re!}"/>

        <div>
            <!--单个字段绑定-->
            <@spring.bind "model.username" />
            <input type="text" id="${spring.status.expression}" name="${spring.status.expression}" value="${spring.status.value!}" />
            <span>${spring.status.errorMessage!}</span>
        </div>
        <div>
            <@spring.formPasswordInput "model.password" />
            <span><@spring.showErrors "" /></span>
        </div>
        <div><input type="checkbox" id="remember" name="remember"/></div>
        <div><input type="submit" value="Login"/></div>
        <a href="${contextPath}/account/register">register</a>
    </form>
</div>
</@layout>