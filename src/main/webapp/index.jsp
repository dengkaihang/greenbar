﻿<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试test</title>
</head>
<body>
jsp页面

<button onclick="cli(this)" id="test">获取验证码</button>


<form action="${pageContext.request.contextPath}/oa/order/testUp" enctype="multipart/form-data" method="post">

    <label>
        <input type="text" name="title">
    </label>
    <br/>

    <input type="file" name="files">
    <input type="file" name="files">
    <input type="file" name="files2">
    <input type="file" name="files2">

    <input type="submit" value="提交">
</form>

<script src="jquery-3.3.1.js"></script>

<script>

    var time = 10;

    function cli(onc) {

        var bu = $(onc);

        if (time == 0) {
            bu.attr("disabled", false);
            bu.html("获取验证码");
            time = 10;
            return true;
        } else {
            bu.attr("disabled", true);
            bu.html("重新发送:" + time);
            time--;
        }
        var st = setTimeout(function () {
            cli(bu)
        }, 1000);

    }

</script>
</body>
</html>
