package com.pay.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pay.pojo.Sjapi;
import com.pay.service.ISjapiService;


@Controller
@RequestMapping("/sjapi")
public class SjapiController {
	
	@Resource
	private ISjapiService sjapiService;
	
	@RequestMapping("/sjapi_index")
	public String sjapi_index(HttpServletResponse response,HttpServletRequest request){
		List<Sjapi> list=sjapiService.getList();
		request.setAttribute("list", list);
		return "backend/sjapi";
	}

}
