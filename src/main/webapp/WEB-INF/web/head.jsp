<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript" src="/js/head.js"></script>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">

<!-- 导航栏 -->
<div class="container">
    <nav class="navbar navbar-fixed-top my-navbar" style="background-color:#CCE6FF;">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#" style="margin: 0;padding: 0;"><img src="${logo.picUrl}" id="nav_img"
                                                                                    style="height:100%;width:180px;"></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav" id="left">
                    <li><a href="/index">首页</a>

                        <%--<li class="dropdown">--%>
                        <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">下拉	 <span class="caret"></span></a>--%>
                        <%--<ul class="dropdown-menu">--%>
                        <%--<li><a href="#">活动</a></li>--%>
                        <%--<li><a href="#">圈圈叉叉</a></li>--%>
                        <%--<li><a href="#">关于</a></li>--%>
                        <%--</ul>--%>
                    </li>
                    <%--<li class="search" style="padding-top: 7px;padding-left: 30px; ">--%>
                    <%--<input type="text" class="form-control" placeholder="search" >--%>
                    <%--</li>--%>
                    <%--<li style="padding-top: 7px; ">--%>
                    <%--<button class="glyphicon glyphicon-search " style="padding-top: 8px;background:transparent;border: 0px;" id="search" ></button>--%>
                    <%--</li>--%>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                    <c:when test="${user==null}">
                    <li class="active"><a href="#login_modal" data-toggle="modal">登录</a></li>
                    <li><a data-toggle="modal" href="#register_modal">注册</a></li>
                </ul>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="#">${user.user}</a>
                    </li>
                    <li id="per_info">
                        <a href="#" style="width: 40px;height: 40px;border:1px ;margin:5px;padding:0" dropdown="toggle">
                            <img src="${user.picUrl}" class="img-responsive img-circle" style="max-width: 100%;">
                        </a>
                        <ul class="dropdown-menu open" id="per_man">
                            <li><a href="/user/info">个人信息</a></li>
                            <li><a href="/article/user_art">全部文章</a></li>
                            <li><a href="/videoList/user_video">全部视频</a></li>
                            <li><a href="/edit">发表文章/视频</a></li>
                            <li><a href="/index/logout">登出</a></li>
                        </ul>
                    </li>
                    </ul>
                </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</div>
</div>


<!-- 用户注册 -->
<div class="modal" id="register_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal"><span>&times;</span></button>
                <h1 class="modal-title">用户注册</h1>
            </div>
            <form method="POST" action="/index?action=register" id="register_form">
                <div class="form-group has-feedback" style="margin: 15px;">
                    <label for="username">用户名:</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名" margin:
                           15px>
                    <span id="user"></span>
                    <span id="user_manage"></span>
                </div>
                <div class="form-group has-feedback" style="margin: 15px;">
                    <label for="password1">密码：</label>
                    <input type="password" class="form-control" name="password" id="password1" placeholder="请输入密码">

                    <span id="pass1"></span>
                    <span id="pass1_manage"></span>
                </div>
                <div class="form-group has-feedback" style="margin: 15px;">
                    <label for="password2">再次输入密码:</label>
                    <input type="password" class="form-control" id="password2" placeholder="请再次输入密码">
                    <span id="pass2"></span>
                    <span id="pass2_manage"></span>
                </div>
                <div class="form-group has-feedback" style="margin: 15px;">
                    <label for="check">请输入验证码:</label>
                    <input type="text" class="from-control" id="check" placeholder="请输入验证码" maxlength="4"
                           style=";width: 120px;">
                    <img src="/index/Check_img" id="check_img" alt="">
                    <a id="check_a" href="javascript:void(0);">看不清换一张<br/></a>
                    <span id="check_manage"></span>
                </div>
                <div class="modal-footer">
                    <span id="manage"></span>
                    <input type="button" class="btn btn-default" id="register" value="注册"/>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 用户登录 -->
<div class="modal" id="login_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header ">
                <button class="close" data-dismiss="modal"><span>&times;</span></button>
                <h1 class="modal-title">用户登录</h1>
            </div>
            <form method="POST" action="/index?action=login" id="login_form">
                <div style="margin: 15px;">
                    <label for="login_username">用户名：</label>
                    <input type="text" class="form-control" name="username" id="login_username" placeholder="请输入用户名">
                    <span id="login_user"></span>
                </div>
                <div style="margin: 15px;">
                    <label for="login_password">密码:</label>
                    <input type="password" class="form-control" name="password" id="login_password" placeholder="请输入密码">
                    <span id="login_pass"></span>
                </div>
                <div class="modal-footer">
                    <span id="login_manage"></span>
                    <input type="button" class="btn btn-default" id="login" value="登录"/>
                </div>
            </form>
        </div>
    </div>
</div>
