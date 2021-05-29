package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.datos.entidad.PlantillaEntidad;
import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.dominio.PlantillaDominio;
import co.edu.uco.sudoku.dominio.RegionDominio;

public interface PlantillaNegocio {

	List<PlantillaDominio> consultar(PlantillaEntidad entidad);

	void registrarSudokuConPistas(PlantillaDominio sudoku);

	void registrarRegiones(List<RegionDominio> regiones);

	Optional<RegionDominio> obtenerRegionSoloPistas(PlantillaEntidad plantilaEntidad, PosicionEntidad posicionEntidad);

	List<PlantillaDominio> organizarPorDificultad();

	boolean compararConSudoku(SudokuEntidad sudoku, PlantillaEntidad plantilla);
}
