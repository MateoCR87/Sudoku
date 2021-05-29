package co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador;

import co.edu.uco.sudoku.dominio.JugadorDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class ClaveJugadorValidadorRegla implements Regla<JugadorDominio> {

	private static final Regla<JugadorDominio> INSTANCIA = new ClaveJugadorValidadorRegla();

	private ClaveJugadorValidadorRegla() {
		super();
	}

	public static Regla<JugadorDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(JugadorDominio dato) {
		if (!UtilTexto.cadenaContieneUnDijito(dato.getClave())) {
			throw new SudokuDominioExepcion("La clave tiene que tener minimo un diito.");
		}

		if (!UtilTexto.cadenaContieneLetraMayuscula(dato.getClave())) {
			throw new SudokuDominioExepcion("La clave tiene que tener minimo una letra MAYUSCULA.");
		}

		if (!UtilTexto.cadenaContieneLetraMinuscula(dato.getClave())) {
			throw new SudokuDominioExepcion("La clave tiene que tener minimo un aletra minuscula.");
		}

		if (!UtilTexto.cadenaContieneUnSimbolo(dato.getClave())) {
			throw new SudokuDominioExepcion("La clave tiene que tener minimo un simbolo.");
		}

		if (!UtilTexto.longitudEsValida(dato.getClave(), 8, 100)) {
			throw new SudokuDominioExepcion("La clave tiene que tener minimo 8 caracteres y maximo 100.");
		}

	}

}
