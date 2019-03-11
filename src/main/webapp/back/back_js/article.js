var tab = $('.table-sort').dataTable({
    "aaSorting": [[1, "desc"]],//默认第几个排序
    "bStateSave": true,//状态保存
    "aoColumnDefs": [
        //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
        {"orderable": false, "aTargets": [0, 8]}// 不参与排序的列
    ]
}).api();

function change(obj) {
    $.ajax({
        type: 'POST',
        url: '/back/art/select_class',
        data: {"class": ($(obj).find("option:selected")).val()},
        dataType: 'json',
        success: function (data) {
            if (data != null) {
                tab.destroy();
                $('#info').html('');
                var str;
                switch (data[0]) {
                    case 0:
                        str = "未审核";
                        break;
                    case 1:
                        str = "已通过";
                        break;
                    case 2:
                        str = "未通过";
                        break;
                    case 3:
                        str = "全部分类";
                        break;
                }

                $('#class').html(str);
                for (var i = 1; i < data.length; i++) {
                    var str = '<tr class="text-c">' +
                        '<td class="my-select"><input type="checkbox" value="' + data[i].artId + '" name="check"></td> ' +
                        '<td >' + data[i].artId + '</td> ' +
                        '<td class="text-l"><a href="/article/show/artId=' + data[i].artId + '" >' + data[i].title + '</a></td> ' +
                        '<td style="display:none"></td> ' +
                        '<td>' + data[i].user + '</td> ' +
                        '<td>' + data[i].time + '</td> ' +
                        '<td>' + data[i].click + '</td> ' +
                        '<td class="td-status"> ';
                    if (data[i].show == 1) {
                        str += '<span class="label label-success radius">已通过';
                    } else if (data[i].show == 0) {
                        str += '<span class="label label-danger radius">未审核';
                    }
                    else if (data[i].show == 2) {
                        str += '<span class="label label-danger radius">未通过';
                    }
                    str += '</span></td>' +
                        '<td class="f-14 td-manage"><a style="text-decoration:none" onClick="article_shenhe(this,\'' + data[i].artId + '\')" href="javascript:;" title="审核">审核</a> ' +
                        '<a style="text-decoration:none" class="ml-5" onClick="article_del(this,\'' + data[i].artId + '\')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td> ' +
                        '</tr>';
                    $('#info').append(str);
                }


                tab = $('.table-sort').dataTable({
                    "aaSorting": [[1, "desc"]],//默认第几个排序
                    "bStateSave": true,//状态保存
                    "aoColumnDefs": [
                        //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
                        {"orderable": false, "aTargets": [0, 8]}// 不参与排序的列
                    ]
                }).api();
            } else if (data == null) {
                //    layer.msg('删除失败', {icon: 6, time: 1000});
                alert("鬼知道发生了什么错误！");
            }
        },
        error: function (data) {
            console.log(data.msg);
        },
    });
}
// /*资讯-添加*/
// function article_add(title,url,w,h){
//     var index = layer.open({
//         type: 2,
//         title: title,
//         content: url
//     });
//     layer.full(index);
// }
// /*资讯-编辑*/
// function article_edit(title,url,id,w,h){
//     var index = layer.open({
//         type: 2,
//         title: title,
//         content: url
//     });
//     layer.full(index);
// }
/*批量删除*/
function datadel() {
    var str = "";
    var check = false;
    $('[name="check"]:checked').each(function () {
        str += $(this).val() + ',';
        check = true;
    });
    if (check) {
        $.ajax({
            type: 'POST',
            url: '/back/art/delete_batch',
            dataType: 'text',
            data: {"check": str},
            success: function (data) {
                if (data == '1') {
                    $('[name="check"]:checked').each(function () {
                        $(this).parents("tr").find(".td-status").html('<span class="label label-danger radius">已删除</span>');
                        $(this).parents("tr").find(".td-manage").html('');
                        $(this).parents("tr").find(".my-select").empty();
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
/*资讯-删除*/
function article_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/back/art/delete?artId=' + id,
            dataType: 'text',
            success: function (data) {
                if (data == '1') {
                    $(obj).parents("tr").find(".my-select").empty();
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">已删除</span>');
                    $(obj).parents("tr").find(".td-manage").html('');

                    layer.msg('删除成功', {icon: 6, time: 1000});
                    //    window.location.reload();
                } else if (data == '0') {
                    layer.msg('删除失败', {icon: 5, time: 1000});
                    alert("删除失败！");
                }
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}

/*资讯-审核*/
function article_shenhe(obj, id) {
    layer.confirm('审核文章？', {
            btn: ['通过', '不通过', '取消'],
            shade: false,
            closeBtn: 0
        },
        function () {
            $.ajax({
                type: 'POST',
                url: '/back/art/check?check=1&&artId=' + id,
                dataType: 'text',
                success: function (data) {
                    if (data == '1') {
                        $(obj).parents("tr").find(".td-manage").prepend('<a  style="text-decoration:none" onClick="article_shenhe(this,' + id + ')" href="javascript:;" title="审核">审核</a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已通过</span>');
                        $(obj).remove();
                        layer.msg('已通过', {icon: 6, time: 1000});
                    }
                    else if (data == '0') {
                        alert("审核出错!");
                    }
                },
                error: function (data) {
                    console.log(data.msg);
                },
            })

        },
        function () {
            $.ajax({
                type: 'POST',
                url: '/back/art/check?check=2&&artId=' + id,
                dataType: 'text',
                success: function (data) {
                    if (data == '1') {
                        $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_shenhe(this,' + id + ')" href="javascript:;" title="申请">审核</a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">未通过</span>');
                        $(obj).remove();
                        layer.msg('未通过', {icon: 6, time: 1000});
                    }
                    else if (data == '0') {
                        alert("审核出错!");
                    }
                },
                error: function (data) {
                    console.log(data.msg);
                },
            })
        });
}
// /*资讯-下架*/
// function article_stop(obj,id){
//     layer.confirm('确认要下架吗？',function(index){
//         $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
//         $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
//         $(obj).remove();
//         layer.msg('已下架!',{icon: 5,time:1000});
//     });
// }
//
// /*资讯-发布*/
// function article_start(obj,id){
//     layer.confirm('确认要发布吗？',function(index){
//         $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
//         $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
//         $(obj).remove();
//         layer.msg('已发布!',{icon: 6,time:1000});
//     });
// }
// /*资讯-申请上线*/
// function article_shenqing(obj,id){
//     $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
//     $(obj).parents("tr").find(".td-manage").html("");
//     layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
// }