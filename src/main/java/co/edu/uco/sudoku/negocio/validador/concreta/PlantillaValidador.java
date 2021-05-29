package co.edu.uco.sudoku.negocio.validador.concreta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uco.sudoku.dominio.PlantillaDominio;
import co.edu.uco.sudoku.negocio.validador.Validador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.plantilla.CodigoPlantillaValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.plantilla.NivelComplejidadPlantillaValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.plantilla.RegionesPlantillaValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.plantilla.SudokuPlantillaValidarRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class PlantillaValidador implements Validador<PlantillaDominio> {

	private Map<TipoValidacion, List<Regla<PlantillaDominio>>> reglas = new HashMap<>();
	public static final Validador<PlantillaDominio> instancia = new PlantillaValidador();

	private PlantillaValidador() {
		reglas.put(TipoValidacion.CREACION, obteneReglasCreacion());
		reglas.put(TipoValidacion.ACTUALIZACION, obteneReglasActializacion());
		reglas.put(TipoValidacion.ELIMINACION, obteneReglasEliminacion());
	}

	public static Validador<PlantillaDominio> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void validar(PlantillaDominio domino, TipoValidacion validacion) {
		for (Regla<PlantillaDominio> regla : obteneReglas(validacion)) {
			regla.validar(domino);
		}
	}

	private List<Regla<PlantillaDominio>> obteneReglas(TipoValidacion tipoValidacion) {
		if (reglas.containsKey(tipoValidacion)) {
			throw new SudokuNegocioExeption("No existen reglas para el tipo de validacion : " + tipoValidacion);
		}

		return reglas.get(tipoValidacion);
	}

	private List<Regla<PlantillaDominio>> obteneReglasCreacion() {

		List<Regla<PlantillaDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoPlantillaValidarRegla.obtenerInstancia());
		listadoReglas.add(NivelComplejidadPlantillaValidarRegla.obtenerInstancia());
		listadoReglas.add(RegionesPlantillaValidarRegla.obtenerInstancia());
		listadoReglas.add(SudokuPlantillaValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<PlantillaDominio>> obteneReglasActializacion() {

		List<Regla<PlantillaDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoPlantillaValidarRegla.obtenerInstancia());
		listadoReglas.add(NivelComplejidadPlantillaValidarRegla.obtenerInstancia());
		listadoReglas.add(RegionesPlantillaValidarRegla.obtenerInstancia());
		listadoReglas.add(SudokuPlantillaValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<PlantillaDominio>> obteneReglasEliminacion() {

		List<Regla<PlantillaDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoPlantillaValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

}
