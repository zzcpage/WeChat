//消息列表的生成
function leftMsgClick()
{
    $("#center-list").empty();
    //从后端获取消息列表的消息数据,对于未读消息进行从后端获取
    $.ajax({
        url: "getmsglist",
        data:"",
        dataType:"json",
        contentType:"appliaction/jsom;charset=UTF-8",
        type:"post",
        success:function (datas){
            console.log("从服务器获得消息列表数据:"+datas)
            var text = "<div id='message-box'>"
            for(var i = 0 ; i < datas.length ; i++ )
            {
                var img = datas[i].friend.imgSrc ;
                var name = datas[i].friend.uname  ;
                var data = datas[i].date ;
                var date = new Date(data);
                var year = date.getFullYear();
                var mount = date.getMonth()+1 ;
                var day = date.getDate() ;
                var message = datas[i].lastMessage ;
                var msg = new String(message) ;
                var count = datas[i].count ;
                text +="<div class='message-box' id='mb"+datas[i].friend.uid+"' name='"+name+"'>"
                text +="<div class='message-box-img-div'><img class='message-box-img' src='"+img+"' ></div>"
                text += "<ul class='message-box-list-left'><li class='message-box-name'>"+name+"</li> <li class='message-box-list-msglist'>"+msg.substr(0,6) +"</li></ul>"
                text += "<ul class='message-box-list-right'><li class='message-box-date'>"+year+"-"+mount+"-"+day+" </li>"
                if(count==0)
                {
                    text +="</ul>"
                }else
                {
                    text +="<li class='message-box-list-num-msglist'>"+count+"</li></ul>"
                }
                text +="</div>"
            }
            text += "</div>"
            $("#center-list").empty() ;
            $("#center-list").append(text) ;
            bindMessage() ;
        }
    });
    $("#right").empty() ;
}