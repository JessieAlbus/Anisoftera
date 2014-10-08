package umariana.anisoftera.datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import umariana.anisoftera.mundo.Fase;


public class FaseDAO {

	/**
	 * @uml.property  name="fachada"
	 * @uml.associationEnd  inverse="faseDAO:umariana.anisoftera.datos.Fachada"
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
	 */
	public FaseDAO(){
		fachada = new Fachada() ;
	}

	/**
	 * Agrega una fase a la DB
	 * @param fase, fase != null
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void agregar(Fase fase) throws SQLException, ClassNotFoundException{
		String sentencia= "INSERT INTO fase (nombre) VALUES ('"+fase.getNombre()+"')" ;
		
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){	
			Statement instruccion = miConexion.createStatement();
			int resultado=instruccion.executeUpdate(sentencia);
			miFachada.desconectar(miConexion);			
		}
	}

	/**
	 * ELimina una fase de la DB
	 * @param fase, fase != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void eliminar(Fase fase) throws ClassNotFoundException, SQLException{
		String sentencia= "DELETE FROM fase WHERE nombre='"+fase.getNombre()+"'" ;
		Connection miConexion = fachada.conectar();
		if(miConexion != null){	
			Statement instruccion = miConexion.createStatement();
			int resultado=instruccion.executeUpdate(sentencia);
			fachada.desconectar(miConexion);			
		}
	}

	/**
	 * Lista las fases de la DB
	 * @return fases, retorna una lista con las fases
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList listar() throws SQLException, ClassNotFoundException{
		ArrayList<Fase> fases = new ArrayList<Fase>();
		String sqlConsultar = "select nombre from fase";
		
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			ResultSet tabla = instruccion.executeQuery(sqlConsultar);
			while(tabla.next()){
				Fase miFase = new Fase(tabla.getString("nombre")) ;
				fases.add(miFase) ;
			}
			miFachada.desconectar(miConexion);
		}
		return fases;
	}
	
	/**
	 * 
	 * @param fases
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void asignarFases(String[] fases, String id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("ESTOY EN EL DAO _-------------------------------");
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		Statement instruccion = miConexion.createStatement();
		String sentencia = "";
		if(miConexion != null){	
			for (int i = 0; i < fases.length; i++) {
				sentencia= "INSERT INTO fase (nombre) VALUES ('"+fases[i]+"')" ;
				//instruccion.executeUpdate(sentencia);
				System.out.println("la sentencia deve estar aqui " + " " + id);
			}			
			miFachada.desconectar(miConexion);			
		}
	}
}