package co.edu.uco.sudoku.dto;

import java.util.Date;

public class PartidaDTO {

	private int codigo;
	private JugadorDTO jugador;
	private PlantillaDTO plantilla;
	private ModalidadJuegoDTO modalidad;
	private Date fechaInicial;
	private Date fechaFinal;
	private int duracion;
	private boolean juegoCompletado;

	private PartidaDTO(int codigo, JugadorDTO jugador, PlantillaDTO plantilla, ModalidadJuegoDTO modalidad,
			Date fechaInicial, Date fechaFinal, int duracion, boolean juegoCompletado) {
		setCodigo(codigo);
		setJugador(jugador);
		setPlantilla(plantilla);
		setModalidad(modalidad);
		setFechaInicial(fechaInicial);
		setFechaFinal(fechaFinal);
		setDuracion(duracion);
		setJuegoCompletado(juegoCompletado);
	}

	public static PartidaDTO crear(int codigo, JugadorDTO jugador, PlantillaDTO plantilla, ModalidadJuegoDTO modalidad,
			Date fechaInicial, Date fechaFinal, int duracion, boolean juegoCompletado) {

		return new PartidaDTO(codigo, jugador, plantilla, modalidad, fechaInicial, fechaFinal, duracion,
				juegoCompletado);
	}

	public static PartidaDTO crear() {
		return new PartidaDTO(0, null, null, null, null, null, 0, false);
	}

	public PartidaDTO setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public PartidaDTO setJugador(JugadorDTO jugador) {
		this.jugador = jugador;
		return this;
	}

	public PartidaDTO setPlantilla(PlantillaDTO plantilla) {
		this.plantilla = plantilla;
		return this;
	}

	public PartidaDTO setModalidad(ModalidadJuegoDTO modalidad) {
		this.modalidad = modalidad;
		return this;
	}

	public PartidaDTO setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
		return this;
	}

	public PartidaDTO setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
		return this;
	}

	public PartidaDTO setDuracion(int duracion) {
		this.duracion = duracion;
		return this;
	}

	public PartidaDTO setJuegoCompletado(boolean juegoCompletado) {
		this.juegoCompletado = juegoCompletado;
		return this;
	}

	public int getCodigo() {
		return codigo;
	}

	public JugadorDTO getJugador() {
		return jugador;
	}

	public PlantillaDTO getPlantilla() {
		return plantilla;
	}

	public ModalidadJuegoDTO getModalidad() {
		return modalidad;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public int getDuracion() {
		return duracion;
	}

	public boolean isJuegoCompletado() {
		return juegoCompletado;
	}

}
