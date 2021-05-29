package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.CeldaDatos;
import co.edu.uco.sudoku.datos.CeldaPorRegionDatos;
import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.relacion.muchos.muchos.CeldaPorRegion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuRelacionArchivoExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class CeldaPorRegionDatosArchivo implements CeldaPorRegionDatos {

	private static final String NOMBRE_CELDAS_POR_REGION_ARCHIVO = "C:\\Users\\mateo\\git\\Sudoku\\CeldaRegion.txt";


	private CeldaPorRegion ensamblar(String registro) {

		String[] datosCeldaRegionActual = registro.split("-");
		int codigoCeldaActual = Integer.parseInt(datosCeldaRegionActual[0]);
		int codigoRegionActual = Integer.parseInt(datosCeldaRegionActual[1]);

		return CeldaPorRegion.crear(codigoCeldaActual, codigoRegionActual);

	}

	@Override
	public CeldaEntidad[][] matrizCeldas(int codigoRegion) {

		List<CeldaPorRegion> arrayCeldasPorRegion = consultar(CeldaPorRegion.crear()).stream()
				.filter(celdaPorRegionDatos -> celdaPorRegionDatos.getCodigoRegion() == codigoRegion)
				.collect(Collectors.toList());

		return crearMatriz(arrayCeldasPorRegion);
	}

	private CeldaEntidad[][] crearMatriz(List<CeldaPorRegion> arrayCeldasPorRegion) {
		CeldaDatos celda = SudokuDatosFactoria.obtenerFactoria().obtenerCeldaDatos();
		CeldaEntidad[][] matrizCeldas = new CeldaEntidad[3][3];

		for (CeldaPorRegion celdaPorRegion : arrayCeldasPorRegion) {

			List<CeldaEntidad> listaCeldas = celda
					.consultar(CeldaEntidad.crear().setCodigo(celdaPorRegion.getCodigoCelda()));
			CeldaEntidad celdaEntidad = listaCeldas.stream()
					.filter(posicion -> posicion.getCodigo() == celdaPorRegion.getCodigoCelda()).findFirst()
					.orElse(CeldaEntidad.crear());

			if (UtilObjeto.esNulo(celdaEntidad)) {
				throw new SudokuRelacionArchivoExepcion("Se perden datos haciendo la matriz de las celdas.");

			}

			matrizCeldas[celdaEntidad.getPosicion().getFila() - 1][celdaEntidad.getPosicion().getColumna()
					- 1] = celdaEntidad;
		}
		return matrizCeldas;
	}

	@Override
	public List<CeldaPorRegion> consultar(CeldaPorRegion entidad) {
		List<CeldaPorRegion> listaCeldasPorRegion = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(NOMBRE_CELDAS_POR_REGION_ARCHIVO);

		for (String linea : lineas) {
			listaCeldasPorRegion.add(ensamblar(linea));
		}

		return listaCeldasPorRegion;
	}

	@Override
	public void crear(CeldaPorRegion objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(CeldaPorRegion objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(CeldaPorRegion objeto) {
		// TODO Auto-generated method stub
		
	}
}
