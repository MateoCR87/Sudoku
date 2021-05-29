package co.edu.uco.sudoku.negocio.validador.regla.concreta.partida;

import co.edu.uco.sudoku.dominio.PartidaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class PlantillaPartidaValidarNegocio implements Regla<PartidaDominio> {

	private static final Regla<PartidaDominio> INSTANCIA = new PlantillaPartidaValidarNegocio();

	private PlantillaPartidaValidarNegocio() {
		super();
	}

	public static Regla<PartidaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PartidaDominio dato) {
		if (UtilObjeto.esNulo(dato.getPlantilla())) {
			throw new SudokuDominioExepcion("La plantilla de una partida no npuede ser nula.");
		}
	}

}
