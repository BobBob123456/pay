package com.pay.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.WttklistDaoImpl;
import com.pay.service.IWttklistService;

@Service("wttklistService")
public class WttklistService implements IWttklistService{

	@Resource
	private WttklistDaoImpl wttklistDaoImpl;
	
	public float getwclmoney(Map<String, Object> map) {
		return this.wttklistDaoImpl.getNumber(map, "getwclmoney");
	}

	public float getyclmoney(Map<String, Object> map) {
		return this.wttklistDaoImpl.getNumber(map, "getyclmoney");
	}

}
