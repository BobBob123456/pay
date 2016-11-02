<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>国盛通管理系统</title>
<base href="<%=basePath%>">
<link href="User/css/css.css" rel="stylesheet">
<link href="User/css/showDialog.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/1.10.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="User/js/js.js"></script>
<script type="text/javascript" src="js/floatDiv.js"></script>
<script type="text/javascript" src="User/js/showDialog.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
    $("#menu div").addClass("menu_bg_y");
	$("#menu div:eq(0)").addClass("menu_bg");
	$("#menu_x > div > div:eq(1)").css("background-image","url(User/images/menumenu.gif)");
	$("#menu_x > div > div:eq(1) a").css("color","#F60");
	$("#fyid").mouseover(function(e) {
        $("#fyxjcd").show();
    });
	
	$("#fyid").mouseout(function(e) {
        $("#fyxjcd").hide();
    });
	
	$("#fyxjcd").mouseover(function(e) {
        $("#fyxjcd").show();
    });
	
	$("#fyxjcd").mouseout(function(e) {
        $("#fyxjcd").hide();
    });
});
</script>
<style type="text/css">
.jymx table{
	width:100%;
	height:40px;
	}
.jymx table tr td{
	 width:16%; 
	height:40px;
	text-align:center;
	vertical-align:middle;
	color:#666;
	font-weight:bold;
	}	
.beizu{
	width:200px;
	/*background-color:#0F0;*/
	overflow:hidden;
    word-break:keep-all;
    white-space:nowrap;
    text-overflow:ellipsis;
	}	
</style>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>

<div style="border:1px solid #ccc; background-image:url(User/images/menu_bg_x.jpg); width:1000px; font-size:15px; font-weight:bold; color:#333; margin:0px auto; margin-top:10px; height:40px;">
<div style="width:50%; float:left; height:40px; line-height:40px; text-align:left;">
&nbsp;
<span style="color:#000; font-weight:bold;"></span>&nbsp;<span style="color:#000; font-weight:bold;">${compellation}</span>&nbsp;&nbsp;<span style="font-weight:normal;">${sessionScope.userName}</span>&nbsp;<span style="color:#000; font-weight:bold;">商户ID：&nbsp;<span style="font-weight:normal; color:#999;"><%=Integer.parseInt(request.getSession().getAttribute("userId").toString())+10000 %>&nbsp;(
<c:if test="${zt==0}" var="未审核">未审核</c:if>
<c:if test="${zt==1}" var="审核中">审核中</c:if>
<c:if test="${zt==2}" var="已审核">已审核</c:if>
)</span></span>
&nbsp;<a href="/User_Index_aqxx.html" style="color:#999; font-size:13px;">密码修改</a>
</div>
<!-------------------------------------->
<div style="width:50%; float:left; height:40px; line-height:40px; text-align:right;">
<span style="color:#999; font-weight:normal; font-size:13px;">上次登录时间：<span style="color:#F90">${sessionScope.scdate}</span></span>&nbsp;&nbsp;
<span style="color:#999; font-weight:normal; font-size:13px;">上次登录IP：<span style="color:#F90">

