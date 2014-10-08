package umariana.anisoftera.mundo;


public class Evalucacion {

	/**
	 * @uml.property  name="autoevaluacion"
	 */
	private double autoevaluacion;

	/**
	 * Getter of the property <tt>autoevaluacion</tt>
	 * @return  Returns the autoevaluacion.
	 * @uml.property  name="autoevaluacion"
	 */
	public double getAutoevaluacion() {
		return autoevaluacion;
	}

	/**
	 * Setter of the property <tt>autoevaluacion</tt>
	 * @param autoevaluacion  The autoevaluacion to set.
	 * @uml.property  name="autoevaluacion"
	 */
	public void setAutoevaluacion(double autoevaluacion) {
		this.autoevaluacion = autoevaluacion;
	}

	/**
	 * @uml.property  name="coevaluacion"
	 */
	private double coevaluacion;

	/**
	 * Getter of the property <tt>coevaluacion</tt>
	 * @return  Returns the coevaluacion.
	 * @uml.property  name="coevaluacion"
	 */
	public double getCoevaluacion() {
		return coevaluacion;
	}

	/**
	 * Setter of the property <tt>coevaluacion</tt>
	 * @param coevaluacion  The coevaluacion to set.
	 * @uml.property  name="coevaluacion"
	 */
	public void setCoevaluacion(double coevaluacion) {
		this.coevaluacion = coevaluacion;
	}

	/**
	 * @uml.property  name="heteroevaluacion"
	 */
	private double heteroevaluacion;

	/**
	 * Getter of the property <tt>heteroevaluacion</tt>
	 * @return  Returns the heteroevaluacion.
	 * @uml.property  name="heteroevaluacion"
	 */
	public double getHeteroevaluacion() {
		return heteroevaluacion;
	}

	/**
	 * Setter of the property <tt>heteroevaluacion</tt>
	 * @param heteroevaluacion  The heteroevaluacion to set.
	 * @uml.property  name="heteroevaluacion"
	 */
	public void setHeteroevaluacion(double heteroevaluacion) {
		this.heteroevaluacion = heteroevaluacion;
	}

		
		/**
		 */
		public Evalucacion(){
		}

		/**
		 * @uml.property  name="integrante"
		 * @uml.associationEnd  inverse="evalucacion:umariana.anisoftera.mundo.Integrante"
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

}
