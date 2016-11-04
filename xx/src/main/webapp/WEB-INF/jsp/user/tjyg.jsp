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
<script type="text/javascript" src="User/js/js.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(
			function(e) {
				var totalPage = ${totalPage};
				var currentPage = ${currentPage;
				if(totalPage!=0){
					$('#page_div').jqPaginator({
						totalPages : totalPage,
						visiblePages : 5,
						currentPage : currentPage,
						onPageChange : function(num, type) {
							if (currentPage != num) {
								location.href = "user/wyjyjl.html?cur=" + num;
							}
						}
					});
				}
			}
		});

	function tjsh() {
		if (confirm("请确保完善了基本资料后再提交审核，提交审核后不能再修改基本信息！") == true) {
			location.href = "/User_Index_shjksh.html";
		}
	}
</script>
<style type="text/css">
#yggl {
	width: 100%;
	height: auto;
}

#yggl tr td {
	width: 10%;
	height: 40px;
	text-align: left;
	padding-left: 10px;
	vertical-align: middle;
	font-size: 13px;
}
</style>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
	<!-------------------------------------------------基本信息-------------------------------------------------------->
	<div class="xgjcxx">
		<div
			style="border: 1px solid #ccc; background-image: url(User/images/menu_bg_x.jpg); width: 100%; height: 40px; line-height: 40px; font-size: 15px; text-align: left; font-weight: bold; color: #333">
			&nbsp;&nbsp;&nbsp;&nbsp;下级管理</div>
		<div
			style="width: 100%; height: auto; border: 1px solid #CCC; border-top: 0px">
			<table border="0" cellpadding="0" cellspacing="0"
				style="width: 100%;" id="yggl">
				<tr>
					<td style="font-weight: bold; border-bottom: 1px solid #CCC;">商户号</td>
					<td style="font-weight: bold; border-bottom: 1px solid #CCC;">用户名</td>
					<td style="font-weight: bold; border-bottom: 1px solid #CCC;">费率</td>
					<td style="font-weight: bold; border-bottom: 1px solid #CCC;">建立时间</td>
				</tr>
				<c:forEach items="${users}" var="vo">
					<tr>
						<td>${vo.id+10000}</td>
						<td>${vo.username}</td>
						<td></td>
						<td><fmt:formatDate value="${vo.regdate}"
								pattern="yyyy/MM/dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="selectclass" style="text-align: center;">
		<ul class="pagination"
			style="text-align: center; margin-bottom: 10px;" id="page_div">

		</ul>
	</div>
</body>
</html>