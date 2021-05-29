package co.edu.uco.sudoku.negocio.validador.regla.concreta.modalidadJuego;

import co.edu.uco.sudoku.dominio.ModalidadJuegoDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class NombreModalidadJuegoValidarRegla implements Regla<ModalidadJuegoDominio> {

	private static final Regla<ModalidadJuegoDominio> INSTANCIA = new NombreModalidadJuegoValidarRegla();

	private NombreModalidadJuegoValidarRegla() {
		super();
	}

	public static Regla<ModalidadJuegoDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(ModalidadJuegoDominio dato) {
		if (UtilTexto.cadenaEstaVacia(dato.getNombre())) {
			throw new SudokuDominioExepcion("El nombre de una modalidad de juego no puede estar vaciá.");
		}

		if (UtilTexto.longitudEsValida(dato.getNombre(), 1, 50)) {
			throw new SudokuDominioExepcion(
					"El nombre de una modalidad de juego debe tener minimo una caracter y maximo 50 caracteres.");
		}

		if (UtilTexto.cadenaContieneSoloLetrasYEspacios(dato.getNombre())) {
			throw new SudokuDominioExepcion("El nombre solo puede contener letras o espacios.");
		}
	}

}
