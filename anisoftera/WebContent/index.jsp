<!-- Abrir la seccion -->
<%@ page session = "true"%>
<!-- VAriable que entra como usuario del campo de texto de la clase login.html -->
<% String nombre = (String)session.getAttribute("usuario");%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Software Anisoftera 2014</title>
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
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Navegacion</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">Anisoftera_2014</a>
            </div>
            <!-- Top Menu Items -->
              <ul class="nav navbar-right top-nav">
            	<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%=nombre%> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="cerrarSesion.jsp"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
               </ul>
			<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                        <a href="inicio.jsp" target="principal"><i class="fa fa-fw fa-dashboard"></i>Home</a>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#equipo"><i class="fa fa-fw fa-desktop"></i>Equipos<i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="equipo" class="collapse">
                        	<li><a href="equipos.jsp" target="principal">Agregar Equipos</a></li>	
                        	<li><a href="integrantes.jsp" target="principal">Agregar Integrantes</a></li>
                        	<li><a href="fase.jsp" target="principal">Agregar Fases</a></li>
                        	<li><a href="actividad.jsp" target="principal">Agregar Actividades</a></li>
                        	<li><a href="registrarUsuario.jsp" target="principal">Agregar Usuarios</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#proyecto"><i class="fa fa-fw fa-table"></i>Proyectos<i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="proyecto" class="collapse">
                        	<li><a href="proyectos.jsp" target="principal">Agregar Proyectos</a></li>
                        	<li><a href="ciclos.jsp" target="principal">Agregar Ciclos</a></li>
                        	<li><a href="semana.jsp" target="principal">Agregar Semanas</a></li>
                        	<li><a href="proyectoActividad.jsp" target="principal">Registrar Actividades</a></li>
                        	<li><a href="proyectoFase.jsp" target="principal">Registrar Fases</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#medicion"><i class="fa fa-fw fa-edit"></i>Mediciones<i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="medicion" class="collapse">
                        	<li><a href="#" target="principal">Registrar Evaluaciones</a></li>
                        	<li><a href="#" target="principal">Registrar Tiempos</a></li>   
                        </ul>               
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#reporte"><i class="fa fa-fw fa-bar-chart-o"></i>Reportes<i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="reporte" class="collapse">
                        	<li><a href="#" target="principal">Generar Reportes</a></li>   
                        </ul>                 
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
        <div id="page-wrapper">
			<iframe class="container-fluid" width="100%" height="450px" name="principal" src="inicio.jsp"></iframe>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <footer>
	<p>Copyright @ 2014 esta investigacion-2014</p>
	</footer>
    <!-- /#wrapper -->
    <!-- jQuery Version 1.11.0 -->
    <script src="js/jquery-1.11.0.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>
</body>
</html>
