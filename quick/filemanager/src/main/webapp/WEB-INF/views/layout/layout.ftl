<#assign contextPath=request.contextPath />

<#macro styles></#macro>
<#macro scripts></#macro>
<#macro layout styles=styles scripts=scripts>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${title!}</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/base.css"/>
    <@styles />
</head>
<body>
<#nested />
<@scripts />
</body>
</html>
</#macro>