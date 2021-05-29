package co.edu.uco.sudoku.datos.api.controlador.respuesta;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class Respuesta<T> {

	private EstadoRespuestaEnum estado = EstadoRespuestaEnum.No_Exitosa;
	private List<String> mensages = new ArrayList<>();
	private List<T> datos;

	public EstadoRespuestaEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoRespuestaEnum estado) {
		this.estado = estado;
	}

	public List<String> getMensages() {
		return mensages;
	}

	public void setMensages(List<String> mensages) {
		this.mensages = mensages;
	}

	public List<T> getDatos() {
		return datos;
	}

	public void setDatos(List<T> datos) {
		this.datos = datos;
	}

	public void adicionaMensage(String  mensage) {
		if(!UtilTexto.cadenaEstaVacia(mensage)) {
			getMensages().add(mensage);
		}
	}
}
