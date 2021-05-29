package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.NivelComplejidadDatos;
import co.edu.uco.sudoku.datos.PlantillaDatos;
import co.edu.uco.sudoku.datos.SudokuDatos;
import co.edu.uco.sudoku.datos.entidad.NivelComplejidadEntidad;
import co.edu.uco.sudoku.datos.entidad.PlantillaEntidad;
import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class PlantillaDatosArchivo implements PlantillaDatos {

	private String rutaArchivo;

	public PlantillaDatosArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<PlantillaEntidad> consultar(PlantillaEntidad entidad) {
		List<PlantillaEntidad> listaPlantillas = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(rutaArchivo);

		for (String linea : lineas) {
			listaPlantillas.add(ensamblar(linea));
		}

		return aplicarFiltro(entidad, listaPlantillas);
	}

	private List<PlantillaEntidad> aplicarFiltro(PlantillaEntidad entidad, List<PlantillaEntidad> listaPlantillas) {
		if (!UtilObjeto.esNulo(entidad)) {
			listaPlantillas = filtrarPorCodigo(entidad, listaPlantillas);
			listaPlantillas = filtrarPorSudoku(entidad, listaPlantillas);
			listaPlantillas = filtrarPorNivelComplejidad(entidad, listaPlantillas);
		}
		return listaPlantillas;
	}

	private List<PlantillaEntidad> filtrarPorCodigo(PlantillaEntidad entidad, List<PlantillaEntidad> listaPlantillas) {
		if (UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0l)) {
			listaPlantillas = listaPlantillas.stream()
					.filter(plantillas -> plantillas.getCodigo() == entidad.getCodigo()).collect(Collectors.toList());
		}
		return listaPlantillas;
	}

	private List<PlantillaEntidad> filtrarPorSudoku(PlantillaEntidad entidad, List<PlantillaEntidad> listaPlantillas) {
		if (!UtilObjeto.esNulo(entidad.getSudoku())) {
			listaPlantillas = listaPlantillas.stream()
					.filter(plantillas -> plantillas.getSudoku().getCodigo() == entidad.getSudoku().getCodigo())
					.collect(Collectors.toList());
		}
		return listaPlantillas;
	}

	private List<PlantillaEntidad> filtrarPorNivelComplejidad(PlantillaEntidad entidad,
			List<PlantillaEntidad> listaPlantillas) {
		if (!UtilObjeto.esNulo(entidad.getNivelComplejidad())) {
			listaPlantillas = listaPlantillas.stream().filter(plantillas -> plantillas.getNivelComplejidad()
					.getCodigo() == entidad.getNivelComplejidad().getCodigo()).collect(Collectors.toList());
		}
		return listaPlantillas;
	}

	private PlantillaEntidad ensamblar(String registro) {

		String[] datosPlantillaActual = registro.split("-");
		int codigoPlantillaActual = Integer.parseInt(datosPlantillaActual[0]);
		int codigoSudokuActual = Integer.parseInt(datosPlantillaActual[1]);
		int codigoNivelComplejidadActual = Integer.parseInt(datosPlantillaActual[2]);

		SudokuDatos sudokuDatos = SudokuDatosFactoria.obtenerFactoria().obtenerSudokuDatos();
		Optional<SudokuEntidad> sudoku = sudokuDatos.consultar(SudokuEntidad.crear().setCodigo(codigoSudokuActual))
				.stream().filter(sudokuEntidad -> sudokuEntidad.getCodigo() == codigoSudokuActual).findFirst();

		RegionPorSudokuDatosArchivo regionPorSudokuDatosArchivo = new RegionPorSudokuDatosArchivo();
		RegionEntidad[][] matrizRegiones = regionPorSudokuDatosArchivo.matrizRegiones(codigoPlantillaActual);

		NivelComplejidadDatos nivelComplejidadDatos = SudokuDatosFactoria.obtenerFactoria()
				.obtenerNivelComplejidadDatos();
		Optional<NivelComplejidadEntidad> nivleComplejidad = nivelComplejidadDatos
				.consultar(NivelComplejidadEntidad.crear().setCodigo(codigoNivelComplejidadActual)).stream()
				.filter(entidades -> entidades.getCodigo() == codigoNivelComplejidadActual).findFirst();

		return PlantillaEntidad.crear().setCodigo(codigoPlantillaActual).setSudoku(sudoku.orElse(null))
				.setNivelComplejidad(nivleComplejidad.orElse(null)).setRegiones(matrizRegiones);

	}

	@Override
	public void crear(PlantillaEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(PlantillaEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(PlantillaEntidad objeto) {
		// TODO Auto-generated method stub

	}

}
