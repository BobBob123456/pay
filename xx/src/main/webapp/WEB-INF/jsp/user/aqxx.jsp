<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国盛通管理系统</title>
<base href="<%=basePath%>">
<link href="User/css/css.css" rel="stylesheet">
<script type="text/javascript" src="User/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="User/js/js.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
    $("#menu div").addClass("menu_bg_y");
	$("#menu div:eq(1)").addClass("menu_bg");
	$("#menu_x > div > div:eq(2)").css("background-image","url(User/images/menumenu.gif)");
	$("#menu_x > div > div:eq(2) a").css("color","#F60");
});

function check1(){
	if($("#AffirmTitle").val() == ""){
		alert("安全提问不能为空！");
		return false;
	}else{
		if($("#AffirmAnswer").val() == ""){
			alert("密码回答不能为空！");
			return false;
		}else{
			if(confirm("您确认要设置安全提问与回答吗？设置后不能修改且安全回答不会在后台显示，设置前请务必牢记您设置的提问与回答！") == true){
				return true;
			}else{
				return false;
			}
		}
	}
}

function check2(){
	if($("#Y_LoginPassWord").val() == ""){
		alert("原登录密码不能为空！");
		$("#Y_LoginPassWord").focus();
		return false;
	}else{
		if($("#X_LoginPassWord").val() == ""){
			alert("新密码不能为空！");
			$("#X_LoginPassWord").focus();
			return false;
		}else{
			if($("#X_LoginPassWord").val() != $("#XX_LoginPassWord").val()){
				alert("两次新密码输入不一致！");
				return false;
			}else{
				if(confirm("你确认要修改登录密码吗？") == true){
					return true;
				}else{
					return false;
				}
			}
		}
	}
}

function check3(){
	if($("#Y_PayPassWord").val() == ""){
		alert("原支付密码不能为空！");
		$("#Y_PayPassWord").focus();
		return false;
	}else{
		if($("#X_PayPassWord").val() == ""){
			alert("新密码不能为空！");
			$("#X_PayPassWord").focus();
			return false;
		}else{
			if($("#X_PayPassWord").val() != $("#XX_PayPassWord").val()){
				alert("两次新密码输入不一致！");
				return false;
			}else{
				if(confirm("你确认要修改支付密码吗？") == true){
					return true;
				}else{
					return false;
				}
			}
		}
	}
}
</script>
<style type="text/javascript">
.jbxx input, .jbxx img{
	vertical-align:middle;
	}
</style>
</head>

<body>

<jsp:include page="../common/top.jsp"></jsp:include>

