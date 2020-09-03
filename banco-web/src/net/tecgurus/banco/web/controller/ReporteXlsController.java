package net.tecgurus.banco.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import net.tecgurus.banco.model.service.ClienteService;
import net.tecgurus.banco.web.util.ReporteXls;

/**
 * Servlet implementation class ReporteXlsController
 */
@WebServlet("/reporte/xls")
public class ReporteXlsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteService clienteServicio;

	@Override
	public void init() throws ServletException {
		clienteServicio = new ClienteService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; fileName=ListadoDeClientes.xls");

		ReporteXls reporteador = new ReporteXls();
		Workbook workbook = reporteador.exportarAXls(clienteServicio.listarClientes());

		workbook.write(response.getOutputStream());

		workbook.close();

	}

}
