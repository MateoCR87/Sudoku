package co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador;

import co.edu.uco.sudoku.dominio.JugadorDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class CodigoJugadorValidoRegla implements Regla<JugadorDominio> {

	private static final Regla<JugadorDominio> INSTANCIA = new CodigoJugadorValidoRegla();

	private CodigoJugadorValidoRegla() {
		super();
	}

	public static Regla<JugadorDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(JugadorDominio dato) {
		if (UtilNumerico.numeroEsMenorOIgual(dato.getCodigo(), 0)) {
			throw new SudokuDominioExepcion("El codigo de un jugador tiene que ser mayor que 0.");
		}
	}

}