<!-------------------------------------------------安全信息-------------------------------------------------------->
<div class="xgjcxx">
<div style="border:1px solid #ccc; background-image:url(User/images/menu_bg_x.jpg); width:1000px; height:40px; line-height:40px; font-size:15px; text-align:left; font-weight:bold; color:#333">
&nbsp;&nbsp;&nbsp;&nbsp;安全信息
</div>
<div style="width:1000px; height:auto; border:1px solid #CCC; border-top:0px">
    <form name="Form1" action="user/anquantiwen.html" method="post" onsubmit="return check1()">
    <div class="jbxx" style="text-align:center;">
      修改安全提问与回答 <input type="hidden" class="input_text" name="id" id="id" value="${info.id}" >
    </div>
     <div class="jbxx" style="text-align:center; font-size:13px; color:#03C;">
      安全提问与回答 是您忘记登录密码时找回登录密码的凭证
    </div>
    <div class="jbxx">
    安全提问：
    <c:if test="${empty info.affirmtitle }">
    	  <input type="text" class="input_text" name="AffirmTitle" id="AffirmTitle" style="width:200px;">
    	&nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">设置后不能修改！</span>
    </c:if>
    <c:if test="${!empty info.affirmtitle }">
    	  <span style="font-size:15px; font-weight:bold; color:#000">${info.affirmtitle}</span>
    </c:if>
    </div>
    <div class="jbxx">
    安全回答：
    
   <c:if test="${empty info.affirmanswer }">
    	  <input type="text" class="input_text" name="AffirmAnswer" id="AffirmAnswer" style="width:200px;">
    &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">设置后不能修改且不会显示，所以设置前请用其它方式记录下您设置的安全提问与回答</span>
    </c:if>
    <c:if test="${!empty info.affirmanswer }">
    	<span style="font-size:20px; font-weight:bold; color:#F00">**********</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--<a href="#" style="font-weight:normal;">忘记安全回答</a>-->
    </c:if>
    </div>
    <c:if test="${empty info.affirmanswer }">
    	 <div class="jbxx" style="text-align:center; height:50px;">
        <input type="image" src="User/images/qrxg.gif" style="vertical-align:middle">
      	&nbsp;&nbsp;&nbsp;&nbsp;
   	 	<img src="User/images/chongzhi.gif" onclick="javascript:document.Form1.reset()" style="vertical-align:middle; cursor:pointer;">  
    </div>
    </c:if>
    </form>

    <div class="jbxx"></div>
    <form name="Form2" method="post" action="user/EditLoginPassWord.html" onsubmit="return check2()">
    <div class="jbxx" style="text-align:center;">
      修改登录密码
    </div>
     <div class="jbxx" style="text-align:center; font-size:13px; color:#03C;">
      登录密码 是您登录管理平台时使用的密码！
    </div>
    <div class="jbxx">
    输入登录密码(原)：<input type="password" class="input_text" name="Y_LoginPassWord" id="Y_LoginPassWord">
    &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">您正在使用的登录密码。</span>
    </div>
    <div class="jbxx">
    输入登录密码(新)：<input type="password" class="input_text" name="X_LoginPassWord" id="X_LoginPassWord">
    &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">您要设置的新的登录密码。</span>
    </div>
    <div class="jbxx">
    确认登录密码(新)：<input type="password" class="input_text" name="XX_LoginPassWord" id="XX_LoginPassWord">
    &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">请再次输入你要设置的新的登录密码。</span>
    </div>
   
   
    <div class="jbxx" style="text-align:center; height:50px;">
  
       
       <input type="image" src="User/images/qrxg.gif" style="vertical-align:middle">
      &nbsp;&nbsp;&nbsp;&nbsp;
    <img src="User/images/chongzhi.gif" onclick="javascript:document.Form2.reset()" style="vertical-align:middle; cursor:pointer;">  
    </div>
    </form>
   
     <div class="jbxx"></div>
   <form name="Form3" method="post" action="user/EditPayPassWord.html" onsubmit="return check3()">
    <div class="jbxx" style="text-align:center;">
      修改支付密码
    </div>
    <div class="jbxx" style="text-align:center; font-size:13px; color:#03C;">
      支付密码 是您进行付款或收款时使用的密码
    </div>
    <div class="jbxx">
    输入支付密码(原)：<input type="password" class="input_text" name="Y_PayPassWord" id="Y_PayPassWord" />
    &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">您正在使用的支付密码。（<span style="color:#1A2179">初始支付密码为123456</span>）</span>
    </div>
     <div class="jbxx">
      输入支付密码(新)：<input type="password" class="input_text" name="X_PayPassWord" id="X_PayPassWord" />
      &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">您要设置的新的支付密码。</span>
     </div>
     <div class="jbxx">
      确认支付密码(新)：<input type="password" class="input_text" name="XX_PayPassWord" id="XX_PayPassWord" />
     &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">请再次确认你要设置的新的支付密码。</span>
     </div>
    
     <div class="jbxx" style="text-align:center; height:50px;">
        
       <input type="image" src="User/images/qrxg.gif" style="vertical-align:middle">
      &nbsp;&nbsp;&nbsp;&nbsp;
    <img src="User/images/chongzhi.gif" onclick="javascript:document.Form3.reset()" style="vertical-align:middle; cursor:pointer;">  
    </div>
    </form>
</div>
</div>
<!-------------------------------------------------安全信息-------------------------------------------------------->



</body>
</html>