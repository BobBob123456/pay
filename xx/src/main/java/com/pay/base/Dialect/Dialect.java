package com.pay.base.Dialect;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.Configuration;

public abstract class Dialect {
	public abstract String getBoundSqlForPage(String sql);

	public abstract void addParameterToBoundSql(Configuration configuration, BoundSql boundSql, int offset, int limit);
}