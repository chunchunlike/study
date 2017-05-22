<#include "/layout/layout.ftl"/>
<#assign title="登陆" />

<#macro styles>
<link rel="stylesheet" href="${contextPath}/resources/css/account.css"/>
</#macro>

<@layout>
<div class="login-content">
    <section class="title">登陆</section>
    <form method="POST">
        <!--/*展示所有错误-->
        <div class="error">
            <ul>
                <li></li>
            </ul>
        </div>
        <!--*/-->
        <input type="hidden" id="re" name="re" value="${re}"/>
        <div>
            <input type="text" id="username" name="username" value="${(model.username)!}"/>
            <span></span>
        </div>
        <div>
            <input type="password" id="password" name="password"/>
            <span></span>
        </div>
        <div><input type="checkbox" id="remember" name="remember"/></div>
        <div><input type="submit" value="Login"/></div>
        <a href="${contextPath}/account/register">register</a>
    </form>
</div>
</@layout>