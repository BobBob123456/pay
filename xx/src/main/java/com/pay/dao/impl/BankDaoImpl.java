package com.pay.dao.impl;

import org.springframework.stereotype.Repository;

import com.pay.dao.BankDao;
import com.pay.pojo.Bank;

@Repository("bankDaoImpl")
public class BankDaoImpl extends CommonDaoImpl<Bank> implements BankDao {

}
