package co.edu.uco.sudoku.transversal.utilitario;

import java.sql.Connection;
import java.sql.SQLException;

public final class UtilSQL {

	private UtilSQL() {
	}

	public static boolean conexionEstaAbierta(Connection conexion ) {
		
		boolean conexionEstaAbierta = true;
		
		try {
			conexionEstaAbierta =  !UtilObjeto.esNulo(conexion) && !conexion.isClosed();
		} catch (SQLException exepcion) {
			conexionEstaAbierta = false;
		}
		return conexionEstaAbierta;
	}
	
}
