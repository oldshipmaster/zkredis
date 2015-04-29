<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path ;
%>
<!doctype html>
<head>
<meta http-equiv="content-type"  content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<title>游戏支付中心-支付结果</title>
<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/css-html5.css">
<script type="text/javascript" charset="utf-8" src="<%=basePath %>/js/xback.js"></script>
</head>

<body>
	<header id="dHeader"> 
		<span>游戏中心-支付结果</span>
		<!-- <a id="dHome" href="javascript:history.go(-1)"><div class="back"></div></a> -->
	</header>
	
	<div class="msg">
		<div class="inf"><img src="<%=basePath %>/images/ico_s_3df4fe9.png" width="20">支付成功</div>
	</div>
	<div class="tips2" style="padding-left:17px;">
		充值帮助:<br/>
		1.一般1~10分钟即可到帐,安全方便<br/>
		2.客服热线:020-87225109<br/>
		  &nbsp;&nbsp;&nbsp;客服QQ:174673877
	</div>
	
	<div id="dButton" class="btnpar">
		<input type="submit" class="btn" id="submit_btn" 
			value="返回游戏" onclick="javascript:window.submitlistner.submit_btn('0')">
	</div>
</body>

<script type="text/javascript">
window.setTimeout(function(){
	window.submitlistner.submit_btn('0');
}, 1500); 
</script>
</html>