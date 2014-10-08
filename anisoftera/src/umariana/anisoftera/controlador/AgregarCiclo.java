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
import umariana.anisoftera.mundo.Proyecto;

public class AgregarCiclo extends HttpServlet{

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
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public AgregarCiclo() throws ClassNotFoundException, SQLException{
		integracion = new Integracion();
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
		String boton = request.getParameter("guardarCiclo")==null?"null":request.getParameter("guardarCiclo");
		String listar = request.getParameter("listarCiclos");
		String equipo = request.getParameter("equipo");
		String cadenaCiclo = request.getParameter("ciclo") ;
		String eliminar = request.getParameter("eliminar")==null?"null":request.getParameter("eliminar") ;
		String bandera = request.getParameter("bandera1");
		String proyecto = request.getParameter("proyecto") ;
		request.setAttribute("bandera1", "1");
		int ciclo = 0 ;	
		String url= "" ;
		
		HttpSession session = request.getSession(true) ;
		String nombreUsuario=session.getAttribute("usuario").toString();
				
		if(nombreUsuario==null){
			url= "login.html" ;
			request.getRequestDispatcher(url).forward(request, response);
		}else{
			System.out.println("usuario ".concat(nombreUsuario));
		}
		
		url = "ciclos.jsp" ;		
		ArrayList<Equipo> equipos = integracion.buscarUsuario(nombreUsuario).getEquipos();
		request.setAttribute("equipos", equipos);
		request.setAttribute("equipo", equipo);
			
		ArrayList<Proyecto> proyectos = null;
		ArrayList<Ciclo> ciclos = null;
		
		if (equipos.isEmpty() == false) {
			if(bandera.length() == 0){
				proyectos = integracion.buscarUsuario(nombreUsuario).buscarEquipo(equipos.get(0).getNombre()).getProyectos();
			}else{
				proyectos = integracion.buscarUsuario(nombreUsuario).buscarEquipo(equipo).getProyectos();
			}
		}
			request.setAttribute("proyectos", proyectos);
			request.setAttribute("proyecto", proyecto);

		if (proyecto != null) {
			if (listar != null) {			
				ciclos = integracion.buscarUsuario(nombreUsuario).buscarEquipo(equipo).buscarProyecto(proyecto).getCiclos() ;
				request.setAttribute("ciclos", ciclos);
			}else if(boton.length() > 0){
				try {
					ciclo = Integer.parseInt(cadenaCiclo) ;
					integracion.agregarCiclo(ciclo, nombreUsuario, equipo, proyecto);
					request.setAttribute("mensaje", "El ciclo numero: " + ciclo + " Se adicionó exitosamente");
				} catch (Exception e) {
					request.setAttribute("ciclo", ciclo);
					request.setAttribute("mensaje", e.getMessage());
				}
			}else if (!eliminar.equalsIgnoreCase("null")){
				try {
					cadenaCiclo = request.getParameter("eliminar");
					int numeroCiclo = Integer.parseInt(cadenaCiclo) ;
					integracion.eliminarCiclo(numeroCiclo, nombreUsuario, equipo, proyecto);
					request.setAttribute("mensaje", "El Ciclo con el número: " + numeroCiclo + " Se elimino exitosamente");
				} catch (Exception e) {
					request.setAttribute("mensaje", e.getMessage()) ;
				}
			}			
		}
		request.getRequestDispatcher(url).forward(request, response);		
	}
}