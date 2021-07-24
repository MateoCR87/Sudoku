package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;

import co.edu.uco.sudoku.dto.PlantillaDTO;

public interface RegionPorPlantillaFachada {
	void registrar(PlantillaDTO plantillaDTO);

	List<PlantillaDTO> consultar(PlantillaDTO plantillaDTO);
	
	void eliminar(int codigoPlantilla);
}
