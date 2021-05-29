package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.dominio.SudokuDominio;

public interface SudokuNegocio {

	List<SudokuDominio> consultar(SudokuEntidad entidad);

	void registrar(SudokuDominio sudoku);

	void registrarRegiones(List<RegionDominio> regiones);

	List<RegionDominio> obtenerRegiones();

	Optional<RegionDominio> obtenerRegionDeterminada(int codigoSudoku, PosicionDominio posicionRegion);
}
