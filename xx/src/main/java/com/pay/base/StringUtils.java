package com.pay.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 
 * @author bob
 *
 *         字符串工具类
 */
public class StringUtils {

	/** 空串 **/
	public static final String EMPTY = "";

	/**
	 * 
	 * @param str
	 *            房间号
	 * @param str2
	 *            返回数据(L:表示楼层,其它表示房号)
	 * @return 楼层或者房号
	 */
	public static int rhouseNO(String str, String str2) {
		String result = "";
		if (str2 == "L") {
			result = str.substring(0, 2);
		} else {
			result = str.substring(2, 4);
		}
		return Integer.parseInt(result);
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断字符串是否为空包括null,NULL
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if ("null".equalsIgnoreCase(str)) {
			return true;
		}
		return isEmpty(str);
	}

	/**
	 * 判断字符数组，不为空
	 * 
	 * @param values
	 *            字符数组
	 * @return true or false
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
				if (result == false) {
					return result;
				}
			}
		}
		return result;
	}

	/**
	 * 判断字符数组是否全部为空
	 * 
	 * @param values
	 *            字符数组
	 * @return true or false
	 */
	public static boolean areAllEmpty(String... values) {
		if (values == null || values.length == 0)
			return true;

		for (String value : values) {
			if (isNotEmpty(value))
				return false;
		}

		return true;
	}

	/**
	 * 判断字符是否为:null, empty or whitespace
	 * 
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符不为null, empty or whitespace
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 去掉字符串左右空格,如果遇到null，则返回空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String trimToEmpty(String str) {
		return str == null ? "" : str.trim();
	}

	/**
	 * 去掉字符串左右空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 当字符串为null返回"",否则返回字符串本身
	 * 
	 * @author jql
	 * @param str
	 * @return
	 */
	public static String defaultString(String str) {
		return defaultString(str, EMPTY);
	}

	/**
	 * 当字符串不为空时返回本身,否则返回指定字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String defaultString(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}

	/**
	 * join方法将 Stirng数组，通过separater分隔符进行分割
	 * 
	 * @param resource
	 *            源数组
	 * @param separater
	 *            分隔符
	 * @return
	 */
	public static String join(String[] resource, String separater) {
		if (resource == null || resource.length == 0) {
			return null;
		}
		int len = resource.length;
		StringBuilder sb = new StringBuilder();
		if (len > 0) {
			sb.append(resource[0]);
		}
		for (int i = 1; i < len; i++) {
			sb.append(separater);
			sb.append(resource[i]);
		}
		return sb.toString();
	}

