package com.pay.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.NewlistDaoImpl;
import com.pay.pojo.Newlist;
import com.pay.service.INewListService;

@Service("newListService")
public class NewListService implements INewListService {

	@Resource
	private NewlistDaoImpl newListDaoImpl;
	
	
	public List<Newlist> getNews() {
		return newListDaoImpl.getObjectListBySelectSqlId(new HashMap<String, Object>(), 1, 4,"getNews");
	}

}
