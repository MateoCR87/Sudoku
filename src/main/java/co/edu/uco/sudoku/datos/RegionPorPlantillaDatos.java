package co.edu.uco.sudoku.datos;

import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.relacion.muchos.muchos.RegionPorPlantilla;

public interface RegionPorPlantillaDatos extends Datos<RegionPorPlantilla> {

	RegionEntidad[][] matrizRegiones(int codigoSudoku);
}
