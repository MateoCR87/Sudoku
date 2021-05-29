package co.edu.uco.sudoku.datos.entidad;

public class PosicionEntidad {

	private int codigo;
	private int fila;
	private int columna;

	private PosicionEntidad(int codigo, int fila, int columna) {
		setCodigo(codigo);
		setFila(fila);
		setColumna(columna);
	}

	public static PosicionEntidad crear() {
		return new PosicionEntidad(0, 0, 0);
	}

	public static PosicionEntidad crear(int codigo, int fila, int columna) {
		return new PosicionEntidad(codigo, fila, columna);
	}

	public PosicionEntidad setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public PosicionEntidad setFila(int fila) {
		this.fila = fila;
		return this;
	}

	public PosicionEntidad setColumna(int columna) {
		this.columna = columna;
		return this;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

}
