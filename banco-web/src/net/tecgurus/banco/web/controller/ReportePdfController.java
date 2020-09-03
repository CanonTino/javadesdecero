package net.tecgurus.banco.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tecgurus.banco.model.service.ClienteService;
import net.tecgurus.banco.web.util.ReportePdf;

/**
 * Servlet implementation class ReportePdfController
 */
@WebServlet("/reporte/pdf")
public class ReportePdfController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteService clienteServicio;

	@Override
	public void init() throws ServletException {
		clienteServicio = new ClienteService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; fileName=ListadoDeClientes.pdf");
		
		ReportePdf reporteador = new ReportePdf();
		reporteador.exportarAPdf(response.getOutputStream(), clienteServicio.listarClientes());
	}

}
