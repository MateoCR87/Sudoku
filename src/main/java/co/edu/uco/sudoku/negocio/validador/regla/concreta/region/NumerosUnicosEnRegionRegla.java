package co.edu.uco.sudoku.negocio.validador.regla.concreta.region;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;

public class NumerosUnicosEnRegionRegla implements Regla<RegionDominio> {

	private static final Regla<RegionDominio> INSTANCIA = new NumerosUnicosEnRegionRegla();

	private NumerosUnicosEnRegionRegla() {
		super();
	}

	public static Regla<RegionDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(RegionDominio dato) {
		List<CeldaDominio> celdas = Arrays.stream(dato.getCeldas()).flatMap(Arrays::stream)
				.collect(Collectors.toList());

		List<Integer> numeros = celdas.stream().map(CeldaDominio::getNumero).distinct().collect(Collectors.toList());

		if (numeros.size() < 9) {
			throw new SudokuDominioExepcion(
					"La region ubicada en la posicion " + dato.getPosicion() + " tiene numeros repetidos");
		}
	}

}
