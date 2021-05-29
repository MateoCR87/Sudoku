package co.edu.uco.sudoku.negocio.validador.regla.concreta.modalidadJuego;

import co.edu.uco.sudoku.dominio.ModalidadJuegoDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class CodigoModalidadJuegoValidarRegla implements Regla<ModalidadJuegoDominio> {

	private static final Regla<ModalidadJuegoDominio> INSTANCIA = new CodigoModalidadJuegoValidarRegla();

	private CodigoModalidadJuegoValidarRegla() {
		super();
	}

	public static Regla<ModalidadJuegoDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(ModalidadJuegoDominio dato) {
		if (!UtilNumerico.numeroEsMayor(dato.getCodigo(), 0)) {
			throw new SudokuDominioExepcion("El codigo de la modalidad tiene que ser mayor a cero.");
		}
	}

}
