package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.dominio.SudokuDominio;

public interface SudokuNegocio {

	List<SudokuDominio> consultar(SudokuEntidad entidad);

	void registrar(SudokuDominio sudoku);
	
	void eliminar(SudokuDominio sudoku);
}
