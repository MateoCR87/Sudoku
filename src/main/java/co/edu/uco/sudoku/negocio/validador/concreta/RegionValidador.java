package co.edu.uco.sudoku.negocio.validador.concreta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.validador.Validador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.region.CeldasRegionValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.region.CodigoRegionValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.region.PosicionRegionValidarRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class RegionValidador implements Validador<RegionDominio> {

	private Map<TipoValidacion, List<Regla<RegionDominio>>> reglas = new HashMap<>();
	public static final Validador<RegionDominio> instancia = new RegionValidador();

	private RegionValidador() {
		reglas.put(TipoValidacion.CREACION, obteneReglasCreacion());
		reglas.put(TipoValidacion.ACTUALIZACION, obteneReglasActializacion());
		reglas.put(TipoValidacion.ELIMINACION, obteneReglasEliminacion());
	}

	public static Validador<RegionDominio> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void validar(RegionDominio domino, TipoValidacion validacion) {
		for (Regla<RegionDominio> regla : obteneReglas(validacion)) {
			regla.validar(domino);
		}
	}

	private List<Regla<RegionDominio>> obteneReglas(TipoValidacion tipoValidacion) {
		if (reglas.containsKey(tipoValidacion)) {
			throw new SudokuNegocioExeption("No existen reglas para el tipo de validacion : " + tipoValidacion);
		}

		return reglas.get(tipoValidacion);
	}

	private List<Regla<RegionDominio>> obteneReglasCreacion() {

		List<Regla<RegionDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CeldasRegionValidarRegla.obtenerInstancia());
		listadoReglas.add(CodigoRegionValidarRegla.obtenerInstancia());
		listadoReglas.add(PosicionRegionValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<RegionDominio>> obteneReglasActializacion() {

		List<Regla<RegionDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CeldasRegionValidarRegla.obtenerInstancia());
		listadoReglas.add(CodigoRegionValidarRegla.obtenerInstancia());
		listadoReglas.add(PosicionRegionValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<RegionDominio>> obteneReglasEliminacion() {

		List<Regla<RegionDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CeldasRegionValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

}
