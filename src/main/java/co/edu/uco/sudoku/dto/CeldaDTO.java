package co.edu.uco.sudoku.dto;

public class CeldaDTO {

	private int codigo;
	private int numero;
	private boolean esPista;
	private PosicionDTO posicion;

	private CeldaDTO(int codigo, int numero, boolean esPista, PosicionDTO posicion) {
		setCodigo(codigo);
		setNumero(numero);
		setEsPista(esPista);
		setPosicion(posicion);
	}

	public static CeldaDTO crear() {
		return new CeldaDTO(0, 0, false, null);
	}

	public static CeldaDTO crear(int codigo, int numero, boolean esPista, PosicionDTO posicion) {
		return new CeldaDTO(codigo, numero, esPista, posicion);
	}

	public CeldaDTO setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public CeldaDTO setNumero(int numero) {
		this.numero = numero;
		return this;
	}

	public CeldaDTO setEsPista(boolean esPista) {
		this.esPista = esPista;
		return this;
	}

	public CeldaDTO setPosicion(PosicionDTO posicion) {
		this.posicion = posicion;
		return this;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getNumero() {
		return numero;
	}

	public boolean isEsPista() {
		return esPista;
	}

	public PosicionDTO getPosicion() {
		return posicion;
	}

}
