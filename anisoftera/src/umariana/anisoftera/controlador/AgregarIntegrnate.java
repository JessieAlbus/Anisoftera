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
import umariana.anisoftera.mundo.Integrante;

public class AgregarIntegrnate extends HttpServlet {

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
	public AgregarIntegrnate() throws ClassNotFoundException, SQLException{
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
		String eliminar = request.getParameter("eliminar")==null?"null":request.getParameter("eliminar");
		String boton = request.getParameter("boton")==null?"null":request.getParameter("boton");
		String opcion = request.getParameter("opcion");
		String listar = request.getParameter("listarIntegrantes");
		String nombre = request.getParameter("nombre");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String roll = request.getParameter("combobox");		
		String equipo = request.getParameter("equipo");
		String bandera = request.getParameter("bandera") ;
		request.setAttribute("bandera", "1");
		String url = "";
		
		HttpSession session = request.getSession(true) ;
		String usuario = session.getAttribute("usuario").toString();
				
		if(usuario == null){
			url= "login.html" ;
			request.getRequestDispatcher(url).forward(request, response);
		}else{
			System.out.println("usuario ".concat(usuario));
		}

		url = "integrantes.jsp";
		
		int contadorEquipos = integracion.buscarUsuario(usuario).getEquipos().size() ;
		if (integracion.buscarUsuario(usuario).getEquipos().isEmpty() == false) {
			ArrayList<Equipo> equipos = integracion.buscarUsuario(usuario).getEquipos();
			request.setAttribute("equipos", equipos);
			request.setAttribute("equipo", equipo);
			
			ArrayList<Integrante> integrantes = null;

			if(bandera.length() == 0){
				integrantes= integracion.buscarUsuario(usuario).buscarEquipo(equipos.get(0).getNombre()).getIntegrantes() ;
				
			}else{
				integrantes = integracion.buscarUsuario(usuario).buscarEquipo(equipo).getIntegrantes();
			}
			request.setAttribute("integrantes",integrantes);
			
			if (listar != null) {
				integrantes = integracion.buscarUsuario(usuario).buscarEquipo(equipo).getIntegrantes() ;
				request.setAttribute("integrantes", integrantes);
			}else if((boton.length() > 0) && opcion.equals("1")){
				try {
					integracion.agregarIntegrante(id,nombre, roll, email, usuario,equipo);				
					request.setAttribute("mensaje", "El Integrante con el nombre: " + nombre + " Se adicionó exitosamente");
				} catch (Exception e) {
					request.setAttribute("nombre", nombre);
					request.setAttribute("email", email);
					request.setAttribute("combobox", roll);
					request.setAttribute("id", id);
					request.setAttribute("mensaje", e.getMessage());
				}
			} else if (boton != null && opcion.equals("2")){
				try {
					integracion.modificarIntegrante(id,nombre, roll, email,usuario, equipo);
					request.setAttribute("mensaje", "El Integrante con el nombre: " + nombre + " Fue modificado exitosamente");
					request.setAttribute("opcion", "1");
				} catch (Exception e) {
					request.setAttribute("nombre", nombre);
					request.setAttribute("email", email);
					request.setAttribute("combobox", roll);
					request.setAttribute("id", id);
					request.setAttribute("opcion", "2");
					request.setAttribute("mensaje", e.getMessage());
				}
			}else if (!modificar.equalsIgnoreCase(null) ){
				try {
					id= request.getParameter("modificar");
					Integrante miIntegrante = integracion.buscarUsuario(usuario).buscarEquipo(equipo).buscarIntegrante(id);
					request.setAttribute("nombre", miIntegrante.getNombreIntegrante());
					request.setAttribute("email", miIntegrante.getEmailIntegrante());
					request.setAttribute("combobox", miIntegrante.getRolIntegrante());
					request.setAttribute("id", miIntegrante.getIdIntegrante());
					request.setAttribute("opcion", "2");
					request.setAttribute("mensaje", "Ahora puede modificar los datos del Integrante");
				} catch (Exception e) {
					request.setAttribute("mensaje", e.getMessage());
				}
			}
			if (!eliminar.equalsIgnoreCase("null")) {
				try {
					id = request.getParameter("eliminar");				
					integracion.eliminarIntegrante(id, usuario, equipo);				
					request.setAttribute("mensaje", "El Integrante con el nombre: " + nombre + " Se elimino exitosamente");
				} catch (Exception e) {
					request.setAttribute("mensaje", e.getMessage()) ;
				}
			}
			request.getRequestDispatcher(url).forward(request, response);
		}else {
			request.getRequestDispatcher(url).forward(request, response) ;
		}
	}
}