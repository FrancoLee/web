/**
 * Created by root on 17-5-14.
 */
$('.table-sort').dataTable({
    "aaSorting": [[ 1, "desc" ]],//默认第几个排序
    "bStateSave": true,//状态保存
    "aoColumnDefs": [
        //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
        {"orderable":false,"aTargets":[0,7]}// 不参与排序的列
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
            url: '/back/art/deleteBatch_complete',
            data: {"check": str},
            dataType: 'text',
            success: function (data) {
                if (data == '1') {
                    $('[name="check"]:checked').each(function () {
                        $(this).parents("tr").find(".td-manage").html('');
                        $(this).parents("tr").find(".td-status").html('<span class="label label-danger radius">已删除</span>');
                        $(this).parents("tr").find(".my-select").empty();
                    });
                    layer.msg('已删除', {icon: 6, time: 1000});
                   // location.replace(location.href);
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
/*资讯-删除*/
function article_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        $.ajax({
            type: 'POST',
            url: '/back/art/delete_complete?artId='+id,
            dataType: 'text',
            success: function(data){
                if(data=='1') {
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">已删除</span>');
                    $(obj).parents("tr").find(".td-manage").html('');
                    $(obj).parents("tr").find(".my-select").empty();
                    layer.msg('已删除', {icon: 6, time: 1000});
                    //window.location.reload();
                }else if(data=='0'){
                    layer.msg('删除失败', {icon: 5, time: 1000});
                }
            },
            error:function(data) {
                console.log(data.msg);
            },
        });
    });
}

/*资讯-审核*/
function article_rev(obj,id){
    layer.confirm('还原文章？', {
            btn: ['还原','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            $.ajax({
                type: 'POST',
                url: '/back/art/reduction?artId='+id,
                dataType: 'text',
                success: function(data){
                    if(data=='1') {
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已还原</span>');
                        $(obj).parents("tr").find(".td-manage").html('');
                        $(obj).parents("tr").find(".my-select").empty();
                        layer.msg('还原成功', {icon: 6, time: 1000});
                      //  window.location.reload();
                    }
                    else if(data=='0'){
                        layer.msg('还原失败', {icon: 5, time: 1000});
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                },
            })

        }
        );
}