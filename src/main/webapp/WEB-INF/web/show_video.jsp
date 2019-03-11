<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%
    String basePath=System.getProperty("webRoot");
%>
<html>
<head>
    <title>视频</title>
    <script type="text/javascript">
        var vid_id=${videoList.listId}
    </script>
    <script src="/bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/webuploader.js"></script>
    <script type="text/javascript" src="/js/show_video.js"></script>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">

    <link rel="stylesheet" href="/css/webuploader.css">
</head>
<body>
<div>
    <jsp:include page="head.jsp"></jsp:include>
</div>
<div class="container" style="margin-top: 100px;">
    <div class="row">
        <div class="col-md-10 info-left">
            <div class="container-fluid" style="padding:0;">
                <div class="row info-content">
                    <div class="col-md-5 col-sm-3 col-xs-5">
                        <img src="${videoList.picUrl}" class="img-responsive" alt=""
                             style="width: 300px; height: 400px;">
                    </div>
                    <div class="col-md-7 col-sm-7 col-xs-7" style="padding-top: 20px;padding-left: -10px;">
                        <h4 style="padding-top: 20px;">${videoList.title}</h4>
                        <p style="padding-top: 20px;"><b>作者:</b> ${videoList.user}</p>
                        <p style="padding-top: 20px;"><b>发表日期：</b>${videoList.time}</p>
                        <p style="padding-top: 20px;"><b>点击数：</b>${videoList.click}</p>
                    </div>
                </div>
                <div class="row" style="padding-top: 10px;">
                    <c:forEach var="video_list" items="${videos}">
                        <c:choose>
                            <c:when test="${(video_list.show==1)||(user.user==video_list.user)}">
                                <a style="padding-left: 8px;"
                                   href="/video/play?id=${video_list.vid}&&epi=${video_list.epi}">第${video_list.epi}集 </a>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${user.user==video_list.user}">
                                <c:choose>
                                    <c:when test="${video_list.show==0}">
                                        (未审核)
                                    </c:when>
                                    <c:when test="${video_list.show==1}">
                                        (通过审核)
                                    </c:when>
                                </c:choose>
                                <a href="/video/delete?vid=${video_list.vid}&&listId=${videoList.listId}">×</a>
                                <%--<button id="btn_remove" data-toggle="modal" href="#show_remove" class="btn-default">删除视频</button>--%>
                            </c:when>
                        </c:choose>

                    </c:forEach>
                </div>
                <c:choose>
                    <c:when test="${user.user==videoList.user}">
                        <div class="row" style="margin-top: 30px;">
                            <button id="btn_add" data-toggle="modal" href="#show_add" class="btn">添加视频</button>
                        </div>
                        <%--<button id="btn_remove" data-toggle="modal" href="#show_remove" class="btn-default">删除视频</button>--%>
                    </c:when>
                </c:choose>

            </div>
        </div>
    </div>
    <div class="modal" id="show_add">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal"><span>&times;</span></button>
                    <h1 class="modal-title">添加视频</h1>
                </div>

                <form action="/videoList/add_video?listId=${videoList.listId}" id="video_from" method="post">

                    <div class="form-group">
                        <label for="title">标题:</label><br>
                        <input type="text" id="title" name="title">
                    </div>
                    <div class="form-group ">
                        <label for="epi">第几集：</label><br>
                        <input type="text" id="epi" name="epi" onkeyup="value=value.replace(/\D/g,'')">
                    </div>
                    <input type="hidden" id="vid" name="vid">
                    <div class="form-group ">
                        <span style="font-size:14px;"></span>
                        <div id="uploader-demo">
                            <!--用来存放item-->
                            <div id="thelist" class="uploader-list"></div>
                            <div id="up_info">
                                <div id="filePicker">选择视频</div>
                                <button id="ctlBtn" type="button" class="btn btn-default">开始上传</button>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <span id="mes"></span>
                        <button class="btn" type="button" id="video_sub">提交</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<div id="foot" style="position: absolute;bottom: 0px;width: 100%">
    <jsp:include page="foot.jsp"></jsp:include>
</div>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
