<%@page import="umariana.anisoftera.mundo.Integracion"%>
<%@page import="umariana.anisoftera.mundo.Integrante"%>
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

	<!-- Validar una sola vez el formulario -->
<script type="text/javascript">
function cargar(){
	if(document.getElementById('bandera').value == ""){ //Carga el formulario para OnLoad
		document.forma.submit();
	}
}
</script>
<!-- Valida los campos vacios del equipo -->
<script type="text/javascript">
function validarEquipos(){
	if(document.getElementById('nombre').value==""){ //Comprueba que no esté vacío
		nombre.focus();
		alert('No Has Ingresado El Nombre Del Equipo');
	}else if(document.getElementById('email').value.indexOf('@') == -1){ //Comprueba que no esté vacío
		email.focus();
		alert('No Has Introducido El E-mail Del Equipo Valido');
	}else{
	document.getElementById('boton').value="Agregar";
	this.forma.submit();
	}
}
</script>
<!-- Funcion de confirmación al eliminar un registro --> 
<script type="text/javascript">
function confirmar(){
	if(confirm("¿Realmente desea eliminar este equipo?")){
		alert("El Equipo ha sido eliminado.")}
		else{
			return false;
		}	
};
</script>
</head>
<body onload="cargar()">
<% HttpSession sesion = request.getSession();
   String url= "login.html" ;
   if(sesion.getAttribute("usuario")==null){
	request.getRequestDispatcher(url).forward(request, response);
	}
%>
<div class="container">
<div class="jumbotron">
<form action="AgregarEquipo" method="POST" name="forma">
	<!-- Validar al ejecutar una vez el OnLoad -->
	<c:choose>
		<c:when test="${bandera != null}">
			<input type="hidden" name="bandera" id="bandera" value="${bandera}">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="bandera" id="bandera" value="">
		</c:otherwise>
	</c:choose>
	<!-- Variable que valida el formulario para las validaciones -->
	<c:choose>
		<c:when test="${bandera1 != null}">
			<input type="hidden" name="boton" id="boton" value="${bandera1}">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="boton" id="boton" value="">
		</c:otherwise>
	</c:choose>			
<table>
	<tr>
		<td><h4>Nombre:</h4></td>
		<c:choose>
			<c:when test="${opcion == 2}">
				<c:choose>
					<c:when test="${nombre != null}">
						<td>
							<div class="input-group">
								<input class="form-control" id="nombre" type="text" name="nombre" value="${nombre}" readonly="readonly">
							</div>
						</td>
					</c:when>
					<c:otherwise>
						<td><input class="form-control" id="nombre" type="text" name="nombre" readonly="readonly"></td>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${nombre != null}">
						<td>
							<div class="input-group">
								<input id="nombre" type="text" class="form-control" name="nombre" value="${nombre}" readonly="readonly" title="Digite un Equipo de Trabajo. Gracias">
							</div>
						</td>
					</c:when>
					<c:otherwise>
						<td><input id="nombre" type="text" class="form-control" name="nombre" placeholder="nombre" title="Digite un Equipo de Trabajo. Gracias"></td>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td><h4>Email:</h4></td>
			<c:choose>
				<c:when test="${email != null}">
					<td>
						<div class="input-group">
							<input id="email" type="email" class="form-control" name="email" value="${email}" placeholder="name@example.com" title="Digite un Email valido. Gracias">
						</div>
					</td>
				</c:when>
				<c:otherwise>
					<td><input id="email" type="email" class="form-control" name="email" placeholder="name@example.com" maxlength="50" title="Digite un Email valido. Gracias"></td>
				</c:otherwise>
			</c:choose>
	</tr>
</table>
<!-- Boton Agregar y Modificar Equipos -->
         	<td>
				<c:choose>
					<c:when test="${opcion == 2}">
						<input type="submit" id="btn" class="btn btn-inverse" name="boton" value="Modificar">
					</c:when>
					<c:otherwise>
						<input type="button" id="boton" class="btn btn-inverse" name="boton" value="Agregar" onclick="validarEquipos()">
					</c:otherwise>
				</c:choose>
			</td>
			<!-- Boton Cancelar Equipos -->
     		<td><input type="submit"  id="btn" class="btn btn-inverse" name="Cancelar" value="Cancelar"></td>
			<!-- Boton Listar Equipos
			<td><button type="submit" id="btn" class="btn btn-inverse" name="listar" value="listar">Mostrar Equipos</button></td> -->
<c:if test="${mensaje != null}">
	<h2>${mensaje}</h2><br>
</c:if><br><br>
<c:if test="${equipos != null}">
	<table class="table table-hover">
		<caption>Listado de los Equipos</caption>
		<tr>
		<th>Nombre</th>
			<th>Email</th>
			<th colspan="2">Administrar Equipos</th>
		</tr>
			<c:forEach var="miEquipo" items="${equipos}">
					<tr>
						<td>${miEquipo.nombre}</td>
						<td>${miEquipo.email}</td>
						<td>
							<input id="modificar" type="hidden" name="modificar" value="null">
							<span title="Modificar Equipo"><input id="modificar" type="image"
								  src="./img/btnEditar.jpg" name="modificar"
								  onclick="document.getElementById('modificar').value='${miEquipo.nombre}' ;this.form.submit();"></span>
						</td>
						<td>
							<input type="hidden" name="eliminar" id="eliminar" value="null">
							<span title="Eliminar Equipo"><input
								  type="image" src="./img/btnEliminar.jpg" id="eliminar" name="eliminar"
								  onclick="document.getElementById('eliminar').value='${miEquipo.nombre}'; confirmar();" ></span>
						</td>
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