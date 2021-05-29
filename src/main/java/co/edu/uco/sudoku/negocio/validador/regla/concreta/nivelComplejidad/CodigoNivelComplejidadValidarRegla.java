package co.edu.uco.sudoku.negocio.validador.regla.concreta.nivelComplejidad;

import co.edu.uco.sudoku.dominio.NivelComplejidadDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class CodigoNivelComplejidadValidarRegla implements Regla<NivelComplejidadDominio> {

	private static final Regla<NivelComplejidadDominio> INSTANCIA = new CodigoNivelComplejidadValidarRegla();

	private CodigoNivelComplejidadValidarRegla() {
		super();
	}

	public static Regla<NivelComplejidadDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(NivelComplejidadDominio dato) {
		if (UtilNumerico.numeroEsMenorOIgual(dato.getCodigo(), 0)) {
			throw new SudokuDominioExepcion("El codigo de un nivel de complejidad tuene que ser mayor a 0");
		}
	}

}
