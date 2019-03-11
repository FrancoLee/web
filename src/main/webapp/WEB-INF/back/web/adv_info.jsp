<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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


<title>图片列表</title>
</head>
<body>
<!--/_header 作为公共模版分离出去-->
<jsp:include page="head.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->
<jsp:include page="menu.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->
<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 轮播图管理 <span class="c-gray en">&gt;</span> 轮播图列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="picture_add('添加','/back/adv/add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加轮播</a></span></div>
            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                    <thead>
                    <tr class="text-c">
                        <th width="40"><input name="" type="checkbox" value=""></th>
                        <th width="80">ID</th>
                        <th width="100">分类</th>
                        <th width="200">封面</th>
                        <th width="150">简介</th>
                        <th width="60">顺序</th>
                        <th width="100">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="adv" items="${adv_list}">
                    <tr class="text-c">
                        <td><input name="check" type="checkbox" value="${adv.id}"></td>
                        <td>${adv.id}</td>
                        <td>轮播图</td>
                        <td><img src="${adv.picUrl}" style="width: 200px;height: 50px;"></td>
                        <td class="text-c">${adv.info}</td>
                        <td>${adv.rank}</td>
                        <%--<td class="td-status"><span class="label label-success radius">已发布</span></td>--%>
                        <td class="td-manage"><a style="text-decoration:none" class="ml-5" onClick="picture_del(this,'${adv.id}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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
<script type="text/javascript" src="/back/back_js/adv_info.js"></script>

</body>
</html>
