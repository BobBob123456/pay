<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改密码--国盛通管理系统</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/style.css" />
<script src="js/jquery.min.js"></script>
<!--背景图片自动更换-->
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<script src="css/layer/layer.js"></script>
<script type="text/javascript">
function do_forget_pwd() {
	var password=$('#password').val();
	var sure_password=$('#sure_password').val();
	var verify=$('#verify').val();
	var id=$('#id').val();
	var activate=$('#activate').val();
	if(password==''){
		$('#password-error').show();
		return;
	}else{
		$('#password-error').hide();
	}
	if(sure_password==''){
		$('#sure_password-error').show();
		return;
	}else{
		$('#sure_password-error').hide();
	}
	if(sure_password!=password){
		$('#msg-error').html("两次密码不相同").show();
		return;
	}else{
		$('#msg-error').show();
	}
	if(verify.length==0){
		$('#verify-error').show();
		return;
	}else{
		$('#verify-error').hide();
	}
	$("#btnsub").attr("disabled","disabled");
 	$.ajax({
		type : 'POST',
		url : "user/do_forget_pwd.html",
		data : {"verify": verify,"password":password,'id':id,"activate":activate},
		dataType : 'text',
		success : function(str) {
			str=eval("("+str+")");
			if (str.msg != "ok") {
				$('#msg-error').html(str.msg).show();
				$("#btnsub").removeAttr("disabled");
			} else {
				alert("修改成功！");
				location.href="<%=basePath%>user/login.html";
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
	 <form class="form-horizontal" name="loginForm" action="user/doLogin.html" method="post">
	 		 <c:if test="${status==0}">
	 			<script type="text/javascript">
	 			layer.alert("修改密码地址已失效，前往重新发送修改密码邮件?", function(index){
	 				 	location.href="user/forget_password.html";
	 				});   
	 			</script>
	 		</c:if>
	 	<div>
	 		<input type="hidden" name="id"  id="id" value="${id}"/>
	 		<input type="hidden" name="activate"  id="activate" value="${activate}"/>
			<input type="password" name="password" id="password" class="password" placeholder="新密码" autocomplete="off"/>
			<label id="password-error" class="error"  style="display: none;">新密码不能为空</label>
		</div>
		<div>
			<input type="password" name="sure_password" id="sure_password" class="sure_password" placeholder="确认新密码" autocomplete="off"/>
			<label id="sure_password-error" class="error"  style="display: none;">确认新密码不能为空</label>
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
		
		<button id="submit" type="button" style="margin-top: 10px;" onclick="return do_forget_pwd()">提交</button>
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