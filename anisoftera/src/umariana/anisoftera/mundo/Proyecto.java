package umariana.anisoftera.mundo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;


import umariana.anisoftera.datos.CicloDAO;
import umariana.anisoftera.datos.ProyectoDAO;


public class Proyecto {

	public final static String RUTA="C:/xampp/htdocs/dokuwiki/data/pages/";

	/** 
	 * identificador del proyecto
	 * @uml.property name="idProyecto"
	 */
	private String idProyecto;
	
	/**
	 * nombre del proyecto
	 * @uml.property  name="nombreProyecto"
	 */
	private String nombreProyecto;

	/**
	 * semestre A o B del proyecto
	 * @uml.property  name="semestre"
	 */
	private String semestre;

	/**
	 * fecha de inicio del proyecto
	 * @uml.property  name="fechaInicio"
	 */
	private String fechaInicio;

	/**
	 * fecha fin del proyecto
	 * @uml.property  name="fechaFin"
	 */
	private String fechaFin;

	/**
	 * año que se esta realizando el proyecto
	 * @uml.property  name="anio"
	 */
	private int anio;

	/**
	 * @uml.property  name="ciclos"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="proyecto:umariana.anisoftera.mundo.Ciclo"
	 */
	private ArrayList ciclos;

	/**
	 * @uml.property  name="cicloDAO"
	 * @uml.associationEnd  inverse="proyecto:umariana.anisoftera.datos.CicloDAO"
	 */
	private CicloDAO cicloDAO;

	/**
	 * constructor de la clase proyecto
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Proyecto(String nombreProyecto, String fechaInicio, String fechaFin, String semestre, int anio,String id) throws ClassNotFoundException, SQLException
	{
		ciclos = new ArrayList<Ciclo>();
		cicloDAO = new CicloDAO();
		this.nombreProyecto=nombreProyecto;
		this.fechaInicio=fechaInicio;
		this.fechaFin= fechaFin;
		this.semestre=semestre;
		this.anio=anio;
		this.idProyecto=id;
		
		cargarCiclo();
		
	}

	/**
	 * se agrega un ciclo al proyecto
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void agregarCiclo(int numeroCiclo, String proyecto) throws IOException, ClassNotFoundException, SQLException{
		Ciclo miCiclo = buscarCiclo(numeroCiclo) ;
		
		if (miCiclo == null) {	
			
			miCiclo = new Ciclo(numeroCiclo, idCiclo()) ;				
			ciclos.add(miCiclo) ;	
			guardarCiclo(miCiclo, proyecto) ;
		}
		else {
			throw new IOException("El ciclo " + numeroCiclo + " ya se encuentra registrado") ;
		}
	}

	/**
	 * @throws IOException 
	 * 
	 */
	
	public void cargarDatosCiclos() throws IOException
	{
		Ciclo miCiclo = null;
		String ruta= getIdProyecto()+"-"+getNombreProyecto()+".txt";
		FileWriter fv= new FileWriter(RUTA+ruta);
		BufferedWriter bv =new BufferedWriter(fv);
		
	
		String cadena = "";
		
		for (int i = 0; i < ciclos.size(); i++) {
			
			miCiclo = (Ciclo) ciclos.get(i);
			
		 
			cadena += "^  CICLO "+miCiclo.getNumeroCiclo()+"  ^"+"\n" +"^  Proceso  ^"+ "\n" +
					"|[[E1B12-Lanzamiento]]  |"+ "\n" +
					"|[[E1B12-Estrategia]]  |"+ "\n" +
					"|[[E1B12-Planeación y seguimiento]]  |"+ "\n" +
					"|[[E1B12-Análisis]]  |"+ "\n" +
					"|[[E1B12-Inspección al análisis]]  |"+ "\n" +
					"|[[E1B12-Diseño]]  |"+ "\n" +
					"|[[E1B12-Inspección al diseño]]  |"+ "\n" +
					"|[[E1B12-Implementación]]  |"+ "\n" +
					"|[[E1B12-Pruebas de aceptación]]  |"+ "\n" +
					"|[[E1B12-Postmortem]]  |"+ "\n" +
					"^  Información del Proceso  ^"+ "\n" +
					"|[[E1B12-Mediciones para el proceo]]  |"+ "\n" ;
		}
		
		
		bv.write(cadena) ;
		bv.close();
		fv.close();
		
	}

	/**
	 * se elimina un ciclo
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void eliminarCiclo(int numeroCiclo) throws ClassNotFoundException, SQLException{
		Ciclo miCiclo = buscarCiclo(numeroCiclo) ;
		if (miCiclo != null) {				
			ciclos.remove(miCiclo) ;		
			cicloDAO.eliminar(miCiclo,getIdProyecto());
		}				
	}

	/**
	 * se modifica un ciclo
	 */
	public void modificarCiclo(int numeroCiclo){
	}

