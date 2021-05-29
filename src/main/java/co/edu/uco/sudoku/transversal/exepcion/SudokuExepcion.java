package co.edu.uco.sudoku.transversal.exepcion;

public class SudokuExepcion extends RuntimeException {

	private static final long serialVersionUID = -8545980853964879864L;

	public SudokuExepcion(String mensage) {
		super(mensage);
	}
}
