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

public class AgregarEquipo extends HttpServlet{
	
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
	public AgregarEquipo() throws ClassNotFoundException, SQLException{
		integracion = new Integracion();
	}
	
	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {		

		try {
			integracion= new Integracion() ;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String modificar = request.getParameter("modificar")==null?"null":request.getParameter("modificar");
		String eliminar = request.getParameter("eliminar") ==null?"null":request.getParameter("eliminar");
		String opcion = request.getParameter("opcion");
		String boton = request.getParameter("boton")==null?"null":request.getParameter("boton");
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String cancelar = request.getParameter("Cancelar") ;
		String bandera = request.getParameter("bandera") ;
		request.setAttribute("bandera", "1") ;
		String url = "";
		
		HttpSession session = request.getSession(true) ;
		String usuario = session.getAttribute("usuario").toString();
		
		if(usuario == null){
			url= "login.html" ;
			request.getRequestDispatcher(url).forward(request, response);
		}else{
			System.out.println("usuario ".concat(usuario));
		}
		
		ArrayList<Equipo> equipos = integracion.buscarUsuario(usuario).getEquipos() ;
		request.setAttribute("equipos", equipos);
				
		url = "equipos.jsp";
		
		if (cancelar != null) {
			equipos = integracion.buscarUsuario(usuario).getEquipos() ;
			request.setAttribute("equipos", equipos);
		}else if((boton.length() > 0) && opcion.equals("1")){
			try {
				integracion.agregarEquipo(nombre,email,usuario);
				request.setAttribute("mensaje", "El Equipo con el nombre: " + nombre + " se adicionó exitosamente");
				request.setAttribute("opcion", "1");
				
			} catch (Exception e) {
				request.setAttribute("nombreEquipo", nombre);
				request.setAttribute("email", email);
				request.setAttribute("opcion", "1");
				request.setAttribute("mensaje", e.getMessage());
			}
		}else if (boton != null && opcion.equals("2")){
			try {
				integracion.modificarEquipo(nombre, email, usuario);
				request.setAttribute("mensaje", "El equipo " + nombre + " fue modificado exitosamente");
				request.setAttribute("opcion", "1");
			} catch (Exception e) {
				request.setAttribute("nombre", nombre);
				request.setAttribute("email", email);
				request.setAttribute("opcion", "2");
				request.setAttribute("mensaje", e.getMessage());
			}
		}else if (!modificar.equalsIgnoreCase("null")){
			try {
				nombre= request.getParameter("modificar");
				
				Equipo miEquipo = integracion.buscarUsuario(usuario).buscarEquipo(nombre);
				request.setAttribute("nombre", miEquipo.getNombre());
				request.setAttribute("email", miEquipo.getEmail());
				request.setAttribute("opcion", "2");
				request.setAttribute("mensaje", "Ahora puede modificar los datos del equipo");
			} catch (Exception e) {
				request.setAttribute("mensaje", e.getMessage());
			}
		}
		
		if (!eliminar.equalsIgnoreCase("null")){
			try{
			    nombre= request.getParameter("eliminar");
				integracion.eliminarEquipo(nombre, usuario);
				request.setAttribute("opcion", "1");
				request.setAttribute("mensaje", "El equipo fue eliminado exitosamante");
			}catch(Exception e){
				request.setAttribute("mensaje", e.getMessage());
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}