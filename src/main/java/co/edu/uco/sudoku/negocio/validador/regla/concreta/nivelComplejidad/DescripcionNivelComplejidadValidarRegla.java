package co.edu.uco.sudoku.negocio.validador.regla.concreta.nivelComplejidad;

import co.edu.uco.sudoku.dominio.NivelComplejidadDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class DescripcionNivelComplejidadValidarRegla implements Regla<NivelComplejidadDominio> {

	private static final Regla<NivelComplejidadDominio> INSTANCIA = new DescripcionNivelComplejidadValidarRegla();

	private DescripcionNivelComplejidadValidarRegla() {
		super();
	}

	public static Regla<NivelComplejidadDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(NivelComplejidadDominio dato) {
		if (UtilTexto.cadenaEstaVacia(dato.getDescripcion())) {
			throw new SudokuDominioExepcion("La descripcion del juego no puede estar vaciá.");
		}

		if (!UtilTexto.cadenaDescripcion(dato.getDescripcion())) {
			throw new SudokuDominioExepcion("Hay caracteres invalidos en la descripcion.");
		}

		if (!UtilTexto.longitudMaximaEsValida(dato.getDescripcion(), 4000)) {
			throw new SudokuDominioExepcion("La descripcion del nivel de complejidad solo puede tener 4000 caracteres");
		}
	}

}
