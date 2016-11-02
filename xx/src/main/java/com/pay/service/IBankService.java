package com.pay.service;

import java.util.Map;

import com.pay.pojo.Bank;

public interface IBankService {
	
	int getBankByUserId(Map<String, Object> map);
	
	Bank getBankName(Map<String, Object> map);
}
