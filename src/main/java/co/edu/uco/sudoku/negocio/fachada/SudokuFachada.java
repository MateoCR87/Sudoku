package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;

import co.edu.uco.sudoku.dto.SudokuDTO;

public interface SudokuFachada {

	List<SudokuDTO> consultar(SudokuDTO sudokuDTO);

	void registrar(SudokuDTO sudokuDTO);
	
	void eliminar(int codigoPlantilla);
}
