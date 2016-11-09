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
<script type="text/javascript" src="js/vaildate_common.js"></script>
<script type="text/javascript">
function vaild_form(){
	   var bankaccountnumber = $("#bankaccountnumber").val();
	   var bankcompellation = $("#bankcompellation").val();
	   var bankbranch = $("#bankbranch").val();
	   var code = $("#code").val();
	   if(bankcompellation==''){
		   alert("开户姓名不能为空");
		   return;
	   }
	   if(bankbranch==''){
		   alert("开户支行不能为空");
		   return;
	   }
	   if (bankaccountnumber == null || "" == bankaccountnumber) {
			alert("卡号不能为空");
			return
		} else {
			var l = /^\d*$/;
			if (!l.exec(bankaccountnumber)) {
				alert("银行卡号必须全为数字");
				return
			}
		}
		if (!checkBankCardNo(bankaccountnumber)) {
			alert("银行卡号：【" + bankaccountnumber + "】不合法，请重新输入");
			return
		}
		if(code==''){
			alert("安全码不能为空");
			return;
		}
		$("#next").attr("disabled","disabled");
		 $.ajax({
          type: "POST",
          url:"account/do_cardSet.html",
          data: $('#card_form').serialize(),
          success : function(g) {
        	if(g=="设置成功"){
        		alert(g);
        		location.reload();
        	}else{
        		alert(g);
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
						<li class="last">银行卡设置</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
<form method="post" action="account/do_cardSet.html" id="card_form">
	  <div class="selectclass">
	  	<div class="ldiv">开户人姓名：</div>
	 	<div class="rdiv">
	 	<input type="text"  id="bankcompellation" name="bankcompellation" value="${bank.bankcompellation}"  class="form-control" style="width: 15%;margin-top:8px;display: inline;"/>
	 	<input type="hidden"  id="id" name="id" value="${bank.id}"  class="form-control" />
	 		<span style="color:red;">银行卡号与开户名必须正确一致，否则提现会失败</span>
	 	</div>
	 </div>
	 
	 <div class="selectclass">
	  	<div class="ldiv">开户银行：</div>
	 	<div class="rdiv">
	 		<select style="width: 15%;margin-top:8px;" class="form-control" id="bankname" name="bankname">
	 			<c:forEach var="vo" items="${list}">
	 				<c:choose>
	 					<c:when test="${vo.bankname ==bank.bankname}">
	 						<option selected="selected" value="${vo.bankname}">${vo.bankname}</option>
	 					</c:when>
	 					<c:otherwise>
	 						<option value="${vo.bankname}">${vo.bankname}</option>
	 					</c:otherwise>
	 				</c:choose>
				</c:forEach>
	 		</select>
	 	</div>
	 </div>
	  <div class="selectclass">
	  	<div class="ldiv">开户支行：</div>
	 	<div class="rdiv">
	 		<input type="text" id="bankbranch" name="bankbranch" value="${bank.bankbranch}" class="form-control" style="width: 15%;margin-top:8px;display: inline;"/>
	 		<span style="color:red;">请正确填写开户支行，否则可能会导致无法到账 </span>
	 	</div>
	 </div>
	 <div class="selectclass">
	  	<div class="ldiv">银行卡号：</div>
	 	<div class="rdiv"><input type="text" class="form-control" id="bankaccountnumber" name="bankaccountnumber" value="${bank.bankaccountnumber}" style="width: 15%;margin-top:8px;"/></div>
	 </div>
	 <div class="selectclass">
	  	<div class="ldiv">安全码：</div>
	 	<div class="rdiv"><input type="password"  class="form-control" id="code" name="code" style="width: 15%;margin-top:8px;"/></div>
	 </div>
	  <div class="selectclass" style="text-align:left;clear: both;margin-top:5px;">
	 	 <span onclick="vaild_form()"  class="btn btn-primary btn-search" style="margin-left:18%" id="next">提交</span>
	 </div>
</form>
<div style="clear:left;"></div>
</body>
</html>