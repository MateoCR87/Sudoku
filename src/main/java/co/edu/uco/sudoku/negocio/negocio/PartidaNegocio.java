package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.datos.entidad.PartidaEntidad;
import co.edu.uco.sudoku.dominio.PartidaDominio;

public interface PartidaNegocio {

	List<PartidaDominio> consultar(PartidaEntidad entidad);

	void registrarPartida(PartidaDominio partida);

	List<PartidaDominio> obtenerHistorial(JugadorEntidad jugador);

	List<PartidaDominio> obtenerRanking();

	boolean saberEstado(PartidaEntidad entidad);

}
