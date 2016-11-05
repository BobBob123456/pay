package com.pay.controller;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import org.apache.log4j.Logger;*/
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.base.DateUtil;
import com.pay.base.Utils;
import com.pay.base.constant.CommonConstant;
import com.pay.pojo.Money;
import com.pay.pojo.MoneyBd;
import com.pay.pojo.Order;
import com.pay.pojo.TkList;
import com.pay.pojo.User;
import com.pay.pojo.UserBasicInformation;
import com.pay.pojo.UserSafeTyInformation;
import com.pay.service.IBankService;
import com.pay.service.IDlDateService;
import com.pay.service.IMoneyBdService;
import com.pay.service.IMoneyService;
import com.pay.service.IOrderService;
import com.pay.service.ITkListService;
import com.pay.service.IUserApiInformationService;
import com.pay.service.IUserSafeTyInformationService;
import com.pay.service.IUserService;
import com.pay.service.IUserbasicinformationService;
import com.pay.service.IWttklistService;



@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	/* private Logger logger=Logger.getLogger(UserController.class); */
	@Resource
	private IUserService userService;
	
	@Resource
	private IMoneyService moneyService;
	
	@Resource
	private IUserbasicinformationService  userbasicinformationService;
	
	@Resource
	private IUserApiInformationService userApiInformationService;
	
	@Resource
	private IDlDateService dlDateService;
	
	@Resource
	private IOrderService orderService;
	
	@Resource
	private ITkListService tkListService;
	
	@Resource
	private IWttklistService wttklistService;
	
	@Resource
	private IBankService bankService;
	
	@Resource
	private IUserSafeTyInformationService userSafeTyInformationService;
	
	@Resource
	private IMoneyBdService moneyBdService;
	
	@RequestMapping("/register")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		return "register";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	/**
	 * 登陆处理方法
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/doLogin")
	public void doLogin(HttpServletResponse response, HttpServletRequest request) {
		String userName = request.getParameter("UserName");
		String loginPassWord = request.getParameter("LoginPassWord");
		String verify = request.getParameter("verify");
		//String userType = request.getParameter("UserType");
		/** 验证码放入session的key值,并从session中取得验证码 **/
		String key = "imagecode";
		String sessionid = request.getSession().getId();
		String code = (String) request.getSession().getAttribute(sessionid + key);
		response.setContentType("text/html;charset=UTF-8");
		/** 验证传输过来的数据 **/
		if (StringUtils.isEmpty(userName)||StringUtils.isEmpty(loginPassWord)) {
			Utils.writer_out(response, "用户名或密码为空！");
			return;
		}
		if (StringUtils.isEmpty(verify)) {
			Utils.writer_out(response, "验证码为空！");
			return;
		}
		if(!verify.toLowerCase().equals(code.toLowerCase())){
			Utils.writer_out(response, "验证码不正确！");
			return;
		}
		User user=this.userService.selectByUserName(userName);
		if(user==null){
			Utils.writer_out(response, "用户名或密码不正确！");
			return;
		}
		if(!user.getLoginpassword().equals(Utils.MD5(loginPassWord))){
			Utils.writer_out(response, "用户名或密码不正确！");
			return;
		}
		if(user.getStatus()==0){
			Utils.writer_out(response, "您的账号没有激活，请激活帐号后再登录");
			return;
		}
		if(user.getStatus()==2){
			Utils.writer_out(response,"您的账号已被锁定！");
			return;
		}
		/**把用户信息放到session里面去**/
		request.getSession().setAttribute("userName",user.getUsername());
		request.getSession().setAttribute("userType",user.getUsertype());
		request.getSession().setAttribute("userId",user.getId());
		Utils.writer_out(response, "ok");
	}
	
	@RequestMapping("/user_basic")
	public String user_basic(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		UserBasicInformation info=this.userbasicinformationService.getUBIByUserId(userId);
		request.setAttribute("info", info);
		return "user/basic";
	}
	
	@RequestMapping("/aqxx")
	public String aqxx(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		UserSafeTyInformation info= userSafeTyInformationService.getUBIByUserId(userId);
		request.setAttribute("info", info);
		return "user/aqxx";
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
	
	@RequestMapping("/EditPayPassWord")
	public void EditPayPassWord(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String Y_PayPassWord=request.getParameter("Y_PayPassWord");
		String X_PayPassWord=request.getParameter("X_PayPassWord");
		String XX_PayPassWord=request.getParameter("XX_PayPassWord");
		if(StringUtils.isEmpty(Y_PayPassWord)){
			Utils.writer_out(response, "<script type='text/javascript'>alert('原支付密码不能为空！'); history.go(-1);</script>");
		}else{
			UserSafeTyInformation user=this.userSafeTyInformationService.getUBIByUserId(userId);
			if(!user.getPaypassword().equals(Utils.MD5(Y_PayPassWord))){
				Utils.writer_out(response, "<script type='text/javascript'>alert('原支付密码错误！'); history.go(-1);</script>");
			}else{
				if(StringUtils.isEmpty(X_PayPassWord)){
					Utils.writer_out(response, "<script type='text/javascript'>alert('新支付密码不能为空！'); history.go(-1);</script>");
				}else{
					if(!X_PayPassWord.equals(XX_PayPassWord)){
						Utils.writer_out(response, "<script type='text/javascript'>alert('两次支付密码输入不一致！'); history.go(-1);</script>");
					}else{
						UserSafeTyInformation t=new UserSafeTyInformation();
						t.setUserid(userId);
						t.setPaypassword(Utils.MD5(X_PayPassWord));;
						int result=this.userSafeTyInformationService.updateSelective(t);
						if(result==1){
							Utils.writer_out(response, "<script type='text/javascript'>alert('支付密码修改成功！'); location.href='history.go(-1);'</script>");
						}else{
							Utils.writer_out(response, "<script type='text/javascript'>alert('支付密码修改失败，请稍后再试！'); location.href='history.go(-1);'</script>");
						}
					}
				}
			}
		}
	}
	
	@RequestMapping("/logou")
	public void logou(HttpServletResponse response,HttpServletRequest request) throws IOException{
		System.out.println("woai");
		request.getSession().removeAttribute("userId");
		request.getSession().removeAttribute("money");
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("userType");
		response.sendRedirect("login.html");
	}
	
	/**获取账号余额**/
	@RequestMapping("/account_money")
	public void account_money(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Money money= this.moneyService.selectByUserId(userId);
		Utils.writer_out(response, money.getMoney().toString());
	}
	
	
	@RequestMapping("/user_index")
	public String wyjyjl(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String cur=request.getParameter("cur");
		String order_number=request.getParameter("order_number");
		String ksjy_date=request.getParameter("ksjy_date");
		String jsjy_date=request.getParameter("jsjy_date");
		if(StringUtils.isEmpty(ksjy_date)&&StringUtils.isEmpty(jsjy_date)){
			ksjy_date=DateUtil.getDate(new Date())+" 00:00:00";
			jsjy_date=DateUtil.getDate(new Date())+" 23:59:59";
		}
		String status=request.getParameter("status");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("order_number", order_number);
		map.put("status", status);
	    int orderNum=this.orderService.getSuccessCount(map);
	    float orderMoney=this.orderService.getSuccessMoney(map);
	    int total=this.orderService.getcount(map);
	    int currentPage=1;
	    if(StringUtils.isEmpty(cur)){
	    	currentPage=1;
	    }else{
	    	currentPage=Integer.valueOf(cur);
	    }
	    /**计算总页数**/
	    float p=Float.valueOf(total)/Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
	    int totalPage=(int) Math.ceil(p);
	    List<Order> list=this.orderService.getOrders(map,currentPage);
	    request.setAttribute("status", status);
	    request.setAttribute("total", total);
	    request.setAttribute("ksjy_date", ksjy_date);
	    request.setAttribute("jsjy_date", jsjy_date);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("orders", list);
	    request.setAttribute("daymoney", orderMoney);
	    request.setAttribute("daynum", orderNum);
		return "user/wyjyjl";
	}
	
	/**收支明细**/
	@RequestMapping("/income_expenses_detail")
	public String income_expenses_detail(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String cur=request.getParameter("cur");
		String ksjy_date=request.getParameter("ksjy_date");
		String jsjy_date=request.getParameter("jsjy_date");
		String lx=request.getParameter("lx");
		if(StringUtils.isEmpty(ksjy_date)&&StringUtils.isEmpty(jsjy_date)){
			ksjy_date=DateUtil.getDate(new Date())+" 00:00:00";
			jsjy_date=DateUtil.getDate(new Date())+" 23:59:59";
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("lx", lx);
	    int total=this.moneyBdService.getMoneyBdCount(map);
	    int currentPage=1;
	    if(StringUtils.isEmpty(cur)){
	    	currentPage=1;
	    }else{
	    	currentPage=Integer.valueOf(cur);
	    }
	    /**计算总页数**/
	    float p=Float.valueOf(total)/Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
	    int totalPage=(int) Math.ceil(p);
	    List<MoneyBd> list=this.moneyBdService.getMoneyBdList(map, currentPage);
	    request.setAttribute("status", lx);
	    request.setAttribute("total", total);
	    request.setAttribute("ksjy_date", ksjy_date);
	    request.setAttribute("jsjy_date", jsjy_date);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("list", list);
		return "user/zjbdjl";
	}
	
	/**结算记录**/
	@RequestMapping("/settlement")
	public String settlement(HttpServletResponse response,HttpServletRequest request){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String cur=request.getParameter("cur");
		String ksjy_date=request.getParameter("ksjy_date");
		String jsjy_date=request.getParameter("jsjy_date");
		String zt=request.getParameter("zt");
		if(StringUtils.isEmpty(ksjy_date)&&StringUtils.isEmpty(jsjy_date)){
			ksjy_date=DateUtil.getDate(new Date())+" 00:00:00";
			jsjy_date=DateUtil.getDate(new Date())+" 23:59:59";
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("zt", zt);
	    int total=this.tkListService.getSettlementCount(map);
	    int currentPage=1;
	    if(StringUtils.isEmpty(cur)){
	    	currentPage=1;
	    }else{
	    	currentPage=Integer.valueOf(cur);
	    }
	    /**计算总页数**/
	    float p=Float.valueOf(total)/Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
	    int totalPage=(int) Math.ceil(p);
	    List<TkList> list=tkListService.getSettlementList(map, currentPage);
	    float success_money=tkListService.getSuccessMoney(map);
	    int success_count=tkListService.getSuccessCount(map);
	    request.setAttribute("zt", zt);
	    request.setAttribute("success_money", success_money);
	    request.setAttribute("success_count", success_count);
	    request.setAttribute("total", total);
	    request.setAttribute("ksjy_date", ksjy_date);
	    request.setAttribute("jsjy_date", jsjy_date);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("list", list);
		return "user/settlement";
	}
	
	@RequestMapping("/ptzz")
	public String ptzz(HttpServletResponse response,HttpServletRequest request){
		String cur=request.getParameter("cur");
		String ksjy_date=request.getParameter("ksjy_date");
		String jsjy_date=request.getParameter("jsjy_date");
		if(StringUtils.isEmpty(ksjy_date)&&StringUtils.isEmpty(jsjy_date)){
			ksjy_date=DateUtil.getDate(new Date())+" 00:00:00";
			jsjy_date=DateUtil.getDate(new Date())+" 23:59:59";
		}
		String status=request.getParameter("status");
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("status", status);

	   
	    int total=this.orderService.getPtCount(map);
	    int currentPage=1;
	    if(StringUtils.isEmpty(cur)){
	    	currentPage=1;
	    }else{
	    	currentPage=Integer.valueOf(cur);
	    }
	    /**计算总页数**/
	    float p=Float.valueOf(total)/Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
	    int totalPage=(int) Math.ceil(p);
	    List<Order> list=this.orderService.getPtOrders(map, currentPage);
	    request.setAttribute("status", status);
	    request.setAttribute("total", total);
	    request.setAttribute("ksjy_date", ksjy_date);
	    request.setAttribute("jsjy_date", jsjy_date);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("orders", list);
		return "user/ptzz";
	}
	
	@RequestMapping("/pt_settlement")
	public String pt_settlement(HttpServletResponse response,HttpServletRequest request){
		String cur=request.getParameter("cur");
		String ksjy_date=request.getParameter("ksjy_date");
		String jsjy_date=request.getParameter("jsjy_date");
		if(StringUtils.isEmpty(ksjy_date)&&StringUtils.isEmpty(jsjy_date)){
			ksjy_date=DateUtil.getDate(new Date())+" 00:00:00";
			jsjy_date=DateUtil.getDate(new Date())+" 23:59:59";
		}
		String status=request.getParameter("status");
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
	
		map.put("status", status);

	   
	    int total=this.orderService.getPtSettlementCount(map);
	    int currentPage=1;
	    if(StringUtils.isEmpty(cur)){
	    	currentPage=1;
	    }else{
	    	currentPage=Integer.valueOf(cur);
	    }
	    /**计算总页数**/
	    float p=Float.valueOf(total)/Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
	    int totalPage=(int) Math.ceil(p);
	    List<Order> list=this.orderService.getPtSettlementList(map, currentPage);
	    request.setAttribute("status", status);
	    request.setAttribute("total", total);
	    request.setAttribute("ksjy_date", ksjy_date);
	    request.setAttribute("jsjy_date", jsjy_date);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("orders", list);
		return "user/pt_settlement";
	}
	
	@RequestMapping("/tjyg")
	public String tjyg(HttpServletResponse response,HttpServletRequest request){
		String cur=request.getParameter("cur");
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sjuserid", userId);
	    int total=this.userService.getOwnerUserCount(map);
	    int currentPage=1;
	    if(StringUtils.isEmpty(cur)){
	    	currentPage=1;
	    }else{
	    	currentPage=Integer.valueOf(cur);
	    }
	    /**计算总页数**/
	    float p=Float.valueOf(total)/Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
	    int totalPage=(int) Math.ceil(p);
	    List<User> list=this.userService.getOwnerUserList(map, currentPage);
	    request.setAttribute("total", total);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("users", list);
		return "user/tjyg";
	}
	
	@RequestMapping("/xj_order")
	public String xj_order(HttpServletResponse response,HttpServletRequest request){
		String cur=request.getParameter("cur");
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sjuserid", userId);
	    int total=this.orderService.getXjOrderCount(map);
	    int currentPage=1;
	    if(StringUtils.isEmpty(cur)){
	    	currentPage=1;
	    }else{
	    	currentPage=Integer.valueOf(cur);
	    }
	    /**计算总页数**/
	    
	    float p=Float.valueOf(total)/Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
	    int totalPage=(int) Math.ceil(p);
	    List<Order> list=this.orderService.getXjOrders(map, currentPage);
	    request.setAttribute("total", total);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("orders", list);
		return "user/xj_order";
	}

	@RequestMapping("/anquantiwen")
	public void anquantiwen(HttpServletResponse response,HttpServletRequest request){
		String AffirmTitle=request.getParameter("AffirmTitle");
		String AffirmAnswer=request.getParameter("AffirmAnswer");
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		UserSafeTyInformation info=new UserSafeTyInformation();
		info.setAffirmtitle(AffirmTitle);
		info.setAffirmanswer(AffirmAnswer);
		info.setUserid(userId);
		int result=this.userSafeTyInformationService.updateSelective(info);
		if(result==1){
			Utils.writer_out(response, "<script type='text/javascript'>alert('设置成功！'); location.href='aqxx.html'</script>");
		}else{
			Utils.writer_out(response, "<script type='text/javascript'>alert('设置失败！'); location.href='aqxx.html'</script>");
		}
	}
	
	@RequestMapping("/basicsave")
	public void basicsave(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String id=request.getParameter("id");
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String Compellation=request.getParameter("Compellation");
		String qq=request.getParameter("qq");
		String IdentificationCard=request.getParameter("IdentificationCard");
		String MobilePhone=request.getParameter("MobilePhone");
		String Tel=request.getParameter("Tel");
		String Address=request.getParameter("Address");
		UserBasicInformation info=new UserBasicInformation();
		info.setAddress(Address);
		info.setCompellation(Compellation);
		info.setQq(qq);
		info.setIdentificationcard(IdentificationCard);
		info.setMobilephone(MobilePhone);
		info.setTel(Tel);
		info.setUserid(userId);
		int result=0;
		if(StringUtils.isEmpty(id)){
			result=this.userbasicinformationService.addSelective(info);
		}else{
			info.setId(Integer.parseInt(id));
			result=this.userbasicinformationService.updateSelective(info);
		}
		if(result==1){
			Utils.writer_out(response, "<script type='text/javascript'>alert('修改成功！'); location.href='user_basic.html'</script>");
			//response.sendRedirect("user_basic.html");
		}
	}
	
	/*@RequestMapping("/user_index")
	public String user_index(HttpServletResponse response,HttpServletRequest request) {
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		*//**获取账号余额**//*
		Money money= this.moneyService.selectByUserId(userId);
		String compellation=this.userbasicinformationService.getUBIByUserId(userId).getCompellation();
		Integer zt=this.userApiInformationService.getUBIByUserId(userId).getZt();
		DlDate dlDate=this.dlDateService.getLastDlDate(userId);
		String scip="无";
		String scdate = "无";
		if(dlDate!=null){
			scip=dlDate.getIp();
			scdate=DateUtil.DateToString(dlDate.getDldate(), DateStyle.YYYY_MM_DD_HH_MM) ;
		}
		DlDate ndlDate=new DlDate();
		ndlDate.setIp(Utils.getIpAddr(request));
		ndlDate.setUserid(userId);
		ndlDate.setDldate(new Date());
	    this.dlDateService.add(ndlDate);
	    Map<String, Object> map=new HashMap<String, Object>();
	    map.put("userId", userId);
	    List<Order> orders= this.orderService.getRecentOrder(map);
	    String tradeDate=DateUtil.getDate(new Date());
	    map.put("tradeDate", tradeDate);
	    int orderNum=this.orderService.getDayOrder(map);
	    float orderMoney=this.orderService.getDayMoney(map);
	    
	    float wclmoney= this.tkListService.getwclmoney(map);     //未处理提款金额
	    float yclmoney = this.tkListService.getyclmoney(map);     //已处理提款金额     
        
	    float wclmoney2= this.wttklistService.getwclmoney(map);     //未处理委托提款金额 
	    float yclmoney2 = this.wttklistService.getyclmoney(map);      //已处理委托提款金额    
        
	    float zhye = wclmoney + wclmoney2 +   money.getMoney().floatValue(); 
	    float ytxmoney =  yclmoney+ yclmoney2;  
	    
	    int count =this.bankService.getBankByUserId(map);
        //String BankName =this.bankService.getBankName(map).getBankname();
        
		request.getSession().setAttribute("scip", scip);
		request.getSession().setAttribute("scdate", scdate);
		request.setAttribute("count", count);
		//request.setAttribute("BankName", BankName);
		request.setAttribute("zhye", zhye);
		request.setAttribute("ytxmoney", ytxmoney);
		request.setAttribute("daymoney", orderMoney);
		request.setAttribute("dayorder", orderNum);
		request.setAttribute("orders", orders);
		request.setAttribute("zt", zt);
		request.setAttribute("compellation", compellation);
		request.setAttribute("money", money.getMoney());
		return "user/user_index";
	}*/
	
}