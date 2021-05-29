package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.CeldaPorRegionDatos;
import co.edu.uco.sudoku.datos.PosicionDatos;
import co.edu.uco.sudoku.datos.RegionDatos;
import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class RegionDatosArchivo implements RegionDatos {

	private String rutaArchivo;

	public RegionDatosArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<RegionEntidad> consultar(RegionEntidad entidad) {
		List<RegionEntidad> listaRegiones = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(rutaArchivo);

		for (String linea : lineas) {
			listaRegiones.add(ensamblar(linea));
		}

		return aplicarFiltro(entidad, listaRegiones);
	}

	private List<RegionEntidad> aplicarFiltro(RegionEntidad entidad, List<RegionEntidad> listaRegiones) {
		if (!UtilObjeto.esNulo(entidad)) {
			listaRegiones = filtrarPorCodigo(entidad, listaRegiones);
			listaRegiones = filtrarPorPosicion(entidad, listaRegiones);
		}
		return listaRegiones;
	}

	private List<RegionEntidad> filtrarPorCodigo(RegionEntidad entidad, List<RegionEntidad> listaRegiones) {
		if (UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0l)) {
			listaRegiones = listaRegiones.stream().filter(region -> region.getCodigo() == entidad.getCodigo())
					.collect(Collectors.toList());
		}
		return listaRegiones;
	}

	private List<RegionEntidad> filtrarPorPosicion(RegionEntidad entidad, List<RegionEntidad> listaRegiones) {
		if (!UtilObjeto.esNulo(entidad.getPosicion())) {
			listaRegiones = listaRegiones.stream()
					.filter(region -> region.getPosicion().getCodigo() == entidad.getPosicion().getCodigo())
					.collect(Collectors.toList());
		}
		return listaRegiones;
	}

	private RegionEntidad ensamblar(String registro) {

		String[] datosRegionActual = registro.split("-");
		int codigoRegionActual = Integer.parseInt(datosRegionActual[0]);
		int codigoPosicionRegionActual = Integer.parseInt(datosRegionActual[1]);

		PosicionDatos posicionDatos = SudokuDatosFactoria.obtenerFactoria().obtenerPosicionDatos();
		Optional<PosicionEntidad> posicionCeldaActual = posicionDatos
				.consultar(PosicionEntidad.crear().setCodigo(codigoPosicionRegionActual)).stream()
				.filter(celda -> celda.getCodigo() == codigoPosicionRegionActual).findFirst();

		CeldaPorRegionDatos celdaPorRegionDatos = new CeldaPorRegionDatosArchivo();
		CeldaEntidad[][] matrizCeldas = celdaPorRegionDatos.matrizCeldas(codigoRegionActual);

		return RegionEntidad.crear().setCodigo(codigoRegionActual).setCeldas(matrizCeldas)
				.setPosicion(posicionCeldaActual.orElse(null));
	}

	@Override
	public void crear(RegionEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(RegionEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(RegionEntidad objeto) {
		// TODO Auto-generated method stub

	}

}
