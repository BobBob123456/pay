package com.pay.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.base.constant.CommonConstant;
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
			st.setCreateDate(new Date());
			statmentTempDaoImpl.addSelective(st);
		}
		return 1;
	}

	public List<StatmentTemp> getStatmentTempList(Map<String, Object> map,int currentPage) {

		return statmentTempDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT, "getStatmentTempList");
	}

	public int getCount(Map<String, Object> map) {
		return statmentTempDaoImpl.getObjectCountBySelecSqltId(map, "getCount");
	}

	/**
	 * 结算总金额
	 */
	public float getStatmentMoney(Map<String, Object> map) {
		return statmentTempDaoImpl.getNumber(map, "getStatmentMoney");
	}

}
