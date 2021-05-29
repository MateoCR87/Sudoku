package co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.entidad.PartidaEntidad;
import co.edu.uco.sudoku.dominio.PartidaDominio;
import co.edu.uco.sudoku.dto.PartidaDTO;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.PartidaEnsamblador;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class PartidaEnsambladorImpl implements PartidaEnsamblador {

	private static final PartidaEnsamblador INSTANCIA = new PartidaEnsambladorImpl();

	private PartidaEnsambladorImpl() {
	}

	public static PartidaEnsamblador obtenerPartidaEnsambladorImpl() {
		return INSTANCIA;
	}

	@Override
	public Optional<PartidaDominio> ensamblarDominioDesdeEntidad(PartidaEntidad entidad) {

		if (UtilObjeto.esNulo(entidad)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una partida a partir de una entidad de una partida nula");
		}

		PartidaDominio partida = PartidaDominio.crear(entidad.getCodigo(),
				JugadorEnsambladorImpl.obtenerJugadorEnsablador().ensamblarDominioDesdeEntidad(entidad.getJugador())
						.get(),
				PlantillaEnsambladorImpl.obtenerPlantillaEnsablador()
						.ensamblarDominioDesdeEntidad(entidad.getPlantilla()).get(),
				ModalidadJuegoEnsambladorImpl.obtenerJModalidaJuegoEnsablador()
						.ensamblarDominioDesdeEntidad(entidad.getModalidad()).get(),
				entidad.getFechaFinal(), entidad.getFechaFinal(), entidad.getDuracion(), entidad.isJuegoCompletado());
		return Optional.of(partida);
	}

	@Override
	public Optional<PartidaEntidad> ensamblarEntidad(PartidaDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una entidad de una partida a partir de un dominio de una partida nula");
		}

		PartidaEntidad partida = PartidaEntidad.crear(dominio.getCodigo(),
				JugadorEnsambladorImpl.obtenerJugadorEnsablador().ensamblarEntidad(dominio.getJugador()).get(),
				PlantillaEnsambladorImpl.obtenerPlantillaEnsablador().ensamblarEntidad(dominio.getPlantilla()).get(),
				ModalidadJuegoEnsambladorImpl.obtenerJModalidaJuegoEnsablador().ensamblarEntidad(dominio.getModalidad())
						.get(),
				dominio.getFechaInicial(), dominio.getFechaFinal(), dominio.getDuracion(), dominio.isJuegoCompletado());
		return Optional.of(partida);
	}

	@Override
	public List<PartidaDominio> ensamblarDominiosDesdeEntidad(List<PartidaEntidad> entidades) {
		List<PartidaDominio> dominios = new ArrayList<>();

		for (PartidaEntidad entidad : entidades) {
			dominios.add(ensamblarDominioDesdeEntidad(entidad).get());
		}
		return dominios;
	}

	@Override
	public PartidaDominio ensamblarDominioDesdeDTO(PartidaDTO dto) {

		if (UtilObjeto.esNulo(dto)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una partida a partir de un dto de una partida nula");
		}

		return PartidaDominio.crear(dto.getCodigo(),
				JugadorEnsambladorImpl.obtenerJugadorEnsablador().ensamblarDominioDesdeDTO(dto.getJugador()),
				PlantillaEnsambladorImpl.obtenerPlantillaEnsablador().ensamblarDominioDesdeDTO(dto.getPlantilla()),
				ModalidadJuegoEnsambladorImpl.obtenerJModalidaJuegoEnsablador()
						.ensamblarDominioDesdeDTO(dto.getModalidad()),
				dto.getFechaFinal(), dto.getFechaFinal(), dto.getDuracion(), dto.isJuegoCompletado());
	}

	@Override
	public List<PartidaDominio> ensamblarDominiosDesdeDTO(List<PartidaDTO> dtos) {
		return dtos.stream().map(obtenerPartidaEnsambladorImpl()::ensamblarDominioDesdeDTO)
				.collect(Collectors.toList());
	}

	@Override
	public PartidaDTO ensamblarDTO(PartidaDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dto de una partida a partir de un dominio de una partida nula");
		}

		return PartidaDTO.crear(dominio.getCodigo(),
				JugadorEnsambladorImpl.obtenerJugadorEnsablador().ensamblarDTO(dominio.getJugador()),
				PlantillaEnsambladorImpl.obtenerPlantillaEnsablador().ensamblarDTO(dominio.getPlantilla()),
				ModalidadJuegoEnsambladorImpl.obtenerJModalidaJuegoEnsablador().ensamblarDTO(dominio.getModalidad()),
				dominio.getFechaInicial(), dominio.getFechaFinal(), dominio.getDuracion(), dominio.isJuegoCompletado());
	}

	@Override
	public List<PartidaDTO> ensamblarDTOs(List<PartidaDominio> dominios) {
		return dominios.stream().map(obtenerPartidaEnsambladorImpl()::ensamblarDTO).collect(Collectors.toList());
	}

}
