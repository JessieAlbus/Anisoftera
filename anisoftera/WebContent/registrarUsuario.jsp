<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
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
</head>
<body>
	<div class="login"> 
	    <div class="img"><img src="img/logo.png" alt="logo"></div>
	    <br>
	    <div class="panel panel-default">
	        <div class="panel-heading">
	            <h3 class="panel-title">Registrate</h3>
	        </div>
	        <div class="panel-body">
	        	<form action="AgregarUsuario"  method="POST">
	            <table>
	                <tr>
	                    <td> <h4>Usuario</h4></td>
	                    <c:choose>
						<c:when test="${nombre != null}">
	                    <td><div class="input-group">
	                        <input type="text" class="form-control" name="nombre" id="usuario" value="${nombre}">
	                        </div>
	                    </td>
	                     </c:when>
                            <c:otherwise>
							<td><input class="form-control" type="text" name="nombre" title="Digite un Nuevo Usuario. Gracias"></td>
						</c:otherwise>
					</c:choose>
	                </tr>
	                <tr>
	                    <td> <h4>Clave</h4></td>
	                    <c:choose>
						<c:when test="${nombre != null}">
	                    <td><div class="input-group">
	                        <input type="password" class="form-control" name="clave" id="password"  value="${clave}">
	                        </div>
	                    </td>
	                     </c:when>
                            <c:otherwise>
							<td><input class="form-control" type="password" name="clave" title="Digite su Clave de Usuario. Gracias"></td>
						</c:otherwise>
					</c:choose>
	                </tr>
	                <tr>
	                    <td>
	                    </td>
	                </tr>
	                <tr>
	                	<td></td>
	                    <td ><button id="guardar" type="submit" name="guardar" class="btn btn-inverse">Guardar</button></td>
	                    <td></td>
	                </tr>
	            </table>
	            </form>
	        </div>
	    </div>
	</div>
</html>