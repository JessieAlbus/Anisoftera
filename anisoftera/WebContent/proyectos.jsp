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
	<link rel="stylesheet" href="/resources/demos/style.css">
	<!-- Estilo combobox -->
	<link rel="stylesheet" href="../../jqwidgets/styles/jqx.base.css" type="text/css" />
    <script type="text/javascript" src="../../scripts/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../../scripts/demos.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxcombobox.js"></script>
<!-- Validar una sola vez el formulario -->
<script type="text/javascript">
function cargar(){
	if(document.getElementById('bandera').value == ""){
		document.forma.submit();
	}
}
</script>
<!-- Validación de los campos vacios -->
<script type="text/javascript">
function validarProyectos(){
	if(document.getElementById('nombre').value == ""){
		nombre.focus();
		alert('No Has Ingresado El Nombre Del Proyecto');
	}else if(document.getElementById('datepickerInicio').value == ""){
		datepickerInicio.focus();
		alert('No Has Ingresado La Fecha Inicial Del Proyecto');
	}else if(document.getElementById('spinner').value == ""){
		spinner.focus();
		alert('No Has Ingresado El Semestre Actual');
	}else if(document.getElementById('datepickerFin').value == ""){
		datepickerFin.focus();
		alert('No Has Ingresado La Fecha Final Del Proyecto');
	}else if(document.getElementById('spinnerAnio').value == ""){
		spinnerAnio.focus();
		alert('No Has Ingresado El Año Actual');
	}else{
		document.getElementById('boton').value="Agregar";
		this.forma.submit();
	}
}
</script>
<!-- Validacion al eliminar un registro -->
<script>
function quieresBorrar(){
	//var eliminar = confirm("¿Deseas borrar el proyecto seleccionado?");
	//if(eliminar){
		//alert("Proyecto Eliminado!!!");
	//}else{
		//window.location = "http://localhost:8080/anisoftera/proyectos.jsp";
	//}
	if(window.confirm("Desea eliminar este proyecto?")){
		document.forma.submit();
	}else{
		alert("Cancelado será redirigido a la página principal");
		window.location="http://localhost:8080/anisoftera/proyectos.jsp";
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
<!-- Semestre -->
<script type="text/javascript">
	$(function(){
		$("#spinner").spinner();
	});
</script>
<!-- Año -->
<script type="text/javascript">
	$(function(){
		$("#spinnerAnio").spinner();
	});
</script>
<!-- Combobox -->

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
<div class="container" id="listarProyectos">
<div class="jumbotron">
<form action="AgregarProyecto" method="POST" name="forma">
	<!-- Variable para ejecutar una vez el OnLoad -->   
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
		<select class="form-control" id="equipo" name="equipo" onChange="this.form.submit()" title="Seleccione un Equipo de Trabajo. Gracias">
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
	<td><h4>Nombre:</h4></td>
	<c:choose>
		<c:when test="${opcion == 2}">
			<c:choose>
				<c:when test="${nombre != null}">
					<td>
						<div>
							<input type="text" class="form-control" name="nombre" value="${nombre}" readonly="readonly">
						</div>
					</td>
				</c:when>
				<c:otherwise>
					<td><input type="text" class="form-control" name="nombre"  id="nombre" readonly="readonly"></td>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${nombre != null}">
					<td>
						<div class="input-group">
							<input type="text" class="form-control" name="nombre" value="${nombre}" readonly="readonly">
						</div>
					</td>
				</c:when>
				<c:otherwise>
					<td><input type="text" class="form-control" name="nombre" id="nombre"  placeholder="nombre" title="Digite un Proyecto para su Equipo de Trabajo. Gracias"></td>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</tr>
<tr>
	<td><h4>Fecha Inicio:</h4></td>
		<c:choose>
			<c:when test="${inicio != null}">
				<td>
					<div class="input-group">
						<input type="text" id="datepickerInicio" class="form-control" name="datepickerInicio" value="${inicio}">
					</div>
				</td>
			</c:when>
			<c:otherwise>
				<td><input type="text" id="datepickerInicio" class="form-control" name="datepickerInicio" maxlength="10" placeholder="mm/dd/aaaa" title="Seleccione la Fecha de Inicio del Proyecto. Gracias"></td>
			</c:otherwise>
		</c:choose>
</tr>
<tr>
	<td><h4>Fecha Fin:</h4></td>
	<c:choose>
		<c:when test="${fin != null}">
			<td>
				<div class="input-group">
					<input type="text" id="datepickerFin" class="form-control" id="datepickerFin" name="fin" value="${fin}">
				</div>
			</td>
		</c:when>
		<c:otherwise>
			<td><input type="text" id="datepickerFin" class="form-control" maxlength="10" name="datepickerFin" placeholder="mm/dd/aaaa" title="Seleccione la Fecha de Fin del Proyecto. Gracias"></td>
		</c:otherwise>
	</c:choose>
</tr>
<tr>
	<td><h4>Semestre:</h4></td>
	<c:choose>
		<c:when test="${semestre != null}">
			<td>
				<div class="input-group">
					<input id="spinner" maxlength="2" type="number" class="form-control" name="spinner" placeholder="Semestre" min="1" max="10" value="${semestre}">
				</div>
			</td>
		</c:when>
		<c:otherwise>
			<td><input id="spinner" maxlength="2" type="number" class="form-control" name="spinner" placeholder="Semestre" min="1" max="10" value="${semestre}" title="Seleccione el Semestre que está Cursando. Gracias">
		</c:otherwise>
	</c:choose>
</tr>
<tr>
	<td><h4>Año:</h4></td>
	<c:choose>
		<c:when test="${anio != null}">
			<td>
				<div class="input-group">
					<input id="spinnerAnio" type="number" class="form-control" name="spinnerAnio" placeholder="año entre (2014-2050)" min="2014" max="2050" value="${anio}">
				</div>
			</td>
		</c:when>
		<c:otherwise>
			<td><input id="spinnerAnio" type="number" class="form-control" name="spinnerAnio" placeholder="año entre (2014-2050)" min="2014" max="2050" title="Seleccione el Año Correspondiente. Gracias"></td>
		</c:otherwise>
	</c:choose>
</tr>
</table>
<td>
	<c:choose>
		<c:when test="${opcion == 2}">
			<input type="submit" id="btn" class="btn btn-inverse" name="boton" value="Modificar">
		</c:when>
		<c:otherwise>
			<input type="button" id="boton" class="btn btn-inverse" name="boton" value="Agregar" onclick="validarProyectos()">
		</c:otherwise>
	</c:choose>
</td>
<td><button type="reset" id="btn" class="btn btn-inverse">Cancelar</button></td>
<!--<td><button type="submit" id="btn" class="btn btn-inverse" name="listarProyectos">Mostrar Proyectos</button></td> -->
<c:if test="${mensaje != null}">
	<h2>${mensaje}</h2><br>
</c:if><br> <br>
<c:if test="${proyectos != null}">
	<table class="table table-hover">
		<caption>Listado de los Proyectos</caption>
		<tr>
			<th>Equipo</th>
			<th>Nombres</th>
			<th>Semestre</th>
			<th>Año</th>
			<th>Fecha Inicio</th>
			<th>Fecha Final</th>
			<th colspan="2">Administrar Proyectos</th>
		</tr>
		<c:forEach var="miProyecto" items="${proyectos}">
			<tr>
				<td>espacio reservado</td>
				<td>${miProyecto.nombreProyecto}</td>
				<td>${miProyecto.semestre}</td>
				<td>${miProyecto.anio}</td>
				<td>${miProyecto.fechaInicio}</td>
				<td>${miProyecto.fechaFin}</td>
				<td>
					<input type="hidden" name="modificar" id=modificar value="null">
					<span title="Modificar Proyecto"><input type="image" src="./img/btnEditar.jpg" name="modificar" onclick="document.getElementById('modificar').value='${miProyecto.nombreProyecto}' ;this.form.submit();"></span>
				</td>
				<td>
					<input type="hidden" name="eliminar" id="eliminar" value="null">
					<span title="Eliminar Proyecto"><input type="image" src="./img/btnEliminar.jpg" id="eliminar" name="eliminar" onclick= "quieresBorrar(document.getElementById('eliminar').value='${miProyecto.nombreProyecto}');this.form.submit();"></span>
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