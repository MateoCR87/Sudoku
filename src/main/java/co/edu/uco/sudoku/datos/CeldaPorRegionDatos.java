package co.edu.uco.sudoku.datos;

import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.relacion.muchos.muchos.CeldaPorRegion;

public interface CeldaPorRegionDatos extends  Datos<CeldaPorRegion> {

	CeldaEntidad[][] matrizCeldas(int codigoRegion);

}
