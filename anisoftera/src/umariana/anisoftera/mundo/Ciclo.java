package umariana.anisoftera.mundo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.Random;

import umariana.anisoftera.datos.CicloDAO;
import umariana.anisoftera.datos.SemanaDAO;
import umariana.anisoftera.datos.FaseDAO;

public class Ciclo {

	/**
	 * Número del ciclo
	 * @uml.property  name="numeroCiclo"
	 */
	private int numeroCiclo;

	/**
	 * @uml.property  name="semanas"
	 * @uml.associationEnd   multiplicity="(0 -1)" ordering="true" inverse="semana:umariana.anisoftera.mundo.Semana"
	 */
	private ArrayList semanas;
	
	/**
	 * @uml.property  name="semanas"
	 * * @uml.associationEnd   multiplicity="(0 -1)" ordering="true" inverse="semana:umariana.anisoftera.mundo.Semana"
	 */
	private SemanaDAO semanaDAO;

	/**
	 * Getter of the property <tt>ciclo</tt>
	 * @return  Returns the numeroCiclo.
	 * @uml.property  name="numeroCiclo"
	 */
	public int getNumeroCiclo() {
		return numeroCiclo;
	}

	/**
	 * Setter of the property <tt>ciclo</tt>
	 * @param numeroCiclo  The ciclo to set.
	 * @uml.property  name="numeorCiclo"
	 */
	public void setNumeroCiclo(int numeroCiclo) {
		this.numeroCiclo = numeroCiclo;
	}

	/**
	 * Getter of the property <tt>semanas</tt>
	 * @return  Returns the semanas.
	 * @uml.property  name="semanas"
	 */
	public ArrayList getSemanas() {
		return semanas;
	}

	/**
	 * Setter of the property <tt>semanas</tt>
	 * @param semanas  The semanas to set.
	 * @uml.property  name="semanas"
	 */
	public void setSemanas(ArrayList semanas) {
		this.semanas = semanas;
	}


	/**
	 * Getter of the property <tt>semanaDAO</tt>
	 * @return  Returns the semanaDAO.
	 * @uml.property  name="semanaDAO"
	 */
	public SemanaDAO getSemanaDAO() {
		return semanaDAO;
	}

	/**
	 * Setter of the property <tt>semanaDAO</tt>
	 * @param semanaDAO  The semanaDAO to set.
	 * @uml.property  name="semanaDAO"
	 */
	public void setSemanaDAO(SemanaDAO semanaDAO) {
		this.semanaDAO = semanaDAO;
	}

	/**
	 * El identificador del ciclo
	 * @uml.property  name="id"
	 */
	private String id;

	/**
	 * Getter of the property <tt>id</tt>
	 * @return  Returns the id.
	 * @uml.property  name="id"
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter of the property <tt>id</tt>
	 * @param id  The id to set.
	 * @uml.property  name="id"
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @uml.property  name="fasesCiclo"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="ciclo:umariana.anisoftera.mundo.Fase"
	 */
	private ArrayList fasesCiclo;

	/**
	 * Getter of the property <tt>fasesCiclo</tt>
	 * @return  Returns the fasesCiclo.
	 * @uml.property  name="fasesCiclo"
	 */
	public ArrayList getFasesCiclo() {
		return fasesCiclo;
	}

	/**
	 * Setter of the property <tt>fasesCiclo</tt>
	 * @param fasesCiclo  The fasesCiclo to set.
	 * @uml.property  name="fasesCiclo"
	 */
	public void setFasesCiclo(ArrayList fasesCiclo) {
		this.fasesCiclo = fasesCiclo;
	}
	
	/**
	 * Constructor de la clase Ciclo.
	 * @param ciclo. El número de ciclo del proyecto. ciclo != null && ciclo != ""
	 * @param id. El identificador del ciclo del proyecto. id != null && id != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public Ciclo(int ciclo, String id) throws ClassNotFoundException, SQLException{
		semanas = new ArrayList<Semana>();
		semanaDAO = new SemanaDAO();
		faseDAO = new FaseDAO() ;
		this.numeroCiclo = ciclo ;
		this.id = id ;
		cargarSemanas() ;
		
	}

	/**
	 * Agrega la semana del ciclo al proyecto.
	 * <b>pre:</b>La lista de semanas debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha agregado la semana a la lista semanas.<br>
	 * @param semana. La semana del ciclo del proyecto. semana != null && semana != ""
	 * @param fechaInicio. la fecha de inicio de la semana. fechaInicio != null && fechaInicio != ""
	 * @param fechaFin. la fecha de fin de la semana. fechaFin != null && fechaFin != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 * @throws IOException. Lanza una excepción si la semana que desea agregar ya existe.
	 */
	public void agregarSemana(int semana, String fechaInicio, String fechaFin) throws ClassNotFoundException, SQLException, IOException{
		Semana miSemana = buscarSemana(semana) ;
		if (miSemana == null) {			
			miSemana = new Semana(semana, fechaInicio, fechaFin, getId()) ;			
			semanas.add(miSemana) ;	
			guardarSemana(miSemana, getId()) ;
		}else {
			throw new IOException("la Semana con el número: " + semana + " ya se encuentra registrada") ;
		}
	}

