package co.edu.uco.sudoku.negocio.negocio.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.CeldaEnsambladorImpl.obtenerCeldaEnsablador;

import java.util.List;
import java.util.Optional;

import co.edu.uco.sudoku.datos.CeldaDatos;
import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.negocio.negocio.CeldaNegocio;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.PosicionEnsambladorImpl;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.celda.NumeroCeldaValidarRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class CeldaNegocioImpl implements CeldaNegocio {

	private CeldaDatos celdaDatos;

	public CeldaNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.celdaDatos = factoriaDatos.obtenerCeldaDatos();
	}

	@Override
	public List<CeldaDominio> consultar(CeldaEntidad entidad) {
		return obtenerCeldaEnsablador().ensamblarDominiosDesdeEntidad(celdaDatos.consultar(entidad));
	}

	@Override
	public Optional<CeldaDominio> modificarNumero(int numero, CeldaDominio celda) {

		if (celda.isEsPista()) {
			throw new SudokuNegocioExeption("No se puede modificar el valor de una celda que es pista.");
		}

		NumeroCeldaValidarRegla.obtenerInstancia().validar(celda);

		CeldaEntidad celdaEntidad = celdaDatos.consultar(CeldaEntidad.crear().setNumero(numero)).stream()
				.filter(celdas -> celdas.getPosicion() == PosicionEnsambladorImpl.obtenerPosicionEnsablador()
						.ensamblarEntidad(celda.getPosicion()).orElse(PosicionEntidad.crear()) && !celdas.isEsPista())
				.findFirst().get();

		return obtenerCeldaEnsablador().ensamblarDominioDesdeEntidad(celdaEntidad);
	}

	@Override
	public void quitarNumero(CeldaDominio celda) {
		if (celda.isEsPista()) {
			throw new SudokuNegocioExeption("No se puede modificar el valor de una celda que es pista.");
		}

		celdaDatos.eliminar(obtenerCeldaEnsablador().ensamblarEntidad(celda).get());
	}
}
