package co.edu.uco.sudoku.negocio.validador.regla.concreta.nivelComplejidad;

import co.edu.uco.sudoku.dominio.NivelComplejidadDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class TiempoNivelComplejidadValidarRegla implements Regla<NivelComplejidadDominio> {

	private static final Regla<NivelComplejidadDominio> INSTANCIA = new TiempoNivelComplejidadValidarRegla();

	private TiempoNivelComplejidadValidarRegla() {
		super();
	}

	public static Regla<NivelComplejidadDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(NivelComplejidadDominio dato) {
		if (!UtilNumerico.numeroEsMayor(dato.getTiempoLimiteSegundos(), 60)) {
			throw new SudokuDominioExepcion("El tiempo limite debe ser mayor a 60 segundos");
		}
	}

}
