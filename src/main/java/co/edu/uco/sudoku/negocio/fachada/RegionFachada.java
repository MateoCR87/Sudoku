package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.dto.CeldaDTO;
import co.edu.uco.sudoku.dto.PosicionDTO;
import co.edu.uco.sudoku.dto.RegionDTO;

public interface RegionFachada {

	List<RegionDTO> consultar(RegionDTO regionDTO);

	void registrar(RegionDTO regionDTO);

	void registrarCeldas(List<CeldaDTO> celdasDTO, RegionDTO regionDTO);

	List<CeldaDTO> obtenerCeldas();

	Optional<RegionDTO> obtenerCeldaDeterminada(int codigoRegion, PosicionDTO posicionCeldaDTO);

	boolean validarSiNumeroExiste(int codigoRegion, int numero);
}
