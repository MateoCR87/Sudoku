package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.JugadorDatos;
import co.edu.uco.sudoku.datos.ModalidadJuegoDatos;
import co.edu.uco.sudoku.datos.PartidaDatos;
import co.edu.uco.sudoku.datos.PlantillaDatos;
import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.datos.entidad.ModalidadJuegoEntidad;
import co.edu.uco.sudoku.datos.entidad.PartidaEntidad;
import co.edu.uco.sudoku.datos.entidad.PlantillaEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class PartidaDatosArchvo implements PartidaDatos {

	private String rutaArchivo;

	public PartidaDatosArchvo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<PartidaEntidad> consultar(PartidaEntidad entidad) {
		List<PartidaEntidad> listaPartidas = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(rutaArchivo);

		for (String linea : lineas) {
			listaPartidas.add(ensamblar(linea));
		}

		return aplicarFiltro(entidad, listaPartidas);
	}

	private List<PartidaEntidad> aplicarFiltro(PartidaEntidad entidad, List<PartidaEntidad> listaPartidas) {
		if (!UtilObjeto.esNulo(entidad)) {
			listaPartidas = filtrarPorCodigo(entidad, listaPartidas);
			listaPartidas = filtrarPorJugador(entidad, listaPartidas);
			listaPartidas = filtrarPorPlantilla(entidad, listaPartidas);
			listaPartidas = filtrarPorDuracion(entidad, listaPartidas);
			listaPartidas = filtrarPorJuegocompletado(entidad, listaPartidas);
		}
		return listaPartidas;
	}

	private List<PartidaEntidad> filtrarPorCodigo(PartidaEntidad entidad, List<PartidaEntidad> listaPartidas) {
		if (UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
			listaPartidas = listaPartidas.stream().filter(partida -> partida.getCodigo() == entidad.getCodigo())
					.collect(Collectors.toList());
		}
		return listaPartidas;
	}

	private List<PartidaEntidad> filtrarPorJugador(PartidaEntidad entidad, List<PartidaEntidad> listaPartidas) {
		if (!UtilObjeto.esNulo(entidad.getJugador())) {
			listaPartidas = listaPartidas.stream()
					.filter(partida -> partida.getJugador().getCodigo() == entidad.getJugador().getCodigo())
					.collect(Collectors.toList());
		}
		return listaPartidas;
	}

	private List<PartidaEntidad> filtrarPorPlantilla(PartidaEntidad entidad, List<PartidaEntidad> listaPartidas) {
		if (!UtilObjeto.esNulo(entidad.getPlantilla())) {
			listaPartidas = listaPartidas.stream()
					.filter(partida -> partida.getPlantilla().getCodigo() == entidad.getPlantilla().getCodigo())
					.collect(Collectors.toList());
		}
		return listaPartidas;
	}

	private List<PartidaEntidad> filtrarPorDuracion(PartidaEntidad entidad, List<PartidaEntidad> listaPartidas) {
		if (UtilNumerico.numeroEsMayor(entidad.getDuracion(), 0)) {
			listaPartidas = listaPartidas.stream().filter(partida -> partida.getDuracion() == entidad.getDuracion())
					.collect(Collectors.toList());
		}
		return listaPartidas;
	}

	private List<PartidaEntidad> filtrarPorJuegocompletado(PartidaEntidad entidad, List<PartidaEntidad> listaPartidas) {
		if (entidad.isJuegoCompletado()) {
			listaPartidas = listaPartidas.stream().filter(partida -> partida.isJuegoCompletado())
					.collect(Collectors.toList());
		}
		return listaPartidas;
	}

	private PartidaEntidad ensamblar(String registro) {

		String[] datosPartidaActual = registro.split("-");

		int codigoPartidaActual = Integer.parseInt(datosPartidaActual[0]);

		int codigoJugadorActual = Integer.parseInt(datosPartidaActual[1]);
		int codigoPlantillaActual = Integer.parseInt(datosPartidaActual[2]);
		int codigoModalidadActual = Integer.parseInt(datosPartidaActual[3]);

		JugadorDatos jugadorDatos = SudokuDatosFactoria.obtenerFactoria().obtenerJugadorDatos();
		Optional<JugadorEntidad> jugador = jugadorDatos.consultar(JugadorEntidad.crear().setCodigo(codigoJugadorActual))
				.stream().filter(jugadores -> jugadores.getCodigo() == codigoJugadorActual).findFirst();

		PlantillaDatos plantillaDatos = SudokuDatosFactoria.obtenerFactoria().obtenerPlantillaDatos();
		Optional<PlantillaEntidad> plantilla = plantillaDatos
				.consultar(PlantillaEntidad.crear().setCodigo(codigoPlantillaActual)).stream()
				.filter(plantillas -> plantillas.getCodigo() == codigoPlantillaActual).findFirst();

		ModalidadJuegoDatos modalidadJuegoDatos = SudokuDatosFactoria.obtenerFactoria().obtenerModalidadJuegoDatos();
		Optional<ModalidadJuegoEntidad> modalidadJuego = modalidadJuegoDatos
				.consultar(ModalidadJuegoEntidad.crear().setCodigo(codigoModalidadActual)).stream()
				.filter(modalidades -> modalidades.getCodigo() == codigoModalidadActual).findFirst();

		Date fechaInicialActual = UtilTexto.convertirCadenaAFecha(datosPartidaActual[4]);
		Date fechaFinalActual = UtilTexto.convertirCadenaAFecha(datosPartidaActual[5]);
		int duracionPartidaActual = Integer.parseInt(datosPartidaActual[6]);
		boolean juegoCompletadoActual = Boolean.parseBoolean(datosPartidaActual[7]);

		return PartidaEntidad.crear().setCodigo(codigoPartidaActual).setJugador(jugador.orElse(null))
				.setPlantilla(plantilla.orElse(null)).setModalidad(modalidadJuego.orElse(null))
				.setFechaInicial(fechaInicialActual).setFechaFinal(fechaFinalActual).setDuracion(duracionPartidaActual)
				.setJuegoCompletado(juegoCompletadoActual);

	}

	@Override
	public void crear(PartidaEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(PartidaEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(PartidaEntidad objeto) {
		// TODO Auto-generated method stub

	}
}
