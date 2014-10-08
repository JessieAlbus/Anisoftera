package umariana.anisoftera.datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import umariana.anisoftera.mundo.Actividad;
import umariana.anisoftera.mundo.Fase;

public class ActividadDAO {

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
	 * Agrega una actividad a la DB
	 * @param actividad, actividad != null
	 * @param fase, fase != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void agregar(Actividad actividad, String fase) throws ClassNotFoundException, SQLException{

		String sentencia= "INSERT INTO actividad (nombre, estado, horas_planeadas, minutos_planeados, horas_utilizadas, minutos_utilizados, Fase_nombre) VALUES ('"+actividad.getNombre()+"', '"+actividad.getEstado()+"', "+actividad.getHorasPlaneadas()+", "+actividad.getMinutosPlaneados()+","+actividad.getHorasUtilizadas()+", "+actividad.getMinutosUtilizados()+", '"+fase+"')";
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){	
			Statement instruccion = miConexion.createStatement();
			int resultado=instruccion.executeUpdate(sentencia);
			miFachada.desconectar(miConexion);			
		}
	}

	/**
	 * Modifica una actividad de la DB
	 * @param actividad, != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modificar(Actividad actividad) throws ClassNotFoundException, SQLException{
		String sqlGuardar = " UPDATE actividad SET estado='"+actividad.getEstado()+"', horas_planeadas ="+actividad.getHorasPlaneadas()+", minutos_planeados="+actividad.getMinutosPlaneados()+", horas_utilizadas="+actividad.getHorasUtilizadas()+", minutos_utilizados="+actividad.getMinutosUtilizados()+", Fase_nombre='"+false+"' WHERE nombre='"+actividad.getNombre()+"'";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado = instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 */
	public void eliminar(){

	}

	/**
	 * 
	 * @param nombre, != null
	 * @return actividades, lista de las actividades encontradas en la DB
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList listar(String nombre) throws ClassNotFoundException, SQLException{
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		String sqlConsultar = "select nombre , estado, horas_planeadas, minutos_planeados, horas_utilizadas, minutos_utilizados from actividad where Fase_nombre= '"+nombre+"'";
		
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			ResultSet tabla = instruccion.executeQuery(sqlConsultar);
			while(tabla.next()){
				Actividad miActividad = new Actividad(tabla.getString("nombre"), tabla.getInt("horas_planeadas"), tabla.getInt("minutos_planeados")) ;
				actividades.add(miActividad) ;
			}
			miFachada.desconectar(miConexion);
		}
		return actividades;
	}
}
