package com.pay.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.BankPayDaoImpl;
import com.pay.pojo.BankPay;
import com.pay.service.IBankPayService;

@Service("bankPayService")
public class BankPayService implements IBankPayService{

	@Resource
	private BankPayDaoImpl bankPayDaoImpl;
	
	public List<BankPay> getBankPayList() {
		
		return bankPayDaoImpl.getObjectListBySelectSqlId(new HashMap<String, Object>(), 0, 0, "getBankPayList");
	}

}
