<#assign contextPath=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/base.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/account.css"/>
</head>
<body>
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
        <input type="hidden" value="${re}"/>
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
</body>
</html>