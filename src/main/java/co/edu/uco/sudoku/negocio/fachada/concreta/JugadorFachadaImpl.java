package co.edu.uco.sudoku.negocio.fachada.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.JugadorEnsambladorImpl.obtenerJugadorEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.JugadorDominio;
import co.edu.uco.sudoku.dto.JugadorDTO;
import co.edu.uco.sudoku.negocio.fachada.JugadorFachada;
import co.edu.uco.sudoku.negocio.negocio.JugadorNegocio;
import co.edu.uco.sudoku.negocio.negocio.concreta.JugadorNegocioImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class JugadorFachadaImpl implements JugadorFachada {

	@Override
	public void registrar(JugadorDTO jugadorDto) {

		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			factoria.iniciarTransaccion();

			JugadorNegocio negocio = new JugadorNegocioImpl(factoria);			
			negocio.registrar( obtenerJugadorEnsablador().ensamblarDominioDesdeDTO(jugadorDto));
			
			factoria.confirmarTransaccion();
		} catch (SudokuExepcion exepcion) {
			factoria.cancelarTransaccion();
			throw exepcion;
		} catch (Exception exepcion) {
			factoria.cancelarTransaccion();
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cambo el registro de la informacion de un jugador");
		} finally {
			factoria.cerrarConeccion();
		}

	}

	@Override
	public List<JugadorDTO> consultar(JugadorDTO jugadorDto) {

		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			JugadorNegocio negocio = new JugadorNegocioImpl(factoria);
			JugadorDominio jugadorDominio = obtenerJugadorEnsablador().ensamblarDominioDesdeDTO(jugadorDto);
			JugadorEntidad jugadorEntidad = obtenerJugadorEnsablador().ensamblarEntidad(jugadorDominio).get();

			List<JugadorDominio> listaDominios = negocio.consultar(jugadorEntidad);

			return obtenerJugadorEnsablador().ensamblarDTOs(listaDominios);

		} catch (SudokuExepcion exepcion) {
			throw exepcion;
		} catch (Exception exepcion) {
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cabo la consulta de la informacion de los jugadores");
		} finally {
			factoria.cerrarConeccion();
		}
	}

	@Override
	public void eliminar(JugadorDTO jugadorDto) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			factoria.iniciarTransaccion();

			JugadorNegocio negocio = new JugadorNegocioImpl(factoria);
			
			negocio.eliminar( obtenerJugadorEnsablador().ensamblarDominioDesdeDTO(jugadorDto));
			
			factoria.confirmarTransaccion();
		} catch (SudokuExepcion exepcion) {
			factoria.cancelarTransaccion();
			throw exepcion;
		} catch (Exception exepcion) {
			factoria.cancelarTransaccion();
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cambo la eliminacion de la informacion de un jugador");
		} finally {
			factoria.cerrarConeccion();
		}		
	}

	@Override
	public void actualizar(JugadorDTO jugadorDto) {
		SudokuDatosFactoria factoria = SudokuDatosFactoria.obtenerFactoria();

		try {
			factoria.iniciarTransaccion();

			JugadorNegocio negocio = new JugadorNegocioImpl(factoria);
			
			negocio.actualizar(obtenerJugadorEnsablador().ensamblarDominioDesdeDTO(jugadorDto));
			
			factoria.confirmarTransaccion();
		} catch (SudokuExepcion exepcion) {
			factoria.cancelarTransaccion();
			throw exepcion;
		} catch (Exception exepcion) {
			factoria.cancelarTransaccion();
			throw new SudokuNegocioExeption(
					"se ha presentado un problema trantando de llevar a cambo la actualizacion de la informacion de un jugador");
		} finally {
			factoria.cerrarConeccion();
		}		
	}
	
	

}
