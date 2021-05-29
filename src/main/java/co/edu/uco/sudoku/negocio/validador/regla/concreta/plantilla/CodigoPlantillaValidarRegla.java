package co.edu.uco.sudoku.negocio.validador.regla.concreta.plantilla;

import co.edu.uco.sudoku.dominio.PlantillaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;

public class CodigoPlantillaValidarRegla implements Regla<PlantillaDominio> {

	private static final Regla<PlantillaDominio> INSTANCIA = new CodigoPlantillaValidarRegla();

	private CodigoPlantillaValidarRegla() {
		super();
	}

	public static Regla<PlantillaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PlantillaDominio dato) {
		if (UtilNumerico.numeroEsMenorOIgual(dato.getCodigo(), 0)) {
			throw new SudokuDominioExepcion("El codigo de una plantilla tiene que ser mayor que 0.");
		}
	}

}
