<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="css/dandelion.css"  media="screen" />
<script type="text/javascript" src="User/js/jquery-1.7.2.js"></script>
</head>
<body>
<jsp:include page="WEB-INF/jsp/common/top.jsp"></jsp:include>
	<div id="da-wrapper" class="fluid">
		<!-- Content -->
		<div id="da-content">

			<!-- Container -->
			<div class="da-container clearfix">

				<div id="da-error-wrapper">

					<div id="da-error-pin"></div>
					<div id="da-error-code">
						error <span>404</span>
					</div>
					<h1 class="da-error-heading">哎哟喂！页面让狗狗叼走了！</h1>
					<p>
						<span style='width: 100%; display: block;'>大家可以到狗狗没有叼过的地方看看！</span><a
							href="user/user_index.html">返回首页</a><a href="javascript:history.go(-1)">返回上一页</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>