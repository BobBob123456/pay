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
<script type="text/javascript" src="User/js/jquery-1.7.2.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="js/jqPaginator.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(
			function(e) {
				var totalPage = ${totalPage};
				var currentPage = ${currentPage};
				if(totalPage!=0){
					$('#page_div').jqPaginator({
						totalPages : totalPage,
						visiblePages : 5,
						currentPage : currentPage,
						onPageChange : function(num, type) {
							if (currentPage != num) {
								location.href = "user/tjyg.html?cur=" + num;
							}
						}
					});
				}
			}
		);
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
						<li>代理管理</li>
						<li class="last">下级商户</li>
					</ul>
				</div>
			</nav>
			<div style='clear:both;'></div>
				<input type="hidden" id="isSearch" name="isSearch" value="0">
				<div class='ztab'>
					<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 116px;">商户号</th>
							<th style="width: 179px;">用户名</th>
							<th style="width: 179px;">收款费率</th>
							<th style="width: 179px;">结算费率</th>
							<th style="width: 240px;">建立时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="vo">
							<tr>
								<td>${vo.id+10000}</td>
								<td>${vo.username}</td>
								<td>
									<c:choose>
							      		<c:when test="${!empty vo.sjfl.chequeFl}">
							      			${vo.sjfl.chequeFl}
							      		</c:when>
							      		<c:otherwise>
							      			${vo.sjapi.fl}
							      		</c:otherwise>
							      	</c:choose>
								</td>
								<td>
									<c:choose>
							      		<c:when test="${!empty vo.sjfl.statementFl}">
							      			${vo.sjfl.statementFl}/保底${vo.sjfl.statementBd}元/封顶${vo.sjfl.statementFd}元
							      		</c:when>
							      		<c:otherwise>
							      			${sjfl_obj.statementFl}/保底${sjfl_obj.statementBd}元/封顶${sjfl_obj.statementFd}元
							      		</c:otherwise>
							      	</c:choose>
								</td>
								<td><fmt:formatDate value="${vo.regdate}"
										pattern="yyyy/MM/dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>		
		</div>
	</div>
	<div class="selectclass" style="text-align: center;">
		<ul class="pagination"
			style="text-align: center; margin-bottom: 10px;" id="page_div">

		</ul>
	</div>
</body>
</html>