package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.dominio.RegionDominio;

public interface RegionNegocio {

	List<RegionDominio> consultar(RegionEntidad entidad);

	void registrar(RegionDominio region);

	void eliminar(RegionDominio regionDominio);
}
