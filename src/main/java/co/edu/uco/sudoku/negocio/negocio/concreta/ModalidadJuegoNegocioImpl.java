package co.edu.uco.sudoku.negocio.negocio.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.ModalidadJuegoEnsambladorImpl.obtenerJModalidaJuegoEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.ModalidadJuegoDatos;
import co.edu.uco.sudoku.datos.entidad.ModalidadJuegoEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.ModalidadJuegoDominio;
import co.edu.uco.sudoku.negocio.negocio.ModalidadJuegoNegocio;;

public class ModalidadJuegoNegocioImpl implements ModalidadJuegoNegocio {

	private ModalidadJuegoDatos modalidadDatos;

	public ModalidadJuegoNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.modalidadDatos = factoriaDatos.obtenerModalidadJuegoDatos();
	}

	@Override
	public List<ModalidadJuegoDominio> consultar(ModalidadJuegoEntidad entidad) {
		return obtenerJModalidaJuegoEnsablador().ensamblarDominiosDesdeEntidad(modalidadDatos.consultar(entidad));
	}

}
