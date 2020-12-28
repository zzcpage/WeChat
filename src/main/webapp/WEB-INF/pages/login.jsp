<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='utf-8'>
    <title>欢迎登录</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/style.css">
    <script src="<%=request.getContextPath()%>/static/js/validate.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/jquery-3.2.1.min.js"></script>
</head>
<body>

<!--
    1、验证用户名是否为10位数字
    2、验证密码是否为6~12位任意字符组成
-->
<div class="box">

    <h2>登录</h2>
    <form action="<%=request.getContextPath()%>/login" method="post">


        <table>
            <c:if test="${not empty requestScope.errorMsg}"><tr id="erroeMsg"><td colspan="2" style="text-align: center;color: red">${requestScope.errorMsg}</td></tr></c:if>
            <tr>
                <td class="right notice">账号：</td>
                <td><input id="username" type="text" name="account" maxlength="10" placeholder="请输入账号" onblur="validate_account(this.value)"/></td>
            </tr>
            <tr>
                <td></td>
                <td id="test_username" class="tip"></td>
            </tr>
            <tr>
                <td class="right notice">密码：</td>
                <td class=""><input id="password" type="password" maxlength="20" name="password" placeholder="密码应为6~20位字符" onblur="validate_password(this.value)"/></td>
            </tr>
            <tr>
                <td></td>
                <td id="test_pw" class="tip"></td>
            </tr>

            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" name="" value="登录" onclick="return validate_form2()"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><a href="<%=request.getContextPath()%>/toRegister">没有账号？点击注册</a></td>
            </tr>
        </table>

    </form>

</div>

</body>

</html>