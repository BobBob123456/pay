package com.pay.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.BankDaoImpl;
import com.pay.pojo.Bank;
import com.pay.service.IBankService;

@Service("bankService")
public class BankService implements IBankService {

	@Resource
	private BankDaoImpl bankDaoImpl;
	
	public int getBankByUserId(Map<String, Object> map) {
		
		return (int) bankDaoImpl.getNumber(map, "getBankByUserId");
	}

	public Bank getBankName(Map<String, Object> map) {
		return bankDaoImpl.getObjectByCondition(map);
	}

}
