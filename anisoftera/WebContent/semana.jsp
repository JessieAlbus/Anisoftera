<%@page import="umariana.anisoftera.mundo.Ciclo"%>
<%@page import="umariana.anisoftera.mundo.Proyecto"%>
<%@page import="umariana.anisoftera.mundo.Equipo"%>
<%@page import="umariana.anisoftera.mundo.Integracion"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Anisoftera 2014</title>
	<link rel="stylesheet" href="./css/combobox.css">
	<!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- Estilo imagen e iframe -->
	<link href="./css/login.css" rel="stylesheet" type="text/css">
	<!-- Estilos para la fecha -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>

<!-- Validar una sola vez el formulario -->
<script type="text/javascript">
function cargar(){
	if(document.getElementById('bandera').value == ""){
		document.forma2.submit();
	}
}
</script>
<!-- Valida los campos vacios de semana-->
<script type="text/javascript">
function validarSemanas(){
	if(document.getElementById('spinner').value == ""){
		spinner.focus();
		alert('No Has Ingresado Una Semana Válida');
	}else if(document.getElementById("datepickerInicio").value == ""){
		datepickerInicio.focus();
		alert('No Has Ingresado Una Fecha Inicial De La Semana');
	}else if(document.getElementById("datepickerFin").value == ""){
		datepickerFin.focus();
		alert('No Has Ingresado Una Fecha Final De La Semana');
	}else{
		document.getElementById('boton').value="Agregar";
		this.forma2.submit();
	}
}
</script>
<!-- Calendario Fecha Inicio -->
<script type="text/javascript">
	$(function(){
		$("#datepickerInicio").datepicker();
	});
</script>
<!-- Calendario Fecha Fin -->
<script type="text/javascript">
	$(function(){
		$("#datepickerFin").datepicker();
	});
</script>
<!-- Semana Número -->
<script type="text/javascript">
	$(function(){
		$("#spinner").spinner();
	});
</script>
</head>
<body onload="cargar()">
	<%
		HttpSession sesion = request.getSession();
		String url = "login.html";

		if (sesion.getAttribute("usuario") == null) {
			request.getRequestDispatcher(url).forward(request, response);
		}
	%>
<div class="container">
<div class="jumbotron">
<form action="AgregarSemana" method="POST" name="forma2">
	<% /** Variable para ejecutar una vez el OnLoad */ %>
		<c:choose>
			<c:when test="${bandera != null}">
				<input type="hidden" name="bandera" id="bandera" value="${bandera}">
			</c:when>
			<c:otherwise>
				<input type="hidden" name="bandera" id="bandera" value="">
			</c:otherwise>
		</c:choose>
		<!-- Validar Ciclos con hidden -->
		<c:choose>
			<c:when test="${bandera2 != null}">
				<input type="hidden" name="boton" id="boton" value="${bandera2}">
			</c:when>
			<c:otherwise>
				<input type="hidden" name="boton" id="boton" value="">
			</c:otherwise>
		</c:choose>
<table>
<tr>
	<td><h4>Equipos:</h4></td>
	<td>
		<select class="form-control" id="equipo" name="equipo" onChange="this.form.submit()" title="Seleccione el Equipo. Gracias">
			<c:forEach var="miEquipo" items="${equipos}">
				<c:choose>
					<c:when test="${miEquipo.nombre.equals(equipo)}">
						<option selected value="${miEquipo.nombre}">${miEquipo.nombre}</option>
					</c:when>
					<c:otherwise>
						<option value="${miEquipo.nombre}">${miEquipo.nombre}</option>
					</c:otherwise>
				</c:choose>				
			</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td><h4>Proyectos</h4></td>
	<td>
		<select class="form-control" id="proyecto" name="proyecto" onload="this.form.submit()" title="Seleccione el Proyecto. Gracias">
			<c:forEach var="miProyecto" items="${proyectos}">
				<c:choose>
					<c:when test="${miProyecto.nombreProyecto.equals(proyecto)}">
						<option selected value="${miProyecto.nombreProyecto}">${miProyecto.nombreProyecto}</option>
					</c:when>
					<c:otherwise>
						<option value="${miProyecto.nombreProyecto}">${miProyecto.nombreProyecto}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</td>
</tr> 
<tr>
	<td><h4>Ciclos</h4></td>
	<td>
		<select class="form-control" id="ciclo" name="ciclo" onChange="this.form.submit()" title="Seleccione el Ciclo. Gracias">
			<c:forEach var="miCiclo" items="${ciclos}">
				<c:choose>
					<c:when test="${miCiclo.numeroCiclo==ciclo}">
						<option selected value="${miCiclo.numeroCiclo}">${miCiclo.numeroCiclo}</option>
					</c:when>
					<c:otherwise>
						<option value="${miCiclo.numeroCiclo}">${miCiclo.numeroCiclo}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</td>
