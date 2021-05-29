package co.edu.uco.sudoku.negocio.validador.regla.concreta.posicion;

import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class FilaPosicionValidarRegla implements Regla<PosicionDominio> {

	private static final Regla<PosicionDominio> INSTANCIA = new FilaPosicionValidarRegla();

	private FilaPosicionValidarRegla() {
		super();
	}

	public static Regla<PosicionDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PosicionDominio dato) {
		if (!UtilNumerico.numeroEstaEntre(dato.getFila(), 1, 3)) {
			throw new SudokuDominioExepcion("La posicion de una fila solo puede estar entre 1 y 3.");
		}
	}

}
