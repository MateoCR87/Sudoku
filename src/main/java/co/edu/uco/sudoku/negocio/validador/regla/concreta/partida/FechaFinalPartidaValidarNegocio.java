package co.edu.uco.sudoku.negocio.validador.regla.concreta.partida;

import co.edu.uco.sudoku.dominio.PartidaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class FechaFinalPartidaValidarNegocio implements Regla<PartidaDominio> {

	private static final Regla<PartidaDominio> INSTANCIA = new FechaFinalPartidaValidarNegocio();

	private FechaFinalPartidaValidarNegocio() {
		super();
	}

	public static Regla<PartidaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PartidaDominio dato) {
		if (UtilObjeto.esNulo(dato.getFechaFinal())) {
			throw new SudokuDominioExepcion("La fecha final no puede ser nula");
		}
	}

}
