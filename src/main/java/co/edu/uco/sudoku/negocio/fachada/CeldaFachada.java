package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;

import co.edu.uco.sudoku.dto.CeldaDTO;

public interface CeldaFachada {

	List<CeldaDTO> consultar(CeldaDTO celdaDTO);

}
