package net.tecgurus.banco.web.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import net.tecgurus.banco.model.dto.Cliente;

public class ReporteXls {
	
	public Workbook exportarAXls(List<Cliente> clientes) {
		Workbook workbook = new HSSFWorkbook();
		Sheet pagina = workbook.createSheet("Productos");
		int indiceDeFila = 0;
		
		Row fila = pagina.createRow(indiceDeFila++);
		fila.createCell(0).setCellValue("ID");
		fila.createCell(1).setCellValue("Nombre");
		fila.createCell(2).setCellValue("Apellido Paterno");
		fila.createCell(3).setCellValue("Apellido Materno");
		fila.createCell(4).setCellValue("ID de Banco");
		fila.createCell(5).setCellValue("Nombre de Banco");
		fila.createCell(6).setCellValue("Status");
		
		if(clientes != null)
			for (Cliente cliente : clientes) {
				fila = pagina.createRow(indiceDeFila++);
				fila.createCell(0).setCellValue(cliente.getNumeroCliente());
				fila.createCell(1).setCellValue(cliente.getNombre());
				fila.createCell(2).setCellValue(cliente.getApellidoPaterno());
				fila.createCell(3).setCellValue(cliente.getApellidoMaterno());
				fila.createCell(4).setCellValue(cliente.getBanco().getClave());
				fila.createCell(5).setCellValue(cliente.getBanco().getNombre());
				if(cliente.isStatus())
					fila.createCell(6).setCellValue("Activo");
				else
					fila.createCell(6).setCellValue("Inactivo");
			}
		
		pagina.autoSizeColumn(0);
		pagina.autoSizeColumn(1);
		pagina.autoSizeColumn(2);
		pagina.autoSizeColumn(3);
		pagina.autoSizeColumn(4);
		pagina.autoSizeColumn(5);
		pagina.autoSizeColumn(6);
		
		return workbook;
		
	}

}
