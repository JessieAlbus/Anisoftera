package umariana.anisoftera.mundo;



import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;



import umariana.anisoftera.mundo.Usuario;
import umariana.anisoftera.datos.UsuarioDAO;

import umariana.anisoftera.datos.FaseDAO;


public class Integracion {


	/**
	 * @uml.property   name="usuarios"
	 */
	private ArrayList usuarios;
	
	/**
	 * @uml.property   name="usuarioDAO"
	 */
	private UsuarioDAO usuarioDAO;

	/**
	 * Getter of the property <tt>usuarios</tt>
	 * @return  Returns the usuarios.
	 * @uml.property  name="usuarios"
	 */
	public ArrayList getUsuarios() {
		return usuarios;
	}

	/**
	 * Setter of the property <tt>usuarios</tt>
	 * @param usuarios  The usuarios to set.
	 * @uml.property  name="usuarios"
	 */
	public void setUsuarios(ArrayList usuarios) 
	{
		this.usuarios = usuarios;
	}

	/**
	 * Getter of the property <tt>usuarioDAO</tt>
	 * @return  Returns the usuarioDAO.
	 * @uml.property  name="usuarioDAO"
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * Setter of the property <tt>usuarioDAO</tt>
	 * @param usuarioDAO  The usuarioDAO to set.
	 * @uml.property  name="usuarioDAO"
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	/**
	 * @uml.property  name="fases"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="integracion:umariana.anisoftera.mundo.Fase"
	 */
	private ArrayList fases;


	/**
	 * Getter of the property <tt>fases</tt>
	 * @return  Returns the fases.
	 * @uml.property  name="fases"
	 */
	public ArrayList getFases() {
		return fases;
	}
	
	/**
	 * Setter of the property <tt>fases</tt>
	 * @param fases  The fases to set.
	 * @uml.property  name="fases"
	 */
	public void setFases(ArrayList fases) {
		this.fases = fases;
	}
	
	/**
	 * @uml.property  name="faseDAO"
	 * @uml.associationEnd  inverse="integracion:umariana.anisoftera.datos.FaseDAO"
	 */
	private FaseDAO faseDAO;


	/**
	 * Getter of the property <tt>faseDAO</tt>
	 * @return  Returns the faseDAO.
	 * @uml.property  name="faseDAO"
	 */
	public FaseDAO getFaseDAO() {
		return faseDAO;
	}

	/**
	 * Setter of the property <tt>faseDAO</tt>
	 * @param faseDAO  The faseDAO to set.
	 * @uml.property  name="faseDAO"
	 */
	public void setFaseDAO(FaseDAO faseDAO) {
		this.faseDAO = faseDAO;
	}

	/**
	 * Constructor de la clase Integración
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.Exception
	 * @throws SQLException
	 */
	public Integracion() throws ClassNotFoundException, SQLException{
		usuarios = new ArrayList<Usuario>();
		usuarioDAO = new UsuarioDAO();		
		fases = new ArrayList<Fase>();
		faseDAO = new FaseDAO() ;
		cargarUsuarios();
		cargarFase() ;
	}

	/**
	 * Agrega un equipo de trabajo
	 * <b>pre:</b>La lista de equipos debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha agregado el equipo a la lista equipos.<br>
	 * @param nombre. El nombre del equipo de trabajo. nombre != null && nombre != ""
	 * @param email. El email del equipo de trabajo. email != null && email != ""
	 * @param usuario. El usuario que ingresa al sistema Anisoftera. usuario != null && usuario != ""
	 * @throws Exception. Lanza una excepción si el equipo que desea agregar no existe el usuario.
	 */
	public void agregarEquipo(String nombre, String email,String usuario) throws Exception{

		Usuario miUsuario = buscarUsuario(usuario);	
		if(miUsuario != null){
			miUsuario.agregarEquipo(nombre, email);
		}else{
			throw new Exception("Al agregar un Equipo, el Usuario no se encuentra registrado ") ;
		}
	}

