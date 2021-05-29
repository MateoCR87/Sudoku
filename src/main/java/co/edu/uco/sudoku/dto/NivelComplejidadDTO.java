package co.edu.uco.sudoku.dto;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class NivelComplejidadDTO {

	private int codigo;
	private String nombre;
	private String descripcion;
	private int tiempoLimiteSegundos;

	private NivelComplejidadDTO(int codigo, String nombre, String descripcion, int tiempoLimiteSegundos) {
		setCodigo(codigo);
		setNombre(nombre);
		setDescripcion(descripcion);
		setTiempoLimiteSegundos(tiempoLimiteSegundos);
	}

	public static NivelComplejidadDTO crear() {
		return new NivelComplejidadDTO(0, UtilTexto.BLANCO, UtilTexto.BLANCO, 0);
	}

	public static NivelComplejidadDTO crear(int codigo, String nombre, String descripcion, int tiempoLimiteSegundos) {
		return new NivelComplejidadDTO(codigo, nombre, descripcion, tiempoLimiteSegundos);
	}

	public NivelComplejidadDTO setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public NivelComplejidadDTO setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
		return this;
	}

	public NivelComplejidadDTO setDescripcion(String descripcion) {
		this.descripcion = UtilTexto.aplicarTrim(descripcion);
		return this;
	}

	public NivelComplejidadDTO setTiempoLimiteSegundos(int tiempoLimiteSegundos) {
		this.tiempoLimiteSegundos = tiempoLimiteSegundos;
		return this;
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
