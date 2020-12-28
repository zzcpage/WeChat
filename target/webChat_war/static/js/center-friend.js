//获取中间好友列表的格式
function getFriendList()
{
   var centerHtml = "<div id='friend-list'><div id='friend-list-text'>好友列表</div></div>"
    return centerHtml ;
}
//获取分组好友信息
function getGroupFriend()
{
    $(".friend-div").click(function(event) {

        //分组ID
        var gid = $(this).attr("id") ;
        var flag  = $(this).parent().find("ol").length ;
        var imgs = $(this).children()[0] ;
        var target = this ;
        //未打开好友列表
        if(flag==0)
        {
            $(imgs).attr("src", "static/images/down.jpg")
            var json = {
                gids:gid
            };
            $.ajax({
                url:"listfriend",
                contentType:"application/json;charset=UTF-8" ,
                data:JSON.stringify(json),
                dataType:"json",
                type:"post",
                success:function (data)
                {
                    var text = "" ;
                    // //解析对象JSON数组
                     text +="<ol>"
                    //增加好友样式即可显示好友样式
                    for(var i=0;i<data.length;i++)
                    {
                        text +="<li><div class='friend-box' id='"+data[i].uid+"'>"
                        text +="<div class='friend-box-imgs'><img class='friend-box-img' src='"+data[i].headimg+"' ></div>"
                        text +="<div class='friend-box-name'>"+data[i].uname+"</div>"
                        text +="<span class='friend-box-signature'>"+data[i].signature.substr(0,4)+"</span>"
                        text +="</div></li>"
                    }
                    text +="</ol>"
                    $(target).after(text) ;
                    bingFriendBox();
                    text = "";


                }
            });

        }else//打开了好友列表
        {
            $(imgs).attr("src", "static/images/right.PNG")
            //进行关闭好友列表
            $(this).parent().children('ol').remove();
        }
    }) ;




}
//进行好友样式的绑定
function bingFriendBox()
{
    var flag = false;
    var timer;
    var x;
    var y;
    var i = 0 ;
    $(document).on("mouseenter", ".friend-box-img", function(e) {
            flag = true;
            var th = $(this) ;
            timer = setTimeout(function() {
                var flags = $("#friend-box-card").length;
                if (flag&&flags==0) {

                    $.get("getFriendInfo", {"fuid":th.parent().parent().attr("id")}, function (obj) {
                        var f_signature = obj.signature;
                        var f_name = obj.uname;
                        var parent = th.parent().parent();
                        var div = $('<div></div>');
                        div.attr('id', "friend-box-card");
                        var card_name = $("<div></div>");
                        card_name.text(f_name);
                        card_name.attr("id", "friend-box-card-name");
                        card_name.appendTo($(div));
                        var card_signature = $("<div>"+f_signature+"</div>");
                        card_signature.attr("id", "friend-box-card-signature");
                        card_signature.appendTo($(div));
                        div.appendTo(parent);
                        //绝对位置
                        var p = parent.position() ;
                        var x= p.left + parent.width() ;
                        var docWidth = $(document).width() ;
                        if(x>docWidth-div.width-20)
                        {
                            x = p.left - div.width ;
                        }
                        div.css("left",x-140);// 再减80可到达头像
                        div.css("top",p.top);
                    },"json")


                }
            }, 500);
        })
    $(document).on("mouseleave", ".friend-box-img", function() {
        if (flag) {
            $("#friend-box-card").remove();
            flag = false;
            clearTimeout(timer)
        }
    })
    $(document).on("mouseenter", "#friend-box-card-signature", function(e) {
        var th = $(this)
        console.log("签名："+this)
        timer = setTimeout(function() {
            var div = $('<span></span>');
            div.text("详细消息/签名");
            div.attr("id", "friend-box-card-detail");
            div.css("top", e.pageY - 10 + "px");
            div.css("left", e.pageX - 60 + "px");
            div.appendTo(th);
        }, 500);

    })
    $(document).on("mouseleave", "#friend-box-card-signature", function(e) {
        $("#friend-box-card-detail").remove();
        clearTimeout(timer);
    })
    //好友列表详细显示（个性签名）
    $(document).on("mouseenter", ".friend-box-signature", function(e) {
        var th = $(this)
        timer = setTimeout(function() {
            var parent = th.parent() ;
            var p = parent.position() ;
            var x= p.left + parent.width() ;
            var docWidth = $(document).width() ;
            var div = $('<div></div>');
            if(x>docWidth-div.width-20)
            {
                x = p.left - div.width ;
            }
            getSignature($(parent).attr("id"),div,x,p,th)
        }, 500);

    })
    $(document).on("mouseleave", ".friend-box-signature", function(e) {
        $("#friend-box-card-detail-list").remove();
        clearTimeout(timer);
    })


    $(document).on("dblclick", ".friend-box", function () {
        var tid = $(this).attr("id");
        var ruids = tid;
        var name = $($(this).children()[1]).html() ;
        var json = {ruid:ruids}
        //$("#right").append('<div id="right-message-box"><div id="right-friend">' + $($(this).children()[1]).html() + '</div><div id="right-messagebox"></div><div class="right-message-history-div"><img class="right-message-history" src="static/images/history.JPG"></div><div id="right-message"><textarea id="send-messages"></textarea></div><div id="right-controller"><button id="right-send-message" onclick="sendmessage('+ruids+')">发送</button></div></div>');
        $.ajax({
            url:"getstate" ,
            data: JSON.stringify(json),
            type:"post",
            dataType: "json",
            contentType:"appliaction/jsom;charset=UTF-8",
            success:function (eve){
                console.log("返回登录状态")
                console.log(eve)
                state = eve.state ;
                console.log("进入 获得状态")
                console.log(state) ;
                $("#right").empty();
                $("#right").append('<div id="right-message-box"><div id="right-friend">' + name+"&nbsp;"+"<span id='onlineState'>"+state+"</span>"+ '</div><div id="right-messagebox"></div><div class="right-message-history-div"><img class="right-message-history" src="static/images/history.JPG"></div><div id="right-message"><textarea  id="send-messages" maxlength="200" oninput="showWords()"></textarea></div><div id="right-controller"><span id="limit-word">0/200</span><button id="right-send-message" onclick="sendmessage('+ruids+')">发送</button></div></div>');
                addHistory(ruids,$("#right-messagebox")) ;
                // $("#right").css("background"," ")
            }
        });
        leftMsgClick() ;
        $("#right-message-box").attr("UID",ruids) ;
        document.getElementById("right-messagebox").scrollTop = document.getElementById("right-messagebox").scrollHeight
        //$(".message-box-list-num-msglist").empty()
        console.log("删除了指定元素")
        console.log($("#right"))
        $("#right-message-box").attr("UID",ruids) ;
        //document.getElementById("right-messagebox").scrollTop = document.getElementById("right-messagebox").scrollHeight
        // $(".message-box-list-num-msglist").empty()

    })

}
//进行获取好友个性签名
function getSignature(ruid,div,x,p,th)
{
    var json = {
        uid:ruid
    }
    $.ajax({
        url:"getsig",
        contentType:"application/json;charset=UTF-8" ,
        data:JSON.stringify(json),
        dataType:"json",
        type:"post",
        success:function (data)
        {
            div.text("详细签名/"+data);
            div.attr("id", "friend-box-card-detail-list");
            div.css("left",x-172);
            div.css("top",p.top+32);
            div.appendTo(th);
        }
    });
}