package co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.entidad.PlantillaEntidad;
import co.edu.uco.sudoku.dominio.PlantillaDominio;
import co.edu.uco.sudoku.dto.PlantillaDTO;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.PlantillaEnsamblador;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class PlantillaEnsambladorImpl implements PlantillaEnsamblador {

	private static final PlantillaEnsamblador INSTANCIA = new PlantillaEnsambladorImpl();

	private PlantillaEnsambladorImpl() {
	}

	public static PlantillaEnsamblador obtenerPlantillaEnsablador() {
		return INSTANCIA;
	}

	@Override
	public Optional<PlantillaDominio> ensamblarDominioDesdeEntidad(PlantillaEntidad entidad) {

		if (UtilObjeto.esNulo(entidad)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una plantilla a partir de una entidad de una plantilla nula");
		}

		PlantillaDominio plantilla = PlantillaDominio.crear(entidad.getCodigo(),
				SudokuEnsambladorImpl.obtenerSudokuEnsablador().ensamblarDominioDesdeEntidad(entidad.getSudoku()).get(),
				RegionEnsambladorImpl.obtenerRegionEnsablador().regionesDominio(entidad.getRegiones()),
				NivelComplejidadEnsambladorImpl.obtenerNivelComplejidadEnsamblador()
						.ensamblarDominioDesdeEntidad(entidad.getNivelComplejidad()).get());
		return Optional.of(plantilla);
	}

	@Override
	public Optional<PlantillaEntidad> ensamblarEntidad(PlantillaDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una entidad de una plantilla a partir de un dominio de una plantilla nula");
		}

		PlantillaEntidad plantilla = PlantillaEntidad.crear(dominio.getCodigo(),
				SudokuEnsambladorImpl.obtenerSudokuEnsablador().ensamblarEntidad(dominio.getSudoku()).get(),
				RegionEnsambladorImpl.obtenerRegionEnsablador().regionesEntidad(dominio.getRegiones()),
				NivelComplejidadEnsambladorImpl.obtenerNivelComplejidadEnsamblador()
						.ensamblarEntidad(dominio.getNivelComplejidad()).get());
		return Optional.of(plantilla);
	}

	@Override
	public List<PlantillaDominio> ensamblarDominiosDesdeEntidad(List<PlantillaEntidad> entidades) {
		List<PlantillaDominio> dominios = new ArrayList<>();

		for (PlantillaEntidad entidad : entidades) {
			dominios.add(ensamblarDominioDesdeEntidad(entidad).get());
		}
		return dominios;
	}

	@Override
	public PlantillaDominio ensamblarDominioDesdeDTO(PlantillaDTO dto) {

		if (UtilObjeto.esNulo(dto)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una plantilla a partir de un dto de una plantilla nula");
		}

		return PlantillaDominio.crear(dto.getCodigo(),
				SudokuEnsambladorImpl.obtenerSudokuEnsablador().ensamblarDominioDesdeDTO(dto.getSudoku()),
				RegionEnsambladorImpl.obtenerRegionEnsablador().regionesDominio(dto.getRegiones()),
				NivelComplejidadEnsambladorImpl.obtenerNivelComplejidadEnsamblador()
						.ensamblarDominioDesdeDTO(dto.getNivelComplejidad()));
	}

	@Override
	public List<PlantillaDominio> ensamblarDominiosDesdeDTO(List<PlantillaDTO> dtos) {
		return dtos.stream().map(obtenerPlantillaEnsablador()::ensamblarDominioDesdeDTO).collect(Collectors.toList());
	}

	@Override
	public PlantillaDTO ensamblarDTO(PlantillaDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dto de una plantilla a partir de un dominio de una plantilla nula");
		}

		return PlantillaDTO.crear(dominio.getCodigo(),
				SudokuEnsambladorImpl.obtenerSudokuEnsablador().ensamblarDTO(dominio.getSudoku()),
				RegionEnsambladorImpl.obtenerRegionEnsablador().regionesDTO(dominio.getRegiones()),
				NivelComplejidadEnsambladorImpl.obtenerNivelComplejidadEnsamblador()
						.ensamblarDTO(dominio.getNivelComplejidad()));
	}

	@Override
	public List<PlantillaDTO> ensamblarDTOs(List<PlantillaDominio> dominios) {
		return dominios.stream().map(obtenerPlantillaEnsablador()::ensamblarDTO).collect(Collectors.toList());
	}

}
