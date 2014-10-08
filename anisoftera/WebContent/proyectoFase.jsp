<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anisoftera 2014</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/bootstrap-theme.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet" href="./css/combobox.css">

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<script src="js/spinner.js"></script>
<script src="js/combobox.js"></script>

<script type="text/javascript">
	$().ready(function() {
		$('.pasar').click(function() {
			return !$('#origen option:selected').remove().appendTo('#destino');
		});
		$('.quitar').click(function() {
			return !$('#destino option:selected').remove().appendTo('#origen');
		});
		$('.pasartodos').click(function() {
			$('#origen option').each(function() {
				$(this).remove().appendTo('#destino');
			});
		});
		$('.quitartodos').click(function() {
			$('#destino option').each(function() {
				$(this).remove().appendTo('#origen');
			});
		});
		$('.submit').click(function() {
			$('#destino option').prop('selected', 'selected');
		});
	});
</script>
<!-- Valida el formulario para cargar el array del select destino -->
<script type="text/javascript">
	function datosDestino(){
		var combo = document.forms["forma2"].destino;
		   for (var i = 0; i < destino.length; i++) {
		         combo[i].selected = true; 
		   }
		   this.forma2.submit();
	}
</script>
<!-- Validar una sola vez el formulario -->
<script type="text/javascript">
	function cargar() {
		if (document.getElementById('bandera').value == "") {
			document.forma2.submit();
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
<p></p>
<form action="RegistrarFaseProyecto" method="POST" name="forma2">
<%	/** Variable para ejecutar una vez el OnLoad */ %>
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
		<td><select class="form-control" id="proyecto" name="proyecto" onchange="this.form.submit()">
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
			</select></td>
</tr>
	<tr>
		<td><h4>Ciclos:</h4></td>
			<td><select class="form-control" id="ciclo" name="ciclo" onchange="this.form.submit()">
					<c:forEach var="miCiclo" items="${ciclos}">
							<c:choose>
								<c:when test="${miCiclo.numeroCiclo.equals(ciclo)}">
									<option selected value="${miCiclo.numeroCiclo}">${miCiclo.numeroCiclo}</option>
								</c:when>
								<c:otherwise>
									<option value="${miCiclo.numeroCiclo}">${miCiclo.numeroCiclo}</option>
								</c:otherwise>
							</c:choose>
					</c:forEach>
			</select></td>
		</tr>
	<tr>
		<c:if test="${mensaje != null}">
			<h2>${mensaje}</h2>
			<br></br>
		</c:if>
	</tr>
	<tr>
		<td>Fases</td>
		<td></td>
		<td>Fases del Ciclo</td>
	</tr>
	<tr>
		<td>
			<!--select name="origen[]" id="origen" multiple="multiple" size="8"-->
			<select name="origen[]" id="origen" multiple="multiple" size="10">
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
		<td>
			<table>
				<tr>
					<tr><input type="button" class="pasartodos izq" value="Todos »"></tr>
					<tr><p></tr>
					<tr><input type="button" class="pasar izq" value="Pasar »"></tr>
					<tr><p></tr>
					<tr><input type="button" class="quitar der" value="« Quitar"></tr>
					<tr><p></tr>
					<tr><input type="button" class="quitartodos der" value="« Todos"></tr>
				</tr>
			</table>
		</td>
		<td>
			<div class="">
				<select name="destino[]" id="destino" multiple="multiple" size="8"></select>
			</div>

		</td>
	</tr>
</table>
<br>
<table>
	<tr>
		<!-- <p class="clear"><input type="submit" class="submit" value="Procesar formulario"></p>  -->
		<td><input type="submit"  id="btn" class="btn btn-inverse" name="Guardar" value="Guardar" onclick="datosDestino()"></td>
		<td><input type="submit"  id="btn" class="btn btn-inverse" name="Cancelar" value="Cancelar"></td>
	</tr>
</table>
</form>
</body>
</html>