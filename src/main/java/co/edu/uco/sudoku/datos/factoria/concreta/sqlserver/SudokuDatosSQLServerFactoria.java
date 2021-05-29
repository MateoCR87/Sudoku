package co.edu.uco.sudoku.datos.factoria.concreta.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.sudoku.datos.CeldaDatos;
import co.edu.uco.sudoku.datos.JugadorDatos;
import co.edu.uco.sudoku.datos.ModalidadJuegoDatos;
import co.edu.uco.sudoku.datos.NivelComplejidadDatos;
import co.edu.uco.sudoku.datos.PartidaDatos;
import co.edu.uco.sudoku.datos.PlantillaDatos;
import co.edu.uco.sudoku.datos.PosicionDatos;
import co.edu.uco.sudoku.datos.RegionDatos;
import co.edu.uco.sudoku.datos.SudokuDatos;
import co.edu.uco.sudoku.datos.concreta.sqlserver.CeldaDatosSQLServer;
import co.edu.uco.sudoku.datos.concreta.sqlserver.JugadorDatosSQLServer;
import co.edu.uco.sudoku.datos.concreta.sqlserver.ModalidadJuegoDatosSQLServer;
import co.edu.uco.sudoku.datos.concreta.sqlserver.NivelComplajidadDatosSQLServer;
import co.edu.uco.sudoku.datos.concreta.sqlserver.PartidaDatosSQLServer;
import co.edu.uco.sudoku.datos.concreta.sqlserver.PlantillaDatosSQLServer;
import co.edu.uco.sudoku.datos.concreta.sqlserver.PosicionDatosSQLServer;
import co.edu.uco.sudoku.datos.concreta.sqlserver.RegionDatosSQLServer;
import co.edu.uco.sudoku.datos.concreta.sqlserver.SudokuDatosSQLServer;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;

public class SudokuDatosSQLServerFactoria extends SudokuDatosFactoria {

	private Connection conexion;
	
	public SudokuDatosSQLServerFactoria() {
		abrirConeccion();
	}
	
	@Override
	public void abrirConeccion() {
		if(UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No se puede abrir una conexion que ya se encuentra en ese estado.");
		}
		try {
			String connectionUrl =
	                "jdbc:sqlserver://wfsg202002.database.windows.net:1433;"
	                        + "database=db_sudoku;"
	                        + "user=wfsg;"
	                        + "password=1234567890SUDOKU.;"
	                        + "encrypt=true;"
	                        + "trustServerCertificate=false;"
	                        + "loginTimeout=30;";
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver" );
			conexion = DriverManager.getConnection(connectionUrl);
		} catch (SQLException  exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de obtener la conexion con la fuente de datos...");
		} catch(ClassNotFoundException exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de obtener la conexion con la fuente de datos devido a que no fue encontrado el driver de coneccion");
		} catch(Exception exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de obtener la conexion con la fuente de datos devido a que se ha presentado unproblema no controlado");
		}
	}

	@Override
	public void cerrarConeccion() {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No se puede cerrar una conexion que ya se encuentra en ese estado.");
		}
		try {
			conexion.close();
		} catch (SQLException  exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de cerrar la conexion con la fuente de datos...");
		}  catch(Exception exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de cerrar la conexion con la fuente de datos devido a que se ha presentado unproblema no controlado");
		}
	}

	@Override
	public void iniciarTransaccion() {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible iniciar una  transaccion con una coneccion cerrada.");
		}
		try {
			conexion.setAutoCommit(false);
		} catch (SQLException  exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de configurar el inicio de una transaccion con la fuente de datos.");
		}  catch(Exception exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de configurar el inicio de una transaccion con la fuente de datos cerrar la conexion con la fuente de datos ");
		}

	}

	@Override
	public void cancelarTransaccion() {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible cancelar una  transaccion con una coneccion cerrada.");
		}
		try {
			conexion.rollback();
		} catch (SQLException  exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de cancelar una transaccion con la fuente de datos.");
		}  catch(Exception exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de cancelar una transaccion con la fuente de datos cerrar la conexion con la fuente de datos ");
		}

	}

	@Override
	public void confirmarTransaccion() {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible confirmar una  transaccion con una coneccion cerrada.");
		}
		try {
			conexion.commit();
		} catch (SQLException  exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de confirmar una transaccion con la fuente de datos.");
		}  catch(Exception exeption) {
			throw new SudokuDatosExepcion("Se ha presentado un problema, tratando de confirmar una transaccion con la fuente de datos cerrar la conexion con la fuente de datos ");
		}

	}

	@Override
	public CeldaDatos obtenerCeldaDatos() {
		return new CeldaDatosSQLServer(conexion);
	}

	@Override
	public JugadorDatos obtenerJugadorDatos() {
		return new JugadorDatosSQLServer(conexion);
	}

	@Override
	public ModalidadJuegoDatos obtenerModalidadJuegoDatos() {
		return new ModalidadJuegoDatosSQLServer(conexion);
	}

	@Override
	public NivelComplejidadDatos obtenerNivelComplejidadDatos() {
		return new NivelComplajidadDatosSQLServer(conexion);
	}

	@Override
	public PartidaDatos obtenerPartidaDatos() {
		return new PartidaDatosSQLServer(conexion);
	}

	@Override
	public PlantillaDatos obtenerPlantillaDatos() {
		return new PlantillaDatosSQLServer(conexion);
	}

	@Override
	public PosicionDatos obtenerPosicionDatos() {
		return new PosicionDatosSQLServer(conexion);
	}

	@Override
	public RegionDatos obtenerRegionDatos() {
		return new RegionDatosSQLServer(conexion);
	}

	@Override
	public SudokuDatos obtenerSudokuDatos() {
		return new SudokuDatosSQLServer(conexion);
	}

}
