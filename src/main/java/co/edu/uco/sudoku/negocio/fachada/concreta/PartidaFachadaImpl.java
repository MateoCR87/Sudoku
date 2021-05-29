package co.edu.uco.sudoku.negocio.fachada.concreta;

import java.sql.Date;
import java.util.List;

import co.edu.uco.sudoku.dto.ModalidadJuegoDTO;
import co.edu.uco.sudoku.dto.PartidaDTO;
import co.edu.uco.sudoku.dto.PlantillaDTO;
import co.edu.uco.sudoku.negocio.fachada.PartidaFachada;

public class PartidaFachadaImpl implements PartidaFachada {

	@Override
	public List<PartidaDTO> consultar(PartidaDTO partidaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarPlantillaAJugar(PlantillaDTO plantillaDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarModoDeJuego(ModalidadJuegoDTO modalidadJuegoDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void obtenerHistorial() {
		// TODO Auto-generated method stub

	}

	@Override
	public void obtenerRanking() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ingresarFechas(Date fechaInicio, Date fechaFin) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean saberEstado() {
		// TODO Auto-generated method stub
		return false;
	}

}
