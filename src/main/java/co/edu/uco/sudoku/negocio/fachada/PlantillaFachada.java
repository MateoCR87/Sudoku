package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.dto.PlantillaDTO;
import co.edu.uco.sudoku.dto.PosicionDTO;
import co.edu.uco.sudoku.dto.RegionDTO;
import co.edu.uco.sudoku.dto.SudokuDTO;

public interface PlantillaFachada {

	List<PlantillaDTO> consultar(PlantillaDTO plantillaDTO);

	void registrarSudokuConPistas(SudokuDTO sudokuDTO);

	void registrarRegiones(List<RegionDTO> regionesDTO);

	Optional<RegionDTO> obtenerRegionSoloPistas(PlantillaDTO plantilaDTO, PosicionDTO posicionDTO);

	void organizarPorDificultad();

	boolean compararConSudoku(SudokuDTO sudokuDTO, PlantillaDTO plantillaDTO);
}
