package co.edu.uco.sudoku.negocio.validador.concreta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uco.sudoku.dominio.ModalidadJuegoDominio;
import co.edu.uco.sudoku.negocio.validador.Validador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.modalidadJuego.CodigoModalidadJuegoValidarRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.modalidadJuego.NombreModalidadJuegoValidarRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class ModalidadJuegoValidador implements Validador<ModalidadJuegoDominio> {

	private Map<TipoValidacion, List<Regla<ModalidadJuegoDominio>>> reglas = new HashMap<>();
	public static final Validador<ModalidadJuegoDominio> instancia = new ModalidadJuegoValidador();

	private ModalidadJuegoValidador() {
		reglas.put(TipoValidacion.CREACION, obteneReglasCreacion());
		reglas.put(TipoValidacion.ACTUALIZACION, obteneReglasActializacion());
		reglas.put(TipoValidacion.ELIMINACION, obteneReglasEliminacion());
	}

	public static Validador<ModalidadJuegoDominio> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void validar(ModalidadJuegoDominio domino, TipoValidacion validacion) {
		for (Regla<ModalidadJuegoDominio> regla : obteneReglas(validacion)) {
			regla.validar(domino);
		}
	}

	private List<Regla<ModalidadJuegoDominio>> obteneReglas(TipoValidacion tipoValidacion) {
		if (reglas.containsKey(tipoValidacion)) {
			throw new SudokuNegocioExeption("No existen reglas para el tipo de validacion : " + tipoValidacion);
		}

		return reglas.get(tipoValidacion);
	}

	private List<Regla<ModalidadJuegoDominio>> obteneReglasCreacion() {

		List<Regla<ModalidadJuegoDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoModalidadJuegoValidarRegla.obtenerInstancia());
		listadoReglas.add(NombreModalidadJuegoValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<ModalidadJuegoDominio>> obteneReglasActializacion() {

		List<Regla<ModalidadJuegoDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoModalidadJuegoValidarRegla.obtenerInstancia());
		listadoReglas.add(NombreModalidadJuegoValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<ModalidadJuegoDominio>> obteneReglasEliminacion() {

		List<Regla<ModalidadJuegoDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoModalidadJuegoValidarRegla.obtenerInstancia());

		return listadoReglas;

	}

}
