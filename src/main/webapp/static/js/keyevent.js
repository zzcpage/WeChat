$(function () {
    //处理按键 ESC消除弹框
    $(document).keydown(function (event) {
        switch (event.keyCode) {
            case 27: $("#background_div").remove();
                    break;
        }
    })
})