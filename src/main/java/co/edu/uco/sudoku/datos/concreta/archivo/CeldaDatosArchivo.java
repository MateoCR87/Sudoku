package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.CeldaDatos;
import co.edu.uco.sudoku.datos.PosicionDatos;
import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class CeldaDatosArchivo implements CeldaDatos {

	private String rutaArchivo;

	public CeldaDatosArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<CeldaEntidad> consultar(CeldaEntidad entidad) {
		List<CeldaEntidad> listaCeldas = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(rutaArchivo);

		for (String linea : lineas) {
			listaCeldas.add(ensamblar(linea));
		}

		return aplicarFiltro(entidad, listaCeldas);
	}

	private List<CeldaEntidad> aplicarFiltro(CeldaEntidad entidad, List<CeldaEntidad> listaCeldas) {
		if (!UtilObjeto.esNulo(entidad)) {
			listaCeldas = filtrarPorCodigo(entidad, listaCeldas);
			listaCeldas = filtrarPorNumero(entidad, listaCeldas);
			listaCeldas = filtrarPorPista(entidad, listaCeldas);
			listaCeldas = filtrarPorPosicion(entidad, listaCeldas);
		}
		return listaCeldas;
	}

	private List<CeldaEntidad> filtrarPorCodigo(CeldaEntidad entidad, List<CeldaEntidad> listaCeldas) {
		if (UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
			listaCeldas = listaCeldas.stream().filter(celda -> celda.getCodigo() == entidad.getCodigo())
					.collect(Collectors.toList());
		}
		return listaCeldas;
	}

	private List<CeldaEntidad> filtrarPorNumero(CeldaEntidad entidad, List<CeldaEntidad> listaCeldas) {
		if (UtilNumerico.numeroEsMayor(entidad.getNumero(), 0)
				&& UtilNumerico.numeroEsMenorOIgual(entidad.getNumero(), 9)) {
			listaCeldas = listaCeldas.stream().filter(celda -> celda.getNumero() == entidad.getNumero())
					.collect(Collectors.toList());
		}
		return listaCeldas;
	}

	private List<CeldaEntidad> filtrarPorPista(CeldaEntidad entidad, List<CeldaEntidad> listaCeldas) {
		if (entidad.isEsPista()) {
			listaCeldas = listaCeldas.stream().filter(celda -> celda.isEsPista()).collect(Collectors.toList());
		}
		return listaCeldas;
	}

	private List<CeldaEntidad> filtrarPorPosicion(CeldaEntidad entidad, List<CeldaEntidad> listaCeldas) {
		if (!UtilObjeto.esNulo(entidad.getPosicion())) {
			listaCeldas = listaCeldas.stream().filter(celda -> celda.getPosicion().equals(entidad.getPosicion()))
					.collect(Collectors.toList());
		}
		return listaCeldas;
	}

	private CeldaEntidad ensamblar(String registro) {

		String[] datosCeldaActual = registro.split("-");
		int codigoCeldaActual = Integer.parseInt(datosCeldaActual[0]);
		int numeroCeldaActual = Integer.parseInt(datosCeldaActual[1]);
		boolean esPistaCeldaActual = Boolean.parseBoolean(datosCeldaActual[2]);
		int codigoPosicionCeldaActual = Integer.parseInt(datosCeldaActual[3]);

		PosicionDatos posicionDatos = SudokuDatosFactoria.obtenerFactoria().obtenerPosicionDatos();
		Optional<PosicionEntidad> posicionCeldaActual = posicionDatos
				.consultar(PosicionEntidad.crear().setCodigo(codigoPosicionCeldaActual)).stream()
				.filter(celda -> celda.getCodigo() == codigoPosicionCeldaActual).findFirst();

		return CeldaEntidad.crear().setCodigo(codigoCeldaActual).setNumero(numeroCeldaActual)
				.setEsPista(esPistaCeldaActual).setPosicion(posicionCeldaActual.orElse(null));

	}

	@Override
	public void crear(CeldaEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(CeldaEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(CeldaEntidad objeto) {
		// TODO Auto-generated method stub

	}

}
