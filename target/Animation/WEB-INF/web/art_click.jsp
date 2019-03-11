<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>文章列表</title>
    <script src="/bootstrap/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/index.css">
</head>
<body style="position: absolute;width:100%;min-height: 100%">
<div>
    <jsp:include page="head.jsp"></jsp:include>
</div>
<div class="container" style="margin-top: 100px;">
    <div class="row">
        <div class="col-md-10 info-left">
            <div class="container-fluid" style="padding:0;">
                <div id="info">
                    <c:forEach items="${show_art}" var="art">
                        <div class="row info-content">
                            <div class="col-md-2 col-sm-2 col-xs-2" style="margin: 2px ; padding: 0">
                                <img src="${art.picUrl}" class="img-responsive" alt=""
                                     href="/article/show?id=${art.artId}"
                                     style="width: 100px;height: 100px;">
                            </div>
                            <div class="col-md-4 col-sm-4 col-xs-4" style="">
                                <h4><a href="/article/show?artId=${art.artId}"> ${art.title}</a></h4>
                                <p>作者：${art.user}</p>
                                <p>发表时间:${art.time}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="foot" <c:choose>
    <c:when test="${size<4}">
        style="position: absolute;bottom: 0px;width: 100%"
    </c:when>
</c:choose> >
    <c:choose>
        <c:when test="${size!=0}">
            <nav style="text-align: center">
                <div id="page">
                    <ul class="pagination">
                        <c:choose>
                            <c:when test="${page.index!=1}">
                                <li><a href="/article/click?page=1">首页</a></li>
                                <li><a href="/article/click?page=${page.index-1}">上一页</a></li>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="index" begin="${page.pageBeg}" end="${page.pageEnd}" step="1" varStatus="int">
                            <li><a href="/article/click?page=${index}">${index}</a></li>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${page.pageEnd!=page.index}">
                                <li><a href="/article/click?page=${page.index+1}">下一页</a></li>
                                <li><a href="/article/click?page=${page.maxPage}">末页</a></li>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                        <li><a href="#">共${page.maxPage}页</a></li>
                    </ul>
                </div>
            </nav>
        </c:when>
    </c:choose>
    <jsp:include page="foot.jsp"></jsp:include>
</div>
</body>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</html>
