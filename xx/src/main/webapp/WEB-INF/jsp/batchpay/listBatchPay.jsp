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
<script type="text/javascript" src="js/jqPaginator.js"></script>
<script type="text/javascript" src="User/js/wyjyjl.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
	flushValidateCode();//进入页面就刷新生成验证码
	var totalPage=${totalPage};
	var currentPage=${currentPage};
	if(totalPage!=0){
		$('#page_div').jqPaginator({
		    totalPages:totalPage,
		    visiblePages: 5,
		    currentPage: currentPage,
		    onPageChange: function (num, type) {
		    	if(currentPage!=num){
		    		location.href="batchPay/listBatchPay.html?cur="+num+"&batchId=${batchId}";
		    	}
		    }
		});
	}
});

/* 刷新生成验证码 */
function flushValidateCode(){
var validateImgObject = document.getElementById("codeValidateImg");
validateImgObject.src = "${pageContext.request.contextPath }/imageGen/getSysManageLoginCode.html?time=" + new Date();
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
						<li>批量付款</li>
						<li class="last">批量付款明细列表</li>
					</ul>
				</div>
			</nav>
			<div style='clear:both;'></div>
				<div class='ztab'>
					<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 116px;">银行</th>
							<th style="width: 179px;">卡号</th>
							<th style="width: 179px;">姓名</th>
							<th style="width: 240px;">金额（元）</th>
						</tr>
					</thead>
					<tbody>
					  <c:forEach items="${list}" var="vo">  
						<tr>
							<td>${vo.bank}</td>
							<td>${vo.card}</td>
							<td>${vo.name }</td>
							<td>${vo.money}</td>
						</tr>
					 </c:forEach> 
					</tbody>
				</table>
				</div>
		</div>
	</div>
 	<div  style="text-align:center;">
 		<ul class="pagination" style="text-align:center;margin-bottom: 10px;" id="page_div" >
    
      </ul>
	</div>
 <div class="selectclass">
 	<div style="width:15%;float:left;text-align:right;">结算订单笔数：</div>
 	<div style="width:85%;float:left;">${total} 笔</div>
 	
 </div>
  <div class="selectclass">
   	<div class="ldiv">总金额：</div>
 	<div class="rdiv">${totalMoney}</div>
 </div>
  <div class="selectclass">
  	<div class="ldiv">安全码：</div>
 	<div class="rdiv"><input type="password" class="form-control" id="code" name="code" style="width: 10%;margin-top:8px;"/></div>
 </div>
  <div class="selectclass" style="text-align:left;">
  	<div class="ldiv">验证码：</div>
 	<div style="width:10%;float:left;"><input type="text" class="form-control" id="code" name="code" style="width: 90%;margin-top:8px;"/></div>
 	<div style="width:10%;float:left;"><img class="yzm" src='' style="vertical-align:middle; height:32px;margin-top:8px; cursor:pointer;" onclick='flushValidateCode()' id="codeValidateImg" name="codeValidateImg" title="点击刷新验证码" /></div>
 </div>
  <div class="selectclass" style="text-align:left;clear: both;">
 	 <input type="submit" value="提交" class="btn btn-primary btn-search" style="margin-left:18%"/>
	 <a class="btn btn-info btn-reset" style='margin:0 -5px 0 0;'> 重 置</a>
 </div>

<div style="clear:left;"></div>
</body>
</html>