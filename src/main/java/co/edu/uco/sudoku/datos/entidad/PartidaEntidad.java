package co.edu.uco.sudoku.datos.entidad;

import java.util.Date;

public class PartidaEntidad {

	private int codigo;
	private JugadorEntidad jugador;
	private PlantillaEntidad plantilla;
	private ModalidadJuegoEntidad modalidad;
	private Date fechaInicial;
	private Date fechaFinal;
	private int duracion;
	private boolean juegoCompletado;

	private PartidaEntidad(int codigo, JugadorEntidad jugador, PlantillaEntidad plantilla,
			ModalidadJuegoEntidad modalidad, Date fechaInicial, Date fechaFinal, int duracion,
			boolean juegoCompletado) {
		setCodigo(codigo);
		setJugador(jugador);
		setPlantilla(plantilla);
		setModalidad(modalidad);
		setFechaInicial(fechaInicial);
		setFechaFinal(fechaFinal);
		setDuracion(duracion);
		setJuegoCompletado(juegoCompletado);
	}

	public static PartidaEntidad crear(int codigo, JugadorEntidad jugador, PlantillaEntidad plantilla,
			ModalidadJuegoEntidad modalidad, Date fechaInicial, Date fechaFinal, int duracion,
			boolean juegoCompletado) {

		return new PartidaEntidad(codigo, jugador, plantilla, modalidad, fechaInicial, fechaFinal, duracion,
				juegoCompletado);
	}

	public static PartidaEntidad crear() {
		return new PartidaEntidad(0, null, null, null, null, null, 0, false);
	}

	public PartidaEntidad setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public PartidaEntidad setJugador(JugadorEntidad jugador) {
		this.jugador = jugador;
		return this;
	}

	public PartidaEntidad setPlantilla(PlantillaEntidad plantilla) {
		this.plantilla = plantilla;
		return this;
	}

	public PartidaEntidad setModalidad(ModalidadJuegoEntidad modalidad) {
		this.modalidad = modalidad;
		return this;
	}

	public PartidaEntidad setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
		return this;
	}

	public PartidaEntidad setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
		return this;
	}

	public PartidaEntidad setDuracion(int duracion) {
		this.duracion = duracion;
		return this;
	}

	public PartidaEntidad setJuegoCompletado(boolean juegoCompletado) {
		this.juegoCompletado = juegoCompletado;
		return this;
	}

	public int getCodigo() {
		return codigo;
	}

	public JugadorEntidad getJugador() {
		return jugador;
	}

	public PlantillaEntidad getPlantilla() {
		return plantilla;
	}

	public ModalidadJuegoEntidad getModalidad() {
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
