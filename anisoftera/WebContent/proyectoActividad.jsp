<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<!-- Estilo imagen e iframe -->
	<link href="./css/login.css" rel="stylesheet" type="text/css">
<!-- Validar una sola vez el formulario -->
<script type="text/javascript">
function cargar(){
	if(document.getElementById('bandera').value == ""){
		document.forma2.submit();
	}
}
</script>
</head>
<body onload="cargar()">
<%	HttpSession sesion = request.getSession();
	String url = "login.html";
	if (sesion.getAttribute("usuario") == null) {
		request.getRequestDispatcher(url).forward(request, response);
	}
%>
<div class="container">
<div class="jumbotron">
<form action="#" method="#" name="forma2">
	<% /** Variable para ejecutar una vez el OnLoad */ %>
		<c:choose>
			<c:when test="${bandera != null}">
				<input type="hidden" name="bandera" id="bandera" value="${bandera}">
			</c:when>
			<c:otherwise>
				<input type="hidden" name="bandera" id="bandera" value="">
			</c:otherwise>
		</c:choose>
		<table>
			<tr>
			<td><h4>Proyectos:</h4></td>
				<td>
					<select class="form-control" id="proyecto" name="proyecto" onChange="this.form.submit()" title="Seleccione un Proyecto. Gracias">
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
						<td><h4>Ciclos:</h4></td>
						<td>
							<select class="form-control" id="ciclo" name="ciclo" onChange="this.form.submit()" title="Seleccione un Ciclo. Gracias">
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
						<td><h4>Fases:</h4></td>
						<td>
							<select class="form-control" id="fase" name="fase" onChange="this.form.submit()" title="Seleccione una Fase. Gracias">
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
						<td> Actividades </td>
						<td></td>
						<td>Actividad del Ciclo</td>
					</tr>
					<tr>
						<td>
							<select class="form-control" id="lista1" name="lista1" size="10" title="Seleccione la Actividad a Registrar. Gracias">
								<option>Actividad 1</option>
								<option>Actividad 2</option>
								<option>Actividad 3</option>
								<option>Actividad 4</option>
								<option>Actividad 5</option>
								<option>Actividad 6</option>
								<option>Actividad 7</option>
								<option>Actividad 8</option>
								<option>Actividad 9</option>
								<option>Actividad 10</option>
							</select>
						</td>
						<td>
							<table>
								<tr><td><button type="submit" id="SelTodos" class="btn btn-inverse" name="listarSemana" title="Seleccionar Todos"> >> </button></td></tr>
								<tr><td><button type="submit" id="SelUno" class="btn btn-inverse"name="listarSemana" title="Selecciona Uno"> -> </button></td></tr>
								<tr><td><button type="submit" id="DesUno" class="btn btn-inverse" name="listarSemana" title="Deselecciona Uno"> <- </button></td></tr>
								<tr><td><button type="submit" id="DesTodos" class="btn btn-inverse"name="listarSemana" title="Deseleccionar Todos"> << </button></td></tr>
							</table>
						</td>
						<td>
							<select class="form-control" id="lista2" name="lista2" size="10" title="Actividad a Registrar. Gracias">
								<option>		</option>
								<option>		</option>
								<option>		</option>
								<option>		</option>
								<option>		</option>
								<option>		</option>
								<option>		</option>
								<option>		</option>
								<option>		</option>
								<option>		</option>
							</select>
						</td>
					</tr>
			</table>
			<br> <br>
			<table>
				<tr>
					<td><input type="button" id="btn" class="btn btn-inverse" name="guardar" value="Registrar"></td>
					<td><button type="reset" id="btn" class="btn btn-inverse" name="salir">Cancelar</button></td>
				</tr>
			</table>
		</form>
	</body>
</html>