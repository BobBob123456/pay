package com.pay.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.StatmentTempDaoImpl;
import com.pay.pojo.StatmentTemp;
import com.pay.service.IStatmentTempService;

@Service("statmentTempService")
public class StatmentTempService implements IStatmentTempService {
	
	@Resource
	private StatmentTempDaoImpl statmentTempDaoImpl;

	public int addStatmentBatch(Integer userId, List<StatmentTemp> list,String batchId) {
		for (StatmentTemp st : list) {
			st.setUserId(userId);
			st.setBatchid(batchId);
			statmentTempDaoImpl.addSelective(st);
		}
		return 1;
	}
	
}
