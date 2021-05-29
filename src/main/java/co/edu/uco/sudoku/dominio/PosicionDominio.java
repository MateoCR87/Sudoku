package co.edu.uco.sudoku.dominio;

public class PosicionDominio {

	private int codigo;
	private int fila;
	private int columna;

	private PosicionDominio(int codigo, int fila, int colomna) {
		setCodigo(codigo);
		setFila(fila);
		setColomna(colomna);
	}

	public static PosicionDominio crear(int codigo, int fila, int colomna) {
		return new PosicionDominio(codigo, fila, colomna);
	}

	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	private void setFila(int fila) {
		this.fila = fila;
	}

	private void setColomna(int columna) {
		this.columna = columna;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getFila() {
		return fila;
	}

	public int getColomna() {
		return columna;
	}

	public String getPosicionTexto() {
		StringBuilder posiciontexto = new StringBuilder();
		posiciontexto.append(getFila()).append("-").append(getColomna());
		return posiciontexto.toString();
	}

}
