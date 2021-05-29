package co.edu.uco.sudoku.negocio.validador.regla.concreta.partida;

import co.edu.uco.sudoku.dominio.PartidaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class JuegoCompletadoPartidaValidarNegocio implements Regla<PartidaDominio> {

	private static final Regla<PartidaDominio> INSTANCIA = new JuegoCompletadoPartidaValidarNegocio();

	private JuegoCompletadoPartidaValidarNegocio() {
		super();
	}

	public static Regla<PartidaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PartidaDominio dato) {
		if (UtilObjeto.esNulo(dato.isJuegoCompletado())) {
			throw new SudokuDominioExepcion("La informacion del juago completado no puede ser nula");
		}
	}

}
