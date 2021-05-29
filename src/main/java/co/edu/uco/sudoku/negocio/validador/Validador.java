package co.edu.uco.sudoku.negocio.validador;

import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;

public interface Validador<D> {

	void validar(D domino, TipoValidacion validacion);

}
