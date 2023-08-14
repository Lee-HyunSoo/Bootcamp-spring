package com.lhs.spring.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class EncodeFilter implements Filter {
	
	ServletContext context;

    public EncodeFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	context = fConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	/* ServletRequest, ServletResponse를 HttpServletRequest, HttpServletResponse로 Down Casting */
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		/* 인코딩 적용 */
		httpRequest.setCharacterEncoding("utf-8");
		httpResponse.setContentType("text/html; charset=utf-8");
		
		chain.doFilter(request, response);
	}


}
