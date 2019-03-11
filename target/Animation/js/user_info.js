var jcrop_api;
var filet = false;
$(function ($) {

//滚轮事件监听，用于图片定点放大，有时间再做
// var scrollFunc=function(){
//   alert("ss");
// }
// if(document.attachEvent){
//      document.attachEvent('onmousewheel',scrollFunc);
// }

//   if(document.addEventListener){
//     document.addEventListener('DOMMouseScroll',scrollFunc,false);
//     }//W3C
//     window.onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome
    $('#change_userinfo').click(function () {
        //alert(1);
        $('#form_update').css('display', '');
        $('#show_info').css('display', 'none');
    });
    $('#show_userinfo').click(function () {
        $('#form_update').css('display', 'none');
        $('#show_info').css('display', '');
    });
  //  jcrop_api.destroy();

  //  $('#content').html('<img src="ssdqw" id="imq"   style="width: 600px;height: 600px;"/> <div id="preview-pane"><div class="preview-container"> <img src="' + picUrl + '" class="jcrop-preview "  id="imx" />   </div></div>');
    res();
    $('#imq').remove();
    $('#pic_updata').click(function () {
        if (filet) {
            $('#pic_form').submit();
        }
        else {
            $('#pic_manage').html("图片格式错误，目前只支持jpg/jpeg,png,且大小不得超过2M!");
        }
    });
});
function res(img) {
    //图片裁切js
    reset();
    var
        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        xsize = $pcnt.width(),
        ysize = $pcnt.height();
    //   alert (xsize);
    console.log('init', [xsize, ysize]);

    $('#imq').Jcrop({
        boxWidth: 600,
        boxHeigh: 600,
        minSelect: [100, 100],
        onRelease: reset,
        // trackDocument:false,
        addClass: 'text',
        onChange: updatePreview,
        onSelect: updatePreview,
        aspectRatio: xsize / ysize
    }, function () {
        // Use the API to get the real image size

        jcrop_api = this;
        var bounds = this.getBounds();
        boundx = bounds[0];
        boundy = bounds[1];
        // alert("ss");
        // Move the preview into the jcrop container for css positioning
        $preview.appendTo(jcrop_api.ui.holder);
        $('.jcrop-keymgr').attr("type", "hidden");
        //   $('.jcrop-holder text').css("width","400px").css("height","400px");
    });

    function updatePreview(c) {
        if (parseInt(c.w) > 0) {
            var rx = xsize / c.w;
            var ry = ysize / c.h;
            $pimg.css({
                width: Math.round(rx * boundx) + 'px',
                height: Math.round(ry * boundy) + 'px',
                marginLeft: '-' + Math.round(rx * c.x) + 'px',
                marginTop: '-' + Math.round(ry * c.y) + 'px',
            });
            $('#cw').val(c.w);
            $('#ch').val(c.h);
            $('#rx').val(c.x);
            $('#ry').val(c.y);
            $('#bx').val(boundx);
            $('#by').val(boundy);
        }
    };
    //

}
//
function reset() {
    $('#cw').val(0);
    $('#ch').val(0);
    $('#rx').val(0);
    $('#ry').val(0);
}
function img_pre() {
    //alert("s");
    var file = document.getElementById('image').files[0];
    reader = new FileReader();
    reader.onload = function (evt) {
        //  var img = document.getElementById('imq');

        if ((file.type != 'image/jpeg' && file.type != 'image/png' ) || file.size > 2 * 1024 * 1024) {

            $('#pic_manage').html("图片格式错误，目前只支持jpg/jpeg,png,且大小不得超过2M!");
            filet = false;
            return;
        }
        // var imx = document.getElementById('imx');
        jcrop_api.destroy();
        $('#content').html('<img src="' + evt.target.result + '" id="imq"   style="width: 600px;height: 600px;"/> <div id="preview-pane"><div class="preview-container"> <img src="' + evt.target.result + '" class="jcrop-preview "  id="imx" />   </div></div>');

        // // img.src=evt.target.result;
        // imx.src=evt.target.result;
        //  $('#imx').attr('src',evt.target.result);//.css("width","226px").css("height","227px");
        // jcrop_api.setImage(evt.target.result);
        // jcrop_api.boxHeigh=600;
        // jcrop_api.boxHeigh=600;
        // jcrop_api.setImage(;
        // $('.text').css("height",100);
        // $('.text').css("width",100);
        res();
        filet = true;

        //  $('.jcrop-tracker').css("height","200px").css("width","200px");

        //src.src=evt.target.result;
        // alert(evt.target.result);
    }
    reader.readAsDataURL(file);
}
