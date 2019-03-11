
var t=false;
$(function () {
   // alert(vid_id);
    $('#video_sub').click(function () {
        if($('#title').val().length<=0){
            $('#mes').html("请输入标题！");
        }
        else if($('#epi').val().length<=0){
            $('#mes').html("请输入第几话！");
        }
        else if(!t){
            $('#mes').html("请上传视频！");
        }
        else {
            $('#video_from').submit();
        }
    });
    //register_modal
    $('#show_add').on('hide.bs.modal',function(){

        // alert("ss12");
        $(':input','#show_add')
            .not(':button')
            .not('#vid')
            .val("");
        $('span','#show_add').html("");
    });

    // function onlyNumber(event){
    //     alert(1);
    //     var keyCode = event.keyCode;
    //     if(keyCode<48 || keyCode>57){
    //         event.keyCode = 0;
    //     }
    // }
    // $("#epi").keydown(onlyNumber);
    $('#btn_add').click(function () {
        //$('#add_video').css("display","none");
        $('#show_add').attr("style","");
        var $list=$("#thelist");   //这几个初始化全局的百度文档上没说明，好蛋疼。
        var $btn =$("#ctlBtn");   //开始上传
        var thumbnailWidth = 100;   //缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档
        var thumbnailHeight = 100;

        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: false,

            // swf文件路径
            swf: '/js/Uploader.swf',

            // 文件接收服务端。
            server: '/video/video_upload?vid='+vid_id,

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: {
            id : '#filePicker',
            multiple: false
            },
            fileNumLimit: 1,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'mp4,avi,flv',
                // mimeTypes: 'image/*'
            },
            method:'POST',
        });
        // 当有文件添加进来的时候

        uploader.on( 'fileQueued', function( file ) {  // webuploader事件.当选择文件后，文件被加载到文件队列中，触发该事件。等效于 uploader.onFileueued = function(file){...} ，类似js的事件定义。
            var $li = $(

                    '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">'+ file.name + '</div>' +
                        '<div id="remove">'+
                    '<button type="button" id="we">取消</button> '+
                        '</div>'+
                    '</div>'
                  //  '<button type='+'"button"'+'class='+'"btn btn-default"'+' id='+'"clear"'+'>取消</button>'
                ),
                $img = $li.find('img');
            // $list为容器jQuery实例
            $list.append( $li );
            $('#we').click(function () {
                $('#thelist').html("");
                uploader.reset();
            });

            // 创建缩略图
            // 如果为非图片文件，可以不用调用此方法。
            // thumbnailWidth x thumbnailHeight 为 100 x 100
            // uploader.makeThumb( file, function( error, src ) {   //webuploader方法
            //     if ( error ) {
            //         $img.replaceWith('<span>不能预览</span>');
            //         return;
            //     }
            //
            //     $img.attr( 'src', src );
            // }, thumbnailWidth, thumbnailHeight );
        });
        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                $percent = $li.find('.progress span');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
            }

            $percent.css( 'width', percentage * 100 + '%' );
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader.on( 'uploadSuccess', function( file ,json) {
            //alert(json.vid);
            if(json.vid<0) {
                $('#up_info').html("<b>上传失败</b>");
                return;
            }
            $('#vid').val(json.vid);
            t=true;
            $('#remove').html("");
            $('#up_info').html("<b>上传成功</b>");
            $( '#'+file.id ).addClass('upload-state-done');
        });

        // 文件上传失败，显示上传出错。
        uploader.on( 'uploadError', function( file ) {
           // alert("ss");
            var $li = $( '#'+file.id ),
                $error = $li.find('div.error');

            // 避免重复创建
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $li );
            }

            $error.text('上传失败');
        });
        // 完成上传完了，成功或者失败，先删除进度条。
        uploader.on( 'uploadComplete', function( file ) {
            //alert(file.filepath);
            $( '#'+file.id ).find('.progress').remove();
        });
        $btn.on( 'click', function() {
            console.log("上传...");
            uploader.upload();
            console.log("上传成功");
        });
    });
});

