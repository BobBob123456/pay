package com.pay.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;





 
/**
 * 
 * @author Bob
 *
 * @param <T>
 */
public interface ICommonService<T> {

	@Transactional(propagation = Propagation.REQUIRED)
	public int add(T t);

	@Transactional(propagation = Propagation.REQUIRED)
	public int addSelective(T t);

	@Transactional(propagation = Propagation.REQUIRED)
	public int update(T t);

	@Transactional(propagation = Propagation.REQUIRED)
	public int updateSelective(T t);

	@Transactional(propagation = Propagation.REQUIRED)
	public int delete(T t);
}