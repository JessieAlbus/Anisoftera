package umariana.anisoftera.mundo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import umariana.anisoftera.datos.ActividadDAO;

public class Fase {

	/**
	 * Nombre de la fase
	 * @uml.property  name="nombre"
	 */
	private String nombre;

	/**
	 * Getter of the property <tt>nombre</tt>
	 * @return  Returns the nombre.
	 * @uml.property  name="nombre"
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter of the property <tt>nombre</tt>
	 * @param nombre  The nombre to set.
	 * @uml.property  name="nombre"
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @uml.property   name="actividadDAO"
	 * @uml.associationEnd   inverse="fase:umariana.anisoftera.datos.ActividadDAO"
	 */
	private ActividadDAO actividadDAO;

	/**
	 * Getter of the property <tt>actividadDAO</tt>
	 * @return  Returns the actividadDAO.
	 * @uml.property  name="actividadDAO"
	 */
	public ActividadDAO getActividadDAO() {
		return actividadDAO;
	}

	/**
	 * Setter of the property <tt>actividadDAO</tt>
	 * @param actividadDAO  The actividadDAO to set.
	 * @uml.property  name="actividadDAO"
	 */
	public void setActividadDAO(ActividadDAO actividadDAO) {
		this.actividadDAO = actividadDAO;
	}
		
	/**
	 * @uml.property  name="actividades"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="fase:umariana.anisoftera.mundo.Actividad"
	 */
	private ArrayList actividades;

	/**
	 * Getter of the property <tt>actividades</tt>
	 * @return  Returns the actividades.
	 * @uml.property  name="actividades"
	 */
	public ArrayList getActividades() {
		return actividades;
	}

	/**
	 * Setter of the property <tt>actividades</tt>
	 * @param actividades  The actividades to set.
	 * @uml.property  name="actividades"
	 */
	public void setActividades(ArrayList actividades) {
		this.actividades = actividades;
	}

	/**
	 * Constructor de la clase Fase
	 * @param nombre. El nombre de la fase. nombre != null && nombre != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public Fase(String nombre) throws ClassNotFoundException, SQLException{
		this.actividades = new ArrayList<Actividad>();
		this.nombre = nombre ;
		this.actividadDAO = new ActividadDAO() ;
		cargarActividades() ;
	}

	/**
	 * Carga las actividades en la clase Fase
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public void cargarActividades() throws ClassNotFoundException, SQLException {
		ArrayList<Actividad> lasActiviades = actividadDAO.listar(getNombre());
		if(lasActiviades != null){
			setActividades(lasActiviades) ;
		}
	}
	
	/**
	 * Busca y retorna la actividad.
	 * <b>pre:</b>La lista de actividad debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha retornado la actividad.<br>
	 * @param nombre. El nombre de la actividad. nombre != null && nombre != ""
	 * @return miActividad. Retorna la actividad , si no lo encuentra devuelve null.
	 */
	public Actividad buscarActividad(String nombre) {
		Actividad miActividad = null;
		boolean encontrado = false;
		for(int indice=0; indice < actividades.size() && !encontrado; indice++){
			miActividad = (Actividad) actividades.get(indice);
			if(miActividad.getNombre().equals(nombre)){	
				encontrado = true;
				return miActividad ;
			}
		}
		return null;
	}

	/**
	 * Agrega la actividad a la fase del proyecto.
	 * <b>pre:</b>La lista de actividades debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha guardado la actividad en la lista actividades.<br>
	 * @param nombre. El nombre de la actividad. nombre != null && nombre != ""
	 * @param horas. Las horas de la actividad. horas != null && horas != ""
	 * @param minutos. Los minutos de la actividad. minutos != null && minutos != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 * @throws IOException. Lanza una excepción si la actividad que desea agregar ya existe.
	 */
	public void agregarActividad(String nombre, int horas, int minutos) throws IOException, ClassNotFoundException, SQLException {
		Actividad miActividad = buscarActividad(nombre) ;	
		if (miActividad == null) {
			miActividad = new Actividad(nombre, horas, minutos) ;
			actividades.add(miActividad) ;
			guardarActividades(miActividad, getNombre()) ;
		}else{
			throw new IOException("La Actividad con el nombre: " + nombre + " ya se encuentra registrada") ;
		}
	}
	
	/**
	 * Agrega la actividad a la lista de actividades.
	 * * <b>pre:</b>La lista de actividades debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha guardado la actividad en la lista actividades.<br>
	 * @param actividad. La actividad de la fase del proyecto. actividad != null && actividad != ""
	 * @param fase. La fase del proyecto. fase != null && fase != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public void guardarActividades(Actividad actividad, String fase) throws ClassNotFoundException, SQLException {
		actividadDAO.agregar(actividad,fase);
	}

	/**
	 * Modifica una actividad de la fase del proyecto de la lista actividades.
	 * @param nombre. El nombre de la actividad. nombre != null && nombre != ""
	 * @param horas. Las horas de la actividad del proyecto. horas != null && horas != ""
	 * @param minutos. Los minutos de la actividad de la fase del proyecto. minutos != null && minutos != ""
	 * @throws Lanza una excepción si la actividad que desea modificar no existe.
	 */
	public void modificarActividad(String nombre, int horas , int minutos) throws Exception {
		Actividad miActividad = buscarActividad(nombre);		
		if(miActividad != null){
			miActividad.setNombre(nombre);	
			miActividad.setHorasPlaneadas(horas) ;
			miActividad.setMinutosPlaneados(minutos) ;
			actividadDAO.modificar(miActividad);
		}else{
			throw new IOException("La Actividad con el nombre: " + nombre + " no se encuentra registrada") ;
		}			
	}
}