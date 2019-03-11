
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>文章</title>
    <script src="/bootstrap/js/jquery.min.js"></script>
</head>
<body>
<div>
    <jsp:include page="head.jsp" flush="false"/>
</div>

<div style="left: 20% ; margin-top: 100px;" >
    <div class="container">
        <b>标题：</b>${art.title}
        <hr>
        ${art.content}
        <hr>
        <div style="float: right">
        <b>作者：</b>${art.user}
        <b>发表日期:</b>${art.time}
        <b>点击量：</b>${art.click}
        </div>
    </div>

</div>
<jsp:include page="foot.jsp"></jsp:include>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
