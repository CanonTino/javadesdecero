<%@page import="net.tecgurus.banco.model.dto.Banco"%>
<%@page import="java.util.List"%>
<%@include file="encabezado.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String bancoSeleccionado = String.valueOf(request.getSession().getAttribute("bancoSeleccionado"));
bancoSeleccionado = bancoSeleccionado == null || bancoSeleccionado.isEmpty() ? "" : bancoSeleccionado;

List<Banco> bancos = ((List<Banco>) request.getSession().getAttribute("bancos"));

bancoSeleccionado = bancoSeleccionado.isEmpty() ? "" : bancoSeleccionado;
%>

<%-- <c:set var="tmpVar" value=" ${bancoSeleccionados == null ? \"empty\" : bancoSeleccionado}"/> --%>

<div
	style="width: 30%; margin-left: 5%; margin-top: 2%; background-color: #99b3ff; border-radius: 8px;">

	<form method="post" action="cliente/guardar">
		<table>
			<tr>
				<td>Banco:</td>
				<td>
				<select name="banco" >
				<%
				for (Banco b : bancos) {
				%>
						<option <%if(bancoSeleccionado.equalsIgnoreCase(b.getClave())) out.print(" selected "); %> value="<%=b.getClave()%>">
						<%=b.getNombre()%>
						</option>
				<%
				}
				%>
<%-- 				<c:forEach items="${bancos}" var="b"> --%>
<%-- 					<option <c:if test="${b.clave == bancoSeleccionado}"> selected </c:if> value="${b.clave}">${b.nombre}</option> --%>
<%-- 				</c:forEach> --%>
				</select></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td>
				<input autofocus type="text" name="nombre" value="" placeholder="Nombre del cliente" />
			</td>
			</tr>
			<tr>
				<td>Apellido Paterno:</td>
				<td>
				<input type="text" name="apellidoPaterno" value="" placeholder="Primer apellido del cliente" />
			</td>
			</tr>
			<tr>
				<td>Apellido Materno:</td>
				<td>
				<input type="text" name="apellidoMaterno" value="" placeholder="Segundo apellido del cliente" />
			</td>
			</tr>
			<tr>
				<td>Fecha de Naciento:</td>
				<td>
				<input type="date" name="fechaDeNacimiento" value="1980-01-01"/>
			</td>
			</tr>
			<tr>
				<td>Status:</td>
				<td>
				<select name="status">
						<option  value="true">Activo</option>
						<option value="false">Inactivo</option>
				</select>
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