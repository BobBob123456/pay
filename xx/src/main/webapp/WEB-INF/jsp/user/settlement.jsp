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
	width: 11%;
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
			&nbsp;&nbsp;&nbsp;&nbsp;提款记录</div>

		<div
			style="width: 100%; height: auto; border: 1px solid #CCC; border-top: 0px;"
			id="ntj">
			<div id="txcontent">

				<div class="selectclass" style="height: auto;">
					<div style="text-align: center;">订单笔数：${total}|成功金额：${success_money}|成功笔数:${success_count}</div>
				</div>
				<form action="user/settlement.html" method="post">
					<div class="selectclass" style="font-size: 13px; text-align: left;margin-left:1rem;">
						请选择申请时间：<input type="text" name="ksjy_date" id="ksjy_date" class="Wdate"
								value="${ksjy_date}"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})"
								style="width: 150px;">
							&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
								name="jsjy_date" id="jsjy_date" value="${jsjy_date}" class="Wdate"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})"
								style="width: 150px;"> &nbsp;&nbsp;&nbsp;&nbsp;
						请选择状态： <select name="zt" id="zt">
							<option value="">全部</option>
							<option value="0">未处理</option>
							<option value="2">已打款</option>
							<option value="3">失败</option>
						</select>&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" style="line-height:normal;" value="搜 索"
							id="SearchButton">&nbsp;&nbsp;&nbsp;&nbsp;
						<script type="text/javascript">
							$("#zt").val(${zt});
						</script>
					</div>
				</form>
				<div class="selectclass">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr style="color: #69C; font-weight: bold;">
							<td>姓名</td>
							<td>卡号</td>
							<td>银行名称</td>
							<td>分行名称</td>
							<td>金额</td>
							<td>申请时间</td>
							<td>状态</td>
						</tr>
					</table>
				</div>
				<c:forEach var="vo" items="${list}">
					<div class="selectclass">
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td style="color: #333;">${vo.myname}</td>
								<td style="color: #333;">${vo.bankNumber}</td>
								<td style="color: #333;">${vo.bankname}</td>
								<td style="color: #333;">${vo.fenBankname}</td>
								<td style="color: #666; font-weight: bold;">￥${vo.tkMoney}</td>
								<td style="color: #666;"><fmt:formatDate value="${vo.sqDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
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
							</tr>
						</table>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div style="text-align:center;">
		<ul class="pagination" style="text-align:center;margin-bottom: 10px;" id="page_div" >
						    
		</ul>
	</div>
	<div style="clear: left;"></div>
</body>
</html>