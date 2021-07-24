package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;

import co.edu.uco.sudoku.dto.RegionDTO;

public interface RegionFachada {

	List<RegionDTO> consultar(RegionDTO regionDTO);

	void registrar(RegionDTO regionDTO);
	
	void Eliminar(RegionDTO regionDTO);
	 
}
