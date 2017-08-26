<#include "/layout/layout.ftl"/>
<#assign title="注册" />

<#macro styles>
<link rel="stylesheet" href="${contextPath}/resources/css/account.css"/>
</#macro>

<@layout>
<div class="register-content">
    <section class="title">注册</section>
    <form method="POST">
        <!--/*展示所有错误-->
        <div class="error">
            <ul>
                <li></li>
            </ul>
        </div>
        <!--*/-->
        <input type="hidden" id="re" name="re" value="${re!}" />
        <div>
            <input type="text" id="username" name="username" placeholder="username" />
            <span></span>
        </div>
        <div>
            <input type="password" id="password" name="password" placeholder="password" />
            <span></span>
        </div>
        <div>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="confirm" />
            <span></span>
        </div>
        <div>
            <input type="email" id="email" name="email" placeholder="email" />
            <span></span>
        </div>
        <div>
            <input type="text" id="phone" name="phone" placeholder="phone" />
            <span></span>
        </div>
        <div><input type="submit" value="注册" /></div>
    </form>
</div>
</@layout>