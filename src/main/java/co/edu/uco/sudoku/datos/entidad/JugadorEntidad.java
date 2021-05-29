package co.edu.uco.sudoku.datos.entidad;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class JugadorEntidad {

	private int codigo;
	private String nombre;
	private int documentoIdentificacion;
	private String correo;
	private String clave;

	private JugadorEntidad(int codigo, String nombre, int documentoIdentificacion, String correo, String clave) {
		setCodigo(codigo);
		setNombre(nombre);
		setDocumentoIdentificacion(documentoIdentificacion);
		setCorreo(correo);
		setClave(clave);
	}

	public static JugadorEntidad crear() {
		return new JugadorEntidad(0, UtilTexto.BLANCO, 0, UtilTexto.BLANCO, UtilTexto.BLANCO);
	}

	public static JugadorEntidad crear(int codigo, String nombre, int documentoIdentificacion, String correo,
			String clave) {
		return new JugadorEntidad(codigo, nombre, documentoIdentificacion, correo, clave);
	}

	public JugadorEntidad setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public JugadorEntidad setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
		return this;
	}

	public JugadorEntidad setDocumentoIdentificacion(int documentoIdentificacion) {
		this.documentoIdentificacion = documentoIdentificacion;
		return this;
	}

	public JugadorEntidad setCorreo(String correo) {
		this.correo = UtilTexto.aplicarTrim(correo);
		return this;
	}

	public JugadorEntidad setClave(String clave) {
		this.clave = UtilTexto.aplicarTrim(clave);
		return this;
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
