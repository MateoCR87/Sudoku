package co.edu.uco.sudoku.negocio.validador.regla.concreta.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.dominio.SudokuDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.region.CeldasUbicadasEnPosicionUnicaEnRegionRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.region.NumerosUnicosEnRegionRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.region.RegionCompletaSudokuRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;

public class RegionesValidasSudokuRegla implements Regla<SudokuDominio> {

	private static final Regla<SudokuDominio> INSTANCIA = new RegionesValidasSudokuRegla();

	private RegionesValidasSudokuRegla() {
		super();
	}

	public static Regla<SudokuDominio> obtenerInstancia() {
		return INSTANCIA;
	}

	@Override
	public void validar(SudokuDominio dato) {
		validarRegionesDiligenciadas(dato);
		regionesUbicadasEnPosicionUnicaSudoku(dato);
		validarCeldasRegiones(dato);
	}

	public void validarRegionesDiligenciadas(SudokuDominio dato) {
		long cantidadRegiones = Arrays.stream(dato.getRegiones()).flatMap(Arrays::stream).filter(Objects::nonNull)
				.count();

		if (cantidadRegiones < 9) {
			throw new SudokuDominioExepcion("El sudoku tiene que tener las 9 regiones diligenciadas");
		}
	}

	public void regionesUbicadasEnPosicionUnicaSudoku(SudokuDominio dato) {
		List<String> celdas = Arrays.stream(dato.getRegiones()).flatMap(Arrays::stream)
				.map(region -> region.getPosicion().getPosicionTexto()).distinct().collect(Collectors.toList());

		if (celdas.size() < 9) {
			throw new SudokuDominioExepcion("El sudoku tiene regiones ubicadas en la misma posicion!!");
		}

	}

	public void validarCeldasRegiones(SudokuDominio dato) {
		List<RegionDominio> regiones = Arrays.stream(dato.getRegiones()).flatMap(Arrays::stream)
				.collect(Collectors.toList());

		List<Regla<RegionDominio>> reglasRegion = new ArrayList<>();
		reglasRegion.add(RegionCompletaSudokuRegla.obtenerInstancia());
		reglasRegion.add(CeldasUbicadasEnPosicionUnicaEnRegionRegla.obtenerInstancia());
		reglasRegion.add(NumerosUnicosEnRegionRegla.obtenerInstancia());

		for (RegionDominio region : regiones) {
			for (Regla<RegionDominio> regla : reglasRegion) {
				regla.validar(region);
			}
		}
	}
}
