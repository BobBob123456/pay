package com.pay.service;

import java.util.List;
import java.util.Map;

import com.pay.pojo.MoneyBd;

public interface IMoneyBdService {

	/**获取资金变动的条数**/
	int getMoneyBdCount(Map<String,Object> map);
	
	/**获取资金变动的条数**/
	List<MoneyBd> getMoneyBdList(Map<String,Object> map,int currentPage);
	
	/**获取所用用户的资金变动条数**/
	int getAllMoneyBdCount(Map<String,Object> map);
	
	/**获取所有资金变动的条数**/
	List<MoneyBd> getAllMoneyBdList(Map<String,Object> map,int currentPage);
}
