package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;

import co.edu.uco.sudoku.dto.ModalidadJuegoDTO;

public interface ModalidadJuegoFachada {

	List<ModalidadJuegoDTO> consultar(ModalidadJuegoDTO modalidadJuegoDTO);
}
