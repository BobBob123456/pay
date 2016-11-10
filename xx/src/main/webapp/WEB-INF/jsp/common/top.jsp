<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/top.css" media="screen">
<link rel="stylesheet" href="css/nav.css" media="screen">
<link href="User/css/css.css" rel="stylesheet">
<script type="text/javascript" src="User/js/js.js"></script>
<script type="text/javascript" src="User/js/pcasunzip.js"></script>
<script type="text/javascript" src="js/Other.js"></script>
<script src="css/layer/layer.js"></script>
<script>
$(function(){
	$.ajax({
		type : "get",
		url : "user/account_money.html",
		cache : false,
		success : function(e) {
			$('#account_money').html(e);
		}
	});
	$.ajax({
		type : "get",
		url : "news/getNews.html",
		cache : false,
		success : function(e) {
			var list=eval("("+e+")");
			var html="";
			for(var i=0;i<list.length;i++){
				var obj=list[i];
				var title=obj.title;
				var content=obj.content;
				var datetime=obj.datetime;
		
				html+='<li onclick="tanchuang('+i+');"><div id="notice_content'+i+'" style="display:none;">'+content+'</div> <i class="splashy-volume_loud"></i> <a href="javascript:void(0);"  style="cursor:hand;" id="title'+i+'">'+title+'</a></li>';
			}
			$('#notice_ul').html(html);
		}
	});
});
	function tanchuang(i){
		var content=$('#notice_content'+i).html()
		var title=$('#title'+i).html();
		layer.open({
			type: 1,
			title: '公告详情',
			closeBtn: 0,
			shadeClose: true,
			skin: 'yourclass',
			area: ['720px', '550px'],
			closeBtn: 1,
			scrollbar: false,
			shadeClose: false,
			//content:$('#notice_content'+i).html()
			content: '<div style="width:720px;height:480px;"><div><div style="text-align: center;border-bottom:1px dashed #ddd;margin:20px 15px;"><h3 style="font-size:16px;color:#333;font-weight:normal;">'+title+'</h3><div style="margin-top:20px;" ><span style="color: #999;line-height:40px;"></span></div>'+content+'</div></div></div>'
		});
	}
	
	</script>