	/**
	 * Elimina un equipo de trabajo de la lista equipos.
	 * <b>pre:</b>La lista de equipos debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha agregado el equipo a la lista equipos.<br>
	 * @param equipo. El equipo de trabajo. equipo != null && equipo != ""
	 * @param usuario. EL usuario que ingresa al sistema Anisoftera. usuario != null && usuario != ""
	 * @throws Exception. Lanza una excepción si el equipo que desea eliminar no existe el usuario.
	 */
	public void eliminarEquipo(String equipo, String usuario)throws Exception{

		Usuario miUsuario = buscarUsuario(usuario) ;
		if (miUsuario != null) {
			miUsuario.eliminarEquipo(equipo) ;	
		}else {
			throw new Exception("Al eliminar un Equipo, el Usuario no se encuentra registrado ") ;
		}
	}

	/**
	 * Busca y retorna el equipo de trabajo
	 * @param usuario. El usuario que ingresa al sistema Anisoftera. usuario != null && usuario != ""
	 * @param equipo. El equipo de trabajo. equipo != null && equipo != ""
	 * @return miEquipo. Retorna el equipo de trabajo, si no lo encuantra devuelve null-
	 * @throws Exception. Lanza una excepción si el equipo que desea buscar, no existe el usuario
	 */
	public Equipo buscarEquipo(String usuario, String equipo ) throws Exception{

		Usuario miUsuario = buscarUsuario(usuario) ;
		Equipo miEquipo = null ;
		if (miUsuario != null) {
			miEquipo =  miUsuario.buscarEquipo(equipo) ;
			if (miEquipo != null ) {
				return miEquipo ;
			}else {
				throw new Exception("Al buscar un Equipo, el Equipo no se encuentra registrado ") ;
			}
		}else{
			throw new Exception("Al buscar un Equipo, el Usuario no se encuentra registrado ") ;
		}
	}

	//-------------------------------Por Aqui voy en los contratos ------------------------------//

	/** 
	 * se modifica un equipo de trabajo
	 * @throws Exception 
	 */
	public void modificarEquipo(String elNombre, String elEmail, String usuario) throws Exception{
		Usuario miUsuario = buscarUsuario(usuario);
		if(miUsuario!=null){
			miUsuario.modificarEquipo(elNombre, elEmail);
		}else{
			throw new Exception("el usuario no existe") ;
		}
	}

	/** 
	 * se agrega un proyecto al equipo de trabajo
	 * @throws Exception 
	 */
	public void agregarProyecto(String nombre, String fechaInicio, String fechaFin, String semestre, int anio, String nombreUsuario,String nomEquipo) throws Exception{
		Usuario miUsuario = buscarUsuario(nombreUsuario);
		if(miUsuario!= null)
		{
			Equipo miEquipo = miUsuario.buscarEquipo(nomEquipo) ;
			if(miEquipo!=null)
			{
				miEquipo.agregarProyecto(nombre, fechaInicio, fechaFin, semestre, anio, miEquipo.getId());
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}
		}
		else
		{
			throw new Exception("El usuario no existe") ;
		}
	}

	/**
	 * se elimina un proyecto dado su identificador
	 * @throws Exception 
	 */
	public void eliminarProyecto(String nombre, String usuario, String equipo) throws Exception{
		Usuario miUsuario = buscarUsuario(usuario);
		if(miUsuario!=null)
		{
			Equipo miEquipo = miUsuario.buscarEquipo(equipo) ;
			if(miEquipo!=null)
			{								
				miEquipo.eliminarProyecto(nombre, miEquipo.getId()) ;
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}
		}
		else
		{
			throw new Exception("El usuario no existe") ;
		}
	}

	/**
	 * @throws Exception 
	 * 
	 */

	public void modificarProyecto(String nombreProyecto, String fechaInicio, String fechaFin, String semestre, int anio, String nombreusuario,String nombreEquipo) throws Exception{
		Usuario miUsuario = buscarUsuario(nombreusuario);
		if(miUsuario!=null)
		{
			Equipo miEquipo=miUsuario.buscarEquipo(nombreEquipo);
			if(miEquipo !=null){
				miEquipo.modificarProyecto(nombreProyecto, semestre, fechaInicio, fechaFin, anio);
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}
		}
		else
		{
			throw new Exception("El usuario no existe") ;
		}
	}


