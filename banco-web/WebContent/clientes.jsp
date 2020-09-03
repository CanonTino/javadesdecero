<%@page import="net.tecgurus.banco.model.dto.Banco"%>
<%@page import="net.tecgurus.banco.model.dto.Cliente"%>
<%@page import="java.util.List"%>
<%@include file="encabezado.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Clientes</h1>

<div style="width: 60%;">
	<form action="cliente/listar" method="get">
		<table style="width: 100%">
			<tr style="text-align: left;">
				<th style="width: 30%;">Banco</th>
				<th>Nombre</th>
				<th>Apellido Paterno</th>
				<th>Apellido Materno</th>
				<th>Status</th>
			</tr>

			<tr style="text-align: left;">
				<td><select name="banco" style="width: 90%;">
						<%
						List<Cliente> clientes = ((List<Cliente>) request.getSession().getAttribute("clientes"));
						
						String bancoSeleccionado = String.valueOf(request.getSession().getAttribute("bancoSeleccionado"));
						bancoSeleccionado = bancoSeleccionado == null || bancoSeleccionado.isEmpty() ? "" : bancoSeleccionado;
						
						List<Banco> bancos = ((List<Banco>) request.getSession().getAttribute("bancos"));
						for (Banco b : bancos) {
						%>
						<option value="<%=b.getClave()%>"
						<%if(bancoSeleccionado.equalsIgnoreCase(b.getClave())) out.print(" selected "); %>
						>
						<%=b.getNombre()%></option>

						<%
							}
						%>
				</select></td>
				<td><input type="text" name="nombre" /></td>
				<td><input type="text" name="apellidoPaterno" /></td>
				<td><input type="text" name="apellidoMaterno" /></td>
				<td><select name="status">
						<option value="true">Activo</option>
						<option value="false">Inactivo</option>
				</select></td>
				<td>
				<input type="submit" value="Filtrar"/>
				<input type="button" value="Agregar Cliente" onclick="window.location.href='crearCliente.jsp'" />
				</td>
				
			</tr>
		</table>
	</form>

	<table border="1"
		style="border-collapse: collapse; border-color: blue; width: 100%;">
		<tr style="text-align: center;">
			<th>Banco</th>
			<th>ID</th>
			<th>Nombre</th>
			<th>Status</th>
			<th>Opciones</th>
			
		</tr>

		<%
		if(clientes != null)
			for (Cliente c : clientes) {
		%>
		<tr>
			<td><%=c.getBanco().getNombre()%></td>
			<td><%=c.getNumeroCliente()%></td>
			<td><%=c.getNombre()%> <%=c.getApellidoPaterno()%> <%=c.getApellidoMaterno()%></td>
			<%if(c.isStatus()) {%>
				<td>Activo</td>
			<%}else{%>
				<td>Inactivo</td>
			<%}%>
			
			<td style="width: 20%;">
			<a href="cliente/recuperar?id=<%=c.getNumeroCliente()%>" style="padding: 5px 10px; margin-left: 5px;"><span style="background-color: #5c5c8a; border-radius: 2px; color: white; ">&#9999; Modificar</span></a>
			<a  onclick="return confirm('¿Desea eliminar el registro?')" href="cliente/eliminar?id=<%=c.getNumeroCliente()%>" style="padding: 5px 10px; margin-left: 5px;"><span style="background-color: #5c5c8a; border-radius: 2px; color: white; ">&#10060; Eliminar</span></a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</div>
</body>
</html>