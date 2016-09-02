package com.vrcc.api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter("/*")
public class LogFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

	private static final String MDC_TICKET = "ticket";

	private static final String MDC_URI = "uri";

	private static final Tickets tickets = new Tickets();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final WrappedHttpServletRequest httpRequest = wrap(request);
		final WrappedHttpServletResponse httpResponse = wrap(response);
		try {
			mdc(httpRequest);
			logRequest(httpRequest);
			chain.doFilter(httpRequest, httpResponse);
			logResponse(httpResponse);
		} finally {
			cleanMdc();
		}

	}

	private void logResponse(WrappedHttpServletResponse httpResponse) {
		log.info("RESPONSE status={}, body={}", httpResponse.getStatus(), httpResponse.getBody());
	}

	private void logRequest(WrappedHttpServletRequest httpRequest) {
		log.info("REQUEST method={}, uri={}, params={}, body={}", httpRequest.getMethod(),
				httpRequest.getRequestURI(), httpRequest.getQueryString(), httpRequest.getBody());
	}

	private WrappedHttpServletResponse wrap(ServletResponse response) throws IOException {
		return new WrappedHttpServletResponse((HttpServletResponse) response);
	}

	private WrappedHttpServletRequest wrap(ServletRequest request) throws IOException {
		return new WrappedHttpServletRequest((HttpServletRequest) request);
	}

	private void cleanMdc() {
		MDC.remove(MDC_URI);
		MDC.remove(MDC_TICKET);
		tickets.clean();
	}

	private void mdc(final HttpServletRequest httpRequest) {
		MDC.put(MDC_URI, httpRequest.getRequestURI());
		MDC.put(MDC_TICKET, tickets.get());
	}

	@Override
	public void destroy() {
	}

}
