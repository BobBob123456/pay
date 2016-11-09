package com.pay.service;

import java.util.List;
import java.util.Map;

import com.pay.pojo.User;


public interface IUserService extends ICommonService<User>  {
	public User getUserById(int userId);
	
	public User selectByUserName(String userName);
	
	/**获得下级用户的数量**/
	int getOwnerUserCount(Map<String, Object> map);
	
	/**获得下级用户的列表**/
	List<User> getOwnerUserList(Map<String, Object> map,int currentPage);
	
	/**获取所有用户数**/
	int getAllUserCount(Map<String, Object> map);
	
	/**获取所有用户**/
	List<User> getAllUser(Map<String, Object> map,int currentPage);
	
	/**获取下级用户的id**/
	
	String getXjUserId(Map<String, Object> map);
}
