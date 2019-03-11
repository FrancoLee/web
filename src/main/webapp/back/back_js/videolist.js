// /**
//  * Created by root on 17-5-15.
//  */
var tab = $('.table-sort').dataTable({
    "aaSorting": [[1, "desc"]],//默认第几个排序
    // "bStateSave": true,//状态保存
    "aoColumnDefs": [
        //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
        {"orderable": false, "aTargets": [0, 6]}// 不参与排序的列
    ],
}).api();

function change(obj) {
    $.ajax({
        type: 'POST',
        url: '/back/videoList/select_class',
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
                        ' <td class="text-c"><u style="cursor:pointer" class="text-primary"' +
                        'onClick="video_edit(\'视频列表\',\'/back/video/show?listId=' + data[i].listId + '\',' + data[i].listId + ',this)" title="查看">' + data[i].title;
                    if (data[i].countNew != 0)
                        str += ' <span><img style="margin-top: -22px;" src="/back/1-140104202130.gif"></span>';
                    str += '</u></td>' +
                        '<td><img src="' + data[i].picUrl + '" style="width: 80px;height: 50px;"></td>' +
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
                        '<td class="f-14 td-manage"><a style="text-decoration:none" onClick="videolist_shenhe(this,\'' + data[i].listId + '\')" href="javascript:;" title="审核">审核</a> ' +
                        '<a style="text-decoration:none" class="ml-5" onClick="videolist_del(this,\'' + data[i].listId + '\')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td> ' +
                        '</tr>';
                    $('#info').append(str);
                }


                tab = $('.table-sort').dataTable({
                    "aaSorting": [[1, "desc"]],//默认第几个排序
                    "bStateSave": true,//状态保存
                    "aoColumnDefs": [
                        //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
                        {"orderable": false, "aTargets": [0, 6]}// 不参与排序的列
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

/*资讯-添加*/
function article_add(title, url, w, h) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*资讯-编辑*/
function video_edit(title, url, id, obj) {
    var width=$(window).width()*0.8;
    var height=$(window).height()*0.8;
    var index = layer.open({
        type: 2,
        title: title,
        content: url,
        area: [width+'px', height+'px'],
        success: function (layero, index) {
            $(document).on('keydown', function (e) {
                if (e.keyCode == 27) {
                    layer.close(index);
                }
            });
        },
        end: function () {
            $.ajax({
                type: 'POST',
                url: '/back/video/queryNew?listId=' + id,
                dataType: 'text',
                success: function (data) {
                    if (data == "2") {
                        $(obj).children("span").html('<img style="margin-top: -22px;" src="/back/1-140104202130.gif">');
                    } else if (data == "1") {
                        $(obj).children("span").html("");
                    }
                },
                error: function (data) {
                    console.log(data.msg);
                },
            });
        }
    });
    //layer.full(index);
}
/*资讯-删除*/
function videolist_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/back/videoList/deleteVideoList?listId=' + id,
            dataType: 'text',
            success: function (data) {
                if (data == "1") {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 6, time: 1000});
                }
                else if (data == "2") {
                    layer.msg('该列表下还有视频，请先删除该列表下所有视频!', {icon: 5, time: 2000});
                } else if (data == "0") {
                    layer.msg('删除出现错误，请重试!', {icon: 5, time: 1000});
                }

            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}

/*资讯-审核*/
function videolist_shenhe(obj, id, index) {
    layer.confirm('审核文章？', {
            btn: ['通过', '不通过', '取消'],
            shade: false,
            closeBtn: 0
        },
        function () {
            $.ajax({
                type: 'POST',
                url: '/back/videoList/updateShow?show=1&&listId=' + id,
                dataType: 'text',
                success: function (data) {
                    if (data == '1') {
                        $(obj).parents("tr").find(".td-manage").prepend('<a  style="text-decoration:none" onClick="videolist_shenhe(this,' + id + ',' + index + ')" href="javascript:;" title="审核">审核</a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已通过</span>');
                        $(obj).remove();
                        // str=str.replace('<span class="label label-default radius">未通过</span>','<span class="label label-success radius">已通过</span>');
                        //  tables.row(index-1).data(str.toArray());
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
            //  $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
            // $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');;
        },
        function () {
            $.ajax({
                type: 'POST',
                url: '/back/videoList/updateShow?show=2&&listId=' + id,
                dataType: 'text',
                success: function (data) {
                    if (data == '1') {
                        $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="videolist_shenhe(this,' + id + ')" href="javascript:;" title="申请">审核</a>');
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
/*资讯-下架*/
function article_stop(obj, id) {
    layer.confirm('确认要下架吗？', function (index) {
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
        $(obj).remove();
        layer.msg('已下架!', {icon: 5, time: 1000});
    });
}

/*资讯-发布*/
function article_start(obj, id) {
    layer.confirm('确认要发布吗？', function (index) {
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
        $(obj).remove();
        layer.msg('已发布!', {icon: 6, time: 1000});
    });
}
/*资讯-申请上线*/
function article_shenqing(obj, id) {
    $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
    $(obj).parents("tr").find(".td-manage").html("");
    layer.msg('已提交申请，耐心等待审核!', {icon: 1, time: 2000});
}