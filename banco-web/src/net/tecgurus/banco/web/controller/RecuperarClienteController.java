package net.tecgurus.banco.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.tecgurus.banco.model.service.ClienteService;

/**
 * Servlet implementation class RecuperarClienteController
 */
@WebServlet("/cliente/recuperar")
public class RecuperarClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteService clienteServicio;

	@Override
	public void init() throws ServletException {
		clienteServicio = new ClienteService();
	}
	
    public RecuperarClienteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		HttpSession sesion = request.getSession();
		sesion.setAttribute("clienteSeleccionado", clienteServicio.buscarPorId(Integer.parseInt(id)));
		
		response.sendRedirect(request.getContextPath() + "/modificarCliente.jsp");
		
	}

}
