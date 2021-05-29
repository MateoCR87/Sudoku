package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.JugadorDatos;
import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class JugadorDatosArchivo implements JugadorDatos {

	private String rutaArchivo;

	public JugadorDatosArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<JugadorEntidad> consultar(JugadorEntidad entidad) {
		List<JugadorEntidad> listaJugadores = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(rutaArchivo);

		for (String linea : lineas) {
			listaJugadores.add(ensamblar(linea));
		}

		return aplicarFiltro(entidad, listaJugadores);
	}

	private List<JugadorEntidad> aplicarFiltro(JugadorEntidad entidad, List<JugadorEntidad> listaJugadores) {
		if (!UtilObjeto.esNulo(entidad)) {
			listaJugadores = filtratPorCodigo(entidad, listaJugadores);
			listaJugadores = filtratPorNombre(entidad, listaJugadores);
			listaJugadores = filtratPorDocumentoDeIdentificacion(entidad, listaJugadores);
			listaJugadores = filtratPorCorreo(entidad, listaJugadores);
		}
		return listaJugadores;
	}

	private List<JugadorEntidad> filtratPorCodigo(JugadorEntidad entidad, List<JugadorEntidad> listaJugadores) {
		if (UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
			listaJugadores = listaJugadores.stream().filter(jugador -> jugador.getCodigo() == entidad.getCodigo())
					.collect(Collectors.toList());
		}
		return listaJugadores;
	}

	private List<JugadorEntidad> filtratPorNombre(JugadorEntidad entidad, List<JugadorEntidad> listaJugadores) {
		if (!UtilTexto.cadenaEstaVacia(entidad.getNombre())) {
			listaJugadores = listaJugadores.stream().filter(jugador -> jugador.getNombre().equals(entidad.getNombre()))
					.collect(Collectors.toList());
		}
		return listaJugadores;
	}

	private List<JugadorEntidad> filtratPorDocumentoDeIdentificacion(JugadorEntidad entidad,
			List<JugadorEntidad> listaJugadores) {
		if (UtilNumerico.numeroEsMayor(entidad.getDocumentoIdentificacion(), 0)) {
			listaJugadores = listaJugadores.stream()
					.filter(jugador -> jugador.getDocumentoIdentificacion() == entidad.getDocumentoIdentificacion())
					.collect(Collectors.toList());
		}
		return listaJugadores;
	}

	private List<JugadorEntidad> filtratPorCorreo(JugadorEntidad entidad, List<JugadorEntidad> listaJugadores) {
		if (!UtilTexto.cadenaEstaVacia(entidad.getCorreo())) {
			listaJugadores = listaJugadores.stream().filter(jugador -> jugador.getCorreo().equals(entidad.getCorreo()))
					.collect(Collectors.toList());
		}
		return listaJugadores;
	}

	private JugadorEntidad ensamblar(String registro) {

		String[] datosJugadorActual = registro.split("-");
		int codigoJugadorActual = Integer.parseInt(datosJugadorActual[0]);
		String nombreJugadorActual = datosJugadorActual[1];
		int documentoIdentificacionJugadorActual = Integer.parseInt(datosJugadorActual[2]);
		String correoJugadorActual = datosJugadorActual[3];
		String claveJugadorActual = datosJugadorActual[4];

		return JugadorEntidad.crear().setCodigo(codigoJugadorActual).setNombre(nombreJugadorActual)
				.setDocumentoIdentificacion(documentoIdentificacionJugadorActual).setCorreo(correoJugadorActual)
				.setClave(claveJugadorActual);

	}

	@Override
	public void crear(JugadorEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(JugadorEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(JugadorEntidad objeto) {
		// TODO Auto-generated method stub

	}

}
