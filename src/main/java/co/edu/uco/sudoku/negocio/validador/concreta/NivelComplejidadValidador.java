package co.edu.uco.sudoku.negocio.validador.concreta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uco.sudoku.dominio.NivelComplejidadDominio;
import co.edu.uco.sudoku.negocio.validador.Validador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.nivelComplejidad.CodigoNivelComplejidadValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.nivelComplejidad.DescripcionNivelComplejidadValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.nivelComplejidad.NombreNivelComplejidadValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.nivelComplejidad.TiempoNivelComplejidadValidarRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class NivelComplejidadValidador implements Validador<NivelComplejidadDominio> {

	private Map<TipoValidacion, List<Regla<NivelComplejidadDominio>>> reglas = new HashMap<>();
	public static final Validador<NivelComplejidadDominio> instancia = new NivelComplejidadValidador();

	private NivelComplejidadValidador() {
		reglas.put(TipoValidacion.CREACION, obteneReglasCreacion());
		reglas.put(TipoValidacion.ACTUALIZACION, obteneReglasActializacion());
		reglas.put(TipoValidacion.ELIMINACION, obteneReglasEliminacion());
	}

	public static Validador<NivelComplejidadDominio> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void validar(NivelComplejidadDominio domino, TipoValidacion validacion) {
		for (Regla<NivelComplejidadDominio> regla : obteneReglas(validacion)) {
			regla.validar(domino);
		}
	}

	private List<Regla<NivelComplejidadDominio>> obteneReglas(TipoValidacion tipoValidacion) {
		if (reglas.containsKey(tipoValidacion)) {
			throw new SudokuNegocioExeption("No existen reglas para el tipo de validacion : " + tipoValidacion);
		}

		return reglas.get(tipoValidacion);
	}

	private List<Regla<NivelComplejidadDominio>> obteneReglasCreacion() {

		List<Regla<NivelComplejidadDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoNivelComplejidadValidarRegla.obtenerInstancia());
		listadoReglas.add(DescripcionNivelComplejidadValidarRegla.obtenerInstancia());
		listadoReglas.add(NombreNivelComplejidadValidarRegla.obtenerInstancia());
		listadoReglas.add(TiempoNivelComplejidadValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<NivelComplejidadDominio>> obteneReglasActializacion() {

		List<Regla<NivelComplejidadDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoNivelComplejidadValidarRegla.obtenerInstancia());
		listadoReglas.add(DescripcionNivelComplejidadValidarRegla.obtenerInstancia());
		listadoReglas.add(NombreNivelComplejidadValidarRegla.obtenerInstancia());
		listadoReglas.add(TiempoNivelComplejidadValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<NivelComplejidadDominio>> obteneReglasEliminacion() {

		List<Regla<NivelComplejidadDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoNivelComplejidadValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

}
