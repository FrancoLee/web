function delete_art(page, artId) {
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/article/delete",
        data: {'artId': artId, 'page': page},
        success: function (data) {
            var articles = data.articles;
            var str = '';
            var pages = data.page;
            if (data.counts == 0) {
                if (page > 1) {
                    var s = "/article/user_art?page=" + (page - 1);
                    window.location.href = s;
                }
                else {
                    alert("所有文章都已经被删除");
                    $('#page').html('');
                    $('#info').html('');
                    return;
                }
            }
            if (data.counts < 4) {
                $('#foot').css("position", "absolute");
                $('#foot').css("bottom", "0px");
                $('#foot').css("width", "100%");
                //style="position: absolute;bottom: 0px;width: 100%"
            }
            else {
                $("#foot").removeAttr("style");
            }
            for (var i = 0; i < articles.length; i++) {
                str += '<div class="row info-content">' +
                    '<div class="col-md-2 col-sm-2 col-xs-2" style="margin: 2px ; padding: 0">' +
                    '<img src="' + articles[i].picUrl + '" class="img-responsive" alt="" href="/article/show?id=' + articles[i].artId + '"' +
                    'style="width: 100px;height: 100px;"></div>' +
                    '<div class="col-md-4 col-sm-4 col-xs-4" style="">' +
                    '<h4><a href="/article/show?artId=${art.artId}">' + articles[i].title + '</a></h4>' +
                    '<p>作者：' + articles[i].user + '</p>' +
                    ' <p>发表时间:' + articles[i].time + '</p>' +
                    ' <button class="btn" onclick="delete_art(' + pages.index + ',' + articles[i].artId + ')">删除</button>' +
                    ' <a href="/article/delete?artId=${art.artId}">删除</a>' +
                    '<b style="color:#4b72a4 ">状态';
                if (articles[i].show == 0)
                    str += '未审核';
                else if (articles[i].show == 2)
                    str += '审核不通过';
                else if (articles[i].show == 1)
                    str += '审核通过';
                str += '</b> </div> </div>';
            }
            var str2 = '<ul class="pagination">';
            if (pages.index != 1) {
                str2 += '<li><a href="/article/user_art?page=1">首页</a></li>' +
                    '<li><a href="/article/user_art?page=' + (pages.index - 1) + '">上一页</a></li>';
            }
            for (var i = pages.pageBeg; i < pages.pageEnd; i++) {
            }
            str2 += '<li><a href="/article/user_art?page=' + i + '">' + i + '</a></li>'
            if (pages.pageEnd != pages.index) {
                str2 += ' <li><a href="/article/user_art?page=' + (pages.index + 1) + '">下一页</a></li>' +
                    '<li><a href="/article/user_art?page=' + pages.maxPage + '">末页</a></li>';

            }
            str2 += '<li><a href="#">共' + pages.maxPage + '页</a></li> </ul>';
            $('#page').html(str2);
            $('#info').html(str);

        }
    });
}
