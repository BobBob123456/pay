package com.pay.controller;


import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.pay.base.BankUtil;
import com.pay.base.DateUtil;
import com.pay.base.ExcelUtil;
import com.pay.base.StringUtils;
import com.pay.base.Utils;
import com.pay.base.constant.CommonConstant;
import com.pay.pojo.BankPay;
import com.pay.pojo.Money;
import com.pay.pojo.StatmentTemp;
import com.pay.service.IBankPayService;
import com.pay.service.IMoneyService;
import com.pay.service.IStatmentTempService;
import com.pay.service.ITkListService;


@Controller
@RequestMapping("/batchPay")
public class BatchPayController {
	
	@Resource
	private ITkListService tkListService;
	
	@Resource
	private IBankPayService bankPayService;
	
	
	@Resource
	private IStatmentTempService statmentTempService;
	
	@Resource 
	private IMoneyService moneyService;
	
	@RequestMapping("/calcurate")
	public String calcurate(HttpServletRequest request,HttpServletResponse response){
		List<BankPay> list=bankPayService.getBankPayList();
		request.setAttribute("list", list);
		return "batchpay/calcurate";
	}
	
	@RequestMapping("/batchSubmission")
	public String batchSubmission(HttpServletRequest request,HttpServletResponse response){
		List<BankPay> list=bankPayService.getBankPayList();
		request.setAttribute("list", list);
		return "batchpay/batchSubmission";
	}
	@RequestMapping("/listBatchPay")
	public String listBatchPay(HttpServletRequest request,HttpServletResponse response){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String batchId=request.getParameter("batchId");
		String cur=request.getParameter("cur");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("batchId", batchId);
	    int total=statmentTempService.getCount(map);
	    int currentPage=1;
	    if(StringUtils.isEmpty(cur)){
	    	currentPage=1;
	    }else{
	    	currentPage=Integer.valueOf(cur);
	    }
	    /**计算总页数**/
	    float p=Float.valueOf(total)/Float.valueOf(CommonConstant.PAGE_SIZE_DEFAULT);
	    int totalPage=(int) Math.ceil(p);
	    float totalMoney=statmentTempService.getStatmentMoney(map);
	    List<StatmentTemp> list=statmentTempService.getStatmentTempList(map, currentPage);
	    request.setAttribute("batchId", batchId);
	    request.setAttribute("totalMoney", totalMoney);
	    request.setAttribute("total", total);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("list", list);		
		return "batchpay/listBatchPay";
	}
	
	
	
