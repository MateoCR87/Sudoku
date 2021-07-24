package co.edu.uco.sudoku.negocio.fachada.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.PosicionEnsambladorImpl.obtenerPosicionEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.dto.PosicionDTO;
import co.edu.uco.sudoku.negocio.fachada.PosicionFachada;
import co.edu.uco.sudoku.negocio.negocio.PosicionNegocio;
import co.edu.uco.sudoku.negocio.negocio.concreta.PosicionNegocioImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class PosicionFachadaImpl implements PosicionFachada {

	@Override
	public List<PosicionDTO> consultar(PosicionDTO posicionDTO) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			PosicionNegocio negocio = new PosicionNegocioImpl(factoria);
			PosicionDominio posicionDominio = obtenerPosicionEnsablador().ensamblarDominioDesdeDTO(posicionDTO);
			PosicionEntidad posicionEntidad = obtenerPosicionEnsablador().ensamblarEntidad(posicionDominio).get();

			List<PosicionDominio> listaDominios = negocio.consultar(posicionEntidad);

			return obtenerPosicionEnsablador().ensamblarDTOs(listaDominios);

		} catch (SudokuExepcion exepcion) {
			throw exepcion;
		} catch (Exception exepcion) {
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cabo la consulta de la informacion de las posiciones");
		} finally {
			factoria.cerrarConeccion();
		}
	}

}
