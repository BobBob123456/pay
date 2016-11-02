<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="User/css/showDialog.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="User/js/js.js"></script>
<!-- <script type="text/javascript" src="User/js/pcasunzip.js"></script> -->
<script type="text/javascript">
$(document).ready(function(e) {
    $("#menu div").addClass("menu_bg_y");
	$("#menu div:eq(1)").addClass("menu_bg");
	$("#menu_x > div > div:eq(0)").css("background-image","url(User/images/menumenu.gif)");
	$("#menu_x > div > div:eq(0) a").css("color","#F60");
});
</script>
</head>

<body>

<jsp:include page="../common/top.jsp"></jsp:include>
<form name="Form1" action="user/basicsave.html" method="post">
<!-------------------------------------------------基本信息-------------------------------------------------------->
<div class="xgjcxx">
<div style="border:1px solid #ccc; background-image:url(User/images/menu_bg_x.jpg); width:1000px; height:40px; line-height:40px; font-size:15px; text-align:left; font-weight:bold; color:#333">
&nbsp;&nbsp;&nbsp;&nbsp;基本信息
</div>

<div style="width:1000px; height:auto; border:1px solid #CCC; border-top:0px">
    <div class="jbxx">
    <input type="hidden" name="id" id="id" value="${info.id}" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名：
    <c:if test="${empty info.compellation}">
    <input type="text" class="input_text form-control" name="Compellation" id="Compellation" value="${info.compellation}">
    &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">请输入身份证上的姓名，添加后不能修改。</span>
    </c:if>
    <c:if test="${!empty info.compellation}">
    <span style="color:#000; font-weight:bold;">${info.compellation}</span>
    </c:if>
    
    </div>
     <div class="jbxx">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QQ号：<input type="text" class="input_text" name="qq" id="qq" style="width:100px;" value="${info.qq}" />
    &nbsp;&nbsp;&nbsp;&nbsp;
    </div>
    
    <div class="jbxx">
    身份证号：<input type="text" class="input_text" name="IdentificationCard" id="IdentificationCard" style="width:300px;" value="${info.identificationcard}" />
    &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">请输入正确的身份证号</span>
    </div>
     <div class="jbxx">
      手机号码：<input type="text" class="input_text" name="MobilePhone" id="MobilePhone" style="width:200px;" value="${info.mobilephone}">
      &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">请输入常用的手机号码。</span>
     </div>
    <div class="jbxx">
     联系电话：<input type="text" class="input_text" name="Tel" id="Tel" style="width:200px;" value="${info.tel}">
     &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">请输入您公司的座机号码，如 010-123123。</span>
    </div>
    <div class="jbxx" style="display:none;">
     所在省市：
     <select name="Province" id="province"></select>&nbsp;&nbsp;
     <select name="City" id="city"></select>
 <!--   <script type="text/javascript">
	 new PCAS("province","city","area");
	 $(document).ready(function(e) {
        $("#province").val('<{$vo.Province}>').change();;
		$("#city").val('<{$vo.City}>');
    });
   </script>	 -->
   &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">请选择您现在所在的省市。</span>	
    </div>
    <div class="jbxx">
    联系地址：<input type="text" class="input_text" name="Address" id="Address" style="width:500px;" value="${info.address}">
    &nbsp;&nbsp;&nbsp;&nbsp;<span class="tsts">请输入您现在所在省市的详细联系地址。</span>
    </div>
 

   
    <div class="jbxx" style="text-align:center; height:50px;">
    <input type="image" src="User/images/qrxg.gif" style="vertical-align:middle">
      &nbsp;&nbsp;&nbsp;&nbsp;
    <img src="User/images/chongzhi.gif" onclick="reset_form()" style="vertical-align:middle; cursor:pointer;">  
    </div>
    
</div>
</div>
<!-------------------------------------------------基本信息-------------------------------------------------------->
 </form>
<script type="text/javascript">
	function reset_form(){
		document.Form1.reset();
	}

</script>

</body>
</html>