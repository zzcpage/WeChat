<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/12/12
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>查询了所有信息</h3>
    <c:forEach items="${user}" var="p">
        ${p.uname}
    </c:forEach>
</body>
</html>
