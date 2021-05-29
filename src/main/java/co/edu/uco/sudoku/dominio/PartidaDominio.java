package co.edu.uco.sudoku.dominio;

import java.util.Date;

public class PartidaDominio {

	private int codigo;
	private JugadorDominio jugador;
	private PlantillaDominio plantilla;
	private ModalidadJuegoDominio modalidad;
	private Date fechaInicial;
	private Date fechaFinal;
	private int duracion;
	private boolean juegoCompletado;

	private PartidaDominio(int codigo, JugadorDominio jugador, PlantillaDominio plantilla,
			ModalidadJuegoDominio modalidad, Date fechaInicial, Date fechaFinal, int duracion,
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

	public static PartidaDominio crear(int codigo, JugadorDominio jugador, PlantillaDominio plantilla,
			ModalidadJuegoDominio modalidad, Date fechaInicial, Date fechaFinal, int duracion,
			boolean juegoCompletado) {
		return new PartidaDominio(codigo, jugador, plantilla, modalidad, fechaInicial, fechaFinal, duracion,
				juegoCompletado);
	}

	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	private void setJugador(JugadorDominio jugador) {
		this.jugador = jugador;
	}

	private void setPlantilla(PlantillaDominio plantilla) {
		this.plantilla = plantilla;
	}

	private void setModalidad(ModalidadJuegoDominio modalidad) {
		this.modalidad = modalidad;
	}

	private void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	private void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	private void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	private void setJuegoCompletado(boolean juegoCompletado) {
		this.juegoCompletado = juegoCompletado;
	}

	public int getCodigo() {
		return codigo;
	}

	public JugadorDominio getJugador() {
		return jugador;
	}

	public PlantillaDominio getPlantilla() {
		return plantilla;
	}

	public ModalidadJuegoDominio getModalidad() {
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
