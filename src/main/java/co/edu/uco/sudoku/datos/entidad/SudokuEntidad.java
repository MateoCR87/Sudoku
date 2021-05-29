package co.edu.uco.sudoku.datos.entidad;

public class SudokuEntidad {

	private int codigo;
	private RegionEntidad[][] regiones = new RegionEntidad[3][3];

	private SudokuEntidad(int codigo, RegionEntidad[][] regiones) {
		setCodigo(codigo);
		setRegiones(regiones);
	}

	public static SudokuEntidad crear() {
		return new SudokuEntidad(0, null);
	}

	public static SudokuEntidad crear(int codigo, RegionEntidad[][] regiones) {
		return new SudokuEntidad(codigo, regiones);
	}

	public int getCodigo() {
		return codigo;
	}

	public SudokuEntidad setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public RegionEntidad[][] getRegiones() {
		return regiones;
	}

	public SudokuEntidad setRegiones(RegionEntidad[][] regiones) {
		this.regiones = regiones;
		return this;
	}

}
