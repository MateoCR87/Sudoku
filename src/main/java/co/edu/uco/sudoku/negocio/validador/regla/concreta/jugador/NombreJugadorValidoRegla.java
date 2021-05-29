package co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador;

import co.edu.uco.sudoku.dominio.JugadorDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class NombreJugadorValidoRegla implements Regla<JugadorDominio> {

	private static final Regla<JugadorDominio> INSTANCIA = new NombreJugadorValidoRegla();

	private NombreJugadorValidoRegla() {
		super();
	}

	public static Regla<JugadorDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(JugadorDominio dato) {
		if (UtilTexto.cadenaEstaVacia(dato.getNombre())) {
			throw new SudokuDominioExepcion("El nombre no es valido.");
		}

		if (!UtilTexto.cadenaContieneSoloLetrasYEspacios(dato.getNombre())) {
			throw new SudokuDominioExepcion("El nombre solo puede tener letras y espacios.");
		}

		if (!UtilTexto.longitudMaximaEsValida(dato.getNombre(), 100)) {
			throw new SudokuDominioExepcion("El nombre solo puede conetener 100 caracteres.");
		}

	}

}
