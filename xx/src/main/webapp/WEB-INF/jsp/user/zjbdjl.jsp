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
<script type="text/javascript" src="User/js/wyjyjl.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) {
						var totalPage=${totalPage};
						var currentPage=${currentPage};
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
						
						$("#banklist").floatdiv("middle");
						$("#menu div").addClass("menu_bg_y");
						$("#menu div:eq(2)").addClass("menu_bg");
						$("#menu_x > div > div:eq(12)").css("background-image",
								"url(User/images/menumenu.gif)");
						$("#menu_x > div > div:eq(12) a").css("color", "#F60");
					});
</script>
<link href="css/table.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="../common/top.jsp"></jsp:include>

	<div class="xgjcxx">
		<div
			style="border: 1px solid #ccc; background-image: url(User/images/menu_bg_x.jpg); width: 100%; height: 40px; line-height: 40px; font-size: 15px; text-align: left; font-weight: bold; color: #333">
			&nbsp;&nbsp;&nbsp;&nbsp;资金变动记录</div>

		<div
			style="width: 100%; height: auto; border: 1px solid #CCC; border-top: 0px;"
			id="ntj">


			<div id="txcontent">
				<form action="user/income_expenses_detail.html" method="post">
					<div class="selectclass"
						style="font-size: 12px; text-align: center; border-bottom: 0px;">
						<input type="text" name="ksjy_date" id="ksjy_date" class="Wdate"
							value="${ksjy_date}"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})"
							style="width: 150px;">
						&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
							name="jsjy_date" id="jsjy_date" value="${jsjy_date}" class="Wdate"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})"
							style="width: 150px;"> 类型： <select name="lx" id="lx">
							<option value="">全部类型</option>
							<option value="1">网银交易</option>
							<option value="2">提成记录</option>
							<option value="4">提款记录</option>
							<option value="5">减金记录</option>
							<option value="6">增金记录</option>
						</select> &nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" value="搜 索"
							id="SearchButton" style="line-height: normal;">
						<script type="text/javascript">
							$("#lx").val(${status});
						</script>
					</div>
				</form>
				<div class="selectclass"
					style="height: auto; overflow: hidden; border-bottom: 0px;">
					<table cellpadding="0" cellspacing="0" border="0" id="listuser">
						<tr style="background-color: #5d7b9d; color: #fff;">

							<td style="width: 30%">交易时间</td>
							<td>原金额</td>
							<td>变动金额</td>
							<td>变动后金额</td>
							<td style="width: 10%;">交易类型</td>
						</tr>
						<c:forEach var="vo" items="${list}">
							<tr>
								<td style="font-size: 18px; width: 30%;">
								<fmt:formatDate value="${vo.datetime}" pattern="yyyy/MM/dd HH:mm:ss" />
									&nbsp;</td>
								<td style="font-size: 20px; font-weight: bold; color: #060;">${vo.ymoney}</td>
								<td style="font-size: 20px; font-weight: bold;">
								<c:choose>
									<c:when test="${vo.money>0}">
										 <span style="color: #F00">+${vo.money} </span>
									</c:when>
									<c:otherwise>
										<span style="color: #F00">${vo.money} </span>
									</c:otherwise>
								</c:choose>
								</td>
								<td style="font-size: 20px; font-weight: bold; color: #66F">${vo.gmoney}</td>
								<td style="width: 10%;">
									<c:if test="${vo.lx==1}">
										网银交易
									</c:if>
									<c:if test="${vo.lx==2}">
										提成记录
									</c:if>
									<c:if test="${vo.lx==4}">
										提款记录
									</c:if>
									<c:if test="${vo.lx==5}">
										减金记录
									</c:if>
									<c:if test="${vo.lx==6}">
										增金记录
									</c:if>
								</td>
							</tr>
					 	</c:forEach>
					</table>
				</div>

			</div>
		</div>
	</div>
	<div class="selectclass" style="text-align:center;">
		<ul class="pagination" style="text-align:center;margin-bottom: 10px;" id="page_div" >
			    
	    </ul>
	</div>
	<div style="clear: left;"></div>
</body>
</html>