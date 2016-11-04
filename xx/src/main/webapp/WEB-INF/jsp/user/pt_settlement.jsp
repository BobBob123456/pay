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
		var totalPage=${totalPage};
		var currentPage=${currentPage};
		if(totalPage!=0){
			$('#page_div').jqPaginator({
			    totalPages:totalPage,
			    visiblePages: 5,
			    currentPage: currentPage,
			    onPageChange: function (num, type) {
			    	if(currentPage!=num){
			    		location.href="user/wyjyjl.html?cur="+num;
			    	}
			    }
			});
		}
	});
</script>

</head>

<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
		<div class="biaoge">
		<div class="main_content">
			<nav>
				<div class='znav'>
					<ul style="width: 5000px;">
						<li class="first"><a href=""><i class="icon-home"></i></a></li>
						<li>我的收款</li>
						<li class="last">账户发付款</li>
					</ul>
				</div>
			</nav>
			<div style='clear:both;'></div>
			<form action="" method="post" name="searchForm" id="searchForm">
				<input type="hidden" id="isSearch" name="isSearch" value="0">
				<!--时间轴s-->
				<div class='ztime'>					
					<div class="input-group" style='width:290px;float:left;font-size:13px;margin:0 5px 0 -5px;'>
						<div class="input-group-addon">开始时间</div>
						<input type="text" class="form-control" name="ksjy_date" id="ksjy_date"  value="${ksjy_date}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})">
						<div class="input-group-addon"><span class="glyphicon glyphicon-search"></span></div>
					</div>
					
					<div class="input-group" style='width:290px;float:left;font-size:13px;'>
						<div class="input-group-addon">结束时间</div>
						<input type="text" class="form-control" name="jsjy_date" id="jsjy_date" value="${jsjy_date}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})">
						 <div class="input-group-addon"><span class="glyphicon glyphicon-search"></span></div> 
					</div>
					<div class="input-group" style='width:290px;float:left;font-size:13px;margin:0 5px;'>
						<div class="input-group-addon">流水号</div>
						<input type="text" class="form-control">
					</div>
					<div class="pull-right">
						<a class="btn btn-primary btn-search"> 查 询 </a> 
						<a class="btn btn-info btn-reset" style='margin:0 -5px 0 0;'> 重 置</a>
					</div>
				</div>
				<!--时间轴e-->
				<div class='ztab'>
					<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 116px;">订单号</th>
							<th style="width: 149px;">交易账户</th>
							<th style="width: 179px;">交易金额（元）</th>
							<th style="width: 149px;">交易时间</th>
							<th style="width: 87px;">备注</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="vo">  
							<tr>
								<td></td>
								<td></td>
								<td>${vo.ordermoney}</td>
								<td><fmt:formatDate value="${vo.tradedate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</form>
		</div>
	</div>
 <div class="selectclass" style="text-align:center;">
 		<ul class="pagination" style="text-align:center;margin-bottom: 10px;" id="page_div" >
    
      </ul>
 </div>
</body>
</html>
