package co.edu.uco.sudoku.datos;

import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.relacion.muchos.muchos.RegionPorSudoku;

public interface RegionPorSudokuDatos extends Datos<RegionPorSudoku> {

	RegionEntidad[][] matrizRegiones(int codigoSudoku);
}
