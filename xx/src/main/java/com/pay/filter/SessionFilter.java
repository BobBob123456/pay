package com.pay.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pay.base.StringUtils;

/**
 * 用于检查用户是否登录了系统的过滤器
 * @author bob
 */
public class SessionFilter implements Filter {

	/** 要检查的 session 的名称 */
	private String sessionKey;

	/** 需要排除（不拦截）的URL的正则表达式 */
	private Pattern excepUrlPattern;

	/** 检查不通过时，转发的URL */
	private String forwardUrl;

	public void init(FilterConfig cfg) throws ServletException {
		sessionKey = cfg.getInitParameter("sessionKey");

		String excepUrlRegex = cfg.getInitParameter("excepUrlRegex");
		if (!StringUtils.isBlank(excepUrlRegex)) {
			excepUrlPattern = Pattern.compile(excepUrlRegex);
		}

		forwardUrl = cfg.getInitParameter("redirectUrl");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 如果 sessionKey 为空，则直接放行
		if (StringUtils.isBlank(sessionKey)) {
			chain.doFilter(req, res);
			return;
		}
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String servletPath = request.getServletPath();
		// 如果请求的路径与forwardUrl相同，或请求的路径是排除的URL时，则直接放行
		if (servletPath.equals(forwardUrl) || excepUrlPattern.matcher(servletPath).matches()) {
			chain.doFilter(req, res);
			return;
		}
		Object sessionObj = request.getSession().getAttribute(sessionKey);
		// 如果Session为空，则跳转到指定页面
		if (sessionObj == null) {
			 if (request.getHeader("x-requested-with") != null  
                     && request.getHeader("x-requested-with").equals("XMLHttpRequest")) { // ajax请求  
                 response.setHeader("sessionstatus", "timeout");  
             } else {  
         		String contextPath = request.getContextPath();
    			String redirect = servletPath + "?" + StringUtils.defaultString(request.getQueryString());
    			response.sendRedirect(contextPath + forwardUrl + "?redirect=" + URLEncoder.encode(redirect, "UTF-8"));
                return;  
             }  
			
	
		} else {
			chain.doFilter(req, res);
		}
	}

	public void destroy() {
	}
}