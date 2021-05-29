package co.edu.uco.sudoku.negocio.negocio;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.ModalidadJuegoEntidad;
import co.edu.uco.sudoku.dominio.ModalidadJuegoDominio;

public interface ModalidadJuegoNegocio {

	List<ModalidadJuegoDominio> consultar(ModalidadJuegoEntidad entidad);

}
