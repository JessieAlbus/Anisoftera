package umariana.anisoftera.mundo;



import java.lang.String;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import umariana.anisoftera.datos.EquipoDAO;
import umariana.anisoftera.datos.UsuarioDAO;



public class Usuario {

	/**
	 * nombre del usuario
	 * @uml.property  name="nombre"
	 */
	private String usuario;
	
	public final static String RUTA="C:/xampp/htdocs/dokuwiki/data/pages/";

	/**
	 * Getter of the property <tt>nombre</tt>
	 * @return  Returns the nombre.
	 * @uml.property  name="nombre"
	 */
	public String getNombre() {
		return usuario;
	}

	/**
	 * Setter of the property <tt>nombre</tt>
	 * @param nombre  The nombre to set.
	 * @uml.property  name="nombre"
	 */
	public void setNombre(String nombre) {
		this.usuario = nombre;
	}

	/**
	 * clave del usuario
	 * @uml.property  name="clave"
	 */
	private String clave;

	/**
	 * Getter of the property <tt>clave</tt>
	 * @return  Returns the clave.
	 * @uml.property  name="clave"
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Setter of the property <tt>clave</tt>
	 * @param clave  The clave to set.
	 * @uml.property  name="clave"
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}



	/** 
	 * constructor de la clase Equipo
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public Usuario(String elNombre, String laClave)throws ClassNotFoundException, SQLException {
		equipos = new ArrayList<Equipo>();
		equipoDAO = new EquipoDAO();
		usuario= elNombre;
		clave=laClave;
		cargarEquipos();
	}

	/** 
	 * agrega un equipo
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException
	 */
	public void agregarEquipo(String nombreEquipo, String emailEquipo)	throws IOException, ClassNotFoundException, SQLException {
		Equipo miEquipo = buscarEquipo(nombreEquipo);
		Equipo miEmail = buscarEmail(emailEquipo) ;
		if(miEquipo == null){
			if (miEmail == null) {
				miEquipo = new Equipo(nombreEquipo, emailEquipo,id()) ;
				equipos.add(miEquipo);			
				guardarEquipo(miEquipo);
				cargarDatosCalendario();
				cargarDatosEquipos() ;
			}
			else {
				throw new IOException("El email " + emailEquipo + " ya esta registrado "   );
			}

		}else{
			throw new IOException("El equipo " + nombreEquipo + " ya se encuentra registrado");
		}
	}
	
	public Equipo buscarEmail(String email) {
		// TODO Auto-generated method stub
		Equipo miEquipo = null;
		boolean encontrado = false;
		for(int indice=0; indice < equipos.size() && !encontrado; indice++){
			miEquipo = (Equipo) equipos.get(indice);			
			if(miEquipo.getEmail().equals(email)){
				encontrado = true;
				return miEquipo;
			}
		}
		return null;
	}

	/**
	 * carga los datos del calendario
	 * @throws IOException
	 */
	
	public void cargarDatosCalendario() throws IOException{
		FileWriter f =new FileWriter(RUTA+"start.txt");
		BufferedWriter b= new BufferedWriter(f);
		String cadena ="^PROYECTOS TSP^"+"\n" +"|[[ Semestre_"+darMes()+"-"+darAnio()+"]]|";
	
		b.write(cadena);
		b.close();
		f.close();	
	}
	
