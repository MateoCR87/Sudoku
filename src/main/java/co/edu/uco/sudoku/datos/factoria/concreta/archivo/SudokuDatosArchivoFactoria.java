package co.edu.uco.sudoku.datos.factoria.concreta.archivo;

import co.edu.uco.sudoku.datos.CeldaDatos;
import co.edu.uco.sudoku.datos.CeldaPorRegionDatos;
import co.edu.uco.sudoku.datos.JugadorDatos;
import co.edu.uco.sudoku.datos.ModalidadJuegoDatos;
import co.edu.uco.sudoku.datos.NivelComplejidadDatos;
import co.edu.uco.sudoku.datos.PartidaDatos;
import co.edu.uco.sudoku.datos.PlantillaDatos;
import co.edu.uco.sudoku.datos.PosicionDatos;
import co.edu.uco.sudoku.datos.RegionDatos;
import co.edu.uco.sudoku.datos.RegionPorPlantillaDatos;
import co.edu.uco.sudoku.datos.RegionPorSudokuDatos;
import co.edu.uco.sudoku.datos.SudokuDatos;
import co.edu.uco.sudoku.datos.concreta.archivo.CeldaDatosArchivo;
import co.edu.uco.sudoku.datos.concreta.archivo.JugadorDatosArchivo;
import co.edu.uco.sudoku.datos.concreta.archivo.ModalidadJuegoDatosArchivo;
import co.edu.uco.sudoku.datos.concreta.archivo.NivelComplejidadDatosArchivo;
import co.edu.uco.sudoku.datos.concreta.archivo.PartidaDatosArchvo;
import co.edu.uco.sudoku.datos.concreta.archivo.PlantillaDatosArchivo;
import co.edu.uco.sudoku.datos.concreta.archivo.PosicionDatosArchivo;
import co.edu.uco.sudoku.datos.concreta.archivo.RegionDatosArchivo;
import co.edu.uco.sudoku.datos.concreta.archivo.SudokuDatosArchivo;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;

public class SudokuDatosArchivoFactoria extends SudokuDatosFactoria {

	private static final String NOMBRE_CELDA_ARCHIVO = "C:\\Users\\mateo\\git\\Sudoku\\Celda.txt";

	private static final String NOMBRE_JUGADOR_ARCHIVO = "C:\\Users\\mateo\\git\\Sudoku\\Jugadores.txt";

	private static final String NOMBRE_ARCHIVO_MODALIDAD_JUEGO = "C:\\Users\\mateo\\git\\Sudoku\\ModalidaJuego.txt";

	private static final String NOMBRE_ARCHIVO_NIVEL_COMPLEJIDAD = "C:\\Users\\mateo\\git\\Sudoku\\NivelDeComplejidad.txt";

	private static final String NOMBRE_PARTIDA_ARCHIVO = "C:\\Users\\mateo\\git\\Sudoku\\Partidas.txt";

	private static final String NOMBRE_PLANTILLA_ARCHIVO = "C:\\Users\\mateo\\git\\Sudoku\\Plantilla.txt";

	private static final String NOMBRE_ARCHIVO_POSICION = "C:\\Users\\mateo\\git\\Sudoku\\Posiciones.txt";

	private static final String NOMBRE_ARCHIVO_REGIONES = "C:\\Users\\mateo\\git\\Sudoku\\Regiones.txt";

	private static final String NOMBRE_SUDOKU_ARCHIVO = "C:\\Users\\mateo\\git\\Sudoku\\Sudokus.txt";

	@Override
	public void abrirConeccion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cerrarConeccion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void iniciarTransaccion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelarTransaccion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void confirmarTransaccion() {
		// TODO Auto-generated method stub

	}

	@Override
	public CeldaDatos obtenerCeldaDatos() {
		return new CeldaDatosArchivo(NOMBRE_CELDA_ARCHIVO);
	}

	@Override
	public JugadorDatos obtenerJugadorDatos() {
		return new JugadorDatosArchivo(NOMBRE_JUGADOR_ARCHIVO);
	}

	@Override
	public ModalidadJuegoDatos obtenerModalidadJuegoDatos() {
		return new ModalidadJuegoDatosArchivo(NOMBRE_ARCHIVO_MODALIDAD_JUEGO);
	}

	@Override
	public NivelComplejidadDatos obtenerNivelComplejidadDatos() {
		return new NivelComplejidadDatosArchivo(NOMBRE_ARCHIVO_NIVEL_COMPLEJIDAD);
	}

	@Override
	public PartidaDatos obtenerPartidaDatos() {
		return new PartidaDatosArchvo(NOMBRE_PARTIDA_ARCHIVO);
	}

	@Override
	public PlantillaDatos obtenerPlantillaDatos() {
		return new PlantillaDatosArchivo(NOMBRE_PLANTILLA_ARCHIVO);
	}

	@Override
	public PosicionDatos obtenerPosicionDatos() {
		return new PosicionDatosArchivo(NOMBRE_ARCHIVO_POSICION);
	}

	@Override
	public RegionDatos obtenerRegionDatos() {
		return new RegionDatosArchivo(NOMBRE_ARCHIVO_REGIONES);
	}

	@Override
	public SudokuDatos obtenerSudokuDatos() {
		return new SudokuDatosArchivo(NOMBRE_SUDOKU_ARCHIVO);
	}

	@Override
	public CeldaPorRegionDatos obtenerCeldaPorRegionDatos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegionPorPlantillaDatos obtenerRegionPorPlantillaDatos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegionPorSudokuDatos obtenerRegionPorSudokuDatos() {
		// TODO Auto-generated method stub
		return null;
	}

}
