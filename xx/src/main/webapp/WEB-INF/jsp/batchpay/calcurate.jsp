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
<script type="text/javascript" src="User/js/jquery-1.7.2.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="js/vaildate_common.js"></script>
<script type="text/javascript">
function addTr(){
	   var trs=$('#mytable tr');
	   var last_tr=trs[trs.length-1]; 
	   var sHtml=$('#bank_hidden').html();
	   var html='<tr><td style="font-size: 18px; width: 30%;"><select  name="bank" class="form-control" style="width:60%;margin-left:20%;">'+sHtml
	   +'</select></td><td><input type="text"  name="card" class="form-control" style="width: 80%;margin-left:10%;"/></td>'+
	   '<td><input type="text"  name="name" class="form-control" style="width: 60%;margin-left:20%;" /></td>'+
	'<td><input type="text"  name="money" class="form-control" style="width: 60%;margin-left:20%;"/></td>'+
	'<td style="width: 10%;" ><button type="button" class="btn btn-danger" onclick="delTr(this)">删除</button></td></tr>';
	 $(last_tr).after(html);
}
function delTr(obj){
	   $(obj).parent().parent().remove();
}

function vaild_form(){
	   var q = document.getElementsByName("bank");
		var c = document.getElementsByName("card");
		var g = document.getElementsByName("name");
		var k = document.getElementsByName("money");
		var h = [];
		var m = [];
		var f = [];
		var o = [];
		var r = [];
		for (var j = 0; j < q.length; j++) {
			var p = q[j].value;
			h.push(p)
		}
		for (var j = 0; j < c.length; j++) {
			var n = c[j].value;
			if (n == null || "" == n) {
				alert("卡号不能为空");
				return
			} else {
				var l = /^\d*$/;
				if (!l.exec(n)) {
					alert("银行卡号必须全为数字");
					return
				}
			}
			if (g[j].value.indexOf("公司") < 0 && q[j].value != "CSCB"
					&& !checkBankCardNo(n)) {
					alert("银行卡号：【" + n + "】不合法，请重新输入");
				return
			}
			m.push(n)
		}
		for (var j = 0; j < g.length; j++) {
			if (g[j].value == null || "" == g[j].value) {
				alert("姓名不能为空");
				return
			}
			o.push(g[j].value)
		}
		var e = 0;
		for (var j = 0; j < k.length; j++) {
			var p = k[j].value;
			e = parseFloat(e) + parseFloat(p);
			if ("" == k[j].value) {
					alert("金额不能为空");
				return
			} else {
				var d = /^\d+(\.\d{1,2})?$/;
				if (!d.test(k[j].value)) {
					alert("金额必须是整数,或者是带两位小数的数字");
					return
				}
				if (parseFloat(k[j].value) == 0) {
					alert("金额必须大于0");
					return
				}
			}
			r.push(p)
		}
		$("#next").attr("disabled","disabled");
		 $.ajax({
             type: "POST",
             url:"batchPay/do_calcurate.html",
             data: $('#pay_form').serialize(),
             success : function(g, f) {
            	g=eval("("+g+")");
 				switch (parseInt(g.code)) {
 				case -1:
 					location.href = "batchPay/listBatchPay.html?batchId="+ g.batchId;
 					break;
 				default:
 					if (g.str == "") {
 						location.href = "batchPay/batchSubmission.html"
 					} else {
 						$("#next").removeAttr("disabled");
 						alert(g.str);
 					}
 					break
 				}
 			},
 			error : function(g, f, h) {
 				$("#next").removeAttr("disabled");
 			}
         });
		///$('#pay_form').submit();
}
</script>
<link href="css/table.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	<form action="batchPay/do_calcurate.html" method="post" id="pay_form"> 
		<div class="biaoge">
		<div class="main_content">
			<nav>
				<div class='znav'>
					<ul style="width: 5000px;">
						<li class="first"><i class="icon-home"></i></li>
						<li>批量付款</li>
						<li class="last">逐笔录入</li>
					</ul>
				</div>
			</nav>
			<div>
				<select id="bank_hidden"  name="bank_hidden" class="form-control" style="width:60%;margin-left:20%;display: none;">
								<c:forEach var="vo" items="${list}">
									<option value="${vo.bankname}">${vo.bankname}</option>
								</c:forEach>
				 </select>
			</div>
			<div style='clear:both;'></div>
				<div class='ztab'>
					<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 116px;">银行</th>
							<th style="width: 179px;">卡号</th>
							<th style="width: 179px;">姓名</th>
							<th style="width: 240px;">金额（元）</th>
							<th style="width: 240px;">操作</th>
						</tr>
					</thead>
					<tbody id="mytable">
					 	<tr>
								<td style="font-size: 18px; width: 30%;"><select
									name="bank" class="form-control"
									style="width: 60%; margin-left: 20%;">
										<c:forEach var="vo" items="${list}">
											<option value="${vo.bankname}">${vo.bankname}</option>
										</c:forEach>
								</select></td>
								<td><input type="text" name="card" class="form-control"
									style="width: 80%; margin-left: 10%;" /></td>
								<td><input type="text" name="name" class="form-control"
									style="width: 60%; margin-left: 20%;" /></td>
								<td><input type="text" name="money" class="form-control"
									style="width: 60%; margin-left: 20%;" /></td>
								<td style="width: 10%;">
									<button type="button" class="btn btn-danger"
										onclick="delTr(this)">删除</button>
								</td>
							</tr>
					</tbody>
				</table>
				</div>
		</div>
	</div>
		<%-- <div class="xgjcxx">
			<div
				style="border: 1px solid #ccc; background-image: url(User/images/menu_bg_x.jpg); width: 100%; height: 40px; line-height: 40px; font-size: 15px; text-align: left; font-weight: bold; color: #333">
				&nbsp;&nbsp;&nbsp;&nbsp;添加列表</div>
	
			<div style="width: 100%; height: auto; border: 1px solid #CCC; border-top: 0px;"
				id="ntj">
				<div id="txcontent">
					<div class="selectclass" style="height:5rem;">
						<select id="bank_hidden"  name="bank_hidden" class="form-control" style="width:60%;margin-left:20%;display: none;">
								<c:forEach var="vo" items="${list}">
									<option value="${vo.bankname}">${vo.bankname}</option>
								</c:forEach>
						</select>
					</div>
					<div class="selectclass"
						style="height: auto; overflow: hidden; border-bottom: 0px;">
						<table cellpadding="0" cellspacing="0" border="0" >
							<tr style="background-color: #5d7b9d; color: #fff;">
								<td style="width: 30%">银行</td>
								<td>卡号</td>
								<td>姓名</td>
								<td>金额(元)</td>
								<td style="width: 10%;">操作</td>
							</tr>
							<tr>
									
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div> --%>
		<div class="selectclass" style="text-align:center;">
			<span  class="btn btn-primary" onclick="vaild_form()" id="next">下一步</span>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default" onclick="addTr()">新增</button>
		</div>
		<div style="clear: left;"></div>
	</form>
</body>
</html>