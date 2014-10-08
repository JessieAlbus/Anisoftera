package umariana.anisoftera.mundo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;

import com.sun.corba.se.impl.protocol.InfoOnlyServantCacheLocalCRDImpl;

import umariana.anisoftera.datos.EquipoDAO;
import umariana.anisoftera.datos.ProyectoDAO;
import umariana.anisoftera.datos.IntegranteDAO;
import umariana.anisoftera.datos.UsuarioDAO;

public class Equipo {

	/**
	 * Constante de la Ruta para escirbir los datos en la DOKUWIKI
	 */
	public final static String RUTA="C:/xampp/htdocs/dokuwiki/data/pages/";

	/**
	 * @uml.property  name="proyectos"
	 * @uml.associationEnd   multiplicity="(0 -1)" ordering="true" inverse="proyecto:umariana.anisoftera.mundo.Proyecto"
	 */
	private ArrayList proyectos;

	/**
	 * @uml.property  name="integrantes"
	 * @uml.associationEnd   multiplicity="(0 -1)" ordering="true" inverse="integrante:umariana.anisoftera.mundo.Integrante"
	 */
	private ArrayList integrantes;

	/**
	 * @uml.property  name="proyectoDAO"
	 * @uml.associationEnd   multiplicity="(0 -1)" ordering="true" inverse="proyectoDAO:umariana.anisoftera.mundo.ProyectoDAO"
	 */
	private ProyectoDAO proyectoDAO;

	/**
	 * @uml.property  name="integranteDAO"
	 * @uml.associationEnd   multiplicity="(0 -1)" ordering="true" inverse="integranteDAO:umariana.anisoftera.mundo.IntegranteDAO"
	 */
	private IntegranteDAO integranteDAO;

	/**
	 * Identificador del equipo
	 * @uml.property  name="id"
	 */
	private String id;

	/**
	 * Nombre del equipo
	 * @uml.property  name="nombre"
	 */
	private String nombre;

	/**
	 * Email del equipo
	 * @uml.property  name="email"
	 */
	private String email;
	
	/**
	 * Getter of the property <tt>proyectos</tt>
	 * @return  Returns the proyectos.
	 * @uml.property  name="proyectos"
	 */
	public ArrayList getProyectos() {
		return proyectos;
	}

	/**
	 * Setter of the property <tt>proyectos</tt>
	 * @param proyectos  The proyectos to set.
	 * @uml.property  name="proyectos"
	 */
	public void setProyectos(ArrayList proyectos) {
		this.proyectos = proyectos;
	}

	/**
	 * Getter of the property <tt>integrantes</tt>
	 * @return  Returns the integrantes.
	 * @uml.property  name="integrantes"
	 */
	public ArrayList getIntegrantes() {
		return integrantes;
	}

	/**
	 * Setter of the property <tt>integrantes</tt>
	 * @param integrantes  The integrantes to set.
	 * @uml.property  name="integrantes"
	 */
	public void setIntegrantes(	ArrayList integrantes) {
		this.integrantes = integrantes;
	}

	/**
	 * Getter of the property <tt>proyectoDAO</tt>
	 * @return  Returns the proyectoDAO.
	 * @uml.property  name="proyectoDAO"
	 */
	public ProyectoDAO getProyectoDAO() {
		return proyectoDAO;
	}

	/**
	 * Setter of the property <tt>proyectoDAO</tt>
	 * @param proyectoDAO  The proyectoDAO to set.
	 * @uml.property  name="proyectoDAO"
	 */
	public void setProyectoDAO(ProyectoDAO proyectoDAO) {
		this.proyectoDAO = proyectoDAO;
	}

	/**
	 * Getter of the property <tt>integranteDAO</tt>
	 * @return  Returns the integranteDAO.
	 * @uml.property  name="integranteDAO"
	 */
	public IntegranteDAO getIntegranteDAO() {
		return integranteDAO;
	}

	/**
	 * Setter of the property <tt>integranteDAO</tt>
	 * @param integranteDAO  The integranteDAO to set.
	 * @uml.property  name="integranteDAO"
	 */
	public void setIntegranteDAO(IntegranteDAO integranteDAO) {
		this.integranteDAO = integranteDAO;
	}

	/** 
	 * Getter of the property <tt>idEquipo</tt>
	 * @return  Returns the idEquipo.
	 * @uml.property  name="id"
	 */
	public String getId() {
		return id;
	}

	/** 
	 * Setter of the property <tt>idEquipo</tt>
	 * @param idEquipo  The idEquipo to set.
	 * @uml.property  name="id"
	 */
	public void setId(String id) {
		this.id = id;
	}