	/**
	 * 将字节流转换成16进制字符串
	 * 
	 * @param bytes
	 *            字节流
	 * @return 16进制字符串
	 */
	public static String toHexString(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex);
		}
		return sign.toString();
	}

	public static String toClassPropertyName(String s) {// 把一个字符串 转换为类变量名的规则
		return toFirstLowerCase(toClassName(s));
	}

	public static String toFirstLowerCase(String s) {// 把一个字符串的首字母变小写
		if (isBlank(s)) {
			return s;
		}
		// A 65 a 97 Z 90 z 122
		int charInt = (int) s.charAt(0);
		if (charInt > 64 && charInt < 91) {// 是大写字母
			return String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1);
		}
		return s;
	}

	public static String toClassName(String s) {// 把一个字符串 转换为类名的规则 首字母大写
		// "- _字母" 转换为"大写字母"
		if (isBlank(s)) {
			throw new NullPointerException("");
		}
		s = s.trim().replaceAll(" ", "").replaceAll("　", "");
		// 去掉前后的下 划线,美元符号 中杠字符 因为这些都是不标准的写法
		while (s.startsWith("_") || s.startsWith("-") || s.startsWith("$")) {
			s = s.substring(1);
		}
		while (s.endsWith("_") || s.endsWith("-") || s.startsWith("$")) {
			if (s.length() > 1) {
				s = s.substring(0, s.length() - 1);
			} else {
				return "";
			}
		}
		if ("".equals(s)) {
			return "";
		}
		if (s.length() == 1) {
			return s.toUpperCase();
		}
		int index = s.indexOf('_');
		String tempStr = "";
		if (index < 1) {// 没有
			return toFirstUpperCase(s);
		} else {// 处理所有的下划线 把紧跟着他后的字母转换大写 后面肯定是有内容的
			tempStr = toFirstUpperCase(s.substring(0, index));
			s = tempStr + toFirstUpperCase(toClassName(s.substring(index + 1)));
		}
		index = s.indexOf('-');
		if (index < 1) {// 没有
			return toFirstUpperCase(s);
		} else {// 处理所有的下划线 把紧跟着他后的字母转换大写 后面肯定是有内容的
			tempStr = toFirstUpperCase(s.substring(0, index));
			s = tempStr + toFirstUpperCase(toClassName(s.substring(index + 1)));
		}
		index = s.indexOf('$');
		if (index < 1) {// 没有
			return toFirstUpperCase(s);
		} else {// 处理所有的下划线 把紧跟着他后的字母转换大写 后面肯定是有内容的
			tempStr = toFirstUpperCase(s.substring(0, index));
			return tempStr + toFirstUpperCase(toClassName(s.substring(index + 1)));
		}
	}

	public static String toFirstUpperCase(String s) {// 把一个字符串的首字母变大写
		if (isBlank(s)) {
			return s;
		}
		// A 65 a 97 Z 90 z 122
		int charInt = (int) s.charAt(0);
		if (charInt > 96 && charInt < 123) {// 是小写字母 是小写字母才转化
			return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
		}
		return s;
	}

	public static boolean isNumber(String str) {
		boolean result = false;
		if (str != null && !"".equals(str)) {
			result = str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
		}
		return result;
	}

	public static boolean areNumber(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result = isNumber(value);
				if (result == false) {
					return result;
				}
			}
		}
		return result;
	}

	/**
	 * 求Map<K,V>中Key(键)的最小值
	 * 
	 * @param map
	 * @return
	 */
	public static Object getMinKey(Map<Integer, String> map) {
		if (map == null)
			return null;
		Set<Integer> set = map.keySet();
		Object[] obj = set.toArray();
		Arrays.sort(obj);
		return obj[0];
	}

	/**
	 * 求Map<K,V>中Value(值)的最小值
	 * 
	 * @param map
	 * @return
	 */
	public static Object getMinValue(Map<String, Integer> map) {
		if (map == null)
			return null;
		Collection<Integer> c = map.values();
		Object[] obj = c.toArray();
		Arrays.sort(obj);
		if (obj.length > 0) {
			return obj[0];
		}
		return null;

	}

	/**
	 * 解码
	 */
	public static String decode(String value) {
		if (isEmpty(value))
			return value;

		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		return null;
	}

	/**
	 * 编码
	 */
	public static String encode(String value) {
		if (isEmpty(value))
			return value;

		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		return null;
	}

	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static int Distance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		Double d_s = new Double(d);
		return d_s.intValue();
	}

	public static String generateOnlyBlueLockCode(int length, List<?> list) {
		// 生成一个随机密码
		String blueLockCode = generateBlueLockCode(length);
		// 判断该密码是否存在于list中,如果存在，则再次生成，否则返回出去
		if (list != null && list.contains(blueLockCode)) {
			blueLockCode = generateBlueLockCode(length);
		}
		return blueLockCode;
	}

	// 同个酒店，生成不相同的6位数字密码
	public static String generateBlueLockCode(int length) {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < length; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

	public static void generate2() {
		// List list = new ArrayList<>();

		Set<Integer> m = new HashSet<Integer>();
		for (int i = 0; i < 100; i++) {
			int a;
			do {
				a = (int) (Math.random() * 1000000);
			} while (m.contains(a));
			m.add(a);
			System.out.println(a);
		}
	}


	//生成随机数字和字母,  
    public static String getStringRandom(int length) {  
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
            	 //输出是大写字母还是小写字母 (65：大写，97：小写) 
                //int temp = random.nextInt(2) % 2 == 0 ? 65 : 97; 
                int temp = 65;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
	public static String StringFilter(String str) throws PatternSyntaxException{      
		  // 只允许字母和数字        
		  // String   regEx  =  "[^a-zA-Z0-9]";                      
		  // 清除掉所有特殊字符   
		  String regEx="[`~!@#$%^&*()+=|{}//[//]<>/~@#￥%……&*（）——+|{}【】]";   
		  Pattern   p   =   Pattern.compile(regEx);      
		  Matcher   m   =   p.matcher(str);      
		  return   m.replaceAll("").trim();      
	}
	public static void main(String[] args) {
		/*
		 * int num = rhouseNO("0517","F"); System.out.println(num);
		 */
		/*
		 * for(int i=0;i<100;i++){ String no= generateRandomNo();
		 * System.out.println(no); }
		 */
		// generate2();
		// generateBlueLockCode(6);
		/*
		 * String a ="0.2223"; String b ="222.2"; String c ="2 ";
		 * System.out.println(isNumber(c));
		 */

		// 114.1077229948 |22.5502491154 114.1058418742 |22.5392680015
		// 酒店经纬度
		/*
		 * double lng1 = 114.065572; double lat1 = 22.539133;
		 * 
		 * //市中心经纬度 double lng2= 114.06110599999996; double lat2 =
		 * 22.533874308501183; double abc = Distance(lng1, lat1, lng2, lat2);
		 * System.out.println("xxx: "+abc);
		 */
		// JSONObject jsonObject = JSONObject.fromObject(
		// "{\"pano\":\"0100010000130501111158026Z5\",\"heading\":\"120\",\"pitch\":\"1.5\",\"zoom\":\"2\"}");
		//
		// Map<String, Object> vistaMap = JSONObject.fromObject(jsonObject);
		// System.out.println(vistaMap);

	}
	
	public static boolean isEmail(String emailStr)
	{
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";    
		 Pattern regex = Pattern.compile(check);    
		 Matcher matcher = regex.matcher(emailStr);    
		 return matcher.matches();    
	}
}
