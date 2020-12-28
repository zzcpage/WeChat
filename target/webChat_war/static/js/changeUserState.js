function changeState(state)
{
    console.log("修改用户状态:"+state)
    var json = {states:state}
    $.ajax({
        url:"userlogin",
        data:JSON.stringify(json),
        contentType:"application/json;charset=UTF-8",
        method:"post",
        dataType:"json",
        success:function (events){
            console.log("用户登录成功") ;
        }

    });
}