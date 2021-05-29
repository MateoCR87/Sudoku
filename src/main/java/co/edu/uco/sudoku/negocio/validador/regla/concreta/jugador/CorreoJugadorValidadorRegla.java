package co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador;

import co.edu.uco.sudoku.dominio.JugadorDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class CorreoJugadorValidadorRegla implements Regla<JugadorDominio> {

	private static final Regla<JugadorDominio> INSTANCIA = new CorreoJugadorValidadorRegla();

	private CorreoJugadorValidadorRegla() {
		super();
	}

	public static Regla<JugadorDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(JugadorDominio dato) {
		if (!UtilTexto.cadenaContieneCorreoElectronico(dato.getCorreo())) {
			throw new SudokuDominioExepcion("El correo electronico no es valido.");
		}

		if (!UtilTexto.longitudMaximaEsValida(dato.getCorreo(), 250)) {
			throw new SudokuDominioExepcion("El correo puede tener como maximo 250 caracteres.");
		}

	}

}
