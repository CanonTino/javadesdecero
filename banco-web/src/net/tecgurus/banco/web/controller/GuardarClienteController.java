package net.tecgurus.banco.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.tecgurus.banco.model.service.ClienteService;

/**
 * Servlet implementation class GuardarClienteController
 */
@WebServlet("/cliente/guardar")
public class GuardarClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteService clienteServicio;

	@Override
	public void init() throws ServletException {
		clienteServicio = new ClienteService();
	}
	
    public GuardarClienteController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String banco = request.getParameter("banco");
		String nombre = request.getParameter("nombre");
		String apellidoPaterno = request.getParameter("apellidoPaterno");
		String apellidoMaterno = request.getParameter("apellidoMaterno");
		String status = request.getParameter("status");
		
		String fechaNacimiento= request.getParameter("fechaDeNacimiento");
		System.out.println(fechaNacimiento);
		
		Date fechaDeNacimiento = null;
		try {
			fechaDeNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		boolean editMode = (id != null);
		
		HttpSession sesion = request.getSession();
		
		if(editMode)
			clienteServicio.actualizarCliente(Integer.parseInt(id), nombre, apellidoPaterno, apellidoMaterno, fechaDeNacimiento, Boolean.valueOf(status), banco);
		else
			clienteServicio.crearCliente(nombre, apellidoPaterno, apellidoMaterno, fechaDeNacimiento, Boolean.valueOf(status), banco);
		
		sesion.removeAttribute("bancoSeleccionado");
		sesion.removeAttribute("clienteSeleccionado");
		response.sendRedirect(request.getContextPath() + "/cliente/listar?banco=" + banco);
	}

}
