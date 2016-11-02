package com.pay.service;

import java.util.List;
import java.util.Map;

import com.pay.pojo.TkList;



public interface ITkListService  {
	float getwclmoney(Map<String, Object> map);
	
	float getyclmoney(Map<String, Object> map);
	
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
}
