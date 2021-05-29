package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.PosicionDatos;
import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class PosicionDatosArchivo implements PosicionDatos {

	private String rutaArchivo;

	public PosicionDatosArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<PosicionEntidad> consultar(PosicionEntidad entidad) {
		List<PosicionEntidad> listaPosiciones = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(rutaArchivo);

		for (String linea : lineas) {
			listaPosiciones.add(ensamblar(linea));
		}

		return aplicarFiltro(entidad, listaPosiciones);
	}

	private List<PosicionEntidad> aplicarFiltro(PosicionEntidad entidad, List<PosicionEntidad> listaPosiciones) {
		if (!UtilObjeto.esNulo(entidad)) {
			listaPosiciones = filtrarPorCodigo(entidad, listaPosiciones);
			listaPosiciones = filtrarPorFila(entidad, listaPosiciones);
			listaPosiciones = filtrarPorColumna(entidad, listaPosiciones);
		}
		return listaPosiciones;
	}

	private List<PosicionEntidad> filtrarPorCodigo(PosicionEntidad entidad, List<PosicionEntidad> listaPosiciones) {
		if (UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
			listaPosiciones = listaPosiciones.stream().filter(posicion -> posicion.getCodigo() == entidad.getCodigo())
					.collect(Collectors.toList());
		}
		return listaPosiciones;
	}

	private List<PosicionEntidad> filtrarPorFila(PosicionEntidad entidad, List<PosicionEntidad> listaPosiciones) {
		if (UtilNumerico.numeroEsMayor(entidad.getFila(), 0)
				&& UtilNumerico.numeroEsMayorOIgual(entidad.getFila(), 9)) {
			listaPosiciones = listaPosiciones.stream().filter(posicion -> posicion.getFila() == entidad.getFila())
					.collect(Collectors.toList());
		}
		return listaPosiciones;
	}

	private List<PosicionEntidad> filtrarPorColumna(PosicionEntidad entidad, List<PosicionEntidad> listaPosiciones) {
		if (UtilNumerico.numeroEsMayor(entidad.getColumna(), 0)
				&& UtilNumerico.numeroEsMayorOIgual(entidad.getColumna(), 9)) {
			listaPosiciones = listaPosiciones.stream().filter(posicion -> posicion.getColumna() == entidad.getColumna())
					.collect(Collectors.toList());
		}
		return listaPosiciones;
	}

	private PosicionEntidad ensamblar(String registro) {

		String[] datosPosicionActual = registro.split("-");
		int codigoPosicionActual = Integer.parseInt(datosPosicionActual[0]);
		int filaPosicionActual = Integer.parseInt(datosPosicionActual[1]);
		int columnaPosicionActual = Integer.parseInt(datosPosicionActual[2]);

		return PosicionEntidad.crear().setCodigo(codigoPosicionActual).setFila(filaPosicionActual)
				.setColumna(columnaPosicionActual);
	}

	@Override
	public void crear(PosicionEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(PosicionEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(PosicionEntidad objeto) {
		// TODO Auto-generated method stub

	}

}
