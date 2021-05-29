package co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.dominio.JugadorDominio;
import co.edu.uco.sudoku.dto.JugadorDTO;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.JugadorEnsamblador;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class JugadorEnsambladorImpl implements JugadorEnsamblador {

	private static final JugadorEnsamblador INSTANCIA = new JugadorEnsambladorImpl();

	private JugadorEnsambladorImpl() {
	}

	public static JugadorEnsamblador obtenerJugadorEnsablador() {
		return INSTANCIA;
	}

	@Override
	public Optional<JugadorDominio> ensamblarDominioDesdeEntidad(JugadorEntidad entidad) {

		if (UtilObjeto.esNulo(entidad)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de un jugador a partir de una entidad de jugador que esta nulo");
		}

		JugadorDominio dominio = JugadorDominio.crear(entidad.getCodigo(), entidad.getNombre(),
				entidad.getDocumentoIdentificacion(), entidad.getCorreo(), entidad.getClave());

		return Optional.of(dominio);
	}

	@Override
	public Optional<JugadorEntidad> ensamblarEntidad(JugadorDominio dominio) {
		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una enbtidad de un jugador a partir de un dominio de jugador que esta nulo");
		}

		JugadorEntidad entidad = JugadorEntidad.crear(dominio.getCodigo(), dominio.getNombre(),
				dominio.getDocumentoIdentificacion(), dominio.getCorreo(), dominio.getClave());

		return Optional.of(entidad);
	}

	@Override
	public List<JugadorDominio> ensamblarDominiosDesdeEntidad(List<JugadorEntidad> entidades) {

		List<JugadorDominio> dominios = new ArrayList<>();

		for (JugadorEntidad entidad : entidades) {
			dominios.add(ensamblarDominioDesdeEntidad(entidad).orElse(null));
		}

		return dominios;
	}

	@Override
	public JugadorDominio ensamblarDominioDesdeDTO(JugadorDTO dto) {

		if (UtilObjeto.esNulo(dto)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de un jugador a partir de un dto de jugador que esta nulo");
		}
		return JugadorDominio.crear(dto.getCodigo(), dto.getNombre(), dto.getDocumentoIdentificacion(), dto.getCorreo(),
				dto.getClave());
	}

	@Override
	public List<JugadorDominio> ensamblarDominiosDesdeDTO(List<JugadorDTO> dtos) {
		return dtos.stream().map(obtenerJugadorEnsablador()::ensamblarDominioDesdeDTO).collect(Collectors.toList());
	}

	@Override
	public JugadorDTO ensamblarDTO(JugadorDominio dominio) {
		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un DTO de un jugador a partir de un dominio de jugador que esta nulo");
		}
		return JugadorDTO.crear(dominio.getCodigo(), dominio.getNombre(), dominio.getDocumentoIdentificacion(),
				dominio.getCorreo(), dominio.getClave());
	}

	@Override
	public List<JugadorDTO> ensamblarDTOs(List<JugadorDominio> dominios) {
		return dominios.stream().map(obtenerJugadorEnsablador()::ensamblarDTO).collect(Collectors.toList());
	}

}
