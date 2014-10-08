package umariana.anisoftera.mundo;


public class Tiempo {

	/**
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
	 * @uml.property  name="horas"
	 */
	private double horas;

	/**
	 * Getter of the property <tt>horas</tt>
	 * @return  Returns the horas.
	 * @uml.property  name="horas"
	 */
	public double getHoras() {
		return horas;
	}

	/**
	 * Setter of the property <tt>horas</tt>
	 * @param horas  The horas to set.
	 * @uml.property  name="horas"
	 */
	public void setHoras(double horas) {
		this.horas = horas;
	}

	/**
	 * @uml.property  name="reseso"
	 */
	private double reseso;

	/**
	 * Getter of the property <tt>reseso</tt>
	 * @return  Returns the reseso.
	 * @uml.property  name="reseso"
	 */
	public double getReseso() {
		return reseso;
	}

	/**
	 * Setter of the property <tt>reseso</tt>
	 * @param reseso  The reseso to set.
	 * @uml.property  name="reseso"
	 */
	public void setReseso(double reseso) {
		this.reseso = reseso;
	}

	/**
	 * @uml.property  name="comentario"
	 */
	private String comentario;

	/**
	 * Getter of the property <tt>comentario</tt>
	 * @return  Returns the comentario.
	 * @uml.property  name="comentario"
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Setter of the property <tt>comentario</tt>
	 * @param comentario  The comentario to set.
	 * @uml.property  name="comentario"
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @uml.property  name="integrante"
	 * @uml.associationEnd  inverse="tiempo:umariana.anisoftera.mundo.Integrante"
	 */
	private Integrante integrante;

	/**
	 * Getter of the property <tt>integrante</tt>
	 * @return  Returns the integrante.
	 * @uml.property  name="integrante"
	 */
	public Integrante getIntegrante() {
		return integrante;
	}

	/**
	 * Setter of the property <tt>integrante</tt>
	 * @param integrante  The integrante to set.
	 * @uml.property  name="integrante"
	 */
	public void setIntegrante(Integrante integrante) {
		this.integrante = integrante;
	}

	/**
	 * @uml.property  name="actividad"
	 * @uml.associationEnd  inverse="tiempo:umariana.anisoftera.mundo.Actividad"
	 */
	private Actividad actividad;

	/**
	 * Getter of the property <tt>actividad</tt>
	 * @return  Returns the actividad.
	 * @uml.property  name="actividad"
	 */
	public Actividad getActividad() {
		return actividad;
	}

	/**
	 * Setter of the property <tt>actividad</tt>
	 * @param actividad  The actividad to set.
	 * @uml.property  name="actividad"
	 */
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

}
