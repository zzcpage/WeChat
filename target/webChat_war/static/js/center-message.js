function bindMessage()
{
    var message = $("#message-box").children() ;
    for (var i = 0; i < message.length; i++) {
        console.log("当前用户:"+message[i])
        console.log("id值为:"+$(this).attr("id")) ;
        console.log($(message[i]));
        $(message[i]).mousemove(function() {
            console.log("in");
            $(this).css("background-color", "rgb(235, 235, 235)")
        });
        $(message[i]).mouseout(function() {
            console.log("out")
            $(this).css("background-color", "rgba(255, 255, 255, 1)")
        });
        $(message[i]).dblclick(function() {
            var tid = $(this).attr("id");
            var tid2 = new String(tid);
            var ruids = tid2.substr(2);
            var state = "在线" ;
            var json = {ruid:ruids}
            var name = $(this).attr("name")
            var now = $(this)  ;
            console.log("进入 ajax请求")
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
                    // $("#right").css("background"," ")
                    addHistory(ruids,$("#right-messagebox")) ;
                    $("#right-message-box").attr("UID",ruids) ;
                    document.getElementById("right-messagebox").scrollTop = document.getElementById("right-messagebox").scrollHeight
                    now.find(".message-box-list-num-msglist").empty();
                    console.log(now);
                    //$(".message-box-list-num-msglist").empty()
                    console.log("删除了指定元素")
                    console.log($("#right"))
                }
            });
        });
    }
}
function addHistory(uid2s,divs)
{
    console.log("进入增加历史记录")
    console.log(uid2s)
    var json = {
        uid2:uid2s
    }
    $.ajax({
        url:"history",
        data:JSON.stringify(json),
        contentType:"appliaction/jsom;charset=UTF-8",
        dataType:"json",
        method:"post",
        success:function (datas){
            console.log(datas);
            for(var i = datas.length - 1  ; i >=0  ; i-- )
            {
                var msg = datas[i].message ;
                var date = new Date(datas[i].date);
                var year = date.getFullYear() ;
                var mount = date.getMonth()+1 ;
                var day = date.getDate();
                var hour = date.getHours() ;
                var mint = date.getMinutes() ;
                var seconds = date.getSeconds();
                var suid = Number(datas[i].suid) ;
                var ruid = Number(datas[i].ruid) ;
                var time = year+"-"+mount+"-"+day+"  "+hour+":"+mint+":"+seconds ;
                var img = datas[i].imgSrc        ;
                var name = datas[i].sName        ;
                var texts = "";
                if(suid==uid2s)
                {
                    texts = "<div class='right-message-box'>";
                    texts +=" <div class='right-message-friendimg-div'>"
                    texts +="<img class='right-message-box-friend'  src='"+img+"'></div>"
                    texts +="<span class='right-message-box-msg right-message-box-msg-left'>"+msg+"<span class='right-message-box-msg-down'></span><span class='right-message-box-msg-top'></span></span></div>"

                }
                else
                {
                    texts = "<div class='right-message-box right-message-box-body'> <span style='font-family: Arial, Helvetica, sans-serif;'></span>"
                    texts += "<div class='right-message-friendimg-div-right'>"
                    texts += "<img class='right-message-friendimg-right' src='"+img+"' ></div>"
                    texts += "<span class='right-message-box-msg right-message-box-msg-right'>"+msg+" <span class='right-message-box-msg-down'></span><span class='right-message-box-msg-top'></span></span></div>"
                }
                divs.append(texts);
                document.getElementById("right-messagebox").scrollTop = document.getElementById("right-messagebox").scrollHeight
            }
        }
    })
}
function addMessage()
{

}