	/**
	 * Elimina la semana de la lista de semanas
	 * <b>pre:</b>La lista de semanas debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha eliminado la semana de la lista semanas.<br>
	 * @param semana. La semana del ciclo del proyecto. semana != null && semana != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 * @throws IOException. Lanza una excepción si la semana que desea agregar ya existe.
	 */
	public void eliminarSemana(int semana) throws ClassNotFoundException, SQLException, IOException{
		Semana miSemana = buscarSemana(semana) ;
		if (miSemana != null) {				
			semanas.remove(miSemana) ;		
			semanaDAO.eliminar(miSemana);
		}else{
			throw new IOException("la Semana con el número: " +semana+ " no se encuentra registrada") ;
		}
	}

	/**
	 * Modifica la semana de la lista de semanas.
	 * <b>pre:</b>La lista de semanas debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha modificado la semana de la lista semanas.<br>
	 * @param semana. La semana del ciclo del proyecto. semana != null && semana != ""
	 * @param fechaInicio. la fecha de inicio de la semana. fechaInicio != null && fechaInicio != ""
	 * @param fechaFin. la fecha de fin de la semana. fechaFin != null && fechaFin != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 * @throws IOException. Lanza una excepción si la semana que desea agregar ya existe.
	 */
	public void modificarSemana(int semana, String fechaInicio, String fechaFin) throws ClassNotFoundException, SQLException, IOException{
		Semana miSemana=buscarSemana(semana);
		if(miSemana!=null){
			miSemana.setFechaFin(fechaFin);
			miSemana.setFechaInicio(fechaInicio);
			miSemana.setSemanaNumero(semana);	
			semanaDAO.modificar(miSemana);
		}else{
			throw new IOException("la Semana con el número: " +semana+ " no se encuentra registrada") ;
		}
	}

	/**
	 * Busca y retorna la semana.
	 * <b>pre:</b>La lista de semanas debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha retornado la semana.<br>
	 * @param semana. La semana del ciclo del proyecto. semana != null && semana != ""
	 * @return miSemana. Retorna la semana, si no la encuentra devuelve null.
	 */
	public Semana buscarSemana(int semana){
		Semana miSemana = null;
		boolean encontrado = false;	
		for(int indice=0; indice < semanas.size() && !encontrado; indice++){	
			miSemana = (Semana) semanas.get(indice);
			if(miSemana.getSemanaNumero() == semana){					
				encontrado = true;
				return miSemana ;
			}
		}
		return null;
	}

	/**
	 * Agrega la semana a la lista de semanas
	 * <b>pre:</b>La lista de semanas debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha guardado la semana de la lista semanas.<br>
	 * @param semana. La semana del ciclo del proyecto. semana != null && semana != ""
	 * @param ciclo. El ciclo del proyecto. ciclo != null && ciclo != ""
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public void guardarSemana(Semana semana, String ciclo) throws ClassNotFoundException, SQLException{
		semanaDAO.agregar(semana , ciclo ) ;
	}

	/**
	 * Carga las semanas de la lista semanas
	 * <b>pre:</b>La lista de semanas debe existir y haberse inicializado.<br>
	 * <b>post:</br>Se ha cargado la semana de la lista semanas.<br>
	 * @throws ClassNotFoundException. Lanza una excepción si la clase no existe.
	 * @throws SQLException. Lanza una excepción al ingresar a la DB.
	 */
	public void cargarSemanas() throws ClassNotFoundException, SQLException{		
		ArrayList<Semana> lasSemanas = semanaDAO.listar(getId());
		if(lasSemanas != null){
			setSemanas(lasSemanas);
		}	
	}
	
	/**
	 * Busca y retorna el identificador de semana
	 * @return id. el identificador de semana. id != null && id != ""
	 */
	public String  id() {
		String id= "" ;
		int s = (int) (Math.random()*9+1);
		int i = (int) (Math.random()*9+1);
		int c = (int) (Math.random()*9+1);		
		int l = (int) (Math.random()*9+1);
		int o = (int) (Math.random()*9+1);
		
		id= "ciclo_"+s+i+c+l+o ;
		return id ;
	}

	public void asignarFases(String[] fases) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub	

		
		
			System.out.println("QUE POSO POR AQUI ..................." + getId());
			//fasesCiclo.add(cadena) ;	
			//guardarFase(fases, getId()) ;;
			faseDAO.asignarFases(fases, getId());
	}

	public void guardarFase(String[] fases, String id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("ESTA ENTRENDO EN LA PARTE ANTES DE PASRA A EL DAO");
		//faseDAO.asignarFases(fases, id);
	}

	/**
	 * @uml.property  name="faseDAO"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="ciclo:umariana.anisoftera.datos.FaseDAO"
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


}