	/**
	 * 
	 */
	public String darCalendario() {
		String cadena = "Semestre_"+darMes()+"-"+darAnio()+".txt" ;
		return cadena ;
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	
	public void cargarDatosEquipos() throws IOException {
		Equipo miEquipo = null;
		FileWriter fv= new FileWriter(RUTA+darCalendario());
		BufferedWriter bv =new BufferedWriter(fv);
		String cadena = "^  Equipos TSP"+" Semestre "+darMes()+"-"+darAnio()+ " ^" + "\n";
		for (int i = 0; i < equipos.size(); i++) {	
			miEquipo = (Equipo) equipos.get(i);	
			cadena += "|[[" + miEquipo.getId()  +"-" +  miEquipo.getNombre() + "]]  |" + "\n" ;
		}
		cadena += "^  Formatos  ^" + "\n" +
				"|[[F1-Lanzamiento]]  |" + "\n" +
				"|[[F1-Estrategia]]  |" + "\n" +
				"|[[F1-Planeación y seguimiento]]  |" + "\n" +
				"|[[F1-Análisis]]  |" + "\n" +
				"|[[F1-Inspección al análisis]]  |" + "\n" +
				"|[[F1-Diseño]]  |" + "\n" +
				"|[[F1-Inspección al diseño]]  |" + "\n" +
				"|[[F1-Implementación]]  |" + "\n" +
				"|[[F1-Pruebas de aceptación]]  |" + "\n" +
				"|[[F1-Postmortem]]  |" + "\n" +
				"|[[F1-Mediciones]]  |" + "\n" +
				"^  Herramientas  ^" + "\n" +
				"^ Planeación  ^" + "\n" +
				"|[[http://www.dotproject.net/]]|" + "\n" +
				"^ Inspección  ^" + "\n" +
				"|[[http://www.mantisbt.org/]]|" + "\n" +
				"^ Tiempo de trabajo  ^" + "\n" +
				"|[[http://rachota.sourceforge.net/en/index.html]]|" + "\n" +
				"^ Diseño de la interfaz GUI  ^" + "\n" +
				"|[[http://pencil.evolus.vn/en-US/Home.aspx]]|" + "\n" +
				"^ Gestión de configuraciones  ^" + "\n" +
				"|[[http://www.wandisco.com/ubersvn]]|" + "\n" + "\n" +
				"{{software_localization.jpg}}" ;
		
		bv.write(cadena) ;
		bv.newLine();
		bv.close();
		fv.close();
	}

	/**
	 * elimina un equipo
	 */
	public void eliminarEquipo(String nombre)throws Exception{
		Equipo miEquipo = buscarEquipo(nombre) ;
		if (miEquipo != null) {	
			equipos.remove(miEquipo) ;
			equipoDAO.eliminar(miEquipo,getNombre()) ;
		}else {
			throw new Exception("el equipo no existe");
		}		
	}

	/** 
	 * modifica un equipo
	 * @throws Exception 
	 */
	public void modificarEquipo(String nombreEquipo, String emailEquipo) throws Exception{
		Equipo miEquipo= buscarEquipo(nombreEquipo);
		Equipo miEmail = buscarEmail(emailEquipo) ;
		if(miEquipo!=null){
			if (miEmail == null) {
				miEquipo.setNombre(nombreEquipo);
				miEquipo.setEmail(emailEquipo);		
				equipoDAO.modificar(miEquipo);
			}
			else {
				throw new Exception("El email " + emailEquipo + " ya esta registrado");
			}
			
		}else{
			throw new Exception("El equipo no existe");
		}
	}

	/** 
	 * busca un equipo dado su identificador
	 */
	public Equipo buscarEquipo(String elNombre){
		Equipo miEquipo = null;
		boolean encontrado = false;
		for(int indice=0; indice < equipos.size() && !encontrado; indice++){
			miEquipo = (Equipo) equipos.get(indice);			
			if(miEquipo.getNombre().equals(elNombre)){
				encontrado = true;
				return miEquipo;
			}
		}
		return null;
	}

	/**
	 * guarda el equipo en la base de datos
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void guardarEquipo(Equipo equipo) throws ClassNotFoundException, SQLException{
		equipoDAO.agregar(equipo,getNombre()) ;
		cargarEquipos() ;
	}

	/**
	 * carga los equipos existentes en la base de datos
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void cargarEquipos() throws ClassNotFoundException, SQLException{
		ArrayList<Equipo> losEquipos = equipoDAO.listar(getNombre());
		if(losEquipos != null){
		setEquipos(losEquipos);
		}
	}

	/**
	 * @uml.property  name="equipos"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="usuario:umariana.anisoftera.mundo.Equipo"
	 */
	private ArrayList equipos;

	/**
	 * Getter of the property <tt>equipos</tt>
	 * @return  Returns the equipos.
	 * @uml.property  name="equipos"
	 */
	public ArrayList getEquipos() {
		return equipos;
	}

	/**
	 * Setter of the property <tt>equipos</tt>
	 * @param equipos  The equipos to set.
	 * @uml.property  name="equipos"
	 */
	public void setEquipos(ArrayList equipo) {
		this.equipos = equipo;
	}

	/**
	 * @uml.property  name="equipoDAO"
	 * @uml.associationEnd  inverse="usuario:umariana.anisoftera.datos.EquipoDAO"
	 */
	private EquipoDAO equipoDAO;

	/**
	 * Getter of the property <tt>equipoDAO</tt>
	 * @return  Returns the equipoDAO.
	 * @uml.property  name="equipoDAO"
	 */
	public EquipoDAO getEquipoDAO() {
		return equipoDAO;
	}

	/**
	 * Setter of the property <tt>equipoDAO</tt>
	 * @param equipoDAO  The equipoDAO to set.
	 * @uml.property  name="equipoDAO"
	 */
	public void setEquipoDAO(EquipoDAO equipoDAO) {
		this.equipoDAO = equipoDAO;
	}


	/**
	 * Generar y retorna el Id del equipo
	 */
	public String generarId(){
		String id = darAnio()+"-"+ darMes()+ "-E";
		return  id ;	
	}
	/**
	 * 
	 * @return
	 */
	public String id() {		
		Equipo miEquipo = null;
		boolean encontrado = false;
		String cadena = getNombre()+"_"+generarId() ;
		String cadena2 = "" ;
		String pk = "" ;
		String comparativa = "" ;
		if (equipos.size() == 0) {
			pk = cadena+String.valueOf(0) ;
		}else{
			for(int indice=0; indice <= equipos.size() && !encontrado; indice++){
				if (indice < equipos.size()) {
					miEquipo = (Equipo) equipos.get(indice);
					cadena2 = miEquipo.getId();
					comparativa = cadena+String.valueOf(indice) ;
					if (!comparativa.equalsIgnoreCase(cadena2)) {
						pk = cadena+String.valueOf(indice);
						encontrado = true ;
					}										
				}else{
					pk = cadena+String.valueOf(indice);
				}
			}
		}
		return pk;
	}
	
	public String darAnio(){
		int anio = 0 ;
		Date date = new Date();		
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(date) ;
	}
	
	public String darMes(){
		Date date = new Date() ;		
		DateFormat dateFormat = new SimpleDateFormat("MM");
		int entero = Integer.parseInt(dateFormat.format(date)) ;
		String calendario = "A" ;
		if (entero > 6) {
			calendario = "B" ;			
		}
		return calendario ;
	}
}
