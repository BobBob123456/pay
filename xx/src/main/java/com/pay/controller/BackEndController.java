package com.pay.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.base.DateUtil;
import com.pay.base.constant.CommonConstant;
import com.pay.pojo.MoneyBd;
import com.pay.pojo.Order;
import com.pay.pojo.Sjfl;
import com.pay.pojo.TkList;
import com.pay.pojo.User;
import com.pay.service.IMoneyBdService;
import com.pay.service.IOrderService;
import com.pay.service.ISjflService;
import com.pay.service.ITkListService;
import com.pay.service.IUserService;

@Controller
@RequestMapping("/backend")
public class BackEndController {
	
	@Resource
	private IOrderService orderService;
	
	@Resource 
	private ITkListService tkListService;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private ISjflService sjflService;
	
	@Resource
	private IMoneyBdService moneyBdService;
	
	@RequestMapping("/order_manage")
	public String order_manage(HttpServletResponse response,HttpServletRequest request){
		String cur = request.getParameter("cur");
		String order_number = request.getParameter("order_number");
		String ksjy_date = request.getParameter("ksjy_date");
		String account=request.getParameter("account");
		String jsjy_date = request.getParameter("jsjy_date");
		if (StringUtils.isEmpty(ksjy_date) && StringUtils.isEmpty(jsjy_date)) {
			ksjy_date = DateUtil.getDate(new Date()) + " 00:00:00";
			jsjy_date = DateUtil.getDate(new Date()) + " 23:59:59";
		}
		String status = request.getParameter("status");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("order_number", order_number);
		map.put("account", account);
		map.put("status", status);
		int orderNum=0;
		float orderMoney=0;
		if(status==null){
			 status="";
		}
		if(status.equals("")||status.equals("1")){
			 orderNum = orderService.getAllOrderSuccessCount(map);
			 orderMoney = orderService.getAllOrderSuccessMoney(map);
		}
		int total = this.orderService.getAllOrderCount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<Order> list = orderService.getAllOrder(map, currentPage);
		request.setAttribute("account", account);
		request.setAttribute("status", status);
		request.setAttribute("total", total);
		request.setAttribute("ksjy_date", ksjy_date);
		request.setAttribute("jsjy_date", jsjy_date);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("orders", list);
		request.setAttribute("daymoney", orderMoney);
		request.setAttribute("daynum", orderNum);
		return "backend/order_manage";
	}
	
	@RequestMapping("/user_manage")
	public String user_manage(HttpServletResponse response,HttpServletRequest request){
		String cur = request.getParameter("cur");
		String ksjy_date = request.getParameter("ksjy_date");
		String account=request.getParameter("account");
		String jsjy_date = request.getParameter("jsjy_date");
		String status = request.getParameter("status");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("account", account);
		map.put("status", status);
		int total = userService.getAllUserCount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<User> list = userService.getAllUser(map, currentPage);
		Sjfl sjfl=sjflService.getDefaultSjfl(null);
		request.setAttribute("sjfl_obj", sjfl);
		request.setAttribute("account", account);
		request.setAttribute("status", status);
		request.setAttribute("total", total);
		request.setAttribute("ksjy_date", ksjy_date);
		request.setAttribute("jsjy_date", jsjy_date);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("users", list);
		return "backend/user_manage";
	}
	
	@RequestMapping("/withdraw_manage")
	public String withdraw__manage(HttpServletResponse response,HttpServletRequest request){
		String cur = request.getParameter("cur");
		String order_number = request.getParameter("order_number");
		String ksjy_date = request.getParameter("ksjy_date");
		String account=request.getParameter("account");
		String jsjy_date = request.getParameter("jsjy_date");
		String zt = request.getParameter("zt")==null?"":request.getParameter("zt");;
		String name=request.getParameter("name");
		String card=request.getParameter("card");
		if (StringUtils.isEmpty(ksjy_date) && StringUtils.isEmpty(jsjy_date)) {
			ksjy_date = DateUtil.getDate(new Date()) + " 00:00:00";
			jsjy_date = DateUtil.getDate(new Date()) + " 23:59:59";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("order_number", order_number);
		map.put("account", account);
		map.put("zt",zt);
		map.put("name",name);
		map.put("card",card);
		int total = tkListService.getAllWithdrawCount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<TkList> list = tkListService.getAllWithdrawList(map, currentPage);
		int successNum=0;int failNum=0;
		float successMoney=0;float failMoney=0;
		if(StringUtils.isEmpty(zt)||zt.equals(2)){
			map.put("zt",2);
			successNum=tkListService.getWithdrawCount(map);
			successMoney=tkListService.getWithdrawMoney(map);
		}
		if(StringUtils.isEmpty(zt)||zt.equals(3)){
			map.put("zt",3);
			failNum=tkListService.getWithdrawCount(map);
			failMoney=tkListService.getWithdrawMoney(map);
		}	
		
		request.setAttribute("account", account);
		request.setAttribute("zt", zt);
		request.setAttribute("total", total);
		request.setAttribute("ksjy_date", ksjy_date);
		request.setAttribute("jsjy_date", jsjy_date);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("tklist", list);
		request.setAttribute("successNum", successNum);
		request.setAttribute("successMoney", successMoney);
		request.setAttribute("failNum", failNum);
		request.setAttribute("failMoney", failMoney);
		request.setAttribute("name", name);
		request.setAttribute("card", card);
		return "backend/withdraw_manage";
	}
	
	@RequestMapping("/money_detail")
	public String money_detail(HttpServletResponse response,HttpServletRequest request){
		String cur = request.getParameter("cur");
		String ksjy_date = request.getParameter("ksjy_date");
		String account=request.getParameter("account");
		String jsjy_date = request.getParameter("jsjy_date");
		String order_number=request.getParameter("order_number");
		if (StringUtils.isEmpty(ksjy_date) && StringUtils.isEmpty(jsjy_date)) {
			ksjy_date = DateUtil.getDate(new Date()) + " 00:00:00";
			jsjy_date = DateUtil.getDate(new Date()) + " 23:59:59";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("account", account);
		map.put("order_number",order_number);
		int total = moneyBdService.getAllMoneyBdCount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<MoneyBd> list = moneyBdService.getAllMoneyBdList(map, currentPage);
		request.setAttribute("account", account);
		request.setAttribute("order_number", order_number);
		request.setAttribute("total", total);
		request.setAttribute("ksjy_date", ksjy_date);
		request.setAttribute("jsjy_date", jsjy_date);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		return "backend/money_detail";
	}
}
