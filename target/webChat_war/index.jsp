<%--
  Created by IntelliJ IDEA.
  User: Administrator1
  Date: 2020/12/8
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:forward page="WEB-INF/pages/login.jsp" />
    <a href="account/testSpringmvc">test</a>
    <a href="account/findall">test2</a>
    <a href="pages/login.jsp">login</a>
<form action="account/save" method="post">
    姓名<input type="text" name="name">
    金额<input type="text" name="money">
    <input type="submit" value="提交">
</form>
</body>
</html>
