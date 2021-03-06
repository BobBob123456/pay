<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>账户登录--国盛通管理系统</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/style.css" />
<script src="js/jquery.min.js"></script>
<!--背景图片自动更换-->
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<script type="text/javascript">
function forget_pwd() {
	var email=$('#email').val();
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
	
	var verify=$('#verify').val();
	if(verify.length==0)
	{
		$('#verify-error').show();
		return;
	}else{
		$('#verify-error').hide();
	}
	
	$("#btnsub").html("发送中...");
	$("#btnsub").attr("disabled","disabled");
 	$.ajax({
		type : 'POST',
		url : "user/sendPwdEmail.html",
		data : {"verify": verify,"email":email},
		dataType : 'text',
		success : function(str) {
			str=eval("("+str+")");
			$("#btnsub").html("提交");
			if (str.msg != "ok") {
				$('#msg-error').html(str.msg).show();
				$("#btnsub").removeAttr("disabled");
			} else {
				alert("已向您的邮箱发送修改密码邮件，注意查收！");
				//$('#msg-error').html("").show();
				$("#btnsub").removeAttr("disabled");
			}
		},
		error : function(str) {
			$("#btnsub").html("提交");
		}
	});
 	return false;
}
$(document).ready(function() {
     flushValidateCode();//进入页面就刷新生成验证码
   });
/* 刷新生成验证码 */
function flushValidateCode(){
var validateImgObject = document.getElementById("codeValidateImg");
validateImgObject.src = "${pageContext.request.contextPath }/imageGen/getSysManageLoginCode.html?time=" + new Date();
}
</script>
</head>
<body>
<div class="login-container">
	<h1>国盛通管理系统-忘记密码</h1>
	<div class="connect">
		<p>忘记密码提示：根据你注册时输入的邮箱，可找回密码。</p>
	</div>
	 <form class="form-horizontal" name="loginForm" action="user/sendPwdEmail.html" method="post">
	 	<div>
			<input type="text" name="email" id="email" class="email" placeholder="请输入您注册填写的邮箱" autocomplete="off"/>
			<label id="email-error" class="error"  style="display: none;">请输入您注册填写的邮箱</label>
		</div>
		
		<div>
			<input type="text" id="verify"  name="verify" class="verify" placeholder="验证码"  style='width:160px;float:left;'/>
			<div><img class="yzm" src="" style="width:100px;border-radius:4px;height:40px; margin-top: 26px;cursor:pointer;" onclick='flushValidateCode()' id="codeValidateImg"
						name="codeValidateImg" title="点击刷新验证码"></div>
			<label id="verify-error" class="error"  style="display: none;text-align:center;clear: both;">请输入验证码</label>
		</div>
		<div>
			<label id="msg-error" class="error"  style="display: none;text-align:center;clear: both;margin-top:1rem;border-radius: 5px 5px 5px 5px;">请输入您的账户</label>
		</div>
		<button id="btnsub" type="button" style="margin-top: 10px;" onclick="return forget_pwd()">提交</button>
	</form>
	<a href="user/login.html">
		<button type="button" class="register-tis" style='width:96px;'>账号登陆</button>
	</a>
	<a href="user/register.html">
		<button type="button" class="register-tis" style='width:96px;'>免费注册</button>
	</a>
	<a href="user/go_activate.html">
		<button type="button" class="register-tis" style='width:96px;'>账号激活</button>
	</a>
</div>
</body>
</html>