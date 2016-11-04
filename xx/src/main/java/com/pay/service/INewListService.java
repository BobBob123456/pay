package com.pay.service;

import java.util.List;

import com.pay.pojo.Newlist;

public interface INewListService {

	/**获取最近四条发布的公告数据**/
	List<Newlist> getNews();
}
