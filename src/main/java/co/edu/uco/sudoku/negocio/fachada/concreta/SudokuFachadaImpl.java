package co.edu.uco.sudoku.negocio.fachada.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.SudokuEnsambladorImpl.obtenerSudokuEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.SudokuDominio;
import co.edu.uco.sudoku.dto.SudokuDTO;
import co.edu.uco.sudoku.negocio.fachada.SudokuFachada;
import co.edu.uco.sudoku.negocio.negocio.SudokuNegocio;
import co.edu.uco.sudoku.negocio.negocio.concreta.SudokuNegocioImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class SudokuFachadaImpl implements SudokuFachada {

	@Override
	public List<SudokuDTO> consultar(SudokuDTO sudokuDTO) {

		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			SudokuNegocio negocio = new SudokuNegocioImpl(factoria);
			SudokuDominio sudokuDominio = obtenerSudokuEnsablador().ensamblarDominioDesdeDTO(sudokuDTO);
			SudokuEntidad sudokuEntidad = obtenerSudokuEnsablador().ensamblarEntidad(sudokuDominio).get();

			List<SudokuDominio> listaDominios = negocio.consultar(sudokuEntidad);

			return obtenerSudokuEnsablador().ensamblarDTOs(listaDominios);

		} catch (SudokuExepcion exepcion) {
			throw exepcion;
		} catch (Exception exepcion) {
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cabo la consulta de la informacion de los sudokus");
		} finally {
			factoria.cerrarConeccion();
		}
	}

	@Override
	public void registrar(SudokuDTO sudokuDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(int codigoPlantilla) {
		// TODO Auto-generated method stub
		
	}



}
