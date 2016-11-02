package com.pay.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.pojo.BankPay;
import com.pay.pojo.TkList;
import com.pay.service.IBankPayService;
import com.pay.service.ITkListService;


@Controller
@RequestMapping("/batchPay")
public class BatchPayController {
	
	@Resource
	private ITkListService tkListService;
	
	@Resource
	private IBankPayService bankPayService;
	
	@RequestMapping("/calcurate")
	public String calcurate(HttpServletRequest request, HttpServletResponse response){
		List<BankPay> list=bankPayService.getBankPayList();
		request.setAttribute("list", list);
		return "batchpay/calcurate";
	}
	
	@RequestMapping("/do_calcurate")
	public String do_calcurate(HttpServletRequest request, HttpServletResponse response){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String [] bank=request.getParameterValues("bank");
		String [] name = request.getParameterValues("name"); 
		String [] card = request.getParameterValues("card"); 
		String [] money = request.getParameterValues("money"); 
		
		TkList tk=new TkList();
		for(int i=0;i<name.length;i++){  
			tk.setBankname(bank[i]);
			tk.setBankNumber(card[i]);
			tk.setMyname(name[i]);
			tk.setSqDate(new Date());
			tk.setWt(0);
			tk.setZt(0);
			tk.setUserid(userId);
			BigDecimal bd=new BigDecimal(money[i]);
			//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
			bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP); 
			tk.setTkMoney(bd);
			tkListService.save_tk(tk);
		}  
		return "batchpay/calcurate";
	}


}
