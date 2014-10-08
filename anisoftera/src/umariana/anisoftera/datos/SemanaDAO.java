package umariana.anisoftera.datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import umariana.anisoftera.mundo.Semana;

public class SemanaDAO {

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
	 * constructor de la clase SemanaDAO
	 */
	public SemanaDAO(){
		fachada = new Fachada() ;
	}

	/**
	 * Agrega una Semana a la DB
	 * @param semana, semana = 0
	 * @param ciclo, ciclo = 0
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void agregar(Semana semana, String ciclo) throws ClassNotFoundException, SQLException{
		String sentencia= "INSERT INTO semana (fechaInicio, fechaFin, numero, Ciclo_numero, semana ) VALUES ('"+semana.getFechaInicio() +"', '" +semana.getFechaFin() +"', '"+semana.getId() +"', '"+ciclo+"', "+semana.getSemanaNumero()+")" ;
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){	
			Statement instruccion = miConexion.createStatement();
			int resultado=instruccion.executeUpdate(sentencia);
			miFachada.desconectar(miConexion);			
		}		
	}

	/**
	 * Elimina la Semana de la DB
	 * @param semana, semana = 0
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void eliminar(Semana semana) throws ClassNotFoundException, SQLException{
		String sqlGuardar = "delete from semana where numero =" + semana.getSemanaNumero();
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado = instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * Modifica una Semana de la DB
	 * @param semana, semana = 0
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modificar(Semana semana) throws ClassNotFoundException, SQLException{
		String sqlGuardar = "UPDATE semana SET fechaInicio='" + semana.getFechaInicio()+ "',fechaFin='" + semana.getFechaFin() +"' where numero="+semana.getSemanaNumero()+";";
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado = instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * Lista las Semanas que se encuentran en la DB
	 * @param ciclo, ciclo = 0
	 * @return semanas, retorna las semanas encontradas en la DB
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList listar(String ciclo) throws ClassNotFoundException, SQLException{

		ArrayList<Semana> semanas = new ArrayList<Semana>();
		String sqlConsultar = "select numero , fechaInicio, fechaFin, semana from semana where Ciclo_numero ='" + ciclo+"'";
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			ResultSet tabla = instruccion.executeQuery(sqlConsultar);
			while(tabla.next()){
				Semana miSemana = new Semana(tabla.getInt("semana"), tabla.getString("fechaInicio"), tabla.getString("fechaFin"), tabla.getString("numero") ) ;
				semanas.add(miSemana) ;
			}
			miFachada.desconectar(miConexion);
		}
		return semanas;
	}
}