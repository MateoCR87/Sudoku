package co.edu.uco.sudoku.datos.entidad;

public class PlantillaEntidad {

	private int codigo;
	private SudokuEntidad sudoku;
	private RegionEntidad[][] regiones = new RegionEntidad[3][3];
	private NivelComplejidadEntidad nivelComplejidad;

	private PlantillaEntidad(int codigo, SudokuEntidad sudoku, RegionEntidad[][] regiones,
			NivelComplejidadEntidad nivelComplejidad) {
		setCodigo(codigo);
		setSudoku(sudoku);
		setRegiones(regiones);
		setNivelComplejidad(nivelComplejidad);
	}

	public static PlantillaEntidad crear() {
		return new PlantillaEntidad(0, null, null, null);
	}

	public static PlantillaEntidad crear(int codigo, SudokuEntidad sudoku, RegionEntidad[][] regiones,
			NivelComplejidadEntidad nivelComplejidad) {
		return new PlantillaEntidad(codigo, sudoku, regiones, nivelComplejidad);
	}

	public PlantillaEntidad setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public PlantillaEntidad setSudoku(SudokuEntidad sudoku) {
		this.sudoku = sudoku;
		return this;
	}

	public PlantillaEntidad setRegiones(RegionEntidad[][] regiones) {
		this.regiones = regiones;
		return this;
	}

	public PlantillaEntidad setNivelComplejidad(NivelComplejidadEntidad nivelComplejidad) {
		this.nivelComplejidad = nivelComplejidad;
		return this;
	}

	public int getCodigo() {
		return codigo;
	}

	public SudokuEntidad getSudoku() {
		return sudoku;
	}

	public RegionEntidad[][] getRegiones() {
		return regiones;
	}

	public NivelComplejidadEntidad getNivelComplejidad() {
		return nivelComplejidad;
	}
}
