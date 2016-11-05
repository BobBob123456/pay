<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>账户注册--国盛通管理系统</title>
<meta
	content="支付网银在线在外贸、航空等多个领域有着多年的外卡收款服务经验，服务客户包括海航等优质客户，商户可以通过单一接口接入MOTOpay和ReD Shield两项服务，实现安全的外卡收款。网银在线专业的风险控制团队可以提供本地支持。"
	name=description>
<meta content="游戏支付平台|在线支付平台|支付平台|三方支付平台|传奇支付平台|自动支付平台|在线支付平台|第三方支付"
	name=keywords>
<base href="<%=basePath%>">

<link rel="stylesheet" type="text/css" href="css/defaultcss.css" />
<link rel="stylesheet" type="text/css" href="css/css.css" />
<link rel="stylesheet" type="text/css" href="css/slider.css" />
<link rel="stylesheet" type="text/css" href="css/reg.css" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.nbspSlider.1.0.min.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<script type="text/javascript" src="js/reg.js"></script>

<script type="text/javascript">
	$(document).ready(function(e) {
		$("#mbkcontent").floatdiv("middle");
		$(".menu_div_div:eq(2)").css({
			"background-image" : "url(images/sbgb.jpg)"
		}).attr("name", "a");
		$(".menu_div_div:eq(2) a").css("color", "#11638b");

	});
	$(document).ready(function() {
		flushValidateCode();//进入页面就刷新生成验证码
	});

	/* 刷新生成验证码 */
	function flushValidateCode() {
		var validateImgObject = document.getElementById("codeValidateImg");
		validateImgObject.src = "${pageContext.request.contextPath }/imageGen/getSysManageLoginCode.html?time="
				+ new Date();
	}
	/*校验验证码输入是否正确*/
	function checkImg(code) {
		var url = "${pageContext.request.contextPath}/checkimagecode";
		$.get(url, {
			"validateCode" : code
		}, function(data) {
			if (data == "ok") {
				alert("ok!")
			} else {
				alert("error!")
				flushValidateCode();
			}
		})
	}
</script>
</head>

<body>
	<script type="text/javascript" src="js/floatDiv.js"></script>

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


	<div id="Company_Content">
		<div
			style="margin-left: 10px; background-image: url(images/reg02.jpg);"
			class="zcx zc">
		</div>
		<div style="clear: left;"></div>
		<br>
		

		<form name="Form1" id="Form1" method="post" action="/Index_reg.html"
			onsubmit="return check()">
			<div
				style="width: 760px; height: 482px; margin: 0px auto; margin-top: 10px; display: none; background-image: url(images/regreg.gif)"
				id="regdiv">
				<div
					style="width: 100%; height: 39px; line-height: 39px; text-align: left; font-size: 15px; font-weight: bold;">
					如果您已经是会员，请直接&nbsp;<a href="/Index_dengluqy.html"
						style="color: #F00;">登录</a>。
				</div>

				<div style="width: 100%; height: 70px;"></div>
				<div class="regtitle reginput">
					账&nbsp;户&nbsp;名：<input type="text" name="UserName" id="UserName"
						style="width: 200px; height: 25px; vertical-align: middle; font-size: 20px; color: #06C;"
						class="inputtext">&nbsp;&nbsp;&nbsp;&nbsp;<span
						style="font-size: 13px; color: #666;">账户只能用Email邮箱地址</span>
					<div class="errordiv">账号不正确！</div>
				</div>


				<div class="regtitle reginput">
					登录密码：<input type="password" name="LoginPassWord" id="LoginPassWord"
						style="width: 200px; height: 25px; vertical-align: middle; font-size: 20px; color: #06C;"
						class="inputtext">&nbsp;&nbsp;&nbsp;&nbsp;<span
						style="font-size: 13px; color: #666;">登录密码为6-16位数字、字母、符号的组合</span>
					<div class="errordiv">密码不正确！</div>
				</div>


				<div class="regtitle reginput">
					确认密码：<input type="password" name="OkPassWord" id="OkPassWord"
						style="width: 200px; height: 25px; vertical-align: middle; font-size: 20px; color: #06C;"
						class="inputtext"> &nbsp;&nbsp;&nbsp;&nbsp;<span
						style="font-size: 13px; color: #666;">再次确认输入登录密码</span>
					<div class="errordiv">密码不正确！</div>
				</div>

				<div class="regtitle reginput">
					验&nbsp;证&nbsp;码：<input type="text" name="verify" id="verify"
						style="width: 100px; height: 25px; vertical-align: middle; font-size: 20px; color: #06C;"
						class="inputtext"> <img class="yzm" src=''
						style="vertical-align: middle; height: 32px; cursor: pointer;"
						onclick='flushValidateCode()' id="codeValidateImg"
						name="codeValidateImg" title="点击刷新验证码" />
					<div class="errordiv">验证码不正确！</div>
				</div>


				<div class="reginput" style="margin-top: 10px; text-align: center;">
					<input type="hidden" name="UserType" id="UserType"> <input
						type="hidden" name="SjUserID" id="SjUserID" value="0"><input
						type="image" src="images/gr_reg.gif"
						style="vertical-align: middle;">&nbsp;&nbsp; <a
						href="javascript:fanhui()" style="font-size: 15px; color: #069;">返
						回</a>
				</div>

			</div>
			<input type="hidden" name="__hash__"
				value="8074ac1302ab2a12eee7e62596fb0485_5643d710f88c6415fc13ace3dd5b8286" />
		</form>
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
					target="_blank"> <img src="images/a2.png" style="border: 0px"></a>
				<a href="http://www.365anfang.com/" target="_blank"><img
					src="images/a3.png" style="border: 0px"></a> <a
					href="http://huhehaote.cyberpolice.cn/" target="_blank"><img
					src="images/a4.png" style="border: 0px"></a> <a
					href="http://net.china.com.cn/" target="_blank"><img
					src="images/a5.gif" style="border: 0px;"></a> <a
					href="http://www.315online.com.cn/" target="_blank"><img
					src="images/a6.gif" style="border: 0px"></a>
			</div>
		</div>
		<br>
		<div class="dt"></div>
	</div>

	<!---------------------------------------------------------------------------------------------->
	</div>

</body>
</html>
