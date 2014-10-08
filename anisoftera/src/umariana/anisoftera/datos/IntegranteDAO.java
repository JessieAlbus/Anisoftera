package umariana.anisoftera.datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import umariana.anisoftera.mundo.Equipo;
import umariana.anisoftera.mundo.Integrante;
import umariana.anisoftera.mundo.Proyecto;

public class IntegranteDAO {

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
	 * constructor de la clase IntegranteDAO
	 */
	public IntegranteDAO(){
		fachada = new Fachada();
	}

	/**
	 * Agrega un Integrante a la DB
	 * @param integrante, integrante != null
	 * @param id, id != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void agregar(Integrante integrante,String id) throws ClassNotFoundException, SQLException{
		String sqlGuardar = "INSERT INTO integrante (id,nombre, email, rol) VALUES('"+integrante.getIdIntegrante() +"','"+ integrante.getNombreIntegrante() +"','" + integrante.getEmailIntegrante() + "','" + integrante.getRolIntegrante() +"')";
		String sql2= "INSERT INTO equipo_has_integrante (equipo_id, integrante_id) VALUES('" + id + "','" + integrante.getIdIntegrante() + "')";
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado = instruccion.executeUpdate(sqlGuardar);
			int resultado2 = instruccion.executeUpdate(sql2);
			miFachada.desconectar(miConexion);
		}
	}

	/**
	 * Elimina un Integrante de la DB
	 * @param integrante, integrante != null
	 * @param id, id != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void eliminar(Integrante integrante,String id) throws ClassNotFoundException, SQLException{
		String sql="delete from equipo_has_integrante where equipo_id='"+id+"'and integrante_id='"+integrante.getIdIntegrante()+"'";
		String sqlGuardar = "delete from integrante where id = '" + integrante.getIdIntegrante()+"'";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado1 = instruccion.executeUpdate(sql);
			int resultado = instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * Modifica un Integrante de la DB
	 * @param integrante, integrante != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modificar(Integrante integrante) throws ClassNotFoundException, SQLException{
		String sqlGuardar = "UPDATE integrante SET nombre='" + integrante.getNombreIntegrante()+ "',email='" + integrante.getEmailIntegrante() +"',rol='" + integrante.getRolIntegrante() + "' where id='"+integrante.getIdIntegrante()+"';";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado = instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * Lista los Integrantes que se encuentran en la DB
	 * @param id, id != null
	 * @return integrantes, retorna la lista de los integrantes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList listar(String id) throws ClassNotFoundException, SQLException{		
		ArrayList<Integrante> integrantes = new ArrayList<Integrante>();
		//String sqlConsultar = "select id,nombre, email, rol from integrante, equipo_has_integrante where equipo_has_integrante.Equipo_id='"+id+"' group by id";
		String sqlConsultar = "select i.id, i.nombre, i.email, i.rol, ei.Equipo_id from equipo e inner join equipo_has_integrante ei on ei.Equipo_id = '"+id+"' inner join integrante i on i.id = ei.Integrante_id group by i.id";
		
		
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			ResultSet tabla = instruccion.executeQuery(sqlConsultar);
			while(tabla.next()){
				Integrante miIntegrante= new Integrante(tabla.getString("id"),tabla.getString("nombre"), tabla.getString("rol"), tabla.getString("email"));
				integrantes.add(miIntegrante);
			}
			miFachada.desconectar(miConexion);
		}
		return integrantes;		
	}
}