package co.edu.uco.sudoku.negocio.validador.regla.concreta.region;

import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class PosicionRegionValidarRegla implements Regla<RegionDominio> {

	private static final Regla<RegionDominio> INSTANCIA = new PosicionRegionValidarRegla();

	private PosicionRegionValidarRegla() {
		super();
	}

	public static Regla<RegionDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(RegionDominio dato) {
		if (UtilObjeto.esNulo(dato.getPosicion())) {
			throw new SudokuDominioExepcion("La posicion de la region no puede ser nula.");
		}
	}

}
