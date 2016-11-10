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
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/style.css" />
<script src="js/jquery.min.js"></script>
<!--背景图片自动更换-->
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<script type="text/javascript" src="js/reg.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		flushValidateCode();//进入页面就刷新生成验证码
	});
	//注册
	function toRegister()
	{
		//用户名
		var UserName = $("#UserName").val();
		if(UserName.length==0)
		{
			$('#username-error').show();
			return;
		}else{
			$('#username-error').hide();
		}
		var email=$('#Email').val();
		if(email.length==0)
		{
			$('#email-error').show();
			return;
		}else{
			$('#email-error').hide();
		}
		var reg = /\w+[@]{1}\w+[.]\w+/;
		if (reg.test(email)) {
			$('#email-error').hide();
		} else {
			$('#email-error').html("邮箱格式不正确").show();
			return;
		}
		
		//密码
		var pwd = $("#LoginPassWord").val();
		if (pwd.length >= 6 && pwd.length <= 16) {
			$('#LoginPassWord-error').hide();
		} else {
			$('#LoginPassWord-error').show();
			return;
		}
		
		//确认密码
		var pwd = $("#LoginPassWord").val();
		var okpwd = $("#OkPassWord").val();
		if (pwd.length >= 6 && pwd.length <= 16) {
			$('#OkPassWord-error').hide();
		} else {
			$('#OkPassWord-error').show();
			return;
		}
		if (pwd != okpwd) {
			$('#OkPassWord-error').html("两次输入的密码不一样").show();
			return;
		} else {
			$('#OkPassWord-error').hide();
		}
		//验证码
		var verify=$("#verify").val();
		if(verify.length==0){
			$('#verify-error').show();
			return;
		}
		else
		{
			$('#verify-error').hide();
		}
		
		
		$("#btnsub").html("发送中...");
		$("#btnsub").attr("disabled","disabled");
	 	$.ajax({
			type : 'POST',
			url : "user/doRegister.html",
			data : {"UserName": $("#UserName").val() ,"LoginPassWord": $("#LoginPassWord").val(),"verify": $("#verify").val(),"Email":email},
			dataType : 'text',
			success : function(str) {
				$("#btnsub").html("注册");
				if (str != "ok") {
					$('#msg-error').html(str).show();
					$("#next").removeAttr("disabled");
				} else {
					location.href = "<%=basePath%>user/succeedReg.html?uname="
							+ $("#UserName").val();
				}
			},
			error : function(str) {
				$("#btnsub").removeAttr("disabled");
				$("#btnsub").html("注册");
			}
		});
	}

	/* 刷新生成验证码 */
	function flushValidateCode() {
		var validateImgObject = document.getElementById("codeValidateImg");
		validateImgObject.src = "${pageContext.request.contextPath }/imageGen/getSysManageRegisterCode.html?time="
				+ new Date();
	}

	function checkOKPwd() {
		var pwd = $("#LoginPassWord").val();
		var okpwd = $("#OkPassWord").val();
		if (pwd != okpwd) {
			$("#OkPassWord").val("");
			$("#errOKPwd").css("display", "block");
		} else {
			$("#errOKPwd").css("display", "none");
		}
	}
	function checkEmail() {
		var email = $("#UserName").val();
		if (email.length == 0) {
			$("#errName").css("display", "block");
		}
		var reg = /\w+[@]{1}\w+[.]\w+/;
		if (reg.test(email)) {
			$("#errName").css("display", "none");
		} else {
			$("#UserName").val("");
			$("#errName").css("display", "block");
		}
	}
</script>
</head>
<body>
<div class="register-container">
	<h1>国盛通管理系统-个人注册</h1>
	<div class="connect">
		<p>注册提示：如果您已经是会员，请直接 <a href='index.html' style='color:red;width:auto;text-decoration:none;font-size:14px;'>登录</a>。</p>
	</div>
	<form action="" method="post" id="registerForm">
		<div>
			<input type="text" name="UserName" id="UserName" placeholder="用户名" autocomplete="off"/>
			<label id="username-error" class="error"  style="display: none;">请输入用户名</label>
		</div>
		<div>
			<input type="text" name="Email" id="Email" placeholder="邮箱" autocomplete="off"/>
			<label id="email-error" class="error"  style="display: none;">请输入您的邮箱</label>
		</div>
		<div>
			<input type="password" name="LoginPassWord" id="LoginPassWord" class="password" placeholder="输入密码"  />
			<label id="LoginPassWord-error" class="error"  style="display: none;">请输入密码</label>
		</div>
		<div>
			<input type="password" name="OkPassWord" id="OkPassWord" onblur="checkOKPwd();" class="confirm_password" placeholder="再次输入密码"  />
			<label id="OkPassWord-error" class="error"  style="display: none;">请再次输入密码</label>
		</div>
		<div>
			<input type="text" name="verify" id="verify" class="verify" placeholder="验证码"  style='width:160px;float:left;'/>
			<div><img class="yzm" src="" style="width:100px;border-radius:4px;height:40px; margin-top: 26px;cursor:pointer;" onclick='flushValidateCode()' id="codeValidateImg"
						name="codeValidateImg" title="点击刷新验证码"></div>
			<label id="verify-error" class="error"  style="display: none;text-align:center;clear: both;">请输入验证码</label>
		</div>
		<div>
			<label id="msg-error" class="error"  style="display: none;text-align:center;clear: both;margin-top:1rem;border-radius: 5px 5px 5px 5px;">请输入您的账户</label>
		</div>
		<button id="btnsub" type="button" style="margin-top: 10px;" onclick="return toRegister();">注 册</button>
	</form>
	<a href="user/login.html">
		<button type="button" class="register-tis" style='margin:15px auto;'>已经有账号？点此返回</button>
	</a>
</div>
</body>
</html>
