package com.pay.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.pay.base.Utils;
import com.pay.pojo.Newlist;
import com.pay.service.INewListService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Resource
	private INewListService newListService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getNews")
	public void getNews(HttpServletRequest request,HttpServletResponse response){
		 Object obj= request.getSession().getAttribute("newsList");
		 List<Newlist> list=null;
		 if(obj==null){
			 list = newListService.getNews();
			 request.getSession().setAttribute("newsList",list);
		 }else{
			 list=(List<Newlist>) obj;
		 }
		 JSONArray a=new JSONArray();
		 a.addAll(list);
		 Utils.writer_out(response, a.toJSONString());
	}
}
