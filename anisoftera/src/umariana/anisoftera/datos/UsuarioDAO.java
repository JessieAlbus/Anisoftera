package umariana.anisoftera.datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import umariana.anisoftera.mundo.Proyecto;
import umariana.anisoftera.datos.Fachada;
import umariana.anisoftera.mundo.Usuario;

public class UsuarioDAO {

	/**
	 * @uml.property  name="fachada"
	 * @uml.associationEnd  inverse="fachada:umariana.anisoftera.mundo.Fachada"
	 */
	private Fachada fachada;

	/**
	 * Getter of the property <tt>fachada</tt>
	 * @return  Returns the fachada.
	 * @uml.property  name="fachada"
	 */
	public Fachada getFachada() {
		return fachada;
	}

	/**
	 * Setter of the property <tt>fachada</tt>
	 * @param fachada  The fachada to set.
	 * @uml.property  name="fachada"
	 */
	public void setFachada(Fachada fachada) {
		this.fachada = fachada;
	}
	
	/**
	 * constructor de la clase UsuarioDAO
	 */
	public UsuarioDAO(){
		fachada = new Fachada();
	}

	/**
	 * Agrega un usuario a la DB
	 * @param usuario, usuario != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void agregar(Usuario usuario) throws ClassNotFoundException, SQLException{		
		String sqlGuardar = "INSERT INTO usuario (usuario, clave) VALUES('" + usuario.getNombre() + "','" + usuario.getClave() + "')";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado = instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * elimina un usuario de la base de datos
	 */
	public void eliminar(Usuario usuario){
	}

	/**
	 * modifica un usuario de la base de datos
	 */
	public void modificar(Usuario usuario){
	}

	/**
	 * Lista a la usuario de la BD
	 * @return usuarios, retorna los usuarios que están en la DB
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList listar() throws ClassNotFoundException, SQLException{
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sqlConsultar = "SELECT usuario, clave FROM usuario ORDER BY usuario";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			ResultSet tabla = instruccion.executeQuery(sqlConsultar);
			while(tabla.next()){
				Usuario miUsuario= new Usuario(tabla.getString("usuario"),tabla.getString("clave"));
				usuarios.add(miUsuario);
			}
			fachada.desconectar(miConexion);
		}
		return usuarios;
	}
}