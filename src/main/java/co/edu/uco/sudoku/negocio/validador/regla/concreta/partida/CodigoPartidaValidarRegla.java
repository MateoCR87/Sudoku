package co.edu.uco.sudoku.negocio.validador.regla.concreta.partida;

import co.edu.uco.sudoku.dominio.PartidaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class CodigoPartidaValidarRegla implements Regla<PartidaDominio> {

	private static final Regla<PartidaDominio> INSTANCIA = new CodigoPartidaValidarRegla();

	private CodigoPartidaValidarRegla() {
		super();
	}

	public static Regla<PartidaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PartidaDominio dato) {
		if (UtilNumerico.numeroEsMenorOIgual(dato.getCodigo(), 0)) {
			throw new SudokuDominioExepcion("El codigo de una partida tiene que ser mayor que 0.");
		}
	}

}
