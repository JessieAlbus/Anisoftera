package umariana.anisoftera.datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import umariana.anisoftera.mundo.Equipo;
import umariana.anisoftera.mundo.Usuario;

public class EquipoDAO {

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
	 * construcotr de la clase EquipoDAO
	 */
	public EquipoDAO(){
		fachada = new Fachada();
	}

	/**
	 * Agrega un equipo a la DB
	 * @param equipo, equipo != null
	 * @param usuario, usuario != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void agregar(Equipo equipo,String usuario) throws ClassNotFoundException, SQLException{
		String sqlGuardar = "INSERT INTO equipo (nombre, email, id) VALUES('" + equipo.getNombre()+ "','" + equipo.getEmail() + "','" + equipo.getId() + "')";
		String sqlGuardar2= "INSERT INTO usuario_has_equipo (usuario_usuario, equipo_id) VALUES('" + usuario + "','" + equipo.getId() + "')";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			instruccion.executeUpdate(sqlGuardar);
			instruccion.executeUpdate(sqlGuardar2);
			fachada.desconectar(miConexion);
		}
	}
	
	/**
	 * Elimina un equipo de la DB
	 * @param equipo, equipo != null
	 * @param usuario, usuario != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void eliminar(Equipo equipo, String usuario) throws ClassNotFoundException, SQLException{
		String sql="delete from usuario_has_equipo where usuario_usuario='"+usuario+"'and equipo_id='"+equipo.getId()+"'";
		String sql3 = "delete from proyecto where equipo_id = '" + equipo.getId()+"'";
		String sql4="delete from equipo_has_integrante where  equipo_id='"+equipo.getId()+"'";
		String sqlGuardar = "delete from equipo where id = '" + equipo.getId()+"'";
		
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			instruccion.executeUpdate(sql3);
			instruccion.executeUpdate(sql4);
			instruccion.executeUpdate(sql);
			instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * Modifica un equipo de la DB
	 * @param equipo, equipo != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modificar(Equipo equipo) throws ClassNotFoundException, SQLException{
		String sqlGuardar = "UPDATE equipo SET nombre='" + equipo.getNombre()+ "',email='" + equipo.getEmail() + "' where nombre='"+equipo.getNombre()+"';";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * Lista los equipos de la DB
	 * @param usuario, usuario != null
	 * @return equipos, retorna una lista con los equipos de la DB
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList listar(String usuario) throws ClassNotFoundException, SQLException{
		
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yyyy");
		String sqlConsultar = "SELECT nombre, email, id FROM equipo, usuario_has_equipo WHERE id=equipo_id and usuario_usuario='"+usuario+"' ORDER BY id";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			ResultSet tabla = instruccion.executeQuery(sqlConsultar);
			while(tabla.next()){
				Equipo miEquipo= new Equipo(tabla.getString("nombre"),tabla.getString("email"), tabla.getString("id"));
				equipos.add(miEquipo);				
			}
			fachada.desconectar(miConexion);
		}
		return equipos;
	}
}