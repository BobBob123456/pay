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
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/table.css" rel="stylesheet">
</head>
<script type="text/javascript">
	function submit_file(){
		var fileName=$('#myfile').val();
		if(fileName==''){
			alert('请选择文件上传！');
			return;
		}
		var a = fileName.substr(fileName.lastIndexOf("."));
		var c = /^.(txt|xls|xlsx|csv)$/i;
		if (!c.test(a)) {
			alert("请选择正确格式的文件（txt/xls/xlsx/csv）");
			return
		}
	
		$.ajaxFileUpload({
			url : "batchPay/fileUpload.html",
			secureuri : false,
			fileElementId : "myfile",
			dataType : "json",
			success : function(g, f) {
				switch (parseInt(g.code)) {
				case -1:
					location.href = Helper
							.getWebRootPath()
							+ "/batchPay/listBatchPay.html?batchId="
							+ g.batchId;
					break;
				default:
					if (g.str == "") {
						location.href = "batchPay/batchSubmission.html"
					} else {
						alert(g.str);
					}
					break
				}
			},
			error : function(g, f, h) {
			/* 	$(".btn-submit").removeAttr(
						"disabled");
				location.href = Helper
						.getWebRootPath()
						+ "/batchPay/batchSubmission.html" */
			}
		})
		
		//$('#pay_form').submit();
		//alert(flieName);
	}

</script>

<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	<form action="batchPay/fileUpload.html" method="post" id="pay_form" enctype="multipart/form-data"> 
		<!-- <div class="xgjcxx">
			<div
				style="border: 1px solid #ccc; background-image: url(User/images/menu_bg_x.jpg); width: 100%; height: 40px; line-height: 40px; font-size: 15px; text-align: left; font-weight: bold; color: #333">
				&nbsp;&nbsp;&nbsp;&nbsp;批量提交</div>
	
		
		</div> -->
		<div class="xgjcxx">
			<div style="background-color:#E6F1F5;height:4rem;line-height:4rem;"><span style="font-size:15px;font-weight: bold;margin-left:2rem;">批量提交</span> <span style="color: #A59A9C;">请您先下载批量付款模板文件，正确填写后上传</span> </div>
		</div>
		
		<div class="xgjcxx">
			<div style="text-align:center;height:4rem;line-height:4rem;">
				<a href="templet/txt.zip"  class="btn btn-primary">TXT模板下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="templet/csv.zip"  class="btn btn-primary">CSV模板下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="templet/xls.zip"  class="btn btn-primary">XLS模板下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="templet/xlsx.zip"  class="btn btn-primary">XLSX模板下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		
		<div class="selectclass" style="text-align: center;">
				<input type="file" id="myfile" name="myfile"  class="btn btn-default" style="display: inline-block;margin-top:10px;"/>
				&nbsp;&nbsp;&nbsp;&nbsp;<span  class="btn btn-default" style="margin-top:10px;" onclick="submit_file()">下一步</span>
		</div>
		<div style="clear: left;"></div>
	</form>
</body>
</html>