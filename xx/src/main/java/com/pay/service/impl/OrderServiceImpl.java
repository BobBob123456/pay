package com.pay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.base.constant.CommonConstant;
import com.pay.dao.impl.OrderDaoImpl;
import com.pay.dao.impl.UserDaoImpl;
import com.pay.pojo.Order;
import com.pay.service.IOrderService;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	@Resource
	private OrderDaoImpl orderDaoImpl;
	
	@Resource
	private UserDaoImpl userDaoImpl;
	
	/** 获取最近10条交易订单**/
	public List<Order> getRecentOrder(Map<String, Object> map) {
		return this.orderDaoImpl.getObjectListBySelectSqlId(map, 1, CommonConstant.PAGE_SIZE_DEFAULT, "getRecentOrder");
	}
	
	/**获取订单的总条数**/
	public int getcount(Map<String, Object> map){
		return this.orderDaoImpl.getCount(map);
	}

	public List<Order> getOrders(Map<String, Object> map,int currentPage) {
		return this.orderDaoImpl.getList(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT);
	}
	/**
	 * 获取每日订单数
	 */
	public int getDayOrder(Map<String, Object> map) {
		return (int) this.orderDaoImpl.getNumber(map,"getDayOrder");
	}
	
	/**
	 * 获取每日订单数
	 */
	public float getDayMoney(Map<String, Object> map) {
		return this.orderDaoImpl.getNumber(map,"getDayMoney");
	}

	public int add(Order t) {
		return 0;
	}

	public int addSelective(Order t) {
		return 0;
	}

	public int update(Order t) {
		return 0;
	}

	public int updateSelective(Order t) {
		return 0;
	}

	public int delete(Order t) {
		return 0;
	}

	public int getSuccessCount(Map<String, Object> map) {
		return (int) this.orderDaoImpl.getNumber(map,"getSuccessCount");
	}

	public float getSuccessMoney(Map<String, Object> map) {
		return this.orderDaoImpl.getNumber(map,"getSuccessMoney");
	}

	public int getPtCount(Map<String, Object> map) {
		
		return this.orderDaoImpl.getObjectCountBySelecSqltId(map, "getPtCount");
	}

	public List<Order> getPtOrders(Map<String, Object> map, int currentPage) {
		return this.orderDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT, "getPtList");
	}

	public List<Order> getXjOrders(Map<String, Object> map, int currentPage) {
		String userids=this.userDaoImpl.getXjUserId(map);
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("userId", userids);
		return this.orderDaoImpl.getObjectListBySelectSqlId(m, currentPage, CommonConstant.PAGE_SIZE_DEFAULT, "getXjOrders");
	}

	public int getXjOrderCount(Map<String, Object> map) {
		String userids=this.userDaoImpl.getXjUserId(map);
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("userId", userids);
		return this.orderDaoImpl.getObjectCountBySelecSqltId(m, "getXjOrderCount");
	}

	
	public int getPtSettlementCount(Map<String, Object> map) {
		return this.orderDaoImpl.getObjectCountBySelecSqltId(map, "getPtSettlementCount");
	}
	
	public List<Order> getPtSettlementList(Map<String, Object> map, int currentPage) {
		return this.orderDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT, "getPtSettlementList");
	}

	public List<Order> getAllOrder(Map<String, Object> map, int currentPage) {
		return orderDaoImpl.getObjectListBySelectSqlId(map, currentPage, CommonConstant.PAGE_SIZE_DEFAULT, "getAllOrder");
	}

	public int getAllOrderCount(Map<String, Object> map) {
		return orderDaoImpl.getObjectCountBySelecSqltId(map, "getAllOrderCount");
	}

	public int getAllOrderSuccessCount(Map<String, Object> map) {
		return orderDaoImpl.getObjectCountBySelecSqltId(map, "getAllOrderSuccessCount");
	}

	public float getAllOrderSuccessMoney(Map<String, Object> map) {
		return orderDaoImpl.getNumber(map, "getAllOrderSuccessMoney");
	}

	
	
}
