package co.edu.uco.sudoku.negocio.validador.regla.concreta.region;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;

public class CeldasUbicadasEnPosicionUnicaEnRegionRegla implements Regla<RegionDominio> {

	private static final Regla<RegionDominio> INSTANCIA = new CeldasUbicadasEnPosicionUnicaEnRegionRegla();

	private CeldasUbicadasEnPosicionUnicaEnRegionRegla() {
		super();
	}

	public static Regla<RegionDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(RegionDominio dato) {
		List<String> celdas = Arrays.stream(dato.getCeldas()).flatMap(Arrays::stream)
				.map(celda -> celda.getPosicion().getPosicionTexto()).distinct().collect(Collectors.toList());

		if (celdas.size() < 9) {
			throw new SudokuDominioExepcion("La region ubicada en la posicion " + dato.getPosicion()
					+ " tiene celdas ubicadas dentre de una misma posicion!!!");
		}
	}

}
