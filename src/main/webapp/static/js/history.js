$(function () {
    var historynum = 0;
    var flag = true;
    $(document).on('mousewheel',"#right_history_box", function(event) {

        var top = this.scrollTop;
        var height = this.scrollHeight;

        var right_history_box = this;

        var fuid = $("#right_history_box").attr("fuid");
        if(flag && top == 0 && event.deltaY > 0) {
            $.get("moreHistory", {"fuid":fuid,"start":historynum}, function (obj) {
                var num = obj.length;
                if(num == 0) {
                    flag = false;
                }
                for(var i = 0; i < num; i++) {
                    var name = obj[i].sName;
                    var time = parseDate02(obj[i].date);
                    var message = obj[i].message;
                    var class_history;

                    if(obj[i].suid == fuid) {
                        class_history = "right-history-receive"
                    } else {
                        class_history = "right-history-send"
                    }
                    var history_item = $("<div class='right-history-show'>" +
                        "<div class='right-history-info'>" +
                        "<table class='"+class_history+"'>" +
                        "<td>"+name+"</td>" +
                        "<td>"+time+"</td>" +
                        "<td></td></table></div>" +
                        "<div class='right-history-msg'>"+message+"</div></div>");
                    history_item.prependTo($("#right_history_box"));
                    historynum++;
                }
                right_history_box.scrollTop += right_history_box.scrollHeight - height;
            })
        }
    });

    function add0(m){return m<10?'0'+m:m }
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

    $(document).on("click", ".right-message-history", function () {
        historynum = 0;
        flag = true;
        var fuid = $("#right-message-box").attr("uid");
        var fname = $("#right-friend").html();
        $("#right-message-box").remove();
        var top = $("<div></div>");
        var title = $("<div id='right_history_title'>"+fname+"</div>");
        title.appendTo($("#right"));
        var right_history_box = $("<div id='right_history_box'></div>");
        right_history_box.attr("fuid", fuid);
        right_history_box.appendTo($("#right"));
        var th = document.getElementById("right_history_box");
        $.get("moreHistory", {"fuid":fuid,"start":historynum}, function (obj) {
            var num = obj.length;
            for(var i = 0; i < num; i++) {
                var name = obj[i].sName;
                var time = parseDate02(obj[i].date);
                var message = obj[i].message;
                var class_history;

                if(obj[i].suid == fuid) {
                    class_history = "right-history-receive"
                } else {
                    class_history = "right-history-send"
                }
                var history_item = $("<div class='right-history-show'>" +
                    "<div class='right-history-info'>" +
                    "<table class='"+class_history+"'>" +
                    "<td>"+name+"</td>" +
                    "<td>"+time+"</td>" +
                    "<td></td></table></div>" +
                    "<div class='right-history-msg'>"+message+"</div></div>");
                history_item.prependTo(right_history_box);
                historynum++;
            }
            th.scrollTop = th.scrollHeight;
        })
    })
})