package com.pay.base.Dialect;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.base.constant.CommonConstant;



public class DialectUtil {
	private static final Logger LOG = LoggerFactory.getLogger(DialectUtil.class);

	private static final Map<String, Dialect> DIALECT_MAP = new ConcurrentHashMap<String, Dialect>();
	private static Dialect dialect = null;
	/** 默认页码 1 */
	private static int DEFAULT_PAGE = 1;
	/** 默认每页显示 10条 */
	private static int DEFAULT_PAGESIZE = 10;
	/** 默认每页最小为1条 */
	private static int MIN_PAGESIZE = 1;
	/** if判断条件，当大于-1时，条件成立 */
	private static int IF_JUDGE = -1;

	static {
		DIALECT_MAP.put("mysql", new Mysql5Dialect());
		DIALECT_MAP.put("oracle", new OracleDialect());
		if (LOG.isInfoEnabled()) {
			Iterator<Entry<String, Dialect>> iterator = DIALECT_MAP.entrySet().iterator();
			LOG.info("DIALECT_MAP:");
			while (iterator.hasNext()) {
				Entry<String, Dialect> entry = iterator.next();
				LOG.info("key=" + entry.getKey() + " value=" + entry.getValue().getClass().getName());
			}
		}
	}

	private DialectUtil() {
	}

	public static int getPageOffset(int pageNo, int pageSize) {
		if (pageNo < DEFAULT_PAGE) {
			pageNo = DEFAULT_PAGE;
		}
		if (pageSize < MIN_PAGESIZE) {
			pageSize = DEFAULT_PAGESIZE;
		}
		if (pageSize > CommonConstant.PAGE_SIZE_MAX) {
			pageSize = CommonConstant.PAGE_SIZE_MAX;
		}
		int offset = (pageNo - DEFAULT_PAGE) * pageSize;
		return offset;
	}

	public static Dialect getDialect() {
		if (dialect == null) {
			if (CommonConstant.DATABASE_DRIVER_CLASS_NAME.toLowerCase().indexOf("mysql") > IF_JUDGE) {
				dialect = DIALECT_MAP.get("mysql");
			} else if (CommonConstant.DATABASE_DRIVER_CLASS_NAME.toLowerCase().indexOf("oracle") > IF_JUDGE) {
				dialect = DIALECT_MAP.get("oracle");
			} else {
				dialect = new Mysql5Dialect();
			}
		}
		return dialect;
	}
}