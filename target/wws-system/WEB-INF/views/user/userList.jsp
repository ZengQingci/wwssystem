<%--
  Created by IntelliJ IDEA.
  User: Object
  Date: 2019/10/14 0014
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+
        request.getServerName()+":"+request.getServerPort()+path+"/";%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8">
    <title>用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form layui-form-pane">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="userName" id="userName" placeholder="请输入用户" autocomplete="off" class="layui-input">
        </div>
        <input type="button" class="layui-btn layui-btn-normal" id="submit" value="搜索"/>
    </div>
</form>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addUserinfo">新增用户</button>
        <button class="layui-btn layui-btn-sm" lay-event="delUserinfos">批量删除</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<%--模板--%>
<%--时间模板--%>
<script type="text/html" id="newDateTemplet">
    {{#return layui.util.toDateString(d.newDate,'yyyy-MM-dd')}}
</script>
<%--序号模板--%>
<script type="text/html" id="xuhaoTemplet">
    {{d.LAY_TABLE_INDEX+1}}
</script>

<script type="text/html" id="switchTpl">
    <input type="checkbox" name="state" value="{{d.id}}" lay-skin="switch" lay-text="启用|禁用" lay-filter="stateDemo" {{ d.state==1?'checked':''}}>
</script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="images/jquery.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use(['table','form'], function(){
        var table = layui.table;
        var form = layui.form;
        var tableIns = table.render({
            elem: '#test'
            ,url:'user/findUsers'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID', width:80, fixed: 'left',style:'display:none'}
                ,{field:'xuhao', title:'序号', width:60, edit: 'text',templet:'#xuhaoTemplet'}
                ,{field:'userName', title:'用户名', width:120, edit: 'text'}
                ,{field:'email', title:'邮箱', width:150, edit: 'text'}
                ,{field:'sex', title:'性别', width:80, edit: 'text', sort: true}
                ,{field:'phone', title:'联系方式', width:100}
                ,{field:'address', title:'地址'}
                ,{field:'state', title:'状态', width:100,templet:'#switchTpl'}
                ,{field:'newDate', title:'时间', width:120,templet:'#newDateTemplet'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: true
            ,done:function(){
                $("[data-field='id']").css("display","none");
            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'addUserinfo':
                    /*var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));*/
                    layer.open({
                        type: 2,
                        title:"新增管理员页面",
                        area:['800px','450px'],
                        maxmin: true,
                        content: 'user/returnAddUserInfo' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                    });
                    break;
                case 'delUserinfos':
                    var ids = new Array();
                    var data = checkStatus.data;
                    /*layer.msg('选中了：'+ data.length + ' 个');*/
                    for(var i=0;i<data.length;i++){
                        ids.push(data[i].id);
                    }
                    $.ajax({
                        url:"user/delUserByIds",
                        data:{"ids":ids.toString()},
                        type:"post",
                        dataType:"text",
                        success:function (data) {
                            if(data=="ok"){
                                window.location.reload();
                            }else{
                                alert("删除失败！");
                            }
                        }
                    })
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });
        //监听状态开头
        form.on('switch(stateDemo)', function(data){
            /*console.log(data.elem); //得到checkbox原始DOM对象
            console.log(data.elem.checked); //开关是否开启，true或者false
            console.log(data.value); //开关value值，也可以通过data.elem.value得到
            console.log(data.othis); //得到美化后的DOM对象*/
            updatestate(data.value,data.elem.checked)
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    /*obj.del();
                    layer.close(index);*/
                    $.ajax({
                        url:"user/delUserById",
                        data:{"id":data.id},
                        type:"post",
                        dataType:"text",
                        success:function (data) {
                            if(data=="ok"){
                                obj.del();
                            }else{
                                alert("删除失败！");
                            }
                        }
                    });
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type: 2,
                    title:"新增管理员页面",
                    area:['800px','450px'],
                    maxmin: true,
                    content: 'user/returnEditUserInfo?id='+data.id //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                });
            }
        });
        //添加搜索条件重新加载页面
        $("#submit").click(function(){
            tableIns.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    userName: $("#userName").val()
                }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });
    });
    //修改状态加载页面
    function updatestate(id,flag){
        $.ajax({
            url:"user/updatestate",
            data:{"id":id,"flag":flag},
            type:"post",
            dataType:"text",
            success:function (data) {
                if(data=="ok"){
                    alert("修改成功！");
                    window.location.reload();
                }else{
                    alert("修改失败！");
                }
            }
        })
    }
</script>

</body>
</html>
