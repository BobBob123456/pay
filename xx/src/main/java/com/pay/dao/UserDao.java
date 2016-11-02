package com.pay.dao;


import java.util.Map;

import com.pay.pojo.User;

public interface UserDao extends CommonDao<User> {
	
	String getXjUserId(Map<String,Object> map);
	
}