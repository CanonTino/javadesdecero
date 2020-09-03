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
 * Servlet implementation class EliminarClienteController
 */
@WebServlet("/cliente/eliminar")
public class EliminarClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteService clienteServicio;

	@Override
	public void init() throws ServletException {
		clienteServicio = new ClienteService();
	}
	
    public EliminarClienteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		clienteServicio.eliminarClientePorId(Integer.parseInt(id));
		
		HttpSession sesion = request.getSession();
		
		String banco = String.valueOf(sesion.getAttribute("bancoSeleccionado"));
		sesion.setAttribute("clientes", clienteServicio.filtrarClientes(banco, "", "", "", true));
		
		response.sendRedirect(request.getContextPath() + "/clientes.jsp");
	}

}