	/**
	 * busca un ciclo
	 */
	public Ciclo buscarCiclo(int numeroCiclo){	
		Ciclo miCiclo = null;
		boolean encontrado = false;
		for(int indice = 0; indice < ciclos.size() && !encontrado; indice++)
		{	
			
			miCiclo = (Ciclo) ciclos.get(indice);
			if(miCiclo.getNumeroCiclo() == numeroCiclo)
			{	
				encontrado = true;
				return miCiclo ;
			}
		}
		return null;
	}

	/**
	 * guarda el ciclo en la base de datos 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void guardarCiclo(Ciclo ciclo, String proyecto) throws ClassNotFoundException, SQLException{	
		cicloDAO.agregar(ciclo, proyecto);
	}

	/**
	 * carga los ciclos existentes en la base de datos 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void cargarCiclo() throws ClassNotFoundException, SQLException{
		ArrayList<Ciclo> losCiclos = cicloDAO.listar(getIdProyecto());
		if(losCiclos != null)
		{
			setCiclos(losCiclos);
		}	
	}

	/**
	 * 
	 * @return
	 */
	public String idCiclo() {
		String id = "" ;
		
		int numero1 = (int) (Math.random()*400+1);
		int numero2 = (int) (Math.random()*100+1);
		int numero3 = (int) (Math.random()*500+1);
		
		return "id_"+numero1+ "_"+numero2+"_"+numero3+"_semana_"+ciclos.size() ;
		
	}
	
	/**
	 * Getter of the property <tt>nombreProyecto</tt>
	 * @return  Returns the nombreProyecto.
	 * @uml.property  name="nombreProyecto"
	 */
	public String getNombreProyecto() {
		return nombreProyecto;
	}

	/**
	 * Setter of the property <tt>nombreProyecto</tt>
	 * @param nombreProyecto  The nombreProyecto to set.
	 * @uml.property  name="nombreProyecto"
	 */
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	/**
	 * Getter of the property <tt>semestre</tt>
	 * @return  Returns the semestre.
	 * @uml.property  name="semestre"
	 */
	public String getSemestre() {
		return semestre;
	}

	/**
	 * Setter of the property <tt>semestre</tt>
	 * @param semestre  The semestre to set.
	 * @uml.property  name="semestre"
	 */
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	/**
	 * Getter of the property <tt>fechaInicio</tt>
	 * @return  Returns the fechaInicio.
	 * @uml.property  name="fechaInicio"
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Setter of the property <tt>fechaInicio</tt>
	 * @param fechaInicio  The fechaInicio to set.
	 * @uml.property  name="fechaInicio"
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Getter of the property <tt>fechaFin</tt>
	 * @return  Returns the fechaFin.
	 * @uml.property  name="fechaFin"
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Setter of the property <tt>fechaFin</tt>
	 * @param fechaFin  The fechaFin to set.
	 * @uml.property  name="fechaFin"
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Getter of the property <tt>anio</tt>
	 * @return  Returns the anio.
	 * @uml.property  name="anio"
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Setter of the property <tt>anio</tt>
	 * @param anio  The anio to set. 
	 * @uml.property  name="anio"
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}


	/**
	 * Getter of the property <tt>ciclos</tt>
	 * @return  Returns the ciclos.
	 * @uml.property  name="ciclos"
	 */
	public ArrayList getCiclos() {
		return ciclos;
	}

	/**
	 * Setter of the property <tt>ciclos</tt>
	 * @param ciclos  The ciclos to set.
	 * @uml.property  name="ciclos"
	 */
	public void setCiclos(ArrayList ciclos) {
		this.ciclos = ciclos;
	}

	/**
	 * Getter of the property <tt>cicloDAO</tt>
	 * @return  Returns the cicloDAO.
	 * @uml.property  name="cicloDAO"
	 */
	public CicloDAO getCicloDAO() {
		return cicloDAO;
	}

	/**
	 * Setter of the property <tt>cicloDAO</tt>
	 * @param cicloDAO  The cicloDAO to set.
	 * @uml.property  name="cicloDAO"
	 */
	public void setCicloDAO(CicloDAO cicloDAO) {
		this.cicloDAO = cicloDAO;
	}

	/** 
	 * Getter of the property <tt>idProyecto</tt>
	 * @return  Returns the idProyecto.
	 * @uml.property  name="idProyecto"
	 */
	public String getIdProyecto() {
		return idProyecto;
	}

	/** 
	 * Setter of the property <tt>idProyecto</tt>
	 * @param idProyecto  The idProyecto to set.
	 * @uml.property  name="idProyecto"
	 */
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}


}
