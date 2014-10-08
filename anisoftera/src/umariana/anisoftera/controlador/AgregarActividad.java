package umariana.anisoftera.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.spi.MixerProvider;

import umariana.anisoftera.mundo.Actividad;
import umariana.anisoftera.mundo.Fase;
import umariana.anisoftera.mundo.Integracion;
import umariana.anisoftera.mundo.Proyecto;
import umariana.anisoftera.mundo.Tiempo;
import umariana.anisoftera.mundo.TiempoActividad;

public class AgregarActividad extends HttpServlet {

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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public AgregarActividad() throws ClassNotFoundException, SQLException{
		integracion = new Integracion() ;
	}

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try {
			integracion= new Integracion() ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String booton = request.getParameter("Agregar");
		String opcion = request.getParameter("opcion");	
		String modificar = request.getParameter("Modificar") ==null?"null":request.getParameter("modificar");
		String eliminar = request.getParameter("eliminar")  ==null?"null":request.getParameter("eliminar") ;
		String nombre = request.getParameter("nombre") ;
		String horas = request.getParameter("horas") ;
		String minutos = request.getParameter("minutos") ;
		String bandera = request.getParameter("bandera") ;
		String fase = request.getParameter("fase") ;
		request.setAttribute("bandera", "1");
		ArrayList<Actividad> actividades = null ;
		int time = 0 ;
		int minute = 0 ;
		String url = "actividad.jsp" ;
		
		System.out.println(booton + " ------------------------------ ");
		
		ArrayList<Fase> fases = integracion.getFases() ;
		request.setAttribute("fases", fases) ;
		request.setAttribute("fase",fase) ;
		  
		
		
		if (fases.isEmpty() == false) {
			if(bandera.length() ==0)
			{
				actividades = integracion.buscarFase(fases.get(0).getNombre()).getActividades();
			}
			else
			{
				actividades = integracion.buscarFase(fase).getActividades() ;
			}
			request.setAttribute("actividades", actividades);

			if (!eliminar.equalsIgnoreCase("null")) {
				
			}
			
			else if((booton.length() > 0) && opcion.equals("1"))
			{	
				try {
					time = Integer.parseInt(horas) ;
					minute = Integer.parseInt(minutos);
					
					integracion.agregarActividad(nombre,time , minute, fase);
					request.setAttribute("mensaje", "La actividad " + nombre + " se adicionó satisfactoriamente");
					request.setAttribute("opcion", "1");
					bandera = null ;
				} catch (Exception e) {
					request.setAttribute("nombre", nombre);
					request.setAttribute("horas", time);
					request.setAttribute("minutos", minute);
					request.setAttribute("opcion", "1");					
					request.setAttribute("mensaje", e.getMessage());
				}

			} else if (booton != null && opcion.equals("2"))
			{
				try {
					System.out.println("  POST MODIFICAR ACTIVIADA ...  ");
					
					time = Integer.parseInt(horas) ;
					minute = Integer.parseInt(minutos);
					integracion.modificarActividad(nombre, time, minute, fase); 
					request.setAttribute("mensaje", "La actividad " + nombre + " fue modificado satisfactoriamente");
					request.setAttribute("opcion", "1");

				} catch (Exception e) {
					request.setAttribute("nombre", nombre);
					request.setAttribute("horas", time);
					request.setAttribute("minutos", minute) ;
					request.setAttribute("opcion", "2");
					request.setAttribute("mensaje", e.getMessage());
				}

			}else if (!modificar.equalsIgnoreCase("null") ){

			}

			else if (!modificar.equalsIgnoreCase("null") )
			{
			}else if (!modificar.equalsIgnoreCase(null) ){


			}else if (!modificar.equalsIgnoreCase(null) ){

				try {
					
					System.out.println("  POST BUSCAR ACTIVIADA ...  ");
					
					nombre= request.getParameter("modificar");
					Actividad miActividad = integracion.buscarFase(fase).buscarActividad(nombre) ;
					
					request.setAttribute("nombre", miActividad.getNombre());
					request.setAttribute("opcion", "2");
					request.setAttribute("mensaje", "Ahora puede modificar los datos de la actividad");
				} catch (Exception e) {
					request.setAttribute("mensaje", e.getMessage());
				}
			}
	
		}
		
		
		
		request.getRequestDispatcher(url).forward(request, response) ;
	}
}