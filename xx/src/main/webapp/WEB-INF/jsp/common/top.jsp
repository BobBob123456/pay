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
<link rel="stylesheet" href="css/top.css" media="screen">
<link rel="stylesheet" href="css/nav.css" media="screen">
<link href="User/css/css.css" rel="stylesheet">
<link href="User/css/showDialog.css" rel="stylesheet">
<script type="text/javascript" src="User/js/js.js"></script>
<script type="text/javascript" src="User/js/pcasunzip.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/Other.js"></script>
<script src="css/layer/layer.js"></script>
<script>

	function tanchuang1(){

		layer.open({
			type: 1,
			title: '通知详情',
			closeBtn: 0,
			shadeClose: true,
			skin: 'yourclass',
			area: ['720px', '550px'],
			closeBtn: 1,
			scrollbar: false,
			shadeClose: false,
			content: '<div style="width:720px;height:480px;"><div><div style="text-align: center;border-bottom:1px dashed #ddd;margin:20px 15px;"><h3 style="font-size:16px;color:#333;font-weight:normal;">提款请避开0点前后1分钟</h3><div ><span style="color: #999;line-height:40px;">2016-07-09 08:51:33</span>     </div> </div> <div><div style="margin:0 15px">24小时秒结算服务因日切点银行对账需要，提款时间更改为：<b><font color="#FF0000">00：01   至 23：59 </font></b>对此带来的不便，深表歉意，敬请谅解！<br></div> </div></div></div>'
		});

	}
	
	function tanchuang2(){

		layer.open({
			type: 1,
			title: '通知详情',
			closeBtn: 0,
			shadeClose: true,
			skin: 'yourclass',
			area: ['720px', '550px'],
			closeBtn: 1,
			scrollbar: false,
			shadeClose: false,
			content: '<div style="width:720px;height:480px;"><div><div style="text-align: center;border-bottom:1px dashed #ddd;margin:20px 15px;"><h3 style="font-size:16px;color:#333;font-weight:normal;">账户安全须知（商户必看！）</h3><div ><span style="color: #999;line-height:40px;">2016-07-09 08:51:33</span>     </div> </div> <div><div style="margin:0 15px">邮箱为<b><font color="#FF0000">最高权限</font></b>，邮箱可以用于找回密码，找回安全码，解绑IP，解绑手机，解绑安全令牌！<br><br>请确保您的邮箱安全，请尽量<font color="#FF0000"><b>开启邮箱的登陆验证</b></font>（例如Gmail的绑定手机），并确保邮箱关闭pop3，smtp，避免邮箱被盗！<br></div> </div></div></div>'
		});

	}
	
	
	function tanchuang3(){

		layer.open({
			type: 1,
			title: '通知详情',
			closeBtn: 0,
			shadeClose: true,
			skin: 'yourclass',
			area: ['720px', '550px'],
			closeBtn: 1,
			scrollbar: false,
			shadeClose: false,
			content: '<div style="width:720px;height:480px;"><div><div style="text-align: center;border-bottom:1px dashed #ddd;margin:20px 15px;"><h3 style="font-size:16px;color:#333;font-weight:normal;">严打理财商户非法接入！</h3><div ><span style="color: #999;line-height:40px;">2016-07-09 08:51:33</span>     </div> </div> <div><div style="margin:0 15px">为了确保整体商户质量，为诚信经营的商户提供良好的、安全的、稳定的支付环境即日起对于P2P、贵金属、期货、众筹、虚拟币等非法理财商户予以清查，理财类商户隐瞒真实身份接入系统的，一经发现予以冻结账户！！！！</div> </div></div></div>'
		});

	}
	
	function tanchuang4(){

		layer.open({
			type: 1,
			title: '通知详情',
			closeBtn: 0,
			shadeClose: true,
			skin: 'yourclass',
			area: ['720px', '550px'],
			closeBtn: 1,
			scrollbar: false,
			shadeClose: false,
			content: '<div style="width:720px;height:480px;"><div><div style="text-align: center;border-bottom:1px dashed #ddd;margin:20px 15px;"><h3 style="font-size:16px;color:#333;font-weight:normal;">账户关停规则！</h3><div ><span style="color: #999;line-height:40px;">2016-07-09 08:51:33</span>     </div> </div> <div><div style="margin:0 15px">注册满30天，无出款记录的账户，每天早上8：30自动关停！对此带来的不便，深表歉意，敬请谅解！</div> </div></div></div>'
		});

	}
	</script>
