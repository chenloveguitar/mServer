package com.magicmoble.luzhouapp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class VerifyLoginFilter implements Filter {
	private List<String> allowedList = new ArrayList<String>();
	private String checkFail;
	private String login;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		String uri = request.getRequestURI();
		if (allowedList.contains(uri) || request.getSession().getAttribute("currAdmin") != null) {
			chain.doFilter(req, resp);
		} else {
			if (login != null) {
				req.setAttribute("login", login);
			}

			req.getRequestDispatcher(checkFail).forward(req, resp);
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String allowedUris = config.getInitParameter("allowedUris");
		checkFail = config.getInitParameter("checkFail").trim();
		login = config.getInitParameter("login");
		String allowedArr[] = allowedUris.split("\\s*,\\s*");
		allowedList = Arrays.asList(allowedArr);

	}

}
