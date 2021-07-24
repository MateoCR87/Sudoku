package co.edu.uco.sudoku.negocio.fachada.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.CeldaEnsambladorImpl.obtenerCeldaEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.dto.CeldaDTO;
import co.edu.uco.sudoku.negocio.fachada.CeldaFachada;
import co.edu.uco.sudoku.negocio.negocio.CeldaNegocio;
import co.edu.uco.sudoku.negocio.negocio.concreta.CeldaNegocioImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class CeldaFachadaImpl implements CeldaFachada {

	@Override
	public List<CeldaDTO> consultar(CeldaDTO celdaDTO) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			CeldaNegocio negocio = new CeldaNegocioImpl(factoria);
			CeldaDominio celdaDominio = obtenerCeldaEnsablador().ensamblarDominioDesdeDTO(celdaDTO);
			CeldaEntidad jugadorEntidad = obtenerCeldaEnsablador().ensamblarEntidad(celdaDominio).get();

			List<CeldaDominio> listaDominios = negocio.consultar(jugadorEntidad);

			return obtenerCeldaEnsablador().ensamblarDTOs(listaDominios);

		} catch (SudokuExepcion exepcion) {
			throw exepcion;
		} catch (Exception exepcion) {
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cabo la consulta de la informacion de las celdas");
		} finally {
			factoria.cerrarConeccion();
		}
	}

}