	/*** 
     * 上传文件 用@RequestParam注解来指定表单上的file为MultipartFile 
     *  
     * @param file 
     * @return 
     */  
    @RequestMapping("/fileUpload")  
    public void fileUpload(HttpServletRequest request,HttpServletResponse response, @RequestParam("myfile") MultipartFile file) {  
    	Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
    	JSONObject obj=new JSONObject();
    	// 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
            	String fileName=file.getOriginalFilename();
            	String suffix= fileName.substring(fileName.lastIndexOf(".")+1);
                // 文件保存路径  
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
                        +userId+"_"+DateUtil.getDate(new Date())+"_"+System.currentTimeMillis()+"."+suffix; 
                // 转存文件  
                List<String> list=null;
                InputStream in=file.getInputStream();
                file.transferTo(new File(filePath));
                if(suffix.equals("txt")||suffix.equals("csv")){
                	 String encode=Utils.getTxtEncode(in);
                	 list=	Utils.readTxtAndCsv(filePath,encode,in);
                }else if(suffix.equals("xlsx")){
                	list=ExcelUtil.readerXlsx(in);
                }else if(suffix.equals("xls")){
                	list=ExcelUtil.readerXls(in);
                }
                List<StatmentTemp> statmentList= check_data_vaild(list, response,userId);
                if(statmentList!=null){
                	String s=userId+"_"+System.currentTimeMillis();
                	String batchId=Utils.MD5(s);
                	this.statmentTempService.addStatmentBatch(userId, statmentList, batchId);
                	obj.put("code", -1);
                	obj.put("str", "");
                	obj.put("batchId", batchId);
                	Utils.writer_out(response, obj.toJSONString());
                }
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }else{
        	obj.put("code", 1);
        	obj.put("str", "文件不能为空！");
        	Utils.writer_out(response, obj.toJSONString());
        }
        // 重定向  
   }  
	
    
    
	public List<StatmentTemp> check_data_vaild(List<String> list,HttpServletResponse response,Integer userId){
		/**获取账号余额**/
		Money m= this.moneyService.selectByUserId(userId);
		JSONObject obj=new JSONObject();
		List<StatmentTemp> statmentList=new ArrayList<StatmentTemp>();
		obj.put("code", 1);
		float total = 0;
		boolean flag=true;
		for(int i=0;i<list.size();++i){
			if(i==0)
				continue;
			String str=list.get(i);
			String [] strs =str.split(",");
			if(strs.length==4){
				String bank=strs[0];
				String card=strs[1];
				String name=strs[2];
				String money=strs[3];
				if(StringUtils.isEmpty(bank)){
		        	obj.put("str", "第"+i+"条记录，银行不能为空");
		        	flag=false;
		        	break;
				}
				if(StringUtils.isEmpty(card)){
					obj.put("str", "第"+i+"条记录，卡号不能为空");
					flag=false;
		        	break;
				}
				if(StringUtils.isEmpty(name)){
					obj.put("str", "第"+i+"条记录，姓名不能为空");
					flag=false;
		        	break;
				}
				if(StringUtils.isEmpty(money)){
					obj.put("str", "第"+i+"条记录，金额不能为空");
					flag=false;
		        	break;
				}
				if(!Utils.check_bank(card)){
					obj.put("str", "第"+i+"条记录，银行卡不合法");
					flag=false;
		        	break;
				}
				String bankName=BankUtil.getNameOfBank(card);
				if(StringUtils.isEmpty(bankName)){
					obj.put("str", "第"+i+"条记录，输入的卡号与选择的银行不匹配");
					flag=false;
					break;
				}
				if(!StringUtils.isEmpty(bankName)){
					String [] bs=bankName.split("·");
					if(bs.length>1){
						if(bank.indexOf(bs[0])==-1){
							obj.put("str", "第"+i+"条记录，输入的卡号属于["+bs[0]+"]");
							flag=false;
							break;
						}
					}
				}
				if(!Utils.check_number(money)){
					obj.put("str", "第"+i+"条记录，金额不正确");
					flag=false;
		        	break;
				}
				if(i==1){
					total=Float.valueOf(money);
				}else{
					total=total+Float.valueOf(money);
				}
				BigDecimal bd=new BigDecimal(total);   
				//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)   
				bd=bd.setScale(6, BigDecimal.ROUND_HALF_UP);  
				if(!Utils.compare_money(m.getMoney(), bd)){
					obj.put("str", "提现金额不能大于余额");
					flag=false;
		        	break;
				}
				StatmentTemp t=new StatmentTemp();
				t.setBank(bank);
				t.setCard(card);
				t.setName(name);
				t.setMoney(Float.valueOf(money));
			
				statmentList.add(t);
			}
		}
		if(flag==false){
			statmentList=null;
			Utils.writer_out(response, obj.toJSONString());
		}
		return statmentList;
	}
	
    
    
	@RequestMapping("/do_calcurate")
	public void do_calcurate(HttpServletRequest request, HttpServletResponse response){
		Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
		String [] bank=request.getParameterValues("bank");
		String [] name = request.getParameterValues("name"); 
		String [] card = request.getParameterValues("card"); 
		String [] money = request.getParameterValues("money"); 
		JSONObject obj=new JSONObject();
		List<String>  list=new ArrayList<String>();
		list.add("银行,卡号,姓名,金额");
		for(int i=0;i<name.length;i++){  
			String str=bank[i]+","+card[i]+","+name[i]+","+money[i];
			list.add(str);
		}  
		List<StatmentTemp> statmentList= check_data_vaild(list, response,userId);
        if(statmentList!=null){
         	String s=userId+"_"+System.currentTimeMillis();
         	String batchId=Utils.MD5(s);
         	this.statmentTempService.addStatmentBatch(userId, statmentList, batchId);
         	obj.put("code", -1);
         	obj.put("str", "");
         	obj.put("batchId", batchId);
         	Utils.writer_out(response, obj.toJSONString());
         }
	}


}
