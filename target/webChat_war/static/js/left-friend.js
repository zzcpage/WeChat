//获取中间好友的列表
function leftFriendClick()
{
        var text = "<div id='friends'><div id='friends-two'>"
        $("#center-list").empty();
        text +=getFriendList()  ;
        $("#right").empty() ;
        $.ajax({
           url:"tajax",
           contentType:"application/json;charset=UTF-8" ,
           dataType:"json",
           type:"post",
           success:function (data)
           {
                   //通过 .key就能获取到对应的值
                   console.log(data)
                   //解析对象JSON数组
                   text +="<div id='friend-list-three'><ol>"
                   for(var i=0;i<data.length;i++)
                   {
                       text +="<li><div id="+data[i].id+" class='friend-div'><img draggable='false' src='static/images/right.PNG'><span >"+data[i].gname+"</span></div></li>"
                           console.log(data[i].gname)
                   }
                    text +="</ol></div></div></div>"
                   $("#center-list").append(text) ;
                   text = "";
                   getGroupFriend();
           }
        });
}