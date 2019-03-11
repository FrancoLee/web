
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv= "Expires " content= "0 ">
    <title>文章编辑</title>
    <script src="/bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/images/zh-cn.js"></script>
    <script src="/js/art_edit.js"></script>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div>
    <jsp:include page="head.jsp" flush="false"/>
</div>
<div style="padding-left: 20%; margin-top: 100px;">
    <button type="button" id="show_creart" class="btn">发表文章</button>
    <button type="button" id="show_video" class="btn">发表视频</button>
</div>
<%--富文本--%>
<div id="art" style="display:block">
<form action="/article/add_art" class="container" method="post" id="art_from">
  <div class="form-group" >
      <label for="title">标题：</label><br>
      <input type="text" maxlength="100" style="width:30%;" id="title" name="title" class="form-control">
  </div>
    <div class="form-group">
        <label >内容:(请至少插入一张图片用于显示)</label>
        <script id="editor" type="text/plain"></script>
        <input type="hidden" id="content" name="content" onchange="alert(1)" >
    </div>
    <div class="form-group">
        <span id="art_mes"></span>
        <button class="btn" type="button" id="art_submit">提交</button>
        <%--<button class="btn" type="button" onclick="location.href='/index'">返回首页</button>--%>
    </div>
</form>
</div>
<%--视频上传--%>
<div id="video" style="display: none">
    <form action="/videoList/add_videoList" name="videoList" class="container" method="post"  id="video_from" enctype="multipart/form-data">
        <div class="form-group">
            <label for="video_title">标题:</label><br>
            <input type="text" maxlength="50" style="width: 30%;" id="video_title"  class="form-control" name="title" >
        </div>
        <div class="form-group">
            <label for="video_pic">图片:(用于显示)</label><br>
            <span class="btn btn-success" style="position: relative;display: inline-block;overflow: hidden;background-color: #00a0e9">
            <span>上传<input type="file" id="video_pic" name="pic" onChange="pic_change()"style=" position:absolute;right: 0px;top: 0px;opacity: 0;-ms-filter: 'alpha(opacity=0)';font-size: 200px;"></span>
        </span>

        </div>
        <div class="form-group">
            <span id="video_mes"></span>
            <button class="btn" type="button" id="video_submit">提交</button>
            <button class="btn" type="button" onclick="location.href='/index'">返回首页</button>
        </div>
    </form>
</div>

</div>
<div id="foot"></div>
</body>
<jsp:include page="foot.jsp"></jsp:include>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</html>
