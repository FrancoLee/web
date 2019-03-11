<%@ page  language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv= "Expires " content= "0 ">
	<meta http-equiv= "kiben " content= "no-cache "> 
	<title>首页</title>
	<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/index.css">
	<script src="/bootstrap/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/index.js"></script>
</head>
<body>
<!-- 导航栏 -->
    <%--<div class="navbar-wrapper" >--%>
      <div class="container">
 <nav class="navbar navbar-fixed-top my-navbar" style="background-color:#CCE6FF;">
 	<div class="container-fluid">
 		<div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" style="margin: 0;padding: 0;"><img  src="${logo.picUrl}" id="nav_img" style="height:100%;width:180px;"></a>
             </div>

          <div id="navbar" class="navbar-collapse collapse" >
            <ul class="nav navbar-nav" id="left">
              <li ><a href="/index">首页</a></li>

              <%--<li class="dropdown">--%>
                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">下拉	 <span class="caret"></span></a>--%>
                <%--<ul class="dropdown-menu">--%>
                  <%--<li><a href="#">活动</a></li>--%>
                  <%--<li><a href="#">圈圈叉叉</a></li>--%>
                  <%--<li><a href="#">关于</a></li>--%>
                <%--</ul>--%>
              </li>
              <li class="search" style="padding-top: 7px;padding-left: 30px; ">
              		<input type="text" class="form-control" placeholder="search" >
              		</li>
              		<li style="padding-top: 7px; ">
              		<button class="glyphicon glyphicon-search " style="padding-top: 8px;background:transparent;border: 0px;" id="search" ></button>
              </li>
           </ul>
  		 <ul class="nav navbar-nav navbar-right">

			<c:choose>
				<c:when test="${user==null}">
				<li class="active"><a href="#login_modal" data-toggle="modal">登录</a></li>
				<li><a data-toggle="modal" href="#register_modal" >注册</a></li>
		 		</ul>
				</c:when>
				<c:otherwise>
				<li>
					<a href="#">${user.user}</a>
				</li>
				<li id="per_info">
					<a href="#" style="width: 40px;height: 40px;border:1px ;margin:5px;padding:0"  dropdown="toggle" >
						<img src="${user.picUrl}" class="img-responsive img-circle" style="max-width: 100%;" >
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
 <%--</div>--%>

<!-- 轮播图 -->
<div id="myCarousel" class="carousel slide" style="margin-top: 50px;" >
	<ol class="carousel-indicators" id="carousel1">
		<c:forEach var="adv_list" items="${advList}" >
			<li data-target="#myCarousel" data-slide-to="${adv_list.rank}" ></li>
		</c:forEach>

	</ol>
	<div class="carousel-inner" id="carousel2">
		<c:forEach items="${advList}" var="adv_list">
			<div class="item " >
				<img src="${adv_list.picUrl}" class="img-responsive"  style="width:100%;height: 180px;">
			</div>
		</c:forEach>
	</div>
		<a class="carousel-control left" href="#myCarousel"
	 		  data-slide="prev" style="background-image: linear-gradient(to right,rgba(0,0,0,0) 0,rgba(0,0,0,0) 100%)"></a>
		<a class="carousel-control right" href="#myCarousel"
	  		 data-slide="next" style="background-image: linear-gradient(to left,rgba(0,0,0,0) 0,rgba(0,0,0,0) 100%)"></a>
</div>
<!--视频资讯-->

