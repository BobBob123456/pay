package com.pay.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.SjapiDaoImpl;
import com.pay.pojo.Sjapi;
import com.pay.service.ISjapiService;

@Service("sjapiService")
public class SjapiService implements ISjapiService {

	@Resource
	private SjapiDaoImpl sjapiDaoImpl;
	
	public int getCount() {
		return sjapiDaoImpl.getCount(null);
	}

	public List<Sjapi> getList() {
		return sjapiDaoImpl.getObjectListBySelectSqlId(null, 0, 0, "getList");
	}

	
	
}
