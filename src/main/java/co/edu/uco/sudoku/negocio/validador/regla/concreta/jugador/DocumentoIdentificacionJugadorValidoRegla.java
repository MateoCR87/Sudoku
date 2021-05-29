package co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador;

import co.edu.uco.sudoku.dominio.JugadorDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class DocumentoIdentificacionJugadorValidoRegla implements Regla<JugadorDominio> {

	private static final Regla<JugadorDominio> INSTANCIA = new DocumentoIdentificacionJugadorValidoRegla();

	private DocumentoIdentificacionJugadorValidoRegla() {
		super();
	}

	public static Regla<JugadorDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(JugadorDominio dato) {

		if (!UtilTexto.longitudEsValida(String.valueOf(dato.getDocumentoIdentificacion()), 0, 50)) {
			throw new SudokuDominioExepcion("La identificacion debe tener entre 1 y 50 caracteres.");
		}

		if (!UtilNumerico.numeroEsMayor(dato.getDocumentoIdentificacion(), 0)) {
			throw new SudokuDominioExepcion("La identificacion debe ser mayor a 0.");
		}
	}

}
