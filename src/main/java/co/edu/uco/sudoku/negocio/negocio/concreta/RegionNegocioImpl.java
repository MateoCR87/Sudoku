package co.edu.uco.sudoku.negocio.negocio.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.RegionEnsambladorImpl.obtenerRegionEnsablador;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.negocio.RegionNegocio;;

public class RegionNegocioImpl implements RegionNegocio {

	private SudokuDatosFactoria regionDatos;

	public RegionNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.regionDatos = factoriaDatos;
	}

	@Override
	public List<RegionDominio> consultar(RegionEntidad entidad) {
		return obtenerRegionEnsablador()
				.ensamblarDominiosDesdeEntidad(regionDatos.obtenerRegionDatos().consultar(entidad));
	}

	@Override
	public void registrar(RegionDominio region) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarCeldas(List<CeldaDominio> celdas, RegionDominio region) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CeldaDominio> obtenerCeldas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validarSiNumeroExiste(int codigoRegion, int numero) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<RegionDominio> obtenerCeldaDeterminada(int codigoRegion, PosicionDominio posicionCelda) {
		// TODO Auto-generated method stub
		return null;
	}

}
