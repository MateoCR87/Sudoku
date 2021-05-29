package co.edu.uco.sudoku.negocio.negocio.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.PosicionEnsambladorImpl.obtenerPosicionEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.negocio.negocio.PosicionNegocio;;

public class PosicionNegocioImpl implements PosicionNegocio {

	private SudokuDatosFactoria posicionDatos;

	public PosicionNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.posicionDatos = factoriaDatos;
	}

	@Override
	public List<PosicionDominio> consultar(PosicionEntidad entidad) {
		return obtenerPosicionEnsablador()
				.ensamblarDominiosDesdeEntidad(posicionDatos.obtenerPosicionDatos().consultar(entidad));
	}

}
