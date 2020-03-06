<%--
  Created by IntelliJ IDEA.
  User: Object
  Date: 2019/10/20 0020
  Time: 11:47
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
        .customerDIV{
            position: relative;
        }
        .customerDIV #customerUL{
            position: absolute;
            z-index: 1;
            background-color: #fff;
            border:1px solid #93D1FF;
            display: none;
        }
        #customerUL li{
            width: 300px;
            height: 20px;
            line-height: 10px;
            cursor: pointer;
        }
        #customerUL li:hover{
            background-color: #93D1FF;
        }
    </style>
</head>
<body>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>订单信息</legend>
    </fieldset>
    <form class="layui-form layui-form-pane">
        <input type="hidden" id="customerId"/>
        <div class="layui-form-item">
            <label class="layui-form-label">订单编号</label>
            <div class="layui-input-block">
                <input type="text" name="orderNumber" id="orderNumber" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">客户姓名</label>
            <div class="layui-input-block customerDIV">
                <input type="text" name="customerName" id="customerName" autocomplete="off" class="layui-input">
                <ul id="customerUL">

                </ul>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系方式</label>
            <div class="layui-input-block">
                <input type="text" name="phone" id="phone" autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">收货地址</label>
            <div class="layui-input-block">
                <input type="text" name="address" id="address" autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">长输入框</label>
            <div class="layui-input-block">
                <input type="text" name="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>
    </form>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>详细订单信息</legend>
    </fieldset>

    <table class="layui-hide" id="detaiOrder" lay-filter="detaiOrder"></table>
    <script type="text/html" id="detaiDemo">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="xuhaoTemplet">
        {{d.LAY_TABLE_INDEX+1}}
    </script>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>产品信息</legend>
    </fieldset>

    <table class="layui-hide" id="product" lay-filter="product"></table>
    <script type="text/html" id="productDemo">
        <a class="layui-btn layui-btn-xs" lay-event="addOrder">加入订单</a>
    </script>
    <script type="text/html" id="productxuhaoTemplet">
        {{d.LAY_TABLE_INDEX+1}}
    </script>

    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="images/jquery.js"></script>

    <script type="text/javascript">
        layui.use('table', function() {
            var table = layui.table;
            var orderNumber = $("#orderNumber").val();
            var tableIns=table.render({
                elem: '#detaiOrder'
                , url: 'detail/findDetail?orderNumber='+orderNumber
                , title: '用户数据表'
                , cols: [[
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'id', title: 'ID', width: 80, fixed: 'left',style:"display:none"}
                    , {field: 'xuhao', title: '序号', width: 80, fixed: 'left',templet:'#xuhaoTemplet'}
                    , {field: 'producName', title: '商品名称', width: 120, edit: 'text'}
                    , {field: 'productPrice', title: '商品价格', width: 150, edit: 'text'}
                    , {field: 'discountRate', title: '折扣', width: 80, edit: 'text'}
                    , {field: 'OrderDetailNumber', title: '商品数量', width: 100}
                    , {field: 'subtotal', title: '小计金额'}
                    , {field: 'productMarque', title: '商品型号', width: 80}
                    , {field: 'detailStatus', title: '状态', width: 120}
                    , {fixed: 'right', title: '操作', toolbar: '#detaiDemo', width: 150}
                ]]
                , page: true
                , done:function () {
                    $("[data-field='id']").css("display","none");
                }
            });
            table.render({
                elem: '#product'
                , url: 'product/findProduct'
                , title: '用户数据表'
                , cols: [[
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'id', title: 'ID', width: 80, fixed: 'left',style:"display:none"}
                    , {field: 'xuhao', title: '序号', width: 80, fixed: 'left',templet:'#productxuhaoTemplet'}
                    , {field: 'infoName', title: '商品名称', width: 120, edit: 'text'}
                    , {field: 'infoCost', title: '商品价格', width: 150, edit: 'text'}
                    , {field: 'infoPrice', title: '折扣', width: 80, edit: 'text'}
                    , {field: 'infoSpec', title: '商品型号', width: 100}
                    , {field: 'infoBrand', title: '小计金额'}
                    , {field: 'infoPostage', title: '商品型号', width: 80}
                    , {fixed: 'right', title: '操作', toolbar: '#productDemo', width: 150}
                ]]
                , page: true
                , done:function () {
                    $("[data-field='id']").css("display","none");
                }
            });
            //监听详细订单行工具事件
            table.on('tool(detaiOrder)', function(obj){
                var data = obj.data;
                //console.log(obj)
                if(obj.event === 'del'){
                    layer.confirm('真的删除行么', function(index){
                        $.ajax({
                            url:"detail/delDetail",
                            data:{"id":data.id},
                            type:"post",
                            dataType:"text",
                            success:function (data) {
                                if(data == "ok"){
                                    tableIns.reload({
                                        page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
                                }
                            }
                        })
                        layer.close(index);
                    });
                }
            });
            //监听产品行工具事件
            table.on('tool(product)', function(obj){
                var data = obj.data;
                //console.log(obj)
                if(obj.event === 'addOrder'){
                    //获取订单编号
                    var orderNumber = $("#orderNumber").val();
                    //获取收货人的姓名
                    var orderConsignee = $("#customerName").val();
                    //获取收货人的地址
                    var addtess = $("#address").val();
                    //获取客户ID
                    var customerId = $("#customerId").val();
                    //获取手机号码
                    var phone = $("#phone").val();
                    var orderJSON = {"productId":data.id,"producName":data.infoName,"infoDiscount":data.infoDidcount,
                    "infoCost":data.infoCost,"infoPrice":data.infoPrice,"infoSpec":data.infoSpec,"customerId":customerId,
                    "orderNumber":orderNumber,"address":addtess,"orderConsignee":orderConsignee,"phone":phone}
                    var orderDetailVo = JSON.stringify(orderJSON);
                    $.ajax({
                        url:"detail/addOrderDetail",
                        type:"post",
                        contentType:"application/json;charset=UTF-8",
                        data:orderDetailVo,
                        dataType:"text",
                        success:function (data) {
                            if(data == "ok"){
                                tableIns.reload({
                                    page: {
                                        curr: 1 //重新从第 1 页开始
                                    }
                                });
                            }
                        }
                    })
                }
            });
        });

        $.ajax({
            url:"order/getOrderCode",
            type:"post",
            dataType:"text",
            success:function (data) {
                $("#orderNumber").val(data);
            }
        });
    </script>
    <script type="text/javascript">
        $("#customerName").keyup(function () {
            var name = $("#customerName").val();
            if(!name){
                return;
            }
            $.ajax({
                url:"customer/getCustomerByName",
                data:{"name":name},
                type:"post",
                dataType:"json",
                success:function (data) {
                    $("#customerUL").empty();
                    $("#customerUL").css("display","block");
                    for(var i=0;i<data.length;i++){
                        $("#customerUL").append("<li customerId='"+data[i].id+"' phone='"+data[i].phone+"' address='"+data[i].address+"' onclick='addCustomerName(this)'>"+data[i].name+"<li>")
                    }
                }
            });
        });
        function addCustomerName(obj){
            var name=$(obj).text();
            var address=$(obj).attr("address");
            var phone=$(obj).attr("phone");
            var customerId=$(obj).attr("customerId");
            $("#customerName").val(name);
            $("#address").val(address);
            $("#phone").val(phone);
            $("#customerId").val(customerId);
            $("#customerUL").css("display","none");
        }
    </script>
</body>
</html>
