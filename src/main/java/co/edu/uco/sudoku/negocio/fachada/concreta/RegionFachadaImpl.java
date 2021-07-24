package co.edu.uco.sudoku.negocio.fachada.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.RegionEnsambladorImpl.obtenerRegionEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.dto.RegionDTO;
import co.edu.uco.sudoku.negocio.fachada.CeldaPorRegionFachada;
import co.edu.uco.sudoku.negocio.fachada.RegionFachada;
import co.edu.uco.sudoku.negocio.negocio.RegionNegocio;
import co.edu.uco.sudoku.negocio.negocio.concreta.RegionNegocioImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class RegionFachadaImpl implements RegionFachada {

	@Override
	public List<RegionDTO> consultar(RegionDTO regionDTO) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			RegionNegocio negocio = new RegionNegocioImpl(factoria);
			RegionDominio regionDominio = obtenerRegionEnsablador().ensamblarDominioDesdeDTO(regionDTO);
			RegionEntidad regionEntidad = obtenerRegionEnsablador().ensamblarEntidad(regionDominio).get();

			List<RegionDominio> listaDominios = negocio.consultar(regionEntidad );

			return obtenerRegionEnsablador().ensamblarDTOs(listaDominios);

		} catch (SudokuExepcion exepcion) {
			throw exepcion;
		} catch (Exception exepcion) {
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cabo la consulta de la informacion de las regiones");
		} finally {
			factoria.cerrarConeccion();
		}
	}

	@Override
	public void registrar(RegionDTO regionDTO) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			factoria.iniciarTransaccion();

			RegionNegocio negocio = new RegionNegocioImpl(factoria);			
			negocio.registrar( obtenerRegionEnsablador().ensamblarDominioDesdeDTO(regionDTO));
			registrarCeldasPorRegion(regionDTO);
			
			factoria.confirmarTransaccion();
		} catch (SudokuExepcion exepcion) {
			factoria.cancelarTransaccion();
			throw exepcion;
		} catch (Exception exepcion) {
			factoria.cancelarTransaccion();
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cambo el registro de la informacion de una region");
		} finally {
			factoria.cerrarConeccion();
		}
	}

	private void registrarCeldasPorRegion(RegionDTO regionDTO) {
		CeldaPorRegionFachada registrar = new CeldaPorRegionFachadaImpl();
		registrar.registrar(regionDTO);
	}

	@Override
	public void Eliminar(RegionDTO regionDTO) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			factoria.iniciarTransaccion();

			RegionNegocio negocio = new RegionNegocioImpl(factoria);
			
			negocio.eliminar( obtenerRegionEnsablador().ensamblarDominioDesdeDTO(regionDTO));
			eliminarCeldasPorRegion(regionDTO);
			
			factoria.confirmarTransaccion();
		} catch (SudokuExepcion exepcion) {
			factoria.cancelarTransaccion();
			throw exepcion;
		} catch (Exception exepcion) {
			factoria.cancelarTransaccion();
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cambo la eliminacion de la informacion de una region");
		} finally {
			factoria.cerrarConeccion();
		}				
	}
	
	private void eliminarCeldasPorRegion(RegionDTO regionDTO) {
		CeldaPorRegionFachada eliminar = new CeldaPorRegionFachadaImpl();
		eliminar.eliminar(regionDTO.getCodigo());
	}

}
