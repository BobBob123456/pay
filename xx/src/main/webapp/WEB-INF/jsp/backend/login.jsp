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
<link href="css/defaultcss.css" rel="stylesheet">
<link href="css/css.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/1.10.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="js/floatDiv.js"></script>
<script src="js/common.js"></script>
<script src="js/js.js"></script>
<script type="text/javascript">
$(document).ready(function() {
     flushValidateCode();//进入页面就刷新生成验证码
   });

/* 刷新生成验证码 */
function flushValidateCode(){
var validateImgObject = document.getElementById("codeValidateImg");
validateImgObject.src = "${pageContext.request.contextPath }/imageGen/getSysManageLoginCode.html?time=" + new Date();
}

function check() {
	$.ajax({
		type : 'POST',
		url : "backend/do_login.html",
		data : "UserName=" + $("#UserName").val() + "&LoginPassWord="
				+ $("#LoginPassWord").val() + "&verify=" + $("#verify").val(),
		dataType : 'text',
		success : function(str) {
			location.href = "user/user_index.html";
		},
		error : function(str) {
		}
	});
	return false;
}
</script>
</head>
<body>
 <form class="form-horizontal" name="Form1" method="post" action="" onsubmit="return check();">
        <input type="hidden" name="mbk" id="mbk" value="">
   <div id="dengludiv">
       <div id="dengludivtitle">&nbsp;&nbsp;后台登录</div>
       <div id="dengludivcontent">
            <div id="dengludivcontentleft">
                 
                <!-------------------------------------------------------------->
              <div style="width:90%;">
                  <div class="form-group">
                    <label for="UserName" class="col-sm-4 control-label" style="text-align:right; color:#3384ad">账户名：</label>
                    <div class="col-sm-8">
                      <input type="text" class="form-control" name="UserName" id="UserName" placeholder="请输入您注册填写的邮箱">
                    </div>
                  </div>
                 <div style="clear:left;"></div>
                  <div class="form-group" style="margin-top:20px;">
                    <label for="LoginPassWord" class="col-sm-4 control-label" style="text-align:right; color:#3384ad">密 码：</label>
                    <div class="col-sm-8">
                      <input type="password" class="form-control" name="LoginPassWord" id="LoginPassWord" placeholder="请输入登录密码">
                    </div>
                  </div>
                  <div style="clear:left;"></div>
                  <div class="form-group" style="margin-top:20px;">
                    <label for="verify" class="col-sm-4 control-label" style="text-align:right; color:#3384ad">验证码：</label>
                    <div class="col-sm-4">
                      <input type="text" class="form-control" name="verify" id="verify" placeholder="验证码">
                    </div>
                     <div class="col-sm-4">
                  
                      &nbsp;&nbsp;<img class="yzm" src='' style="vertical-align:middle; height:32px; cursor:pointer;" onclick='flushValidateCode()' id="codeValidateImg" name="codeValidateImg" title="点击刷新验证码" />
                    </div>
                  </div>
                 <div style="clear:left;"></div>
                  <div class="form-group" style="margin-top:20px;">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button type="submit" class="btn btn-primary">登录</button>&nbsp;&nbsp;&nbsp;&nbsp;<a href="user/doLogin" style="text-decoration:none; color:#3584c5; font-size:12px;">忘记密码?</a>
                    </div>
                  </div>
                </div>
            </div>
       </div>
   </div>
   </form>


</body>