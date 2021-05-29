package co.edu.uco.sudoku.negocio.validador.concreta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.negocio.validador.Validador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.celda.CodigoCeldaValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.celda.EsPistaCeldaValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.celda.NumeroCeldaValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.celda.PosicionCeldaValidaRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class CeldaValidador implements Validador<CeldaDominio> {

	private Map<TipoValidacion, List<Regla<CeldaDominio>>> reglas = new HashMap<>();
	public static final Validador<CeldaDominio> instancia = new CeldaValidador();

	private CeldaValidador() {
		reglas.put(TipoValidacion.CREACION, obteneReglasCreacion());
		reglas.put(TipoValidacion.ACTUALIZACION, obteneReglasActializacion());
		reglas.put(TipoValidacion.ELIMINACION, obteneReglasEliminacion());
	}

	public static Validador<CeldaDominio> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void validar(CeldaDominio domino, TipoValidacion validacion) {
		for (Regla<CeldaDominio> regla : obteneReglas(validacion)) {
			regla.validar(domino);
		}
	}

	private List<Regla<CeldaDominio>> obteneReglas(TipoValidacion tipoValidacion) {
		if (reglas.containsKey(tipoValidacion)) {
			throw new SudokuNegocioExeption("No existen reglas para el tipo de validacion : " + tipoValidacion);
		}

		return reglas.get(tipoValidacion);
	}

	private List<Regla<CeldaDominio>> obteneReglasCreacion() {

		List<Regla<CeldaDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoCeldaValidarRegla.obtenerInstancia());
		listadoReglas.add(EsPistaCeldaValidarRegla.obtenerInstancia());
		listadoReglas.add(NumeroCeldaValidarRegla.obtenerInstancia());
		listadoReglas.add(PosicionCeldaValidaRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<CeldaDominio>> obteneReglasActializacion() {

		List<Regla<CeldaDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoCeldaValidarRegla.obtenerInstancia());
		listadoReglas.add(EsPistaCeldaValidarRegla.obtenerInstancia());
		listadoReglas.add(NumeroCeldaValidarRegla.obtenerInstancia());
		listadoReglas.add(PosicionCeldaValidaRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<CeldaDominio>> obteneReglasEliminacion() {

		List<Regla<CeldaDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoCeldaValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

}
