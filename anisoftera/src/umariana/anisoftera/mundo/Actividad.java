package umariana.anisoftera.mundo;

public class Actividad {

	/**
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
	 * @uml.property  name="estado"
	 */
	private String estado;

	/**
	 * Getter of the property <tt>estado</tt>
	 * @return  Returns the estado.
	 * @uml.property  name="estado"
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Setter of the property <tt>estado</tt>
	 * @param estado  The estado to set.
	 * @uml.property  name="estado"
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/** 
	 * @uml.property name="FINALIZADA" readOnly="true"
	 */
	public static final String finalizada = "FINALIZADA";
	/** 
	 * @uml.property name="EN_PROSESO" readOnly="true"
	 */
	public static final String en_PROSESO = "EN_PROSESO";
	/**
	 * @uml.property  name="NO_INICIADA" readOnly="true"
	 */
	public static final String no_INICIADA = "NO_INICIADA";
	
	/**
	* @uml.property  name="semana"
	* @uml.associationEnd  inverse="actividad:umariana.anisoftera.mundo.Semana"
	*/
	private Semana semana;

	/**
	* Getter of the property <tt>semana</tt>
	* @return  Returns the semana.
	* @uml.property  name="semana"
	*/
	public Semana getSemana() {
		return semana;
	}

	/**
	* Setter of the property <tt>semana</tt>
	* @param semana  The semana to set.
	* @uml.property  name="semana"
	*/
	public void setSemana(Semana semana) {
		this.semana = semana;
	}

	/**
	* @uml.property  name="tiempoActividad"
	* @uml.associationEnd  inverse="actividad:umariana.anisoftera.mundo.TiempoActividad"
	*/
	private TiempoActividad tiempoActividad;

	/**
	* Getter of the property <tt>tiempoActividad</tt>
	* @return  Returns the tiempoActividad.
	* @uml.property  name="tiempoActividad"
	*/
	public TiempoActividad getTiempoActividad() {
		return tiempoActividad;
	}

	/**
	* Setter of the property <tt>tiempoActividad</tt>
	* @param tiempoActividad  The tiempoActividad to set.
	* @uml.property  name="tiempoActividad"
	*/
	public void setTiempoActividad(TiempoActividad tiempoActividad) {
		this.tiempoActividad = tiempoActividad;
	}

	/**
	* @uml.property  name="tiempoActividad1"
	* @uml.associationEnd  inverse="actividad:umariana.anisoftera.mundo.TiempoActividad"
	*/
	private TiempoActividad tiempoActividad1;

	/**
	* Getter of the property <tt>tiempoActividad1</tt>
	* @return  Returns the tiempoActividad1.
	* @uml.property  name="tiempoActividad1"
	*/
	public TiempoActividad getTiempoActividad1() {
		return tiempoActividad1;
	}

	/**
	* Setter of the property <tt>tiempoActividad1</tt>
	* @param tiempoActividad1  The tiempoActividad1 to set.
	* @uml.property  name="tiempoActividad1"
	*/
	public void setTiempoActividad1(TiempoActividad tiempoActividad1) {
		this.tiempoActividad1 = tiempoActividad1;
	}

	/**
	* @uml.property  name="horasPlaneadas"
	*/
	private int horasPlaneadas;

	/**
	* Getter of the property <tt>horasPlaneadas</tt>
	* @return  Returns the horasPlaneadas.
	* @uml.property  name="horasPlaneadas"
	*/
	public int getHorasPlaneadas() {
		return horasPlaneadas;
	}

	/**
	* Setter of the property <tt>horasPlaneadas</tt>
	* @param horasPlaneadas  The horasPlaneadas to set.
	* @uml.property  name="horasPlaneadas"
	*/
	public void setHorasPlaneadas(int horasPlaneadas) {
		this.horasPlaneadas = horasPlaneadas;
	}

	/**
	* @uml.property  name="minutosPlaneados"
	*/
	private int minutosPlaneados;

	/**
	* Getter of the property <tt>minutosPlaneados</tt>
	* @return  Returns the minutosPlaneados.
	* @uml.property  name="minutosPlaneados"
	*/
	public int getMinutosPlaneados() {
		return minutosPlaneados;
	}

	/**
	* Setter of the property <tt>minutosPlaneados</tt>
	* @param minutosPlaneados  The minutosPlaneados to set.
	* @uml.property  name="minutosPlaneados"
	*/
	public void setMinutosPlaneados(int minutosPlaneados) {
		this.minutosPlaneados = minutosPlaneados;
	}

	/**
	* @uml.property  name="horasUtilizadas"
	*/
	private int horasUtilizadas;

	/**
	* Getter of the property <tt>horasUtilizadas</tt>
	* @return  Returns the horasUtilizadas.
	* @uml.property  name="horasUtilizadas"
	*/
	public int getHorasUtilizadas() {
		return horasUtilizadas;
	}

	/**
	* Setter of the property <tt>horasUtilizadas</tt>
	* @param horasUtilizadas  The horasUtilizadas to set.
	* @uml.property  name="horasUtilizadas"
	*/
	public void setHorasUtilizadas(int horasUtilizadas) {
		this.horasUtilizadas = horasUtilizadas;
	}

	/**
	* @uml.property  name="minutosUtilizados"
	*/
	private int minutosUtilizados;

	/**
	* Getter of the property <tt>minutosUtilizados</tt>
	* @return  Returns the minutosUtilizados.
	* @uml.property  name="minutosUtilizados"
	*/
	public int getMinutosUtilizados() {
		return minutosUtilizados;
	}

	/**
	* Setter of the property <tt>minutosUtilizados</tt>
	* @param minutosUtilizados  The minutosUtilizados to set.
	* @uml.property  name="minutosUtilizados"
	*/
	public void setMinutosUtilizados(int minutosUtilizados) {
		this.minutosUtilizados = minutosUtilizados;
	}
		
	/**
	 * El constructor de la Clase Actividad
	 * @param nombre, el nombre de la actividad. nombre != null && nombre != ""
	 * @param horas, las horas de la actividad. horas != null && horas != ""
	 * @param minutos, los minutos de la actividad. minutos != null && minutos != ""
	 */
	public Actividad(String nombre, int horas , int minutos){
		this.nombre = nombre ;
		this.horasPlaneadas = horas ;
		this.minutosPlaneados = minutos ;
	}
}