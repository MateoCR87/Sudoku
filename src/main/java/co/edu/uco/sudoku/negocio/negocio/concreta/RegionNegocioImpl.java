package co.edu.uco.sudoku.negocio.negocio.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.RegionEnsambladorImpl.obtenerRegionEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.RegionDatos;
import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.negocio.RegionNegocio;
import co.edu.uco.sudoku.negocio.validador.concreta.RegionValidador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class RegionNegocioImpl implements RegionNegocio {

	private RegionDatos regionDatos;

	public RegionNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.regionDatos = factoriaDatos.obtenerRegionDatos();
	}

	@Override
	public List<RegionDominio> consultar(RegionEntidad entidad) {
		return obtenerRegionEnsablador().ensamblarDominiosDesdeEntidad(regionDatos.consultar(entidad));
	}

	@Override
	public void registrar(RegionDominio region) {
		RegionValidador.obtenerInstancia().validar(region, TipoValidacion.CREACION);
		asegurarRegionNoExisteConMismoCodigo(region);

		regionDatos.crear(obtenerRegionEnsablador().ensamblarEntidad(region).orElse(null));
	}

	private void asegurarRegionNoExisteConMismoCodigo(RegionDominio region) {
		RegionEntidad regionEntidad = RegionEntidad.crear().setCodigo(region.getCodigo());

		if (!consultar(regionEntidad).isEmpty()) {
			throw new SudokuNegocioExeption("Ya existe una region con el numero mismo codigo :" + region.getCodigo());
		}
	}

	@Override
	public void eliminar(RegionDominio region) {
		RegionValidador.obtenerInstancia().validar(region, TipoValidacion.ELIMINACION);
		asegurarQueRegionExistaConCodigo(region, TipoValidacion.ELIMINACION);

		regionDatos.eliminar(obtenerRegionEnsablador().ensamblarEntidad(region).orElse(null));
	}

	private void asegurarQueRegionExistaConCodigo(RegionDominio region, TipoValidacion validacion) {
		RegionEntidad regionEntidad = RegionEntidad.crear().setCodigo(region.getCodigo());

		if (consultar(regionEntidad).isEmpty()) {
			throw new SudokuNegocioExeption("No existe una region con el codigo : " + region.getCodigo()
					+ " para poder hacer una " + validacion);
		}
	}
}
