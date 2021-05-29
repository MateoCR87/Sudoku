package co.edu.uco.sudoku.negocio.validador.regla.concreta.celda;

import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class PosicionCeldaValidaRegla implements Regla<CeldaDominio> {

	private static final Regla<CeldaDominio> INSTANCIA = new PosicionCeldaValidaRegla();

	private PosicionCeldaValidaRegla() {
		super();
	}

	public static Regla<CeldaDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(CeldaDominio dato) {
		if (UtilObjeto.esNulo(dato.getPosicion())) {
			throw new SudokuDominioExepcion("La posicion de una celda no puede estar vacia");
		}
	}

}
