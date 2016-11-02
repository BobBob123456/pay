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
		 $("#menu div").addClass("menu_bg_y");
		 $("#menu div:eq(2)").addClass("menu_bg");
		 $("#menu_x > div > div:eq(4)").css("background-image",
						"url(User/images/menumenu.gif)");
		$("#menu_x > div > div:eq(4) a").css("color", "#F60");
	});
</script>
<style type="text/css">
#txtitle {
	width: 800px;
	height: 50px;
	margin: 0px auto;
	text-align: center;
}

#txtitle div {
	width: 800px;
	height: 30px;
	font-weight: bold;
	font-size: 20px;
	color: #06C;
	text-align: center;
	line-height: 30px;
	border: 1px solid #999;
	border-bottom: 0px;
	cursor: pointer;
	margin: 0px auto;
}

#txcontent {
	width: 100%;
	height: auto;
}

.selectclass {
	width: 100%;
	height: 50px;
	line-height: 50px;
	text-align: center;
	text-align: left;
	font-size: 15px;
	margin: 0px auto;
	border-bottom: 1px dashed #999999;
}

.selectclass table {
	width: 100%;
	height: 50px;
}

.selectclass table tr td {
	width: 20%;
	height: 50px;
	text-align: center;
	vertical-align: middle;
	font-size: 12px;
}

.selectclass a {
	font-size: 13px;
	color: #333;
}

.selectclass a:hover {
	
}
</style>
</head>

<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="xgjcxx">
		<div
			style="border: 1px solid #ccc; background-image: url(User/images/menu_bg_x.jpg); width: 100%; height: 40px; line-height: 40px; font-size: 15px; text-align: left; font-weight: bold; color: #333">
			&nbsp;&nbsp;&nbsp;&nbsp;平台转账记录</div>

		<div
			style="width: 100%; height: auto; border: 1px solid #CCC; border-top: 0px;"
			id="ntj">
			<div id="txcontent">
				<div class="selectclass" style="font-size: 13px; text-align: right;">
					请选择交易时间：<input type="text" name="sq_date" id="sq_date"
						class="Wdate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowClear:false,readOnly:false})"
						style="width: 100px; vertical-align: middle"
						value=""> &nbsp;&nbsp;&nbsp;&nbsp;
					请选择状态： <select name="zt" id="zt">
						<option value="">全部</option>
						<option value="0">转入</option>
						<option value="1">转出</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="搜 索"
						id="SearchButton">&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<div class="selectclass">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr style="color: #69C; font-weight: bold;">
							<td>支付类型</td>
							<td>交易时间</td>
							<td>交易金额</td>
							<td>交易账号</td>
							<td>状态</td>
						</tr>
					</table>
				</div>
				<c:forEach items="${orders}" var="vo">  
				<div class="selectclass">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td style="color: #999; font-weight: bold;">平台转账 
								<c:choose>
								   <c:when test="${vo.typepay==5 && vo.userid== sessionScope.userId}"> 
								    	(<span style="color: #F00">转出</span>)
								   </c:when>
								   <c:otherwise>
									  	(<span style="color: #090">转入</span>)
								   </c:otherwise>
								</c:choose>
							</td>
							<td style="color: #006; font-weight: bold;"><fmt:formatDate value="${vo.tradedate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
								<c:choose>
									<c:when
										test="${vo.typepay==5 && vo.userid== sessionScope.userId }">
										<td style="color:#F00; font-size:20px;">- ${vo.ordermoney}</td>
									</c:when>
									<c:otherwise>
										<td style="color:#F00; font-size:20px;">+ ${vo.ordermoney}</td>
									</c:otherwise>
								</c:choose>
								<td><span style="color: #F90"><!-- <{:R("User/Index/GetUsername",Array($vo["Username"]))}> --></span>
							</td>
							<td>
								<c:choose>
									<c:when test="${vo.zt==1}">
										 成功
									</c:when>
									<c:otherwise>
										待付款
									</c:otherwise>
								</c:choose>
						</tr>
					</table>
				</div>
				</c:forEach>
 <div class="selectclass" style="text-align:center;">
 		<ul class="pagination" style="text-align:center;margin-bottom: 10px;" id="page_div" >
    
      </ul>
 </div>
			</div>

		</div>
	</div>
	<div style="clear: left;"></div>
</body>
</html>
