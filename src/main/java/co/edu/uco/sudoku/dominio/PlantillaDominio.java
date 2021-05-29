package co.edu.uco.sudoku.dominio;

public class PlantillaDominio {

	private int codigo;
	private SudokuDominio sudoku;
	private RegionDominio[][] regiones = new RegionDominio[3][3];
	private NivelComplejidadDominio nivelComplejidad;

	private PlantillaDominio(int codigo, SudokuDominio sudoku, RegionDominio[][] regiones,
			NivelComplejidadDominio nivelComplejidad) {
		setCodigo(codigo);
		setSudoku(sudoku);
		setRegiones(regiones);
		setNivelComplejidad(nivelComplejidad);
	}

	public static PlantillaDominio crear(int codigo, SudokuDominio sudoku, RegionDominio[][] regiones,
			NivelComplejidadDominio nivelComplejidad) {
		return new PlantillaDominio(codigo, sudoku, regiones, nivelComplejidad);
	}

	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	private void setSudoku(SudokuDominio sudoku) {
		this.sudoku = sudoku;
	}

	private void setRegiones(RegionDominio[][] regiones) {
		this.regiones = regiones;
	}

	private void setNivelComplejidad(NivelComplejidadDominio nivelComplejidad) {
		this.nivelComplejidad = nivelComplejidad;
	}

	public int getCodigo() {
		return codigo;
	}

	public SudokuDominio getSudoku() {
		return sudoku;
	}

	public RegionDominio[][] getRegiones() {
		return regiones;
	}

	public NivelComplejidadDominio getNivelComplejidad() {
		return nivelComplejidad;
	}
}
