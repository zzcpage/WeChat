$(function () {

    function parseDate02(date) {
        var time = new Date(date);
        var y = time.getFullYear();
        var m = time.getMonth()+1;
        var d = time.getDate();
        var h = time.getHours();
        var mm = time.getMinutes();
        var s = time.getSeconds();
        return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
    }

    //var i = 0;
    var comment_num = 0;
    function moreCom02() {
        var fuid = $("#comment_box_fri").attr("fuid");
        $.post("moreImpress", {"fuid":fuid,"start":comment_num}, function (obj) {
            var showSize = 20;
            if(obj.length < 20) {
                $("#more_fri").css("display", "none");
                //comment_num += obj.length;
                showSize = obj.length;
            } else {
                $("#more_fri").css("display", "block");
                //comment_num += 20;
            }
            for(var i =0; i < showSize; i++){
                var fname = obj[i].uname;
                var date = obj[i].date;
                var final = parseDate02(date);
                var curid = obj[i].suid;
                var msg = obj[i].msg;

                var div = $("<div></div>");
                div.attr("class", "comment");
                div.attr("fuid", curid);
                div.appendTo($("#comments"));
                if (comment_num % 2 == 1) {
                    div.css("background-color", "white");
                }
                var span1 = $("<span></span>");

                span1.attr("class", "friend_name");
                span1.text(fname);
                span1.appendTo(div);

                var span2 = $("<span></span>");
                span2.attr("class", "comment_time");
                span2.text(final + "描述了他：");
                span2.appendTo(div);

                var span3 = $("<span></span>");
                span3.attr("class", "comment_detail");
                span3.text(msg);
                span3.appendTo(div);

                var span4 = $("<span></span>");
                span4.appendTo(div);

                comment_num++;
            }
        },"json");
    }

    $(document).on("click", "#more_fri", moreCom02);

    $(document).on("click", "#shuaxing_fri", function () {
        comment_num = 0;
        $("#comments").remove();
        $("#more_fri").remove();
        var comments = $("<div></div>");
        comments.attr("id", "comments");
        comments.appendTo($("#comment_box_fri"));
        var more = $("<a id='more_fri' href='#' style='text-align: center'>显示更多</a>");
        more.appendTo($("#comment_box_fri"));
        moreCom02();
    });

    $(document).on("click", "#change",function(){
        $("#c_detail").removeAttr("disabled");
    });
    function add0(m){return m<10?'0'+m:m }

    $(document).on("click", "#showFriendImpress", function() {
        comment_num = 0;

        $("#right_box").remove();
        $("#showFriendInfo").css("background-color", "rgb(127, 127, 127)");
        $("#showFriendImpress").css("background-color", "white");
        var right_box = $("<div></div>");
        right_box.attr("id", "right_box");
        right_box.appendTo($("#right"));

        var comment_box = $("<div></div>");
        comment_box.attr("id", "comment_box_fri");
        comment_box.appendTo(right_box);

        var img = $("<img>");
        img.attr("src", "static/images/shuaxing.png");
        img.attr("id", "shuaxing_fri");
        img.appendTo(comment_box);

        var h3 = $("<h2></h2>");
        h3.attr("id", "title");
        h3.text("其他人对他的印象");
        h3.appendTo(comment_box);

        var comments = $("<div></div>");
        comments.attr("id", "comments");
        comments.appendTo(comment_box);

        var more = $("<a></a>");
        more.text("显示更多");
        more.attr("id", "more_fri");
        more.attr("style", "text-align: center;");
        more.attr("href", "#");
        more.appendTo(comment_box);

        var mycom = $("<div></div>")
        mycom.attr("id", "mycomment");
        mycom.appendTo(right_box);

        var textarea = $("<textarea></textarea>");
        textarea.attr("id", "c_detail");
        textarea.attr("placeholder", "请输入你对他的印象");
        textarea.attr("maxlength", "30");
        textarea.attr("disabled", "false");
        textarea.appendTo(mycom);
        $("<br/>").appendTo(mycom);

        var div = $("<div></div>");
        div.attr("style", "text-align: center; width: 840px;")
        div.appendTo(mycom);

        var b1 = $("<input>");
        b1.attr("class", "button");
        b1.attr("type", "button");
        b1.attr("value", "修改");
        b1.attr("id", "change");
        b1.appendTo(div);

        var b2 = $("<input>");
        b2.attr("class", "button");
        b2.attr("type", "button");
        b2.attr("value", "保存");
        b2.attr("id", "save");
        b2.appendTo(div);

        var fuid = $(this).attr("fuid");
        $("#comment_box_fri").attr("fuid", fuid);
        $.get("getImpress", {"fuid":fuid}, function (obj) {
            var showSize = 20;
            if(obj.length < 20) {
                $("#more_fri").css("display", "none");
                showSize = obj.length;
            } else {
                $("#more_fri").css("display", "block");
            }
            for(var i = 0; i < showSize; i++){
                var fname = obj[i].uname;

                var date = obj[i].date;
                var time = new Date(date);
                var final = parseDate02(date);
                var curid = obj[i].suid;
                var msg = obj[i].msg;

                var div = $("<div></div>");
                div.attr("class", "comment");
                div.attr("fuid", curid);
                div.appendTo($("#comments"));
                if (i % 2 == 1) {
                    div.css("background-color", "white");
                }
                var span1 = $("<span></span>");

                span1.attr("class", "friend_name");
                span1.text(fname);
                span1.appendTo(div);

                var span2 = $("<span></span>");
                span2.attr("class", "comment_time");
                span2.text(final + "描述了他：");
                span2.appendTo(div);

                var span3 = $("<span></span>");
                span3.attr("class", "comment_detail");
                span3.text(msg);
                span3.appendTo(div);
                comment_num++;
            }
        },"json");

        $.get("yourImpress", {"fuid":fuid}, function (obj) {
            $("#c_detail").text(obj.msg);
        },"json");

    })
    $(document).on("click", "#save", function () {
        var comment = $("#c_detail").val();
        var fuid = $("#comment_box_fri").attr("fuid");
        $.get("updateImpress", {"fuid":fuid, "msg":comment, "date":new Date()}, function () {

        },"json");
        $("#c_detail").attr("disabled","disabled");
    })
})