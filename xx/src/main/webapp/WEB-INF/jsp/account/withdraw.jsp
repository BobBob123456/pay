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
<script src="css/layer/layer.js"></script>
<script type="text/javascript">
function vaild_form(){
	   var bankaccountnumber = $("#bankaccountnumber").val();
	   var money = $("#money").val();
	   var code = $("#code").val();
	   if(bankaccountnumber==''){
		   layer.alert('银行卡未设置，前往设置银行?', function(index){
			 	location.href="account/cardSet.html";
			});  
		   return;
	   }
	   if(money==''){
		   alert("提现金额不能为空");
		   return;
	   }
	   	var d = /^\d+(\.\d{1,2})?$/;
		if (!d.test(money)) {
			alert("金额必须是整数,或者是带两位小数的数字");
			return
		}
		if (parseFloat(money) == 0) {
			alert("金额必须大于0");
			return
		}
		$("#next").attr("disabled","disabled");
		 $.ajax({
          type: "POST",
          url:"account/do_withdraw.html",
          data: $('#withdraw_form').serialize(),
          success : function(g) {
        	var json=eval("("+g+")");
        	if(json.status==-1){
        		alert(json.str);
        		$("#next").removeAttr("disabled");
        	}else{
        		location.href = "batchPay/listBatchPay.html?batchId="+ json.batchId;
        	}
		},error : function(g) {
			$("#next").removeAttr("disabled");
		}
      });
}

</script>
<style type="text/css">
.selectclass{
	width:96%;
	height:50px;
	line-height:50px;
	text-align:center;
	text-align:left;
	font-size:15px;
	margin:0px auto;
	border-bottom:1px dashed #999999;
}
.selectclass .ldiv{width:15%;float:left;text-align:right;}
.selectclass .rdiv{width:85%;float:left;}
</style>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="biaoge">
		<div class="main_content">
			<nav>
				<div class='znav'>
					<ul style="width: 5000px;">
						<li class="first"><a href=""><i class="icon-home"></i></a></li>
						<li>账号管理</li>
						<li class="last">提现</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
<form id="withdraw_form" method="post" action="account/do_withdraw.html" >
	  <div class="selectclass">
	  	<div class="ldiv">提现银行卡：</div>
	 	<div class="rdiv">
	 		<c:if test="${empty bank.bankaccountnumber}">
	 			<script type="text/javascript">
	 			layer.alert('银行卡未设置，前往设置银行?', function(index){
	 				 	location.href="account/cardSet.html";
	 				});   
	 			</script>
	 		</c:if>
	 		<c:if test="${!empty bank.bankaccountnumber}">
	 			${bank.bankaccountnumber}
	 		</c:if>
	 		<input type="hidden" id="bankaccountnumber" name="bankaccountnumber" value="${bank.bankaccountnumber}"/>
	 	</div>
	 </div>
	 <div class="selectclass">
	  	<div class="ldiv">提现金额(元)：</div>
	 	<div class="rdiv"><input type="text"  id="money" name="money" class="form-control"  style="width: 15%;margin-top:8px;"/></div>
	 </div>
	   <div class="selectclass">
	  	<div class="ldiv">安全码：</div>
	 	<div class="rdiv"><input type="text" class="form-control" id="code" name="code" style="width: 15%;margin-top:8px;"/></div>
	 </div>
	  <div  style="text-align:left;clear: both;margin-top:10px;">
	 	<span onclick="vaild_form()"  class="btn btn-primary btn-search" style="margin-left:18%" id="next">提交</span>
	 </div>
</form>
<div style="clear:left;"></div>
</body>
</html>