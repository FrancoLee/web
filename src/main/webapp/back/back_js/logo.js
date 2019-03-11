
function datadel() {
    var str = "";
    var check = false;
    $('[name="check"]:checked').each(function () {
        str += $(this).val() + ',';
        check = true;
    });
    //alert(str);
    if (check) {
        $.ajax({
            type: 'POST',
            url: 'back_adv?action=del_batch',
            dataType: 'text',
            data: {"id_list": str},
            success: function (data) {
                if (data == '1') {
                    $('[name="check"]:checked').each(function () {
                        // $(this).parents("tr").find(".td-status").html('');
                        $(this).parents("tr").find(".td-manage").html('<span class="label label-danger radius">已删除</span>');
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
function change(obj) {
    var text=$(obj).val();
    $(obj).parents('tr').find('.info').html("<input class='input-text' id='info_text' type='text' onblur='info_ok(this)'  value='"+text+"'>");
    $('#info_text').focus();
}
function info_ok(obj) {
    var text=$(obj).val();
    if(text.length==0)
        text='暂无';
    $.ajax({
        type: 'POST',
        url: '/back/adv/updateLogoInfo',
        dataType: 'text',
        data: {"info": text},
        success: function (data) {
            if (data == '1') {
                $(obj).parents('tr').find('.info').html("<span onClick='change(this)'>"+text+" </span>");
                layer.msg('修改成功', {icon: 6, time: 1000});
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
/*图片-添加*/
function pic_up(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url,
        area: ['800px', '800px']
    });
    //layer.full(index);
}
/*图片-查看*/
function picture_show(title,url,id){javascript:location.replace(location.href);
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*图片-审核*/
function picture_shenhe(obj,id){
    layer.confirm('审核文章？', {
            btn: ['通过','不通过'],
            shade: false
        },
        function(){
            $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="picture_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
            $(obj).remove();
            layer.msg('已发布', {icon:6,time:1000});
        },
        function(){
            $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="picture_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
            $(obj).remove();
            layer.msg('未通过', {icon:5,time:1000});
        });
}
/*图片-下架*/
function picture_stop(obj,id){
    layer.confirm('确认要下架吗？',function(index){
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="picture_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
        $(obj).remove();
        layer.msg('已下架!',{icon: 5,time:1000});
    });
}

/*图片-发布*/
function picture_start(obj,id){
    layer.confirm('确认要发布吗？',function(index){
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="picture_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
        $(obj).remove();
        layer.msg('已发布!',{icon: 6,time:1000});
    });
}
/*图片-申请上线*/
function picture_shenqing(obj,id){
    $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
    $(obj).parents("tr").find(".td-manage").html("");
    layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}
/*图片-编辑*/
function picture_edit(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*图片-删除*/
function picture_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        $.ajax({
            type: 'POST',
            url: 'back_adv?action=del_car',
            dataType: 'text',
            data: {"id": id},
            success: function (data) {
                if (data == '1') {
                    $(obj).parents("tr").find(".td-manage").html('<span class="label label-danger radius">已删除</span>');
                    layer.msg('已删除!',{icon:1,time:1000});
                    //location.replace(location.href);
                } else if (data == '0') {
                    layer.msg('删除失败', {icon: 6, time: 1000});
                    alert("删除失败！");
                }
            },
            error: function (data) {
                console.log(data.msg);
            },
        });


    });
}
/**
 * Created by root on 17-5-15.
 */