</tr> 
<tr>
	<td><h4>Semana nùmero 1:</h4></td>
		<c:choose>
			<c:when test="${semestre != null}">
				<td>
					<div class="input-group">
						<input id="spinner" type="number" class="form-control" name="spinner" placeholder="entre 1 y 8 " min="1" max="8" value="${semestre}">
					</div>
				</td>
			</c:when>
			<c:otherwise>
				<td><input id="spinner" type="number" class="form-control" name="spinner" placeholder="entre 1 y 8 " min="1" max="8" value="${semestre}" title="Seleccione el número de la Semana. Gracias">
			</c:otherwise>
		</c:choose>
</tr>
<tr>
	<td><h4>Semana nùmero:</h4></td>
		<c:choose>
			<c:when test="${opcion == 2}">
				<c:choose>
					<c:when test="${semana != null}">
						<td><input type="number" class="form-control" name="semana" value="${semana}" readonly="readonly"></td>
					</c:when>
					<c:otherwise>
						<td><input type="number" class="form-control" name="semana" readonly="readonly"></td>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${semana != null}">
						<td>
							<div class="input-group">
								<input type="text" class="form-control" name="semana" value="${semana}" readonly="readonly">
							</div>
						</td>
					</c:when>
					<c:otherwise>
						<td><input id="semana" type="text" class="form-control" name="semana" placeholder="Número"></td>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
</tr>
<tr>
	<td><h4>Fecha Inicio:</h4></td>
		<c:choose>
			<c:when test="${fechaInicio != null}">
				<td>
					<div class="input-group">
						<input id="datepickerInicio" type="text" class="form-control" name="datepickerInicio" placeholder="mm/dd/aaaa" value="${fechaInicio}" title="Seleccione la Fecha de Inicio de la Semana. Gracias">
					</div>
				</td>
			</c:when>
			<c:otherwise>
				<td><input id="datepickerInicio" type="text"  class="form-control" name="datepickerInicio" placeholder="mm/dd/aaaa" title="Seleccione la Fecha de Inicio de la Semana. Gracias"></td>
			</c:otherwise>
		</c:choose>
</tr>
<tr>
	<td><h4>Fecha Fin:</h4></td>
		<c:choose>
			<c:when test="${fechaFin != null}">
				<td>
					<div class="input-group">
						<input id="datepickerFin" type="text" class="form-control" name="datepickerFin" placeholder="mm/dd/aaaa" value="${fechaFin}" title="Seleccione la Fecha de Fin de la Semana. Gracias">
					</div>
				</td>
			</c:when>
			<c:otherwise>
				<td><input id="datepickerFin" type="text" class="form-control" name="datepickerFin" placeholder="mm/dd/aaaa" title="Seleccione la Fecha de Fin de la Semana. Gracias"></td>
			</c:otherwise>
		</c:choose>
		</tr>
</table><br> <br>
<td>
	<c:choose>
		<c:when test="${opcion == 2}">
			<input type="submit" id="btn" class="btn btn-inverse" name="boton" value="Modificar">
		</c:when>
		<c:otherwise>
			<input type="button" id="boton" class="btn btn-inverse" name="boton" value="Agregar" onclick="validarSemanas()">
		</c:otherwise>
	</c:choose>
</td>
<td><button type="reset" id="btn" class="btn btn-inverse" name="salir">Cancelar</button></td>
<td><button type="submit" id="btn" class="btn btn-inverse"name="listarSemana">Mostrar Semanas</button></td>
<c:if test="${mensaje != null}">
	<h2>${mensaje}</h2><br>
</c:if><br> <br>
<c:if test="${semanas != null}">
	<table class="table table-hover">
		<caption>Listado de las Semanas</caption>
		<tr>
			<th>Equipo</th>
			<th>Proyecto</th>
			<th>Ciclo</th>
			<th>Semana</th>
			<th>Fecha Inicio</th>
			<th>Fecha Fin</th>
			<th colspan="2">Administrar Semanas</th>
		</tr>
		<c:forEach var="miSemana" items="${semanas}">
			<tr>
				<td>Los Euipos</td>
				<td>Los proyectos</td>
				<td>Los ciclos</td>
				<td>${miSemana.semanaNumero}</td>
				<td>${miSemana.fechaInicio}</td>
				<td>${miSemana.fechaFin}</td>
				<td><input type="hidden" name="modificar" id="modificar" value="null"> <span title="Modificar Semana"><input type="image" src="./img/btnEditar.jpg" name="modificar" onclick="document.getElementById('modificar').value='${miSemana.semanaNumero}' ;this.form.submit();"></span></td>
				<td><input type="hidden" name="eliminar " id="eliminar" value="null"><span title="Eliminar Semana"><input type="image" src="./img/btnEliminar.jpg" id="eliminar" name="eliminar" onclick="document.getElementById('eliminar').value='${miSemana.semanaNumero}' ;"></span></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
	<c:choose>
		<c:when test="${opcion == null}">
			<input type="hidden" name="opcion" value="1">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="opcion" value="${opcion}">
		</c:otherwise>
	</c:choose>
</form>
</div></div>
</body>
</html>