package co.edu.uco.sudoku.negocio.validador.regla.concreta.celda;

import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class NumeroCeldaValidarRegla implements Regla<CeldaDominio> {

	private static final Regla<CeldaDominio> INSTANCIA = new NumeroCeldaValidarRegla();

	private NumeroCeldaValidarRegla() {
		super();
	}

	public static Regla<CeldaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(CeldaDominio dato) {
		if (!UtilNumerico.numeroEstaEntre(dato.getNumero(), 1, 9)) {
			throw new SudokuDominioExepcion("La celda solo puede contener numeros entre 1 y 9");
		}
	}

}
