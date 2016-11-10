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
	});

	function djs() {
		i--;
		$("#djs").text(i);
		if (i == 0) {
			window.clearInterval(t);
			$("#cxfs").attr("disabled", false);
		}
	}
	
	function plfsjh(arraylist) {
		$.ajax({
			type : 'POST',
			url : '<%=basePath%>user/cfEmail.html',
			data : "uname=" + arraylist,
			dataType : 'text',
			success : function(str) {
				if(str!="ok"){
					alert(str+"ere");
				}else{
					alert("已成功重新发送激活邮件，请意查收！");
				}
			},
			error : function() {
				alert("处理失败！");
			}
		});
	}


</script>
</head>

<body>

<div class="login-container">
	<h1>
	国盛通管理系统 -激活账号
	</h1>
	<br/><br/><br/><br/>	
	<p>国盛通已向您的邮箱发送了一封邮件，请注意查收！<br/>
您的国盛通的账户名是：<span style='color:red;width:auto;text-decoration:none;font-size:14px;'>${uname}</span><br/>
请进入邮箱查收邮件，如无法收到邮件，请点击 <a onclick="return plfsjh('${uname}');" style='color:red;width:auto;text-decoration:none;font-size:14px;cursor: pointer;'>重新发送邮件</a></p>
</div>
</body>
</html>