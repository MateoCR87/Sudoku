package co.edu.uco.sudoku.negocio.fachada.concreta;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.dto.PlantillaDTO;
import co.edu.uco.sudoku.dto.PosicionDTO;
import co.edu.uco.sudoku.dto.RegionDTO;
import co.edu.uco.sudoku.dto.SudokuDTO;
import co.edu.uco.sudoku.negocio.fachada.PlantillaFachada;

public class PlantillaFachadaImpl implements PlantillaFachada {

	@Override
	public List<PlantillaDTO> consultar(PlantillaDTO plantillaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarSudokuConPistas(SudokuDTO sudokuDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarRegiones(List<RegionDTO> regionesDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<RegionDTO> obtenerRegionSoloPistas(PlantillaDTO plantilaDTO, PosicionDTO posicionDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void organizarPorDificultad() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean compararConSudoku(SudokuDTO sudokuDTO, PlantillaDTO plantillaDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
