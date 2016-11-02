package com.pay.dao.impl;

import org.springframework.stereotype.Repository;

import com.pay.dao.MoneyDao;
import com.pay.pojo.Money;


@Repository("moneyDaoImpl")
public class MoneyDaoImpl extends CommonDaoImpl<Money> implements MoneyDao {



}
