package co.edu.uco.sudoku.transversal.utilitario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.edu.uco.sudoku.transversal.exepcion.SudokuUtilExeption;

public final class UtilTexto {

	private UtilTexto() {
	}

	private static final String PATRON_SOLO_LETRAS_ESPACIOS = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ ]*$";
	private static final String PATRON_CORREO_ELECTRONICO = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	private static final String PATRON_DESCRIPCION = "^[a-zA-Z0-9ÑáéíóúÁÉÍÓÚ.?-'!* ]*$";
	private static final String PATRON_MINIMO_UN_SIMBOLO = "^.*[\\!\\\"\\#\\$\\%\\&\\'\\(\\)\\*\\-\\+\\/].+$";
	private static final String PATRON_MINIMO_UNA_LETRA_MINUSCULA = "^.*[a-z].*$";
	private static final String PATRON_MINIMO_UNA_LETRA_MAYUSCULA = "^.*[A-Z].*$";
	private static final String PATRON_MINIMO_UN_DIGITO = "^.*[0-9].*$";
	public static final String BLANCO = "";

	public static boolean cadenaEsNula(String cadena) {
		return cadena == null;
	}

	public static String obtenerValorDefecto(String cadena) {
		return UtilObjeto.obtenerValorDefecto(cadena, "");
	}

	public static String aplicarTrim(String cadena) {
		return obtenerValorDefecto(cadena).trim();
	}

	public static boolean cadenaEstaVacia(String cadena) {
		return "".equals(aplicarTrim(cadena));
	}

	public static boolean longitudMinimaEsValida(String cadena, int longitudMinima) {
		return UtilNumerico.numeroEsMayorOIgual(aplicarTrim(cadena).length(), longitudMinima);
	}

	public static boolean longitudMaximaEsValida(String cadena, int longitudMaxiam) {
		return UtilNumerico.numeroEsMenorOIgual(aplicarTrim(cadena).length(), longitudMaxiam);
	}

	public static boolean longitudEsValida(String cadena, int longitudMinima, int longitudMaxima) {
		return longitudMinimaEsValida(cadena, longitudMinima) && longitudMaximaEsValida(cadena, longitudMaxima);
	}

	public static boolean cadenaCumplePatron(String cadena, String patron) {
		return aplicarTrim(cadena).matches(patron);
	}

	public static boolean cadenaContieneSoloLetrasYEspacios(String cadena) {
		return cadenaCumplePatron(cadena, PATRON_SOLO_LETRAS_ESPACIOS);
	}

	public static boolean cadenaContieneCorreoElectronico(String cadena) {
		return cadenaCumplePatron(cadena, PATRON_CORREO_ELECTRONICO);
	}

	public static boolean cadenaContieneLetraMayuscula(String cadena) {
		return cadenaCumplePatron(cadena, PATRON_MINIMO_UNA_LETRA_MAYUSCULA);
	}

	public static boolean cadenaContieneLetraMinuscula(String cadena) {
		return cadenaCumplePatron(cadena, PATRON_MINIMO_UNA_LETRA_MINUSCULA);
	}

	public static boolean cadenaContieneUnDijito(String cadena) {
		return cadenaCumplePatron(cadena, PATRON_MINIMO_UN_DIGITO);
	}

	public static boolean cadenaContieneUnSimbolo(String cadena) {
		return cadenaCumplePatron(cadena, PATRON_MINIMO_UN_SIMBOLO);
	}

	public static boolean cadenaDescripcion(String cadena) {
		return cadenaCumplePatron(cadena, PATRON_DESCRIPCION);
	}

	public static Date convertirCadenaAFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
		} catch (ParseException formatoIncorrecto) {
			throw new SudokuUtilExeption("La fecha tiene un formato invalido.");
		}
		return fechaDate;
	}
}
