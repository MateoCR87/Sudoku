package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.dto.PosicionDTO;
import co.edu.uco.sudoku.dto.RegionDTO;
import co.edu.uco.sudoku.dto.SudokuDTO;

public interface SudokuFachada {

	List<SudokuDTO> consultar(SudokuDTO sudokuDTO);

	void registrar(SudokuDTO sudokuDTO);

	void registrarRegiones(List<RegionDTO> regionesDTO);

	List<RegionDTO> obtenerRegiones();

	Optional<RegionDTO> obtenerRegionDeterminada(int codigoSudoku, PosicionDTO posicionRegionDTO);
}
