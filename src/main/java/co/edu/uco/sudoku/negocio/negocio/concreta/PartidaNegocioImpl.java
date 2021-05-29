
package co.edu.uco.sudoku.negocio.negocio.concreta;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import co.edu.uco.sudoku.datos.PartidaDatos;
import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.datos.entidad.PartidaEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.PartidaDominio;
import co.edu.uco.sudoku.negocio.negocio.PartidaNegocio;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.JugadorEnsambladorImpl;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.PartidaEnsambladorImpl;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.PlantillaEnsambladorImpl;
import co.edu.uco.sudoku.negocio.validador.concreta.PartidaValidador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class PartidaNegocioImpl implements PartidaNegocio{

	private PartidaDatos partidaDatos;

	public PartidaNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.partidaDatos = factoriaDatos.obtenerPartidaDatos();
	}

	@Override
	public List<PartidaDominio> consultar(PartidaEntidad entidad) {
		return PartidaEnsambladorImpl.obtenerPartidaEnsambladorImpl()
				.ensamblarDominiosDesdeEntidad(partidaDatos.consultar(entidad));
	}

	@Override
	public void registrarPartida(PartidaDominio partida) {
		PartidaValidador.obtenerInstancia().validar(partida, TipoValidacion.CREACION);
		asegurarQueNoExistaJugadorConLaMismaPlantillaJugada(partida);
		verificarFechasValidas(partida.getFechaInicial(), partida.getFechaFinal());

		partidaDatos.crear(PartidaEnsambladorImpl.obtenerPartidaEnsambladorImpl().ensamblarEntidad(partida).get());
	}

	private void asegurarQueNoExistaJugadorConLaMismaPlantillaJugada(PartidaDominio partida) {
		PartidaEntidad partidaEntidad = PartidaEntidad.crear()
				.setJugador(
						JugadorEnsambladorImpl.obtenerJugadorEnsablador().ensamblarEntidad(partida.getJugador()).get())
				.setPlantilla(PlantillaEnsambladorImpl.obtenerPlantillaEnsablador()
						.ensamblarEntidad(partida.getPlantilla()).get());

		if (consultar(partidaEntidad).isEmpty()) {
			throw new SudokuNegocioExeption("No se puede crear una la partida por que el jugador :"
					+ partida.getJugador().getNombre() + " ya jugo esa plantilla. ");
		}
	}

	private void verificarFechasValidas(Date fechaInicio, Date fechaFin) {
		if (!fechaFin.after(fechaInicio)) {
			throw new SudokuNegocioExeption(
					"la fecha final en la que acaba la partida no puede se anterior a la fecha inicial ");
		}
	}

	@Override
	public List<PartidaDominio> obtenerHistorial(JugadorEntidad jugador) {
		return consultar(PartidaEntidad.crear().setJugador(jugador));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartidaDominio> obtenerRanking() {
		return (List<PartidaDominio>) consultar(PartidaEntidad.crear().setJuegoCompletado(true)).stream().sorted(Comparator.comparing(PartidaDominio::getDuracion)) ;
	}

	@Override
	public boolean saberEstado(PartidaEntidad entidad) {
		return entidad.isJuegoCompletado();
	}

}
