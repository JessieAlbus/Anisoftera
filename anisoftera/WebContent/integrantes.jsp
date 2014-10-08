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
<script type="text/javascript">
	function cargar(){
		if(document.getElementById('bandera').value == ""){
			document.forma2.submit();
		}
	}
</script>
<!-- Validar campos integrantes -->
<script type="text/javascript">
function validarIntegrantes(){
	if(document.getElementById('nombre').value==""){
		nombre.focus();
		alert('No Has Ingresado El Nombre Del Integrante');
	}else if(document.getElementById('email').value.indexOf('@') == -1){
		email.focus();
		alert('No Has Introducido El E-mail Del Integrante Valido');
	}else if(document.getElementById('cod').value == ""){
		cod.focus();
		alert('No Has Ingresado La Identificación Del Integrante');
	}else if(document.getElementById('combobox').value == ""){
		combobox.focus();
		alert('No Has Seleccionado El Rol Del Estudiante');
	}else{
		document.getElementById('boton').value="Agregar";
		this.forma2.submit();
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
<form action="AgregarIntegrnate" method="POST" name="forma2">
	<% /** Variable para ejecutar una vez el OnLoad */ %>
    <c:choose>
		<c:when test="${bandera != null}">
			<input type="hidden" name="bandera" id="bandera" value="${bandera}">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="bandera" id="bandera" value="">
		</c:otherwise>
	</c:choose>
	<!-- Validar Proyectos con hidden -->
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
	<td><h4>Equipos:</h4></td>
	<td>
		<select class="form-control" id="equipo" name="equipo"  onChange="this.form.submit()" title="Seleccione un Equipo. Gracias">
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
	<td><h4>Nombre(s):</h4></td>
		<c:choose>
			<c:when test="${nombre != null}">
				<td>
					<div class="input-group">
						<input type="text" class="form-control" name="nombre" value="${nombre}">
					</div>
				</td>
			</c:when>
			<c:otherwise>
				<td><input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" title="Digite el Nombre del Integrante. Gracias"></td>
			</c:otherwise>
		</c:choose>
</tr>
<tr>			
	<td><h4>Email:</h4></td>
		<c:choose>
			<c:when test="${email != null}">
				<td>
					<div class="input-group">
						<input type="email" class="form-control" name="email" value="${email}">
					</div>
				</td>
			</c:when>
			<c:otherwise>
				<td><input id="email" type="email" class="form-control" name="email" placeholder="name@example.com" title="Digite un Email Valido. Gracias"></td>
			</c:otherwise>
		</c:choose>
</tr>
<tr>
	<td><h4>Identificación:</h4></td>
		<c:choose>
			<c:when test="${opcion == 2}">
				<c:choose>
					<c:when test="${id != null}">
						<td>
							<div>
								<input id="cod" type="text" class="form-control" name="id" value="${id}" readonly="readonly">
							</div>
						</td>
					</c:when>
					<c:otherwise>
						<input id="cod" type="text" class="form-control" name="id" placeholder="Código" title="Digite su Código Estudiantil. Gracias">
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${id != null}">
						<td>
							<div class="input-group">
								<input id="cod" type="text" class="form-control" name="id" value="${id}">
							</div>
						</td>
					</c:when>
					<c:otherwise>
						<td><input id="cod" type="text" class="form-control" name="id" placeholder="Código" title="Digite su Código Estudiantil. Gracias"></td>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
</tr>
<tr>
	<td><h4>Rol:</h4></td>
		<td>
			<select class="form-control" id="combobox" name="combobox" title="Seleccione su Rol. Gracias">
				<option name="" value="Seleccionar..."></option>
				<option name="Lider_Proyecto" value="Lider_Proyecto"> Lider_Proyecto </option> 
				<option name="Lider_Desarrollo" value="Lider_Desarrollo"> Lider_Desarrollo </option>
				<option name="Lider_Planeacion" value="Lider_Planeacion"> Lider_Planeación </option>
				<option name="Lider_Soporte" value="Lider_Soporte"> Lider_Soporte </option>
				<option name="Lider_Calidad" value="Lider_Calidad"> Lider_Calidad </option>
			</select>
		</td>
</tr>
</table>
	<td>
		<c:choose>
			<c:when test="${opcion == 2}">
				<input type="submit" id="btn" class="btn btn-inverse" name="boton" value="Modificar">
			</c:when>
			<c:otherwise>
				<input type="button" id="boton" class="btn btn-inverse" name="boton" value="Agregar" onclick="validarIntegrantes()">
			</c:otherwise>
		</c:choose>
	</td>
	<td><button type="reset" id="btn" class="btn btn-inverse">Cancelar</button></td>
	<!--<td><button type="submit" id="btn" class="btn btn-inverse" name="listarProyectos">Mostrar Integrantes</button></td>-->
<c:if test="${mensaje != null}">
	<h2>${mensaje}</h2><br>
</c:if><br><br>
<c:if test="${integrantes != null}">
	<table class="table table-hover">
		<caption>Listado de los Integrantes</caption>
		<tr>
			<th>Equipo</th>
			<th>Cod. Integrante</th>
			<th>Nombre(s)</th>						
			<th>Email</th>
			<th>Rol</th>
			<th colspan="2">Administrar Integrantes</th>
		</tr>
		<c:forEach var="miIntegrante" items="${integrantes}">
			<tr>
				<td>${miEquipo.nombre}</td>
				<td>${miIntegrante.idIntegrante}</td>
				<td>${miIntegrante.nombreIntegrante}</td>							
				<td>${miIntegrante.emailIntegrante}</td>
				<td>${miIntegrante.rolIntegrante}</td>
				<td>
					<input type="hidden" name="modificar" id="modificar" value="null">
					<span title="Modificar Integrante"><input type="image" src="./img/btnEditar.jpg" name="modificar" onclick="document.getElementById('modificar').value='${miIntegrante.idIntegrante}' ;this.form.submit();"></span>
				</td>
				<td>
					<input type="hidden" name="eliminar" id="eliminar" value="null">
					<span title="Eliminar Integrante"><input type="image" src="./img/btnEliminar.jpg" id="eliminar" name="eliminar" onclick="document.getElementById('eliminar').value='${miIntegrante.idIntegrante}' ;"></span>
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