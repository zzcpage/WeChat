<%--
  Created by IntelliJ IDEA.
  User: Administrator1
  Date: 2020/12/18
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="<%=request.getContextPath()%>/static/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/Particleground.js"></script>
<html>
<head>
    <title>WebQQ</title>
    <script>
        $(function () {
            function add0(m){return m<10?'0'+m:m }
            //显示我的好友印象
            $(document).on("click", "#showImpress", function() {
                comment_num = 0;
                $("#right_box").remove();

                $("#showImpress").css("background-color", "white");
                $("#showInfo").css("background-color", "rgb(127,127,127)");

                var right_box = $("<div></div>");
                right_box.attr("id", "right_box");
                right_box.appendTo($("#right"))

                var comment_box = $("<div></div>");
                comment_box.attr("id", "comment_box");
                comment_box.attr("fuid", "${user.uid}");
                comment_box.appendTo(right_box);

                var img = $("<img>")
                img.attr("src",  "static/images/shuaxing.png");
                img.attr("id", "shuaxing");
                img.appendTo(comment_box)

                var h3 = $("<h2></h2>");
                h3.attr("id", "title");
                h3.text("大家对我的印象")
                h3.appendTo(comment_box);

                var comments = $("<div></div>");
                comments.attr("id", "comments");
                comments.appendTo(comment_box);

                var more = $("<a></a>");
                more.text("显示更多");
                more.attr("id", "more");
                more.attr("style", "text-align: center;");
                more.attr("href", "#");
                more.appendTo(comment_box);

                $.get( "getImpress", {"fuid":$("#comment_box").attr("fuid")}, function (obj) {
                    //alert(obj.length);
                    var showSize = 20;
                    if(obj.length < 20) {
                        $("#more").css("display", "none");
                        //comment_num += obj.length;
                        showSize = obj.length;
                    } else {
                        $("#more").css("display", "block");
                        //comment_num += 20;
                    }
                    //alert(showSize);
                    for(var i = 0; i < showSize; i++){
                        var fname = obj[i].uname;
                        var date = obj[i].date;
                        var time = new Date(date);
                        var y = time.getFullYear();
                        var m = time.getMonth()+1;
                        var d = time.getDate();
                        var h = time.getHours();
                        var mm = time.getMinutes();
                        var s = time.getSeconds();
                        var final = y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);

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
                        span2.text(final + "描述了我：");
                        span2.appendTo(div);

                        var span3 = $("<span></span>");
                        span3.attr("class", "comment_detail");
                        span3.text(msg);
                        span3.appendTo(div);


                        var span4 = $("<span></span>");
                        span4.appendTo(div);

                        var a = $("<a></a>");
                        a.text("删除");
                        a.attr("class", "delete");
                        a.attr("href", "#");
                        a.appendTo(span4);
                        //i++;
                        comment_num++;
                    }
                },"json");
            })

            $(document).on("click", ".delete", function(){
                var conf =  confirm("确认删除" + $(this).parent().parent().children(".friend_name").text() + "对你的印象？");
                if(conf) {
                    //执行删除操作
                    $(this).parent().parent().remove();
                    $.get("deleteImpress", {"fuid":$(this).parent().parent().attr("fuid")}, function () {
                    },"json");
                }
            })


            function moreCom() {
                var fuid = $("#comment_box").attr("fuid");
                $.get( "moreImpress", {"fuid":fuid,"start":comment_num}, function (obj) {
                    var showSize = 20;
                    if(obj.length < 20) {
                        $("#more").css("display", "none");
                        //comment_num += obj.length;
                        showSize = obj.length;
                    } else {
                        $("#more").css("display", "block");
                        //comment_num += 20;
                    }
                    for(var i = 0; i < showSize; i++){
                        var fname = obj[i].uname;
                        var date = obj[i].date;
                        var time = new Date(date);
                        var y = time.getFullYear();
                        var m = time.getMonth()+1;
                        var d = time.getDate();
                        var h = time.getHours();
                        var mm = time.getMinutes();
                        var s = time.getSeconds();
                        var final = y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);

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

                        var a = $("<a></a>");
                        a.text("删除");
                        a.attr("class", "delete");
                        a.attr("href", "#");
                        a.appendTo(span4);

                        comment_num++;
                    }
                },"json");
            }
            $(document).on("click", "#more", moreCom);
            $(document).on("click", "#shuaxing", function(){
                comment_num = 0;
                $("#comments").remove();
                $("#more").remove();
                var comments = $("<div></div>");
                comments.attr("id", "comments");
                comments.appendTo($("#comment_box"));

                var more = $("<a id='more' href='#' style='text-align: center'>显示更多</a>");

                more.appendTo($("#comment_box"))
                moreCom();
            })
        })
    </script>

    <script src="<%=request.getContextPath()%>/static/js/jquery.mousewheel.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/showAndUpdateMsg.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/impress01.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/friendRequest.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/addFriend.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/center-friend.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/center-message.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/right_msg.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/websocket.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/left-friend.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/left-message.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/friendInfo.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/changePwd.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/keyevent.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/changeUserState.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/history.js"></script>


    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/impress02.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/handleRequest.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/updateMsg.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/topadd.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/impress.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/friend.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/right_message.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/msgList.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/info-table.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/history.css">


