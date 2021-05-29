package co.edu.uco.sudoku.datos.entidad;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class NivelComplejidadEntidad {

	private int codigo;
	private String nombre;
	private String descripcion;
	private int tiempoLimiteSegundos;

	private NivelComplejidadEntidad(int codigo, String nombre, String descripcion, int tiempoLimiteSegundos) {
		setCodigo(codigo);
		setNombre(nombre);
		setDescripcion(descripcion);
		setTiempoLimiteSegundos(tiempoLimiteSegundos);
	}

	public static NivelComplejidadEntidad crear() {
		return new NivelComplejidadEntidad(0, UtilTexto.BLANCO, UtilTexto.BLANCO, 0);
	}

	public static NivelComplejidadEntidad crear(int codigo, String nombre, String descripcion,
			int tiempoLimiteSegundos) {
		return new NivelComplejidadEntidad(codigo, nombre, descripcion, tiempoLimiteSegundos);
	}

	public NivelComplejidadEntidad setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public NivelComplejidadEntidad setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
		return this;
	}

	public NivelComplejidadEntidad setDescripcion(String descripcion) {
		this.descripcion = UtilTexto.aplicarTrim(descripcion);
		return this;
	}

	public NivelComplejidadEntidad setTiempoLimiteSegundos(int tiempoLimiteSegundos) {
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
