package com.pay.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.pay.base.BankUtil;
import com.pay.base.Utils;
import com.pay.pojo.Bank;
import com.pay.pojo.BankPay;
import com.pay.pojo.Money;
import com.pay.pojo.StatmentTemp;
import com.pay.pojo.User;
import com.pay.service.IBankPayService;
import com.pay.service.IBankService;
import com.pay.service.IMoneyService;
import com.pay.service.IStatmentTempService;
import com.pay.service.IUserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IBankPayService bankPayService;
	
	@Resource
	private IBankService bankService;
	
	@Resource
	private IStatmentTempService statmentTempService;
	
	@Resource 
	private IMoneyService moneyService;
	
	    
	@RequestMapping("/update_password")
	public String update_password(HttpServletResponse response,HttpServletRequest request){
		return "account/update_password";
	}
	
	@RequestMapping("/cardSet")
	public String cardSet(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		List<BankPay> list=bankPayService.getBankPayList();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		Bank bank=bankService.getBankByUserId(map);
		request.setAttribute("bank", bank);
		request.setAttribute("list", list);
		return "account/card_set";
	}
	
	@RequestMapping("/do_cardSet")
	public void do_cardSet(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String bankcompellation=request.getParameter("bankcompellation");
		String id=request.getParameter("id");
		String bankname=request.getParameter("bankname");
		String bankbranch=request.getParameter("bankbranch");
		String bankaccountnumber=request.getParameter("bankaccountnumber");
		User user=userService.getUserById(userId);
		String code=request.getParameter("code");
		if(!user.getCode().equals(Utils.MD5(code))){
			Utils.writer_out(response, "安全码不正确");
			return;
		}
		if(!Utils.check_bank(bankaccountnumber)){
			Utils.writer_out(response, "银行卡不合法");
        	return;
		}
		String bankName=BankUtil.getNameOfBank(bankaccountnumber);
		if(StringUtils.isEmpty(bankName)){
			Utils.writer_out(response, "输入的卡号与选择的银行不匹配");
			return;
		}
		if(!StringUtils.isEmpty(bankName)){
			String [] bs=bankName.split("·");
			if(bs.length>1){
				if(bankname.indexOf(bs[0])==-1){
					Utils.writer_out(response, "输入的卡号属于["+bs[0]+"]");
					return;
				}
			}
		}
		Bank bank=new Bank();
		bank.setBankaccountnumber(bankaccountnumber);
		bank.setBankbranch(bankbranch);
		bank.setBankcompellation(bankcompellation);
		bank.setBankname(bankname);
		bank.setUserid(userId);
		if(!StringUtils.isEmpty(id)){
			bank.setId(Integer.valueOf(id));
		}
		int result=bankService.addAndUpdate(bank);
		if(result==1){
			Utils.writer_out(response, "设置成功");
		}else{
			Utils.writer_out(response, "设置失败");
		}
	}
	
	
	@RequestMapping("/withdraw")
	public String withdraw(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		Bank bank=bankService.getBankByUserId(map);
		request.setAttribute("bank", bank);
		return "account/withdraw";
	}
	
	@RequestMapping("/do_withdraw")
	public void do_withdraw(HttpServletResponse response,HttpServletRequest request){
		JSONObject obj=new JSONObject();
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String money=request.getParameter("money");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		Bank bank=bankService.getBankByUserId(map);
		User user=userService.getUserById(userId);
		String code=request.getParameter("code");
		if(!user.getCode().equals(Utils.MD5(code))){
			obj.put("status", -1);
			obj.put("str", "安全码不正确");
			Utils.writer_out(response,obj.toString());
			return;
		}
		/**获取账号余额**/
		Money m= this.moneyService.selectByUserId(userId);
		BigDecimal bd=new BigDecimal(money);   
		//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)   
		bd=bd.setScale(6, BigDecimal.ROUND_HALF_UP);  
		if(!Utils.compare_money(m.getMoney(), bd)){
			obj.put("status", -1);
			obj.put("str", "提现金额不能大于余额");
			Utils.writer_out(response,obj.toString());
			return;
		}
		StatmentTemp st=new StatmentTemp();
		st.setBank(bank.getBankname());
		st.setCard(bank.getBankaccountnumber());
		st.setMoney(Float.valueOf(money));
		st.setName(bank.getBankcompellation());
	 	String s=userId+"_"+System.currentTimeMillis();
     	String batchId=Utils.MD5(s);
     	List<StatmentTemp> statmentList=new ArrayList<StatmentTemp>();
     	statmentList.add(st);
     	statmentTempService.addStatmentBatch(userId, statmentList, batchId);
     	obj.put("status", 1);
     	obj.put("batchId", batchId);
     	Utils.writer_out(response, obj.toJSONString());

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
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
							Utils.writer_out(response, "<script type='text/javascript'>alert('登录密码修改成功！'); location.href='"+basePath+"user/login.html'</script>");
						}else{
							Utils.writer_out(response, "<script type='text/javascript'>alert('修改失败，请稍后再试！'); location.href='history.go(-1);'</script>");
						}
					}
				}
			}
		}
	}
	
	@RequestMapping("/set_code")
	public void set_code(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String vaild_code=request.getParameter("vaild_code");
		String code=request.getParameter("code");
		String sure_code=request.getParameter("sure_code");
		String key = "imagecode";
		String sessionid = request.getSession().getId();
		String verify = (String) request.getSession().getAttribute(sessionid + key);
		System.out.println(verify);
		System.out.println(vaild_code);
		User user=new User();
		user.setId(userId);
		if(!sure_code.equals(code)){
			Utils.writer_out(response, "两次安全码不相同");
			return;
		}
		if(!verify.toLowerCase().equals(vaild_code.toLowerCase())){
			Utils.writer_out(response, "验证码不正确");
        	return;
		}
		user.setCode(Utils.MD5(code));
		userService.update(user);
		request.getSession().removeAttribute("is_set");
		request.getSession().setAttribute("is_set", 1);
		Utils.writer_out(response, "设置成功");
		
	}
}
