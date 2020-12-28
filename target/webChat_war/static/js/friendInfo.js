$(function () {
    function add0(m){return m<10?'0'+m:m }
    function parseDate01(date) {
        var time = new Date(date);
        var y = time.getFullYear();
        var m = time.getMonth()+1;
        var d = time.getDate();
        return y+'-'+add0(m)+'-'+add0(d);
    }
    function showUserInfo() {
        $("#right").remove();
        var right = $("<div></div>");
        right.attr("id", "right");
        right.appendTo($("#right_td"));

        var topbar = $("<div></div>");
        topbar.attr("id", "topbar");
        topbar.appendTo(right);

        var span1 = $("<span></span>");
        span1.attr("id", "showFriendInfo");
        span1.attr("fuid", $(this).attr("id"));
        span1.text("好友基本信息");
        span1.css("background-color", "white");
        $("#showFriendImpress").css("background-color", "rgb(127, 127, 127)");

        span1.attr("class", "changeModule");
        span1.appendTo(topbar);
        var span2 = $("<span></span>");
        span2.attr("class", "changeModule");
        span2.attr("id", "showFriendImpress");
        span2.attr("fuid", $(this).attr("id"));
        span2.text("好友印象");
        span2.appendTo(topbar);

        var right_box = $("<div></div>");
        right_box.attr("id", "right_box");
        right_box.appendTo(right);

        var table = $("<table></table>");
        table.attr("id", "info_table");
        table.appendTo(right_box);
        $.get("getFriendInfo", {"fuid":$(this).attr("id")}, function (obj) {

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var th = $("<th></th>");
            th.attr("colspan", "2");
            th.css("font-size", "25px");
            th.text("好友基本信息");
            th.appendTo(tr);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("账号");
            td1.appendTo(tr);
            var td2 = $("<td></td>");
            td2.text(obj.account);
            td2.appendTo(tr);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("昵称");
            td1.appendTo(tr);
            var td2 = $("<td></td>");
            td2.text(obj.uname);
            td2.attr("id", "friend_name_infotable");
            td2.appendTo(tr);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("性别");
            td1.appendTo(tr);

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
            td2.text(parseDate01(obj.birth));
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
            td1.css("text-align", "center");
            td1.appendTo(tr);

            var input2 = $("<input>");
            input2.attr("type", "button");
            input2.attr("value", "修改分组");
            input2.attr("id", "changeGroup")
            input2.appendTo(td1);

            var input = $("<input>");
            input.attr("type", "button");
            input.attr("value", "删除好友");
            input.attr("id", "deleteFriBtn");
            input.css("margin-left", "30px");
            input.appendTo(td1);
        },"json");
    }


    $(document).on("click", "#deleteFriBtn", function () {
        var flag = confirm("你确定要删除"+ $("#friend_name_infotable").html()+"吗？");
        if(flag) {
            $.get("deleteFriend",{"fuid":$("#showFriendInfo").attr("fuid")}, function (obj) {
                //window.location.reload();
                leftFriendClick();
            });
        }
    })

    function showUserInfo02() {
        $("#right_box").remove();
        $("#showFriendImpress").css("background-color", "rgb(127, 127, 127)");
        $("#showFriendInfo").css("background-color", "white");
        var right_box = $("<div></div>");
        right_box.attr("id", "right_box");
        right_box.appendTo($("#right"));

        var table = $("<table></table>");
        table.attr("id", "info_table");
        table.appendTo(right_box);
        $.get("getFriendInfo", {"fuid":$("#showFriendInfo").attr("fuid")}, function (obj) {
            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var th = $("<th></th>");
            th.attr("colspan", "2")
            th.text("好友基本信息")
            th.appendTo(tr)

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("账号")
            td1.appendTo(tr)
            var td2 = $("<td></td>");
            td2.text(obj.account)
            td2.appendTo(tr);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("昵称");
            td1.appendTo(tr)
            var td2 = $("<td></td>");
            td2.text(obj.uname);
            td2.appendTo(tr);

            var tr = $("<tr></tr>");
            tr.appendTo(table);
            var td1 = $("<td></td>");
            td1.attr("class", "label_name");
            td1.text("性别");
            td1.appendTo(tr);

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
            td2.text(parseDate01(obj.birth));
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
            td1.css("text-align","center");
            td1.appendTo(tr);
            var input2 = $("<input>");
            input2.attr("type", "button");
            input2.attr("value", "修改分组");
            input2.attr("id", "changeGroup")
            input2.appendTo(td1);
            var input = $("<input>");
            input.attr("type", "button");
            input.attr("value", "删除好友");
            input.css("margin-left", "30px");
            input.attr("id", "deleteFriBtn")
            input.appendTo(td1);



        },"json");
    }

    $(document).on("click", "#changeGroup", function () {
        $("#background_div").remove();
        var bg_div = $("<div></div>");
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
        table.attr("id", "addGroupTable");
        table.appendTo(form);


        var tr = $("<tr></tr>");
        tr.appendTo(table);
        var th = $("<th></th>");
        th.attr("colspan", "2");
        th.text("修改分组");
        th.appendTo(tr);
        var img = $("<img>");
        img.attr("src", "static/images/cha.png");
        img.attr("id", "cha");
        img.appendTo(th);

        var tr = $("<tr><td colspan='2'><select id='chooseGroup'></select><input type='button' value='确定', id='changeGroupBtn'></td></tr>");
        tr.appendTo(table);

        $.get("getGroup", function (obj) {
            for(var i in obj){
                var gname = obj[i].gname;
                var gid = obj[i].id;
                var suid = obj[i].id;
                var option = $("<option value='"+gname+"' gid='"+gid+"'>"+gname+"</option>");
                $("#chooseGroup").append(option);
            }

            $.get("getFriendGroup",{"fuid": $("#showFriendInfo").attr("fuid")}, function (obj) {
                $("option[gid="+obj+"]").attr("selected", "selected");
            },"json");

        },"json");
    })

    $(document).on("click", "#changeGroupBtn", function () {
        $.get("changeGroup", {"fuid" : $("#showFriendInfo").attr("fuid"), "newgid":$(this).prev().find("option:selected").attr("gid")}, function (obj) {
            $("#background_div").remove();
            //window.location.reload();
            leftFriendClick();
        },"json");
    })

    $(document).on("click", ".friend-box", showUserInfo);

    $(document).on("click", "#showFriendInfo", showUserInfo02);
})

