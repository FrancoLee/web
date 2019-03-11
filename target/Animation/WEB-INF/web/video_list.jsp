<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>全部视频</title>
    <script src="/bootstrap/js/jquery.min.js"></script>
    <script src="/js/video_list.js"></script>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/index.css">

</head>
<body>
<div>
    <jsp:include page="head.jsp"></jsp:include>
</div>
<div class="container" style="margin-top: 100px;">
    <div class="row">
        <div class="col-md-10 info-left">
            <div class="container-fluid" style="padding:0;">
                <div id="info">
                <c:forEach items="${show_video}" var="video">
                    <div class="row info-content">
                        <div class="col-md-2 col-sm-2 col-xs-2" style="margin: 2px ; padding: 0">
                            <img src="${video.picUrl}" class="img-responsive" alt=""
                                 href="/video?action=show_video&&listId=${video.listId}"
                                 style="width: 100px;height: 100px;margin-left: 20px;">
                        </div>
                        <div class="col-md-4 col-sm-4 col-xs-4" style="">
                            <h4><a href="/videoList/show_list?listId=${video.listId}"> ${video.title}</a></h4>
                            <p>作者：${video.user}</p>
                            <p>发表时间:${video.time}</p>
                            <c:choose>
                                <c:when test="${user.user==video.user}">
                                    <button class="btn" onclick="delete_video(${page.index},${video.listId})" value="删除">删除</button>
                                    <%--<a href="/videoList/delete?listId=${video.listId}">删除</a>--%>
                                    <b style="color:#4b72a4 ">
                                        状态:
                                        <c:choose>
                                            <c:when test="${video.show==0}">
                                                未审核
                                            </c:when>
                                            <c:when test="${video.show==2}">
                                                审核不通过
                                            </c:when>
                                            <c:when test="${video.show==1}">
                                                审核通过
                                            </c:when>
                                        </c:choose>
                                    </b>
                                </c:when>
                            </c:choose>
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
                                <li><a href="/videoList/user_video?page=1">首页</a></li>
                                <li><a href="/videoList/user_video?page=${page.index-1}">上一页</a></li>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="index" begin="${page.pageBeg}" end="${page.pageEnd}" step="1" varStatus="int">
                            <li><a href="/videoList/user_video?page=${index}">${index}</a></li>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${page.pageEnd!=page.index}">
                                <li><a href="/videoList/user_video?page=${page.index+1}">下一页</a></li>
                                <li><a href="/videoList/user_video?page=${page.maxPage}">末页</a></li>
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
