package co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.dto.CeldaDTO;
import co.edu.uco.sudoku.negocio.negocio.ensamblador.CeldaEnsamblador;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;

public class CeldaEnsambladorImpl implements CeldaEnsamblador {

	private static final CeldaEnsamblador INSTANCIA = new CeldaEnsambladorImpl();

	private CeldaEnsambladorImpl() {
	}

	public static CeldaEnsamblador obtenerCeldaEnsablador() {
		return INSTANCIA;
	}

	@Override
	public Optional<CeldaDominio> ensamblarDominioDesdeEntidad(CeldaEntidad entidad) {

		if (UtilObjeto.esNulo(entidad)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar un dominio de una celda a partir de una entidad de una celda que esta nula");
		}

		CeldaDominio celda = CeldaDominio.crear(entidad.getCodigo(), entidad.getNumero(), entidad.isEsPista(),
				PosicionEnsambladorImpl.obtenerPosicionEnsablador().ensamblarDominioDesdeEntidad(entidad.getPosicion())
						.get());

		return Optional.of(celda);
	}

	@Override
	public Optional<CeldaEntidad> ensamblarEntidad(CeldaDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una entidad de una celda a partir de un dominio de celda que esta nulo");
		}

		CeldaEntidad celda = CeldaEntidad.crear(dominio.getCodigo(), dominio.getNumero(), dominio.isEsPista(),
				PosicionEnsambladorImpl.obtenerPosicionEnsablador().ensamblarEntidad(dominio.getPosicion()).get());

		return Optional.of(celda);
	}

	@Override
	public List<CeldaDominio> ensamblarDominiosDesdeEntidad(List<CeldaEntidad> entidades) {
		List<CeldaDominio> dominios = new ArrayList<>();

		for (CeldaEntidad entidad : entidades) {
			dominios.add(ensamblarDominioDesdeEntidad(entidad).get());
		}
		return dominios;
	}

	@Override
	public CeldaEntidad[][] celdasEntidad(CeldaDominio[][] matrizCeldas) {

		if (UtilObjeto.esNulo(matrizCeldas)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una matriz  de celdas de entidad a partir de una matriz de celdas de dominio nula");
		}

		CeldaEntidad[][] celdas = new CeldaEntidad[3][3];

		for (int fila = 0; fila <= 2; fila++) {
			for (int columna = 0; columna <= 2; columna++) {
				celdas[fila][columna] = ensamblarEntidad(matrizCeldas[fila][columna]).get();
			}
		}
		return celdas;
	}

	@Override
	public CeldaDominio[][] celdasDominio(CeldaEntidad[][] matrizCeldas) {

		if (UtilObjeto.esNulo(matrizCeldas)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una matriz  de celdas de dominio a partir de una matriz de celdas de entidad nula");
		}

		CeldaDominio[][] celdas = new CeldaDominio[3][3];

		for (int fila = 0; fila <= 2; fila++) {
			for (int columna = 0; columna <= 2; columna++) {
				celdas[fila][columna] = ensamblarDominioDesdeEntidad(matrizCeldas[fila][columna]).get();
			}
		}
		return celdas;
	}

	@Override
	public CeldaDominio[][] celdasDominio(CeldaDTO[][] matrizCeldas) {

		if (UtilObjeto.esNulo(matrizCeldas)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una matriz  de celdas de dominio a partir de una matriz de celdas de dto nula");
		}

		CeldaDominio[][] celdas = new CeldaDominio[3][3];

		for (int fila = 0; fila <= 2; fila++) {
			for (int columna = 0; columna <= 2; columna++) {
				celdas[fila][columna] = ensamblarDominioDesdeDTO(matrizCeldas[fila][columna]);
			}
		}
		return celdas;
	}

	@Override
	public CeldaDTO[][] celdasDTO(CeldaDominio[][] matrizCeldas) {

		if (UtilObjeto.esNulo(matrizCeldas)) {
			throw new SudokuNegocioExeption(
					"No es posible ensamblar una matriz  de celdas de dto a partir de una matriz de celdas de dominio nula");
		}

		CeldaDTO[][] celdas = new CeldaDTO[3][3];

		for (int fila = 0; fila <= 2; fila++) {
			for (int columna = 0; columna <= 2; columna++) {
				celdas[fila][columna] = ensamblarDTO(matrizCeldas[fila][columna]);
			}
		}
		return celdas;
	}

	@Override
	public CeldaDominio ensamblarDominioDesdeDTO(CeldaDTO dto) {

		if (UtilObjeto.esNulo(dto)) {
			throw new SudokuNegocioExeption("No es posible ensamblar una celda dominio a partir de una celda dto nula");
		}

		return CeldaDominio.crear(dto.getCodigo(), dto.getNumero(), dto.isEsPista(),
				PosicionEnsambladorImpl.obtenerPosicionEnsablador().ensamblarDominioDesdeDTO(dto.getPosicion()));
	}

	@Override
	public List<CeldaDominio> ensamblarDominiosDesdeDTO(List<CeldaDTO> dtos) {
		return dtos.stream().map(obtenerCeldaEnsablador()::ensamblarDominioDesdeDTO).collect(Collectors.toList());
	}

	@Override
	public CeldaDTO ensamblarDTO(CeldaDominio dominio) {

		if (UtilObjeto.esNulo(dominio)) {
			throw new SudokuNegocioExeption("No es posible ensamblar una celda dto a partir de una celda dominio nula");
		}

		return CeldaDTO.crear(dominio.getCodigo(), dominio.getNumero(), dominio.isEsPista(),
				PosicionEnsambladorImpl.obtenerPosicionEnsablador().ensamblarDTO(dominio.getPosicion()));
	}

	@Override
	public List<CeldaDTO> ensamblarDTOs(List<CeldaDominio> dominios) {
		return dominios.stream().map(obtenerCeldaEnsablador()::ensamblarDTO).collect(Collectors.toList());
	}

}
