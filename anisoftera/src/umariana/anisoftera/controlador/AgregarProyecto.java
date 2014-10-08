package umariana.anisoftera.controlador;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import umariana.anisoftera.mundo.Equipo;
import umariana.anisoftera.mundo.Integracion;
import umariana.anisoftera.mundo.Proyecto;

public class AgregarProyecto extends HttpServlet {

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
	public AgregarProyecto() throws ClassNotFoundException, SQLException{
		integracion = new Integracion() ;
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
		
		String modificar = request.getParameter("modificar")==null?"null":request.getParameter("modificar");
		String eliminar = request.getParameter("eliminar") ==null?"null":request.getParameter("eliminar");
		String boton = request.getParameter("boton")==null?"null":request.getParameter("boton");
		String opcion = request.getParameter("opcion");		
		String listar = request.getParameter("listarProyectos");
		String nombre = request.getParameter("nombre");
		String semestre = request.getParameter("spinner");
		String anio1 = request.getParameter("spinnerAnio");
		String fechaInicio =request.getParameter("datepickerInicio");
		String fechaFin =request.getParameter("datepickerFin");
		String equipo = request.getParameter("equipo") ;
		String bandera = request.getParameter("bandera") ;
		request.setAttribute("bandera", "1");
		String url= "" ;	
		
		ArrayList<Proyecto> proyectos = null ;
		ArrayList<Equipo> equipos = null ;

		HttpSession session = request.getSession(true) ;
		String usuario = session.getAttribute("usuario").toString();

		if(usuario == null){
			url= "login.html" ;
			request.getRequestDispatcher(url).forward(request, response);
		}else{
			System.out.println("usuario ".concat(usuario));
		}		

		url = "proyectos.jsp";
		equipos = integracion.buscarUsuario(usuario).getEquipos();
		request.setAttribute("equipos", equipos);
		request.setAttribute("equipo", equipo);
	
		if (integracion.buscarUsuario(usuario).getEquipos().isEmpty() == false) {
			proyectos = integracion.buscarUsuario(usuario).buscarEquipo(equipos.get(0).getNombre()).getProyectos();
			
			if(bandera.length() ==0){
				proyectos = integracion.buscarUsuario(usuario).buscarEquipo(equipos.get(0).getNombre()).getProyectos();
			}else{
				proyectos = integracion.buscarUsuario(usuario).buscarEquipo(equipo).getProyectos();
			}
				request.setAttribute("proyectos", proyectos);	

			if (listar != null) {
				proyectos = integracion.buscarUsuario(usuario).buscarEquipo(equipo).getProyectos();
				request.setAttribute("proyectos", proyectos);
			} else if((boton.length() > 0) && opcion.equals("1")){
				int anio = Integer.parseInt(anio1);	
				try {				
					integracion.agregarProyecto(nombre, fechaInicio, fechaFin, semestre, anio, usuario,equipo) ;
					request.setAttribute("mensaje", "El Proyecto con el nombre: " + nombre + " se adicionó exitosamente");
					request.setAttribute("opcion", "1");
					bandera = null ;
				} catch (Exception e) {
					request.setAttribute("nombreProyecto", nombre);
					request.setAttribute("spinner", semestre);
					request.setAttribute("spinnerAnio", anio);
					request.setAttribute("datepickerInicio", fechaInicio);
					request.setAttribute("datepickerFin", fechaFin);
					request.setAttribute("opcion", "1");					
					request.setAttribute("mensaje", e.getMessage());
				}
			} else if (boton != null && opcion.equals("2")){
				int anio = Integer.parseInt(anio1);
				try {
					integracion.modificarProyecto(nombre, fechaInicio, fechaFin, semestre, anio,usuario,equipo);
					request.setAttribute("mensaje", "El Proyecto con el nombre " + nombre + " fue modificado exitosamente");
					request.setAttribute("opcion", "1");
				} catch (Exception e) {
					request.setAttribute("nombreProyecto", nombre);
					request.setAttribute("spinner", semestre);
					request.setAttribute("spinnerAnio", anio);
					request.setAttribute("datepickerInicio", fechaInicio);
					request.setAttribute("datepickerFin", fechaFin);
					request.setAttribute("opcion", "2");
					request.setAttribute("mensaje", e.getMessage());
				}
			}else if (!modificar.equalsIgnoreCase("null") ){
				try {
					nombre= request.getParameter("modificar");
					Proyecto miPoryecto = integracion.buscarUsuario(usuario).buscarEquipo(equipo).buscarProyecto(nombre);
					request.setAttribute("nombre", miPoryecto.getNombreProyecto());
					request.setAttribute("spinner", miPoryecto.getSemestre());
					request.setAttribute("spinnerAnio", miPoryecto.getAnio());
					request.setAttribute("datepickerInicio", miPoryecto.getFechaInicio());
					request.setAttribute("datepickerFin", miPoryecto.getFechaFin());
					request.setAttribute("opcion", "2");
					request.setAttribute("mensaje", "El Proyecto con el nombre: " + nombre + "ya se le puede modificar los datos");
				} catch (Exception e) {
					request.setAttribute("mensaje", e.getMessage());
				}
			}

			if (!eliminar.equalsIgnoreCase("null")) {
				try {
					nombre = request.getParameter("eliminar");				
					integracion.eliminarProyecto(nombre,usuario,equipo);
					request.setAttribute("mensaje", "El Proyecto con el nombre: " + nombre + " Fue eliminado exitosamente");
				} catch (Exception e) {
					request.setAttribute("mensaje", e.getMessage()) ;
				}
			}
		}
		request.getRequestDispatcher(url).forward(request, response) ;
	}
}