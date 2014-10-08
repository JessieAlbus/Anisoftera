package umariana.anisoftera.datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import umariana.anisoftera.mundo.Ciclo;
import umariana.anisoftera.mundo.Proyecto;

public class CicloDAO {

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
	 * 
	 */
	public CicloDAO(){
		fachada = new Fachada() ;
	}

	/**
	 * Agrega un ciclo a la DB
	 * @param ciclo, ciclo = 0
	 * @param proyecto, proyecto != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void agregar(Ciclo ciclo, String proyecto) throws ClassNotFoundException, SQLException{
		String sentencia ="insert into ciclo(ciclo,Proyecto_id) value (" + ciclo.getNumeroCiclo() + ","  + proyecto + ") " ;
		sentencia = "INSERT INTO ciclo (numero, Proyecto_id, ciclo) VALUES ('"+ciclo.getId()+"', '"+proyecto+"', "+ciclo.getNumeroCiclo()+")";
		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();	
		
		if(miConexion != null){	
			Statement instruccion = miConexion.createStatement();
			int resultado=instruccion.executeUpdate(sentencia);			
			miFachada.desconectar(miConexion);			
		}
	}

	/**
	 * Modifica el ciclo de la DB
	 * @param ciclo, ciclo = 0
	 * @param id, id != null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void eliminar(Ciclo ciclo,String id) throws ClassNotFoundException, SQLException{
		String sqlGuardar = sqlGuardar= "DELETE FROM ciclo WHERE numero='"+ciclo.getId()+"' AND Proyecto_id='"+id+"'";
		
		Connection miConexion = fachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			int resultado = instruccion.executeUpdate(sqlGuardar);
			fachada.desconectar(miConexion);
		}
	}

	/**
	 * 
	 * @param ciclo
	 */
	public void modificar(Ciclo ciclo){
	}

	/**
	 * Lista los ciclos que se encuentran en la DB
	 * @param id, id != null
	 * @return ciclos, retorna los ciclos que se encuentran en la DB
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList listar(String id) throws ClassNotFoundException, SQLException{
		ArrayList<Ciclo> ciclos = new ArrayList<Ciclo>();
		String sqlConsultar = "select ciclo, numero from ciclo where Proyecto_id ='" + id+"'";

		Fachada miFachada = new Fachada() ;
		Connection miConexion = miFachada.conectar();
		if(miConexion != null){
			Statement instruccion = miConexion.createStatement();
			ResultSet tabla = instruccion.executeQuery(sqlConsultar);
			while(tabla.next()){
				Ciclo miCiclo= new Ciclo(tabla.getInt("ciclo"), tabla.getString("numero")) ;
				ciclos.add(miCiclo);				
			}
			miFachada.desconectar(miConexion);
		}
		return ciclos;
	}
}