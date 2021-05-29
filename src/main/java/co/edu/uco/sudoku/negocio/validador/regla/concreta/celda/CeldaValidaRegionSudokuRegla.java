package co.edu.uco.sudoku.negocio.validador.regla.concreta.celda;

import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;

public class CeldaValidaRegionSudokuRegla implements Regla<CeldaDominio> {

	private static final Regla<CeldaDominio> INSTANCIA = new CeldaValidaRegionSudokuRegla();

	private CeldaValidaRegionSudokuRegla() {
		super();
	}

	public static Regla<CeldaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(CeldaDominio dato) {
		validarCeldaNoEsPista(dato);
	}

	private void validarCeldaNoEsPista(CeldaDominio dato) {
		if (!dato.isEsPista()) {
			throw new SudokuDominioExepcion(
					"La celda asociada asociada a la region del sudoku tiene que estar asociada como una pista, todas las celdas de la region de un sudoku tienen que estar diligenciadas");
		}
	}
}
