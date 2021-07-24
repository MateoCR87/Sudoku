package co.edu.uco.sudoku.negocio.fachada.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.SudokuEnsambladorImpl.obtenerSudokuEnsablador;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.SudokuDominio;
import co.edu.uco.sudoku.dto.SudokuDTO;
import co.edu.uco.sudoku.negocio.fachada.RegionPorSudokuFachada;
import co.edu.uco.sudoku.relacion.muchos.muchos.RegionPorSudoku;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class RegionPorSudokuFachadaImpl implements RegionPorSudokuFachada{

	@Override
	public void registrar(SudokuDTO sudokuDTO) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			factoria.iniciarTransaccion();

			for(int fila = 0; fila<3 ; fila++) {
				for(int columna = 0; columna<3 ; columna++) {
					factoria.obtenerRegionPorSudokuDatos().crear(RegionPorSudoku.crear(sudokuDTO.getRegiones()[fila][columna].getCodigo(), sudokuDTO.getCodigo()));
				}
			}
			
			factoria.confirmarTransaccion();
		} catch (SudokuExepcion exepcion) {
			factoria.cancelarTransaccion();
			throw exepcion;
		} catch (Exception exepcion) {
			factoria.cancelarTransaccion();
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cambo el registro de la informacion de las regiones por sudoku");
		} finally {
			factoria.cerrarConeccion();
		}				
	}

	@Override
	public void eliminar(int codigoSudoku) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			factoria.iniciarTransaccion();
			factoria.obtenerRegionPorSudokuDatos().eliminar(RegionPorSudoku.crear().setCodigoRegion(codigoSudoku));
			factoria.confirmarTransaccion();
		} catch (SudokuExepcion exepcion) {
			factoria.cancelarTransaccion();
			throw exepcion;
		} catch (Exception exepcion) {
			factoria.cancelarTransaccion();
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cambo la eliminacion de la informacion de una region por sudoku");
		} finally {
			factoria.cerrarConeccion();
		}
	}

	@Override
	public SudokuDTO consultar(int codigoSudoku) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			List<RegionPorSudoku> regionPorSudoku = factoria.obtenerRegionPorSudokuDatos().consultar(RegionPorSudoku.crear().setCodigoRegion(codigoSudoku));
			SudokuEntidad sudokuEntidad = SudokuEntidad.crear().setCodigo(codigoSudoku);
			RegionEntidad[][] matrizRegiones = new RegionEntidad[3][3];
			List<RegionEntidad> regiones= new ArrayList<>();
				
			for (RegionPorSudoku codigoRegion : regionPorSudoku) {
				regiones.add(factoria.obtenerRegionDatos().consultar(RegionEntidad.crear().setCodigo(codigoRegion.getCodigoRegion())).stream().filter(regionConsultada -> regionConsultada.getCodigo() == codigoRegion.getCodigoRegion()).findFirst().get());
			}
			
			for (RegionEntidad regionesEntidad : regiones) {
				matrizRegiones[regionesEntidad.getPosicion().getFila()][regionesEntidad.getPosicion().getColumna()] = regionesEntidad;
			}
			
			sudokuEntidad.setRegiones(matrizRegiones);

			SudokuDominio sudokuDominio = obtenerSudokuEnsablador().ensamblarDominioDesdeEntidad(sudokuEntidad).get();
			return obtenerSudokuEnsablador().ensamblarDTO(sudokuDominio);

		} catch (SudokuExepcion exepcion) {
			throw exepcion;
		} catch (Exception exepcion) {
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cabo la consulta de la informacion de las regiones del sudoku");
		} finally {
			factoria.cerrarConeccion();
		}
	}

}
