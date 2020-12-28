$(function () {
    function isValidPwd() {
        var reg = /^\S{6,20}$/;
        var pwd = $("#originPwd").val();
        var newPwd = $("#newPwd").val();
        if(newPwd == pwd) {
            alert("新密码不能与旧密码一致");
            return false;
        } else if(!reg.test(pwd) || !reg.test(newPwd)) {
            alert("输入密码的格式不正确,请输入6-20位非空白符组成的密码！");
            return false;
        }
        return true;
    }



    function add0(m){return m<10?'0'+m:m }
    function parseDate01(date) {
        var time = new Date(date);
        var y = time.getFullYear();
        var m = time.getMonth()+1;
        var d = time.getDate();
        return y+'-'+add0(m)+'-'+add0(d);
    }
    $(document).on("click", "#changePwd", function () {
        $("#background_div").remove();
        var bg_div = $("<div></div>")
        bg_div.attr("id", "background_div");
        bg_div.appendTo($("body"));

        var form = $("<form></form>")
        form.attr("autocomplete", "off");
        form.attr("action", "update");
        form.attr("enctype", "multipart/form-data");
        form.attr("method", "post");
        form.appendTo(bg_div);

        var table = $("<table></table>");
        table.css("margin", "0 auto");
        table.attr("id", "searchTable");
        table.appendTo(form);

        var tr = $("<tr></tr>");
        tr.appendTo(table);
        var th = $("<th></th>");
        th.attr("colspan", "2");
        th.text("修改密码");
        th.appendTo(tr);
        var img = $("<img>");
        img.attr("src", "static/images/cha.png");
        img.attr("id", "cha");
        img.appendTo(th);

        var tr = $("<tr></tr>");
        tr.appendTo(table);
        var td1 = $("<td></td>");
        td1.attr("class", "label_name");
        td1.text("原密码");
        td1.appendTo(tr);
        var td2 = $("<td></td>");
        td2.appendTo(tr);
        var input = $("<input>");
        input.attr("type", "password");
        input.attr("id", "originPwd");
        input.attr("name", "originPwd");
        input.attr("maxlength", "20");
        input.appendTo(td2);

        var tr = $("<tr></tr>");
        tr.appendTo(table);
        var td1 = $("<td></td>");
        td1.attr("class", "label_name");
        td1.text("新密码");
        td1.appendTo(tr);
        var td2 = $("<td></td>");
        td2.appendTo(tr);
        var input = $("<input>");
        input.attr("type", "password");
        input.attr("maxlength", "20");
        input.attr("id", "newPwd");
        input.attr("name", "newPwd");
        input.appendTo(td2);


        var tr = $("<tr></tr>");
        tr.appendTo(table);

        var td2 = $("<td></td>");
        td2.appendTo(tr);
        td2.attr("colspan", "2");
        td2.attr("style", "text-align:center;");
        var input = $("<input>");
        input.attr("type", "button");

        input.attr("value", "修改");

        input.attr("id", "changePwdBtn");
        input.appendTo(td2);
    })



    $(document).on("click", "#changePwdBtn", function () {
        if(!isValidPwd()) {
            return;
        }
        $.get("changePwd", {"originPwd":$("#originPwd").val(), "newPwd":$("#newPwd").val()}, function (obj) {
            if(obj == "true") {
                $("#background_div").remove();
                alert("修改成功");
            }else{
                alert("修改失败,原密码输入错误");
            }
        },"text")
    })
})