	/** 
	 * Getter of the property <tt>nombreEquipo</tt>
	 * @return  Returns the nombreEquipo.
	 * @uml.property  name="nombre"
	 */
	public String getNombre() {
		return nombre;
	}

	/** 
	 * Setter of the property <tt>nombreEquipo</tt>
	 * @param nombreEquipo  The nombreEquipo to set.
	 * @uml.property  name="nombre"
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** 
	 * Getter of the property <tt>emailEquipo</tt>
	 * @return  Returns the emailEquipo.
	 * @uml.property  name="email"
	 */
	public String getEmail() {
		return email;
	}

	/** 
	 * Setter of the property <tt>emailEquipo</tt>
	 * @param emailEquipo  The emailEquipo to set.
	 * @uml.property  name="email"
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Constructor de la clase Equipo
	 * @param elNombre. El nombre del equipo de trabajo. elNombre != null && elNombre != ""
	 * @param elEmail. El email del equipo de trabajo. elEmail != null && elEmail != ""
	 * @param elId. El identificador del equipo de trabajo. elId != null && elId != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public Equipo(String elNombre, String elEmail, String elId) throws ClassNotFoundException, SQLException{
		proyectos = new ArrayList<Proyecto>();
		proyectoDAO = new ProyectoDAO();
		integrantes= new ArrayList<Integrante>();
		integranteDAO = new IntegranteDAO();
		nombre = elNombre;
		email = elEmail;
		id = elId;
		cargarIntegrantes() ;
		cargarProyecto();
	}
	
	/**
	 * Agrega un proyecto al equipo de trabajo.
	 * <b>pre:</b>La lista de proyecto debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha agregado el proyecto a la lista proyectos.<br>
	 * @param nombre. El nombre del proyecto. nombre != null && nombre != ""
	 * @param fechaInicio. La fecha de inicio del proyecto. fechaInicio != null && fechaInicio != ""
	 * @param fechaFin. La fecha de fin del proyecto. fechaFin != null && fechaFin != ""
	 * @param semestre. El semestre del integrante del proyecto. semestre != null && semestre != ""
	 * @param anio. El año en curso del proyecto. anio != null && anio != ""
	 * @param equipo. El nombre del equipo que contiene el proyecto. equipo != null && equipo != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 * @throws IOException. Lanza una excepción si el proyecto que desea agregar ya existe.
	 */
	public void agregarProyecto(String nombre, String fechaInicio, String fechaFin, String semestre, int anio, String equipo) throws IOException, ClassNotFoundException, SQLException{
		Proyecto miProyecto = buscarProyecto(nombre) ;		
		if (miProyecto == null) {
			miProyecto = new Proyecto(nombre, fechaInicio, fechaFin, semestre, anio,id()) ;
			proyectos.add(miProyecto) ;
			guardarProyecto(miProyecto, equipo) ;
		}else {
			throw new IOException("El proyecto " + nombre + " ya se encuentra registrado") ;
		}
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void cargarDatosProyectos() throws IOException{
		/*
		Proyecto miProyecto = null;
		String ruta= getId()+"-"+getNombre()+".txt";
		FileWriter fv= new FileWriter(RUTA+ruta);
		BufferedWriter bv =new BufferedWriter(fv);


		String cadena = "^  Proyectos ^" + "\n";
		for (int i = 0; i < proyectos.size(); i++) {

			miProyecto = (Proyecto) proyectos.get(i);
			cadena += "|[[" + miProyecto.getIdProyecto() +"-" +  miProyecto.getNombreProyecto() + "]]  |" + "\n" ;
		}
		bv.write(cadena) ;
		bv.close();
		fv.close();
		*/
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public void cargarDatosIntegrantes() throws IOException
	{
		/*
		Integrante miIntegrante = null;
		String ruta= getId()+"-"+getNombre()+".txt";
		FileWriter fv= new FileWriter(RUTA+ruta);
		BufferedWriter bv =new BufferedWriter(fv);

		String cadena =" ======"+getNombre()+"======"+"\n";

		for (int i = 0; i < integrantes.size(); i++) {

			miIntegrante = (Integrante) integrantes.get(i);

			cadena +="^ Integrante: |"+ miIntegrante.getNombreIntegrante()+"|"+"\n";
		}

		cadena+="^  e-mail del Equipo:  |"+getEmail()+"|";

		bv.write(cadena) ;
		bv.close();
		fv.close();
*/
	}

	/**
	 * Elimina un proyecto del equipo de trabajo de la lista proyectos
	 * <b>pre:</b>La lista de proyecto debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha eliminado el proyecto de la lista proyectos.<br>
	 * @param nombre. El nombre del proyecto. nombre != null && nombre != ""
	 * @param id. El identificador del proyecto. id != null && id != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 * @throws IOException. Lanza una excepción si el proyecto que desea eliminar no existe.
	 */
	public void eliminarProyecto(String nombre, String id) throws IOException, ClassNotFoundException, SQLException{
		Proyecto miProyecto = buscarProyecto(nombre) ;
		if(miProyecto != null){
			proyectos.remove(miProyecto);
			proyectoDAO.eliminar(miProyecto, id);
		}else{
			throw new IOException("El Proyecto con el nombre: " +nombre+ " no se encuentra registrado");
		}
	}

	/**
	 * Modifica un proyecto del equipo de trabajo de la lista proyectos
	 * @param nombreProyecto. El nombre del proyecto. nombreProyecto != null && nombreProyecto != ""
	 * @param semestre. El semestre correspondiente del integrante del proyecto. semestre != null && semestre != ""
	 * @param fechaInicio. La fecha de inicio del proyecto. fechaInicio != null && fechaInicio != ""
	 * @param fechaFin. La fecha de fin del proyecto. fechaFin != null && fechaFin != ""
	 * @param anio. El año en curso del proyecto. anio != null && anio != ""
	 * @throws Exception. Lanza una excepción si el proyecto que desea modificar no existe.
	 */
	public void modificarProyecto(String nombreProyecto, String semestre, String fechaInicio, String fechaFin, int anio) throws Exception{
		Proyecto miPoryecto = buscarProyecto(nombreProyecto);		
		if(miPoryecto != null){
			miPoryecto.setNombreProyecto(nombreProyecto);
			miPoryecto.setSemestre(semestre);
			miPoryecto.setFechaInicio(fechaInicio);
			miPoryecto.setFechaFin(fechaFin);
			miPoryecto.setAnio(anio);			
			proyectoDAO.modificar(miPoryecto);
		}
		else{
			throw new IOException("El Proyecto con el nombre: " +nombreProyecto+ " no se encuentra registrado");
		}
	}

	/**
	 * Busca y retorna el proyecto.
	 * <b>pre:</b>La lista de proyectos debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha retornado el proyecto.<br>
	 * @param nombre. El nombre del proyecto. nombre != null && nombre != ""
	 * @return miProyecto. Retorna el proyecto, si no lo encuentra devuelve null.
	 */
	public Proyecto buscarProyecto(String nombre){	
		Proyecto miProyecto = null;
		boolean encontrado = false;
		for(int indice=0; indice < proyectos.size() && !encontrado; indice++){
			miProyecto = (Proyecto) proyectos.get(indice);
			if(miProyecto.getNombreProyecto().equals(nombre)){	
				encontrado = true;
				return miProyecto ;
			}
		}
		return null;
	}

	/**
	 * Agrega el proyecto a la lista de proyectos.
	 * <b>pre:</b>La lista de proyectos debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha guardado el proyecto en la lista proyecto.<br>
	 * @param proyecto. El proyecto del equipo de trabajo. proyecto != null && proyecto != ""
	 * @param id_Equipo. El identificador del equipo de trabajo. id_Equipo != null && id_Equipo != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public void guardarProyecto(Proyecto proyecto, String id_Equipo) throws ClassNotFoundException, SQLException{	
		proyectoDAO.agregar(proyecto,id_Equipo);		
	}

	/**
	 * Carga los proyectos de la lista de proyectos
	 * <b>pre:</b>La lista de proyectos debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha cargado el proyecto en la lista proyecto.<br>
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public void cargarProyecto() throws ClassNotFoundException, SQLException{	
		ArrayList<Proyecto> losProyectos = proyectoDAO.listar(getId());
		if(losProyectos != null){
			setProyectos(losProyectos);
		}
	}


	/**
	 * Agrega un integrante al proyecto que se encuantra en un equipo de trabajo.
	 * <b>pre:</b>La lista de integrantes debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha agregado el integrante a la lista integrantes.<br>
	 * @param id. El identificador del integrante. id != null && id != ""
	 * @param nombre. El nombre del integrante. nombre != null && nombre!= ""
	 * @param roll. El rol del integrante. roll != null && roll != ""
	 * @param email. El email del integrante. email != null && email != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 * @throws IOException. Lanza una excepción si el integrante que desea agregar ya existe.
	 */
	public void agregarIntegrante(String id,String nombre, String roll, String email) throws IOException, ClassNotFoundException, SQLException{
		Integrante miIntegrante = buscarIntegrante(nombre);
		if(miIntegrante == null){
			miIntegrante = new Integrante(id,nombre, roll, email) ;
			integrantes.add(miIntegrante);
			guardarIntegrante(miIntegrante);
		}else{
			throw new IOException("El Integrante con el nombre: " + nombre + " ya se encuentra registrado");
		}
	}

	/**
	 * Elimina un integrante de la lista de integrantes
	 * @param idIntegrante. El identificador del integrante. idIntegrante != null && idIntegrante != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 * @throws IOException. Lanza una excepción si la semana que desea agregar ya existe.
	 */
	public void eliminarIntegrante(String idIntegrante) throws IOException, ClassNotFoundException, SQLException{
		Integrante miIntegrante = buscarIntegrante(idIntegrante);
		if(miIntegrante != null){
			integrantes.remove(miIntegrante);
			integranteDAO.eliminar(miIntegrante,getId());
		}else{
			throw new IOException("El Integrante con el nombre: " + idIntegrante + " no se encuantra registrado");
		}
	}

	/**
	 * Modifica un integrante de la lista de integrantes
	 * @param id. El identificador del integrante. id != null && id != ""
	 * @param nombreIntegrante. El nombre del integrante. nombreIntegrante != null && nombreIntegrante != ""
	 * @param emailIntegrante. El email del integrante. emailIntegrante != null && emailIntegrante != ""
	 * @param rolIntegrante. El rol del integrante. rolIntegrante != null && rolIntegrante != "" 
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 * @throws IOException. Lanza una excepción si el integrante que desea modificar no existe.
	 */
	public void modificarIntegrante(String id,String nombreIntegrante, String emailIntegrante, String rolIntegrante) throws ClassNotFoundException, SQLException, IOException{
		Integrante miIntegrante=buscarIntegrante(id);
		if(miIntegrante!=null){
			miIntegrante.setIdIntegrante(id);
			miIntegrante.setNombreIntegrante(nombreIntegrante);
			miIntegrante.setRolIntegrante(rolIntegrante);
			miIntegrante.setEmailIntegrante(emailIntegrante);
			integranteDAO.modificar(miIntegrante);
		}else{
			throw new IOException("El Integrante con el nombre: " + nombreIntegrante + " no se encuantra registrado");
		}
	}

	/**
	 * Busca y retorna el integrante.
	 * <b>pre:</b>La lista de semanas debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha retornado la semana.<br>
	 * @param id. El identificador del integrante. id != null && id != ""
	 * @return miIntegrante. Retorna el integrante, si no lo encuentra devuelve null.
	 */
	public Integrante buscarIntegrante(String id){	
		Integrante miIntegrante = null;
		boolean encontrado = false;
		for(int indice=0; indice < integrantes.size() && !encontrado; indice++){
			miIntegrante = (Integrante) integrantes.get(indice);
			if(miIntegrante.getIdIntegrante().equals(id)){	
				encontrado = true;
				return miIntegrante;	
			}
		}
		return null ;
	}

	/**
	 * Agrega al integrante a la lista de semanas
	 * <b>pre:</b>La lista de integrantes debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha guardado el inetgrante en la lista integrantes.<br>
	 * @param integrante. El integrante del proyecto. integrante != null && integrante != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public void guardarIntegrante(Integrante integrante) throws ClassNotFoundException, SQLException{
		integranteDAO.agregar(integrante,getId());
	}


	/**
	 * Carga los integrantes de la lista integrantes.
	 * <b>pre:</b>La lista de integrantes debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha cargado el integrante de la lista integrantes.<br>
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public void cargarIntegrantes() throws ClassNotFoundException, SQLException{			
		ArrayList<Integrante> losIntegrantes = integranteDAO.listar(getId());
		if(losIntegrantes != null){
			setIntegrantes(losIntegrantes);
		}
	}
	
	/**
	 * Busca y retorna el identificador de integrante
	 * @return id. el identificador deintegrante. id != null && id != ""
	 */
	public String id() {
		String id = "" ;
		int p = (int) (Math.random() *6) + 1;
		int r = (int) (Math.random() *6) + 1;
		int o = (int) (Math.random() *6) + 1;
		int y = (int) (Math.random() *6) + 1;
		int e = (int) (Math.random() *6) + 1;
		int c = (int) (Math.random() *6) + 1;
		int t = (int) (Math.random() *6) + 1;
		id = getNombre()+"_"+ p+r+o+y+e+c+t ;
		return id ;
	}
}