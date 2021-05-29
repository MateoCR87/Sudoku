package co.edu.uco.sudoku.negocio.fachada;

import java.sql.Date;
import java.util.List;

import co.edu.uco.sudoku.dto.ModalidadJuegoDTO;
import co.edu.uco.sudoku.dto.PartidaDTO;
import co.edu.uco.sudoku.dto.PlantillaDTO;

public interface PartidaFachada {

	List<PartidaDTO> consultar(PartidaDTO partidaDTO);

	void registrarPlantillaAJugar(PlantillaDTO plantillaDTO);

	void registrarModoDeJuego(ModalidadJuegoDTO modalidadJuegoDTO);

	void obtenerHistorial();

	void obtenerRanking();

	void ingresarFechas(Date fechaInicio, Date fechaFin);

	boolean saberEstado();
}
