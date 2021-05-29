package co.edu.uco.sudoku.dto;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class ModalidadJuegoDTO {

	private int codigo;
	private String nombre;

	private ModalidadJuegoDTO(int codigo, String nombre) {
		setCodigo(codigo);
		setNombre(nombre);
	}

	public static ModalidadJuegoDTO crear() {
		return new ModalidadJuegoDTO(0, UtilTexto.BLANCO);
	}

	public static ModalidadJuegoDTO crear(int codigo, String nombre) {
		return new ModalidadJuegoDTO(codigo, nombre);
	}

	public int getCodigo() {
		return codigo;
	}

	public ModalidadJuegoDTO setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public String getNombre() {
		return UtilTexto.aplicarTrim(nombre);
	}

	public ModalidadJuegoDTO setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
		return this;
	}

}
