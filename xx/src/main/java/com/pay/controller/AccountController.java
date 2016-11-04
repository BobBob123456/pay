package com.pay.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.base.Utils;
import com.pay.pojo.BankPay;
import com.pay.pojo.User;
import com.pay.service.IBankPayService;
import com.pay.service.IUserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IBankPayService bankPayService;
	
	@RequestMapping("/update_password")
	public String update_password(HttpServletResponse response,HttpServletRequest request){
		return "account/update_password";
	}
	
	@RequestMapping("/cardSet")
	public String cardSet(HttpServletResponse response,HttpServletRequest request){
		List<BankPay> list=bankPayService.getBankPayList();
		request.setAttribute("list", list);
		return "account/card_set";
	}
	
	@RequestMapping("/withdraw")
	public String withdraw(HttpServletResponse response,HttpServletRequest request){
		return "account/withdraw";
	}
	
	@RequestMapping("/retrieveSafeCode")
	public String retrieveSafeCode(HttpServletResponse response,HttpServletRequest request){
		return "account/retrieveSafeCode";
	}
	
	@RequestMapping("/merchantKey")
	public String merchantKey(HttpServletResponse response,HttpServletRequest request){
		return "account/merchantKey";
	}
	
	
	
	
	@RequestMapping("/EditLoginPassWord")
	public void EditLoginPassWord(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String Y_LoginPassWord=request.getParameter("Y_LoginPassWord");
		String X_LoginPassWord=request.getParameter("X_LoginPassWord");
		String XX_LoginPassWord=request.getParameter("XX_LoginPassWord");
		if(StringUtils.isEmpty(Y_LoginPassWord)){
			Utils.writer_out(response, "<script type='text/javascript'>alert('原密码不能为空！'); history.go(-1);</script>");
		}else{
			User user=this.userService.getUserById(userId);
			if(!user.getLoginpassword().equals(Utils.MD5(Y_LoginPassWord))){
				Utils.writer_out(response, "<script type='text/javascript'>alert('原密码错误！'); history.go(-1);</script>");
			}else{
				if(StringUtils.isEmpty(X_LoginPassWord)){
					Utils.writer_out(response, "<script type='text/javascript'>alert('新密码不能为空！'); history.go(-1);</script>");
				}else{
					if(!X_LoginPassWord.equals(XX_LoginPassWord)){
						Utils.writer_out(response, "<script type='text/javascript'>alert('两次新密码输入不一致！'); history.go(-1);</script>");
					}else{
						User t=new User();
						t.setId(userId);
						t.setLoginpassword(Utils.MD5(X_LoginPassWord));
						int result=this.userService.updateSelective(t);
						if(result==1){
							request.getSession().setAttribute("userId", "");
							Utils.writer_out(response, "<script type='text/javascript'>alert('登录密码修改成功！'); location.href='login.html'</script>");
						}else{
							Utils.writer_out(response, "<script type='text/javascript'>alert('修改失败，请稍后再试！'); location.href='history.go(-1);'</script>");
						}
					}
				}
			}
		}
	}
	
	

}
