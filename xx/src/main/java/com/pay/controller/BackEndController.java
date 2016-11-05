package com.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backend")
public class BackEndController {
	
	@RequestMapping("/backend_index")
	public void backend_index(HttpServletResponse response,HttpServletRequest request){
		System.out.println("woai");
		//return "backend/backend_index";
	}

}
