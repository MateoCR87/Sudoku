package co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.dto.RegionDTO;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.RegionEnsamblador;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class RegionEnsambladorImpl implements RegionEnsamblador {

	private static final RegionEnsamblador INSTANCIA = new RegionEnsambladorImpl();

	private RegionEnsambladorImpl() {
	}

	public static RegionEnsamblador obtenerRegionEnsablador() {
		return INSTANCIA;
	}

	@Override
	public Optional<RegionDominio> ensamblarDominioDesdeEntidad(RegionEntidad entidad) {

		if (UtilObjeto.esNulo(entidad)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una region a partir de una entidad de una region nula");
		}

		RegionDominio region = RegionDominio.crear(entidad.getCodigo(),
				CeldaEnsambladorImpl.obtenerCeldaEnsablador().celdasDominio(entidad.getCeldas()),
				PosicionEnsambladorImpl.obtenerPosicionEnsablador().ensamblarDominioDesdeEntidad(entidad.getPosicion())
						.orElse(null));
		return Optional.of(region);
	}

	@Override
	public Optional<RegionEntidad> ensamblarEntidad(RegionDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una entidad de una region a partir de un dominio de una region nula");
		}

		RegionEntidad region = RegionEntidad.crear(dominio.getCodigo(),
				CeldaEnsambladorImpl.obtenerCeldaEnsablador().celdasEntidad(dominio.getCeldas()),
				PosicionEnsambladorImpl.obtenerPosicionEnsablador().ensamblarEntidad(dominio.getPosicion())
						.orElse(null));
		return Optional.of(region);
	}

	@Override
	public List<RegionDominio> ensamblarDominiosDesdeEntidad(List<RegionEntidad> entidades) {
		List<RegionDominio> dominios = new ArrayList<>();

		for (RegionEntidad entidad : entidades) {
			dominios.add(ensamblarDominioDesdeEntidad(entidad).get());
		}
		return dominios;
	}

	@Override
	public RegionEntidad[][] regionesEntidad(RegionDominio[][] matrizRegiones) {

		if (UtilObjeto.esNulo(matrizRegiones)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una matriz de regiones de una entidad a partir de un una matriz de regiones de un dominio nulo");
		}

		RegionEntidad[][] regiones = new RegionEntidad[3][3];

		for (int fila = 0; fila <= 2; fila++) {
			for (int columna = 0; columna <= 2; columna++) {
				regiones[fila][columna] = ensamblarEntidad(matrizRegiones[fila][columna]).get();
			}
		}

		return regiones;
	}

	@Override
	public RegionDominio[][] regionesDominio(RegionEntidad[][] matrizRegiones) {

		if (UtilObjeto.esNulo(matrizRegiones)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una matriz de regiones de un dominio a partir de un una matriz de regiones de una entidad de una matriz nula");
		}

		RegionDominio[][] regiones = new RegionDominio[3][3];

		for (int fila = 0; fila <= 2; fila++) {
			for (int columna = 0; columna <= 2; columna++) {
				regiones[fila][columna] = ensamblarDominioDesdeEntidad(matrizRegiones[fila][columna]).get();
			}
		}

		return regiones;
	}

	@Override
	public RegionDominio[][] regionesDominio(RegionDTO[][] matrizRegiones) {

		if (UtilObjeto.esNulo(matrizRegiones)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una matriz de regiones de un dominio a partir de un una matriz de regiones de un dto nulo");
		}

		RegionDominio[][] regiones = new RegionDominio[3][3];

		for (int fila = 0; fila <= 2; fila++) {
			for (int columna = 0; columna <= 2; columna++) {
				regiones[fila][columna] = ensamblarDominioDesdeDTO(matrizRegiones[fila][columna]);
			}
		}

		return regiones;
	}

	@Override
	public RegionDTO[][] regionesDTO(RegionDominio[][] matrizRegiones) {

		if (UtilObjeto.esNulo(matrizRegiones)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una matriz de regiones de un dto a partir de un una matriz de regiones de un dominio nulo");
		}

		RegionDTO[][] regiones = new RegionDTO[3][3];

		for (int fila = 0; fila <= 2; fila++) {
			for (int columna = 0; columna <= 2; columna++) {
				regiones[fila][columna] = ensamblarDTO(matrizRegiones[fila][columna]);
			}
		}

		return regiones;
	}

	@Override
	public RegionDominio ensamblarDominioDesdeDTO(RegionDTO dto) {

		if (UtilObjeto.esNulo(dto)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una region a partir de un dto de una region nula");
		}

		return RegionDominio.crear(dto.getCodigo(),
				CeldaEnsambladorImpl.obtenerCeldaEnsablador().celdasDominio(dto.getCeldas()),
				PosicionEnsambladorImpl.obtenerPosicionEnsablador().ensamblarDominioDesdeDTO(dto.getPosicion()));
	}

	@Override
	public List<RegionDominio> ensamblarDominiosDesdeDTO(List<RegionDTO> dtos) {
		return dtos.stream().map(obtenerRegionEnsablador()::ensamblarDominioDesdeDTO).collect(Collectors.toList());
	}

	@Override
	public RegionDTO ensamblarDTO(RegionDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dto de una region a partir de un dominio de una region nula");
		}

		return RegionDTO.crear(dominio.getCodigo(),
				CeldaEnsambladorImpl.obtenerCeldaEnsablador().celdasDTO(dominio.getCeldas()),
				PosicionEnsambladorImpl.obtenerPosicionEnsablador().ensamblarDTO(dominio.getPosicion()));
	}

	@Override
	public List<RegionDTO> ensamblarDTOs(List<RegionDominio> dominios) {
		return dominios.stream().map(obtenerRegionEnsablador()::ensamblarDTO).collect(Collectors.toList());
	}

}
