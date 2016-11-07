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
		    		location.href="user/user_index.html?cur="+num;
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
						<li>商户管理</li>
					</ul>
				</div>
			</nav>
			<div style='clear:both;'></div>
			 <form action="backend/user_manage.html" method="post">
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
						<div class="input-group-addon">用户名</div>
						<input type="text" class="form-control" id="account" name="account" value="${account}" >
					</div>
					<div class="input-group" style='width:290px;float:left;font-size:13px;'>
						<div class="input-group-addon">状态</div>
						 <select class="form-control" name="status" id="status">
							<option value="">全部</option>
							<option value="0">未激活</option>
							<option value="1">激活</option>
							<option value="2">停用</option>
						</select>
						<script type="text/javascript">
							$("#status").val(${status});
						</script>
					</div>
					<div class="pull-right">
						 <input type="submit" value="查询" class="btn btn-primary btn-search"/>
						<!-- <a class="btn btn-info btn-reset" style='margin:0 -5px 0 0;'> 重 置</a> -->
					</div>
				</div>
				</form>
				<!--时间轴e-->
				<div class='ztab'>
					<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 76px;">商户类型</th>
							<th style="width: 179px;">用户名</th>
							<th style="width: 50px;">商户号</th>
							<th style="width: 109px;">网银通道</th>
							<th style="width: 240px;" colspan="2">金额（元）</th>
							<th style="width: 149px;">状态</th>
							<th style="width: 149px;">注册时间</th>
							<th style="width: 149px;" colspan="2">手续费率设置</th>
							<th style="width: 149px;" colspan="2">提款费率设置</th>
							<th style="width: 149px;">上级</th>
							<th style="width: 149px;">下级数量</th>
							<th style="width: 149px;">操作</th>
						
						</tr>
					</thead>
					<tbody>
					  <c:forEach items="${users}" var="vo">  
						<tr>
							<td>
								<c:if test="${vo.usertype==0||vo.usertype==3}">
									商户
								</c:if>
								<c:if test="${vo.usertype==5}">
									代理
								</c:if>
							</td>
							<td>${vo.username}</td>
							<td>${vo.id}</td>
							<td>${vo.sjapi.myname }</td>
							<td>${vo.money.money }</td>
							<td><span class="btn btn-primary btn-search">金额</span></td>
							<td> 
								<c:if test="${vo.status==0}">
									未激活
								</c:if>
								<c:if test="${vo.status==1}">
									已激活
								</c:if>
					       </td>
					      <td><fmt:formatDate value="${vo.regdate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
					      <td>${vo.sjapi.fl}</td>
					      <td><span class="btn btn-primary btn-search">设置</span></td>
					      <td></td>
					      <td><span class="btn btn-primary btn-search">设置</span></td>
					      <td>${vo.sj_name }</td>
					      <td>${vo.xj_num}</td>
					      <td></td>
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