package com.pay.base;

import java.util.List;

/**
 * 结果返回
 * 
 * @author jql
 */
public class Resp {

	/**
	 * 返回码
	 */
	int retCode;

	/**
	 * 返回信息
	 */
	String msg = "";
	 
	/**
	 * 返回数据
	 */
	Object result = "";
	/**
	 * 结果记录数
	 */
	int resultCount = 0;
	/**
	 * 结果子记录数（适用于酒店里面的房间）
	 */
	int resultChildCount = 0;

	
	public Resp() {
		this(RetCode.OK, RetDesc.OK_DESC);
	}

	public Resp(Object result) {
		this(RetCode.OK, RetDesc.OK_DESC, result);
	}

	public Resp(List<?> list) {
		this(RetCode.OK, RetDesc.OK_DESC, list);
	}

	public Resp(int retCode, String msg) {
		this(retCode, msg,"");
	}

	public Resp(int retCode, String msg,Object result) {
		this.retCode = retCode;
		this.msg = msg;
		if (result == null) {
			this.result = "";
		} else {
			this.result = result;
		}
	} 
	public Resp(int retCode, String msg,String result) {
		this.retCode = retCode;
		this.msg = msg;
		if (result == null) {
			this.result = "";
		} else {
			this.result = result;
		}
	} 
	public Resp(int retCode, String msg,int resultCount,int resultChildCount, List<?> list) {
		this.retCode = retCode;
		this.msg = msg;
		this.resultCount = resultCount;
		this.resultChildCount = resultChildCount;
		if (list == null || list.isEmpty()) {
			Object[] s = new Object[0];
			this.result = s;
		} else {
			this.result = list;
		}
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public int getResultChildCount() {
		return resultChildCount;
	}

	public void setResultChildCount(int resultChildCount) {
		this.resultChildCount = resultChildCount;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Resp [retCode=" + retCode + ", msg=" + msg + ", result=" + result + ", resultCount=" + resultCount
				+ ", resultChildCount=" + resultChildCount + "]";
	}
 
 
  

	 

}
