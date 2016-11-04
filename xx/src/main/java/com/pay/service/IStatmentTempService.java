package com.pay.service;

import java.util.List;

import com.pay.pojo.StatmentTemp;

public interface IStatmentTempService {

	int addStatmentBatch(Integer userId,List<StatmentTemp> list,String batchId);
}
