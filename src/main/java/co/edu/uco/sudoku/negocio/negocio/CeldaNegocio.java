package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.dominio.CeldaDominio;

public interface CeldaNegocio {

	List<CeldaDominio> consultar(CeldaEntidad entidad);

	Optional<CeldaDominio> modificarNumero(int numero, CeldaDominio celda);

	void quitarNumero(CeldaDominio celda);

}
