package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.NivelComplejidadEntidad;
import co.edu.uco.sudoku.dominio.NivelComplejidadDominio;

public interface NivelComplejidadNegocio {

	List<NivelComplejidadDominio> consultar(NivelComplejidadEntidad entidad);

	void registrar(NivelComplejidadDominio nivelComplejidad);

	void modificar(NivelComplejidadDominio nivelComplejidad);

}
