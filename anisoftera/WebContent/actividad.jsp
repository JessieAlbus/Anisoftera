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
	<link rel="stylesheet" href="./css/combobox.css">
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- Estilos para la fecha -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
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
<!-- Validar los campos vacios -->
<script type="text/javascript">
function validarActividad(){
	if(document.getElementById('nombre').value == ""){
		nombre.focus();
		alert('No Has Introducido El Nombre De la Actividad');
	}else if(document.getElementById('spinner').value == ""){
		spinner.focus();
		alert('No Has Seleccionado las Horas');
	}else if(document.getElementById('spinnerMin').value == ""){
		spinnerMin.focus();
		alert('No Has Seleccionado los Minutos');
	}else{
		document.getElementById('Agregar').value="Agregar";
		this.forma.submit();
	}
}
</script>
<!-- Horas  -->
<script type="text/javascript">
	$(function(){
		$("#spinner").spinner();
	});
</script>
<!-- Minutos -->
<script type="text/javascript">
	$(function(){
		$("#spinnerMin").spinner();
	});
</script>
</head>
<body onload="cargar()">
<%
HttpSession sesion = request.getSession();
String url= "login.html" ;
	if(sesion.getAttribute("usuario")==null){
		request.getRequestDispatcher(url).forward(request, response);	
	}
%>
<div class="container">
<div class="jumbotron">
<form action="AgregarActividad" method="POST" name="forma">
<% /** Variable para ejecutar una vez el OnLoad */ %>
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
			<input type="hidden" name="Agregar" id="Agregar" value="${bandera2}">
		</c:when>
		<c:otherwise>
				<input type="hidden" name="Agregar" id="Agregar" value="">
		</c:otherwise>
	</c:choose>
<table>
<tr>
	<td><h4>Fases:</h4></td>
	<td>
		<select class="form-control" id="fase" name="fase" onChange="this.form.submit()" title="Seleccione la Fase, para Agregarle la Actividad. Gracias">
			<c:forEach var="miFase" items="${fases}">
				<c:choose>
					<c:when test="${miFase.nombre.equals(fase)}">
						<option selected value="${miFase.nombre}">${miFase.nombre}</option>
					</c:when>
					<c:otherwise>
						<option value="${miFase.nombre}">${miFase.nombre}</option>
					</c:otherwise>
				</c:choose>					
			</c:forEach>	
		</select>
	</td>
</tr>
<tr>
	<td><h4>Nombre:</h4></td>
	<c:choose>
		<c:when test="${opcion == 2}">
			<c:choose>
				<c:when test="${nombre != null}">
					<td>
						<div class="input-group">
							<input id="nombre" type="text" class="form-control" name="nombre" value="${nombre}" readonly="readonly">	
						</div>
					</td>
				</c:when>
				<c:otherwise>
					<td><input type="text" class="form-control" name="nombre" id="nombre"  placeholder="nombre" title="Digite el Nombre de la Actividad. Gracias"></td>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${nombre != null}">
					<td>
						<div class="input-group">
							<input id="nombre" type="text" class="form-control" name="nombre" value="${nombre}" readonly="readonly">
						</div>
					</td>
				</c:when>
				<c:otherwise>
					<td><input type="text" class="form-control" name="nombre" id="nombre"  placeholder="nombre" title="Digite el Nombre de la Actividad. Gracias"></td>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</tr>
<tr>
	<td><h4>Horas:</h4></td>
	<c:choose>
		<c:when test="${tiempo != null}">
			<td>
				<div maxlength="2" class="input-group">
					<input id="spinner" type="number" class="form-control" name="horas" min="1" max="48" value="${tiempo}">
				</div>
			</td>
			</c:when>
			<c:otherwise>
				<td><input id="spinner" maxlength="2" type="number" class="form-control" name="horas" placeholder="Horas" min="1" max="24" value="${tiempo}" title="Seleccione las Horas Calculadas. Gracias">
			</c:otherwise>
	</c:choose>						
</tr>
<tr>
	<td><h4>Minutos:</h4></td>
	<c:choose>
		<c:when test="${tiempo != null}">
			<td>
				<div maxlength="2" class="input-group">
					<input id="spinnerMin" type="number" class="form-control" name="minutos" min="0" max="48" value="${tiempo}">
				</div>
			</td>
			</c:when>
			<c:otherwise>
				<td><input id="spinnerMin" maxlength="2" type="number" class="form-control" name="minutos" placeholder="Minutos"  min="0" max="59" value="${tiempo}" title="Seleccione los Minutos Calculados. Gracias">
			</c:otherwise>
	</c:choose>
</tr>
</table>
<tr>

					<tr>
						<td><c:choose>
								<c:when test="${opcion == 2}">
									<input type="submit" id="btn" class="btn btn-inverse"
										name="boton" value="Modificar">
								</c:when>
								<c:otherwise>
									<input type="button" id="btn" class="btn btn-inverse"
										name="Agregar" value="Agregar" onclick="validarActividad()">
								</c:otherwise>
							</c:choose></td>
						
							<td><input type="submit" id="btn" class="btn btn-inverse" value="Cancelar"></td>
						

					</tr>
	
</tr>
<c:if test="${mensaje != null}">
	<h2>${mensaje}</h2><br>
</c:if><br> <br> 
<c:if test="${actividades != null}">
	<table class="table table-hover">
		<caption>Listado de las Actividades</caption>
		<tr>
			<th>Fases</th>
			<th>Nombres</th>
			<th>Estado</th>
			<th>Tiempo</th>
			<th colspan="2">Administrar Activadad</th>
		</tr>
		<c:forEach var="miActividad" items="${actividades}">
				<tr>
					<td>Espacio reservado</td>
					<td>${miActividad.nombre}</td>
					<td>${miActividad.estado}</td>
					<td>${miActividad.horasPlaneadas}: ${miActividad.minutosPlaneados}</td>
					<td>
						<input type="hidden" name="modificar" id=modificar value="null"> 
						<span title="Modificar Actividad"><input type="image" src="./img/btnEditar.jpg" name="modificar" onclick="document.getElementById('modificar').value='${miActividad.nombre}' ;this.form.submit();"></span>
					</td>
					<td>
						<input type="hidden" name="eliminar" id="eliminar" value="null">
						<span title="Eliminar Actividad"><input type="image" src="./img/btnEliminar.jpg" id="eliminar" name="eliminar" onclick="document.getElementById('eliminar').value='${miActividad.nombre}' ;"></span>
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