<div id="information" style="background-color: #fff " >
	<div class="row">
		<div class="col-md-1">

		</div>
		<div class="col-md-10 ">
			<div class="container-fluid" >
				<div class="row">
					<div class="col-md-8 ">
						<blockquote>
							<b>最新动漫</b>
							<a href="/videoList/new">more</a>
						</blockquote>
					</div>
					<div class="col-md-2" style="padding-left: 80px">
						<blockquote>
							<b>热门资讯</b>
							<a href="/article/click" style="float: right">more</a>
						</blockquote>
					</div>
				</div>
				<div class="	row col-md-9  edits" >
					<div class="row" style="padding-top: 0px;">
					<c:forEach  items="${videoListNew}" var="video"  >
						<div class="col-md-2">
							<a href="/videoList/show_list?listId=${video.listId}"><img src="${video.picUrl}"  class="img-responsive" alt="" style="width: 175px;height: 158px;"></a>
							<a href="/videoList/show_list?listId=${video.listId}" class="mod-cover">更新至第${video.maxEpi}话</a>
							<h5><a href="/videoList/show_list?listId=${video.listId}" style="color: #0f0f0f">${video.title}</a></h5>
							<b style="float: left" >作者：</b><p>${video.user}</p>

						</div>
					</c:forEach>
					</div>
						<blockquote>
							<b style="color: #0f0f0f">最热动漫</b>
							<a href="/videoList/click" >more</a>
						</blockquote>
					<div class="row">
						<c:forEach  items="${videoListClick}" var="video"  >
							<div class="col-md-2" >
								<a href="/videoList/show_list?listId=${video.listId}"><img src="${video.picUrl}"  class="img-responsive" alt="" style="width: 175px;height: 158px"></a>
								<a href="/videoList/show_list?listId=${video.listId}" class="mod-cover">更新至第${video.maxEpi}话</a>
								<h5><a href="/videoList/show_list?listId=${video.listId}" style="color: #0f0f0f">${video.title}</a></h5>
								<b style="float: left" >作者：</b><p>${video.user}</p>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="col-md-3 info-right" >
					<div class="container-fluid">
						<c:forEach items="${articleClick}" var="art">
							<div class="row" style="padding-top: 10px;">
								<a href="/article/show?artId=${art.artId}" > <img src="${art.picUrl}"   class="img-responsive" alt="" style="height: 110px; width:110px;float: left;margin-top:-5px;margin-right: 5px; "></a>
									<span style="width: 230px;"><a style="display:inline-block;text-overflow:ellipsis;white-space:nowrap; overflow:hidden;width: 230px; " href="/article/show?artId=${art.artId}"  >${art.title}</a></span>
									<p>作者：${art.user}</p>
									<p>发表时间:${art.time}</p>
							</div>
						</c:forEach>
					</div>
				</div>

			</div>
		</div>


	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
> <div class="modal" id="register_modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal"><span>&times;</span></button>
				<h1 class="modal-title">用户注册</h1>
			</div>
			<form method="POST" action="/index/register" id="register_form" >
				<div class="form-group has-feedback" style="margin: 15px;">
					<label for="username">用户名:</label>
					<input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名" margin: 15px>
					<span id="user" ></span>
					<span id="user_manage"></span>
				</div>
				<div class="form-group has-feedback" style="margin: 15px;">
					<label for="password1" >密码：</label>
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
					<input type="text" class="from-control" id="check" placeholder="请输入验证码" maxlength="4" style=";width: 120px;">
					<img src="/index/Check_img" id="check_img" alt="">
					<a id="check_a" href="javascript:void(0);">看不清换一张<br/></a>
					<span id="check_manage"></span>
				</div>
				<div class="modal-footer">
					<span id="manage"></span>
					<input type="button" class="btn btn-default" id="register" value="注册" />
				</div>
			</form>
		</div>
	</div>
</div>
 <!-- 用户登录 -->
<div class="modal"  id="login_modal" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header ">
						<button class="close" data-dismiss="modal"><span>&times;</span></button>
						<h1 class="modal-title">用户登录</h1>
					</div>
				<form  method="POST" action="/index/login" id="login_form" >
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
					<input type="button" class="btn btn-default" id="login" value="登录" />
				</div>
			</form>
		</div>
	</div>
</div>

<script src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function () {
	$('#carousel1').children('li').eq(0).addClass("active");
        $('#carousel2').children('div').eq(0).addClass("active");
        $('#myCarousel').carousel({
			interval:2000
		});
    });

</script>

</body>
</html>
