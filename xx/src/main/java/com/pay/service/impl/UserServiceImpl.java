package com.pay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.pay.base.constant.CommonConstant;
import com.pay.dao.impl.UserDaoImpl;
import com.pay.pojo.User;
import com.pay.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserDaoImpl userDaoImpl;
	

	public User getUserById(int userId) {
		//Map<String, Object> map=new HashMap<String, Object>();
		//int count= this.userDaoImpl.getCount(map);
		return this.userDaoImpl.get(String.valueOf(userId));
	}

	/**
	 * 根据账号查询用户信息
	 */
	public User selectByUserName(String userName) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userName", userName);
		List<User> users= this.userDaoImpl.getObjectListBySelectSqlId(map, 0, 0, "selectByUserName");
		return users.size()==0?null:users.get(0);
	}

	public int add(User t) {
		return 0;
	}

	public int addSelective(User t) {
		return 0;
	}

	public int update(User t) {
		return 0;
	}

	
	public int updateSelective(User t) {
		return this.userDaoImpl.updateSelective(t);
	}

	public int delete(User t) {
		
		return 0;
	}

	public int getOwnerUserCount(Map<String, Object> map) {
		return this.userDaoImpl.getObjectCountBySelecSqltId(map, "getOwnerUserCount");
	}

	public List<User> getOwnerUserList(Map<String, Object> map,int currentPage) {
		return this.userDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT, "getOwnerUserList");
	}




	
	
	
}
