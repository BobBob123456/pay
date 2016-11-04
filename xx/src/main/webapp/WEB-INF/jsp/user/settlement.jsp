<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>国盛通管理系统</title>
<base href="<%=basePath%>">
<link href="User/css/css.css" rel="stylesheet">
<script type="text/javascript" src="User/js/jquery-1.7.2.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="js/jqPaginator.js"></script>
<script type="text/javascript" src="js/floatDiv.js"></script>
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
				    		location.href="user/income_expenses_detail.html?cur="+num;
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
						<li>我的结算</li>
						<li class="last">银行卡结算</li>
					</ul>
				</div>
			</nav>
			<div style='clear:both;'></div>
			<form action="user/settlement.html" method="post">
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
	<!-- 				<div class="input-group" style='width:214px;float:left;font-size:13px;margin:0 5px;'>
						<div class="input-group-addon">流水号</div>
						<input type="text" class="form-control">
					</div> -->
					<div class="input-group" style='width:214px;float:left;font-size:13px;'>
						<div class="input-group-addon">状态</div>
						 <select class="form-control" name="zt" id="zt">
							<option value="">全部</option>
							<option value="0">未处理</option>
							<option value="2">已打款</option>
							<option value="3">失败</option>
						</select>
						<script type="text/javascript">
							$("#zt").val(${zt});
						</script>
					</div>
					<div class="pull-right">
						<input type="submit" value="查 询"  class="btn btn-primary btn-search"/>
						<a class="btn btn-info btn-reset" style='margin:0 -5px 0 0;'> 重 置</a>
					</div>
				</div>
				<!--时间轴e-->
				<div style="text-align: center;">订单笔数：${total}|成功金额：${success_money}|成功笔数:${success_count}</div>
				<div class='ztab'>
					<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 116px;">结算订单号</th>
							<th style="width: 179px;">姓名</th>
							<th style="width: 179px;">卡号</th>
							<th style="width: 240px;">金额（元）</th>
							<th style="width: 149px;">订单状态</th>
							<th style="width: 149px;">建立时间</th>
							<th style="width: 87px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="vo" items="${list}">
							<tr>
								<td></td>
								<td>${vo.myname}</td>
								<td>${vo.bankNumber}</td>
								<td>￥${vo.tkMoney}</td>
								<td>
									<c:choose>
										<c:when test="${vo.zt==0}">
											<span style="color: #F00">未处理</span>
										</c:when>
										<c:when test="${vo.zt==1}">
											正在处理中
										</c:when>
										<c:when test="${vo.zt==2}">
											已打款
										</c:when>
										<c:otherwise>
											失败
										</c:otherwise>
									</c:choose>
								</td>
								<td><fmt:formatDate value="${vo.sqDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</form>
		</div>
	</div>
	<div style="text-align:center;">
		<ul class="pagination" style="text-align:center;margin-bottom: 10px;" id="page_div" >		    
		</ul>
	</div>
</body>
</html>