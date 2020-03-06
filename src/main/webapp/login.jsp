<%--
  Created by IntelliJ IDEA.
  User: Object
  Date: 2019/10/21 0021
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+
        request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <link rel="stylesheet" href="images/login.css"/>
    <style>
        .admin-header{
            text-align: center;
            font-size: 28px;
            font-family: '微软雅黑';
            font-weight: 100;
            color:#fff;
        }
        .a-button{
            width:300px;
            margin-top:10px;
        }
        body{
            background-image: url(images/bj.jpg);
            background-repeat: no-repeat;
            background-size:100% 100%;
            background-attachment:fixed;
        }
    </style>
</head>
<body>
<div id="container">
    <div></div>
    <div class="admin-login-background">
        <div class="admin-header">
            微商管理系统
        </div>
        <form class="layui-form"  method="post" action="login/toLogin">
            <div>
                <i class="layui-icon layui-icon-username admin-icon admin-icon-username"></i>
                <input type="text" name="username" placeholder="请输入用户名"
                       id="username"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-username">
            </div>
            <div>
                <i class="layui-icon layui-icon-password admin-icon admin-icon-password"></i>
                <input type="password" name="password" id="password"
                       placeholder="请输入密码"
                       autocomplete="off"
                       class="layui-input admin-input">
            </div>
            <div>
                <input type="text" name="code"
                       id="code"
                       placeholder="请输入验证码"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-verify">
                <img class="admin-captcha codeimg" id="codeimg"
                     src="img/code.do"
                     onclick="updateVerify()">
            </div>
            <div class="layui-form-item" id="but">
                <input type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="submit" onclick="login" value=""/>
                <input type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="submit" onclick="register" value=""/>
                <input type="button" class="layui-btn layui-btn-normal" value="重置"/>
            </div>
        </form>
    </div>
</div>
<script src="layui/layui.js"></script>
<script src="images/jquery.js"></script>
<script>
    //用户登录方法
    function login() {
        var username = $("#username").val();
        var password = $("#password").val();
        var code = $("#code").val();
        if(username == null || username == ""){
            alert("用户名不能为空！");
            return false;
        }
        if(password == null || password == ""){
            alert("密码不能为空！");
            return false;
        }
        if(code == null || code == ""){
            alert("验证码不能为空！");
            return false;
        }
        $.ajax({
            url:"login/toLogin",
            data:{"username":username,"password":password,"code":code},
            type:"post",
            dataType:"text",
            success:function (data) {
                if(data == "3"){
                    alert("验证码错误！");
                    //更新验证码
                    updateVerify();
                    return false;
                }
                if(data == "2"){
                    alert("用户不存在或用户名和密码输入错误！");
                    updateVerify();
                    return false;
                }
                if(data == "4"){
                    alert("您的账号已禁用！");
                    updateVerify();
                    return false;
                }
                window.location.href="login/returnLogin";
            }
        })
    }

    //用户注册方法
    function register() {


    }
    //切换验证码
    function updateVerify() {
        var imgSrc = $("#codeimg");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    }
    //加入时间戳，去缓存机制
    function chgUrl(url) {
        //获取时间戳
        var timestamp = (new Date()).valueOf();
        if ((url.indexOf("&")) >= 0) {
            url = url + "&timestamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }
</script>
</body>
</html>
