package umariana.anisoftera.mundo;


public class Integrante {

	/**
	 * constante del lider del proyecto
	 * @uml.property  name="LIDER" readOnly="true"
	 */
	public static final String lider = "lider";
	/**
	 * constante del lider de calidad
	 * @uml.property  name="LIDER_CALIDAD" readOnly="true"
	 */
	public static final String lider_CALIDAD = "calidad";
	/**
	 * constante lider de planeacion 
	 * @uml.property  name="LIDER_PLANEACION" readOnly="true"
	 */
	public static final String lider_PLANEACION = "planeacion";
	/**
	 * constante lider de soporte
	 * @uml.property  name="LIDER_SOPORTE" readOnly="true"
	 */
	public static final String lider_SOPORTE = "soporte";
	/**
	 * constante lider de desarrollo
	 * @uml.property  name="LIDER_DESARROLLO" readOnly="true"
	 */
	public static final String lider_DESARROLLO = "desarrollo";
	/**
	 * Getter of the property <tt>idIntegrante</tt>
	 * @return  Returns the idIntegrante.
	 * @uml.property  name="idIntegrante"
	 */
	public String getIdIntegrante() {
		return idIntegrante;
	}
	/**
	 * Setter of the property <tt>idIntegrante</tt>
	 * @param idIntegrante  The idIntegrante to set.
	 * @uml.property  name="idIntegrante"
	 */
	public void setIdIntegrante(String idIntegrante) {
		this.idIntegrante = idIntegrante;
	}
	/**
	 * nombre del integrante
	 * @uml.property  name="nombreIntegrante"
	 */
	private String nombreIntegrante;
	/**
	 * Getter of the property <tt>nombreIntegrante</tt>
	 * @return  Returns the nombreIntegrante.
	 * @uml.property  name="nombreIntegrante"
	 */
	public String getNombreIntegrante() {
		return nombreIntegrante;
	}
	/**
	 * Setter of the property <tt>nombreIntegrante</tt>
	 * @param nombreIntegrante  The nombreIntegrante to set.
	 * @uml.property  name="nombreIntegrante"
	 */
	public void setNombreIntegrante(String nombreIntegrante) {
		this.nombreIntegrante = nombreIntegrante;
	}
	/**
	 * identificador del integrante
	 * @uml.property  name="idIntegrante"
	 */
	private String idIntegrante;
	/**
	 * rol del integrante
	 * @uml.property  name="rolIntegrante"
	 */
	private String rolIntegrante;
	/**
	 * Getter of the property <tt>rolIntegrante</tt>
	 * @return  Returns the rolIntegrante.
	 * @uml.property  name="rolIntegrante"
	 */
	public String getRolIntegrante() {
		return rolIntegrante;
	}
	/**
	 * Setter of the property <tt>rolIntegrante</tt>
	 * @param rolIntegrante  The rolIntegrante to set.
	 * @uml.property  name="rolIntegrante"
	 */
	public void setRolIntegrante(String rolIntegrante) {
		this.rolIntegrante = rolIntegrante;
	}
	/**
	 * email del integrante
	 * @uml.property  name="emailIntegrante"
	 */
	private String emailIntegrante;
	/**
	 * Getter of the property <tt>emailIntegrante</tt>
	 * @return  Returns the emailIntegrante.
	 * @uml.property  name="emailIntegrante"
	 */
	public String getEmailIntegrante() {
		return emailIntegrante;
	}
	/**
	 * Setter of the property <tt>emailIntegrante</tt>
	 * @param emailIntegrante  The emailIntegrante to set.
	 * @uml.property  name="emailIntegrante"
	 */
	public void setEmailIntegrante(String emailIntegrante) {
		this.emailIntegrante = emailIntegrante;
	}

	/**
	 * constructor de la clase integrante
	 */
	public Integrante(String id,String nombreIntegrante, String rolIntegrante, String emailIntegrante){
		this.emailIntegrante = emailIntegrante ;
		this.nombreIntegrante = nombreIntegrante ;
		this.rolIntegrante = rolIntegrante ;
		this.idIntegrante=id;
		
	}

}
