package com.pay.service;

import java.util.List;
import java.util.Map;

import com.pay.pojo.User;


public interface IUserService extends ICommonService<User>  {
	public User getUserById(int userId);
	
	public User selectByUserName(String userName);
	
	public User selectByUserEmail(String email);
	
	/**获得下级用户的数量**/
	int getOwnerUserCount(Map<String, Object> map);
	
	/**获得下级用户的列表**/
	List<User> getOwnerUserList(Map<String, Object> map,int currentPage);
	
	/**获取所有用户数**/
	int getAllUserCount(Map<String, Object> map);
	
	/**获取所有用户**/
	List<User> getAllUser(Map<String, Object> map,int currentPage);
	
	public User selectUserByEmail(String email) ;
}
