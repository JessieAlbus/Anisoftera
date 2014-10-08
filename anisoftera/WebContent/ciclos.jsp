<%@page import="umariana.anisoftera.mundo.Equipo"%>
<%@page import="umariana.anisoftera.mundo.Proyecto"%>
<%@page import="umariana.anisoftera.mundo.Integracion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="umariana.anisoftera.controlador.AgregarProyecto"%>
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
	<!-- Estilo para los Combobox -->
	<link rel="stylesheet" href="./css/combobox.css">
<script type="text/javascript">
function cargar(){
	if(document.getElementById('bandera1').value == ""){
		document.forma1.submit();
	}
}
</script>
<script type="text/javascript">
function validarCiclos(){
	if(document.getElementById('ciclo').value == ""){
		ciclo.focus();
		alert('No Has Introducido El Número Del Ciclo');
	}else{
		document.getElementById('guardarCiclo').value="Guardar";
		this.forma1.submit();
	}
}
</script>
</head>
<body onload="cargar()">
<%	HttpSession sesion = request.getSession();
	String url= "login.html" ;
 	if(sesion.getAttribute("usuario")==null){
		request.getRequestDispatcher(url).forward(request, response);
	}
%>
<div class="container">
<div class="jumbotron">
<form action="AgregarCiclo" method="POST" name="forma1">
	<% /** Variable para ejecutar una vez el OnLoad */ %>
	<c:choose>
		<c:when test="${bandera1 != null}">
			<input type="hidden" name="bandera1" id="bandera1" value="${bandera1}">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="bandera1" id="bandera1" value="">
		</c:otherwise>
	</c:choose>
	<!-- Variable para validar el formulario al validar los campos -->
	<c:choose>
		<c:when test="${bandera2 != null}">
			<input type="hidden" name="guardarCiclo" id="guardarCiclo" value="${bandera2}">
		</c:when>
		<c:otherwise>
				<input type="hidden" name="guardarCiclo" id="guardarCiclo" value="">
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
	<td><h4>Proyectos:</h4></td>
	<td>
		<select class="form-control" id="proyecto" name="proyecto" onChange="this.form.submit()" title="Seleccione el Proyecto. Gracias">
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
	<td><h4>Ciclo Nùmero:</h4></td>
		<c:choose>
			<c:when test="${ciclo != null}">
				<td>
					<div class="input-group">
						<select class="form-control" id="ciclo" name="ciclo" title="Seleccione el Ciclo a Agregar. Gracias">
							<option name="uno" value="1"> 1 </option> 
							<option name="dos" value="2"> 2 </option>
							<option name="tres" value="3"> 3 </option>
							<option name="cuatro" value="4"> 4 </option>
							<option name="cinco" value="5"> 5 </option>
							<option name="seis" value="6"> 6 </option> 
							<option name="siete" value="7"> 7 </option>
							<option name="ocho" value="8"> 8 </option>
							<option name="nueve" value="9"> 9 </option>
							<option name="dies" value="10"> 10 </option>
						</select>
					</div>
				</td>
			</c:when>
			<c:otherwise>
				<td>
					<select class="form-control" id="ciclo" name="ciclo" title="Seleccione el Ciclo a Agregar. Gracias">
						<option name="uno" value="1"> 1 </option> 
						<option name="dos" value="2"> 2 </option>
						<option name="tres" value="3"> 3 </option>
						<option name="cuatro" value="4"> 4 </option>
						<option name="cinco" value="5"> 5 </option>
						<option name="seis" value="6"> 6 </option> 
						<option name="siete" value="7"> 7 </option>
						<option name="ocho" value="8"> 8 </option>
						<option name="nueve" value="9"> 9 </option>
						<option name="dies" value="10"> 10 </option>
					</select>
				</td>
			</c:otherwise>
		</c:choose>
</tr>
</table>
<td><button type="button" id="guardarCiclo" class="btn btn-inverse" name="guardarCiclo" value="Guardar" onclick="validarCiclos()">Guardar</button></td>
<td><button type="reset" id="btn" class="btn btn-inverse" name="salir">Cancelar</button></td>
<td><button type="submit" id="btn" class="btn btn-inverse" name="listarCiclos">Listar</button></td>
<c:if test="${mensaje != null}">
	<h2>${mensaje}</h2><br>
</c:if><br> <br>
<c:if test="${ciclos != null}">
	<table class="table table-hover">
		<caption>Listado de los Ciclos</caption>
		<tr>
			<th>Equipo</th>
			<th>Proyecto</th>						
			<th>Ciclo</th>
			<th colspan="2">Administrar Ciclos</th>
		</tr>
			<c:forEach var="miCiclo" items="${ciclos}">
			<tr>
				<td>Los Equipos</td>
				<td>Los proyectos</td>
				<td>${miCiclo.numeroCiclo}</td>
				<td><input type="hidden" name="eliminar" id="eliminar"value="null"> <span title="Eliminar Ciclo"><input type="image" src="./img/btnEliminar.jpg" id="eliminar" name="eliminar" onclick="document.getElementById('eliminar').value='${miCiclo.numeroCiclo}' ;"></span></td>
			</tr>
			</c:forEach>
	</table>
</c:if>
</form>
</div></div>
</body>
</html>