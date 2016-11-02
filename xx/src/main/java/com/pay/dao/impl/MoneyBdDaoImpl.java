package com.pay.dao.impl;

import org.springframework.stereotype.Repository;

import com.pay.dao.MoneyBdDao;
import com.pay.pojo.MoneyBd;

@Repository("moneyBdDaoImpl")
public class MoneyBdDaoImpl extends CommonDaoImpl<MoneyBd> implements MoneyBdDao{

}