<!--头部-->
	<div class="header">
		<div class='logo'><img src='images/logo4.png' /></div>
		<div class='header-right' align="right">
			<c:if test="${sessionScope.userType!=6}">
				<span class='yue'>账户余额<span id="account_money"></span>元</span>
			</c:if>
			<span style="font-size:14px;">${sessionScope.userName}</span><span style='margin:0 5px;'>|</span><span class='logout'><a href="user/logou.html">退出系统</a></span>
		</div>
	</div>

	<div class="top">
		<div class="w t_cen">
			<div class="t_c_cen">
				<div class="t_c_bottom">
					<ul>
						<c:if test="${sessionScope.userType!=6}">
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
							<li><a href="user/income_expenses_detail.html"><span class="icon-shouzhi"></span>收支明细</a></li>
							<li>
								<a href="javascript:void(0);"><span class="icon-fukuan"></span>批量付款<b class="caret"></b></a>
								<div class="Nodes">
									<ul>
										<li><a href="batchPay/calcurate.html">逐笔录入</a></li>
										<li><a href="batchPay/batchSubmission.html">批量提交</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="javascript:void(0);"><span class="icon-zhanghu"></span>账户管理<b class="caret"></b></a>
								<div class="Nodes">
									<ul>
										<li class='bott1'><a href="account/withdraw.html">提现</a></li>
										<li><a href="account/update_password.html">修改密码</a></li>
										<li><a href="account/cardSet.html">银行卡设置</a></li>
										<li><a href="account/merchantKey.html">商户key值</a></li>
										<li class='bott1'><a href="account/retrieveSafeCode.html">找回安全码</a></li>
										<!-- <li class='bott2'><a href="">手机绑定</a></li>
										<li class='bott1'><a href="">IP绑定</a></li>
										<li class='bott2'><a href="">手机解绑</a></li>
										<li class='bott1'><a href="">IP解绑</a></li> -->
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
											<li><a href="user/xj_withdraw.html">下级结算订单</a></li>
											<li><a href="user/xj_money_detail.html">下级收支明细</a></li>
										
										</ul>
									</div>
								</li>
							</c:if>
							<li><a href=""><span class="icon-api"></span>API接口</a></li>
						</c:if>
						<c:if test="${sessionScope.userType==6}">
							<li>
								<a href="backend/order_manage.html"><span class="icon-shouzhi"></span>订单管理</a>
							</li>
							<li>
								<a href="backend/user_manage.html"><span class="icon-shouzhi"></span>商户管理</a>
							</li>
							
							<li>
								<a href="sjapi/sjapi_index.html"><span class="icon-shouzhi"></span>通道管理</a>
							</li>
							<li>
								<a href="javascript:void(0);"><span class="icon-shouzhi"></span>公告管理</a>
							</li>
							<li>
								<a href="backend/withdraw_manage.html"><span class="icon-shouzhi"></span>结算管理</a>
							</li>
							<li>
								<a href="backend/money_detail.html"><span class="icon-shouzhi"></span>收支明细管理</a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="mainNotice">
		<ul id="notice_ul"> 
		
		</ul>
	</div>
	<textarea id="tanchu" style="display: none;">
		<div style="height: auto; padding: 20px 0 0;">
			<form class="form-horizontal" id="code_form">
				<div class="form-group" style="margin-left: 0; margin-right: 0;">
					<label class="col-sm-4 control-label">安全码</label>
					<div class="col-sm-6">
						<input type="password" id="code" name="code" class="form-control" placeholder="请输入安全码">
					</div>
				</div>
				<div class="form-group" style="margin-left: 0; margin-right: 0;">
					<label class="col-sm-4 control-label">确认安全码</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="sure_code" name="sure_code" placeholder="请确认安全码">
					</div>
				</div>
				<div class="form-group" style="margin-left: 0; margin-right: 0;">
					<label class="col-sm-4 control-label">验证码</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="vaild_code" name="vaild_code" placeholder="请输入验证码">
					</div>
					<div class="col-sm-3">
						<img class="yzm" src="" onclick='flushValidateCode()' id="codeValidateImg" name="codeValidateImg" style="width:100px;border-radius:4px;cursor:pointer;"  title="点击刷新验证码"/>
					</div>
				</div>
				<div class="form-group" style="margin-left: 0; margin-right: 0;">
					<div class="col-sm-12" style="font-size:14px;color:red;text-align:center;" id="msg_div">
						
					</div>
				</div>
				<div class="form-group" style="margin-left: 0; margin-right: 0;">
					<label class="col-sm-4 control-label"></label>
					<div class="col-sm-6">
						 <span onclick="vaild_form()"  class="btn btn-primary btn-search" style="margin-left:18%" id="next">提交</span>
					</div>
				</div>
			</form>
		</div>
	</textarea>
	<c:if test="${sessionScope.is_set==0}">
		<script type="text/javascript">
			$(function(){
				layer.open({
					type: 1,
					title: '设置安全码',
					closeBtn: 0,
					shadeClose: true,
					skin: 'yourclass',
					area: ['500px', '290px'],
					closeBtn: 1,
					scrollbar: false,
					shadeClose: false,
					content: $('#tanchu').val()
				});
				 flushValidateCode();
			});
			function vaild_form(){
				if($('#code').val()==''){
					$('#msg_div').html("安全码不能为空");
					return;
				}
				if($('#sure_code').val()==''){
					$('#msg_div').html("确认安全码不能为空");
					return;
				}
				if($('#sure_code').val()!=$('#code').val()){
					$('#msg_div').html("两次安全码不相同");
					return;
				}
				if($('#vaild_code').val()==''){
					$('#msg_div').html("验证码不能为空");
					return;
				}
				$("#next").attr("disabled","disabled");
				 $.ajax({
		          type: "POST",
		          url:"account/set_code.html",
		          data: $('#code_form').serialize(),
		          success : function(g) {
		        	if(g=="设置成功"){
		        		alert(g);
		        		location.reload();
		        	}else{
		        		$("#next").removeAttr("disabled");
		        		alert(g);
		        	}
				},error : function(g) {
					$("#next").removeAttr("disabled");
				}
		      });
			}
			/* 刷新生成验证码 */
			function flushValidateCode(){
			var validateImgObject = document.getElementById("codeValidateImg");
			validateImgObject.src = "${pageContext.request.contextPath }/imageGen/getSysManageLoginCode.html?time=" + new Date();
			}
		</script>
	</c:if>
	
	<!--注意事项e-->
	

