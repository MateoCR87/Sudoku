package co.edu.uco.sudoku.dto;

public class PosicionDTO {

	private int codigo;
	private int fila;
	private int columna;

	private PosicionDTO(int codigo, int fila, int columna) {
		setCodigo(codigo);
		setFila(fila);
		setColumna(columna);
	}

	public static PosicionDTO crear() {
		return new PosicionDTO(0, 0, 0);
	}

	public static PosicionDTO crear(int codigo, int fila, int columna) {
		return new PosicionDTO(codigo, fila, columna);
	}

	public PosicionDTO setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public PosicionDTO setFila(int fila) {
		this.fila = fila;
		return this;
	}

	public PosicionDTO setColumna(int columna) {
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
