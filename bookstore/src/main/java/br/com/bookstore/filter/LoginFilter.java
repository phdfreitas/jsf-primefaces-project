package br.com.bookstore.filter;

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
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "loginFilter", urlPatterns = {"/protected/*"})
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = (HttpSession) req.getSession();
				
		if(session.getAttribute("user") == null) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
		}
		else {
			chain.doFilter(request, response);
		}
	}
	
	 @Override
	 public void init(FilterConfig filterConfig) throws ServletException {}

	 @Override
	 public void destroy() {}

}