	/** 
	 * se agrega un integrante al euipo de trabajo
	 * @throws Exception 
	 */
	public void agregarIntegrante(String id,String nombre, String roll, String email, String nombreUsuario, String nombreEquipo) throws Exception{
		Usuario miUsuario = buscarUsuario(nombreUsuario);
		if(miUsuario!=null)
		{
			Equipo miEquipo = miUsuario.buscarEquipo(nombreEquipo) ;
			if(miEquipo!=null)
			{

				miEquipo.agregarIntegrante(id,nombre, roll, email) ;
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}

		}
		else
		{
			throw new Exception("El usuario no existe") ;
		}
	}



	/**
	 * se elimina un integrante dado su identificador
	 * @throws Exception 
	 */
	public void eliminarIntegrante(String idIntegrante, String usuario, String equipo) throws Exception {

		Usuario miUsuario = buscarUsuario(usuario);	
		if(miUsuario!=null)
		{
			Equipo miEquipo = miUsuario.buscarEquipo(equipo) ;
			if(miEquipo!=null)
			{			
				miEquipo.eliminarIntegrante(idIntegrante) ;
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}
		}
		else
		{
			throw new Exception("El usuario no existe") ;
		}

	}

	/** 
	 * se modifica un integrante de un equipo de trabajo
	 * @throws Exception 
	 */
	public void modificarIntegrante(String id, String nombreIntegrante, String rolIntegrante, String emailIntegrante, String nombreusuario, String nombreEquipo) throws Exception
	{
		
		Usuario miUsuario=buscarUsuario(nombreusuario);
		if(miUsuario!=null)
		{
			Equipo miEquipo= miUsuario.buscarEquipo(nombreEquipo);
			if(miEquipo!=null)
			{
				miEquipo.modificarIntegrante(id,nombreIntegrante, emailIntegrante, rolIntegrante);
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}
		}
		else
		{
			throw new Exception("El usuario no existe") ;
		}

	}

	/**
	 * se agrega un ciclo al proyecto 
	 * @throws Exception 
	 */
	public void agregarCiclo(int numeroCiclo, String nombreUsuario, String nomEquipo,String nombreProyecto) throws Exception{
		Usuario miUsuario = buscarUsuario(nombreUsuario);
		if(miUsuario!=null)
		{
			Equipo miEquipo = miUsuario.buscarEquipo(nomEquipo) ;
			if(miEquipo!=null)
			{
				Proyecto miProyecto = miEquipo.buscarProyecto(nombreProyecto) ;
				if(miProyecto != null)
				{
					miProyecto.agregarCiclo(numeroCiclo, miProyecto.getIdProyecto());			
				}
				else
				{
					throw new Exception("El proyecto no existe") ;
				}
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}

		}
		else
		{
			throw new Exception("El usuario no existe") ;
		}
	}



	/**
	 * se elimina un ciclo de un proyecto
	 * @throws Exception 
	 */
	public void eliminarCiclo(int numeroCiclo, String nombreUsuario, String nomEquipo,String nombreProyecto) throws Exception{
		
		Usuario miUsuario = buscarUsuario(nombreUsuario);
		if(miUsuario!=null)
		{
			Equipo miEquipo = miUsuario.buscarEquipo(nomEquipo) ;
			if(miEquipo!=null)
			{
				Proyecto miProyecto = miEquipo.buscarProyecto(nombreProyecto) ;

				if(miProyecto!=null)
				{		
					miProyecto.eliminarCiclo(numeroCiclo); 		
				}
				else
				{
					throw new Exception("El proyecto no existe") ;
				}
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}	
		}
		else
		{
			throw new Exception("El usuario no existe") ;
		}
	}



	/**
	 * se modifica un ciclo
	 */
	public void modificarCiclo(int numeroCiclo){
	}



	/**
	 * se agrega una semana a un ciclo
	 * @throws Exception 
	 */
	public void agregarSemana(int semana, String fechaInicio, String fechaFin,String nombreUsuario, String nombreEquipo,String nombreProyecto, int ciclo) throws Exception{
		Usuario miUsuario = buscarUsuario(nombreUsuario);
		if(miUsuario!=null)
		{
			Equipo miEquipo = miUsuario.buscarEquipo(nombreEquipo) ;
			if(miEquipo!=null)
			{
				Proyecto miProyecto = miEquipo.buscarProyecto(nombreProyecto) ;
				if(miProyecto!=null)
				{
					Ciclo miCiclo=miProyecto.buscarCiclo(ciclo);
					if(miCiclo != null)
					{
						miCiclo.agregarSemana(semana, fechaInicio, fechaFin);			
					}
					else
					{
						throw new Exception("El ciclo no existe") ;
					}
				}
				else
				{
					throw new Exception("El proyecto no existe") ;
				}
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}
		}
		else
		{
			throw new Exception("el usuario no existe") ;
		}
	}

