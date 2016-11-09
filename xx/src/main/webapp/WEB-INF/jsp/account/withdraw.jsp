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
<script src="css/layer/layer.js"></script>
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
						<li class="last">提现</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
<form name="Form2" method="post" action="account/EditLoginPassWord.html" onsubmit="return check2()">
	  <div class="selectclass">
	  	<div class="ldiv">提现银行卡：</div>
	 	<div class="rdiv">
	 		<c:if test="${empty bank.bankaccountnumber}">
	 			<script type="text/javascript">
	 			layer.alert('银行卡未设置，前往设置银行?', function(index){
	 				 	location.href="account/cardSet.html";
	 				});       
	 			/* layer.msg('银行卡未设置，前往设置银行', {
	 				 shadeClose: true,
	 				 time: 0 //不自动关闭
	 				  ,btn: ['去设置']
	 				  ,yes: function(index){
	 				    layer.close(index);
	 				    
	 				  }
	 			}); */
	 				
	 			</script>
	 		</c:if>
	 		<c:if test="${!empty bank.bankaccountnumber}">
	 			${bank.bankaccountnumber}
	 		</c:if>
	 	</div>
	 </div>
	 <div class="selectclass">
	  	<div class="ldiv">体现金额(元)：</div>
	 	<div class="rdiv"><input type="text"  id="money" name="money" class="form-control"  style="width: 15%;margin-top:8px;"/></div>
	 </div>
	   <div class="selectclass">
	  	<div class="ldiv">安全码：</div>
	 	<div class="rdiv"><input type="text" class="form-control" id="code" name="code" style="width: 15%;margin-top:8px;"/></div>
	 </div>
	  <div class="selectclass" style="text-align:left;clear: both;">
	 	 <input type="submit" value="提交" class="btn btn-primary btn-search" style="margin-left:18%"/>
		 <a class="btn btn-info btn-reset" style='margin:0 -5px 0 0;'> 重 置</a>
	 </div>
</form>
<div style="clear:left;"></div>
</body>
</html>