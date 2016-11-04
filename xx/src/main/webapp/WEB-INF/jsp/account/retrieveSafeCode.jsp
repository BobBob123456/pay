<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript">
function check(){

}

</script>
<style type="text/css">
.selectclass{
	width:96%;
	height:50px;
	line-height:50px;
	text-align:center;
	text-align:left;
	font-size:15px;
	margin:0px auto;
	border-bottom:1px dashed #999999;
}
.selectclass .ldiv{width:15%;float:left;text-align:right;}
.selectclass .rdiv{width:85%;float:left;}
</style>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="biaoge">
		<div class="main_content">
			<nav>
				<div class='znav'>
					<ul style="width: 5000px;">
						<li class="first"><a href=""><i class="icon-home"></i></a></li>
						<li>账号管理</li>
						<li class="last">找回安全码</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
<form name="Form2" method="post" action="account/EditLoginPassWord.html" onsubmit="return check2()">
	  <div class="selectclass">
	  	<div class="ldiv">注册邮箱：</div>
	 	<div class="rdiv">
	 		<input type="text"  id="" name=""  class="form-control" style="width: 15%;margin-top:8px;display: inline;"/>
	 		<span style="color:red;">请输入注册时输入的邮箱，否则无法修改</span>
	 	</div>
	 </div>
	  <div class="selectclass" style="text-align:left;clear: both;">
	 	 <input type="submit" value="提交" class="btn btn-primary btn-search" style="margin-left:18%"/>
		 <a class="btn btn-info btn-reset" style='margin:0 -5px 0 0;'> 重 置</a>
	 </div>
</form>
<div style="clear:left;"></div>
</body>
</html>