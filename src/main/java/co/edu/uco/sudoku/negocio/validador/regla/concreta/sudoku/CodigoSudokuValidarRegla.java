package co.edu.uco.sudoku.negocio.validador.regla.concreta.sudoku;

import co.edu.uco.sudoku.dominio.SudokuDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class CodigoSudokuValidarRegla implements Regla<SudokuDominio> {

	private static final Regla<SudokuDominio> INSTANCIA = new CodigoSudokuValidarRegla();

	private CodigoSudokuValidarRegla() {
		super();
	}

	public static Regla<SudokuDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(SudokuDominio dato) {
		if (UtilNumerico.numeroEsMenorOIgual(dato.getCodigo(), 0)) {
			throw new SudokuDominioExepcion("El codigo del Sudoku tien que ser mayor que 0.");
		}
	}

}
