package umariana.anisoftera.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import umariana.anisoftera.mundo.Integracion;
import umariana.anisoftera.mundo.Usuario;

public class AgregarUsuario extends HttpServlet {

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
	 * Getter of the property <tt>integracion</tt>
	 * @return  Returns the integracion.
	 * @uml.property  name="integracion"
	 */
	public void setIntegracion(Integracion integracion) {
		this.integracion = integracion;
	}

	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException Errores en el acceso a la base de datos
	 */
	public AgregarUsuario() throws ClassNotFoundException, SQLException{
		integracion = new Integracion();	
	}

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		String boton = request.getParameter("guardar");
		String nombre = request.getParameter("nombre");
		String clave = request.getParameter("clave");
		ArrayList<Usuario> usuarios=integracion.getUsuarios();
		request.setAttribute("usuarios", usuarios);
		String url = "";
		
		if(boton != null){
			try {
				integracion.agregarUsusario(nombre, clave);
				request.setAttribute("mensaje", "El Usuario con el nombre: " + nombre + " Se adicionó exitosamente");
			} catch (Exception e) {
				request.setAttribute("nombre", nombre);
				request.setAttribute("clave", clave);
				request.setAttribute("mensaje", e.getMessage());
			}
			url = "login.html";
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}