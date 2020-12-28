$(function () {
    function add0(m){return m<10?'0'+m:m }

    function getAllRequest() {
        $("#right").remove();
        var right = $("<div></div>");
        right.attr("id", "right");
        right.appendTo($("#right_td"));

        var h1 = $("<h1>好友验证<h1>");
        h1.css("text-align", "center");
        h1.appendTo(right);

        var right_box = $("<div id='right_box'></div>");
        right_box.attr("style", "height:515px;overflow-y: auto;")
        right_box.appendTo(right);

        $.get("getRequest", function (obj) {

            if($.isEmptyObject(obj)) {
                var info = $("<h3>暂无好友验证</h3>");
                info.appendTo(right_box);
                info.css("text-align","center");
                return;
            }

            for(var i in obj){
                var fname = obj[i].uname;
                var date = obj[i].dates;
                var time = new Date(date);
                var y = time.getFullYear();
                var m = time.getMonth()+1;
                var d = time.getDate();
                var final = y+'-'+add0(m)+'-'+add0(d);
                var msg = obj[i].remark;
                var img_src = obj[i].imgSrc;
                var request_id = obj[i].id;
                var request_time = $("<div></div>");
                var state = obj[i].state;

                request_time.text(final);
                request_time.attr("class", "request_time");
                request_time.appendTo(right_box);

                var request_item = $("<div class='request_item' rid='"+ request_id+"'>" +
                    "<div class='div1'>" +
                    "<img class='request_img' src='"+img_src+"'>" +
                    "</div>" +
                    "<div class='div2'>" +
                    "<div class='request_fname'>"+fname+"</div>" +
                    "<div class='request_tip'>验证信息</div>" +
                    "<div class='yanzheng'>"+msg+"</div></div>" +
                    "<div class='div3' style='text-align: center'>" +
                    "<select class='request_ans'>" +
                    "<option value='1'>同意</option>" +
                    "<option value='2'>拒绝</option></select>" +
                    "<select style='width: 50px' class='group_select'>" +
                    "</select><input type='button' value='确认' class='update_req'></div></div>");
                request_item.appendTo(right_box);
                if(state == "1") {
                    $(".div3:last").html("已通过");
                } else if(state == "2") {
                    $(".div3:last").html("已拒绝");
                }
            }
            $.get("getGroup", function (obj) {
                for(var i in obj){
                    var gname = obj[i].gname;
                    var gid = obj[i].id;
                    var suid = obj[i].id;
                    var option = $("<option value='"+gname+"' gid='"+gid+"'>"+gname+"</option>");
                    $(".group_select").append(option);
                }
            },"json");
        },"json");
    }

    $(document).on("click", "#handle_request", getAllRequest);

    $(document).on("change", ".request_ans", function () {
        if($(this).val() == "1") {
            $(this).next().removeAttr("disabled");
        }else{
            $(this).next().attr("disabled", "disabled");
        }
    })

    $(document).on("click", ".update_req", function () {
        var gid = $(this).prev().find("option:selected").attr("gid");
        var rid = $(this).parent().parent().attr("rid");
        var state = $(this).prev().prev().find("option:selected").val();
        $.get("updateRequest", {"rid":rid, "sgroup":gid, "state":state}, function (obj) {
            getAllRequest();
        },"json");
    })
})