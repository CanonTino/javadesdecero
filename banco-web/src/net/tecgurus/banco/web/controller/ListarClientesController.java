package net.tecgurus.banco.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.tecgurus.banco.model.dto.Banco;
import net.tecgurus.banco.model.service.ClienteService;

/**
 * Servlet implementation class ListarClientesController
 */
@WebServlet("/cliente/listar")
public class ListarClientesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteService clienteServicio;

	@Override
	public void init() throws ServletException {
		clienteServicio = new ClienteService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sesion = req.getSession();
		
		String banco = req.getParameter("banco");
		String nombre = req.getParameter("nombre");
		String apellidoPaterno = req.getParameter("apellidoPaterno");
		String apellidoMaterno = req.getParameter("apellidoMaterno");
		String status = req.getParameter("status");
		
		nombre = nombre != null ? nombre : "";
		apellidoPaterno = apellidoPaterno != null ? apellidoPaterno : "";
		apellidoMaterno = apellidoMaterno != null ? apellidoMaterno : "";
		status = status != null ? status : "true";
		banco = banco != null ? banco : ((List<Banco>)sesion.getAttribute("bancos")).get(0).getClave();
		
		sesion.setAttribute("bancoSeleccionado", banco);
		sesion.setAttribute("clientes", clienteServicio.filtrarClientes(banco, nombre, apellidoPaterno, apellidoMaterno, Boolean.valueOf(status)));
		
		resp.sendRedirect(req.getContextPath() + "/clientes.jsp");
	}

}