${sessionScope.scip}
</span></span>&nbsp;&nbsp;
</div>
</div>
<div style="clear:left"></div>
<div id="grxx">
  <div class="grxx2">
      <div style="width:100%; height:78px; line-height:78px; font-weight:bold; font-size:30px;">
      &nbsp;&nbsp;&nbsp;&nbsp;<sapn style="font-family:'微软雅黑'; color:#3384ad;">可用余额：</span>
       &nbsp;&nbsp;<span style="color:#F00; font-size:35px;"> ${money}</span>&nbsp;<span style="font-size:18px; font-weight:normal; color:#3384ad;">元</span>&nbsp;&nbsp;
       </div>
       <div style="width:100%; height:30px; line-height:30px; font-size:13px; text-align:right; color:#3384ad;">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账户余额 (可用余额 + 未处理提现金额)：
       &nbsp;&nbsp;<span style="color:#F60; font-size:25px;">${zhye}</span>&nbsp;<span style="font-size:18px; font-weight:normal;">元</span>&nbsp;&nbsp;
       
        &nbsp;&nbsp;&nbsp;&nbsp;已提现金额：
       &nbsp;&nbsp;<span style="color:#F60; font-size:25px;">${ytxmoney}</span>&nbsp;<span style="font-size:18px; font-weight:normal;">元</span>&nbsp;&nbsp;
      </div>
      
  </div>
  <div class="grxx3" style="width:auto;">
   <div style="width:100%; height:auto; font-size:13px; color:#000; margin-top:8px;">
        <div style="text-align:right; width:220px; height:25px;"><a href="/User_Index_sjtgg.html" style="color:#000; font-size:13px;">更多</a></div>
   </div>
  </div>
  <div style="clear:left;"></div>
  <div class="grxx1" style="position:relative">
      <button  class="btn btn-primary" onclick="javascript:window.location.href='user/user_basic.html'">用户管理</button>
     &nbsp;&nbsp;
     <button  class="btn btn-primary" onclick="javascript:window.location.href='/User_Index_tktx.html'">&nbsp;&nbsp;提&nbsp;&nbsp;&nbsp;&nbsp;现&nbsp;&nbsp;</button>
     &nbsp;&nbsp;
       <button  class="btn btn-primary" onclick="javascript:window.location.href='/User_Index_tkyh_banktype_0.html'">设置银行卡</button>
       &nbsp;&nbsp;
        <button  class="btn btn-primary" onclick="javascript:window.location.href='/User_Index_chongzhi.html'">&nbsp;&nbsp;充&nbsp;&nbsp;&nbsp;&nbsp;值&nbsp;&nbsp;</button>
      &nbsp;&nbsp;
      <button  class="btn btn-primary" onclick="javascript:window.location.href='/User_Index_npdy.html'">&nbsp;&nbsp;付&nbsp;&nbsp;&nbsp;&nbsp;款&nbsp;&nbsp;</button>
      &nbsp;&nbsp;
      <button  class="btn btn-primary" onclick="javascript:window.location.href='/User_Index_shjk.html'">商户接入</button>
       &nbsp;&nbsp;
       <div class="btn-group">
  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    开通功能 <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li><a href="/User_Index_sqzdjs.html">T + 0</a></li>
    <li class="divider"></li>
    <li><a href="/User_Index_wtplxf.html">委托批量下发</a></li>
  </ul>
</div>
       
  </div>
  
  <div class="grxx4" style=" color:#3384ad;">
    【当日订单数量：<span style="color:#333">${dayorder}</span>&nbsp;笔】【总额订单：<span style="color:#F00">${daymoney}</span>&nbsp;元】【默认银行卡：<span style="font-weight:bold; color:#666">${BankName}</span>】
  </div>
  
</div>

