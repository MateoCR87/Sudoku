package co.edu.uco.sudoku.negocio.validador.regla.concreta.celda;

import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class CodigoCeldaValidarRegla implements Regla<CeldaDominio> {

	private static final Regla<CeldaDominio> INSTANCIA = new CodigoCeldaValidarRegla();

	private CodigoCeldaValidarRegla() {
		super();
	}

	public static Regla<CeldaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(CeldaDominio dato) {
		if (UtilNumerico.numeroEsMenorOIgual(dato.getCodigo(), 0)) {
			throw new SudokuDominioExepcion("El codigo de una celda tiene que ser mayor que 0.");
		}
	}

}
