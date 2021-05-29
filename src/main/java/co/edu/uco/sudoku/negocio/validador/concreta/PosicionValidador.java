package co.edu.uco.sudoku.negocio.validador.concreta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.negocio.validador.Validador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.posicion.CodigoPosicionValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.posicion.ColumnaPosicionValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.posicion.FilaPosicionValidarRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class PosicionValidador implements Validador<PosicionDominio> {

	private Map<TipoValidacion, List<Regla<PosicionDominio>>> reglas = new HashMap<>();
	public static final Validador<PosicionDominio> instancia = new PosicionValidador();

	private PosicionValidador() {
		reglas.put(TipoValidacion.CREACION, obteneReglasCreacion());
		reglas.put(TipoValidacion.ACTUALIZACION, obteneReglasActializacion());
		reglas.put(TipoValidacion.ELIMINACION, obteneReglasEliminacion());
	}

	public static Validador<PosicionDominio> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void validar(PosicionDominio domino, TipoValidacion validacion) {
		for (Regla<PosicionDominio> regla : obteneReglas(validacion)) {
			regla.validar(domino);
		}
	}

	private List<Regla<PosicionDominio>> obteneReglas(TipoValidacion tipoValidacion) {
		if (reglas.containsKey(tipoValidacion)) {
			throw new SudokuNegocioExeption("No existen reglas para el tipo de validacion : " + tipoValidacion);
		}

		return reglas.get(tipoValidacion);
	}

	private List<Regla<PosicionDominio>> obteneReglasCreacion() {

		List<Regla<PosicionDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoPosicionValidarRegla.obtenerInstancia());
		listadoReglas.add(ColumnaPosicionValidarRegla.obtenerInstancia());
		listadoReglas.add(FilaPosicionValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<PosicionDominio>> obteneReglasActializacion() {

		List<Regla<PosicionDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoPosicionValidarRegla.obtenerInstancia());
		listadoReglas.add(ColumnaPosicionValidarRegla.obtenerInstancia());
		listadoReglas.add(FilaPosicionValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<PosicionDominio>> obteneReglasEliminacion() {

		List<Regla<PosicionDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoPosicionValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

}
