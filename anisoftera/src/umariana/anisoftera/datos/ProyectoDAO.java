package umariana.anisoftera.datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import umariana.anisoftera.mundo.Integrante;
import umariana.anisoftera.mundo.Proyecto;

public class ProyectoDAO {

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
	 * constructor de la clase ProyectoDAO
	 */
	public ProyectoDAO(){
		fachada = new Fachada();
	}

	/**
	 * Agrega un Proyecto a la DB
	 * @param proyecto, proyecto != null
	 * @param id, id != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void agregar(Proyecto proyecto, String id) throws ClassNotFoundException, SQLException{
		
		String sentencia = "INSERT INTO proyecto (id, nombre, fechaInicio, fechaFin, semestre, anio, Equipo_id) VALUES ('"+ proyecto.getIdProyecto() +"', '"+proyecto.getNombreProyecto()+"', '"+proyecto.getFechaInicio()+"', '"+ proyecto.getFechaFin() +"', '"+proyecto.getSemestre() + "', "+proyecto.getAnio()  + ",'" +id+ "')" ;
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){	
			Statement instruccion = miConexion.createStatement();
			int resultado=instruccion.executeUpdate(sentencia);			
			miFachada.desconectar(miConexion);			
		}
	}

	/**
	 * Elimina un Proyecto de la DB
	 * @param proyecto, proyecto != null
	 * @param id, id !0 null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void eliminar(Proyecto proyecto, String id) throws ClassNotFoundException, SQLException{
		
		String sqlGuardar = "delete from proyecto where Equipo_id =  '" + id +"'  and   id = '" + proyecto.getIdProyecto()+"'";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado = instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * Modifica un Proyecto de la DB
	 * @param proyecto, proyecto != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modificar(Proyecto proyecto) throws ClassNotFoundException, SQLException{
		String sqlGuardar = "UPDATE proyecto SET nombre='" + proyecto.getNombreProyecto()+ "',semestre='" + proyecto.getSemestre() +"',fechaInicio='" + proyecto.getFechaInicio() + "',fechaFin='" + proyecto.getFechaFin() +"',anio=" + proyecto.getAnio() +" where id='"+proyecto.getIdProyecto()+"';";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado = instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * Lista los Proyectos de la DB
	 * @param id, id != null
	 * @return proyectos, retorna una lista de los proyectos de la DB
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList listar(String id) throws ClassNotFoundException, SQLException{
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
		String sqlConsultar = "select id , nombre , fechaInicio, fechaFin , semestre ,anio from proyecto where equipo_id='"+id+"'";
		Fachada miFachada = new Fachada() ;
		
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			ResultSet tabla = instruccion.executeQuery(sqlConsultar);
			while(tabla.next()){
				Proyecto miProyecto= new Proyecto(tabla.getString("nombre"), tabla.getString("fechaInicio"), tabla.getString("fechaFin"), tabla.getString("semestre"), tabla.getInt("anio"), tabla.getString("id"));
				proyectos.add(miProyecto);				
			}
			miFachada.desconectar(miConexion);
		}
		return proyectos;
	}
}