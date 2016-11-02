package com.pay.service;

import com.pay.pojo.UserSafeTyInformation;

public interface IUserSafeTyInformationService extends ICommonService<UserSafeTyInformation> {

	UserSafeTyInformation getUBIByUserId(Integer userId);
	
	
}
