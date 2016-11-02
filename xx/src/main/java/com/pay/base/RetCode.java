package com.pay.base;

/**
 *	返回码
 * @author Admin
 * @date 2015年4月8日
 */
/**
 *
 * @author jql
 */
public class RetCode {
	/**
	 * 响应正常,状态码
	 */
	public final static int OK = 1;
	/**
	 * 操作失败,状态码
	 */
	public final static int FAILURE = -1;
	
	/**
	 * 查询没有发现数据
	 */
	public final static int NO_DATA = 0;
	

	/** 408:网络连接超时 **/
	public final static int CONNECTION_TIME_OUT = 408;

	// 1000~2000 通用的：服务器自身、服务器之间通讯出现问题
	/**
	 * 系统错误
	 */
	public final static int SYSTEM_ERROR = 1000; 
	/**
	 * 输入错误
	 */
	public final static int INPUT_ERROR = 1001;
	
	/**
	 * 该银行卡账号已经存在
	 */
	public final static int BANK_ACCOUNT_NO_IS_ALREADY_EXISTS = 1002;
	/**
	 * 订单号已经存在
	 */
	public final static int PAY_ORDER_ALREADY_EXISTS = 1003;
	
	/**
	 * 订单房价guid已经存在
	 */
	public final static int PAY_ORDER_HOUSE_PRICE_ALREADY_EXISTS = 1004;
		
	/**
	 * 数据已经存在
	 */
	public final static int DATA_ALREADY_EXISTS = 1005;
	
	
	//---------------------------------文件操作---------------------------------------------
 	/**
	 * 删除文件成功
	 */
	public static final int DELETE_FILE_SUCCESS = 2005;
	/**
	 * 删除文件失败
	 */
	public static final int DELETE_FILE_FAILURE = 2006;
	/**
	 * 获取文件成功
	 */
	public static final int GET_FILE_SUCCESS = 2007;
	/**
	 * 获取文件失败
	 */
	public static final int GET_FILE_FAILURE = 2008;
	/**
	 * 没有检测到有效的文件
	 */
	public static final int NO_VALID_FILE = 2009;
	/**
	 * 上传文件数量太多
	 */
	public static final int FILE_UPLOAD_EXCESSIVE_NUMBER = 2010;
	/**
	 * 文件超过限制最大数
	 */
	public static final int FLE_UPLOAD_EXCESSIVE_MAX_SIZE = 2011;
	/**
	 * 文件不合法
	 */
	public static final int UNVALID_FILE = 2012;
	/**
	 * 创建文件错误
	 */
	public static final int CREATE_FILE_FAILURE = 2013;
	/**
	 * 上传文件失败
	 */
	public static final int UPLOAD_FILE_FAILURE = 2014;
	/**
	 * 上传文件成功
	 */
	public static final int UPLOAD_FILE_SUCCESS = 2015;
	
	/**
	 * 获取文件 没有查找到文件
	 */
	public static final int GET_FILE_NOFILE = 2016;
	
	/**
	 * 用户帐号不存在
	 */
	public static final int USER_NOT_EXIST = 3001;
	
	/**
	 * 用户密码不正确
	 */
	public static final int USER_PASSWORD_ERROR = 3002;
	
	/**
	 * 用户权限不足
	 */
	public static final int USER_NOT_AUTHORITY = 3003;
	
}
