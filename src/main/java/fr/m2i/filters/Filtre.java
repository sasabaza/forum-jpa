package fr.m2i.filters;

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

@WebFilter({"/account"})
public class Filtre implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		if(httpServletRequest.getSession().getAttribute("access") != null && (boolean) httpServletRequest.getSession().getAttribute("access") == true) {
			chain.doFilter(request, response);
		}
		else {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/accueil");
		}
	}

	@Override
	public void destroy() {		
	}
}
