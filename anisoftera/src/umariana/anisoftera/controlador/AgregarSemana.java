package umariana.anisoftera.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import umariana.anisoftera.mundo.Ciclo;
import umariana.anisoftera.mundo.Equipo;
import umariana.anisoftera.mundo.Integracion;
import umariana.anisoftera.mundo.Integrante;
import umariana.anisoftera.mundo.Proyecto;
import umariana.anisoftera.mundo.Semana;


public class AgregarSemana extends HttpServlet{


	/**
	 * @uml.property  name="integracion"
	 * @uml.associationEnd  inverse="agregarActividad:umariana.anisoftera.mundo.Integracion"
	 */
	private Integracion integracion;

	/**
	 * Getter of the property <tt>integracion</tt>
	 * @return  Returns the integracion.
	 * @uml.property  name="integracion"
	 */
	public Integracion getIntegracion() {
		return integracion;
	}

	/**
	 * Setter of the property <tt>integracion</tt>
	 * @param integracion  The integracion to set.
	 * @uml.property  name="integracion"
	 */
	public void setIntegracion(Integracion integracion) {
		this.integracion = integracion;
	}
	
	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public AgregarSemana() throws ClassNotFoundException, SQLException{
		integracion = new  Integracion() ;
	}			

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		try {
			integracion= new Integracion() ;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		// esta es una prueva 
		// otro comentario 
		String modificar = request.getParameter("modificar")==null?"null":request.getParameter("modificar");
		String eliminar = request.getParameter("eliminar") ==null?"null":request.getParameter("eliminar");
		String boton = request.getParameter("boton")==null?"null":request.getParameter("boton");
		String proyecto = request.getParameter("proyecto") ==null?"null":request.getParameter("proyecto");
		String opcion = request.getParameter("opcion");		
		String listar = request.getParameter("listarSemana");
		String cadenaSemana = request.getParameter("spinner") ;
		String fechaInicio = request.getParameter("datepickerInicio") ;
		String fechaFin = request.getParameter("datepickerFin") ;		
		String equipo = request.getParameter("equipo");
		String bandera = request.getParameter("bandera");
		String ciclo = request.getParameter("ciclo");
		request.setAttribute("bandera", "1");
		int ciclo1 = 0;
		String url= "" ;

		HttpSession session = request.getSession(true) ;
		String usuario = session.getAttribute("usuario").toString();

		if(usuario == null){
			url= "login.html" ;
			request.getRequestDispatcher(url).forward(request, response);
		}else{
			System.out.println("usuario ".concat(usuario));
		}

		url = "semana.jsp";		
		ArrayList<Equipo> equipos = integracion.buscarUsuario(usuario).getEquipos();
		request.setAttribute("equipos", equipos);
		request.setAttribute("equipo", equipo);

		ArrayList<Proyecto> proyectos = null;
		ArrayList<Ciclo> ciclos= null;
		ArrayList<Semana> semanas = null ;

		if (equipos.isEmpty() == false) {
			if(bandera.length() == 0){
				proyectos = integracion.buscarUsuario(usuario).buscarEquipo(equipos.get(0).getNombre()).getProyectos();
			}else{
				proyectos = integracion.buscarUsuario(usuario).buscarEquipo(equipo).getProyectos();
			}
		}
			request.setAttribute("proyectos", proyectos);
			request.setAttribute("proyecto", proyecto);

		if (proyectos.isEmpty() == false && !proyecto.equalsIgnoreCase("null")) {
			if (bandera.length() == 0 ) {
				ciclos = integracion.buscarUsuario(usuario).buscarEquipo(equipos.get(0).getNombre()).buscarProyecto(proyectos.get(0).getNombreProyecto()).getCiclos() ;
			}else if(proyecto != null){
				ciclos = integracion.buscarUsuario(usuario).buscarEquipo(equipo).buscarProyecto(proyecto).getCiclos() ;
			}
		}
			request.setAttribute("ciclos", ciclos) ;
			request.setAttribute("ciclo", ciclo);

		if (ciclo != null ) {
			if (listar != null) {
				ciclo1 = Integer.parseInt(ciclo) ;
				try {
					semanas = integracion.buscarUsuario(usuario).buscarEquipo(equipo).buscarProyecto(proyecto).buscarCiclo(ciclo1).getSemanas() ;
				} catch (Exception e) {
					e.printStackTrace();
				}
					request.setAttribute("semanas", semanas);
			}else if((boton.length() > 0) && opcion.equals("1")){
				try {
					ciclo1 = Integer.parseInt(ciclo) ;
					int semana = Integer.parseInt(cadenaSemana);
					integracion.agregarSemana(semana, fechaInicio, fechaFin, usuario, equipo, proyecto, ciclo1) ;
					request.setAttribute("mensaje", "La Semana con el número: " + semana + " Se adicionó exitosamente");
				} catch (Exception e) {
					request.setAttribute("spinner", cadenaSemana);
					request.setAttribute("datepickerInicio", fechaInicio);
					request.setAttribute("datepickerFin", fechaFin);
					request.setAttribute("mensaje", e.getMessage());
				}
			}else if (boton != null && opcion.equals("2")){
				int semana = Integer.parseInt(cadenaSemana);
				ciclo1 = Integer.parseInt(ciclo) ;
				try {
					integracion.modificarSemana(semana, fechaInicio, fechaFin, usuario, equipo, proyecto, ciclo1);
					request.setAttribute("mensaje", "la Semana con el número: " + semana + " fue modificada exitosamnete");
					request.setAttribute("opcion", "1");
				} catch (Exception e) {
					request.setAttribute("spinner", semana);
					request.setAttribute("datepickerInicio", fechaInicio);
					request.setAttribute("datepickerFin", fechaFin);
					request.setAttribute("opcion", "2");
					request.setAttribute("mensaje", e.getMessage());
				}
			}else if (!modificar.equalsIgnoreCase("null") ){
				try {
					cadenaSemana= request.getParameter("modificar");
					int semana = Integer.parseInt(cadenaSemana);
					ciclo1 = Integer.parseInt(ciclo) ;

					Semana miSemana = integracion.buscarUsuario(usuario).buscarEquipo(equipo).buscarProyecto(proyecto).buscarCiclo(ciclo1).buscarSemana(semana);
					String cadena=Integer.toString(miSemana.getSemanaNumero());
					request.setAttribute("spinner", cadena);
					request.setAttribute("datepickerInicio", miSemana.getFechaInicio());
					request.setAttribute("datepickerFin", miSemana.getFechaFin());
					request.setAttribute("opcion", "2");
					request.setAttribute("mensaje", "Ahora puede modificar los datos de Semana");
				} catch (Exception e) {
					request.setAttribute("mensaje", e.getMessage());
				}
			}

			if (!eliminar.equalsIgnoreCase("null")) {
				try {
					String cadena = request.getParameter("eliminar");
					int numero = Integer.parseInt(cadena);
					ciclo1 = Integer.parseInt(ciclo) ;
					integracion.eliminarSemana(numero , usuario, equipo, proyecto, ciclo1);						
					request.setAttribute("mensaje", "La Semana con el número: " + numero + " Se elimino exitosamente");
				} catch (Exception e) {
					request.setAttribute("mensaje", e.getMessage()) ;
				}				
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}