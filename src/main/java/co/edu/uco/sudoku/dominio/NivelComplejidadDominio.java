package co.edu.uco.sudoku.dominio;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class NivelComplejidadDominio {

	private int codigo;
	private String nombre;
	private String descripcion;
	private int tiempoLimiteSegundos;

	private NivelComplejidadDominio(int codigo, String nombre, String descripcion, int tiempoLimiteSegundos) {
		setCodigo(codigo);
		setNombre(nombre);
		setDescripcion(descripcion);
		setTiempoLimiteSegundos(tiempoLimiteSegundos);
	}

	public static NivelComplejidadDominio crear(int codigo, String nombre, String descripcion,
			int tiempoLimiteSegundos) {
		return new NivelComplejidadDominio(codigo, nombre, descripcion, tiempoLimiteSegundos);
	}

	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	private void setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
	}

	private void setDescripcion(String descripcion) {
		this.descripcion = UtilTexto.aplicarTrim(descripcion);
	}

	private void setTiempoLimiteSegundos(int tiempoLimiteSegundos) {
		this.tiempoLimiteSegundos = tiempoLimiteSegundos;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getTiempoLimiteSegundos() {
		return tiempoLimiteSegundos;
	}

}
