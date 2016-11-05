package com.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backend")
public class BackEndController {
	
	
	@RequestMapping("/backend_login")
	public String backend_login(HttpServletResponse response,HttpServletRequest request){
		return "backend/login";
	}
	
	@RequestMapping("/do_login")
	public void do_login(HttpServletResponse response,HttpServletRequest request){
		
	}
}
