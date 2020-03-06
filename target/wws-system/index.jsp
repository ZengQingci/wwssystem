<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+
        request.getServerName()+":"+request.getServerPort()+path+"/";%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <link rel="stylesheet" href="css/hello.css">
</head>
<body>
<p>HelloWorld!</p>
<a href="user/returnHome">重定向</a>
<h2>Hello World!</h2>
<iframe name="iframe" src="test/sayHello" width="100%" height="100%"
        frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="auto"></iframe>
</body>
</html>
