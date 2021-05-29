package co.edu.uco.sudoku.negocio.validador.regla.concreta.plantilla;

import co.edu.uco.sudoku.dominio.PlantillaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class SudokuPlantillaValidarRegla implements Regla<PlantillaDominio> {

	private static final Regla<PlantillaDominio> INSTANCIA = new SudokuPlantillaValidarRegla();

	private SudokuPlantillaValidarRegla() {
		super();
	}

	public static Regla<PlantillaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(PlantillaDominio dato) {
		if (UtilObjeto.esNulo(dato.getSudoku())) {
			throw new SudokuDominioExepcion("El sudoku de la plantilla no puede estar vacio.");
		}
	}

}
