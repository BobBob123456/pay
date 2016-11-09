package com.pay.service;

import java.util.Map;

import com.pay.pojo.Sjfl;

public interface ISjflService {
	
	/**获取默认的商户费率**/
	Sjfl getDefaultSjfl(Map<String, Object> map);
	
	/**添加或者修改商户费率**/
	int addAndUpdateSjfl(String userId,Sjfl Sjfl);

}
