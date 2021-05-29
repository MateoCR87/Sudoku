package co.edu.uco.sudoku.negocio.validador.regla.concreta.partida;

import co.edu.uco.sudoku.dominio.PartidaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class DuracionPartidaValidarNegocio implements Regla<PartidaDominio> {

	private static final Regla<PartidaDominio> INSTANCIA = new DuracionPartidaValidarNegocio();

	private DuracionPartidaValidarNegocio() {
		super();
	}

	public static Regla<PartidaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PartidaDominio dato) {
		if (UtilNumerico.numeroEsMenorOIgual(dato.getDuracion(), 0)) {
			throw new SudokuDominioExepcion("La duracion de una partida tiene que ser  mayor a 0");
		}
	}

}
