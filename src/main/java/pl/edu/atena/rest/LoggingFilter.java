package pl.edu.atena.rest;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import pl.edu.atena.dao.AudytDao;

//@WebFilter(filterName = "LoggingFilter", urlPatterns = { "/*" })
public class LoggingFilter implements Filter {

	@Inject
	private AudytDao audyt;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Instant start = Instant.now();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		try {
			chain.doFilter(request, response);
		} finally {
			audyt.loguj(Date.from(start), Duration.between(start, Instant.now()).toMillis(),
					"request" + httpServletRequest.getPathInfo().toString());
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