<div id="jylb">
<div style="border:1px solid #ccc; background-image:url(User/images/menu_bg_x.jpg); width:1000px; height:40px; line-height:40px; font-size:15px; text-align:left; font-weight:bold; color:#333">
&nbsp;&nbsp;&nbsp;&nbsp;最近交易列表&nbsp;&nbsp;&nbsp;&nbsp;<a href="/User_Index_wyjyjl.html" style="font-weight:normal; color:#333; font-size:13px;">网银交易记录</a> | <a href="/User_Index_gamejl.html" style="font-weight:normal; color:#333; font-size:13px;">点卡交易记录</a> | <a href="/User_Index_wtjl.html" style="font-weight:normal; color:#333; font-size:13px;">委托提款记录</a> | <a href="/User_Index_tkjl.html" style="font-weight:normal; color:#333; font-size:13px;">提现记录</a>
</div>
<div style="width:1000px; height:auto; border:1px solid #CCC; border-top:0px">
    
    <div class="jymx">
    <table border="0" cellpadding="0" cellspacing="0">
    <tr style="background-color:#EAEAFD">
    <td>交易类型</td>
  
    <td>交易时间</td>
    <td>交易金额</td>
    <td>交易账户</td>
    <td>状态</td>
    <td>备注</td>
    </tr>
    </table>
    </div>
    <c:forEach items="${orders}" var="order">

    <div class="jymx">
    <table border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td style="color:#999">
	    <c:if test="${order.typepay==0}" var="type">网银订单</c:if>
	    <c:if test="${order.typepay==1}" var="type">网银充值</c:if>
	    <c:if test="${order.typepay==2}" var="type">点卡订单</c:if>
	    <c:if test="${order.typepay==3}" var="type">网银收款</c:if>
	    <c:if test="${order.typepay==4}" var="type">点卡收款</c:if>
	    <c:if test="${order.typepay==5}" var="type">平台转账</c:if>
    </td>

    <td style="color:#999; text-align:center;">
    <fmt:formatDate value="${order.tradedate}" pattern="yyyy/MM/dd" />
    	
    </td>
    
 <c:choose>
   <c:when test="${order.typepay==5 && order.userid== sessionScope.userId }"> 
       <td style="color:#999">
    	-
    	 ${order.ordermoney}
    	</td>
   </c:when>
   <c:otherwise>
	  <td style="color:#999">
	    +
	     ${order.ordermoney}
	    </td>
   </c:otherwise>
</c:choose>
    <td>
<!--     <if condition="$vo.typepay == 5">
    <span style="color:#F90"><{:R("User/Index/GetUsername",Array($vo["Username"]))}></span>
    <else /> -->
    ${order.username}
  <!--   </if> -->
    </td>
    <td style="font-weight:normal; font-size:13px;">
    <c:if test="${order.zt==1}" var="status"> 成功</c:if>
    <c:if test="${order.zt==2}" var="status"> 未处理</c:if>
   
    </td>
    <td style="color:#999;"><div class="beizu">${order.additionalinfo}</div></td>
    </tr>
    </table>
    </div>
    </c:forEach>
</div>
</div>


<!------------------------------------------------------------------------------------------------------>
<div style="width:400px; height:auto; border-top:1px solid #09F; border-left:1px solid #09F;  background-color:#F7F7F7; display:none;" id="xxtsdiv">		
    <div style="width:400px; height:40px; background-image:url(User/images/menu_bg_x.jpg); text-align:right; line-height:40px;">
    <span style="font-size:13px; color:#0089ad; font-weight:bold; cursor:pointer;" id="sqgb" zt="0">收 起</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:13px; color:#F00; cursor:pointer;" id="gbxx" title="关 闭"><img src="User/images/gb.gif" style="vertical-align:middle;"></span>&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
    <div style="width:400px; height:360px; background-color:#FFF" id="showtsxx"><br>
       <ul style="list-style:none;">
       <%-- <volist name="listTongzhi" id="vo">
            <li style="height:30px; line-height:30px;"><img src="User/images/arrow_061.gif">&nbsp;<a href="javascript:showDialog('info','<{$vo.Content}>','<{$vo.Title}>');" style="font-size:13px; color:#06F"><{$vo.Title}></a></li>
          </volist>  --%>
       </ul>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
    $("#xxtsdiv").floatdiv("rightbottom");
	$("#sqgb").click(function(e) {
        if($(this).attr("zt") == 0){
			$("#showtsxx").animate({"height":"0px"},1000,function(){
				$("#showtsxx").hide();
				$("#sqgb").attr("zt","1");
				$("#sqgb").text("展 开");
				$("#sqgb").css("color","#F00");
				});
			}else{
				if($(this).attr("zt") == 1){
					$("#showtsxx").show();
				  $("#showtsxx").animate({"height":"360px"},1000,function(){
					  $("#sqgb").attr("zt","0");
					  $("#sqgb").text("收 起");
					  $("#sqgb").css("color","#0089ad");
					  });
				  }
				}
    });
	
	$("#gbxx").click(function(e) {
        $("#xxtsdiv").fadeOut(1000);
    });
});

</script>
<!------------------------------------------------------------------------------------------------------>
</html>