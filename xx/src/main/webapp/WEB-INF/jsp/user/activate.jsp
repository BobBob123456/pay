<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册成功-国盛通-第三方在线支付平台-支付改变生活</title>
<base href="<%=basePath%>"/>
<link rel="stylesheet" href="css/style.css" />
<script src="js/jquery.min.js"></script>
<!--背景图片自动更换-->
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>

<script type="text/javascript">
	var i = 61;
	var t;
	$(document).ready(function(e) {
		t = window.setInterval("djs()", 1000);
		var result = $("#result").val();
		if (result == "activated") {
			$("#Company_Content").html("该账户已经激活");
			$("#Company_Content").css("background-image", "");
		}

		if (result == "fail") {
			$("#Company_Content").html("无效的信息");
			$("#Company_Content").css("background-image", "");
		}
	});

	function djs() {
		i--;
		$("#djs").text(i);
		if (i == 0) {
			window.clearInterval(t);
			$("#cxfs").attr("disabled", false);
		}
	}
</script>
</head>

<body>

<div class="login-container">
	<h1>国盛通管理系统-账号激活信息</h1>
	<br/><br/><br/><br/>	
	<p>感谢您注册国盛通，您已经成功激活</p><br/>
	<p>您现在可以登录国盛通，使用国盛通提供的各种支付服务。</p><br/>
	<p><a href="user/login.html" style="color:white;">登录国盛通</a></p>
</div>
</body>
</html>






