package co.edu.uco.sudoku.negocio.fachada;

import java.util.List;

import co.edu.uco.sudoku.dto.JugadorDTO;

public interface JugadorFachada {

	void registrar(JugadorDTO jugadorDto);

	List<JugadorDTO> consultar(JugadorDTO jugadorDto);
	
	void eliminar(JugadorDTO jugadorDto);
	
	void actualizar(JugadorDTO jugadorDto);

}
