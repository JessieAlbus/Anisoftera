package umariana.anisoftera.controlador;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import umariana.anisoftera.mundo.Integracion;

public class RegistrarActividadProyecto extends HttpServlet {

	/**
	 * @uml.property  name="integracion"
	 * @uml.associationEnd  inverse="registrarActividadProyecto:umariana.anisoftera.mundo.Integracion"
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
		 */
	public RegistrarActividadProyecto(){
	}
			
	/**
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response){
	}
}