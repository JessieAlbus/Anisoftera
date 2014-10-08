package umariana.anisoftera.mundo;

import java.util.Date;
import java.util.Collection;


public class Semana {

	/**
	 * numero de la semana
	 * @uml.property  name="semanaNumero"
	 */
	private int semanaNumero;

	/**
	 * Getter of the property <tt>semanaNumero</tt>
	 * @return  Returns the semanaNumero.
	 * @uml.property  name="semanaNumero"
	 */
	public int getSemanaNumero() {
		return semanaNumero;
	}

	/**
	 * Setter of the property <tt>semanaNumero</tt>
	 * @param semanaNumero  The semanaNumero to set.
	 * @uml.property  name="semanaNumero"
	 */
	public void setSemanaNumero(int semanaNumero) {
		this.semanaNumero = semanaNumero;
	}

	/**
	 * fecha inicio de la semana
	 * @uml.property  name="fechaInicio"
	 */
	private String fechaInicio;

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
	 * fecha fin de la semana
	 * @uml.property  name="fechaFin"
	 */
	private String fechaFin;

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
	 * constructor de la clase Semana
	 */
	public Semana(int semanaNumero, String fechaInicio, String fechaFin, String id){
		this.semanaNumero = semanaNumero ;
		this.fechaInicio = fechaInicio ;
		this.fechaFin = fechaFin ;
		this.id = id ;
		
	}

	/**
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
	 * @uml.property  name="evalucaciones"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="semana:umariana.anisoftera.mundo.Evalucacion"
	 */
	private Collection evalucaciones;

	/**
	 * Getter of the property <tt>evalucaciones</tt>
	 * @return  Returns the evalucaciones.
	 * @uml.property  name="evalucaciones"
	 */
	public Collection getEvalucaciones() {
		return evalucaciones;
	}

	/**
	 * Setter of the property <tt>evalucaciones</tt>
	 * @param evalucaciones  The evalucaciones to set.
	 * @uml.property  name="evalucaciones"
	 */
	public void setEvalucaciones(Collection evalucaciones) {
		this.evalucaciones = evalucaciones;
	}

	/**
	 * @uml.property   name="tiempos"
	 * @uml.associationEnd   multiplicity="(0 -1)" inverse="semana:umariana.anisoftera.mundo.Tiempo"
	 */
	private Collection tiempos;

	/**
	 * Getter of the property <tt>tiempos</tt>
	 * @return  Returns the tiempos.
	 * @uml.property  name="tiempos"
	 */
	public Collection getTiempos() {
		return tiempos;
	}

	/**
	 * Setter of the property <tt>tiempos</tt>
	 * @param tiempos  The tiempos to set.
	 * @uml.property  name="tiempos"
	 */
	public void setTiempos(Collection tiempos) {
		this.tiempos = tiempos;
	}

}
