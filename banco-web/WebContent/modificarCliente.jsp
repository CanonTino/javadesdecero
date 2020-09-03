
<%@page import="net.tecgurus.banco.model.dto.Cliente"%>
<%@include file="encabezado.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	Cliente cliente = (Cliente) request.getSession().getAttribute("clienteSeleccionado");
%>

<div
	style="width: 30%; margin-left: 5%; margin-top: 2%; background-color: #99b3ff; border-radius: 8px;">

	<form method="post" action="cliente/guardar">
		<table>
			<tr>
				<td>Banco:</td>
				<td>
				<select name="banco" >
						<option selected value="${clienteSeleccionado.banco.clave}">${clienteSeleccionado.banco.nombre}</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>ID:</td>
				<td>
				<input type="text" name="id" readonly="readonly" value="${clienteSeleccionado.numeroCliente}" />
			</td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td>
				<input autofocus type="text" name="nombre" value="${clienteSeleccionado.nombre}" placeholder="Nombre del cliente" />
			</td>
			</tr>
			<tr>
				<td>Apellido Paterno:</td>
				<td>
				<input type="text" name="apellidoPaterno" value="${clienteSeleccionado.apellidoPaterno}" placeholder="Primer apellido del cliente" />
			</td>
			</tr>
			<tr>
				<td>Apellido Materno:</td>
				<td>
				<input type="text" name="apellidoMaterno" value="${clienteSeleccionado.apellidoMaterno}" placeholder="Segundo apellido del cliente" />
			</td>
			</tr>
			<tr>
				<td>Fecha de Naciento:</td>
				<td>
				<input type="date" name="fechaDeNacimiento" value="${clienteSeleccionado.fechaNacimiento}"/>
			</td>
			</tr>
			<tr>
				<td>Status:</td>
				<td>
				<select name="status">
						<option  <% if(cliente.isStatus()) out.print("selected"); %>  value="true">Activo</option>
						<option <%if(!cliente.isStatus()) out.print("selected"); %> value="false">Inactivo</option>
				</select>
				
<!-- 				<select name="status"> -->
<%-- 						<option  <c:if test="${clienteSeleccionado.status}"> selected </c:if> value="true">Activo</option> --%>
<%-- 						<option <c:if test="${not clienteSeleccionado.status}"> selected </c:if> value="false">Inactivo</option> --%>
<!-- 				</select> -->
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
				<input type="button" value="Cancelar" onclick="window.location.href='cliente/listar'" />
				<input type="submit" value="Guardar" /> 
				</td>
			</tr>
		</table>

		   


	</form>

</div>
</body>
</html>