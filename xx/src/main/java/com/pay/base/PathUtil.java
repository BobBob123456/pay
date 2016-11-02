package com.pay.base;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author jql
 *
 */
public class PathUtil {
	private static String BASE_REAL_PATH = getRealPathByClass();

	/**
	 * 通过ServletContext上下文获取应用绝对路径
	 * 
	 * @param request
	 * @param s
	 * @return 参数s值来决定,""||null后面有File.separator <br>
	 *         "/abc"后面也没有File.separator<br>
	 *         /abc/ 后面有File.separator
	 */
	public static String getRealPath(HttpServletRequest request, String s) {
		String realPath = getBaseRealPath(request);
		if (StringUtils.isEmpty(s)) {
			return realPath;
		}
		s = toPath(s);
		if (!s.startsWith(File.separator) && !realPath.endsWith(File.separator)) {
			realPath = realPath + File.separator;
		}
		return toPath(realPath + s);
	}

	/**
	 * 功能：把web路径转换为真实路径形式,前后的路径由 s格式来决定
	 * 
	 * @param s
	 *            如：front/person/templet/，不支持../路径
	 * @return front(File.separator)person(File.separator)templet(File.
	 *         separator)
	 */
	public static String toPath(String s) {
		if (StringUtils.isEmpty(s)) {
			return "";
		}
		s = s.trim().replaceAll("\\\\", "/");
		s = s.replaceAll("\\.\\./", "/");
		s = s.replaceAll("//", "/");
		while (s.indexOf("//") > -1) {
			s = s.replaceAll("//", "/");
		}
		int indexTemp = -1;
		String strTemp = "";
		if (File.separatorChar != '/') {// 把 /转化为系统的分割符号
			indexTemp = s.indexOf('/');
			strTemp = "";
			while (indexTemp > -1) {
				strTemp = s.substring(0, indexTemp);
				s = strTemp + File.separatorChar + s.substring(indexTemp + 1);
				indexTemp = s.indexOf('/');
			}
		}
		return s;
	}

	/**
	 * 通过ServletContext上下文获取应用绝对路径
	 * 
	 * @param request
	 * @return 后面有File.separator
	 */
	public static String getBaseRealPath(HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		if (servletContext == null) {
			throw new NullPointerException("servletContext == null");
		}
		String realPath = servletContext.getRealPath("/");
		if (realPath == null) {
			throw new NullPointerException("servletContext.getRealPath(\"\") == null");
		}
		return toPath(realPath);
	}

	public static String toWebPath(String s) {
		if (StringUtils.isEmpty(s)) {
			return "";
		}
		s = s.trim().replaceAll("\\\\", "/");
		s = s.replaceAll("\\.\\./", "/");
		s = s.replaceAll("//", "/");
		while (s.indexOf("//") > -1) {
			s = s.replaceAll("//", "/");
		}
		if (File.separatorChar == '/') {
			return s;
		}

		int indexTemp = -1;
		String strTemp = "";

		if (File.separatorChar != '/') {// 把系统的分割符号转化为 /
			indexTemp = s.indexOf(File.separator);
			strTemp = "";
			while (indexTemp > -1) {
				strTemp = s.substring(0, indexTemp);
				s = strTemp + '/' + s.substring(indexTemp + 1);
				indexTemp = s.indexOf(File.separator);
			}
		}
		return s;
	}

	/**
	 * 该方法仅仅是开发的时候使用
	 * 
	 * @return
	 */
	public static String getEclipseWorkspaceProjectPath() {
		return getEclipseWorkspaceProjectPath(null);
	}

	public static String getEclipseWorkspaceProjectPath(String projectName) {
		String projectPath = toWebPath(getRealPath(""));
		if (StringUtils.isBlank(projectName)) {
			int index = projectPath.indexOf("/target/classes/");
			if (index > -1) {
				projectPath = projectPath.substring(0, index);
			}
		} else {
			int index = projectPath.indexOf(projectName);
			if (index > -1) {
				String s = projectPath.substring(0, index);
				projectPath = s + projectName;
			}
		}
		return toPath(projectPath);
	}

	public static String getRealPath(String s) {
		String baseRealPath = getBaseRealPath();
		if (StringUtils.isBlank(s)) {
			return baseRealPath;
		}
		s = toPath(s);
		if (!s.startsWith(File.separator) && !baseRealPath.endsWith(File.separator)) {
			baseRealPath = baseRealPath + File.separator;
		}
		return toPath(baseRealPath + s);
	}

	public static String getBaseRealPath() {
		return BASE_REAL_PATH;
	}

	public static String getRealPathByClass() {
		String realPath = getClassesRealPath();
		int indexTemp = -1;
		String strTemp = "";
		if (!File.separator.equals("/")) {// 系统的文件路径分隔符 统一转换为 /
			indexTemp = realPath.indexOf(File.separator);
			strTemp = "";
			while (indexTemp > -1) {
				strTemp = realPath.substring(0, indexTemp);
				realPath = strTemp + '/' + realPath.substring(indexTemp + 1);
				indexTemp = realPath.indexOf(File.separator);
			}
		}

		int index = realPath.indexOf("/WEB-INF/");
		if (index > -1) {
			realPath = realPath.substring(0, index);// 获取应用的绝对路径
		}
		while (realPath.indexOf(":") > 0 && realPath.startsWith("/")) {// windows下删除第一个
			// linux下不要删除
			realPath = realPath.substring(1);
		}
		// 替换成 File.separator realPathStr.replaceAll("/",
		// File.separator);File.separator==\ 时，会出现异常
		if (realPath.toLowerCase().endsWith(".jar")) {
			index = realPath.lastIndexOf('/');
			if (index > -1) {
				realPath = realPath.substring(0, index);
			}
		}

		index = realPath.indexOf("/target/classes/");
		if (index > -1) {
			realPath = realPath.substring(0, index + 1);
		} else if (realPath.endsWith("/target/classes")) {
			index = realPath.indexOf("/target/classes");
			if (index > -1) {
				realPath = realPath.substring(0, index + 1);
			}
		}

		if (!File.separator.equals("/")) {// 恢复为系统的文件路径分隔符
			indexTemp = realPath.indexOf('/');
			strTemp = "";
			while (indexTemp > -1) {
				strTemp = realPath.substring(0, indexTemp);
				realPath = strTemp + File.separator + realPath.substring(indexTemp + 1);
				indexTemp = realPath.indexOf('/');
			}
		}

		if (!realPath.endsWith(File.separator)) {// 去掉最后的 File.separator
			realPath = realPath + File.separator;
		}
		return realPath;
	}

	public static String getClassesRealPath() {
		String classesRealPath = "";
		classesRealPath = PathUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();

		if (StringUtils.isBlank(classesRealPath)) {
			throw new NullPointerException("classesRealPath==null");
		}
		classesRealPath = classesRealPath.trim();
		while (classesRealPath.indexOf(":") > 0
				&& (classesRealPath.startsWith("/") || classesRealPath.startsWith("\\"))) {// windows下删除第一个
			// linux下不要删除
			classesRealPath = classesRealPath.substring(1);
		}
		return classesRealPath;
	}
	
	/**
     * 从request对象中获取客户端真实的ip地址
     * @param request request对象
     * @return 客户端的IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
