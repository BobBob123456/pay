package com.pay.dao.impl;


import org.springframework.stereotype.Repository;

import com.pay.dao.NewlistDao;
import com.pay.pojo.Newlist;

@Repository("newListDaoImpl")
public class NewlistDaoImpl extends CommonDaoImpl<Newlist> implements NewlistDao{

}
