package com.pay.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.pay.dao.impl.SjflDaoImpl;
import com.pay.pojo.Sjfl;
import com.pay.service.ISjflService;

@Service("sjflService")
public class SjflService implements ISjflService {

	@Resource
	private SjflDaoImpl sjflDaoImpl;
	
	public Sjfl getDefaultSjfl(Map<String, Object> map) {
		return sjflDaoImpl.getObjectByCondition(map);
	}

	public int addAndUpdateSjfl(String userId, Sjfl sjfl) {
		Sjfl sj=sjflDaoImpl.get(userId);
		int result=0;
		if(sj==null){
			result=sjflDaoImpl.addSelective(sjfl);
		}else{
			result=sjflDaoImpl.updateSelective(sjfl);
		}
		return result;
	}

}
