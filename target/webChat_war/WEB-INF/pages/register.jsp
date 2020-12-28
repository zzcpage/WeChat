<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='utf-8'>
    <title>欢迎登录</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/style.css">
    <script src="<%=request.getContextPath()%>/static/js/validate.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/jquery-3.2.1.min.js"></script>
    <script>
        $(function () {
            $("#yzm_img").click(function () {
                this.src = "kaptcha.jpg?d=" + new Date();
            })
        })
    </script>
</head>
<body>

<!--
    1、验证昵称是否为10位数字
    2、验证密码是否为6~12位任意字符组成
    3、验证密码和确认密码是否一致
    4、验证邮箱是否符合规范
-->
<div class="box">
    <h2>注册</h2>
    <form action="register" method="post">
        <table>
            <c:if test="${not empty requestScope.errorMsg}">
                <tr id="erroeMsg" ><td colspan="2" style="text-align: center;color: red">${requestScope.errorMsg}</td></tr>
            </c:if>
            <tr>
                <td class="right notice">昵称：</td>
                <td><input id="username" type="text" name="name" placeholder="请输入1-10位昵称" maxlength="10" onblur="validate_username(this.value)"/></td>
            </tr>
            <tr>
                <td></td>
                <td id="test_username" class="tip"></td>
            </tr>
            <tr>
                <td class="right notice">密码：</td>
                <td class=""><input id="password" type="password" name="password" placeholder="密码应为6~20位字符"
                                    onblur="validate_password(this.value)" maxlength="20"/></td>

            </tr>
            <tr>
                <td></td>
                <td id="test_pw" class="tip"></td>
            </tr>
            <tr>
                <td class="right notice">确认密码：</td>
                <td><input id="password2" type="password" name="password2" maxlength="20" onblur="validate_password2(this.value)  "/></td>

            </tr>
            <tr>
                <td></td>
                <td id="is_test_pw" class="tip"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input style="width: 120px;height: 40px;font-size: 15px" type="text" placeholder="请输入验证码" maxlength="5" class="intxt" id="yzm" name="yzm" onblur="validate_yzm(this.value)">
                <img style="vertical-align: top" id="yzm_img" src="kaptcha.jpg" width="100px" height="45px" title="点击切换验证码"></td>
            </tr>
            <tr>
                <td id="test_yzm" class="tip" style="color: red;text-align: right"></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" id="submit_form" value="注册" onclick="return validate_form()"/>
                    <input type="reset" value="重置"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><a href="toLogin">已有账号？点击登录</a></td>
            </tr>
        </table>
    </form>
</div>

</body>

</html>