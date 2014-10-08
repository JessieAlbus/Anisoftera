package umariana.anisoftera.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import umariana.anisoftera.mundo.Usuario;
import umariana.anisoftera.mundo.Integracion;

public class LoginUsuario  extends HttpServlet{

	/**
	 * @uml.property  name="integracion"
	 * @uml.associationEnd  inverse="loginUsuario:umariana.anisoftera.mundo.Integracion"
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
	 * constructor de la clase LoginUsuario
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public LoginUsuario() throws ClassNotFoundException, SQLException{
		integracion = new Integracion();
	}

		/** 
		 * Controlador para agregar usuarios
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
				try {
					integracion = new Integracion();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
				String enviar = request.getParameter("Enviar");
				String nombre = request.getParameter("usuario");
				String clave = request.getParameter("password");
				
				ArrayList<Usuario> usuarios=integracion.getUsuarios();	
				
				HttpSession session = request.getSession(true) ;
				session.setAttribute("usuario", nombre);
				
				String url = "";
				if(enviar != null){
					Usuario myUsuario = integracion.buscarUsuario(nombre);
					try {				
						if(myUsuario != null){				
							request.setAttribute("mensaje", "Bienvenido " + nombre);
							url = "index.jsp";
							request.getRequestDispatcher(url).forward(request, response);
						}else{
							url= "login.html" ;
							request.getRequestDispatcher(url).forward(request, response);	
						}
					} catch (Exception e) {
						request.setAttribute("mensaje", e.getMessage());
					}
				}
			}
}