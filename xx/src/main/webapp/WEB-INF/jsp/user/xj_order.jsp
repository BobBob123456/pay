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
   $("#menu div").addClass("menu_bg_y");
	$("#menu div:eq(2)").addClass("menu_bg");
	$("#menu_x > div > div:eq(0)").css("background-image","url(User/images/menumenu.gif)");
	$("#menu_x > div > div:eq(0) a").css("color","#F60");
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
<style type="text/css">
#txtitle{
	width:800px;
	height:50px;
	margin:0px auto;
	text-align:center;
	}
#txtitle div{
	width:800px;
	height:30px;
	font-weight:bold;
	font-size:20px;
	color:#06C;
	text-align:center;
	line-height:30px;
	border:1px solid #999;
	border-bottom:0px;
	cursor:pointer;
	margin:0px auto;
	}
	
#txcontent{
	width:100%;
	height:auto;
	}	
	
.selectclass{
	width:100%;
	height:70px;
	line-height:50px;
	text-align:center;
	text-align:left;
	font-size:15px;
	margin:0px auto;
	border-bottom:1px dashed #999999;
	}
.selectclass table{
	width:100%;
	height:50px;
	}

.selectclass table tr td{
	width:9%;
	height:50px;
	text-align:center;
	vertical-align:middle;
	font-size:12px;
	}	
.selectclass a{
	font-size:13px;
	color:#333;
	}
.selectclass a:hover{
	
	}
</style>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>

<div class="xgjcxx">
<div style="border:1px solid #ccc; background-image:url(User/images/menu_bg_x.jpg); width:100%; height:40px; line-height:40px; font-size:15px; text-align:left; font-weight:bold; color:#333">
&nbsp;&nbsp;&nbsp;&nbsp;网银交易记录
</div>

<div style="width:100%; height:auto; border:1px solid #CCC; border-top:0px;" id="ntj">

  
  <div id="txcontent">
      
      
      <div class="selectclass" style="height:auto;">
      <form action="user/wyjyjl.html" method="post">
	      <table border="0" cellpadding="0" cellspacing="0">
		      <tr>
		      	  <td>订单笔数：<span style="font-size:20px; color:#F00; font-weight:bold;">${total}</span> 笔</td>
			      <td>成功金额：<span style="font-size:20px; color:#F00; font-weight:bold;">${daymoney}</span> 元</td>
			      <td>成功笔数：<span style="font-size:20px; color:#F00; font-weight:bold;">${daynum}</span> 元</td>
		      </tr>
		      <tr>
		      	<td colspan="3" style="text-align: left;"><span style="margin-left:11.5rem;">
		      	交易时间：</span>
		      	<input type="text" name="ksjy_date" id="ksjy_date"  class="Wdate" value="${ksjy_date}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})" style="width:150px;">
		      	&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  name="jsjy_date" id="jsjy_date" value="${jsjy_date}"  class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,readOnly:true})" style="width:150px;">
		      	&nbsp;&nbsp;&nbsp;&nbsp;订单状态：
			      	<select id="status" name="status">
			      		 <c:choose>
					      	<c:when test="${status==''}"><option value="" selected="selected">请选择</option></c:when>
					      	<c:otherwise><option value="">请选择</option></c:otherwise>
					      </c:choose>
			      		 <c:choose>
					      	<c:when test="${!empty status &&status==0}"><option value="0" selected="selected">待付款</option></c:when>
					      	<c:otherwise><option value="0">待付款</option></c:otherwise>
					      </c:choose>
					      <c:choose>
					      	<c:when test="${status==1}"><option value="1" selected="selected">成功</option></c:when>
					      	<c:otherwise><option value="1">成功</option></c:otherwise>
					      </c:choose>
					      <c:choose>
					      	<c:when test="${status==2}"><option value="2" selected="selected">失败</option></c:when>
					      	<c:otherwise><option value="2">失败</option></c:otherwise>
					      </c:choose>
			 
			    
			        </select>&nbsp;&nbsp;&nbsp;&nbsp;商户订单号:
			        <input type="text" id="order_number" name="order_number" value="${order_number}" style="line-height: normal;"/>
			        &nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="submit" value="查询" style="line-height: normal;"/>
		      	 </td>
		      </tr>
	      </table>
      </form>
      </div>
   <div class="selectclass">
   <table border="0" cellpadding="0" cellspacing="0">
   <tr style="color:#69C; font-weight:bold;">
  <!-- <td>支付类型</td>
   <td style="width:20%;">订单号</td>
   <td style="width:13%">交易时间</td>
   <td>交易金额</td>
   <td>手续费</td>
   <td>实收金额</td>
   <td style="width:10%;">充值银行</td>
   <td>状态</td>
   <td>通知</td>-->
   <td style="width:12%;">支付方式</td>
   <td style="width:24%;">商户订单号</td>
   <td style="width:20%;">建立时间</td>
   <td style="width:8%;">金额</td>
 <!--   <td style="width:8%;">实收金额</td> 
   <td style="width:8%;">类型</td>-->
   <td style="width:8%;">状态</td>
   <td style="width:8%;">操作</td>
   </tr>
   </table>
   </div>
    <c:forEach items="${orders}" var="vo">  
      <div class="selectclass">
      <table border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td style="width:12%;">${vo.bankname}</td>
      <td style="width:24%;">${vo.transid}</td>
      <td style="width:20%;"><fmt:formatDate value="${vo.tradedate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
      <td style="width:8%;">${vo.trademoney}</td>
     <%--  <td style="width:8%;">${vo.ordermoney}</td>
      <td style="width:8%;">
      <c:choose>
      	<c:when test="${vo.typepay==0}">订单</c:when>
      	<c:otherwise>充值</c:otherwise>
      </c:choose>
      </td> --%>
      <td style="width:8%;">   
      <c:choose>
      	<c:when test="${vo.zt==1}">成功</c:when>
      	<c:otherwise>待付款</c:otherwise>
      </c:choose>
      </td>
      <td style="width:8%;">
    <!--   <{:R('/User/Index/tzshow',array($vo["TransID"]))}> -->
      </td>
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
<div style="clear:left;"></div>
</body>
</html>