package co.edu.uco.sudoku.relacion.muchos.muchos;

public class CeldaPorRegion {

	private int codigoCelda;
	private int codigoRegion;

	private CeldaPorRegion(int codigoCelda, int codigoRegion) {
		this.codigoCelda = codigoCelda;
		this.codigoRegion = codigoRegion;
	}
	
	public static CeldaPorRegion crear() {
		return new CeldaPorRegion(0, 0);
	}

	public static CeldaPorRegion crear(int codigoCelda, int codigoRegion) {
		return new CeldaPorRegion(codigoCelda, codigoRegion);
	}

	public CeldaPorRegion setCodigoCelda(int codigoCelda) {
		this.codigoCelda = codigoCelda;
		return this;
	}

	public CeldaPorRegion setCodigoRegion(int codigoRegion) {
		this.codigoRegion = codigoRegion;
		return this;
	}

	public int getCodigoCelda() {
		return codigoCelda;
	}

	public int getCodigoRegion() {
		return codigoRegion;
	}
}
