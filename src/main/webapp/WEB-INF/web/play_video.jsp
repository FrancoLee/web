<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>播放视频</title>
    <script src="/bootstrap/js/jquery.min.js"></script>
</head>
<body>
<div>
    <jsp:include page="head.jsp" flush="false"/>
</div>
<div style=" margin-top: 100px">

</div>
<c:choose>
    <c:when test="${video_play==null}">
        该视频未审核;
    </c:when>
    <c:otherwise>
        <div class="container">
            <b>标题:</b> ${video_play.title}<br>
            <hr>
            <video class="edui-upload-video  vjs-default-skin video-js" controls="" preload="none" width="1000" height="800"  src="${video_play.vidUrl}"></video><br>
            <hr>
            <button type="button" class="btn" onclick="location.href='/videoList/show_list?listId=${video_play.listId}'">返回视频列表</button>
        </div>
    </c:otherwise>
</c:choose>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
