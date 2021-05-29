package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.dominio.PosicionDominio;

public interface PosicionNegocio {

	List<PosicionDominio> consultar(PosicionEntidad entidad);

}
