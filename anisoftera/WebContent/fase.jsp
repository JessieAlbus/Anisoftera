<%@page import="umariana.anisoftera.mundo.Integracion"%>
<%@page import="umariana.anisoftera.mundo.Integrante"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	if(document.getElementById('bandera').value == ""){
		document.forma.submit();
	}
}
</script>
<script type="text/javascript">
function validarFases(){
	if(document.getElementById('fase').value == ""){
		fase.focus();
		alert('No Has Introducido el Nombre de la Fase');
	}else{
		document.getElementById('guardarFase').value="Guardar";
		this.forma.submit();
	}
}
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
<form action="AgregarFase" method="POST" name="forma">
	<!-- Validar al ejecutar una vez el OnLoad -->
	<c:choose>
		<c:when test="${bandera != null}">
			<input type="hidden" name="bandera" id="bandera" value="${bandera}">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="bandera" id="bandera" value="">
		</c:otherwise>
	</c:choose>
	<!-- Variable para validar el formulario al validar los campos -->
	<c:choose>
		<c:when test="${bandera2 != null}">
			<input type="hidden" name="guardarFase" id="guardarFase" value="${bandera2}">
		</c:when>
		<c:otherwise>
				<input type="hidden" name="guardarFase" id="guardarFase" value="">
		</c:otherwise>
	</c:choose>
<table class="tabla">
<tr>
	<td><h4>Fase:</h4></td>
	<td>
		<div class="input-group">
			<input type="text" class="form-control" name="fase" id="fase" title="Digite el Nombre de la Fase. Gracias">
		</div>
	</td>
</tr>
	<tr>
       <td><button type="button"  id="guardarFase" name="guardarFase" class="btn btn-inverse" value="Guardar" onclick="validarFases()" >Guardar</button></td>
	   <td><button type="reset" id="btn" class="btn btn-inverse" name="salir">Cancelar</button></td>
	</tr>
</table>
<c:if test="${mensaje != null}">
	<h2>${mensaje}</h2></br>
</c:if> <br>
<c:if test="${fases != null}">
	<table class="table table-hover">
		<caption>Listado de las Fases</caption>
		<tr>
			<th>Fases</th>
			<th>Administrar Fases</th>
		</tr>		
		<c:forEach var="miFase" items="${fases}">
			<tr>
				<td>${miFase.nombre}</td>			
				<td><input type="hidden" name="eliminar" id="eliminar" value="null"><span title="Eliminar Fase"><input type="image" src="./img/btnEliminar.jpg" id="eliminar" name="eliminar" onclick="document.getElementById('eliminar').value='${miFase.nombre}' ;"></span></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</form>
</div></div>
</body>
</html>