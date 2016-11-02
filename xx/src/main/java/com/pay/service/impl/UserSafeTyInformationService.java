package com.pay.service.impl;


import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.UserSafeTyInformationDaoImpl;
import com.pay.pojo.UserSafeTyInformation;

import com.pay.service.IUserSafeTyInformationService;

@Service("userSafeTyInformationService")
public class UserSafeTyInformationService implements IUserSafeTyInformationService {

	@Resource
	private UserSafeTyInformationDaoImpl userSafeTyInformationDaoImpl;
	
	public UserSafeTyInformation getUBIByUserId(Integer userId) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		return this.userSafeTyInformationDaoImpl.getObjectByCondition(map);
	}

	public int add(UserSafeTyInformation t) {
		
		return 0;
	}

	public int addSelective(UserSafeTyInformation t) {
		
		return userSafeTyInformationDaoImpl.addSelective(t);
	}

	public int update(UserSafeTyInformation t) {
		
		return 0;
	}

	public int updateSelective(UserSafeTyInformation t) {
		
		return userSafeTyInformationDaoImpl.updateSelective(t);
	}

	public int delete(UserSafeTyInformation t) {
		
		return 0;
	}

}
