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
	

	public int getSettlementCount(Map<String, Object> map) {
		return tkListDaoImpl.getObjectCountBySelecSqltId(map,"getSettlementCount");
	}

	public List<TkList> getSettlementList(Map<String, Object> map, int currentPage) {
		return tkListDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT, "getSettlementList");
	}

	public float getSuccessMoney(Map<String, Object> map) {
		return tkListDaoImpl.getNumber(map, "getSuccessMoney");
	}

	public int getSuccessCount(Map<String, Object> map) {
		return (int) tkListDaoImpl.getNumber(map, "getSuccessCount");
	}

	public int save_tk(TkList tk) {
		return tkListDaoImpl.addSelective(tk);
	}

	public int getAllWithdrawCount(Map<String, Object> map) {
		return tkListDaoImpl.getObjectCountBySelecSqltId(map, "getAllWithdrawCount");
	}

	public List<TkList> getAllWithdrawList(Map<String, Object> map, int currentPage) {
		return tkListDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT,"getAllWithdrawList");
	}

	public int getWithdrawCount(Map<String, Object> map) {
		return tkListDaoImpl.getObjectCountBySelecSqltId(map, "getWithdrawCount");
	}

	public float getWithdrawMoney(Map<String, Object> map) {
		return tkListDaoImpl.getNumber(map, "getWithdrawMoney");
	}

}
