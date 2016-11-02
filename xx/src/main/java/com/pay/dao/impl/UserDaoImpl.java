package com.pay.dao.impl;




import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pay.dao.UserDao;
import com.pay.pojo.User;

@Repository("userDaoImpl")
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao {

	public String getXjUserId(Map<String,Object> map) {
		return this.getSqlSession().selectOne(getStatementPrefix() + "." + "getXjUserId", map);
	}



}
