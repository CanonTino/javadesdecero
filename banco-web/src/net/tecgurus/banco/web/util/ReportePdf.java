package net.tecgurus.banco.web.util;

import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.tecgurus.banco.model.dto.Cliente;

public class ReportePdf {

	public void exportarAPdf(OutputStream out, List<Cliente> clientes) {
		Document documento = new Document();
		try {
			PdfWriter.getInstance(documento, out);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		documento.open();
		try {
			documento.add(new Paragraph("Listado de Clientes"));
			documento.add(new Paragraph(""));
			documento.add(crearTabla(clientes));
			documento.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private PdfPTable crearTabla(List<Cliente> clientes) {
		PdfPTable tabla = null;
//		float[] anchuraDeColumnas = {30,15,15,10,10,10,10};
		tabla = new PdfPTable(7);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(10);
		PdfPCell celda;
		
		//Crear encabezado
		celda = new PdfPCell(new Paragraph("ID"));
		tabla.addCell(celda);
		celda = new PdfPCell(new Paragraph("Nombre"));
		tabla.addCell(celda);
		celda = new PdfPCell(new Paragraph("Apellido Paterno"));
		tabla.addCell(celda);
		celda = new PdfPCell(new Paragraph("Apellido Materno"));
		tabla.addCell(celda);
		celda = new PdfPCell(new Paragraph("ID de Banco"));
		tabla.addCell(celda);
		celda = new PdfPCell(new Paragraph("Nombre de Banco"));
		tabla.addCell(celda);
		celda = new PdfPCell(new Paragraph("Status"));
		tabla.addCell(celda);
		
		
		//agregar contenido
		for (Cliente c : clientes) {
			celda = new PdfPCell(new Paragraph(String.valueOf(c.getNumeroCliente())));
			tabla.addCell(celda);

			celda = new PdfPCell(new Paragraph(c.getNombre()));
			tabla.addCell(celda);

			celda = new PdfPCell(new Paragraph(c.getApellidoPaterno()));
			tabla.addCell(celda);

			celda = new PdfPCell(new Paragraph(c.getApellidoMaterno()));
			tabla.addCell(celda);

			celda = new PdfPCell(new Paragraph(c.getBanco().getClave()));
			tabla.addCell(celda);

			celda = new PdfPCell(new Paragraph(c.getBanco().getNombre()));
			tabla.addCell(celda);

			String status = c.isStatus() ? "Activo" : "Inactivo";
			
			celda = new PdfPCell(new Paragraph(status));
			tabla.addCell(celda);

		}
		return tabla;
	}
}
