package com.pay.service;

import com.pay.pojo.UserApiInformation;


public interface IUserApiInformationService {

	UserApiInformation getUBIByUserId(Integer userId);
}

