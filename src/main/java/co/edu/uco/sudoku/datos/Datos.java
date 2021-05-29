package co.edu.uco.sudoku.datos;

import java.util.List;

public interface Datos<T> {

	List<T> consultar(T entidad);

	void crear(T objeto);

	void actualizar(T objeto);

	void eliminar(T objeto);
}
