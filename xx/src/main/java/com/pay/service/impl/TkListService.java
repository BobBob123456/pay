package com.pay.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.base.constant.CommonConstant;
import com.pay.dao.impl.TkListDaoImpl;
import com.pay.pojo.TkList;
import com.pay.service.ITkListService;


@Service("tkListService")
public class TkListService implements ITkListService {

	@Resource
	private TkListDaoImpl tkListDaoImpl;
	
	public float getwclmoney(Map<String, Object> map) {
		
		return this.tkListDaoImpl.getNumber(map, "getwclmoney");
	}

	public float getyclmoney(Map<String, Object> map) {
		
		return this.tkListDaoImpl.getNumber(map, "getyclmoney");
	}

	public int getSettlementCount(Map<String, Object> map) {
		return this.tkListDaoImpl.getObjectCountBySelecSqltId(map,"getSettlementCount");
	}

	public List<TkList> getSettlementList(Map<String, Object> map, int currentPage) {
		return this.tkListDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT, "getSettlementList");
	}

	public float getSuccessMoney(Map<String, Object> map) {
		return this.tkListDaoImpl.getNumber(map, "getSuccessMoney");
	}

	public int getSuccessCount(Map<String, Object> map) {
		return (int) this.tkListDaoImpl.getNumber(map, "getSuccessCount");
	}

	public int save_tk(TkList tk) {
		return this.tkListDaoImpl.addSelective(tk);
	}

}
