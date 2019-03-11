<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
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

    <title>视频管理</title>

</head>
<body>

<!--/_header 作为公共模版分离出去-->
<jsp:include page="head.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->
<jsp:include page="menu.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->
<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
        <span class="c-gray en">&gt;</span>
        视频管理
        <span class="c-gray en">&gt;</span>
        视频列表
        <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    </nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
				<select id="select_class" name=""class="select" style="width: 100px" onchange="change(this)">
                    <option value="5" selected="selected">请选择分类</option>
					<option value="3" >全部分类</option>
					<option value="1">已通过</option>
					<option value="0">未审核</option>
                    <option value="2">未通过</option>
                </select>当前分类：<span id="class">${c}</span></span>

            </div>
            <%--<div class="cl pd-5 bg-1 bk-gray mt-20">--%>
				<%--<span class="l">--%>
				<%--&lt;%&ndash;<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>&ndash;%&gt;--%>
				<%--&lt;%&ndash;<a class="btn btn-primary radius" data-title="添加资讯" _href="article-add.html" onclick="article_add('添加资讯','article-add.html')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a>&ndash;%&gt;--%>
				<%--</span>--%>
            <%--</div>--%>
            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                    <thead>
                    <tr class="text-c">
                        <%--<th width="25"><input type="checkbox" name="" value=""></th>--%>
                        <th width="100">标题</th>
                        <th width="80">图片</th>
                        <th width="80">作者</th>
                        <th width="120">发布时间</th>
                        <th width="75">浏览次数</th>
                        <th width="60">发布状态</th>
                        <th width="120">操作</th>
                    </tr>
                    </thead>
                    <tbody id="info">
                    <c:forEach  items="${video_list}" var="video" >
                        <tr class="text-c">
                            <%--<td><input type="checkbox" value="${video.listId}" name=""></td>--%>
                            <td class="text-c"><u style="cursor:pointer" class="text-primary" onClick="video_edit('视频列表','/back/video/show?listId=${video.listId}',${video.listId},this)" title="查看">${video.title}
                               <c:choose>
                                   <c:when test="${video.countNew!=0}">
                                       <span><img style="margin-top: -22px;" src="/back/1-140104202130.gif"></span>
                                   </c:when>
                               </c:choose>

                            </u></td>
                            <td><img src="${video.picUrl}" style="width: 80px;height: 50px;"></td>
                            <td>${video.user}</td>
                            <td>${video.time}</td>
                            <td>${video.click}</td>
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
                            <td class="f-14 td-manage"><a style="text-decoration:none" onClick="videolist_shenhe(this,'${video.listId}')" href="javascript:;" title="审核"><i class="Hui-iconfont">审核</i></a>
                                <a style="text-decoration:none" class="ml-5" onClick="videolist_del(this,'${video.listId}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
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
<script type="text/javascript" src="/back/back_js/videolist.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>