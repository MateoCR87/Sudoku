package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.dto.CeldaDTO;

public interface CeldaFachada {

	List<CeldaDTO> consultar(CeldaDTO celdaDTO);

	void registrar(CeldaDTO celdaDTO);

	Optional<CeldaDTO> modificarNumero(int numero, CeldaDTO celdaDTO);

	void quitarNumero(CeldaDTO celdaDTO);
}
