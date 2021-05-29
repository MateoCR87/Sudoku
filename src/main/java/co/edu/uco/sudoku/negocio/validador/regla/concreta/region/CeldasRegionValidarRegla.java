package co.edu.uco.sudoku.negocio.validador.regla.concreta.region;

import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class CeldasRegionValidarRegla implements Regla<RegionDominio> {

	private static final Regla<RegionDominio> INSTANCIA = new CeldasRegionValidarRegla();

	private CeldasRegionValidarRegla() {
		super();
	}

	public static Regla<RegionDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(RegionDominio dato) {
		if (UtilObjeto.esNulo(dato.getCeldas())) {
			throw new SudokuDominioExepcion("La matriz de celdas en la region no pueden estar vacias.");
		}
	}

}
