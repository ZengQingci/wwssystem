<%--
  Created by IntelliJ IDEA.
  User: Object
  Date: 2019/10/19 0019
  Time: 19:32
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
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addOrder">新增订单</button>
        <button class="layui-btn layui-btn-sm" lay-event="delOrders">批量删除</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="xuhaoTemplet">
    {{d.LAY_TABLE_INDEX+1}}
</script>


<script src="layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="images/jquery.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'order/findOrderList'
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
                ,{field:'xuhao', title:'序号', width:120, edit: 'text',templet:'#xuhaoTemplet'}
                ,{field:'orderNumber', title:'订单编号', width:150, edit: 'text'}
                ,{field:'productCount', title:'产品数量', width:80, edit: 'text', sort: true}
                ,{field:'orderAmountTotal', title:'付款金额', width:100}
                ,{field:'phone', title:'电话号码',width:100}
                ,{field:'orderConsignee', title:'客户姓名', width:80, sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: true
            ,done:function () {
                $("[data-field='id']").css("display","none");
            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'addOrder':
                    /*var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));*/
                    layer.open({
                        type: 2,
                        title:'新增订单页面',
                        area: ['800px', '450px'],
                        maxmin: true,
                        content: 'order/returnAddOrder' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                    });
                    break;
                case 'delOrders':
                    var data = checkStatus.data;
                    var ids  = new Array();
                    for(var i = 0;i<data.length;i++){
                        ids.push(data[i].id);
                    }
                    layer.confirm('真的删除行么', function(index){
                        $.ajax({
                            url:"order/delOrders",
                            data:{"ids":ids.toString()},
                            type:"post",
                            dataType:"text",
                            success:function (data) {
                                if(data == "ok"){
                                    alert("删除成功！");
                                    window.location.reload();
                                }else {
                                    alert("删除失败！");
                                }
                            }
                        });
                        layer.close(index);
                    });
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

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url:"order/delOrderById",
                        data:{"id":data.id},
                        type:"post",
                        dataType:"text",
                        success:function (data) {
                            if(data == "ok"){
                                obj.del();
                                alert("删除成功！");
                            }else {
                                alert("删除失败！");
                            }
                        }
                    });
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.prompt({
                    formType: 2
                    ,value: data.email
                }, function(value, index){
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }
        });
    });
</script>

</body>
</html>
