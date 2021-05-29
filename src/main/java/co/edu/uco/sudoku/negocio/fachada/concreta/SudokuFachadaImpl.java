package co.edu.uco.sudoku.negocio.fachada.concreta;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.dto.PosicionDTO;
import co.edu.uco.sudoku.dto.RegionDTO;
import co.edu.uco.sudoku.dto.SudokuDTO;
import co.edu.uco.sudoku.negocio.fachada.SudokuFachada;

public class SudokuFachadaImpl implements SudokuFachada {

	@Override
	public List<SudokuDTO> consultar(SudokuDTO sudokuDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrar(SudokuDTO sudokuDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarRegiones(List<RegionDTO> regionesDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RegionDTO> obtenerRegiones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RegionDTO> obtenerRegionDeterminada(int codigoSudoku, PosicionDTO posicionRegionDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
