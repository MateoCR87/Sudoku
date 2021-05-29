package co.edu.uco.sudoku.dto;

public class SudokuDTO {

	private int codigo;
	private RegionDTO[][] regiones = new RegionDTO[3][3];

	private SudokuDTO(int codigo, RegionDTO[][] regiones) {
		setCodigo(codigo);
		setRegiones(regiones);
	}

	public static SudokuDTO crear() {
		return new SudokuDTO(0, null);
	}

	public static SudokuDTO crear(int codigo, RegionDTO[][] regiones) {
		return new SudokuDTO(codigo, regiones);
	}

	public int getCodigo() {
		return codigo;
	}

	public SudokuDTO setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public RegionDTO[][] getRegiones() {
		return regiones;
	}

	public SudokuDTO setRegiones(RegionDTO[][] regiones) {
		this.regiones = regiones;
		return this;
	}

}
