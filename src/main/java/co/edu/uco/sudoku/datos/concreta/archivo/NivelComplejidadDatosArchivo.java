package co.edu.uco.sudoku.datos.concreta.archivo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.NivelComplejidadDatos;
import co.edu.uco.sudoku.datos.entidad.NivelComplejidadEntidad;
import co.edu.uco.sudoku.transversal.utilitario.UtilArchivo;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class NivelComplejidadDatosArchivo implements NivelComplejidadDatos {
	private String rutaArchivo;

	public NivelComplejidadDatosArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<NivelComplejidadEntidad> consultar(NivelComplejidadEntidad entidad) {

		List<NivelComplejidadEntidad> listaComplejidades = new ArrayList<>();
		List<String> lineas = UtilArchivo.leerLineasArchivo(rutaArchivo);

		for (String linea : lineas) {
			listaComplejidades.add(ensamblar(linea));
		}

		return aplicaFiltro(entidad, listaComplejidades);
	}

	private List<NivelComplejidadEntidad> aplicaFiltro(NivelComplejidadEntidad entidad,
			List<NivelComplejidadEntidad> listaNiveles) {
		if (!UtilObjeto.esNulo(entidad)) {
			listaNiveles = filtrarPorCodigo(entidad, listaNiveles);
			listaNiveles = filtrarPorNombre(entidad, listaNiveles);
		}
		return listaNiveles;
	}

	private List<NivelComplejidadEntidad> filtrarPorCodigo(NivelComplejidadEntidad entidad,
			List<NivelComplejidadEntidad> listaNiveles) {
		if (UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
			listaNiveles = listaNiveles.stream().filter(niveles -> niveles.getCodigo() == entidad.getCodigo())
					.collect(Collectors.toList());
		}
		return listaNiveles;
	}

	private List<NivelComplejidadEntidad> filtrarPorNombre(NivelComplejidadEntidad entidad,
			List<NivelComplejidadEntidad> listaNiveles) {
		if (!UtilTexto.cadenaEstaVacia(entidad.getNombre())) {
			listaNiveles = listaNiveles.stream().filter(niveles -> niveles.getNombre().equals(entidad.getNombre()))
					.collect(Collectors.toList());
		}
		return listaNiveles;
	}

	private NivelComplejidadEntidad ensamblar(String registro) {

		String[] datosNivelComplejidadaActual = registro.split("-");
		int codigoComplejidadaActual = Integer.parseInt(datosNivelComplejidadaActual[0]);
		String nombreComplejidadaActual = datosNivelComplejidadaActual[1];
		String descripcionComplejidadaActual = datosNivelComplejidadaActual[2];
		int tiempoLimiteEnSegundosComplejidadaActual = Integer.parseInt(datosNivelComplejidadaActual[3]);

		return NivelComplejidadEntidad.crear().setCodigo(codigoComplejidadaActual).setNombre(nombreComplejidadaActual)
				.setDescripcion(descripcionComplejidadaActual)
				.setTiempoLimiteSegundos(tiempoLimiteEnSegundosComplejidadaActual);
	}

	@Override
	public void crear(NivelComplejidadEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(NivelComplejidadEntidad objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(NivelComplejidadEntidad objeto) {
		// TODO Auto-generated method stub

	}

}
