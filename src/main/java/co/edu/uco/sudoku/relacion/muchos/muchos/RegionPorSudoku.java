package co.edu.uco.sudoku.relacion.muchos.muchos;

public class RegionPorSudoku {

	private int codigoRegion;
	private int codigoSudoku;

	private RegionPorSudoku(int codigoRegion, int codigoSudoku) {
		this.codigoSudoku = codigoSudoku;
		this.codigoRegion = codigoRegion;
	}
	
	public static RegionPorSudoku crear() {
		return new RegionPorSudoku(0, 0);
	}

	public static RegionPorSudoku crear(int codigoRegion, int codigoSudoku) {
		return new RegionPorSudoku(codigoRegion, codigoSudoku);
	}

	public RegionPorSudoku setCodigoRegion(int codigoRegion) {
		this.codigoRegion = codigoRegion;
		return this;
	}

	public RegionPorSudoku setCodigoSudoku(int codigoSudoku) {
		this.codigoSudoku = codigoSudoku;
		return this;
	}

	public int getCodigoSudoku() {
		return codigoSudoku;
	}

	public int getCodigoRegion() {
		return codigoRegion;
	}

}
