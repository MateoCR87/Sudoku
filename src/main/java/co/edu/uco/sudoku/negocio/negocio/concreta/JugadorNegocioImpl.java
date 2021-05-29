package co.edu.uco.sudoku.negocio.negocio.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.JugadorEnsambladorImpl.obtenerJugadorEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.JugadorDatos;
import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.JugadorDominio;
import co.edu.uco.sudoku.negocio.negocio.JugadorNegocio;
import co.edu.uco.sudoku.negocio.validador.concreta.JugadorValidador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class JugadorNegocioImpl implements JugadorNegocio {

	private JugadorDatos jugadorDatos;

	public JugadorNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		jugadorDatos = factoriaDatos.obtenerJugadorDatos();
	}

	@Override
	public void registrar(JugadorDominio jugadorDominio) {
		JugadorValidador.obtenerInstancia().validar(jugadorDominio, TipoValidacion.CREACION);
		asegurarJugadorNoExisteConNumeroIdentificacion(jugadorDominio);
		asegurarJugadorNoExisteConCorreo(jugadorDominio);
		
		jugadorDatos.crear(obtenerJugadorEnsablador().ensamblarEntidad(jugadorDominio).get());
	}

	@Override
	public List<JugadorDominio> consultar(JugadorEntidad jugadorEntidad) {
		return obtenerJugadorEnsablador().ensamblarDominiosDesdeEntidad(jugadorDatos.consultar(jugadorEntidad));
	}

	private void asegurarJugadorNoExisteConNumeroIdentificacion(JugadorDominio jugadorDominio) {
		JugadorEntidad jugadorEntidad = JugadorEntidad.crear()
				.setDocumentoIdentificacion(jugadorDominio.getDocumentoIdentificacion());

		if (!consultar(jugadorEntidad).isEmpty()) {
			throw new SudokuNegocioExeption("Ya existe un jugador con el numero de identificacion :"
					+ jugadorDominio.getDocumentoIdentificacion());
		}
	}

	private void asegurarJugadorNoExisteConCorreo(JugadorDominio jugadorDominio) {
		JugadorEntidad jugadorEntidad = JugadorEntidad.crear().setCorreo(jugadorDominio.getCorreo());

		if (!consultar(jugadorEntidad).isEmpty()) {
			throw new SudokuNegocioExeption("Ya existe un jugador con el correo: " + jugadorDominio.getCorreo());
		}
	}

	@Override
	public void eliminar(JugadorDominio jugador) {
		JugadorValidador.obtenerInstancia().validar(jugador, TipoValidacion.ELIMINACION);
		asegurarQueJugadorConCodigoExista(jugador, TipoValidacion.ELIMINACION);
		
		jugadorDatos.eliminar(obtenerJugadorEnsablador().ensamblarEntidad(jugador).get());		
	}
	
	private void asegurarQueJugadorConCodigoExista(JugadorDominio jugadorDominio, TipoValidacion validacion) {
		JugadorEntidad jugadorEntidad = JugadorEntidad.crear().setCodigo(jugadorDominio.getCodigo());

		if (consultar(jugadorEntidad).isEmpty()) {
			throw new SudokuNegocioExeption("No existe un jugador con el codigo : " + jugadorDominio.getCodigo() + " para poder  hacer una " + validacion);
		}
	}

	@Override
	public void actualizar(JugadorDominio jugador) {
		JugadorEntidad jugadorAIngresar = asignarValoresFaltantesALaActualizacion(jugador);
		
		JugadorValidador.obtenerInstancia().validar(obtenerJugadorEnsablador().ensamblarDominioDesdeEntidad(jugadorAIngresar).get(), TipoValidacion.ACTUALIZACION);
		asegurarQueJugadorConCodigoExista(jugador, TipoValidacion.ACTUALIZACION);
		asegurarQueNoSeModifiquenDatosRepetidosPorOtrosUsuarios(jugador);
		
		jugadorDatos.actualizar(jugadorAIngresar);				
	}
	
	private void asegurarQueNoSeModifiquenDatosRepetidosPorOtrosUsuarios(JugadorDominio jugador) {		
		if(!UtilTexto.cadenaEstaVacia( jugador.getCorreo()) ) {
			JugadorEntidad jugadorEntidad = JugadorEntidad.crear().setCorreo(jugador.getCorreo());

			if (!consultar(jugadorEntidad).stream().filter(persona -> persona.getCodigo() != jugador.getCodigo()).findFirst().isEmpty()) {
				throw new SudokuNegocioExeption("Ya existe un jugador con ese correo, no puedes utilizarlo.");
			}
		}
		
		if(UtilNumerico.numeroEsMayor(jugador.getDocumentoIdentificacion(), 0) ) {
			JugadorEntidad jugadorEntidad = JugadorEntidad.crear().setDocumentoIdentificacion(jugador.getDocumentoIdentificacion());

			if (!consultar(jugadorEntidad).stream().filter(persona -> persona.getDocumentoIdentificacion() != jugador.getDocumentoIdentificacion()).findFirst().isEmpty()) {
				throw new SudokuNegocioExeption("Ya existe un jugador con ese documento de identificacion, no puedes utilizarlo.");
			}
		}
		
	}
	
	private JugadorEntidad asignarValoresFaltantesALaActualizacion(JugadorDominio jugador) {
		List<JugadorDominio> jugadores = consultar(JugadorEntidad.crear());
		
		JugadorDominio jugadorComparador = jugadores.stream().filter(persona -> persona.getCodigo() == jugador.getCodigo()).findFirst().get();
		JugadorEntidad jugadorEntidad =obtenerJugadorEnsablador().ensamblarEntidad(jugadorComparador).get();
		
		if(!UtilTexto.cadenaEstaVacia(jugador.getNombre()) ) {
			jugadorEntidad.setNombre(jugador.getNombre());
		}
		
		if(!UtilTexto.cadenaEstaVacia( jugador.getCorreo() )) {
			jugadorEntidad.setCorreo(jugador.getCorreo());
		}

		if(UtilNumerico.numeroEsMayor(jugador.getDocumentoIdentificacion(), 0)) {
			jugadorEntidad.setDocumentoIdentificacion(jugador.getDocumentoIdentificacion());
		}

		if(!UtilTexto.cadenaEstaVacia(jugador.getClave()) ) {
			jugadorEntidad.setClave(jugador.getClave());
		}

		return jugadorEntidad;
	}

}
