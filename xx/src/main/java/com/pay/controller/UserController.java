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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pay.base.DateUtil;
import com.pay.base.EmailUtil;
import com.pay.base.Utils;
import com.pay.base.constant.CommonConstant;
import com.pay.pojo.Money;
import com.pay.pojo.MoneyBd;
import com.pay.pojo.Order;
import com.pay.pojo.Sjfl;
import com.pay.pojo.TkList;
import com.pay.pojo.User;
import com.pay.pojo.UserBasicInformation;
import com.pay.pojo.UserSafeTyInformation;
import com.pay.service.IBankService;
import com.pay.service.IDlDateService;
import com.pay.service.IMoneyBdService;
import com.pay.service.IMoneyService;
import com.pay.service.IOrderService;
import com.pay.service.ISjflService;
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
	private IUserbasicinformationService userbasicinformationService;

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
	
	@Resource
	private ISjflService sjflService;

	@RequestMapping("/register")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		return "register";
	}

	@RequestMapping("/doRegister")
	public void doRegister(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("UserName");
		String loginPassWord = request.getParameter("LoginPassWord");
		String verify = request.getParameter("verify");
		String email = request.getParameter("Email");
		// 检验
		String key = "registercode";
		String sessionid = request.getSession().getId();
		String code = (String) request.getSession().getAttribute(sessionid + key);

		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(loginPassWord)) {
			Utils.writer_out(response, "用户名或密码为空！");
			return;
		}

		if (!com.pay.base.StringUtils.isEmail(email)) {
			 Utils.writer_out(response, "邮箱地址格式不正确！");
			return;
		}

		if (userService.selectByUserName(userName) != null) {
			Utils.writer_out(response, "用户名已存在！");
			return;
		}
		if (userService.selectUserByEmail(email) != null) {
			Utils.writer_out(response, "邮箱已经注册过！");
			return;
		}
		if (StringUtils.isEmpty(verify)) {
			Utils.writer_out(response, "验证码为空！");
			return;
		}
		if (!verify.toLowerCase().equals(code.toLowerCase())) {
			Utils.writer_out(response, "验证码不正确！");
			return;
		}

		// 添加
		User user = new User();
		user.setUsername(userName);
		user.setLoginpassword(Utils.MD5(loginPassWord));
		String activate = com.pay.base.StringUtils.getStringRandom(64);
		user.setRegdate(new Date());
		user.setActivate(activate);
		user.setStatus(0);
		user.setUsertype(0);
		user.setMbk(0);
		user.setSjuserid(0);
		user.setGmwttk(0);
		user.setEmail(email);
		user.setKey(Utils.MD5(loginPassWord+userName));
		userService.add(user);
		// 发送邮件
		sendEmail(request, user.getId(), activate, email);
		Utils.writer_out(response, "ok");
		return;
	}
	
	@RequestMapping("/do_activate")
	public void do_activate(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj=new JSONObject();
		String verify = request.getParameter("verify");
		String email = request.getParameter("email");
		// 检验
		String key = "imagecode";
		String sessionid = request.getSession().getId();
		String code = (String) request.getSession().getAttribute(sessionid + key);
		User u=userService.selectUserByEmail(email);
		if (!com.pay.base.StringUtils.isEmail(email)) {
			 obj.put("msg", "邮箱地址格式不正确！");
			 Utils.writer_out(response, obj.toString());
			return;
		}
		if (u == null) {
			obj.put("msg", "该邮箱地址未被注册！");
			Utils.writer_out(response, obj.toString());
			return;
		}
		if (!verify.toLowerCase().equals(code.toLowerCase())) {
			obj.put("msg", "验证码不正确！");
			Utils.writer_out(response, obj.toString());
			return;
		}
		// 修改
		User user = new User();
		user.setId(u.getId());
		String activate = com.pay.base.StringUtils.getStringRandom(64);
		user.setActivate(activate);
		userService.update(user);
		// 发送邮件
		sendEmail(request, u.getId(), activate, email);
		obj.put("msg", "ok");
		obj.put("uname", u.getUsername());
		Utils.writer_out(response, obj.toString());
	}

	private void sendEmail(HttpServletRequest request, Integer id, String activate, String userName) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";

		String urlPath = basePath + "user/activate.html?id=" + id + "&activate=" + activate;

		StringBuilder str = new StringBuilder();
		str.append("亲爱的会员：");
		str.append("<a target=\"_blank\" href=\"mailto:" + userName + "\">" + userName + "</a>您好！");
		str.append("<br>感谢您注册国盛通账户！<br>您现在可以激活您的国盛通账户，激活成功后，您可以使用国盛通提供的各种支付服务。");
		str.append("<br><a target=\"_blank\" href=" + urlPath + ">点此激活国盛通账户 </a>");
		str.append(" <br>如果上述文字点击无效，请把下面网页地址复制到浏览器地址栏中打开：<br>");
		str.append("<a target=\"_blank\" href=" + urlPath + ">");
		str.append(urlPath + "</a>");
		str.append("<br>此为系统邮件，请勿回复<br>请保管好您的邮箱，避免国盛通账户被他人盗用");
		str.append("<br>如有任何疑问，可查看 国盛通相关规则，国盛通网站访问");
		str.append(
				"<a target=\"_blank\" href=\"http://pay.jiangxin123.cn/\">http://pay.jiangxin123.cn/</a><br>Copyright 国盛通 2013 All Right Reserved");

		EmailUtil.send(userName, "国盛通账户激活邮件", str.toString());
	}
	
	@RequestMapping("/resetpwd")
	public String resetpwd(HttpServletRequest request, HttpServletResponse response){
		String id=request.getParameter("id");
		String activate=request.getParameter("activate");
		int status=0;
		if(id!=null){
			User user=userService.getUserById(Integer.valueOf(id));
			if(user!=null&&user.getActivate().equals(activate)){
				status=1;
			}
		}
		request.setAttribute("id", id);
		request.setAttribute("activate", activate);
		request.setAttribute("status", status);
		return "resetpwd";
	}
	
	@RequestMapping("/do_forget_pwd")
	public void do_forget_pwd(HttpServletRequest request, HttpServletResponse response){
		JSONObject obj=new JSONObject();
		String id=request.getParameter("id");
		String activate=request.getParameter("activate");
		String password=request.getParameter("password");
		String verify = request.getParameter("verify");
		String key = "imagecode";
		String sessionid = request.getSession().getId();
		String code = (String) request.getSession().getAttribute(sessionid + key);
		if (!verify.toLowerCase().equals(code.toLowerCase())) {
			obj.put("msg", "验证码不正确！");
			Utils.writer_out(response, obj.toString());
			return;
		}
		if (id != null) {
			User user = userService.getUserById(Integer.valueOf(id));
			if (user != null && user.getActivate().equals(activate)) {
				user.setLoginpassword(Utils.MD5(password));
				obj.put("msg", "ok");
				Utils.writer_out(response, obj.toString());
				return;
			}else{
				obj.put("msg", "修改密码地址已失效！");
				Utils.writer_out(response, obj.toString());
				return;
			}
		}else{
			obj.put("msg", "修改密码地址已失效！");
			Utils.writer_out(response, obj.toString());
			return;
		}
	}
	
	
	@RequestMapping("/sendPwdEmail")
	public void sendPwdEmail(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj=new JSONObject();
		String verify = request.getParameter("verify");
		String email = request.getParameter("email");
		// 检验
		String key = "imagecode";
		String sessionid = request.getSession().getId();
		String code = (String) request.getSession().getAttribute(sessionid + key);
		if (!verify.toLowerCase().equals(code.toLowerCase())) {
			obj.put("msg", "验证码不正确！");
			Utils.writer_out(response, obj.toString());
			return;
		}
		User user = userService.selectUserByEmail(email);
		if (user != null) {
			Integer id=user.getId();
			String activate = com.pay.base.StringUtils.getStringRandom(64);
			// 修改
			User u = new User();
			u.setId(user.getId());
			u.setActivate(activate);
			userService.update(u);
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
					+ "/";
			String urlPath = basePath + "user/resetpwd.html?id=" + id + "&activate=" + activate;
			StringBuilder str = new StringBuilder();
			str.append("亲爱的会员：");
			str.append("<a target=\"_blank\" href=\"mailto:" + email + "\">" + email + "</a>您好！");
			str.append("<br>感谢您使用国盛通！<br>您现在可以修改您的国盛通账户密码");
			str.append("<br><a target=\"_blank\" href=" + urlPath + ">点此修改国盛通账户密码 </a>");
			str.append(" <br>如果上述文字点击无效，请把下面网页地址复制到浏览器地址栏中打开：<br>");
			str.append("<a target=\"_blank\" href=" + urlPath + ">");
			str.append(urlPath + "</a>");
			str.append("<br>此为系统邮件，请勿回复<br>请保管好您的邮箱，避免国盛通账户被他人盗用");
			str.append("<br>如有任何疑问，可查看 国盛通相关规则，国盛通网站访问");
			str.append(
					"<a target=\"_blank\" href=\"http://pay.jiangxin123.cn/\">http://pay.jiangxin123.cn/</a><br>Copyright 国盛通 2013 All Right Reserved");
			EmailUtil.send(email, "国盛通账户激活邮件", str.toString());
			obj.put("msg", "ok");
			Utils.writer_out(response, obj.toString());
		} else {
			Utils.writer_out(response, "用户不存在");
		}
	}

	@RequestMapping("/succeedReg")
	public ModelAndView succeedReg(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("uname", request.getParameter("uname"));
		modelAndView.setViewName("user/succeedReg");
		return modelAndView;
	}

	@RequestMapping("/go_activate")
	public String go_activate(HttpServletRequest request, HttpServletResponse response) {
		return "go_activate";
	}
	@RequestMapping("/forget_password")
	public String forget_password(HttpServletRequest request, HttpServletResponse response) {
		return "forget_password";
	}
	
	
	@RequestMapping("/activate")
	public ModelAndView activate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();

		String id = request.getParameter("id");
		String activate = request.getParameter("activate");
		User user = userService.getUserById(Integer.parseInt(id));

		String resp = "fail";
		if (user != null && user.getActivate().equalsIgnoreCase(activate)) {
			if (user.getStatus() == 0) {
				resp = "ok";
				user.setStatus(1);
				userService.update(user);
			} else {
				
				resp = "activated";
			}
		}
		modelAndView.addObject("result", resp);
		modelAndView.setViewName("user/activate");
		return modelAndView;
	}

	@RequestMapping("/cfEmail")
	public void cfEmail(HttpServletRequest request, HttpServletResponse response) {
		User user = userService.selectByUserName(request.getParameter("uname"));
		if (user != null) {
			sendEmail(request, user.getId(), user.getActivate(), user.getEmail());
			Utils.writer_out(response, "ok");
		} else {
			Utils.writer_out(response, "用户不存在");
		}
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
		// String userType = request.getParameter("UserType");
		/** 验证码放入session的key值,并从session中取得验证码 **/
		String key = "imagecode";
		String sessionid = request.getSession().getId();
		String code = (String) request.getSession().getAttribute(sessionid + key);
		response.setContentType("text/html;charset=UTF-8");
		/** 验证传输过来的数据 **/
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(loginPassWord)) {
			Utils.writer_out(response, "用户名或密码为空！");
			return;
		}
		if (StringUtils.isEmpty(verify)) {
			Utils.writer_out(response, "验证码为空！");
			return;
		}
		if (!verify.toLowerCase().equals(code.toLowerCase())) {
			Utils.writer_out(response, "验证码不正确！");
			return;
		}
		User user = this.userService.selectByUserName(userName);
		if (user == null) {
			Utils.writer_out(response, "用户名或密码不正确！");
			return;
		}
		if (!user.getLoginpassword().equals(Utils.MD5(loginPassWord))) {
			Utils.writer_out(response, "用户名或密码不正确！");
			return;
		}
		if (user.getStatus() == 0) {
			Utils.writer_out(response, "您的账号没有激活，请激活帐号后再登录");
			return;
		}
		if (user.getStatus() == 2) {
			Utils.writer_out(response, "您的账号已被锁定！");
			return;
		}
		User u = new User();
		u.setId(user.getId());
		u.setUsersessionid(sessionid);
		userService.updateSelective(u);
		if(StringUtils.isEmpty(user.getCode())){
			if(user.getUsertype()==6){
				request.getSession().setAttribute("is_set", 1);
			}else{
				request.getSession().setAttribute("is_set", 0);
			}
		}else{
			request.getSession().setAttribute("is_set", 1);
		}
		/** 把用户信息放到session里面去 **/
		request.getSession().setAttribute("userName", user.getUsername());
		request.getSession().setAttribute("userType", user.getUsertype());
		request.getSession().setAttribute("userId", user.getId());
		Utils.writer_out(response, "ok");

	}
	
	@RequestMapping("/set_code")
	public void set_code(HttpServletResponse response, HttpServletRequest request) {
		
	}

	@RequestMapping("/user_basic")
	public String user_basic(HttpServletResponse response, HttpServletRequest request) {
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		UserBasicInformation info = this.userbasicinformationService.getUBIByUserId(userId);
		request.setAttribute("info", info);
		return "user/basic";
	}

	@RequestMapping("/aqxx")
	public String aqxx(HttpServletResponse response, HttpServletRequest request) {
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		UserSafeTyInformation info = userSafeTyInformationService.getUBIByUserId(userId);
		request.setAttribute("info", info);
		return "user/aqxx";
	}

	@RequestMapping("/EditPayPassWord")
	public void EditPayPassWord(HttpServletResponse response, HttpServletRequest request) {
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String Y_PayPassWord = request.getParameter("Y_PayPassWord");
		String X_PayPassWord = request.getParameter("X_PayPassWord");
		String XX_PayPassWord = request.getParameter("XX_PayPassWord");
		if (StringUtils.isEmpty(Y_PayPassWord)) {
			Utils.writer_out(response, "<script type='text/javascript'>alert('原支付密码不能为空！'); history.go(-1);</script>");
		} else {
			UserSafeTyInformation user = this.userSafeTyInformationService.getUBIByUserId(userId);
			if (!user.getPaypassword().equals(Utils.MD5(Y_PayPassWord))) {
				Utils.writer_out(response,
						"<script type='text/javascript'>alert('原支付密码错误！'); history.go(-1);</script>");
			} else {
				if (StringUtils.isEmpty(X_PayPassWord)) {
					Utils.writer_out(response,
							"<script type='text/javascript'>alert('新支付密码不能为空！'); history.go(-1);</script>");
				} else {
					if (!X_PayPassWord.equals(XX_PayPassWord)) {
						Utils.writer_out(response,
								"<script type='text/javascript'>alert('两次支付密码输入不一致！'); history.go(-1);</script>");
					} else {
						UserSafeTyInformation t = new UserSafeTyInformation();
						t.setUserid(userId);
						t.setPaypassword(Utils.MD5(X_PayPassWord));
						;
						int result = this.userSafeTyInformationService.updateSelective(t);
						if (result == 1) {
							Utils.writer_out(response,
									"<script type='text/javascript'>alert('支付密码修改成功！'); location.href='history.go(-1);'</script>");
						} else {
							Utils.writer_out(response,
									"<script type='text/javascript'>alert('支付密码修改失败，请稍后再试！'); location.href='history.go(-1);'</script>");
						}
					}
				}
			}
		}
	}

	@RequestMapping("/logou")
	public void logou(HttpServletResponse response, HttpServletRequest request) throws IOException {
		request.getSession().removeAttribute("userId");
		request.getSession().removeAttribute("money");
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("userType");
		response.sendRedirect("login.html");
	}

	/** 获取账号余额 **/
	@RequestMapping("/account_money")
	public void account_money(HttpServletResponse response, HttpServletRequest request) {
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Money money = this.moneyService.selectByUserId(userId);
		if(money==null){
			Utils.writer_out(response,"0");
		}else{
			Utils.writer_out(response, money.getMoney().toString());
		}
	}

	@RequestMapping("/user_index")
	public String wyjyjl(HttpServletResponse response, HttpServletRequest request) throws IOException {
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Integer userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
		if(userType==6){
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			response.sendRedirect(basePath+"backend/order_manage.html");
		}
		String cur = request.getParameter("cur");
		String order_number = request.getParameter("order_number");
		String ksjy_date = request.getParameter("ksjy_date");
		String jsjy_date = request.getParameter("jsjy_date");
		if (StringUtils.isEmpty(ksjy_date) && StringUtils.isEmpty(jsjy_date)) {
			ksjy_date = DateUtil.getDate(new Date()) + " 00:00:00";
			jsjy_date = DateUtil.getDate(new Date()) + " 23:59:59";
		}
		String status = request.getParameter("status");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("order_number", order_number);
		map.put("status", status);
		int orderNum = this.orderService.getSuccessCount(map);
		float orderMoney = this.orderService.getSuccessMoney(map);
		int total = this.orderService.getcount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<Order> list = this.orderService.getOrders(map, currentPage);
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

	/** 收支明细 **/
	@RequestMapping("/income_expenses_detail")
	public String income_expenses_detail(HttpServletResponse response, HttpServletRequest request) {
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String cur = request.getParameter("cur");
		String ksjy_date = request.getParameter("ksjy_date");
		String jsjy_date = request.getParameter("jsjy_date");
		String lx = request.getParameter("lx");
		if (StringUtils.isEmpty(ksjy_date) && StringUtils.isEmpty(jsjy_date)) {
			ksjy_date = DateUtil.getDate(new Date()) + " 00:00:00";
			jsjy_date = DateUtil.getDate(new Date()) + " 23:59:59";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("lx", lx);
		int total = this.moneyBdService.getMoneyBdCount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<MoneyBd> list = this.moneyBdService.getMoneyBdList(map, currentPage);
		request.setAttribute("status", lx);
		request.setAttribute("total", total);
		request.setAttribute("ksjy_date", ksjy_date);
		request.setAttribute("jsjy_date", jsjy_date);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		return "user/zjbdjl";
	}

	/** 结算记录 **/
	@RequestMapping("/settlement")
	public String settlement(HttpServletResponse response, HttpServletRequest request) {
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String cur = request.getParameter("cur");
		String ksjy_date = request.getParameter("ksjy_date");
		String jsjy_date = request.getParameter("jsjy_date");
		String zt = request.getParameter("zt");
		if (StringUtils.isEmpty(ksjy_date) && StringUtils.isEmpty(jsjy_date)) {
			ksjy_date = DateUtil.getDate(new Date()) + " 00:00:00";
			jsjy_date = DateUtil.getDate(new Date()) + " 23:59:59";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("zt", zt);
		int total = this.tkListService.getSettlementCount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<TkList> list = tkListService.getSettlementList(map, currentPage);
		float success_money = tkListService.getSuccessMoney(map);
		int success_count = tkListService.getSuccessCount(map);
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
	public String ptzz(HttpServletResponse response, HttpServletRequest request) {
		String cur = request.getParameter("cur");
		String ksjy_date = request.getParameter("ksjy_date");
		String jsjy_date = request.getParameter("jsjy_date");
		if (StringUtils.isEmpty(ksjy_date) && StringUtils.isEmpty(jsjy_date)) {
			ksjy_date = DateUtil.getDate(new Date()) + " 00:00:00";
			jsjy_date = DateUtil.getDate(new Date()) + " 23:59:59";
		}
		String status = request.getParameter("status");
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("status", status);

		int total = this.orderService.getPtCount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<Order> list = this.orderService.getPtOrders(map, currentPage);
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
	public String pt_settlement(HttpServletResponse response, HttpServletRequest request) {
		String cur = request.getParameter("cur");
		String ksjy_date = request.getParameter("ksjy_date");
		String jsjy_date = request.getParameter("jsjy_date");
		if (StringUtils.isEmpty(ksjy_date) && StringUtils.isEmpty(jsjy_date)) {
			ksjy_date = DateUtil.getDate(new Date()) + " 00:00:00";
			jsjy_date = DateUtil.getDate(new Date()) + " 23:59:59";
		}
		String status = request.getParameter("status");
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);

		map.put("status", status);

		int total = this.orderService.getPtSettlementCount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<Order> list = this.orderService.getPtSettlementList(map, currentPage);
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
	public String tjyg(HttpServletResponse response, HttpServletRequest request) {
		String cur = request.getParameter("cur");
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sjuserid", userId);
		int total = this.userService.getOwnerUserCount(map);
		int currentPage = 1;
		if (StringUtils.isEmpty(cur)) {
			currentPage = 1;
		} else {
			currentPage = Integer.valueOf(cur);
		}
		/** 计算总页数 **/
		float p = Float.valueOf(total) / Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
		int totalPage = (int) Math.ceil(p);
		List<User> list = this.userService.getOwnerUserList(map, currentPage);
		Sjfl sjfl=sjflService.getDefaultSjfl(null);
		request.setAttribute("sjfl_obj", sjfl);
		request.setAttribute("total", total);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("users", list);
		return "user/tjyg";
	}


	@RequestMapping("/xj_withdraw")
	public String xj_withdraw(HttpServletResponse response,HttpServletRequest request){
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
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
		map.put("sjuserid", userId);
		String xjUserId=userService.getXjUserId(map);
		map.put("xjUserId", xjUserId);
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
		return "user/xj_withdraw";
	}
	
	
	@RequestMapping("/xj_money_detail")
	public String xj_money_detail(HttpServletResponse response,HttpServletRequest request){
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
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
		map.put("sjuserid", userId);
		String xjUserId=userService.getXjUserId(map);
		map.put("ksjy_date", ksjy_date);
		map.put("jsjy_date", jsjy_date);
		map.put("account", account);
		map.put("order_number",order_number);
		map.put("xjUserId",xjUserId);
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
		return "user/xj_money_detail";
	}
	
	@RequestMapping("/xj_order")
	public String xj_order(HttpServletResponse response,HttpServletRequest request){
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
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
		map.put("sjuserid", userId);
		String xjUserId=userService.getXjUserId(map);
		map.put("xjUserId", xjUserId);
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
		return "user/xj_order";
	}
	
	@RequestMapping("/anquantiwen")
	public void anquantiwen(HttpServletResponse response, HttpServletRequest request) {
		String AffirmTitle = request.getParameter("AffirmTitle");
		String AffirmAnswer = request.getParameter("AffirmAnswer");
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		UserSafeTyInformation info = new UserSafeTyInformation();
		info.setAffirmtitle(AffirmTitle);
		info.setAffirmanswer(AffirmAnswer);
		info.setUserid(userId);
		int result = this.userSafeTyInformationService.updateSelective(info);
		if (result == 1) {
			Utils.writer_out(response,
					"<script type='text/javascript'>alert('设置成功！'); location.href='aqxx.html'</script>");
		} else {
			Utils.writer_out(response,
					"<script type='text/javascript'>alert('设置失败！'); location.href='aqxx.html'</script>");
		}
	}

	@RequestMapping("/basicsave")
	public void basicsave(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String id = request.getParameter("id");
		Integer userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String Compellation = request.getParameter("Compellation");
		String qq = request.getParameter("qq");
		String IdentificationCard = request.getParameter("IdentificationCard");
		String MobilePhone = request.getParameter("MobilePhone");
		String Tel = request.getParameter("Tel");
		String Address = request.getParameter("Address");
		UserBasicInformation info = new UserBasicInformation();
		info.setAddress(Address);
		info.setCompellation(Compellation);
		info.setQq(qq);
		info.setIdentificationcard(IdentificationCard);
		info.setMobilephone(MobilePhone);
		info.setTel(Tel);
		info.setUserid(userId);
		int result = 0;
		if (StringUtils.isEmpty(id)) {
			result = this.userbasicinformationService.addSelective(info);
		} else {
			info.setId(Integer.parseInt(id));
			result = this.userbasicinformationService.updateSelective(info);
		}
		if (result == 1) {
			Utils.writer_out(response,
					"<script type='text/javascript'>alert('修改成功！'); location.href='user_basic.html'</script>");
		}
	}
}