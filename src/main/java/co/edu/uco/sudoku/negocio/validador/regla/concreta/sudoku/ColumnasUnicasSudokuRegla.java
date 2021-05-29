package co.edu.uco.sudoku.negocio.validador.regla.concreta.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.dominio.SudokuDominio;
import co.edu.uco.sudoku.negocio.validador.regla.Regla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;

public class ColumnasUnicasSudokuRegla implements Regla<SudokuDominio> {

	private static final Regla<SudokuDominio> INSTANCIA = new ColumnasUnicasSudokuRegla();

	private ColumnasUnicasSudokuRegla() {
		super();
	}

	public static Regla<SudokuDominio> obtenerInstancia() {

		return INSTANCIA;
	}

	@Override
	public void validar(SudokuDominio dato) {
		for (int columnaAEvaluar = 1; columnaAEvaluar < 10; columnaAEvaluar++) {
			List<RegionDominio> regiones = listaDeRegiones(dato, columnaAEvaluar);
			List<CeldaDominio> celdas = unirListas(convertirMatrizEnLista(regiones.get(0).getCeldas()),
					convertirMatrizEnLista(regiones.get(1).getCeldas()),
					convertirMatrizEnLista(regiones.get(2).getCeldas()));
			filaContieneNumerosRepetidos(filtarCeldasBuscadas(celdas, columnaAEvaluar), columnaAEvaluar);
		}

	}

	public void filaContieneNumerosRepetidos(List<CeldaDominio> lista, int columna) {

		for (int numero = 1; numero < 10; numero++) {

			boolean numeroYaEncontrado = false;

			for (CeldaDominio celdas : lista) {

				if (verificarSiCeldaContieneNumero(celdas, numero) && numeroYaEncontrado) {
					throw new SudokuDominioExepcion("La columna " + columna + " contiene numeros repetidos");
				}

				if (verificarSiCeldaContieneNumero(celdas, numero)) {
					numeroYaEncontrado = true;
				}
			}
		}
	}

	private List<CeldaDominio> filtarCeldasBuscadas(List<CeldaDominio> lista, int columna) {
		StringBuilder posicionCelda1 = new StringBuilder();
		posicionCelda1.append(1).append("-").append(((columna - 1) % 3) + 1);

		StringBuilder posicionCelda2 = new StringBuilder();
		posicionCelda2.append(2).append("-").append(((columna - 1) % 3) + 1);

		StringBuilder posicionCelda3 = new StringBuilder();
		posicionCelda3.append(3).append("-").append(((columna - 1) % 3) + 1);

		return lista.stream()
				.filter(celdas -> celdas.getPosicion().getPosicionTexto().equals(posicionCelda1.toString())
						|| celdas.getPosicion().getPosicionTexto().equals(posicionCelda2.toString())
						|| celdas.getPosicion().getPosicionTexto().equals(posicionCelda3.toString()))
				.collect(Collectors.toList());
	}

	private List<CeldaDominio> unirListas(List<CeldaDominio> primeraRegion, List<CeldaDominio> segundaRegion,
			List<CeldaDominio> terceraRegion) {
		List<CeldaDominio> listaFinal = new ArrayList<>();
		listaFinal.addAll(primeraRegion);
		listaFinal.addAll(segundaRegion);
		listaFinal.addAll(terceraRegion);
		return listaFinal;
	}

	private List<RegionDominio> listaDeRegiones(SudokuDominio dato, int columna) {
		StringBuilder posicionRegion1 = new StringBuilder();
		posicionRegion1.append(1).append("-").append(((columna - 1) / 3) + 1);

		StringBuilder posicionRegion2 = new StringBuilder();
		posicionRegion2.append(2).append("-").append(((columna - 1) / 3) + 1);

		StringBuilder posicionRegion3 = new StringBuilder();
		posicionRegion3.append(3).append("-").append(((columna - 1) / 3) + 1);

		return Arrays.stream(dato.getRegiones()).flatMap(Arrays::stream)
				.filter(region -> region.getPosicion().getPosicionTexto().equals(posicionRegion1.toString())
						|| region.getPosicion().getPosicionTexto().equals(posicionRegion2.toString())
						|| region.getPosicion().getPosicionTexto().equals(posicionRegion3.toString()))
				.collect(Collectors.toList());

	}

	private List<CeldaDominio> convertirMatrizEnLista(CeldaDominio[][] matriz) {
		List<CeldaDominio> celdas = new ArrayList<>();

		for (int fila = 0; fila < 3; fila++) {
			for (int columna = 0; columna < 3; columna++) {
				celdas.add(matriz[fila][columna]);
			}
		}
		return celdas;
	}

	private boolean verificarSiCeldaContieneNumero(CeldaDominio celda, int numero) {
		return celda.getNumero() == numero;
	}
}
