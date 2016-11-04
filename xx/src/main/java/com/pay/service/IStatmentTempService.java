package com.pay.service;

import java.util.List;
import java.util.Map;

import com.pay.pojo.StatmentTemp;


public interface IStatmentTempService {

	int addStatmentBatch(Integer userId,List<StatmentTemp> list,String batchId);
	
	int getCount(Map<String,Object> map);
	
	List<StatmentTemp> getStatmentTempList(Map<String,Object> map,int currentPage);
	
	float getStatmentMoney(Map<String,Object> map);
}
