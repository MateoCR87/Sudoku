package co.edu.uco.sudoku.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

@Entity
public class JugadorDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	
	@Column
	private String nombre;
	
	@Column
	private int documentoIdentificacion;
	
	@Column
	private String correo;
	
	@Column
	private String clave;

	private JugadorDTO(int codigo, String nombre, int documentoIdentificacion, String correo, String clave) {
		setCodigo(codigo);
		setNombre(nombre);
		setDocumentoIdentificacion(documentoIdentificacion);
		setCorreo(correo);
		setClave(clave);
	}

	public static JugadorDTO crear() {
		return new JugadorDTO(0, UtilTexto.BLANCO, 0, UtilTexto.BLANCO, UtilTexto.BLANCO);
	}

	public static JugadorDTO crear(int codigo, String nombre, int documentoIdentificacion, String correo,
			String clave) {
		return new JugadorDTO(codigo, nombre, documentoIdentificacion, correo, clave);
	}

	public JugadorDTO setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public JugadorDTO setNombre(String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
		return this;
	}

	public JugadorDTO setDocumentoIdentificacion(int documentoIdentificacion) {
		this.documentoIdentificacion = documentoIdentificacion;
		return this;
	}

	public JugadorDTO setCorreo(String correo) {
		this.correo = UtilTexto.aplicarTrim(correo);
		return this;
	}

	public JugadorDTO setClave(String clave) {
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
