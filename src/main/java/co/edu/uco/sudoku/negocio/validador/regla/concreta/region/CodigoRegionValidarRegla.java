package co.edu.uco.sudoku.negocio.validador.regla.concreta.region;

import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class CodigoRegionValidarRegla implements Regla<RegionDominio> {

	private static final Regla<RegionDominio> INSTANCIA = new CodigoRegionValidarRegla();

	private CodigoRegionValidarRegla() {
		super();
	}

	public static Regla<RegionDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(RegionDominio dato) {
		if (UtilNumerico.numeroEsMayor(dato.getCodigo(), 0)) {
			throw new SudokuDominioExepcion("El codigo de la region debe ser mayor que 0.");
		}
	}

}
