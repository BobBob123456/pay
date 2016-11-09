<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>激活成功-国盛通-第三方在线支付平台-支付改变生活</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/defaultcss.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/slider.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/reg.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery.nbspSlider.1.0.min.js"></script>

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
	<script type="text/javascript" src="<%=basePath%>js/floatDiv.js"></script>
	<div id="toptoptop">
		<div>
			欢迎来到国盛通！&nbsp;&nbsp;<a href="<%=basePath %>"
				style="color: #fff">[登录]</a>&nbsp;&nbsp;<a
				href="<%=basePath %>" style="color: #fff">[注册]</a>&nbsp;|&nbsp;
		</div>
	</div>

	<input type="hidden" id="result" value="${result}" />
	<div class="ge" style="clear: left"></div>
	<div id="toptopmenudiv"
		style="width: 3000px; height: 300px; background-color: #F2F2F2; display: none;"></div>
	<script type="text/javascript">
		$(document).ready(function(e) {
			$("#toptopmenudiv").floatdiv({
				left : "0px",
				top : "110px"
			});
		});
	</script>

	<div id="Company_Content"
		style="text-align: center; width: 760px; height: 296px; margin-top: 30px; margin-bottom: 30px; background-image: url(<%=basePath%>images/regregok.gif)">
		<div style="width: 100%; height: auto;">
			<div
				style="width: 100%; height: 150px; text-align: left; font-size: 15px;"></div>
			<div
				style="width: 100%; height: auto; text-align: left; font-size: 15px; font-weight: bold;">感谢您注册国盛通，您已经成功激活</div>
			<div
				style="width: 100%; height: auto; text-align: left; font-size: 15px; font-weight: bold;">您现在可以登录国盛通，使用国盛通提供的各种支付服务。</div>
			<div
				style="width: 100%; height: auto; text-align: left; font-size: 15px; font-weight: bold;">
				<a href="/Index_dengluqy.html">登录国盛通</a>
			</div>
		</div>
	</div>

	<div style="clear: left;"></div>
	<div style="clear: left"></div>
	<br>
	<div
		style="width: 100%; height: 180px; margin: 0px auto; background-color: #dbe0e3;">
		<br>
		<br>
		<!---------------------------------------------------------------------------------------------->
		<div id="foot">
			<div class="dt">
				<a href="/Index_company.html">关于国盛通</a> | <a
					href="Index_sjtcjwt.html">帮助中心</a> | <a href="Index_sjtcjwt.html">问题反馈</a>
				| <a href="/Index_fwdt.html">联系我们</a> | <a href="/Index_ysxy.html">国盛通服务协议</a>
			</div>
			<div class="dt">
				版权所有2012-2013 渝ICP备12006448号 <span
					style="font-weight: bold; color: #F60;"></span>
			</div>
			<div class="dt"
				style="margin: 0px auto; height: 35px; text-align: center">

				<a
					href="https://sealinfo.verisign.com/splash?form_file=fdf/splash.fdf&dn=www.ruift.com&lang=zh_cn"
					target="_blank"> <img src="<%=basePath%>images/a2.png"
					style="border: 0px"></a> <a href="http://www.365anfang.com/"
					target="_blank"><img src="<%=basePath%>images/a3.png"
					style="border: 0px"></a> <a
					href="http://huhehaote.cyberpolice.cn/" target="_blank"><img
					src="<%=basePath%>images/a4.png" style="border: 0px"></a> <a
					href="http://net.china.com.cn/" target="_blank"><img
					src="<%=basePath%>images/a5.gif" style="border: 0px;"></a> <a
					href="http://www.315online.com.cn/" target="_blank"><img
					src="<%=basePath%>images/a6.gif" style="border: 0px"></a>
			</div>
		</div>
		<br>
		<div class="dt"></div>
	</div>

	<!---------------------------------------------------------------------------------------------->
	</div>

</body>
</html>
