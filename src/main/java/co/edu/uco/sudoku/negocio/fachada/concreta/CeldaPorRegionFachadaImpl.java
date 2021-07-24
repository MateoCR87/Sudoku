package co.edu.uco.sudoku.negocio.fachada.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.RegionEnsambladorImpl.obtenerRegionEnsablador;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.dto.RegionDTO;
import co.edu.uco.sudoku.negocio.fachada.CeldaPorRegionFachada;
import co.edu.uco.sudoku.relacion.muchos.muchos.CeldaPorRegion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class CeldaPorRegionFachadaImpl implements CeldaPorRegionFachada{

	@Override
	public void registrar(RegionDTO regionDTO) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			factoria.iniciarTransaccion();

			for(int fila = 0; fila<3 ; fila++) {
				for(int columna = 0; columna<3 ; columna++) {
					factoria.obtenerCeldaPorRegionDatos().crear(CeldaPorRegion.crear(regionDTO.getCeldas()[fila][columna].getCodigo(), regionDTO.getCodigo()));
				}
			}
			
			factoria.confirmarTransaccion();
		} catch (SudokuExepcion exepcion) {
			factoria.cancelarTransaccion();
			throw exepcion;
		} catch (Exception exepcion) {
			factoria.cancelarTransaccion();
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cambo el registro de la informacion de las celdas por region");
		} finally {
			factoria.cerrarConeccion();
		}		
	}

	@Override
	public RegionDTO consultar(int codigoRegion) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			List<CeldaPorRegion> celdaPorRegion = factoria.obtenerCeldaPorRegionDatos().consultar(CeldaPorRegion.crear().setCodigoRegion(codigoRegion));
			RegionEntidad regionEntidad = RegionEntidad.crear().setCodigo(codigoRegion);
			CeldaEntidad[][] matrizCeldas = new CeldaEntidad[3][3];
			List<CeldaEntidad> celdas= new ArrayList<>();
				
			for (CeldaPorRegion codigoCelda : celdaPorRegion) {
				celdas.add(factoria.obtenerCeldaDatos().consultar(CeldaEntidad.crear().setCodigo(codigoCelda.getCodigoCelda())).stream().filter(celdaConsultada -> celdaConsultada.getCodigo() == codigoCelda.getCodigoCelda()).findFirst().get());
			}
			
			for (CeldaEntidad celdaEntidad : celdas) {
				matrizCeldas[celdaEntidad.getPosicion().getFila()][celdaEntidad.getPosicion().getColumna()] = celdaEntidad;
			}
			
			regionEntidad.setCeldas(matrizCeldas);

			RegionDominio regionDominio = obtenerRegionEnsablador().ensamblarDominioDesdeEntidad(regionEntidad).get();
			return obtenerRegionEnsablador().ensamblarDTO(regionDominio);

		} catch (SudokuExepcion exepcion) {
			throw exepcion;
		} catch (Exception exepcion) {
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cabo la consulta de la informacion de la region");
		} finally {
			factoria.cerrarConeccion();
		}
	}

	@Override
	public void eliminar(int codigoRegion) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			factoria.iniciarTransaccion();
			factoria.obtenerCeldaPorRegionDatos().eliminar(CeldaPorRegion.crear().setCodigoRegion(codigoRegion));
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

}
