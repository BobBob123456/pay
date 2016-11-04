$(document).ready(function() {
	BatchSubmission.init()
});
var BatchSubmission = {
	tipElements : [],
	init : function() {
		BatchSubmission.getAmountRange();
		BatchSubmission.initEvent();
		BatchSubmission.getCurUserSafeCode();
		BatchSubmission.genAuthCodeImg()
	},
	initEvent : function() {
		$(".auth-code-img").on("click", BatchSubmission.genAuthCodeImg);
		$(".btn-submit").on("click", BatchSubmission.postData)
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
					BatchSubmission.checkSafeCodeStatus()
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
			PayCommon.loadSafeCodeModal(BatchSubmission.updatePageSafeCode);
			return
		}
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
	genAuthCodeImg : function() {
		var a = Helper.genAuthCode(22);
		$("#authCodeImg").attr("src", a)
	},
	postData : function() {
		if ($("#uploadTemplateFile").val() == "") {
			Helper.alert("请选择需要上传的文件");
			return
		}
		var b = $.trim($("#authCode").val());
		if (b == "" || b == null) {
			Helper.alert("验证码不能为空");
			return
		}
		var d = $("#uploadTemplateFile").val();
		var c = /^.(txt|xls|xlsx|csv)$/i;
		var a = d.substr(d.lastIndexOf("."));
		if (!c.test(a)) {
			Helper.alert("请选择正确格式的文件（txt/xls/xlsx/csv）");
			return
		}
		$(".btn-submit").attr("disabled", "true");
		$
				.ajax({
					type : "POST",
					url : Helper.getRootPath() + "/pub/checkAuthCode.do",
					data : {
						authCode : b,
						authCodeType : "random_code_batch_submission"
					},
					cache : false,
					dataType : "json",
					success : function(e) {
						$(".btn-submit").removeAttr("disabled");
						if (e.status) {
							$
									.ajaxFileUpload({
										url : Helper.getRootPath()
												+ "/fileUploadServlet",
										secureuri : false,
										fileElementId : "uploadTemplateFile",
										dataType : "json",
										success : function(g, f) {
											switch (parseInt(g.code)) {
											case -1:
												location.href = Helper
														.getWebRootPath()
														+ "/batchPay/listBatchPay.html?batchId="
														+ g.batchId;
												break;
											case 2:
												Helper.alert("文件大小超过最大值10M");
												BatchSubmission
														.genAuthCodeImg();
												break;
											case 3:
												Helper.alert("上传错误");
												BatchSubmission
														.genAuthCodeImg();
												break;
											case 11:
											case 12:
											case 13:
											case 14:
											case 15:
											case 21:
											case 22:
												if (g.errorInfo == "") {
													location.href = Helper
															.getWebRootPath()
															+ "/batchPay/batchSubmission.html"
												} else {
													Helper.alert(g.errorInfo);
													BatchSubmission
															.genAuthCodeImg()
												}
												break;
											default:
												if (g.errorInfo == "") {
													location.href = Helper
															.getWebRootPath()
															+ "/batchPay/batchSubmission.html"
												} else {
													Helper.alert(g.errorInfo);
													BatchSubmission
															.genAuthCodeImg()
												}
												break
											}
										},
										error : function(g, f, h) {
											$(".btn-submit").removeAttr(
													"disabled");
											location.href = Helper
													.getWebRootPath()
													+ "/batchPay/batchSubmission.html"
										}
									})
						} else {
							Helper.alert(e.errorInfo);
							BatchSubmission.genAuthCodeImg()
						}
					}
				})
	}
};