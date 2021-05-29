package co.edu.uco.sudoku.negocio.validador.regla.concreta.region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.celda.CeldaValidaRegionSudokuRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.celda.NumeroCeldaValidarRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;

public class RegionCompletaSudokuRegla implements Regla<RegionDominio> {

	private static final Regla<RegionDominio> INSTANCIA = new RegionCompletaSudokuRegla();

	private RegionCompletaSudokuRegla() {
		super();
	}

	public static Regla<RegionDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(RegionDominio dato) {
		validarQueRegionTengaNueveCeldas(dato);
		celdasRegionSudokuSeanValidas(dato);
	}

	private void validarQueRegionTengaNueveCeldas(RegionDominio dato) {
		long cantidadCeldas = Arrays.stream(dato.getCeldas()).flatMap(Arrays::stream).filter(Objects::nonNull).count();

		if (cantidadCeldas < 9) {
			throw new SudokuDominioExepcion(
					"La region ubicada en la posicion " + dato.getPosicion() + " no tiene las 9 celdas completas");
		}
	}

	private void celdasRegionSudokuSeanValidas(RegionDominio dato) {
		List<CeldaDominio> celdasRegion = Arrays.stream(dato.getCeldas()).flatMap(Arrays::stream)
				.collect(Collectors.toList());
		List<Regla<CeldaDominio>> reglasCeldas = new ArrayList<>();

		reglasCeldas.add(CeldaValidaRegionSudokuRegla.obtenerInstancia());
		reglasCeldas.add(NumeroCeldaValidarRegla.obtenerInstancia());

		celdasRegion.forEach(celda -> {
			reglasCeldas.forEach(regla -> {
				regla.validar(celda);
			});
		});

	}

}
