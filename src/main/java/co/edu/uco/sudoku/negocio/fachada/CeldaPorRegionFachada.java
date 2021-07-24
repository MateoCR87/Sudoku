package co.edu.uco.sudoku.negocio.fachada;

import co.edu.uco.sudoku.dto.RegionDTO;

public interface CeldaPorRegionFachada {

	void registrar(RegionDTO regionDTO);

	RegionDTO consultar(int codigoRegion);
	
	void eliminar(int codigoRegion);
}
