package com.pay.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.MoneyDaoImpl;
import com.pay.pojo.Money;
import com.pay.service.IMoneyService;

@Service("moneyService")
public class MoneyServiceImpl implements IMoneyService {

	@Resource
	private MoneyDaoImpl moneyDaoImpl;
	
	public Money selectByPrimaryKey(Integer id) {
		return this.moneyDaoImpl.get(String.valueOf(id));
	}

	public Money selectByUserId(Integer userId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		return this.moneyDaoImpl.getObjectByCondition(map);
	}

}
