<%--
  Created by IntelliJ IDEA.
  User: Object
  Date: 2019/10/21 0021
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>Title</title>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 100%;height:100%;"></div>
<!-- 引入 ECharts 文件 -->
<script type="text/javascript" src="images/echarts.min.js"></script>
<script type="text/javascript" src="images/jquery.js"></script>
<script type="text/javascript">
    var month = new Array();
    var datamonth = new Array();
    $.ajax({
        url: "statistics/findMonthData",
        type: "post",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                month[i] = "第" + data[i].months + "月";
                datamonth[i] = {"value": data[i].price, "name": month[i]}
            }
            initMonth()
        }
    });

    function initMonth() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '某站点用户访问来源',
                subtext: '纯属虚构',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: month
            },
            series: [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: datamonth,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
</script>
</body>
</html>
