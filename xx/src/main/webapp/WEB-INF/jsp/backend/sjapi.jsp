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
<script type="text/javascript" src="User/js/jquery-1.7.2.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="biaoge">
		<div class="main_content">
			<nav>
				<div class='znav'>
					<ul style="width: 5000px;">
						<li class="first"><a href=""><i class="icon-home"></i></a></li>
						<li>通道管理</li>
					</ul>
				</div>
			</nav>
			<div style='clear:both;'></div>			

				<div class='ztab'>
					<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 116px;">通道</th>
							<th style="width: 179px;">商户ID</th>
							<th style="width: 179px;">密    钥</th>
							<th style="width: 179px;">账    户</th>
							<th style="width: 240px;">修改时间</th>
							<th style="width: 149px;">网银费率</th>
							<th style="width: 149px;">操作</th>
						
						</tr>
					</thead>
					<tbody>
					  <c:forEach items="${list}" var="vo">  
						<tr>
							<td>${vo.myname}</td>
							<td>${vo.shid}</td>
							<td>${vo.key}</td>
							<td>${vo.zhanghu}</td>
							<td>${vo.editDate}</td>
							<td>${vo.fl}</td>
					      	<td> <input type="button" value="修改" class="btn btn-primary btn-search"/></td>
						</tr>
					 </c:forEach> 
					</tbody>
				</table>
				</div>
		</div>
	</div>
 <div class="selectclass" style="text-align:center;">
 		<ul class="pagination" style="text-align:center;margin-bottom: 10px;" id="page_div" >
    
      </ul>
 </div>
 </body>
</html>