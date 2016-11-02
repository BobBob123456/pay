package com.pay.base.constant;

/**
 * 系统公共调用的数据常量定义在这里
 */
public class CommonConstant {
	/** 系统内部通讯参数，默认字符编码定义 */
	public final static String CharacterEncoding_Default = "utf-8";
	/** 系统内部通讯参数，时间日期格式定义 */
	public final static String DatetimePattern_Date = "yyyy-MM-dd";
	/** 系统内部通讯参数，时间日期格式定义 */
	public final static String DatetimePattern_Full = "yyyy-MM-dd HH:mm:ss.SSS";
	/** 系统内部通讯参数，时间日期格式定义 */
	public final static String DatetimePattern_Lite = "yyyy-MM-dd HH:mm:ss";
	/** 日期格式 yyyyMMddHHmmssSSS */
	public final static String DatetimePattern_Long = "yyyyMMddHHmmssSSS";
	public final static String DatetimePattern_long14 = "yyyyMMddHHmmss";
	/** 系统内部通讯参数，默认的时间日期所在时区，中国时区 */
	public final static String DatetimeTimeZone_Default = "GMT+8";

	/** 数据库 相关的常量定义 开始 */
	public static final String DATABASE_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String CHARSET = "UTF-8";
	public static final int PAGE_NO_DEFAULT = 1;
	public static final int PAGE_SIZE_DEFAULT = 10;
	public static final int PAGE_SIZE_MAX = 10000;
	
	/** 用户session数据 常量定义*/
	public final static String USER_INFO = "user_info";
}
