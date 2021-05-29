package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.RegionDatos;
import co.edu.uco.sudoku.datos.RegionPorSudokuDatos;
import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.relacion.muchos.muchos.RegionPorSudoku;
import co.edu.uco.sudoku.transversal.exepcion.SudokuRelacionArchivoExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class RegionPorSudokuDatosArchivo implements RegionPorSudokuDatos {

	private static final String NOMBRE_REGION_POR_SUDOKU_ARCHIVO = "C:\\Users\\mateo\\git\\Sudoku\\RegionSudoku.txt";


	private RegionPorSudoku ensamblar(String registro) {

		String[] datosCeldaRegionActual = registro.split("-");
		int codigoRegionActual = Integer.parseInt(datosCeldaRegionActual[0]);
		int codigoSudokuActual = Integer.parseInt(datosCeldaRegionActual[1]);

		return RegionPorSudoku.crear(codigoRegionActual, codigoSudokuActual);

	}

	@Override
	public RegionEntidad[][] matrizRegiones(int codigoSudokuOPlantilla) {

		List<RegionPorSudoku> arrayRegionPorSudoku = consultar(RegionPorSudoku.crear()).stream()
				.filter(regionPorSudokuDatos -> regionPorSudokuDatos.getCodigoSudoku() == codigoSudokuOPlantilla)
				.collect(Collectors.toList());

		return crearMatriz(arrayRegionPorSudoku);
	}

	private RegionEntidad[][] crearMatriz(List<RegionPorSudoku> arrayRegionPorSudoku) {
		RegionDatos region = SudokuDatosFactoria.obtenerFactoria().obtenerRegionDatos();

		RegionEntidad[][] matrizRegiones = new RegionEntidad[3][3];

		for (RegionPorSudoku regionPorSudoku : arrayRegionPorSudoku) {

			List<RegionEntidad> listaCeldas = region
					.consultar(RegionEntidad.crear().setCodigo(regionPorSudoku.getCodigoRegion()));
			RegionEntidad regionEntidad = listaCeldas.stream()
					.filter(posicion -> posicion.getCodigo() == regionPorSudoku.getCodigoRegion()).findFirst()
					.orElse(RegionEntidad.crear());

			if (UtilObjeto.esNulo(regionEntidad)) {
				throw new SudokuRelacionArchivoExepcion("Se perden datos haciendo la matriz de las regiones.");

			}

			matrizRegiones[regionEntidad.getPosicion().getFila() - 1][regionEntidad.getPosicion().getColumna()
					- 1] = regionEntidad;
		}
		return matrizRegiones;
	}

	@Override
	public List<RegionPorSudoku> consultar(RegionPorSudoku entidad) {
		List<RegionPorSudoku> listaRegionesPorSudoku = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(NOMBRE_REGION_POR_SUDOKU_ARCHIVO);

		for (String linea : lineas) {
			listaRegionesPorSudoku.add(ensamblar(linea));
		}

		return listaRegionesPorSudoku;
	}

	@Override
	public void crear(RegionPorSudoku objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(RegionPorSudoku objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(RegionPorSudoku objeto) {
		// TODO Auto-generated method stub
		
	}

}
