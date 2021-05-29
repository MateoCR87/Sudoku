package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.ModalidadJuegoDatos;
import co.edu.uco.sudoku.datos.entidad.ModalidadJuegoEntidad;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class ModalidadJuegoDatosArchivo implements ModalidadJuegoDatos {

	private String rutaArchivo;

	public ModalidadJuegoDatosArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<ModalidadJuegoEntidad> consultar(ModalidadJuegoEntidad entidad) {

		List<ModalidadJuegoEntidad> listaModalidadesJuego = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(rutaArchivo);

		for (String linea : lineas) {
			listaModalidadesJuego.add(ensamblar(linea));
		}

		return aplicarFiltro(entidad, listaModalidadesJuego);
	}

	private List<ModalidadJuegoEntidad> aplicarFiltro(ModalidadJuegoEntidad entidad,
			List<ModalidadJuegoEntidad> listaModalidadesJuego) {

		if (!UtilObjeto.esNulo(entidad)) {
			listaModalidadesJuego = filtrarPorCodigo(entidad, listaModalidadesJuego);
			listaModalidadesJuego = filtrarPorNombre(entidad, listaModalidadesJuego);
		}
		return listaModalidadesJuego;
	}

	private List<ModalidadJuegoEntidad> filtrarPorCodigo(ModalidadJuegoEntidad entidad,
			List<ModalidadJuegoEntidad> listaModalidadesJuego) {

		if (UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
			listaModalidadesJuego = listaModalidadesJuego.stream()
					.filter(modalidadJuego -> modalidadJuego.getCodigo() == entidad.getCodigo())
					.collect(Collectors.toList());
		}
		return listaModalidadesJuego;
	}

	private List<ModalidadJuegoEntidad> filtrarPorNombre(ModalidadJuegoEntidad entidad,
			List<ModalidadJuegoEntidad> listaModalidadesJuego) {

		if (!UtilTexto.cadenaEstaVacia(entidad.getNombre())) {
			listaModalidadesJuego = listaModalidadesJuego.stream()
					.filter(modalidadJuego -> modalidadJuego.getNombre().equals(entidad.getNombre()))
					.collect(Collectors.toList());
		}
		return listaModalidadesJuego;
	}

	private ModalidadJuegoEntidad ensamblar(String registro) {

		String[] datosModalidadJuegoActual = registro.split("-");
		int codigoModalidaJuegoActual = Integer.parseInt(datosModalidadJuegoActual[0]);
		String nombreModalidadJuegoActual = datosModalidadJuegoActual[1];

		return ModalidadJuegoEntidad.crear().setCodigo(codigoModalidaJuegoActual).setNombre(nombreModalidadJuegoActual);

	}

	@Override
	public void crear(ModalidadJuegoEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(ModalidadJuegoEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(ModalidadJuegoEntidad objeto) {
		// TODO Auto-generated method stub

	}
}
