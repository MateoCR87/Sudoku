package co.edu.uco.sudoku.dominio;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class ModalidadJuegoDominio {

	private int codigo;
	private String nombre;

	private ModalidadJuegoDominio(int codigo, String nombre) {
		setCodigo(codigo);
		setNombre(nombre);
	}

	public static ModalidadJuegoDominio crear(int codigo, String nombre) {
		return new ModalidadJuegoDominio(codigo, nombre);
	}

	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	private void setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

}
