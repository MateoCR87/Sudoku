package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;

import co.edu.uco.sudoku.dto.NivelComplejidadDTO;

public interface NivelComplejidadFachada {

	List<NivelComplejidadDTO> consultar(NivelComplejidadDTO nivelComplejidadDTO);
}
