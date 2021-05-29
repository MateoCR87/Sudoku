package co.edu.uco.sudoku.negocio.validador.regla.concreta.posicion;

import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class CodigoPosicionValidarRegla implements Regla<PosicionDominio> {

	private static final Regla<PosicionDominio> INSTANCIA = new CodigoPosicionValidarRegla();

	private CodigoPosicionValidarRegla() {
		super();
	}

	public static Regla<PosicionDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PosicionDominio dato) {
		if (!UtilNumerico.numeroEsMayorOIgual(dato.getCodigo(), 0)) {
			throw new SudokuDominioExepcion("El codigode la posicion tiene que ser mayor que 0.");
		}
	}

}
