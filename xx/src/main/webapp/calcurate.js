$(document).ready(function() {
	Calcurate.init()
});
var addCount = 1;
var Calcurate = {
	tipElements : [],
	init : function() {
		Calcurate.getAmountRange();
		Calcurate.initEvent();
		Calcurate.getCurUserSafeCode();
		Calcurate.genAuthCodeImg();
		PayCommon.genBankInfoList()
	},
	initEvent : function() {
		$(".auth-code-img").on("click", Calcurate.genAuthCodeImg);
		$(".btn-submit").on("click", Calcurate.postData);
		$(".btn-add").on("click", Calcurate.onAddTR);
		$(".btn-danger").attr("onClick", "Calcurate.getDel(this)")
	},
	getCurUserSafeCode : function() {
		$.ajax({
			type : "POST",
			url : Helper.getRootPath() + "/pub/getCurUser.do",
			cache : false,
			dataType : "json",
			success : function(a) {
				if (a.status) {
					$("#sysSafeCode").val(a.safeCode);
					PayCommon.initSafeCode();
					Calcurate.checkSafeCodeStatus()
				} else {
					Helper.alert(a.errorInfo)
				}
			}
		})
	},
	updatePageSafeCode : function(a) {
		$("#sysSafeCode").val(a);
		Helper.alert("安全码设置成功")
	},
	checkSafeCodeStatus : function() {
		var a = $("#sysSafeCode").val();
		if (a == "0") {
			PayCommon.loadSafeCodeModal(Calcurate.updatePageSafeCode);
			return
		}
	},
	postData : function() {
		var b = $.trim($("#authCode").val());
		if (b == "" || b == null) {
			Helper.alert("验证码不能为空");
			return false
		}
		var q = document.getElementsByName("bankCardType");
		var c = document.getElementsByName("bankCardNo");
		var g = document.getElementsByName("cardName");
		var a = document.getElementsByName("openBank");
		var k = document.getElementsByName("amount");
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
				Helper.alert("卡号不能为空");
				return
			} else {
				var l = /^\d*$/;
				if (!l.exec(n)) {
					Helper.alert("银行卡号必须全为数字");
					return
				}
			}
			if (g[j].value.indexOf("公司") < 0 && q[j].value != "CSCB"
					&& !PayCommon.checkBankCardNo(n)) {
				Helper.alert("银行卡号：【" + n + "】不合法，请重新输入");
				return
			}
			m.push(n)
		}
		for (var j = 0; j < g.length; j++) {
			if (g[j].value == null || "" == g[j].value) {
				Helper.alert("姓名不能为空");
				return
			}
			o.push(g[j].value)
		}
		var e = 0;
		for (var j = 0; j < k.length; j++) {
			var p = k[j].value;
			e = parseFloat(e) + parseFloat(p);
			if ("" == k[j].value) {
				Helper.alert("金额不能为空");
				return
			} else {
				var d = /^\d+(\.\d{1,2})?$/;
				if (!d.test(k[j].value)) {
					Helper.alert("金额必须是整数,或者是带两位小数的数字");
					return
				}
				if (parseFloat(k[j].value) == 0) {
					Helper.alert("金额必须大于0");
					return
				}
			}
			r.push(p)
		}
		$(".btn-primary").attr("disabled", "true");
		$
				.ajax({
					type : "POST",
					data : {
						cardType : h.join(","),
						cardNo : m.join(","),
						cardName : o.join(","),
						openBank : f.join(","),
						amount : r.join(","),
						totalAmount : e,
						authCode : b
					},
					url : Helper.getRootPath() + "/user/checkMoreCardInfo.do",
					cache : false,
					dataType : "json",
					success : function(i) {
						$(".btn-primary").removeAttr("disabled");
						if (i.code == 1) {
							$
									.ajax({
										type : "POST",
										url : Helper.getRootPath()
												+ "/user/payAccountCardBing.do",
										data : {
											cardType : h.join(","),
											cardNo : m.join(","),
											cardName : o.join(","),
											openBank : f.join(","),
											amount : r.join(",")
										},
										cache : false,
										dataType : "json",
										success : function(s) {
											if (s.code == 1) {
												location.href = Helper
														.getWebRootPath()
														+ "/batchPay/listBatchPay.html?batchId="
														+ s.batchId
											} else {
												Helper
														.confirm_one(
																s.errorMsg,
																function() {
																	location.href = Helper
																			.getWebRootPath()
																			+ "/batchPay/calcurate.html"
																})
											}
										}
									})
						} else {
							Helper.alert(i.msg);
							Calcurate.genAuthCodeImg()
						}
					}
				})
	},
	getAmountRange : function() {
		$.ajax({
			type : "POST",
			url : Helper.getRootPath() + "/user/merchantBoundCheck.do",
			cache : false,
			dataType : "json",
			success : function(a) {
				if (a.code == 1) {
					$(".max-remit-amount").html(a.MaxAmount)
				} else {
					Helper.alert(a.msg)
				}
			}
		})
	},
	onAddTR : function() {
		addCount++;
		$("#dt_a tbody")
				.append(
						"<tr><td><select name='bankCardType' style='margin-bottom: 0px'></select></td><td><input  type='text' name='bankCardNo' class='span3' maxlength='19' style='margin-bottom: 0px' /></td><td><input type='text'  name='cardName' class='span2' maxlength='15' style='margin-bottom: 0px'/></td><td><input  type='text' name='amount' class='span2' onblur='Calcurate.checkAmount(this);'  maxlength='12' style='margin-bottom: 0px'/></td><td><a class='btn btn-small btn-danger' href='#'>删除</a></td></tr>");
		PayCommon.genBankInfoList();
		$(".btn-danger").attr("onClick", "Calcurate.getDel(this)")
	},
	getDel : function(a) {
		if (addCount > 1) {
			$(a).parent().parent().remove();
			addCount--
		}
	},
	genAuthCodeImg : function() {
		var a = Helper.genAuthCode(21);
		$("#authCodeImg").attr("src", a)
	},
	checkAmount : function(b) {
		var a = /^\d+(\.\d{1,2})?$/;
		if (!a.test(b.value)) {
			Helper.alert("金额必须是整数,或者是带两位小数的数字");
			return
		}
		if (parseFloat(b.value) == 0) {
			Helper.alert("金额必须大于0");
			return
		}
	}
};