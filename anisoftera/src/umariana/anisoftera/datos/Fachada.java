package umariana.anisoftera.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Fachada {

	/**
	 * DRIVER realiza la conexion con el motor de DB 
	 */
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * URL describe la ruta de la DB
	 */
	public static final String URL = "jdbc:mysql://localhost:3306/anisoftera";
	
	/**
	 * USUARIO el usuario que esta definido en la DB es root 
	 */
	public static final String USUARIO = "root";
	
	/**
	 * PASSWORD es la clave de acceso a la DB
	 */
	public static final String PASSWORD = "1234";
	
	/**
	 * Atributo de la url: conectar BD
	 */
	private String urlFachada;
	
	/**
	 * Atributo del usuario de la BD
	 */
	private String usuarioFachada;

	/**
	 * Atributo del password de la BD
	 */
	private String passwordFachada;

	/**
	 * Atributo del drvier de conexion
	 */
	private String driverFachada;

	/**
	 * constructor de la clase fachada
	 */
	public Fachada(){
		urlFachada = URL;
		usuarioFachada = USUARIO;
		passwordFachada = PASSWORD;
	}

	/**
	 * Realiza la conexión con la DB 
	 * @return La conexión con la DB
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection conectar() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
		 Connection conexion = DriverManager.getConnection(urlFachada,usuarioFachada,passwordFachada);
		 return conexion;	
	}

	/**
	 * Realiza la desconexión de la DB
	 * @param connection, cierra la conexión
	 * @throws SQLException
	 */
	public void desconectar(Connection connection) throws SQLException{
		connection.close();
	}

	/**
	 * Getter of the property <tt>url</tt>
	 * @return  Returns the url.
	 * @uml.property  name="url"
	 */
	public String getUrlFachada() {
		return urlFachada;
	}
	
	/**
	 * Setter of the property <tt>url</tt>
	 * @param url  The url to set.
	 * @uml.property  name="url"
	 */
	public void setUrlFachada(String urlFachada) {
		this.urlFachada = urlFachada;
	}
	
	/**
	 * Getter of the property <tt>usuario</tt>
	 * @return  Returns the usuario.
	 * @uml.property  name="usuario"
	 */
	public String getUsuarioFachada() {
		return usuarioFachada;
	}
	
	/**
	 * Setter of the property <tt>usuario</tt>
	 * @param usuario  The usuario to set.
	 * @uml.property  name="usuario"
	 */
	public void setUsuarioFachada(String usuarioFachada) {
		this.usuarioFachada = usuarioFachada;
	}
	
	/**
	 * Getter of the property <tt>password</tt>
	 * @return  Returns the password.
	 * @uml.property  name="password"
	 */
	public String getPasswordFachada() {
		return passwordFachada;
	}
	
	/**
	 * Setter of the property <tt>password</tt>
	 * @param password  The password to set.
	 * @uml.property  name="password"
	 */
	public void setPasswordFachada(String passwordFachada) {
		this.passwordFachada = passwordFachada;
	}

	/**
	 * Getter of the property <tt>driver</tt>
	 * @return  Returns the driver.
	 * @uml.property  name="driver"
	 */
	public String getDriverFachada() {
		return driverFachada;
	}
	
	/**
	 * Setter of the property <tt>driver</tt>
	 * @param driver  The driver to set.
	 * @uml.property  name="driver"
	 */
	public void setDriverFachada(String driverFachada) {
		this.driverFachada = driverFachada;
	}
}