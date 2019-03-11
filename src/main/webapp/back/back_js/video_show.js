
$('.table-sort').dataTable({
    "aaSorting": [[ 1, "desc" ]],//默认第几个排序
    "bStateSave": true,//状态保存
    "aoColumnDefs": [
        //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
        {"orderable":false,"aTargets":[0,6]}// 不参与排序的列
    ]
});

function datadel() {
    var str="";
    var check=false;
    $('[name="check"]:checked').each(function () {
        str += $(this).val() + ',';
        check = true;
    });
    if(check) {
        $.ajax({
            type: 'POST',
            url: '/back/video/batchDelete',
            dataType: 'text',
            data: {"check": str},
            success: function (data) {
                if (data == '1') {
                    $('[name="check"]:checked').each(function () {
                        $(this).parents("tr").find(".td-status").html('<span class="label label-danger radius">已删除</span>');
                        $(this).parents("tr").find(".td-manage").html('');
                    });
                    layer.msg('删除成功', {icon: 6, time: 1000});
                    //location.replace(location.href);
                } else if (data == '0') {
                    layer.msg('删除失败', {icon: 5, time: 1000});
                    alert("删除失败！");
                }
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    }
}
function play_video(url) {
   var index= layer.open({
        type: 1 //Page层类型
        ,title: '看小电影咯'
        ,shade: 0.6 //遮罩透明度
        ,maxmin: true //允许全屏最小化
        ,content: '<video controls="" preload="none" width="100%" height="600px"   src="'+url+'"></video><br>'
    });
    layer.full(index);
}

function video_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        $.ajax({
            type: 'POST',
            url: '/back/video/delete?vid='+id,
            dataType: 'text',
            success: function(data){
                if(data=='1') {
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">已删除</span>');
                    $(obj).parents("tr").find(".td-manage").html('');
                    layer.msg('删除成功', {icon: 6, time: 1000});
                    //    window.location.reload();
                }else if(data=='0'){
                    layer.msg('删除失败', {icon: 5, time: 1000});
                    alert("删除失败！");
                }
            },
            error:function(data) {
                console.log(data.msg);
            },
        });
    });
}
function video_shenhe(obj,id) {
    layer.confirm('审核文章？', {
            btn: ['通过','不通过','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            $.ajax({
                type: 'POST',
                url: '/back/video/updateShow?show=1&&vid='+id,
                dataType: 'text',
                success: function(data){
                    if(data=='1') {
                        $(obj).parents("tr").find(".td-manage").prepend('<a  style="text-decoration:none" onClick="video_shenhe(this,'+id+')" href="javascript:;" title="审核">审核</a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已通过</span>');
                        $(obj).remove();
                        layer.msg('已通过', {icon: 6, time: 1000});
                    }
                    else if(data=='0'){
                        alert("审核出错!");
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                },
            })
            //  $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
            // $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');;
        },
        function(){
            $.ajax({
                type: 'POST',
                url: '/back/video/updateShow?show=2&&vid='+id,
                dataType: 'text',
                success: function(data){
                    if(data=='1') {
                        $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="video_shenhe(this,'+id+')" href="javascript:;" title="申请">审核</a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">未通过</span>');
                        $(obj).remove();
                        layer.msg('未通过', {icon:6,time:1000});
                    }
                    else if(data=='0'){
                        alert("审核出错!");
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                },
            })
        });
}