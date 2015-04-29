<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>404页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta property="wb:webmaster" content="3f67e55490dc7949" />
<style type="text/css">
        body { font-family: "Microsoft yahei"; background: #f2f2f2; }
        img { border: none; }
        .msg-wrap { padding-top: 12%; }
        .msg-img { text-align: center; }
        .msg-info { line-height: 1.5; text-align: center; font-size: 16px; font-weight: bold; color: #333; }
        .msg-info a { color: #bb0202; text-decoration: none; }
        .msg-info a:hover { text-decoration: underline; }
    </style>
</head>

<body>
	<div class="msg-wrap">
		<div class="msg-img">
			<img src="<%=basePath%>/images/404.png">
		</div>
		<div class="msg-info">
			<p>
				嚓~迷路竟然遇到大(gao)神(shou)了！！<a rel="nofollow"
					href="<%=basePath%>/index.html">点击这里</a>
				PK一下~ <br>或<a href="<%=basePath%>/index.html">返回首页</a>~
			</p>
		</div>
	</div>
</body>
</html>
