package com.pay.base;

import java.io.UnsupportedEncodingException;


import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class EmailUtil {

	private static String code;

	private static String smtp; // 服务器地址

	private static String sender; // 发件人的邮箱

	private static String name; // 发件人昵称

	private static String userName; // 账号

	private static String password; // 密码

	private static boolean isInit=false;

	public static void init(){
		//Properties pp = PropertiesUtil.readProperties(EmailUtil.class,"/mail.properties");

		code = "UTF-8";
		smtp = "smtp.sina.cn";
		sender = "guo_shengtong@sina.com";
		try {
			name =MimeUtility.encodeText("国盛通");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		userName = "guo_shengtong@sina.com";
		password = "guo123456";
		isInit=true;
	}

	public static boolean send(String address, String title, String msg) {
		if(!isInit){
			init();
		}
		// 发送email
		HtmlEmail email = new HtmlEmail();
		try {
			// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
			email.setHostName(smtp);
			// 字符编码集的设置
			email.setCharset(code);
			// 收件人的邮箱
			email.addTo(address);
			// 发送人的邮箱
			email.setFrom(sender, name);
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			email.setAuthentication(userName, password);
			// 要发送的邮件主题
			email.setSubject(title);
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			email.setMsg(msg);
			// 发送
			email.send();
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		EmailUtil.send("345425170@qq.com", "逗我玩", "这他么是一个a标签<a href=\"http://hao123.com\">这是一个a标签</a>");
	}
}
