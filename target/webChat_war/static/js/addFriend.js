$(function () {
    $(document).on("click","#addFriend", function () {
        var ul = $("<ul id='addList'>" +
            "<li id='list_addFriend'>添加好友</li>" +
            "<li id='list_addGroup'>添加新分组</li></ul>");
        ul.appendTo($("body"));
        $(document).one("click", function () {
            ul.remove();
        })
    })

    $(document).on("click", "#list_addGroup", function () {
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
        table.attr("id", "addGroupTable");
        table.appendTo(form);


        var tr = $("<tr></tr>");
        tr.appendTo(table);
        var th = $("<th></th>");
        th.attr("colspan", "2")
        th.text("添加分组");
        th.appendTo(tr);
        var img = $("<img>");
        img.attr("src", "static/images/cha.png");
        img.attr("id", "cha");
        img.appendTo(th);

        var tr = $("<tr></tr>");
        tr.appendTo(table);
        var td2 = $("<td></td>");
        td2.appendTo(tr);
        var input = $("<input>");
        input.attr("type", "text");
        input.attr("maxlength", "8");
        input.attr("id", "group_name");
        input.appendTo(td2);
        var td = $("<td></td>");
        td.appendTo(tr);
        var input = $("<input>");
        input.attr("type", "button");
        input.attr("id", "addGroupBtn");
        input.attr("value", "添加");
        input.appendTo(td2);
    })

    function verifyGname() {
        var account = $("#group_name").val();
        var reg = /^\S{1,8}$/;
        return reg.test(account);
    }

    $(document).on("click", "#addGroupBtn", function () {
        if(!verifyGname()) {
            alert("请输入非空，长度小于等于8个字符的分组名");
        }
        $.get("addGroup", {"gname":$("#group_name").val()}, function (obj) {
            $("#background_div").remove();
            leftFriendClick();
        },"json");
        //$("#background_div").remove();
    })

    $(document).on("click", "#list_addFriend", function () {
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
        th.text("添加好友");
        th.appendTo(tr);
        var img = $("<img>");
        img.attr("src", "static/images/cha.png");
        img.attr("id", "cha");
        img.appendTo(th);


        var tr = $("<tr></tr>");
        tr.appendTo(table);
        var td2 = $("<td></td>");
        td2.appendTo(tr);
        var input = $("<input>");
        input.attr("type", "text");
        input.attr("id", "account");
        input.attr("maxlength", "15");
        input.appendTo(td2);
        var td = $("<td></td>");
        td.appendTo(tr);
        var input = $("<input>");
        input.attr("type", "button");
        input.attr("id", "searchFriendBtn");
        input.attr("value", "查询");
        input.appendTo(td);


    });


    function verifyAccount() {
        var account = $("#account").val();
        var reg = /^[1-9][0-9]{9}$/;
        return reg.test(account);
    }

    $(document).on("click", "#searchFriendBtn", function () {
        if(!verifyAccount()) {
            alert("请输入有效的账号！");
            return;
        }

        $.get("searchFriend", {"account":$("#account").val()}, function (obj) {
            $("#showFRow").remove();
            $("#showFRow02").remove();
            $("#showFRow03").remove();
            if(obj == true) {
                show = $("<tr id='showFRow'><td colspan='2'><div id='showFriend'>" + "您与他已经是好友了！" + "</div></td></tr>");
                show.appendTo($("#searchTable"));
                return;
            }
            if($.isEmptyObject(obj)){
                show = $("<tr id='showFRow'><td colspan='2'><div id='showFriend'>" + "未找到结果！" + "</div></td></tr>");
                show.appendTo($("#searchTable"));
                return;
            }
            var show = $("<tr id='showFRow'><td id='showFriend' colspan='2'><img src='" + obj.headimg+ "'id='search_friend_img'>" +
                "<span id='search_fname'>" + obj.uname+ "</span></td></tr>" +
                "<tr><td id='showFRow02' colspan='2'><textarea id='send_msg'></textarea></td></tr>" +
                "<tr id='showFRow03'><td colspan='2'><select id='chooseGroup'></select>" +
                "<input type='button' value='添加好友', id='sendRequest'></td></tr>");
            show.appendTo($("#searchTable"));
            $("#showFriend").attr("fuid", obj.uid);
            $("#send_msg").attr("maxlength", "30");
            $("#send_msg").attr("placeholder", "请输入验证信息");

            $.get("getGroup", function (obj) {
                for(var i in obj){
                    var gname = obj[i].gname;
                    var gid = obj[i].id;
                    var suid = obj[i].id;
                    var option = $("<option value='"+gname+"' gid='"+gid+"'>"+gname+"</option>");
                    $("#chooseGroup").append(option);
                }
            },"json");
        },"json");
    })
    $(document).on("click", "#sendRequest", function () {
        $.get("sendRequest", {"fuid":$("#showFriend").attr("fuid"), "rgroup":$(this).prev().find("option:selected").attr("gid"), "remark":$("#send_msg").val()}, function (obj) {
            $("#background_div").remove();
        },"json");
        $("#background_div").remove();
    })




})