package co.edu.uco.sudoku.dominio;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class JugadorDominio {

	private int codigo;
	private String nombre;
	private int documentoIdentificacion;
	private String correo;
	private String clave;

	private JugadorDominio(int codigo, String nombre, int documentoIdentificacion, String correo, String clave) {
		setCodigo(codigo);
		setNombre(nombre);
		setDocumentoIdentificacion(documentoIdentificacion);
		setCorreo(correo);
		setClave(clave);
	}

	public static JugadorDominio crear(int codigo, String nombre, int documentoIdentificacion, String correo,
			String clave) {
		return new JugadorDominio(codigo, nombre, documentoIdentificacion, correo, clave);
	}

	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	private void setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
	}

	private void setDocumentoIdentificacion(int documentoIdentificacion) {
		this.documentoIdentificacion = documentoIdentificacion;
	}

	private void setCorreo(String correo) {
		this.correo = UtilTexto.aplicarTrim(correo);
	}

	private void setClave(String clave) {
		this.clave = clave;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDocumentoIdentificacion() {
		return documentoIdentificacion;
	}

	public String getCorreo() {
		return correo;
	}

	public String getClave() {
		return clave;
	}

}
