package com.pay.dao.impl;

import org.springframework.stereotype.Repository;

import com.pay.dao.BankPayDao;
import com.pay.pojo.BankPay;

@Repository("bankPayDaoImpl")
public class BankPayDaoImpl extends CommonDaoImpl<BankPay> implements BankPayDao {

}
