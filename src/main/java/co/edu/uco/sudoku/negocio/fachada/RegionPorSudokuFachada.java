package co.edu.uco.sudoku.negocio.fachada;

import co.edu.uco.sudoku.dto.SudokuDTO;

public interface RegionPorSudokuFachada {
	
	void registrar(SudokuDTO sudokuDTO);

	SudokuDTO consultar(int codigoSudoku);
	
	void eliminar(int codigoSudoku);
}
