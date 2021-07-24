package co.edu.uco.sudoku.negocio.fachada.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.NivelComplejidadEnsambladorImpl.obtenerNivelComplejidadEnsamblador;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.NivelComplejidadEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.NivelComplejidadDominio;
import co.edu.uco.sudoku.dto.NivelComplejidadDTO;
import co.edu.uco.sudoku.negocio.fachada.NivelComplejidadFachada;
import co.edu.uco.sudoku.negocio.negocio.concreta.NivelComplajidadNegocioImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class NivelComplejidadFachadaImpl implements NivelComplejidadFachada {

	@Override
	public List<NivelComplejidadDTO> consultar(NivelComplejidadDTO nivelComplejidadDTO) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			NivelComplajidadNegocioImpl negocio = new NivelComplajidadNegocioImpl(factoria);
			NivelComplejidadDominio modalidadJuegoDominio = obtenerNivelComplejidadEnsamblador().ensamblarDominioDesdeDTO(nivelComplejidadDTO);
			NivelComplejidadEntidad jugadorEntidad = obtenerNivelComplejidadEnsamblador().ensamblarEntidad(modalidadJuegoDominio).get();

			List<NivelComplejidadDominio> listaDominios = negocio.consultar(jugadorEntidad);

			return obtenerNivelComplejidadEnsamblador().ensamblarDTOs(listaDominios);

		} catch (SudokuExepcion exepcion) {
			throw exepcion;
		} catch (Exception exepcion) {
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cabo la consulta de la informacion de los niveles de complejidad");
		} finally {
			factoria.cerrarConeccion();
		}
	}

}
