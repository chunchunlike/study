<#include "/layout/layout.ftl"/>
<#assign title="" />

<#macro scripts>
<script>
    $('form').on('click', '#btn-submit', function () {
        var id = $('#id').val();
        var name = $('#name').val();
        $.ajax({
            url: $('#folder-form').attr('action'),
            type: "POST",
            data: {
                id: id,
                name: name
            },
            cache: false,
            success: function (data) {
                alert(data);
            }
        });
    });
</script>
</#macro>

<@layout>

<form action="/test/ajaxBindModel" method="post">
    <input type="text" name="id" placeholder="id" id="id"/>
    <input type="text" name="name" placeholder="name" id="name"/>
    <input type="button" value="submit" id="btn-submit"/>
</form>

</@layout>