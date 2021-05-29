package co.edu.uco.sudoku.negocio.negocio.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.SudokuEnsambladorImpl.obtenerSudokuEnsablador;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.dominio.SudokuDominio;
import co.edu.uco.sudoku.negocio.negocio.SudokuNegocio;;

public class SudokuNegocioImpl implements SudokuNegocio {

	private SudokuDatosFactoria sudokuDatos;

	public SudokuNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.sudokuDatos = factoriaDatos;
	}

	@Override
	public List<SudokuDominio> consultar(SudokuEntidad entidad) {
		return obtenerSudokuEnsablador()
				.ensamblarDominiosDesdeEntidad(sudokuDatos.obtenerSudokuDatos().consultar(entidad));
	}

	@Override
	public void registrar(SudokuDominio sudoku) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarRegiones(List<RegionDominio> regiones) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RegionDominio> obtenerRegiones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RegionDominio> obtenerRegionDeterminada(int codigoSudoku, PosicionDominio posicionRegion) {
		// TODO Auto-generated method stub
		return null;
	}

	public void ValidarSudokusRepetidos() {
		// TODO
	}

}
