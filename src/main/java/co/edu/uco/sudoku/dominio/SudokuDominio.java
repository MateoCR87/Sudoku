package co.edu.uco.sudoku.dominio;

public class SudokuDominio {

	private int codigo;
	private RegionDominio[][] regiones = new RegionDominio[3][3];

	private SudokuDominio(int codigo, RegionDominio[][] regiones) {
		setCodigo(codigo);
		setRegiones(regiones);
	}

	public static SudokuDominio crear(int codigo, RegionDominio[][] regiones) {
		return new SudokuDominio(codigo, regiones);
	}

	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	private void setRegiones(RegionDominio[][] regiones) {
		this.regiones = regiones;
	}

	public int getCodigo() {
		return codigo;
	}

	public RegionDominio[][] getRegiones() {
		return regiones;
	}
}
