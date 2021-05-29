package co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.entidad.NivelComplejidadEntidad;
import co.edu.uco.sudoku.dominio.NivelComplejidadDominio;
import co.edu.uco.sudoku.dto.NivelComplejidadDTO;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.NivelComplejidadEnsamblador;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class NivelComplejidadEnsambladorImpl implements NivelComplejidadEnsamblador {

	private static final NivelComplejidadEnsamblador INSTANCIA = new NivelComplejidadEnsambladorImpl();

	private NivelComplejidadEnsambladorImpl() {
	}

	public static NivelComplejidadEnsamblador obtenerNivelComplejidadEnsamblador() {
		return INSTANCIA;
	}

	@Override
	public Optional<NivelComplejidadDominio> ensamblarDominioDesdeEntidad(NivelComplejidadEntidad entidad) {

		if (UtilObjeto.esNulo(entidad)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de un nivel de complejidad a partir de una entidad de un nivel de complejidad nulo");
		}

		NivelComplejidadDominio complejidad = NivelComplejidadDominio.crear(entidad.getCodigo(), entidad.getNombre(),
				entidad.getDescripcion(), entidad.getTiempoLimiteSegundos());
		return Optional.of(complejidad);
	}

	@Override
	public Optional<NivelComplejidadEntidad> ensamblarEntidad(NivelComplejidadDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una entidad de un nivel de complejidad a partir de un dominio de un nivel de complejidad nulo");
		}

		NivelComplejidadEntidad complejidad = NivelComplejidadEntidad.crear(dominio.getCodigo(), dominio.getNombre(),
				dominio.getDescripcion(), dominio.getTiempoLimiteSegundos());
		return Optional.of(complejidad);
	}

	@Override
	public List<NivelComplejidadDominio> ensamblarDominiosDesdeEntidad(List<NivelComplejidadEntidad> entidades) {
		List<NivelComplejidadDominio> dominios = new ArrayList<>();

		for (NivelComplejidadEntidad entidad : entidades) {
			dominios.add(ensamblarDominioDesdeEntidad(entidad).get());
		}
		return dominios;
	}

	@Override
	public NivelComplejidadDominio ensamblarDominioDesdeDTO(NivelComplejidadDTO dto) {

		if (UtilObjeto.esNulo(dto)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de un nivel de complejidad a partir de un dto de un nivel de complejidad nulo");
		}

		return NivelComplejidadDominio.crear(dto.getCodigo(), dto.getNombre(), dto.getDescripcion(),
				dto.getTiempoLimiteSegundos());
	}

	@Override
	public List<NivelComplejidadDominio> ensamblarDominiosDesdeDTO(List<NivelComplejidadDTO> dtos) {
		return dtos.stream().map(obtenerNivelComplejidadEnsamblador()::ensamblarDominioDesdeDTO)
				.collect(Collectors.toList());
	}

	@Override
	public NivelComplejidadDTO ensamblarDTO(NivelComplejidadDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dto de un nivel de complejidad a partir de un dominio de un nivel de complejidad nulo");
		}

		return NivelComplejidadDTO.crear(dominio.getCodigo(), dominio.getNombre(), dominio.getDescripcion(),
				dominio.getTiempoLimiteSegundos());
	}

	@Override
	public List<NivelComplejidadDTO> ensamblarDTOs(List<NivelComplejidadDominio> dominios) {
		return dominios.stream().map(obtenerNivelComplejidadEnsamblador()::ensamblarDTO).collect(Collectors.toList());
	}

}
