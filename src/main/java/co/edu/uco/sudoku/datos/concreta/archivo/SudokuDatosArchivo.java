package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.SudokuDatos;
import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class SudokuDatosArchivo implements SudokuDatos {

	private String rutaArchivo;

	public SudokuDatosArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<SudokuEntidad> consultar(SudokuEntidad entidad) {
		List<SudokuEntidad> listaSudokus = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(rutaArchivo);

		for (String linea : lineas) {
			listaSudokus.add(ensamblar(linea));
		}

		return aplicarFiltro(entidad, listaSudokus);
	}

	private List<SudokuEntidad> aplicarFiltro(SudokuEntidad entidad, List<SudokuEntidad> listaSudokus) {
		if (UtilObjeto.esNulo(entidad)) {
			listaSudokus = filtrarporCodigo(entidad, listaSudokus);
		}
		return listaSudokus;
	}

	private List<SudokuEntidad> filtrarporCodigo(SudokuEntidad entidad, List<SudokuEntidad> listaSudokus) {
		if (UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
			listaSudokus = listaSudokus.stream().filter(sudoku -> sudoku.getCodigo() == entidad.getCodigo())
					.collect(Collectors.toList());
		}
		return listaSudokus;
	}

	private SudokuEntidad ensamblar(String registro) {

		String[] datosSudokuActual = registro.split("-");
		int codigoSudokuActual = Integer.parseInt(datosSudokuActual[0]);

		RegionPorSudokuDatosArchivo regionPorSudokuDatosArchivo = new RegionPorSudokuDatosArchivo();
		RegionEntidad[][] regionDatos = regionPorSudokuDatosArchivo.matrizRegiones(codigoSudokuActual);

		return SudokuEntidad.crear().setCodigo(codigoSudokuActual).setRegiones(regionDatos);

	}

	@Override
	public void crear(SudokuEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(SudokuEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(SudokuEntidad objeto) {
		// TODO Auto-generated method stub

	}

}
