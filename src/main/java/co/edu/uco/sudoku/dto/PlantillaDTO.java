package co.edu.uco.sudoku.dto;

public class PlantillaDTO {

	private int codigo;
	private SudokuDTO sudoku;
	private RegionDTO[][] regiones = new RegionDTO[3][3];
	private NivelComplejidadDTO nivelComplejidad;

	private PlantillaDTO(int codigo, SudokuDTO sudoku, RegionDTO[][] regiones, NivelComplejidadDTO nivelComplejidad) {
		setCodigo(codigo);
		setSudoku(sudoku);
		setRegiones(regiones);
		setNivelComplejidad(nivelComplejidad);
	}

	public static PlantillaDTO crear() {
		return new PlantillaDTO(0, null, null, null);
	}

	public static PlantillaDTO crear(int codigo, SudokuDTO sudoku, RegionDTO[][] regiones,
			NivelComplejidadDTO nivelComplejidad) {
		return new PlantillaDTO(codigo, sudoku, regiones, nivelComplejidad);
	}

	public PlantillaDTO setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public PlantillaDTO setSudoku(SudokuDTO sudoku) {
		this.sudoku = sudoku;
		return this;
	}

	public PlantillaDTO setRegiones(RegionDTO[][] regiones) {
		this.regiones = regiones;
		return this;
	}

	public PlantillaDTO setNivelComplejidad(NivelComplejidadDTO nivelComplejidad) {
		this.nivelComplejidad = nivelComplejidad;
		return this;
	}

	public int getCodigo() {
		return codigo;
	}

	public SudokuDTO getSudoku() {
		return sudoku;
	}

	public RegionDTO[][] getRegiones() {
		return regiones;
	}

	public NivelComplejidadDTO getNivelComplejidad() {
		return nivelComplejidad;
	}
}
