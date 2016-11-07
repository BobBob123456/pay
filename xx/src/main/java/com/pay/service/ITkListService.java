package com.pay.service;

import java.util.List;
import java.util.Map;

import com.pay.pojo.TkList;



public interface ITkListService  {

	
	/**获取结算条数**/
	int getSettlementCount(Map<String, Object> map);
	
	/**获取结算列表**/
	List<TkList> getSettlementList(Map<String, Object> map,int currentPage);
	
	/**成功金额**/
	float getSuccessMoney(Map<String, Object> map);
	
	/**成功笔数**/
	int getSuccessCount(Map<String, Object> map);
	
	/**新增提款**/
	int save_tk(TkList tk);
	
	/**提款管理**/
	int getAllWithdrawCount(Map<String, Object> map);
	
	/**提款管理**/
	List<TkList> getAllWithdrawList(Map<String, Object> map,int currentPage);
	
	/**获取成功或失败条数**/
	int getWithdrawCount(Map<String, Object> map);
	
	/**获取成功或者失败金额**/
	float getWithdrawMoney(Map<String, Object> map);
}
