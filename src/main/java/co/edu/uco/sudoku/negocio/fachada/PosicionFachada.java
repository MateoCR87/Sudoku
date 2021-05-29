package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;

import co.edu.uco.sudoku.dto.PosicionDTO;

public interface PosicionFachada {

	List<PosicionDTO> consultar(PosicionDTO posicionDTO);

}
