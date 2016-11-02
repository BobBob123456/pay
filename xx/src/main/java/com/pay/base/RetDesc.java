package com.pay.base;

/**
 * 状态返回码描述
 * 
 * @author jql
 *
 */
public class RetDesc {

	/**
	 * 响应正常,说明
	 */
	public final static String OK_DESC = "操作成功";
	/**
	 * 重复操作说明
	 */
	public final static String RESET_DESC = "不可重复操作";
	/**
	 * 操作失败说明
	 */
	public final static String FAILURE_DESC = "操作失败";
	/**
	 * 查询没有发现数据
	 */
	public final static String NO_DATA_DESC = "查询没有发现数据";
	/**
	 * 网络连接超时
	 */
	public static final String CONNECTION_TIME_OUT_DESC = "网络连接超时"; 
	/**
	 * 系统错误
	 */
	public final static String SYSTEM_ERROR_DESC = "系统错误";
	/**
	 * 数据输入错误
	 */
	public final static String INPUT_ERROR_DESC = "参数输入错误";
	
 
	public final static String BANK_ACCOUNT_NO_IS_ALREADY_EXISTS_DESC = "该银行卡账号已经存在";
	 
	public final static String PAY_ORDER_ALREADY_EXISTS_DESC = "订单号已经存在";
 
	public final static String PAY_ORDER_HOUSE_PRICE_ALREADY_EXISTS = "订单房价guid已经存在";
	
	public final static String DATA_ALREADY_EXISTS_DESC = "数据已经存在";

	
	//---------------------------------文件操作---------------------------------------------
	public static final String DELETE_FILE_SUCCESS_DESC = "删除文件成功";
	public static final String DELETE_FILE_FAILURE_DESC = "删除文件失败";
	public static final String GET_FILE_SUCCESS_DESC = "获取文件成功";
	public static final String GET_FILE_FAILURE_DESC = "获取文件失败";
	public static final String NO_VALID_FILE_DESC = "没有检测到有效文件";
	public static final String FILE_UPLOAD_EXCESSIVE_NUMBER_DESC = "上传文件数量过多";
	public static final String FLE_UPLOAD_EXCESSIVE_MAX_SIZE_DESC = "文件超过最大限制";
	public static final String UNVALID_FILE_DESC = "文件不合法";
	public static final String CREATE_FILE_FAILURE_DESC = "创建文件错误";
	public static final String UPLOAD_FILE_FAILURE_DESC = "上传文件失败";
	public static final String UPLOAD_FILE_SUCCESS_DESC = "上传文件成功";
	
	
	//-----------------------------登录------------------------------------------------------------
	public static final String USER_NOT_EXIST_DESC = "用户帐号不存在";
	public static final String USER_PASSWORD_ERROR_DESC = "用户密码不正确";
	public static final String USER_NOT_AUTHORITY_DESC = "用户权限不足";
	
}
