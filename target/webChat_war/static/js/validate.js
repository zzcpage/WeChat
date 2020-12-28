//onblur失去焦点事件，用户离开输入框时执行 JavaScript 代码：

//函数1：验证账号是否为10位数字 1234567890
function validate_account(username) {
    $("#erroeMsg").css("display", "none");
    var userNameReg = /^[1-9][0-9]{9}$/;
    //console.log(username);
    if (username != "" && username.search(userNameReg) != -1) {
        document.getElementById("test_username").innerHTML = "<font color='green' size='3px'>√账号格式正确</font>";
        return true;
    } else {
        document.getElementById("test_username").innerHTML = "<font color='red' size='3px'>账号应为10位数字</font>";
        return false;
    }
}


//函数2：验证密码是否为6~20位任意字符组成 123456
function validate_password(password) {
    //测试密码：123456
    $("#erroeMsg").css("display", "none");
    var passwordReg = /^.{6,20}$/;
    if (password != "" && password.search(passwordReg) != -1) {
        document.getElementById("test_pw").innerHTML = "<font color='green' size='3px'>√密码格式正确</font>";
        return true;
    } else {
        document.getElementById("test_pw").innerHTML = "<font color='red' size='3px'>密码应为6~20位字符</font>";
        return false;
    }
}

//函数3：验证两次输入的密码是否一样
function validate_password2(password2) {
    $("#erroeMsg").css("display", "none");
    var password = document.getElementById("password").value;
    //测试：console.log(password);
    //测试：console.log(password2);
    if (password == "") {
        document.getElementById("is_test_pw").innerHTML = "<font color='red' size='3px'>密码不为空</font>";
        return false;
    } else if (password == password2) {
        document.getElementById("is_test_pw").innerHTML = "<font color='green' size='3px'>√两次输入的密码相同</font>";
        return true;
    } else {
        document.getElementById("is_test_pw").innerHTML = "<font color='red' size='3px'>两次输入的密码不相同</font>";
        return false;

    }
}

//函数3：验证两次输入的密码是否一样
function validate_yzm(yzm) {
    $("#erroeMsg").css("display", "none");
    var password = document.getElementById("yzm").value;

    var reg =/^[0-9A-Za-z]{5}$/;
    if(reg.test(yzm)) {
        document.getElementById("test_yzm").innerHTML = "";
        return true;
    }else{
        document.getElementById("test_yzm").innerHTML = "验证码错误";
        return false;
    }

}

function validate_username(username) {
    $("#erroeMsg").css("display", "none");
    var reg = /^\S{1,10}/;
    if (username != "" && reg.test(username)) {
        document.getElementById("test_username").innerHTML = "<font color='green' size='3px'>√昵称格式正确</font>";
        return true;
    } else {
        document.getElementById("test_username").innerHTML = "<font color='red' size='3px'>昵称应为1-10位字符</font>";
        return false;
    }

}

//函数4：验证表单是否已经填好
function validate_form() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var password2 = document.getElementById("password2").value;
    var yzm = document.getElementById("yzm").value;

    if(validate_username(username) && validate_password(password) && validate_password2(password2)  && validate_yzm(yzm)) {
        return true;
    }
    return false;

}

function validate_form2() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var userNameReg = /^\d{10}$/;
    var passwordReg = /^.{6,20}$/;
    if (username != "" && userNameReg.test(username)) {

        if (password != "" && passwordReg.test(password)) {
            console.log("信息填写正确，可以正常提交！");
            $("form").submit() ;
            return true;
        } else {
            // alert("密码格式错误，提交失败，请重新填写！");
            console.log("密码格式错误，提交失败，请重新填写！");
            return false;
        }

    } else {
        // alert("注册的账号不符合要求，提交失败，请重新填写！");
        console.log("注册的账号不符合要求，提交失败，请重新填写！");
        return false;
    }
}


