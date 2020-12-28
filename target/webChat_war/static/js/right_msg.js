//获取当前的时间
function getNowFormatDate() {
    var myDate = new Date();
    return myDate.getTime();
}
//获取聊天列表

//获取消息盒子
function getMessageBox()
{
    return
}
function  showWords()
{
    var len = $("#send-messages").val().length
    $("#limit-word").text(len+"/200")
}