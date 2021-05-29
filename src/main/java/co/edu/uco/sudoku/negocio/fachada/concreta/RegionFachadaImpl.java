package co.edu.uco.sudoku.negocio.fachada.concreta;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.dto.CeldaDTO;
import co.edu.uco.sudoku.dto.PosicionDTO;
import co.edu.uco.sudoku.dto.RegionDTO;
import co.edu.uco.sudoku.negocio.fachada.RegionFachada;

public class RegionFachadaImpl implements RegionFachada {

	@Override
	public List<RegionDTO> consultar(RegionDTO regionDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrar(RegionDTO regionDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarCeldas(List<CeldaDTO> celdasDTO, RegionDTO regionDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CeldaDTO> obtenerCeldas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RegionDTO> obtenerCeldaDeterminada(int codigoRegion, PosicionDTO posicionCeldaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validarSiNumeroExiste(int codigoRegion, int numero) {
		// TODO Auto-generated method stub
		return false;
	}

}
