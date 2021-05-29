package co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.entidad.ModalidadJuegoEntidad;
import co.edu.uco.sudoku.dominio.ModalidadJuegoDominio;
import co.edu.uco.sudoku.dto.ModalidadJuegoDTO;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.ModalidadJuegoEnsamblador;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class ModalidadJuegoEnsambladorImpl implements ModalidadJuegoEnsamblador {

	private static final ModalidadJuegoEnsamblador INSTANCIA = new ModalidadJuegoEnsambladorImpl();

	private ModalidadJuegoEnsambladorImpl() {
	}

	public static ModalidadJuegoEnsamblador obtenerJModalidaJuegoEnsablador() {
		return INSTANCIA;
	}

	@Override
	public Optional<ModalidadJuegoDominio> ensamblarDominioDesdeEntidad(ModalidadJuegoEntidad entidad) {

		if (UtilObjeto.esNulo(entidad)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una modalidad de juego a partir de una entidad de una modalidad de juego nula");
		}

		ModalidadJuegoDominio modalidad = ModalidadJuegoDominio.crear(entidad.getCodigo(), entidad.getNombre());
		return Optional.of(modalidad);
	}

	@Override
	public Optional<ModalidadJuegoEntidad> ensamblarEntidad(ModalidadJuegoDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una entidad de una modalidad de juego a partir de un dominio de una modalidad de juego nula");
		}

		ModalidadJuegoEntidad modalidad = ModalidadJuegoEntidad.crear(dominio.getCodigo(), dominio.getNombre());
		return Optional.of(modalidad);
	}

	@Override
	public List<ModalidadJuegoDominio> ensamblarDominiosDesdeEntidad(List<ModalidadJuegoEntidad> entidades) {
		List<ModalidadJuegoDominio> dominios = new ArrayList<>();

		for (ModalidadJuegoEntidad entidad : entidades) {
			dominios.add(ensamblarDominioDesdeEntidad(entidad).get());
		}
		return dominios;
	}

	@Override
	public ModalidadJuegoDominio ensamblarDominioDesdeDTO(ModalidadJuegoDTO dto) {

		if (UtilObjeto.esNulo(dto)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una modalidad de juego a partir de un dto de una modalidad de juego nula");
		}

		return ModalidadJuegoDominio.crear(dto.getCodigo(), dto.getNombre());
	}

	@Override
	public List<ModalidadJuegoDominio> ensamblarDominiosDesdeDTO(List<ModalidadJuegoDTO> dtos) {
		return dtos.stream().map(obtenerJModalidaJuegoEnsablador()::ensamblarDominioDesdeDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ModalidadJuegoDTO ensamblarDTO(ModalidadJuegoDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dto de una modalidad de juego a partir de un dominio de una modalidad de juego nula");
		}

		return ModalidadJuegoDTO.crear(dominio.getCodigo(), dominio.getNombre());
	}

	@Override
	public List<ModalidadJuegoDTO> ensamblarDTOs(List<ModalidadJuegoDominio> dominios) {
		return dominios.stream().map(obtenerJModalidaJuegoEnsablador()::ensamblarDTO).collect(Collectors.toList());
	}

}
