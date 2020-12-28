
$(function () {
    //更新用户信息
    $(document).on("click", "#change_msg", function() {
        $("#background_div").remove();
        var bg_div = $("<div></div>")
        bg_div.attr("id", "background_div");
        bg_div.appendTo($("body"));

        var form = $("<form></form>")
        form.attr("autocomplete", "off");
        form.attr("action",  "update");
        form.attr("enctype", "multipart/form-data");
        form.attr("method", "post");
        form.appendTo(bg_div);

        var table = $("<table></table>");
        table.css("margin", "0 auto");
        table.appendTo(form);


        var tr = $("<tr></tr>");
        tr.appendTo(table);
        var th = $("<th></th>");
        th.attr("colspan", "2")
        th.text("修改基本信息")
        th.appendTo(tr)
        var img = $("<img>")
        img.attr("src",  "static/images/cha.png");
        img.attr("id", "cha");
        img.appendTo(th);

        $.get("getUserInfo", function (obj) {
            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("头像");
            td1.appendTo(tr);
            var td2 = $("<td></td>");
            td2.appendTo(tr);
            var label = $("<label></label>")
            label.appendTo(td2)

            var img = $("<img>")
            img.attr("src",  obj.headimg);
            img.attr("id", "touxiang");
            img.appendTo(label);
            var input = $("<input>");
            input.attr("type", "file");
            input.attr("id", "chooseImg");
            input.attr("name", "upload");
            input.attr("accept", ".jpg, .jpeg, .png");
            input.appendTo(label);


            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.attr("maxlength", "10");
            td1.text("用户名");
            td1.appendTo(tr);
            var td2 = $("<td></td>");
            td2.appendTo(tr);
            var input = $("<input>");
            input.attr("value", obj.uname)
            input.attr("type", "text");
            input.attr("id", "username");
            input.attr("name", "username");
            input.appendTo(td2);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("性别")
            td1.appendTo(tr)

            var td2 = $("<td></td>");
            td2.appendTo(tr);
            var input = $("<input>");
            input.attr("type", "radio");
            input.attr("value", "男");
            input.attr("name", "sex");
            input.attr("id", "nan");
            input.appendTo(td2);
            var label = $("<label></label>");
            label.attr("for", "nan")
            label.appendTo(td2);
            label.text("男")
            if(obj.sex == "男") {
                input.attr("checked", "checked")
            }

            var input = $("<input>");
            input.attr("type", "radio");
            input.attr("value", "女");
            input.attr("id", "nv");
            input.attr("name", "sex");
            input.attr("style", "margin-left: 50px");
            input.appendTo(td2);
            var label = $("<label></label>");
            label.attr("for", "nv");
            label.appendTo(td2);
            label.text("女")
            if(obj.sex == "女") {
                input.attr("checked", "checked")
            }

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("生日");
            td1.appendTo(tr);
            var td2 = $("<td></td>");
            td2.appendTo(tr);
            var input = $("<input>");
            input.attr("type", "date");
            input.attr("value", parseDate01(obj.birthday));
            input.attr("max", parseDate01(new Date()));
            input.attr("name", "birthday");
            input.attr("id", "birthday");
            input.appendTo(td2);



            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("邮箱");
            td1.appendTo(tr);
            var td2 = $("<td></td>");
            td2.appendTo(tr);
            var input = $("<input>");
            input.attr("value", obj.email)
            input.attr("type", "text");
            input.attr("id", "email");
            input.attr("name", "email");
            input.appendTo(td2);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("个性签名");
            td1.appendTo(tr)
            var td2 = $("<td></td>");
            td2.appendTo(tr);
            var textarea = $("<textarea></textarea>");
            textarea.attr("name", "signature");
            textarea.attr("maxlength", "100");
            textarea.attr("id", "signature");
            textarea.val(obj.signature);
            textarea.appendTo(td2);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("colspan", "2");
            td1.appendTo(tr)
            var input = $("<input>");
            input.attr("type", "button");
            input.attr("value", "保存");
            input.attr("id", "btn_save");
            input.css("margin", "0 auto");
            input.css("display", "block");
            input.appendTo(td1);
        },"json")


    })



    function parseDate01(date) {
        var time = new Date(date);
        var y = time.getFullYear();
        var m = time.getMonth()+1;
        var d = time.getDate();
        return y+'-'+add0(m)+'-'+add0(d);
    }

    function add0(m){return m<10?'0'+m:m }

    //显示我的基本资料
    function showUserInfo() {
        $("#right").remove();
        $.get("getUserInfo", function (obj) {
            var right = $("<div></div>");
            right.attr("id", "right");
            right.appendTo($("#right_td"));

            var topbar = $("<div></div>");
            topbar.attr("id", "topbar");
            topbar.appendTo(right);

            var span1 = $("<span></span>");
            span1.attr("id", "showInfo");
            span1.text("基本信息");
            span1.css("background-color", "white");
            $("#showImpress").css("background-color", "rgb(127, 127, 127)");

            span1.attr("class", "changeModule");
            span1.appendTo(topbar);
            var span2 = $("<span></span>");
            span2.attr("class", "changeModule");
            span2.attr("id", "showImpress");
            span2.text("好友印象")
            span2.appendTo(topbar);

            var right_box = $("<div></div>");
            right_box.attr("id", "right_box");
            right_box.appendTo(right);

            var table = $("<table></table>");
            table.attr("id", "info_table");
            //table.css("margin", "0 auto");
            //table.css("height", "400px");
            table.appendTo(right_box);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var th = $("<th></th>");
            th.attr("colspan", "2")
            th.attr("style", "font-size:30px")
            th.text("基本信息")
            th.appendTo(tr)

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("账号")
            td1.appendTo(tr)
            var td2 = $("<td></td>");
            td2.text(obj.account);
            td2.appendTo(tr);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("昵称")
            td1.appendTo(tr)
            var td2 = $("<td></td>");
            td2.text(obj.uname);
            td2.appendTo(tr);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("性别")
            td1.appendTo(tr)

            var td2 = $("<td></td>");
            td2.text(obj.sex);
            td2.appendTo(tr);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("生日");
            td1.appendTo(tr);
            var td2 = $("<td></td>");
            td2.text(parseDate01(obj.birthday));
            td2.appendTo(tr);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("邮箱");
            td1.appendTo(tr);
            var td2 = $("<td></td>");
            td2.text(obj.email);
            td2.appendTo(tr);


            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");

            td1.text("个性签名");
            td1.appendTo(tr);
            var td2 = $("<td></td>");
            td2.appendTo(tr);
            var div = $("<div></div>");
            div.text(obj.signature);
            div.attr("id", "mysign");
            div.appendTo(td2);



            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("colspan", "2");
            td1.appendTo(tr)
            var input = $("<input>");
            input.attr("type", "button");
            input.attr("value", "修改信息");
            input.attr("id", "change_msg")
            input.css("margin", "0 auto");
            input.css("display", "block");
            input.appendTo(td1);
        });

    }

    $(document).on("click", "#headimg", showUserInfo);

    $(document).on("click", "#showInfo", showUserInfo);

    function verifyForm() {
        var username = $("#username").val();
        var email = $("#email").val();
        //var birthday = $("#birthday").val();

        var reg1 = /^\S{1,10}$/;
        var reg2 = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
        if(!reg1.test(username)) {
            alert("用户名格式错误，请输入长度10位以内非空(不包含空格换行等)字符串！");
            return false;
        }
        if(!reg2.test(email) && email.length > 0) {
            alert("邮箱格式错误！");
            return false;
        }
        return true;

    }

    //不刷新页面上传数据
    $(document).on("click", "#btn_save", function () {
        if(!verifyForm()) {
            return;
        }
        var formData = new FormData();
        formData.append("upload",$("#chooseImg")[0].files[0]);
        formData.append("sex",$('input[name="sex"]:checked').val());
        formData.append("username",$("#username").val());
        formData.append("birthday",$("#birthday").val());
        formData.append("signature",$("#signature").val());
        formData.append("email",$("#email").val());
        $.ajax({
            url:  'update',
            type: 'post',
            data: formData,
            contentType: false,
            processData: false,
            success:function(obj){
                if(!$.isEmptyObject(obj))
                    $("#headimg").attr("src", obj.headimg);
                showUserInfo();
            }
        })
        $("#background_div").remove();

    });

    $(document).on("click", "#cha", function () {
        $("#background_div").remove();
    });

    //更新图片（不上传）
    $(document).on('change', "#chooseImg", function () {
        var filePath = $(this).val(),         //获取到input的value，里面是文件的路径
            fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
            src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式
        // 检查是否是图片
        if (!fileFormat.match(/.png|.jpg|.jpeg/)) {
            alert('上传错误,文件格式必须为：png/jpg/jpeg');
            return;
        }
        $('#touxiang').attr('src', src);
    });
})