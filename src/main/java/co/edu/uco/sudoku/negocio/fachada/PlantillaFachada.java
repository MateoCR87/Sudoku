package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;

import co.edu.uco.sudoku.dto.PlantillaDTO;

public interface PlantillaFachada {

	List<PlantillaDTO> consultar(PlantillaDTO plantillaDTO);

	void registrar(PlantillaDTO plantilla);

	void organizarPorDificultad();
	
	void eliminar(int codigoPlantilla);
}
