package co.edu.uco.sudoku.transversal.utilitario;

public class UtilNumerico {

	private UtilNumerico() {
	}

	@SuppressWarnings("unchecked")
	public static <T extends Number> T obtenerNumeroDefecto(T numero) {
		return (T) UtilObjeto.obtenerValorDefecto(numero, 0);
	}

	public static <T extends Number> boolean numeroEsMayor(T numero, T numeroComparador) {
		return obtenerNumeroDefecto(numero).doubleValue() > obtenerNumeroDefecto(numeroComparador).doubleValue();
	}

	public static <T extends Number> boolean numeroEsMenor(T numero, T numeroComparador) {
		return obtenerNumeroDefecto(numero).doubleValue() < obtenerNumeroDefecto(numeroComparador).doubleValue();
	}

	public static <T extends Number> boolean numeroEsIgual(T numero, T numeroComparador) {
		return obtenerNumeroDefecto(numero).doubleValue() == obtenerNumeroDefecto(numeroComparador).doubleValue();
	}

	public static <T extends Number> boolean numeroEsMayorOIgual(T numero, T numeroComparador) {
		return numeroEsMayor(numero, numeroComparador) || numeroEsIgual(numero, numeroComparador);
	}

	public static <T extends Number> boolean numeroEsMenorOIgual(T numero, T numeroComparador) {
		return numeroEsMenor(numero, numeroComparador) || numeroEsIgual(numero, numeroComparador);
	}

	public static <T extends Number> boolean numeroEsDiferente(T numero, T numeroComparador) {
		return !numeroEsIgual(numero, numeroComparador);
	}

	public static <T extends Number> boolean numeroEstaEntre(T numero, T limiteInferior, T limiteSuperior) {
		return numeroEsMayorOIgual(numero, limiteInferior) && numeroEsMenorOIgual(numero, limiteSuperior);
	}

}
