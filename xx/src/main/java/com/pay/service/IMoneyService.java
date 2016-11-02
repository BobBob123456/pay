package com.pay.service;

import com.pay.pojo.Money;

public interface IMoneyService {
	
	 Money selectByPrimaryKey(Integer id);
	 
	 Money selectByUserId(Integer userId);
}
