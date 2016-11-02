package com.pay.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.pay.base.Dialect.DialectUtil;
import com.pay.dao.CommonDao;


/**
 * @author bob
 * @param <T>
 */
public abstract  class CommonDaoImpl<T> extends SqlSessionDaoSupport implements CommonDao<T> {
	private static final Logger LOG = LoggerFactory.getLogger(CommonDaoImpl.class);
	private String statementPrefix = null;

	public String getStatementPrefix() {
		if (statementPrefix == null || "".equals(statementPrefix.trim())) {
			statementPrefix = this.getClass().getName().replaceAll("\\.impl\\.", ".").replaceAll("Impl", "");
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("statementPrefix=" + statementPrefix);
		}
		return statementPrefix;
	}

	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		// http://www.oschina.net/code/snippet_1029551_21550
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param id
	 * @return T
	 */
	public T get(String id) {
		if (id == null || "".equals(id)) {
			return null;
		}
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param t
	 * @return int
	 */
	public int add(T t) {
		if (t == null) {
			throw new NullPointerException("t=" + t);
		}
		return this.getSqlSession().insert(getStatementPrefix() + ".add", t);
	}

	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param t
	 * @return int
	 */
	public int addSelective(T t) {
		if (t == null) {
			throw new NullPointerException("t=" + t);
		}
		return this.getSqlSession().insert(getStatementPrefix() + ".addSelective", t);
	}

	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param t
	 * @return int
	 */
	public int update(T t) {
		if (t == null) {
			throw new NullPointerException("t=" + t);
		}
		return this.getSqlSession().update(getStatementPrefix() + ".update", t);
	}

	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param t
	 * @return int
	 */
	public int updateSelective(T t) {
		if (t == null) {
			throw new NullPointerException("t=" + t);
		}
		return this.getSqlSession().insert(getStatementPrefix() + ".updateSelective", t);
	}

	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param t
	 * @return int
	 */
	public int delete(T t) {
		return this.getSqlSession().delete(getStatementPrefix() + ".delete", t);
	}

	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return List<T>
	 */
	
	public List<T> getList(Map<String, Object> map, int pageNo, int pageSize) {
		RowBounds rowBounds = null;
		if (pageNo > 0 && pageSize > 0) {
			int offset = DialectUtil.getPageOffset(pageNo, pageSize);
			rowBounds = new RowBounds(offset, pageSize);
		}
		List<T> resultList = null;
		if (map == null && rowBounds == null) {
			resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
		} else if (map != null && rowBounds == null) {
			resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList", map);
		} else if (map == null && rowBounds != null) {
			resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList", rowBounds);
		} else {
			resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList", map, rowBounds);
		}
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}

	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param map
	 * @return int
	 */
	public int getCount(Map<String, Object> map) {
		Integer result;
		if (map == null) {
			result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getCount");
		} else {
			result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getCount", map);
		}
		return result != null ? result.intValue() : 0;
	}
	
	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param map
	 * @return Float
	 */
	public float getNumber(Map<String, Object> map,String selectSqlId) {
		Float result;
		if (map == null) {
			result = (Float) this.getSqlSession().selectOne(getStatementPrefix() + "."+selectSqlId);
		} else {
			result = (Float) this.getSqlSession().selectOne(getStatementPrefix() + "."+selectSqlId, map);
		}
		return (float) (result != null ? result.floatValue() : 0.0);
	}
	
	/**
	 * 假如 XxxMapper.xml中的sql没有配置，调用该方法将会抛出异常
	 * 
	 * @param map
	 * @return int
	 */
	public T getObjectByCondition(Map<String, Object> map) {
		T result;
		if (map == null) {
			result =  this.getSqlSession().selectOne(getStatementPrefix() + ".getObjectByCondition");
		} else {
			result =  this.getSqlSession().selectOne(getStatementPrefix() + ".getObjectByCondition", map);
		}
		return result ;
	}

	/***
	 * @param map
	 * @param pageNo 第几页
	 * @param pageSize 每页显示多少条
	 * @param selectSqlId
	 * 对应XxxMapper.xml中sql的ID
	 * @return int
	 */
	public List<T> getObjectListBySelectSqlId(Map<String, Object> map, int pageNo, int pageSize, String selectSqlId) {
		RowBounds rowBounds = null;
		if (pageNo > 0 && pageSize > 0) {
			int offset = DialectUtil.getPageOffset(pageNo, pageSize);
			rowBounds = new RowBounds(offset, pageSize);
		}
		if (StringUtils.isEmpty(selectSqlId)) {
			return getList(map, pageNo, pageSize);
		}
		List<T> resultList;
		if (map == null && rowBounds == null) {
			resultList = this.getSqlSession().selectList(getStatementPrefix() + "." + selectSqlId);
		} else if (map != null && rowBounds == null) {
			resultList = this.getSqlSession().selectList(getStatementPrefix() + "." + selectSqlId, map);
		} else if (map == null && rowBounds != null) {
			resultList = this.getSqlSession().selectList(getStatementPrefix() + "." + selectSqlId, rowBounds);
		} else {
			resultList = this.getSqlSession().selectList(getStatementPrefix() + "." + selectSqlId, map, rowBounds);
		}
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}

	public int getObjectCountBySelecSqltId(Map<String, Object> map, String selectSqlId) {
		if (StringUtils.isEmpty(selectSqlId)) {
			return getCount(map);
		}
		Integer result;
		if (map == null) {
			result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + "." + selectSqlId);
		} else {
			result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + "." + selectSqlId, map);
		}
		return result != null ? result.intValue() : 0;
	}
}