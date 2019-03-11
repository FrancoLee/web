<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>动漫后台 v0.1</title>
</head>
<link rel="Bookmark" href="favicon.ico" >
<link rel="Shortcut Icon" href="favicon.ico" />

<link rel="stylesheet" type="text/css" href="/back/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/back/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/css/style.css" />
<body>
<!--/_header 作为公共模版分离出去-->
<jsp:include page="head.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->
<jsp:include page="menu.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->

<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> <a href="/back/index">首页</a>
        <span class="c-gray en">&gt;</span>
        资讯管理
        <span class="c-gray en">&gt;</span>
        资讯列表
        <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
    </nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
				<select id="select_class" name=""class="select" style="width: 100px" onchange="change(this)">
                    <option value="5" selected="selected">请选择分类</option>
					<option value="3">全部分类</option>
					<option value="1">已通过</option>
					<option value="0">未审核</option>
                    <option value="2">未通过</option>
                </select>当前分类：<span id="class">${c}</span></span>
				<%--</span>--%>
                <%--<input type="text" name="" id="" placeholder=" 资讯名称" style="width:250px" class="input-text">--%>
                <%--<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜资讯</button>--%>
            </div>
            <div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
				<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
				<%--<a class="btn btn-primary radius" data-title="添加资讯" _href="article-add.html" onclick="article_add('添加资讯','article-add.html')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a>--%>
				</span>
                <%--<span class="r">共有数据：<strong>${count}</strong> 条</span>--%>
            </div>
            <div class="mt-20">
                <table class="table table-border table-bordered table-bg table-hover table-sort">
                    <thead>
                    <tr class="text-c">
                        <th width="25"><input type="checkbox" name="" value=""></th>
                        <th width="80">ID</th>
                        <th>标题</th>
                        <th width="80" style="display:none">分类</th>
                        <th width="80">来源</th>
                        <th width="120">更新时间</th>
                        <th width="75">浏览次数</th>
                        <th width="60">发布状态</th>
                        <th width="120">操作</th>
                    </tr>
                    </thead>
                    <tbody id="info">
                    <c:forEach var="art" items="${art_list}">
                        <tr class="text-c">
                            <td class="my-select"><input type="checkbox" value="${art.id}" name="check"></td>
                            <td>${art.id}</td>
                            <td class="text-l"><a href="/article/show?artId=${art.id}" >${art.title}</a></td>
                            <td style="display:none">行业动态</td>
                            <td>${art.artUser}</td>
                            <td><fmt:formatDate value="${art.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${art.click}</td>
                            <td class="td-status">
                            <c:choose>
                                <c:when test="${art.artState=='accept'}">
                                <span class="label label-success radius">
                                    已通过
                                </c:when>
                                <c:when test="${art.artState=='unprocessed'}">
                                    <span class="label label-danger radius">
                                    未审核
                                </c:when>
                                <c:when test="${art.artState=='fail'}">
                                    <span class="label label-default radius">
                                    未通过
                                </c:when>
                            </c:choose>
                                </span></td>
                                 <td class="f-14 td-manage"><a style="text-decoration:none" onClick="article_shenhe(this,'${art.id}')" href="javascript:;" title="审核">审核</a>
                                <%--<a style="text-decoration:none" class="ml-5" onClick="article_edit('资讯编辑','article-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>--%>
                                <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'${art.id}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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
<script type="text/javascript" src="/back/back_js/article.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
