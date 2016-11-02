package com.pay.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.pay.dao.impl.UserApiInformationDaoImpl;
import com.pay.pojo.UserApiInformation;
import com.pay.service.IUserApiInformationService;


@Service("userApiInformationService")
public class UserApiInformationServiceImpl implements IUserApiInformationService {
	@Resource
	private UserApiInformationDaoImpl userApiInformationDaoImpl;
	

	public UserApiInformation getUBIByUserId(Integer userId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		return this.userApiInformationDaoImpl.getObjectByCondition(map);
	}

	
	
	
}
