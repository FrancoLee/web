<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="Bookmark" href="favicon.ico">
    <link rel="Shortcut Icon" href="favicon.ico"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="/back/lib/html5.js"></script>
    <script type="text/javascript" src="/back/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="/back/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/css/style.css"/>

    <!--/meta 作为公共模版分离出去-->

    <title>用户列表 </title>
</head>
<body>
<!--/_header 作为公共模版分离出去-->
<jsp:include page="head.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->
<jsp:include page="menu.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->


<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span
            class="c-gray en">&gt;</span> 会员列表<a class="btn btn-success radius r"
                                                 style="line-height:1.6em;margin-top:3px"
                                                 href="javascript:location.replace(location.href);" title="刷新"><i
            class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <%--<a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a>--%>
            <div class="cl pd-5 bg-1 bk-gray mt-20"> <!--<span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>--%>
            --> <span class="l">
				<select id="select_class" name="" class="select" style="width: 100px;" onchange="change(this)">
                <option value="3" selected="selected">请选择分类</option>
					<option value="2">全部分类</option>
					<option value="1">已停用</option>
					<option value="0">已启用</option>
                </select>当前分类：<span id="class">${user_class}</span></span>
                <%--<span class="r">共有数据：<strong>${count}</strong> 条</span> --%>
            </div>
            <div class="mt-20">
                <table class="table table-border table-bordered table-hover table-bg table-sort">
                    <thead>
                    <tr class="text-c">
                        <th width="25" style="display: none"><input type="checkbox" name="" value=""></th>
                        <th width="80" style="display: none">ID</th>
                        <th width="100">用户名</th>
                        <th width="40">昵称</th>
                        <th width="90">头像</th>
                        <th width="150">爱好</th>
                        <th width="">简介</th>
                        <th width="130">加入时间</th>
                        <th width="70">状态</th>
                        <th width="100">操作</th>
                    </tr>
                    </thead>
                    <tbody id="info">
                    <c:forEach var="user" items="${user_list}">
                        <tr class="text-c">
                            <td style="display: none"><input type="checkbox" value="${user.user}" name=""></td>
                            <td style="display: none"></td>
                            <td>${user.user}</td>
                                <%--<u style="cursor:pointer" class="text-primary" onclick="member_show('张三','member-show.html','10001','360','400')">张三</u>--%>
                            <td>${user.name}</td>
                            <td><img src="${user.picUrl}" style="width: 50px;height: 50px;" alt=""></td>
                            <td>${user.hobby}</td>

                            <td class="text-l">${user.info}</td>
                            <td>${user.regTime}</td>
                            <td class="td-status">
                                <c:choose>
                                <c:when test="${user.state=='regular'}">
                                <span class="label label-success radius">已启用</span>
                            <td class="td-manage"><a style="text-decoration:none"
                                                     onClick="member_stop(this,'${user.user}')" href="javascript:;"
                                                     title="停用"><i class="Hui-iconfont">&#xe631;</i></a></td>
                            </c:when>
                            <c:when test="${user.state=='disable'}">
                                <span class="label label-defaunt radius">已停用</span>
                                <td class="td-manage"><a style="text-decoration:none"
                                                         onClick="member_start(this,'${user.user}')"
                                                         href="javascript:;" title="启用"><i
                                        class="Hui-iconfont">&#xe6e1;</i></a></td>
                            </c:when>
                            </c:choose>

                            </td>

                            <!--<td class="td-manage"><a style="text-decoration:none" onClick="member_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i><!--</a> <a title="编辑" href="javascript:;" onclick="member_edit('编辑','member-add.html','4','','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','change-password.html','10001','600','270')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>-->
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
<script type="text/javascript" src="/back/back_js/member-list.js"></script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
