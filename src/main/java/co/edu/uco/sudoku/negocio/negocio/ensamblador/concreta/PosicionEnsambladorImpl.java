package co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.dominio.PosicionDominio;
import co.edu.uco.sudoku.dto.PosicionDTO;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.PosicionEnsamblador;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class PosicionEnsambladorImpl implements PosicionEnsamblador {

	private static final PosicionEnsamblador INSTANCIA = new PosicionEnsambladorImpl();

	private PosicionEnsambladorImpl() {
	}

	public static PosicionEnsamblador obtenerPosicionEnsablador() {
		return INSTANCIA;
	}

	@Override
	public Optional<PosicionDominio> ensamblarDominioDesdeEntidad(PosicionEntidad entidad) {

		if (UtilObjeto.esNulo(entidad)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una posicion a partir de una entidad de una posicion nula");
		}

		PosicionDominio posicion = PosicionDominio.crear(entidad.getCodigo(), entidad.getFila(), entidad.getColumna());
		return Optional.of(posicion);
	}

	@Override
	public Optional<PosicionEntidad> ensamblarEntidad(PosicionDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una entidad de una posicion a partir de un dominio de una posicion nula");
		}

		PosicionEntidad posicion = PosicionEntidad.crear(dominio.getCodigo(), dominio.getFila(), dominio.getColomna());
		return Optional.of(posicion);
	}

	@Override
	public List<PosicionDominio> ensamblarDominiosDesdeEntidad(List<PosicionEntidad> entidades) {
		List<PosicionDominio> dominios = new ArrayList<>();

		for (PosicionEntidad entidad : entidades) {
			dominios.add(ensamblarDominioDesdeEntidad(entidad).get());
		}
		return dominios;
	}

	@Override
	public PosicionDominio ensamblarDominioDesdeDTO(PosicionDTO dto) {

		if (UtilObjeto.esNulo(dto)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una posicion a partir de un dto de una posicion nula");
		}

		return PosicionDominio.crear(dto.getCodigo(), dto.getFila(), dto.getColumna());
	}

	@Override
	public List<PosicionDominio> ensamblarDominiosDesdeDTO(List<PosicionDTO> dtos) {
		return dtos.stream().map(obtenerPosicionEnsablador()::ensamblarDominioDesdeDTO).collect(Collectors.toList());
	}

	@Override
	public PosicionDTO ensamblarDTO(PosicionDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dto de una posicion a partir de un dominio de una posicion nula");
		}

		return PosicionDTO.crear(dominio.getCodigo(), dominio.getFila(), dominio.getColomna());
	}

	@Override
	public List<PosicionDTO> ensamblarDTOs(List<PosicionDominio> dominios) {
		return dominios.stream().map(obtenerPosicionEnsablador()::ensamblarDTO).collect(Collectors.toList());
	}

}
