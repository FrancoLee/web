/**
 * Created by root on 17-5-14.
 */
var tab;
$(function () {
    tab = $('.table-sort').dataTable({
        "aaSorting": [[1, "desc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable": false, "aTargets": [0, 8, 9]}// 制定列不参与排序
        ]
    }).api();
    $('.table-sort tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
});

function retable(data) {
    tab.destroy();
    var str;
    switch (data[0]) {
        case 0:
            str = "已启用";
            break;
        case 1:
            str = "已停用";
            break;
        case 2:
            str = "全部分类";
            break;
    }
    $("#class").html(str);
    // $("#count").html(data[1]);
    var info = $('#info');
    info.html("");
    str="";
    for (var i = 1; i < data.length; i++) {
        var str = '<tr class="text-c">' +
            '<td style="display: none"><input type="checkbox" value="' + data[i].user + '" name="">1</td>' +
            //     <td style="display: none"><input type="checkbox" value="${user.username}" name=""></td>
            '<td style="display: none">1</td>' +
            '<td>' + data[i].user + '</td>' +
            //     <%--<u style="cursor:pointer" class="text-primary" onclick="member_show('张三','member-show.html','10001','360','400')">张三</u>--%>
            '<td>' + data[i].name + '</td>' +
            '<td><img src="' + data[i].picUrl + '" style="width: 50px;height: 50px;" alt=""></td>' +
            '<td>' + data[i].hobby + '</td>' +
            ' <td class="text-l">' + data[i].info + '</td>' +
            '<td >' + data[i].regTime + '</td>';
        if (data[i].state == 'regular') {
            str += '<td class="td-status"><span class="label label-success radius">已启用</span>' +
                '<td class="td-manage"><a style="text-decoration:none" onClick="member_stop(this,\'' + data[i].user + '\')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a></td></tr>';
        } else if (data[i].state =='disable') {
            str += '<td class="td-status"><span class="label label-default radius">已停用</span>' +
                '<td class="td-manage"><a style="text-decoration:none" onClick="member_start(this,\'' + data[i].user + '\')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe631;</i></a></td></tr>';
        }
    }
    info.append(str);
    tab = $('.table-sort').dataTable({
        "aaSorting": [[1, "desc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable": false, "aTargets": [0, 8, 9]}// 制定列不参与排序
        ]
    }).api();
    $('.table-sort tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
}


function change(obj) {
    $.ajax({
        type: 'POST',
        url: '/back/users/selectClass',
        data: {"class": ($(obj).find("option:selected")).val()},
        dataType: 'json',
        success: function (data) {
            //alert(1);
            // alert(data);
            if (data != null) {
                retable(data);
            } else if (data == null) {
                alert("11");
                alert("鬼知道发生了什么错误！");
            }
        },
        error: function (data) {
            console.log(data.msg);
        },
    });
}

/*用户-添加*/
// function member_add(title,url,w,h){
//     layer_show(title,url,w,h);
// }
// /*用户-查看*/
// function member_show(title,url,id,w,h){
//     layer_show(title,url,w,h);
// }
/*用户-停用*/
function member_stop(obj, user) {
    layer.confirm('确认要停用吗？', function (index) {
        $.ajax({
            type: 'post',
            url: '/back/users/state',
            dataType: 'text',
            data: {'state': 'disable', 'user': user},
            success: function (data) {
                if (data == '1') {
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
                    $(obj).remove();
                    //  retable(data);
                    //  layer.msg('删除成功', {icon: 5, time: 1000});
                    //  location.replace(location.href);
                    layer.msg('已停用!', {icon: 5, time: 1000});
                    //  window.location.href = "/back_users?action=index";
                } else if (data == '0') {
                    //    layer.msg('删除失败', {icon: 6, time: 1000});
                    alert("鬼知道发生了什么错误！");
                }
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}

/*用户-启用*/
function member_start(obj, user) {
    layer.confirm('确认要启用吗？', function (index) {
        $.ajax({
            type: 'post',
            url: '/back/users/state',
            dataType: 'text',
            data: {'state': 'regular', 'user': user},
            success: function (data) {
                if (data == '1') {
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,' + user + ')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                    $(obj).remove();
                    layer.msg('已启用!', {icon: 6, time: 1000});
                    //  layer.msg('删除成功', {icon: 5, time: 1000});
                    //  location.replace(location.href);
                    // window.location.href = "/back_users?action=index";
                } else if (data == '0') {
                    //    layer.msg('删除失败', {icon: 6, time: 1000});
                    alert("鬼知道发生了什么错误！");
                }
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}

//
// /*用户-编辑*/
// function member_edit(title,url,id,w,h){
//     layer_show(title,url,w,h);
// }
// /*密码-修改*/
// function change_password(title,url,id,w,h){
//     layer_show(title,url,w,h);
// }
// /*用户-删除*/
// function member_del(obj,id){
//     layer.confirm('确认要删除吗？',function(index){
//         $(obj).parents("tr").remove();
//         layer.msg('已删除!',{icon:1,time:1000});
//     });
// }