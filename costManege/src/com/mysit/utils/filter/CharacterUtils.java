package com.mysit.utils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 字符编码集过滤器
 * @author hshuai
 *
 */
@WebFilter("/*")
public class CharacterUtils implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest rep, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		//设置请求返回编码集 讲所有的字符编码改为中文编码，解决中文乱码的问题
		rep.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		filterChain.doFilter(rep, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
