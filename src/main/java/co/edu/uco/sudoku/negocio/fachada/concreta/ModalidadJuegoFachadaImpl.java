package co.edu.uco.sudoku.negocio.fachada.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.ModalidadJuegoEnsambladorImpl.obtenerJModalidaJuegoEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.ModalidadJuegoEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.ModalidadJuegoDominio;
import co.edu.uco.sudoku.dto.ModalidadJuegoDTO;
import co.edu.uco.sudoku.negocio.fachada.ModalidadJuegoFachada;
import co.edu.uco.sudoku.negocio.negocio.concreta.ModalidadJuegoNegocioImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class ModalidadJuegoFachadaImpl implements ModalidadJuegoFachada {

	@Override
	public List<ModalidadJuegoDTO> consultar(ModalidadJuegoDTO modalidadJuegoDTO) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			ModalidadJuegoNegocioImpl negocio = new ModalidadJuegoNegocioImpl(factoria);
			ModalidadJuegoDominio modalidadJuegoDominio = obtenerJModalidaJuegoEnsablador().ensamblarDominioDesdeDTO(modalidadJuegoDTO);
			ModalidadJuegoEntidad jugadorEntidad = obtenerJModalidaJuegoEnsablador().ensamblarEntidad(modalidadJuegoDominio).get();

			List<ModalidadJuegoDominio> listaDominios = negocio.consultar(jugadorEntidad);

			return obtenerJModalidaJuegoEnsablador().ensamblarDTOs(listaDominios);

		} catch (SudokuExepcion exepcion) {
			throw exepcion;
		} catch (Exception exepcion) {
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cabo la consulta de la informacion de las modalidades de juego");
		} finally {
			factoria.cerrarConeccion();
		}
	}

}
