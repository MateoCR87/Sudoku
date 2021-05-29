package co.edu.uco.sudoku.negocio.negocio.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.NivelComplejidadEnsambladorImpl.obtenerNivelComplejidadEnsamblador;

import java.util.List;

import co.edu.uco.sudoku.datos.NivelComplejidadDatos;
import co.edu.uco.sudoku.datos.entidad.NivelComplejidadEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.NivelComplejidadDominio;
import co.edu.uco.sudoku.negocio.negocio.NivelComplejidadNegocio;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;;

public class NivelComplajidadNegocioImpl implements NivelComplejidadNegocio {

	private NivelComplejidadDatos nivelComplejidadDatos;

	public NivelComplajidadNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.nivelComplejidadDatos = factoriaDatos.obtenerNivelComplejidadDatos();
	}

	@Override
	public List<NivelComplejidadDominio> consultar(NivelComplejidadEntidad entidad) {
		return obtenerNivelComplejidadEnsamblador()
				.ensamblarDominiosDesdeEntidad(nivelComplejidadDatos.consultar(entidad));
	}

	@Override
	public void registrar(NivelComplejidadDominio nivelComplejidad) {
		asegurarQueElCodigoDelNivelDeComplejidadNoExista(nivelComplejidad);
		asegurarQueElNombreDelNivelDeComplejidadNoExista(nivelComplejidad);

	}

	private void asegurarQueElCodigoDelNivelDeComplejidadNoExista(NivelComplejidadDominio nivelComplejidad) {
		NivelComplejidadEntidad nivelComplejidadEntidad = NivelComplejidadEntidad.crear()
				.setCodigo(nivelComplejidad.getCodigo());

		if (!consultar(nivelComplejidadEntidad).isEmpty()) {
			throw new SudokuNegocioExeption(
					"Ya existe un nivel de complejidad con el codigo  : " + nivelComplejidad.getCodigo());
		}
	}

	private void asegurarQueElNombreDelNivelDeComplejidadNoExista(NivelComplejidadDominio nivelComplejidad) {
		NivelComplejidadEntidad nivelComplejidadEntidad = NivelComplejidadEntidad.crear()
				.setNombre(nivelComplejidad.getNombre());

		if (!consultar(nivelComplejidadEntidad).isEmpty()) {
			throw new SudokuNegocioExeption(
					"Ya existe un nivel de complejidad con el codigo  : " + nivelComplejidad.getCodigo());
		}
	}

	@Override
	public void modificar(NivelComplejidadDominio nivelComplejidad) {
		if (UtilObjeto.esNulo(nivelComplejidad)) {
			throw new SudokuNegocioExeption("El nivel de complejidad a modificar  es nulo  : " + nivelComplejidad);
		}

		if (SudokuDatosFactoria
				.obtenerFactoria().obtenerNivelComplejidadDatos().consultar(NivelComplejidadEntidad.crear()
						.setNombre(nivelComplejidad.getNombre()).setDescripcion(nivelComplejidad.getDescripcion()))
				.isEmpty()) {
			throw new SudokuNegocioExeption(
					"ya existe un nivel de complejidad con los datos que se desean modificar, su codigo es   : "
							+ nivelComplejidad.getCodigo());
		}

		nivelComplejidadDatos.actualizar(obtenerNivelComplejidadEnsamblador().ensamblarEntidad(nivelComplejidad).get());

	}

}
