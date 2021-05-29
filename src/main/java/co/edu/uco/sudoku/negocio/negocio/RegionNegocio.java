package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.dominio.RegionDominio;

public interface RegionNegocio {

	List<RegionDominio> consultar(RegionEntidad entidad);

	void registrar(RegionDominio region);

	void registrarCeldas(List<CeldaDominio> celdas, RegionDominio region);

	List<CeldaDominio> obtenerCeldas();

	Optional<RegionDominio> obtenerCeldaDeterminada(int codigoRegion, PosicionDominio posicionCelda);

	boolean validarSiNumeroExiste(int codigoRegion, int numero);
}
