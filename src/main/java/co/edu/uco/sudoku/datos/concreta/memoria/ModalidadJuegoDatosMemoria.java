package co.edu.uco.sudoku.datos.concreta.memoria;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.ModalidadJuegoDatos;
import co.edu.uco.sudoku.datos.entidad.ModalidadJuegoEntidad;

public class ModalidadJuegoDatosMemoria implements ModalidadJuegoDatos {

	private static List<ModalidadJuegoEntidad> modalidadesJuego = new ArrayList<>();

	static {
		modalidadesJuego.add(ModalidadJuegoEntidad.crear(1, "con tiempo"));
		modalidadesJuego.add(ModalidadJuegoEntidad.crear(2, "libre"));
	}

	@Override
	public List<ModalidadJuegoEntidad> consultar(ModalidadJuegoEntidad entidad) {
		return modalidadesJuego;
	}

	@Override
	public void crear(ModalidadJuegoEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(ModalidadJuegoEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(ModalidadJuegoEntidad objeto) {
		// TODO Auto-generated method stub

	}

}
