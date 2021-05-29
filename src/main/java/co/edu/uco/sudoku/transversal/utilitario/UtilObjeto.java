package co.edu.uco.sudoku.transversal.utilitario;

public class UtilObjeto {

	private UtilObjeto() {
	}

	public static <T> boolean esNulo(T objeto) {
		return objeto == null;
	}

	public static <T> T obtenerValorDefecto(T objeto, T valorDefecto) {
		return esNulo(objeto) ? valorDefecto : objeto;
	}
}
