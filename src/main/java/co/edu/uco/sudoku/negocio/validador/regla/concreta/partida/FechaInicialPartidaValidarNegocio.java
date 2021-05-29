package co.edu.uco.sudoku.negocio.validador.regla.concreta.partida;

import co.edu.uco.sudoku.dominio.PartidaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class FechaInicialPartidaValidarNegocio implements Regla<PartidaDominio> {

	private static final Regla<PartidaDominio> INSTANCIA = new FechaInicialPartidaValidarNegocio();

	private FechaInicialPartidaValidarNegocio() {
		super();
	}

	public static Regla<PartidaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PartidaDominio dato) {
		if (UtilObjeto.esNulo(dato.getFechaInicial())) {
			throw new SudokuDominioExepcion("La fecha inicial no puede ser nula");
		}
	}

}
