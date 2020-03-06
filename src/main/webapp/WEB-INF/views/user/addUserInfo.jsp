<%--
  Created by IntelliJ IDEA.
  User: Object
  Date: 2019/10/15 0015
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+
        request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <style>
        .layui-form{
            margin-left: 50px;
        }
        #but{
            text-align: center;
        }
    </style>
</head>
<body>
    <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="userName" id="userName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="passWord" id="passWord" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-inline">
                <input type="password" name="pwd" id="pwd" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" id="email" lay-verify="email" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话号码</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" id="phone" lay-verify="required|phone" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男" checked="">
                <input type="radio" name="sex" value="女" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <select name="state">
                    <option value="0">禁用</option>
                    <option value="1">启用</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系地址</label>
            <div class="layui-input-inline">
                <select name="province" lay-filter="province" id="province">
                    <option value="">请选择省</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="city" lay-filter="city" id="city">
                    <option value="">请选择市</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="area" lay-filter="area" id="area">
                    <option value="">请选择县/区</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <textarea placeholder="请输入详细地址" class="layui-textarea" id="address"></textarea>
        </div>
        <div class="layui-form-item">
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="image">上传图片</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" width="100px" height="100px" id="demo1">
                    <p id="demoText"></p>
                    <input type="hidden" name="pic" id="pic"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item" id="but">
            <input type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="submit" value="提交"/>
            <input type="button" class="layui-btn layui-btn-normal" value="重置"/>
        </div>
    </form>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="images/jquery.js"></script>
    <script type="text/javascript">
        layui.use(['form','upload'],function () {
            var form = layui.form;
            var upload = layui.upload;
            //三级联动
            $.ajax({
                url:"address/findProvince",
                type:"post",
                dataType:"json",
                success:function (data) {
                    for(var i= 0;i<data.length;i++){
                        $("#province").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
                    }
                    //重新渲染
                    form.render();
                }
            });
            form.on('select(province)', function(data){
                //console.log(data.value); //得到被选中的值
                getCity(data.value);
            });
            function getCity(provincecode){
                $.ajax({
                    url:"address/findCity",
                    data:{"provincecode":provincecode},
                    type:"post",
                    dataType:"json",
                    success:function (data) {
                        $("#city").empty();
                        for(var i= 0;i<data.length;i++){
                            $("#city").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
                        }
                        //获取市级下拉框的第一个选项
                        var citycode = $("#city option:first").val();
                        getArea(citycode);
                        /*form.render();*/
                    }
                });
            }
            form.on('select(city)', function(data){
                //console.log(data.value); //得到被选中的值
                /*alert(data.value);*/
                getArea(data.value);
            });
            form.on('select(area)', function(data){
                //获取省市县的信息填入详细地址框
                var province = $("#province").find("option:selected").text();
                var city = $("#city").find("option:selected").text();
                var area = $("#area").find("option:selected").text();
                $("#address").text(province+"-"+city+"-"+area);
            });
            function getArea(citycode) {
                $.ajax({
                    url:"address/findArea",
                    data:{"citycode":citycode},
                    type:"post",
                    dataType:"json",
                    success:function (data) {
                        $("#area").empty();
                        for(var i= 0;i<data.length;i++){
                            $("#area").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
                        }
                        //获取省市县的信息填入详细地址框
                        var province = $("#province").find("option:selected").text();
                        var city = $("#city").find("option:selected").text();
                        var area = $("#area").find("option:selected").text();
                        $("#address").text(province+"-"+city+"-"+area);
                        form.render();
                    }
                });
            }
            //图片上传
            var uploadInst = upload.render({
                elem: '#image'
                , url: 'upload/inupload'
                , before: function (obj) {
                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result); //图片链接（base64）
                    });
                }
                , done: function (res) {
                    //如果上传失败
                    if (res.code > 0) {
                        return layer.msg('上传失败');
                    }
                    //上传成功
                    $("#pic").val(res.data);
                }
            });
            //提交表单
            form.on('submit(submit)', function(data){
                var pwd1 = $("#passWord").val();
                var pwd2 = $("#pwd").val();
                if(pwd1 != pwd2){
                    return;
                }
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                var user = data.field;
                user.province = $("#province").find("option:selected").text();
                user.city = $("#city").find("option:selected").text();
                user.area = $("#area").find("option:selected").text();
                user.address = $("#address").val();
                delete user.file;
                delete user.pwd;
                //把对象转成json对象
                var tbuserinfo = JSON.stringify(user);
                $.ajax({
                    url:"user/saveuserinfo",
                    contentType:"application/json;charset=UTF-8",
                    data:tbuserinfo,
                    type:"post",
                    dataType:"Text",
                    success:function (data) {
                        if(data == "ok"){
                            alert("添加成功！");
                            window.parent.location.reload();
                        }else{
                            alert("添加失败！");
                        }
                    }
                });
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        });
    </script>
</body>
</html>
