package co.edu.uco.sudoku.datos.entidad;

public class CeldaEntidad {

	private int codigo;
	private int numero;
	private boolean esPista;
	private PosicionEntidad posicion;

	private CeldaEntidad(int codigo, int numero, boolean esPista, PosicionEntidad posicion) {
		setCodigo(codigo);
		setNumero(numero);
		setEsPista(esPista);
		setPosicion(posicion);
	}

	public static CeldaEntidad crear() {
		return new CeldaEntidad(0, 0, false, null);
	}

	public static CeldaEntidad crear(int codigo, int numero, boolean esPista, PosicionEntidad posicion) {
		return new CeldaEntidad(codigo, numero, esPista, posicion);
	}

	public CeldaEntidad setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public CeldaEntidad setNumero(int numero) {
		this.numero = numero;
		return this;
	}

	public CeldaEntidad setEsPista(boolean esPista) {
		this.esPista = esPista;
		return this;
	}

	public CeldaEntidad setPosicion(PosicionEntidad posicion) {
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

	public PosicionEntidad getPosicion() {
		return posicion;
	}

}