	/**
	 * se elimina una semana dado su numero 
	 * @throws Exception 
	 */
	public void eliminarSemana(int semana, String nombreUsuario,String nombreEquipo,String nombreProyecto,int ciclo) throws Exception{

		Usuario miUsuario = buscarUsuario(nombreUsuario);
		if(miUsuario!=null)
		{
			Equipo miEquipo = miUsuario.buscarEquipo(nombreEquipo) ;
			if(miEquipo!=null)
			{
				Proyecto miProyecto = miEquipo.buscarProyecto(nombreProyecto) ;
				if(miProyecto!=null)
				{
					Ciclo miCiclo = miProyecto.buscarCiclo(ciclo) ;
					if(miCiclo != null)
					{
						miCiclo.eliminarSemana(semana);			
					}
					else
					{
						throw new Exception("El ciclo no existe") ;
					}
				}
				else
				{
					throw new Exception("El proyecto no existe") ;
				}
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}
		}
		else
		{
			throw new Exception("el usuario no existe") ;
		}
		
	}
	/**
	 * se modifica una semana de un ciclo
	 * @throws Exception 
	 */
	public void modificarSemana(int semana, String fechaInicio, String fechaFin, String nombreUsuario, String equipo, String proyecto, int ciclo) throws Exception{
		Usuario miUsuario = buscarUsuario(nombreUsuario);
		if(miUsuario!=null)
		{
			Equipo miEquipo=miUsuario.buscarEquipo(equipo);
			if(miEquipo!=null)
			{
				Proyecto miPoyecto=miEquipo.buscarProyecto(proyecto);
				if(miPoyecto!=null)
				{
					Ciclo miCiclo=miPoyecto.buscarCiclo(ciclo);
					if(miCiclo!=null)
					{
						miCiclo.modificarSemana(semana, fechaInicio, fechaFin);
					}
					else
					{
						throw new Exception("El ciclo no existe") ;
					}
				}
				else
				{
					throw new Exception("El proyecto no existe") ;
				}
			}
			else
			{
				throw new Exception("El equipo no existe") ;
			}
		}
		else
		{
			throw new Exception("El usuario no existe") ;
		}		
	}

	/**
	 * busca un proyecto dado su idnetificador
	 * @throws Exception 
	 */
	public Proyecto buscarProyecto(String nomUsuario, String nomEquipo,String nomProyecto)
	{
		return null;
	}

	/**
	 * busca un integrante dado su identificador
	 */
	public Integrante buscarIntegrante(int idIntegrante){
		return null;
	}

	/**
	 * busca un ciclo dado su identificador
	 */
	public Ciclo buscarCiclo(int numeroCiclo){
		return null;
	}

	/**
	 * busca una semana
	 */
	public Semana buscarSemana(int semana){
		return null;
	}
	/** 
	 * agrega un usuario
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void agregarUsusario(String elNombre, String laClave) throws IOException, ClassNotFoundException, SQLException{
	Usuario miUsuario = buscarUsuario(elNombre);
		if(miUsuario == null)
		{
			miUsuario = new Usuario(elNombre,laClave);
			usuarios.add(miUsuario);
			guardarUsuario(miUsuario);
		}
		else
		{
			throw new IOException("El usuario " + elNombre + " ya se encuentra registrado");
		}
		
	}

	/**
	 * guarda el usuario en la base de datos
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void guardarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException{

		usuarioDAO.agregar(usuario);
	}

	/**
	 * busca un usuario con su nombre
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Usuario buscarUsuario(String elNombre) 
	{
		Usuario miUsuario = null;
		boolean encontrado = false;		
		for(int indice=0; indice < usuarios.size() && !encontrado; indice++)
		{	
			miUsuario = (Usuario) usuarios.get(indice);			
			if(miUsuario.getNombre().equals(elNombre))
			{	
				encontrado = true;	
				return miUsuario;
			}
		}
		return null ;
	}

	/**
	 * 
	 * @param elNombre
	 * @return
	 */
	public boolean buscar(String elNombre){
		/*
		boolean encontrado = false;
		for(int indice=0; indice < usuarios.size() && !encontrado; indice++)
		{	
			Usuario miUsuario = (Usuario) usuarios.get(indice);
			if(miUsuario.getNombre().equals(elNombre))
			{
				encontrado = true;
			}
		}
		return encontrado;
		*/
		return true ;
	}

