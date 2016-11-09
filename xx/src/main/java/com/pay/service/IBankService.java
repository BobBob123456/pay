package com.pay.service;

import java.util.Map;

import com.pay.pojo.Bank;

public interface IBankService {
	
	Bank getBankByUserId(Map<String, Object> map);
	
	int addAndUpdate(Bank bank);
}
