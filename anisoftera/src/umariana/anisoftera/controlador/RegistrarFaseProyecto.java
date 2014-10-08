package umariana.anisoftera.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import umariana.anisoftera.mundo.Ciclo;
import umariana.anisoftera.mundo.Equipo;
import umariana.anisoftera.mundo.Fase;
import umariana.anisoftera.mundo.Integracion;
import umariana.anisoftera.mundo.Proyecto;
import umariana.anisoftera.mundo.Semana;

public class RegistrarFaseProyecto extends HttpServlet {

	/**
	 * @uml.property  name="integracion"
	 * @uml.associationEnd  inverse="registrarFaseProyecto:umariana.anisoftera.mundo.Integracion"
	 */
	private Integracion integracion;

	/**
	 * Getter of the property <tt>integracion</tt>
	 * @return  Returns the integracion.
	 * @uml.property  name="integracion" .
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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public RegistrarFaseProyecto() throws ClassNotFoundException, SQLException{
		integracion = new Integracion() ;
	}

	/**
	 * Metodo del controlador Registrar la Fase del Proyecto
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try {
			this.integracion = new Integracion();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String fases[] = request.getParameterValues("destino[]") ;	
		String equipo = request.getParameter("equipo") ;
		String proyecto = request.getParameter("proyecto") ==null?"null":request.getParameter("proyecto");
		String ciclo = request.getParameter("ciclo") ;
		String guardar = request.getParameter("Guardar") ;
		String cancelar = request.getParameter("Cancelar") ;
		String bandera = request.getParameter("bandera") ;
		String url = "proyectoFase.jsp" ;
		int cilos = 0 ;
		request.setAttribute("bandera", "1");				 		
		ArrayList<Fase> faces = integracion.getFases() ;
		
		HttpSession session = request.getSession(true) ;
		String usuario = session.getAttribute("usuario").toString();
		ArrayList<Object> a = null;
		
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
			request.setAttribute("fases", faces) ;
		
			if (guardar != null) {
			try {
				cilos = Integer.parseInt(ciclo);
				integracion.agregarFasesProyecto(usuario , equipo, proyecto, cilos, fases) ;
				request.setAttribute("mensaje", "La Fase se adicionó exitosamente");
			} catch (Exception e) {
				request.setAttribute("mensaje", e.getMessage());
			}
		}
		request.getRequestDispatcher(url).forward(request, response);		
	}
}