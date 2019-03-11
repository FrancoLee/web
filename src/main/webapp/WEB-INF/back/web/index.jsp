<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="favicon.ico" >
    <link rel="Shortcut Icon" href="favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="/back/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="/back/static/h-ui.admin/css/style.css" />
    <!--/meta 作为公共模版分离出去-->

    <title>动漫后台 v0.1</title>
    <meta name="keywords" content="H-ui.admin v3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin v3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>

<!--/_header 作为公共模版分离出去-->
<jsp:include page="head.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->
<jsp:include page="menu.jsp"></jsp:include>
<!--_menu 作为公共模版分离出去-->

<!--/_menu 作为公共模版分离出去-->

<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont"></i> <a href="/back_index?action=index" class="maincolor">首页</a>
        <span class="c-999 en">&gt;</span>
        <span class="c-666">登录信息</span>
        <span ><a href="/back/test">qqqq</a></span>
        <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="Hui-article">
        <article class="cl pd-20">
            <p class="f-20 text-success">欢迎使用动漫网站
                <span class="f-14">v0.1</span>
                后台</p>
            <%--<p>登录次数：18 </p>--%>
            <p>上次登录时间：${admin.lastTime} </p>
            <p> 本次登录ip：${admin.nowIp} &nbsp &nbsp上次登录ip：${admin.lastIp}</p>
            <table class="table table-border table-bordered table-bg">
                <thead>
                <tr>
                    <th colspan="7" scope="col">信息统计</th>
                </tr>
                <tr class="text-c">
                    <th>统计</th>
                    <th>资讯</th>
                    <th>视频列表</th>
                    <th>视频库</th>
                    <th>用户</th>
                    <th>管理员</th>
                </tr>
                </thead>
                <tbody>
                <tr class="text-c">
                    <td>总数</td>
                    <td>${back_info.countArt}</td>
                    <td>${back_info.countVideoList}</td>
                    <td>${back_info.countVideo}</td>
                    <td>${back_info.countUser}</td>
                    <td>${back_info.countAdmin}</td>
                </tr>
                <tr class="text-c">
                    <td>已审核</td>
                    <td>${back_info.showArt}</td>
                    <td>${back_info.showVideoList}</td>
                    <td>${back_info.showVideo}</td>
                    <td>${back_info.countUser-back_info.sealUser}</td>
                    <td>0</td>
                </tr>
                <tr class="text-c">
                    <td>未审核</td>
                    <td>${back_info.countArt-back_info.showArt}</td>
                    <td>${back_info.countVideoList-back_info.showVideoList}</td>
                    <td>${back_info.countVideo-back_info.showVideo}</td>
                    <td>${back_info.sealUser}</td>
                    <td>0</td>
                </tr>
                </tbody>
            </table>
            <%--<table class="table table-border table-bordered table-bg mt-20">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th colspan="2" scope="col">服务器信息</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody>--%>
                <%--<tr>--%>
                    <%--<th width="30%">服务器计算机名</th>--%>
                    <%--<td><span id="lbServerName">http://127.0.0.1/</span></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器IP地址</td>--%>
                    <%--<td>192.168.1.1</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器域名</td>--%>
                    <%--<td>www.h-ui.net</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器端口 </td>--%>
                    <%--<td>80</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器IIS版本 </td>--%>
                    <%--<td>Microsoft-IIS/6.0</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>本文件所在文件夹 </td>--%>
                    <%--<td>D:\WebSite\HanXiPuTai.com\XinYiCMS.Web\</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器操作系统 </td>--%>
                    <%--<td>Microsoft Windows NT 5.2.3790 Service Pack 2</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>系统所在文件夹 </td>--%>
                    <%--<td>C:\WINDOWS\system32</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器脚本超时时间 </td>--%>
                    <%--<td>30000秒</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器的语言种类 </td>--%>
                    <%--<td>Chinese (People's Republic of China)</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>.NET Framework 版本 </td>--%>
                    <%--<td>2.050727.3655</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器当前时间 </td>--%>
                    <%--<td>2014-6-14 12:06:23</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器IE版本 </td>--%>
                    <%--<td>6.0000</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>服务器上次启动到现在已运行 </td>--%>
                    <%--<td>7210分钟</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>逻辑驱动器 </td>--%>
                    <%--<td>C:\D:\</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>CPU 总数 </td>--%>
                    <%--<td>4</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>CPU 类型 </td>--%>
                    <%--<td>x86 Family 6 Model 42 Stepping 1, GenuineIntel</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>虚拟内存 </td>--%>
                    <%--<td>52480M</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>当前程序占用内存 </td>--%>
                    <%--<td>3.29M</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>Asp.net所占内存 </td>--%>
                    <%--<td>51.46M</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>当前Session数量 </td>--%>
                    <%--<td>8</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>当前SessionID </td>--%>
                    <%--<td>gznhpwmp34004345jz2q3l45</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>当前系统用户名 </td>--%>
                    <%--<td>NETWORK SERVICE</td>--%>
                <%--</tr>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        </article>
        <jsp:include page="footer.jsp" flush="false"/>

    </div>
</section>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/back/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/back/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/back/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="/back/static/h-ui.admin/js/H-ui.admin.page.js"></script>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">

</script>
<!--/请在上方写此页面业务相关的脚本-->

<!--此乃百度统计代码，请自行删除-->
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
      //  hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
        var s = document.getElementsByTagName("script")[0];
       // s.parentNode.insertBefore(hm, s);
    })();
</script>
<!--/此乃百度统计代码，请自行删除-->
</body>
</html>
