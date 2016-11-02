package com.pay.base;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
