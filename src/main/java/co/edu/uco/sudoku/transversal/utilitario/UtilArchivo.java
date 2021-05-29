package co.edu.uco.sudoku.transversal.utilitario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.edu.uco.sudoku.transversal.exepcion.SudokuUtilExeption;

public class UtilArchivo {

	private UtilArchivo() {
	}

	public static List<String> leerLineasArchivo(String ruta) {

		if (UtilTexto.cadenaEstaVacia(ruta)) {
			throw new SudokuUtilExeption("no es posible leer el contenido de una ruta vacia");
		}

		List<String> lineas = new ArrayList<>();

		try (Scanner escaner = new Scanner(new File(ruta))) {
			while (escaner.hasNextLine()) {
				lineas.add(escaner.nextLine());
			}
		} catch (FileNotFoundException exepcion) {
			throw new SudokuUtilExeption(
					"No existe el archivo en la ruta " + ruta + " para el cual se quieren extraer datos.");
		}

		return lineas;
	}
}
