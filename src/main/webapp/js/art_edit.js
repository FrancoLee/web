
var t=false;
function pic_change() {
    var file=document.getElementById('video_pic').files[0];
    if ((file.type != 'image/jpeg' && file.type != 'image/png' ) || file.size > 2 * 1024 * 1024) {
        $('#video_mes').html("图片格式错误，目前只支持jpg/jpeg,png,且大小不得超过2M!<br>");
        $('#video_pic').val("");
        t=false;
    }
    else {
        t=true;
    }
}
$(function () {
    // UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    // UE.Editor.prototype.getActionUrl = function(action) {
    //     if (action == '/demo/add_art' || action == '/demo/add_art' || action == '/demo/add_art') {
    //         return 'http://localhost/demo/add_art';
    //     } else if (action == 'uploadvideo') {
    //         return 'http://a.b.com/video.php';
    //     } else {
    //         return this._bkGetActionUrl.call(this, action);
    //     }
    // }
    var ue = UE.getEditor('editor');
    $('#show_video').click(function () {
        $('#art').css('display','none');
        $('#video').css('display','');
        $('#foot').css('margin-top','450px');
    });
    $('#show_creart').click(function () {
        $('#art').css('display','');
        $('#video').css('display','none');
        $('#foot').css('margin-top','0px');
    });

    $('#art_submit').click(function () {
        if($('#title').val()<=0){
            $('#art_mes').html("请输入标题!<br>");
        }
        else if(!ue.hasContents()){
            $('#art_mes').html("请输入内容！<br>");
        }
        else {
            $('#art_from').submit();
        }
    });
    $('#video_submit').click(function () {
       // alert("1");
        if($('#video_title').val()<=0){
            $('#video_mes').html("请输入标题！<br>");
        }
        else if(t){
            $('#video_from').submit();
        }
        else {
            $('#video_mes').html("请选择图片！<br>");
        }
    });

})
