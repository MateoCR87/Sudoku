package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.dominio.JugadorDominio;

public interface JugadorNegocio {

	void registrar(JugadorDominio jugador);

	List<JugadorDominio> consultar(JugadorEntidad entidad);
	
	void eliminar(JugadorDominio jugador);
	
	void actualizar(JugadorDominio jugador);

}
