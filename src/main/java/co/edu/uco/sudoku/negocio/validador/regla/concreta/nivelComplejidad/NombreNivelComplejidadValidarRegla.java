package co.edu.uco.sudoku.negocio.validador.regla.concreta.nivelComplejidad;

import co.edu.uco.sudoku.dominio.NivelComplejidadDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class NombreNivelComplejidadValidarRegla implements Regla<NivelComplejidadDominio> {

	private static final Regla<NivelComplejidadDominio> INSTANCIA = new NombreNivelComplejidadValidarRegla();

	private NombreNivelComplejidadValidarRegla() {
		super();
	}

	public static Regla<NivelComplejidadDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(NivelComplejidadDominio dato) {
		if (UtilTexto.cadenaEstaVacia(dato.getNombre())) {
			throw new SudokuDominioExepcion("El nombre de una modalidad de juego no puede estar vaciá.");
		}

		if (!UtilTexto.cadenaContieneSoloLetrasYEspacios(dato.getNombre())) {
			throw new SudokuDominioExepcion("El nombre del nivel de complejidada solo puede contener numeros y letras");
		}

		if (!UtilTexto.longitudMaximaEsValida(dato.getNombre(), 250)) {
			throw new SudokuDominioExepcion("El nombre del nivel de complejidad solo puede tener 250 caracteres");
		}
	}

}
