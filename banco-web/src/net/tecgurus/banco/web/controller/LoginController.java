package net.tecgurus.banco.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.tecgurus.banco.model.dto.Usuario;
import net.tecgurus.banco.model.service.BancoService;
import net.tecgurus.banco.model.service.UsuarioService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("user");
		String pass = request.getParameter("password");
		
		Usuario usuario = usuarioService.login(user, pass);
		if(usuario == null) 
			response.sendRedirect("index.html");
		else {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", usuario);
			sesion.setAttribute("bancos", new BancoService().getBancosByStatus(true));
			response.sendRedirect(request.getContextPath() + "/home.jsp");
		}
	}

}
