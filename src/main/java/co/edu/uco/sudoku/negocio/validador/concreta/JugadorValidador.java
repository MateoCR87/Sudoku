package co.edu.uco.sudoku.negocio.validador.concreta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uco.sudoku.dominio.JugadorDominio;
import co.edu.uco.sudoku.negocio.validador.Validador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador.ClaveJugadorValidadorRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador.CodigoJugadorValidoRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador.CorreoJugadorValidadorRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador.DocumentoIdentificacionJugadorValidoRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.jugador.NombreJugadorValidoRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;

public class JugadorValidador implements Validador<JugadorDominio> {

	private Map<TipoValidacion, List<Regla<JugadorDominio>>> reglas = new HashMap<>();
	public static final Validador<JugadorDominio> instancia = new JugadorValidador();

	private JugadorValidador() {
		reglas.put(TipoValidacion.CREACION, obteneReglasCreacion());
		reglas.put(TipoValidacion.ACTUALIZACION, obteneReglasActializacion());
		reglas.put(TipoValidacion.ELIMINACION, obteneReglasEliminacion());
	}

	public static Validador<JugadorDominio> obtenerInstancia() {
		return instancia;
	}

	@Override
	public void validar(JugadorDominio domino, TipoValidacion validacion) {
		for (Regla<JugadorDominio> regla : obteneReglas(validacion)) {
			regla.validar(domino);
		}
	}

	private List<Regla<JugadorDominio>> obteneReglas(TipoValidacion tipoValidacion) {
		if (!reglas.containsKey(tipoValidacion)) {
			throw new SudokuNegocioExeption("No existen reglas para el tipo de validacion : " + tipoValidacion);
		}

		return reglas.get(tipoValidacion);
	}

	private List<Regla<JugadorDominio>> obteneReglasCreacion() {

		List<Regla<JugadorDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(NombreJugadorValidoRegla.obtenerInstancia());
		listadoReglas.add(CorreoJugadorValidadorRegla.obtenerInstancia());
		listadoReglas.add(DocumentoIdentificacionJugadorValidoRegla.obtenerInstancia());
		listadoReglas.add(ClaveJugadorValidadorRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<JugadorDominio>> obteneReglasActializacion() {

		List<Regla<JugadorDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(CodigoJugadorValidoRegla.obtenerInstancia());
		listadoReglas.add(NombreJugadorValidoRegla.obtenerInstancia());
		listadoReglas.add(DocumentoIdentificacionJugadorValidoRegla.obtenerInstancia());

		return listadoReglas;

	}

	private List<Regla<JugadorDominio>> obteneReglasEliminacion() {

		List<Regla<JugadorDominio>> listadoReglas = new ArrayList<>();
		listadoReglas.add(NombreJugadorValidoRegla.obtenerInstancia());
		listadoReglas.add(CorreoJugadorValidadorRegla.obtenerInstancia());
		listadoReglas.add(DocumentoIdentificacionJugadorValidoRegla.obtenerInstancia());
		listadoReglas.add(ClaveJugadorValidadorRegla.obtenerInstancia());
		return listadoReglas;

	}
}
