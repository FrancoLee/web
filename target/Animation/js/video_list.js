function delete_video(index, listId) {
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/videoList/delete",
        data: {'listId': listId, 'page': index},
        success: function (data) {
            var videos = data.videos;
            var str='';
            var pages=data.page;
            // alert(data.counts);
            if(data.counts==0){
                if(index>1){
                    var s="/videoList/user_video?page="+(index-1);
                    window.location.href=s;
                }
                else {
                    alert("所有文章都已经被删除");
                    $('#page').html('');
                    $('#info').html('');
                    return;
                }
            }
            if(data.counts<4){
                $('#foot').css("position","absolute");
                $('#foot').css("bottom","0px");
                $('#foot').css("width","100%");
                //style="position: absolute;bottom: 0px;width: 100%"
            }
            else {
                $("#foot").removeAttr("style");
            }
            for (var i = 0;i < videos.length;i++) {
                str+= '<div class="row info-content">' +
                    '<div class="col-md-2 col-sm-2 col-xs-2" style="margin: 2px ; padding: 0">' +
                    '<img src="' + videos[i].picUrl + '" class="img-responsive" alt=""' +
                    'href="/video?action=show_video&&listId=' + videos[i].listId + '"' +
                    'style="width: 100px;height: 100px;margin-left: 20px;"></div>' +
                    ' <div class="col-md-4 col-sm-4 col-xs-4" style="">' +
                    '<h4><a href="/videoList/show_list?listId=${video.listId}">' + videos[i].title + '</a></h4>' +
                    '<p>作者：' + videos[i].user + '</p>' +
                    '<p>发表时间:' + videos[i].time + '</p>' +
                    '<button class="btn" onclick="delete_video(' + pages.index + ',' + videos[i].listId + ')" value="删除">删除</button>'+
                '    <b style="color:#4b72a4 "> 状态: ';
                if(videos[i].show==0)
                    str+='未审核';
                else if(videos[i].show==2)
                    str+='审核不通过';
                else if(videos[i].show==1)
                    str+='审核通过';
                str+='  </b> </div> </div>';
            }
            var str2='';
            str2+='<ul class="pagination">';
                if(pages.index!=1) {
                    str2 += '<li><a href="/videoList/user_video?page='+(pages.index-1)+'">上一页</a></li>' +
                        '<li><a href="/videoList/user_video?page=1">首页</a></li>';
                }
                for(var i=pages.pageBeg;i<=pages.pageEnd;i++)
                    str2+='<li><a href="/videoList/user_video?page='+i+'">'+i+'</a></li>';
                if(pages.index!=pages.pageEnd) {
                    str2 += '<li><a href="/videoList/user_video?page='+pages.index+'">下一页</a></li>' +
                        '<li><a href="/videoList/user_video?page='+pages.maxPage+'">末页</a></li>';
                }
                str2+='<li><a href="#">'+pages.maxPage+'页</a></li> </ul>';
            $('#page').html(str2);
            $('#info').html(str);
        }
    });
}