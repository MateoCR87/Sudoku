package co.edu.uco.sudoku.negocio.validador.concreta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uco.sudoku.dominio.SudokuDominio;
import co.edu.uco.sudoku.negocio.validador.Validador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.sudoku.CodigoSudokuValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.sudoku.RegionesValidasSudokuRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class SudokuValidador implements Validador<SudokuDominio> {

	private Map<TipoValidacion, List<Regla<SudokuDominio>>> reglas = new HashMap<>();
	public static final Validador<SudokuDominio> instancia = new SudokuValidador();

	private SudokuValidador() {
		reglas.put(TipoValidacion.CREACION, obteneReglasCreacion());
		reglas.put(TipoValidacion.ACTUALIZACION, obteneReglasActializacion());
		reglas.put(TipoValidacion.ELIMINACION, obteneReglasEliminacion());
	}

	public static Validador<SudokuDominio> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void validar(SudokuDominio domino, TipoValidacion validacion) {
		for (Regla<SudokuDominio> regla : obteneReglas(validacion)) {
			regla.validar(domino);
		}
	}

	private List<Regla<SudokuDominio>> obteneReglas(TipoValidacion tipoValidacion) {
		if (reglas.containsKey(tipoValidacion)) {
			throw new SudokuNegocioExeption("No existen reglas para el tipo de validacion : " + tipoValidacion);
		}

		return reglas.get(tipoValidacion);
	}

	private List<Regla<SudokuDominio>> obteneReglasCreacion() {

		List<Regla<SudokuDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoSudokuValidarRegla.obtenerInstancia());
		listadoReglas.add(RegionesValidasSudokuRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<SudokuDominio>> obteneReglasActializacion() {

		List<Regla<SudokuDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoSudokuValidarRegla.obtenerInstancia());
		listadoReglas.add(RegionesValidasSudokuRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<SudokuDominio>> obteneReglasEliminacion() {

		List<Regla<SudokuDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoSudokuValidarRegla.obtenerInstancia());

		return listadoReglas;

	}
}
