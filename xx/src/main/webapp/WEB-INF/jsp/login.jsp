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
<meta  content="支付网银在线在外贸、航空等多个领域有着多年的外卡收款服务经验，服务客户包括海航等优质客户，商户可以通过单一接口接入MOTOpay和ReD Shield两项服务，实现安全的外卡收款。网银在线专业的风险控制团队可以提供本地支持。" name=description>
<meta content="游戏支付平台|在线支付平台|支付平台|三方支付平台|传奇支付平台|自动支付平台|在线支付平台|第三方支付" name=keywords>
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
$(document).ready(function(e) {
	$("#mbkcontent").floatdiv("middle");
    $(".menu_div_div:eq(2)").css({"background-image":"url(images/sbgb.jpg)"}).attr("name","a");
	$(".menu_div_div:eq(2) a").css("color","#11638b");
	
});
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

<!-- <include file="Index:Top" /> -->
 <form class="form-horizontal" name="Form1" method="post" action="" onsubmit="return check();">
        <input type="hidden" name="mbk" id="mbk" value="">
   <div id="dengludiv">
       <div id="dengludivtitle">&nbsp;&nbsp;登录</div>
       <div id="dengludivts">
          <div style="width:60px; height:60px; float:left; text-align:center;"><img src="images/dengludivts.gif" style="margin-top:15px;"></div>
          <div style="width:auto; height:60px; font-size:13px;">
            <p style="margin-top:5px; line-height:20px;"><b>登录提示</b></p>
            <p style="line-height:10px;">您还没有登录，无法使用相关支付功能，请在下面进行登录！</p>
          </div>
       </div>
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
                <!-------------------------------------------------------------->
           
            </div>
            <div id="dengludivcontentright">
                <button type="button" class="btn btn-default" onclick="javascript:window.location.href='user/doLogin.html'">免费注册</button><br><br>
          		<button type="button" class="btn btn-default" onclick="javascript:window.location.href=''">账号激活</button><br><br>
              <!--  <button type="button" class="btn btn-primary" onclick="javascript:window.location.href=''">代理登录</button> -->
            </div>
       </div>
   </div>
   <input type="hidden" name="UserTpye" id="UserType" value="2" />
   </form>
   <div id="mbkcontent">
</div>
<!-- <include file="Index:bottom" /> -->
</body>