<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--_meta 作为公共模版分离出去-->
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="favicon.ico" >
    <link rel="Shortcut Icon" href="favicon.ico" />
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="/back/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/css/style.css" />

    <!--/meta 作为公共模版分离出去-->

    <title>视频列表</title>
    <meta name="keywords" content="H-ui.admin v3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin v3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<section>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 视频列表
        <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a> </nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <div class="cl pd-5 bg-1 bk-gray mt-20">
                <span class="l"> <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span>
               <%--<span class="r">共有数据：<strong>54</strong> 条</span>--%>
            </div>
            <table class="table table-border table-bordered table-bg table-sort">
                <thead>
                <tr>
                    <th scope="col" colspan="9">视频列表</th>
                </tr>
                <tr class="text-c">
                    <th width="25"><input type="checkbox" name="" value=""></th>
                    <th width="250">标题</th>
                    <th width="90">集数</th>
                    <th width="150">play</th>
                    <th width="100">作者</th>
                    <%--<th width="130">加入时间</th>--%>
                    <th width="100">审核</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="video" items="${video_list}">
                    <tr class="text-c">
                        <td><input type="checkbox" value="${video.vid}" name="check"></td>
                        <td>${video.title}</td>
                        <td>${video.epi}</td>
                        <td><img src="/back/static/h-ui/images/play.jpeg" style="width: 30px;height: 30px;" alt="" onClick="play_video('${video.vidUrl}')"></td>
                        <td>${video.user}</td>
                        <td class="td-status">
                            <c:choose>
                                <c:when test="${video.show==0}">
                                    <span class="label label-danger radius">未审核</span>
                                </c:when>
                                <c:when test="${video.show==1}">
                                    <span class="label label-success radius">已通过</span>
                                </c:when>
                                <c:when test="${video.show==2}">
                                    <span class="label label-default radius">未通过</span>
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="f-14 td-manage"><a style="text-decoration:none" onClick="video_shenhe(this,'${video.vid}')" href="javascript:;" title="审核"><i class="Hui-iconfont">审核</i></a>
                        <%--<td class="td-manage"><a style="text-decoration:none" onClick="video_shenhe(this,'10001')" href="javascript:;" title=""><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','admin-add.html','1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>--%>
                            <a style="text-decoration:none" class="ml-5" onClick="video_del(this,'${video.vid}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </article>
    </div>
</section>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/back/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/back/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/back/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="/back/static/h-ui.admin/js/H-ui.admin.page.js"></script>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/back/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/back/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/back/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="/back/back_js/video_show.js"></script>
</body>
</html>
