<#assign contextPath=request.contextPath />
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="${contextPath}/resources/bootstrap/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <form role="form" id="folder-form" action="${contextPath}/filemanager/addfolder">
                <input type="hidden" name="folderId" value="${parentId}"/>
                <div class="form-group">
                    <label for="name">文件夹名</label>
                    <input type="text" id="name" name="name" placeholder="请输入名称" class="form-control"/>
                </div>
                <input type="button" class="btn btn-default btn-submit" value="提交"/>
            </form>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <form role="form" id="file-form" action="${contextPath}/filemanager/add" method="post"
                  enctype="multipart/form-data">
                <input type="hidden" name="folderId" value="${parentId}"/>
                <div class="form-group">
                    <label for="file">文件输入</label>
                    <input type="file" id="file" name="file" class="form-control"/>
                </div>
                <input type="button" class="btn btn-default btn-submit" value="提交"/>
            </form>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <form role="form" id="files-form" action="${contextPath}/filemanager/multiadd" method="post"
                  enctype="multipart/form-data">
                <input type="hidden" name="folderId" value="${parentId}"/>
                <div class="form-group">
                    <label for="files">多文件输入</label>
                    <input type="file" id="files" name="files" multiple="multiple" class="form-control"/>
                </div>
                <input type="button" class="btn btn-default btn-submit" value="提交"/>
            </form>
        </div>
    </div>
    <div class="row">
        <div>${id}</div>
    <#list files as entity>
        <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
            <p>${entity.fileId}</p>
            <#if entity.fileType == 1>
                <a href="${requestContext}/filemanager?folderId=${entity.fileId}">${entity.fileName}</a>
            <#else>
                <p>${entity.fileName}</p>
            </#if>
            <p>${entity.filePath}</p>
            <a href="${requestContext}/filemanager/delete/id=${entity.fileId}">删除</a>
        </div>
    </#list>
    </div>
</div>


<script src="${contextPath}/resources/js/jquery.js"></script>
<script src="${contextPath}/resources/bootstrap/js/bootstrap.js"></script>
<script>
    $('#folder-form').on('click', '.btn-submit', function () {
        var name = $('#name').val();
        var folderId = $('#folder-form input[name="folderId"]').val();

        $.ajax({
            url: $('#folder-form').attr('action'),
            type: "GET",
            data: {
                folderId: folderId,
                name: name
            },
            cache: false,
            success: function (data) {
                alert(data);
            }
        });
    });

    $('#file-form').on('click', '.btn-submit', function () {
        var formData = new FormData(document.getElementById('file-form'));

        $.ajax({
            url: $('#file-form').attr('action'),
            type: "POST",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
            }
        });
    });

    $('#files-form').on('click', '.btn-submit', function () {
        var formData = new FormData(document.getElementById('files-form'));

        $.ajax({
            url: $('#files-form').attr('action'),
            type: "POST",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
            }
        });
    });

    function uploadFile() {
        var formData = new FormData(document.getElementById('files-form'));

        var xhr = new XMLHttpRequest();
        xhr.open("POST", $('#files-form').attr('action'));
        xhr.overrideMimeType("application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if ((xhr.status >= 200 && xhr.status < 300)
                        || xhr.status == 304) {
                    alert('保存成功');
                } else {
                    alert(xhr.status);
                }
            }
        }
        xhr.send(formData);
    }
</script>
</body>
</html>