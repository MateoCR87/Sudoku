package co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.dominio.SudokuDominio;
import co.edu.uco.sudoku.dto.SudokuDTO;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.SudokuEnsamblador;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class SudokuEnsambladorImpl implements SudokuEnsamblador {

	private static final SudokuEnsamblador INSTANCIA = new SudokuEnsambladorImpl();

	private SudokuEnsambladorImpl() {
	}

	public static SudokuEnsamblador obtenerSudokuEnsablador() {
		return INSTANCIA;
	}

	@Override
	public Optional<SudokuDominio> ensamblarDominioDesdeEntidad(SudokuEntidad entidad) {

		if (UtilObjeto.esNulo(entidad)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una sudoku a partir de una entidad de un sudoku nulo");
		}

		SudokuDominio sudoku = SudokuDominio.crear(entidad.getCodigo(),
				RegionEnsambladorImpl.obtenerRegionEnsablador().regionesDominio(entidad.getRegiones()));
		return Optional.of(sudoku);
	}

	@Override
	public Optional<SudokuEntidad> ensamblarEntidad(SudokuDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una entidad de una sudoku a partir de un dominio de un sudoku nulo");
		}

		SudokuEntidad sudoku = SudokuEntidad.crear(dominio.getCodigo(),
				RegionEnsambladorImpl.obtenerRegionEnsablador().regionesEntidad(dominio.getRegiones()));
		return Optional.of(sudoku);
	}

	@Override
	public List<SudokuDominio> ensamblarDominiosDesdeEntidad(List<SudokuEntidad> entidades) {
		List<SudokuDominio> dominios = new ArrayList<>();

		for (SudokuEntidad entidad : entidades) {
			dominios.add(ensamblarDominioDesdeEntidad(entidad).get());
		}
		return dominios;
	}

	@Override
	public SudokuDominio ensamblarDominioDesdeDTO(SudokuDTO dto) {

		if (UtilObjeto.esNulo(dto)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una sudoku a partir de un dto de un sudoku nulo");
		}

		return SudokuDominio.crear(dto.getCodigo(),
				RegionEnsambladorImpl.obtenerRegionEnsablador().regionesDominio(dto.getRegiones()));
	}

	@Override
	public List<SudokuDominio> ensamblarDominiosDesdeDTO(List<SudokuDTO> dtos) {
		return dtos.stream().map(obtenerSudokuEnsablador()::ensamblarDominioDesdeDTO).collect(Collectors.toList());
	}

	@Override
	public SudokuDTO ensamblarDTO(SudokuDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dto de una sudoku a partir de un dominio de un sudoku nulo");
		}

		return SudokuDTO.crear(dominio.getCodigo(),
				RegionEnsambladorImpl.obtenerRegionEnsablador().regionesDTO(dominio.getRegiones()));
	}

	@Override
	public List<SudokuDTO> ensamblarDTOs(List<SudokuDominio> dominios) {
		return dominios.stream().map(obtenerSudokuEnsablador()::ensamblarDTO).collect(Collectors.toList());
	}

}