<body>
<!--头部-->
	<div class="header">
		<div class='logo'><img src='images/logo4.png' /></div>
		<div class='header-right'>
			<div class='yue'>账户余额<span>${sessionScope.money}</span>元</div>
			<div class='login-name'>${sessionScope.userName}<span style='margin:0 5px;'>|</span><span class='logout'><a href="user/logou.html">退出系统</a></span></div>
		</div>
	</div>
	<div class="top">
		<div class="w t_cen">
			<div class="t_c_cen">
				<div class="t_c_bottom">
					<ul>
						<li>
							<a href="javascript:void(0);"><span class="icon-shoukuan"></span>我的收款<b class="caret"></b></a>
							<div class="Nodes">
								<ul>
									<li><a href="user/user_index.html">网关收款</a></li>
									<li><a href="user/ptzz.html">账户收款</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="javascript:void(0);"><span class="icon-jiesuan"></span>我的结算<b class="caret"></b></a>
							<div class="Nodes">
								<ul>
									<li><a href="user/settlement.html">银行卡结算</a></li>
									<li><a href="user/pt_settlement.html" class="messages">账户付款</a></li>
								</ul>
							</div>
						</li>
						<li><a href="javascript:void(0);"><span class="icon-shouzhi"></span>收支明细</a></li>
						<li>
							<a href="javascript:void(0);"><span class="icon-fukuan"></span>批量付款<b class="caret"></b></a>
							<div class="Nodes">
								<ul>
									<li><a href="batchPay/calcurate.html">逐笔录入</a></li>
									<li><a href="">批量提交</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="javascript:void(0);"><span class="icon-zhanghu"></span>账户管理<b class="caret"></b></a>
							<div class="Nodes">
								<ul>
									<li class='bott1'><a href="">提现</a></li>
									<li class='bott2'><a href="">子账户管理</a></li>
									<li><a href="">修改密码</a></li>
									<li><a href="">银行卡设置</a></li>
									<li><a href="">商户key值</a></li>
									<li class='bott1'><a href="">找回安全码</a></li>
									<li class='bott2'><a href="">手机绑定</a></li>
									<li class='bott1'><a href="">IP绑定</a></li>
									<li class='bott2'><a href="">手机解绑</a></li>
									<li class='bott1'><a href="">IP解绑</a></li>
									<li class='bott2'><a href="">安全令牌</a></li>
								</ul>
							</div>
						</li>
						
						<c:if test="${sessionScope.userType==5}">
							<li>
								<a href="javascript:void(0);"><span class="icon-daili"></span>代理管理<b class="caret"></b></a>
								<div class="Nodes">
									<ul>
										<li class='bott1'><a href="user/tjyg.html">下级商户</a></li>
										<li class='bott2'><a href="user/xj_order.html">下级收款订单</a></li>
										<li><a href="">下级结算订单</a></li>
										<li><a href="">下级收支明细</a></li>
									
									</ul>
								</div>
							</li>
						</c:if>
						<li><a href=""><span class="icon-api"></span>API接口</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="mainNotice">
		<ul> 
			<li onclick='tanchuang1();'> <i class="splashy-volume_loud"></i> <a href="javascript:void(0);" onclick="menu.initNoticeShow(25);" style="cursor:hand;"> 提款请避开0点前后1分钟 2016-09-30</a></li>
			<li onclick='tanchuang2();'> <i class="splashy-volume_loud"></i> <a href="javascript:void(0);" onclick="menu.initNoticeShow(23);" style="cursor:hand;"> 账户安全须知（商户必看！） 2016-07-09</a></li>
			<li onclick='tanchuang3();'> <i class="splashy-volume_loud"></i> <a href="javascript:void(0);" onclick="menu.initNoticeShow(22);" style="cursor:hand;"> 严打理财商户非法接入！ 2016-07-29</a></li>
			<li onclick='tanchuang4();'> <i class="splashy-volume_loud"></i> <a href="javascript:void(0);" onclick="menu.initNoticeShow(21);" style="cursor:hand;"> 账户关停规则！ 2016-08-02</a></li>
		</ul>
	</div>
	
	<!--注意事项e-->
	
</body>
</html>