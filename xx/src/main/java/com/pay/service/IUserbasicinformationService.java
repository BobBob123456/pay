package com.pay.service;

import com.pay.pojo.UserBasicInformation;

public interface IUserbasicinformationService extends ICommonService<UserBasicInformation> {
	
	
	UserBasicInformation getUBIByUserId(Integer userId);
	
}
