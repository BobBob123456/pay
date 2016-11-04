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
	
	@RequestMapping("/getNews")
	public void getNews(HttpServletRequest request,HttpServletResponse response){
		 List<Newlist> list= newListService.getNews();
		 JSONArray a=new JSONArray();
		 a.addAll(list);
		// System.out.println(a.toJSONString());
		 Utils.writer_out(response, a.toJSONString());
	}
}
