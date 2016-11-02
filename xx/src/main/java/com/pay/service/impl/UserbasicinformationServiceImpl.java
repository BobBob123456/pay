package com.pay.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.UserBasicInformationDaoImpl;
import com.pay.pojo.UserBasicInformation;
import com.pay.service.IUserbasicinformationService;

@Service("userbasicinformationService")
public class UserbasicinformationServiceImpl  implements IUserbasicinformationService {
	
	@Resource
	private UserBasicInformationDaoImpl userbasicinformationDaoImpl;
	
	public UserBasicInformation getUBIByUserId(Integer userId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		return this.userbasicinformationDaoImpl.getObjectByCondition(map);
	}

	public int add(UserBasicInformation t) {
		return 0;
	}

	public int addSelective(UserBasicInformation t) {
		return this.userbasicinformationDaoImpl.addSelective(t);
	}

	public int update(UserBasicInformation t) {
		return 0;
	}

	public int updateSelective(UserBasicInformation t) {
		
		return this.userbasicinformationDaoImpl.updateSelective(t);
	}

	public int delete(UserBasicInformation t) {
		
		return 0;
	}

	
	
}
