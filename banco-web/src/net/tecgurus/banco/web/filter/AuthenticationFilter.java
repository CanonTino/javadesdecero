package net.tecgurus.banco.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//1) rescatar la sesion
		HttpSession sesion = req.getSession(false);
		
		//2) validamos la existencia de una sesion y un usuario en sesion
		if(sesion != null && sesion.getAttribute("usuario") != null)
			chain.doFilter(request, response);
		else //si no hay una sesion iniciada o un usuario en esa sesion, redireccionamos al usuario al formulario de login
			resp.sendRedirect(req.getContextPath() + "/index.html");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
