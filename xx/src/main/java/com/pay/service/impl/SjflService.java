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

	
}
