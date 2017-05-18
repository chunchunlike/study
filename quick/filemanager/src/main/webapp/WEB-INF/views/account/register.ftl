<#assign contextPath=request.contextPath />
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/base.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/account.css" />
</head>
<body>

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
        <input type="hidden" th:value="${re}" />
        <div>
            <input type="text" id="username" name="username" />
            <span></span>
        </div>
        <div>
            <input type="password" id="password" name="password" />
            <span></span>
        </div>
        <div>
            <input type="password" id="confirmPassword" name="confirmPassword" />
            <span></span>
        </div>
        <div>
            <input type="email" id="email" name="email" />
            <span></span>
        </div>
        <div>
            <input type="text" id="phone" name="phone" />
            <span></span>
        </div>
        <div><input type="submit" value="注册" /></div>
    </form>
</div>
</body>
</html>