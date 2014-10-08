package umariana.anisoftera.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import umariana.anisoftera.mundo.Fase;
import umariana.anisoftera.mundo.Integracion;

public class AgregarFase extends HttpServlet{

	/**
	 * @uml.property  name="integracion"
	 * @uml.associationEnd  inverse="agregarFase:umariana.anisoftera.mundo.Integracion"
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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public AgregarFase() throws ClassNotFoundException, SQLException{
		integracion = new Integracion() ;
	}

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

		String eliminar = request.getParameter("eliminar")==null?"null":request.getParameter("eliminar");
		String guardar = request.getParameter("guardarFase") ==null?"null":request.getParameter("guardarFase");
		String nombre = request.getParameter("fase");
		String bandera = request.getParameter("bandera");
		request.setAttribute("bandera", "1");
		String url = "";

		ArrayList<Fase> fases = integracion.getFases();
		request.setAttribute("fases", fases);

		url = "fase.jsp";

		if(guardar.length() > 0){
			try {
				integracion.agregarFase(nombre);
				request.setAttribute("mensaje", "La Fase con el nombre: " + nombre + " Se adicionó exitosamente");
			} catch (Exception e) {
				request.setAttribute("nombre", nombre);
				request.setAttribute("mensaje", e.getMessage());
			}
		}else if(!eliminar.equalsIgnoreCase("null")){
			try {
				nombre = request.getParameter("eliminar");
				integracion.eliminarFase(nombre);
				request.setAttribute("mensaje", "La Fase con el nombre: " + nombre + "Se elimino exitosamente" );
			} catch (Exception e) {
				request.setAttribute("mensaje", e.getMessage());
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}