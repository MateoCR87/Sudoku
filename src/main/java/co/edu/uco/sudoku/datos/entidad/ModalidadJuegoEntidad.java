package co.edu.uco.sudoku.datos.entidad;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class ModalidadJuegoEntidad {

	private int codigo;
	private String nombre;

	private ModalidadJuegoEntidad(int codigo, String nombre) {
		setCodigo(codigo);
		setNombre(nombre);
	}

	public static ModalidadJuegoEntidad crear() {
		return new ModalidadJuegoEntidad(0, UtilTexto.BLANCO);
	}

	public static ModalidadJuegoEntidad crear(int codigo, String nombre) {
		return new ModalidadJuegoEntidad(codigo, nombre);
	}

	public int getCodigo() {
		return codigo;
	}

	public ModalidadJuegoEntidad setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public String getNombre() {
		return UtilTexto.aplicarTrim(nombre);
	}

	public ModalidadJuegoEntidad setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
		return this;
	}

}
