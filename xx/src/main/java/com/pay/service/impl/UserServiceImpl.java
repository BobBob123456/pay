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
		return userDaoImpl.get(String.valueOf(userId));
	}

	/**
	 * 根据账号查询用户信息
	 */
	public User selectByUserName(String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		List<User> users = userDaoImpl.getObjectListBySelectSqlId(map, 0, 0, "selectByUserName");
		return users.size() == 0 ? null : users.get(0);
	}

	public int add(User t) {
		userDaoImpl.add(t);
		return 0;
	}

	public int addSelective(User t) {

		return 0;
	}

	public int update(User t) {
		userDaoImpl.updateSelective(t);
		return 0;
	}

	public int updateSelective(User t) {
		return userDaoImpl.updateSelective(t);
	}

	public int delete(User t) {
		return 0;
	}

	public int getOwnerUserCount(Map<String, Object> map) {
		return userDaoImpl.getObjectCountBySelecSqltId(map, "getOwnerUserCount");
	}

	public List<User> getOwnerUserList(Map<String, Object> map, int currentPage) {
		return userDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT,
				"getOwnerUserList");
	}

	public int getAllUserCount(Map<String, Object> map) {
		return userDaoImpl.getObjectCountBySelecSqltId(map, "getAllUserCount");
	}

	public List<User> getAllUser(Map<String, Object> map, int currentPage) {
		List<User> list = userDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT,
				"getAllUser");
		for (User user : list) {
			if(user.getSjuserid()!=0){
				User u=getUserById(user.getSjuserid());
				if(u!=null){
					user.setSj_name(u.getUsername());
				}
			}
			map.put("sjuserid",user.getId());
			user.setXj_num(getOwnerUserCount(map));
		}
		return list;
	}

	public String getXjUserId(Map<String, Object> map) {
		return userDaoImpl.getXjUserId(map);
	}

	/**
	 * 根据邮箱查询用户信息
	 */
	public User selectUserByEmail(String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		List<User> users = userDaoImpl.getObjectListBySelectSqlId(map, 0, 0, "selectUserByEmail");
		return users.size() == 0 ? null : users.get(0);
	}
}
