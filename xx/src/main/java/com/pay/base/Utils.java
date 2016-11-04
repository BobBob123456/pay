package com.pay.base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.input.BOMInputStream;

/***
 * 工具类
 * @author bob
 *
 */
public class Utils {

	/** 输出文字到客户端 **/
	public static void writer_out(HttpServletResponse response, String str) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * md5 加密方法
	 * @param s 加密的参数
	 * @return 加密后返回的字符串
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {  
         String ip = request.getHeader("X-Forwarded-For");  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("Proxy-Client-IP");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("WL-Proxy-Client-IP");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("HTTP_CLIENT_IP");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getRemoteAddr();  
         }  
         return ip;  
     }  
	
	/**
	 * 获取txt输入流的编码格式
	 * @param in
	 * @return
	 */
	public static String getTxtEncode(InputStream in){
		String encode="";
		 byte[] b = new byte[3];  
         try {
			in.read(b);
			in.close();  
	         if (b[0] == -17 && b[1] == -69 && b[2] == -65)  
	            encode= "UTF-8";  
	         else  
	        	 encode= "GBK";
		} catch (IOException e) {
			e.printStackTrace();
		}  
        return encode;
	}
	
	 /** 
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 
     * 该校验的过程：  
     * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。  
     * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去9），再求和。  
     * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。 
     */  
    public static char getBankCardCheckCode(String nonCheckCodeCardId){    
           if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0    
                   || !nonCheckCodeCardId.matches("\\d+")||nonCheckCodeCardId.trim().length()<15  
                   ||nonCheckCodeCardId.trim().length()>18) {    
               //如果传的数据不合法返回N    
               System.out.println("银行卡号不合法！");  
               return 'N';  
           }    
           char[] chs = nonCheckCodeCardId.trim().toCharArray();    
           int luhmSum = 0;   
           // 执行luh算法  
           for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {    
               int k = chs[i] - '0';    
               if(j % 2 == 0) {  //偶数位处理  
                   k *= 2;    
                   k = k / 10 + k % 10;    
               }    
               luhmSum += k;               
           }    
           return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');    
       }  
    /**
     * 检查银行卡是否合法
     * @param bankNo
     * @return
     */
    public static boolean check_bank(String bankNo){
    	 char res = getBankCardCheckCode(bankNo.substring(0, bankNo.length()-1));  
         if(res!='N'){  
             String charJX = bankNo.substring(bankNo.length()-1);//存入不含校验位的卡号  
             //System.out.println("银行卡的校验位为："+charJX);  
             if(charJX.equals(String.valueOf(res))){  
                 System.out.println("银行卡合法！");  
                 return true;
             }else{  
                 System.out.println("银行卡不合法！"); 
                 return false;
             }  
         }else{
        	 return false;
         }
    }
    
 // 读文件
 	public static List<String> readTxtAndCsv(String filePath,String encode,InputStream in) {
 		BufferedReader reader = null;
 		List<String> list=null;
 		try {
 			if(encode.equals("UTF-8")){
 				 reader = new BufferedReader(new InputStreamReader(new BOMInputStream(in),encode)); 
 			}else{
 				reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),encode)); 
 			}
 			list=new ArrayList<String>();
 			String str = null;
 			while ((str = reader.readLine()) != null) {
 				list.add(str);
 			}
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		} finally {
 			try {
 				reader.close();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}
 		return list;
 	}
 	
 	 /**
 	  * 验证字符串是否是数字，或者是带两位小数的数字
 	  * @param str
 	  * @return
 	  */
     public static boolean check_number(String str) {
     	String regex="^\\d+(\\.\\d{1,2})?$";
     	Pattern pattern = Pattern.compile(regex);
     	Matcher matcher = pattern.matcher(str);
     	return matcher.matches();
     }
     
     
     /**
 	 * 比较两个数 a小 return false,b 大于或者等于return true
 	 * @param a
 	 * @param b
 	 * @return
 	 */
 	public static Boolean compare_money(BigDecimal a,BigDecimal b){
 		if(a.compareTo(b)==-1){
 			return false;
 		}else{
 			return true;
 		}
 	}

}