	/**
	 * carga los usuarios existentes en la base de datos
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void cargarUsuarios() throws ClassNotFoundException, SQLException{		
		ArrayList<Usuario> losUsuarios = usuarioDAO.listar();
		if(losUsuarios != null)
		{
			setUsuarios(losUsuarios);
		}
	}

	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public void modificarUsuario(String nombre, String clave) throws ClassNotFoundException, SQLException, IOException{
		/*
		Usuario miUsuario = buscarUsuario(nombre);
		if(miUsuario != null)
		{
			miUsuario.setNombre(nombre);
			miUsuario.setClave(clave);

			usuarios.add(miUsuario);
			guardarUsuario(miUsuario);
		}
		else
		{
			throw new IOException("El usuario " + nombre + " ya se encuentra registrado");
		}
		*/
	}
		
		public void agregarFase(String nombre) throws IOException, ClassNotFoundException, SQLException {
			Fase miFase = buscarFase(nombre) ;
			if(miFase == null){
				miFase = new Fase(nombre);
				fases.add(miFase);
				guardarFase(miFase);
			}else{
				throw new IOException("La fase " + nombre + " ya se encuentra registrada");
			}
		}
		
		public void  guardarFase(Fase fase) throws ClassNotFoundException, SQLException {
			faseDAO.agregar(fase) ;
		}

		public Fase buscarFase(String nombre) {
			Fase miFase = null;
			boolean encontrado = false;		
			for(int indice=0; indice < fases.size() && !encontrado; indice++){
				miFase = (Fase)fases.get(indice);			
				if(miFase.getNombre().equals(nombre)){
					encontrado = true;	
					return miFase;
				}
			}
			return null ;
		}
		
		public void eliminarFase(String nombre) throws Exception {
			Fase miFase = buscarFase(nombre) ;
			if (miFase != null) {
				fases.remove(miFase) ;
				faseDAO.eliminar(miFase) ;
			}else {
				throw new Exception("La fase no existe");
			}	
		}

		public void cargarFase() throws ClassNotFoundException, SQLException {
			ArrayList<Fase> lasFases = faseDAO.listar();
			if(lasFases != null){	
				setFases(lasFases);
			}		
		}

		public void agregarActividad(String nombre,int horas, int minutos,  String fase) throws Exception {	
			Fase miFase = buscarFase(fase) ;
			if(miFase != null){
				miFase.agregarActividad(nombre, horas, minutos);
			}else{
				throw new Exception("La fase no existe") ;
			}	
		}

		public void modificarActividad(String nombre,int horas , int minutos , String fase) throws Exception {
			Fase miFase = buscarFase(nombre) ;
			if(miFase!=null){
				miFase.modificarActividad(nombre, horas, minutos);
			}else{
				throw new Exception("La fase no existe") ;
			}
		}

		public void agregarFasesProyecto(String usuario, String equipo,
				String proyecto, int ciclos, String[] fases) throws Exception {
			

		
			// TODO Auto-generated method stub
			Usuario miUsuario = buscarUsuario(usuario);
			if(miUsuario!=null)
			{
				Equipo miEquipo = miUsuario.buscarEquipo(equipo) ;
				if(miEquipo!=null)
				{
					Proyecto miProyecto = miEquipo.buscarProyecto(proyecto) ;
					if(miProyecto!=null)
					{
						Ciclo miCiclo=miProyecto.buscarCiclo(ciclos);
						if(miCiclo != null)
						{
								miCiclo.asignarFases(fases) ;							
						}
						else
						{
							throw new Exception("El ciclo no existe") ;
						}
					}
					else
					{
						throw new Exception("El proyecto no existe") ;
					}
				}
				else
				{
					throw new Exception("El equipo no existe") ;
				}
			}
			else
			{
				throw new Exception("el usuario no existe") ;
			}
		
		}

			
}
