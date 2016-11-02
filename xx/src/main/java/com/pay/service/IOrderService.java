package com.pay.service;

import java.util.List;
import java.util.Map;

import com.pay.pojo.Order;

public interface IOrderService extends ICommonService<Order> {
	
	List<Order> getRecentOrder(Map<String, Object> map); 
	
	int getDayOrder(Map<String, Object> map);
	
	float getDayMoney(Map<String, Object> map);
	/**获得成功笔数**/
	int  getSuccessCount(Map<String, Object> map);
	
	float getSuccessMoney(Map<String, Object> map);
	/**获取网银转账条数**/
	int getcount(Map<String, Object> map);
	/**获取网银转账列表**/
	List<Order> getOrders(Map<String, Object> map,int currentPage);
	/**获取平台账号收款条数**/
	int getPtCount(Map<String, Object> map);
	/**获取平台账号收款列表**/
	List<Order> getPtOrders(Map<String, Object> map,int currentPage);
	
	/**获取平台账号结算条数**/
	int getPtSettlementCount(Map<String, Object> map);
	/**获取平台账号结算列表**/
	List<Order> getPtSettlementList(Map<String, Object> map,int currentPage);
	
	List<Order> getXjOrders(Map<String, Object> map,int currentPage);
	
	int   getXjOrderCount(Map<String, Object> map);
	
	
}
