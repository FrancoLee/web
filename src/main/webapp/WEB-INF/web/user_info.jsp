<%@ page  language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Expires " content="0 ">
    <meta http-equiv="kiben " content="no-cache ">
    <title>个人信息</title>
    <script src="/bootstrap/js/jquery.min.js"></script>
    <script src="/jquery/jquery.Jcrop.min.js"></script>
    <script type="text/javascript">
        var picUrl=${user.picUrl};
    </script>
    <script type="text/javascript" src="/js/user_info.js"></script>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/jquery.Jcrop.min.css">
    <link rel="stylesheet" href="/css/user_info.css">

</head>
<body>
<div>
    <jsp:include page="head.jsp" flush="false"/>
</div>
<div class="container" style="margin-top: 100px;">
    <%--<div class="form-group" >--%>
    <img src="${user.getPicUrl()}" class="img-responsive img-circle" style="width: 100px;height: 100px;">
    <a data-toggle="modal" href="#user_info" style="top: 50%;">上传头像</a>
    <div id="form_update" style="display:none">
        <form class="user_update" action="/user/info_change" method="post">

            <%--</div>--%>
            <div class="form-group">
                <label for="username">昵称：</label>
                <input type="text" id="username" name="name">
            </div>
            <div class="form-group">
                <label for="hobby">爱好：</label>
                <input type="text" id="hobby" name="hobby">
            </div>
            <div class="form-group">
                <label for="info">个人简介:</label>
                <textarea class="form-control" rows="4" style=" resize:none;width: 40%" id="info"
                          name="info"></textarea>
            </div>
            <div class="form-group">
                <input type="submit" class="btn" value="确认修改">
                <button id="show_userinfo" class="btn" type="button">返回</button>
            </div>
            <input type="hidden" name="user" value="${user.user}">
        </form>
    </div>
    <div id="show_info">
        昵称:<label>
        <c:choose>
            <c:when test="${user.name==''}">
                暂无
            </c:when>
            <c:otherwise>
                ${user.name}
            </c:otherwise>
        </c:choose>
    </label><br/>
        爱好：<label>
        <c:choose>
            <c:when test="${user.hobby==''}">
                暂无
            </c:when>
            <c:otherwise>
                ${user.hobby}
            </c:otherwise>
        </c:choose>
    </label><br/>
        兴趣:<label>
        <c:choose>
            <c:when test="${user.info==''||user.info==null}">
                暂无
            </c:when>
            <c:otherwise>
                ${user.info}
            </c:otherwise>
        </c:choose>
    </label><br/>
        <button id="change_userinfo" class="btn">修改信息</button>
    </div>

</div>

<div class="modal" id="user_info" >
    <div class="modal-dialog" style="width:900px; ">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal"><span>&times;</span></button>
                <h1 class="modal-title">选择图片<span>(至少选择100×100像素以上,默认全图)</span></h1>
            </div>
            <form method="post" action="/user/pic_upload" id="pic_form" enctype="multipart/form-data">
                <input type="file" id="image" name="img" onchange="img_pre()"/>
                <div id="content">
                    <img src="" id="imq" style="width: 600px;height: 600px;"/>
                    <div id="preview-pane">
                        <div class="preview-container">
                            <img src="" class="jcrop-preview " id="imx"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="pic_manage"></span>
                    <input type="button" class="btn btn-default" id="pic_updata" value="确定"/>
                </div>
                <input type="hidden" name="width" id="cw">
                <input type="hidden" name="height" id="ch">
                <input type="hidden" name="c_width" id="rx">
                <input type="hidden" name="c_height" id="ry">
            </form>
        </div>
    </div>
</div>
<div style="position: absolute;bottom: 0px;width: 100%">
    <jsp:include page="foot.jsp"></jsp:include>
</div>
</body>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</html>