</head>
<body>
<div id="mains">
    <table id="t1" border="1" style="table-layout: fixed;border-collapse: collapse;">
        <tr>
            <td>
                <div id="left">
                    <div id="left-head" style="width: 50px;height: 100px">
                        <div id="left-head-imgs" style="height: 50px;width: 50px">
                            <img draggable="false"  id="headimg" src="<%=request.getContextPath()%>/${user.imgSrc}" alt="查看基本资料">
                        </div>
                    </div>
                    <div class="left-list">
                        <div  class="left-list-ico"  >
                            <img id="messgae" onclick="leftMsgClick()" class="left-list-ico-img" draggable="false" src="<%=request.getContextPath()%>/static/images/message.jpg" title="消息列表">
                        </div>
                    </div>
                    <div class="left-list">
                        <div  class="left-list-ico"  >
                            <img id="friend" onclick="leftFriendClick()" class="left-list-ico-img"  draggable="false" src="<%=request.getContextPath()%>/static/images/friend.png" title="好友列表">
                        </div>
                    </div>
                    <div class="left-list">
                        <div  class="left-list-ico"  >
                          <img id="handle_request" class="left-list-ico-img"  draggable="false" src="<%=request.getContextPath()%>/static/images/qingqiu.png" title="好友请求">
                        </div>
                    </div>
                    <div class="left-list">
                        <div  class="left-list-ico"  >
                            <img id="changePwd"  class="left-list-ico-img"  draggable="false" src="<%=request.getContextPath()%>/static/images/changepwd.png" title="修改密码">
                        </div>
                    </div>
                </div>
            </td>
            <td>
                <div id="center">
                    <div id="search">
                        <div style="float: left;position: relative;top: 5px;left: 8px;height: 40px;width: 250px;border: 1px solid;border-radius: 20px;" >
                            <img draggable="false" src="<%=request.getContextPath()%>/static/images/search.jpg" class="search">
                        </div>
                        <img draggable="false" src="<%=request.getContextPath()%>/static/images/add.png" id="addFriend" style="position: relative;top: 8px;left: 8px;width: 40px;height: 40px">
                    </div>
                    <div id="center-list"></div>
                </div>
            </td>
            <td id="right_td">
                <div id="right"></div>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    var websocket  ;
    //页面加载完成后执行的方法.
    $(function() {
        // 页面关闭、刷新  onbeforeunload 在即将离开当前页面(刷新或关闭)时执行
        leftFriendClick();
        websocket = connectWebsocket();
        window.onbeforeunload = changeState(0);
        $('body').particleground({
            dotColor: '#E8DFE8',
            lineColor: '#133b88'
        });
    });

</script>
<script>
    function sendmessage(ruid) {
        if(websocket!=null)
        {
            console.log("in");
            //发送消息的按钮事件
            var message = document.getElementById("send-messages");
            console.log(message.value)
            console.log("接收者UID"+ruid);
            <%--<%--%>
            <%-- //设置消息类型--%>
            <%-- session.setAttribute("type",1);--%>
            <%-- %>--%>
            //进行发送数据
            var text = message.value;
            message.value = "" ;
            $("#limit-word").text("0/200")
            console.log(text);
            var now_date = getNowFormatDate() ;
            //传送数据
            var jsons = {
                messages:text,
                suids:<%=session.getAttribute("UID")%>,
                ruids:ruid,
                datas:now_date
            }
            //发送的数据
            console.log(jsons);
            //发送数据到客户端,对接收者进行消息处理
            websocket.send(JSON.stringify(jsons));
            console.log("发送端显示数据");
            //在页面显示发送的内容到当前用户聊天框
            var texts = "" ;
            texts = "<div class='right-message-box right-message-box-body'> <span style='font-family: Arial, Helvetica, sans-serif;'></span>"
            texts += "<div class='right-message-friendimg-div-right'>"
            texts += "<img class='right-message-friendimg-right' src="+$("#headimg").attr("src")+" ></div>"
            texts += "<span class='right-message-box-msg right-message-box-msg-right'>"+text+" <span class='right-message-box-msg-down'></span><span class='right-message-box-msg-top'></span></span></div>"
            $("#right-messagebox").append(texts);
            //滑轮滑倒底部
            document.getElementById("right-messagebox").scrollTop = document.getElementById("right-messagebox").scrollHeight
        }
        else
        {
            console.log("web socket为空")
        }
    }
</script>
</body>
</html>
