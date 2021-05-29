package co.edu.uco.sudoku.negocio.validador.concreta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uco.sudoku.dominio.PartidaDominio;
import co.edu.uco.sudoku.negocio.validador.Validador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.partida.CodigoPartidaValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.partida.DuracionPartidaValidarNegocio;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.partida.FechaFinalPartidaValidarNegocio;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.partida.FechaInicialPartidaValidarNegocio;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.partida.JuegoCompletadoPartidaValidarNegocio;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.partida.JugadorPartidaValidarNegocio;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.partida.ModalidadJuegoPartidaValidarNegocio;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.partida.PlantillaPartidaValidarNegocio;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class PartidaValidador implements Validador<PartidaDominio> {

	private Map<TipoValidacion, List<Regla<PartidaDominio>>> reglas = new HashMap<>();
	public static final Validador<PartidaDominio> instancia = new PartidaValidador();

	private PartidaValidador() {
		reglas.put(TipoValidacion.CREACION, obteneReglasCreacion());
		reglas.put(TipoValidacion.ACTUALIZACION, obteneReglasActializacion());
		reglas.put(TipoValidacion.ELIMINACION, obteneReglasEliminacion());
	}

	public static Validador<PartidaDominio> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void validar(PartidaDominio domino, TipoValidacion validacion) {
		for (Regla<PartidaDominio> regla : obteneReglas(validacion)) {
			regla.validar(domino);
		}
	}

	private List<Regla<PartidaDominio>> obteneReglas(TipoValidacion tipoValidacion) {
		if (reglas.containsKey(tipoValidacion)) {
			throw new SudokuNegocioExeption("No existen reglas para el tipo de validacion : " + tipoValidacion);
		}

		return reglas.get(tipoValidacion);
	}

	private List<Regla<PartidaDominio>> obteneReglasCreacion() {

		List<Regla<PartidaDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoPartidaValidarRegla.obtenerInstancia());
		listadoReglas.add(DuracionPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(FechaFinalPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(FechaInicialPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(JuegoCompletadoPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(JugadorPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(ModalidadJuegoPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(PlantillaPartidaValidarNegocio.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<PartidaDominio>> obteneReglasActializacion() {

		List<Regla<PartidaDominio>> listadoReglas = new ArrayList<>();

		listadoReglas.add(CodigoPartidaValidarRegla.obtenerInstancia());
		listadoReglas.add(DuracionPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(FechaFinalPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(FechaInicialPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(JuegoCompletadoPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(JugadorPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(ModalidadJuegoPartidaValidarNegocio.obtenerInstancia());
		listadoReglas.add(PlantillaPartidaValidarNegocio.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<PartidaDominio>> obteneReglasEliminacion() {

		List<Regla<PartidaDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoPartidaValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

}
