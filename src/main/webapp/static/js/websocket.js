// 连接WebSocket函数,定义连接方法以及定义WebSocket的各种方法
function connectWebsocket(){
    var websocket;
    //进行连接服务器
    var target = "ws://39.106.167.113:8080/webChat_war/hello.htm" ;
    // var target = "ws://localhost:8080/webChat_war_exploded/hello.htm" ; ;
    if ( 'WebSocket' in window) {
        websocket = new WebSocket(target);
    } else if ( 'MozWebSocket' in window) {
        websocket = new MozWebSocket(target);
    } else {
        alert( 'WebSocket is not supported by this browser.' );
        return ;
    }
    //连接服务器成功，进行修改用户登录状态
    websocket.onopen = function (evnt) {
        changeState(1) ;
        console.log("服务器连接成功");
    };
    //消息的显示
    websocket.onmessage = function (evnt) {
        console.log("接收到的消息:"+evnt);
        //也就是接收到的消息
        var now_chat = $("#right-message-box").attr("UID") ;
        console.log("当前会话对象UID："+now_chat);
        console.log(evnt.data) ;
        var obj = JSON.parse(evnt.data)
        console.log("JSON")
        console.log(obj)
        console.log(obj.messages)
        if(obj.hasOwnProperty("suids"))
        {

            //是当前会话对象
            if(obj.suids==now_chat)
            {
                var texts = "" ;
                console.log($('#mb2'));
                var src = $('#mb'+now_chat).find('img').attr("src")
                console.log(src);
                texts = "<div class='right-message-box'>";
                texts +=" <div class='right-message-friendimg-div'>"
                texts +="<img class='right-message-box-friend'  src='"+src+"'></div>"
                texts +="<span class='right-message-box-msg right-message-box-msg-left'>"+obj.messages+"<span class='right-message-box-msg-down'></span><span class='right-message-box-msg-top'></span></span></div>"
                $("#right-messagebox").append(texts);
                //触发已读状态
                console.log("消息id"+obj.id);
                updateMessageStatue(obj.id) ;
                //滑轮滑倒底部
                document.getElementById("right-messagebox").scrollTop = document.getElementById("right-messagebox").scrollHeight
            }
            else
            {
                console.log("不是当前会话对象")
                var suid =  obj.suids ;
                var ruid = obj.ruids ;
                console.log(suid)
                console.log(ruid)
                var bid ;
                //不是当前会话对象，进行显示在消息列表
                var child =  $("#message-box").children() ;
                console.log("进入孩子")
                console.log(child.length)
                for(var i = 0 ; i<child.length;i++)
                {
                    bid = $(child[i]).attr("id");
                    var suids = "mb" + suid ;
                    //找到了当前的用户对象,未读消息增加1
                    if( suids ==bid)
                    {
                        var box =  $(child[i]).find('.message-box-list-right') ;
                        var msgs = $(child[i]).find('.message-box-list-msglist') ;
                        console.log(msgs);
                        $(msgs).text(obj.messages.substr(0,6))
                        console.log(box)
                        if(box.find('.message-box-list-num-msglist').length==0)
                        {
                            var text = "<li class='message-box-list-num-msglist'>1</li>"
                            $(box).append(text)
                        }else
                        {
                            console.log("存在")
                            console.log($(box).find('.message-box-list-num-msglist'))
                            console.log($(box).find('.message-box-list-num-msglist').text());
                            var n = Number($(box).find('.message-box-list-num-msglist').text()) ;
                            n = n+1 ;
                            $(box).find('.message-box-list-num-msglist').text(n) ;
                        }
                        return ;
                    }
                }
                console.log("新加消息列表")
                addMsgList(obj) ;
            }
        }else if(obj.hasOwnProperty("uidstate"))
        {
            var uids = obj.uidstate ;
            var state= obj.state    ;
            if(uids==now_chat)
            {
                $("#onlineState").text(state) ;
            }
        }

    };
    websocket.onerror = function (evnt) {
    };
    //连接关闭，进行改变用户登录状态
    websocket.onclose = function (evnt) {
        console.log("服务器断开连接")
    }
    return websocket ;
}
function addMsgList(jsons)
{
    console.log("进行ajax增加消息列表")
    console.log(jsons)
    //获取到用户对象
    $.ajax({
        url:"../msglist/getnewlist",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(jsons),
        dataType:"json",
        method:"post",
        success:function (datas){
            console.log("接收到的消息")
            console.log(datas)
            var img = datas.friend.imgSrc ;
            var name = datas.friend.uname  ;
            var data = datas.date ;
            var date = new Date(data);
            var year = date.getFullYear();
            var mount = date.getMonth()+1 ;
            var day = date.getDate() ;
            var message = datas.lastMessage ;
            var msg = new String(message) ;
            var count = 1 ;
            var  text = "" ;
            text +="<div class='message-box' id='"+"mb"+datas.friend.uid+"' name='"+name+"'>"
            text +="<div class='message-box-img-div'><img class='message-box-img' src='../statics/imgs/"+img+"' ></div>"
            text += "<ul class='message-box-list-left'><li class='message-box-name'>"+name+"</li> <li class='message-box-list-msglist'>"+msg.substr(0,6)+"</li></ul>"
            text += "<ul class='message-box-list-right'><li class='message-box-date'>"+year+"-"+mount+"-"+day+" </li>"
            text +="<li class='message-box-list-num-msglist'>"+count+"</li></ul></div>"
            $("#message-box").append(text) ;
            console.log($("#message-box"))
            console.log("增加完毕")
            bindMessage() ;
        }
    });

}
function updateMessageStatue(id)
{
    console.log("更新的id:"+id);
    var json = {ids:id} ;
    $.ajax({
        url:"changeState",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(json),
        dataType:"json",
        method:"post"
    });
}
//发送消息处理函数JS
function send(websocket)
{
    if (websocket != null ) {
        var ruid = document.getElementById("ruid").value;
        console.log(ruid)
        var message = document.getElementById( 'message' ).value;
        var jsons  = {
            messages:message,
            ruids:ruid
        }
        websocket.send(JSON.stringify(jsons));
    }
    else {
        alert( '未与服务器链接.' );
    }
}