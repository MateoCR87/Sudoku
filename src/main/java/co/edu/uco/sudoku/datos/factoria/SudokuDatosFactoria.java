package co.edu.uco.sudoku.datos.factoria;

import co.edu.uco.sudoku.datos.CeldaDatos;
import co.edu.uco.sudoku.datos.JugadorDatos;
import co.edu.uco.sudoku.datos.ModalidadJuegoDatos;
import co.edu.uco.sudoku.datos.NivelComplejidadDatos;
import co.edu.uco.sudoku.datos.PartidaDatos;
import co.edu.uco.sudoku.datos.PlantillaDatos;
import co.edu.uco.sudoku.datos.PosicionDatos;
import co.edu.uco.sudoku.datos.RegionDatos;
import co.edu.uco.sudoku.datos.SudokuDatos;
import co.edu.uco.sudoku.datos.factoria.concreta.archivo.SudokuDatosArchivoFactoria;
import co.edu.uco.sudoku.datos.factoria.concreta.memoria.SudokuDatosMemoriaFactoria;
import co.edu.uco.sudoku.datos.factoria.concreta.mysql.SudokuDatosMySQLFactoria;
import co.edu.uco.sudoku.datos.factoria.concreta.sqlserver.SudokuDatosSQLServerFactoria;
import co.edu.uco.sudoku.datos.factoria.enumerador.Factoria;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;

public abstract class SudokuDatosFactoria {

	private static final Factoria FACTORIA_ACTUAL = Factoria.SQL_SERVER;

	public static SudokuDatosFactoria obtenerFactoria() {

		SudokuDatosFactoria factoria = null;

		switch (FACTORIA_ACTUAL) {
		case MEMORIA:
			factoria = new SudokuDatosMemoriaFactoria();
			break;

		case ARCHIVO:
			factoria = new SudokuDatosArchivoFactoria();
			break;

		case MYSQL:
			factoria = new SudokuDatosMySQLFactoria();
			break;
			
		case SQL_SERVER:
			factoria = new SudokuDatosSQLServerFactoria();
			break;

		default:
			throw new SudokuDatosExepcion("La factoiria de acceso a datos que se intenta crear no exixte");
		}

		return factoria;
	}

	public abstract void abrirConeccion();

	public abstract void cerrarConeccion();

	public abstract void iniciarTransaccion();

	public abstract void cancelarTransaccion();

	public abstract void confirmarTransaccion();

	public abstract CeldaDatos obtenerCeldaDatos();

	public abstract JugadorDatos obtenerJugadorDatos();

	public abstract ModalidadJuegoDatos obtenerModalidadJuegoDatos();

	public abstract NivelComplejidadDatos obtenerNivelComplejidadDatos();

	public abstract PartidaDatos obtenerPartidaDatos();

	public abstract PlantillaDatos obtenerPlantillaDatos();

	public abstract PosicionDatos obtenerPosicionDatos();

	public abstract RegionDatos obtenerRegionDatos();

	public abstract SudokuDatos obtenerSudokuDatos();

}
