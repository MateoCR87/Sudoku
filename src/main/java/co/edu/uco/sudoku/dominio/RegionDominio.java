package co.edu.uco.sudoku.dominio;

public class RegionDominio {

	private int codigo;
	private CeldaDominio[][] celdas = new CeldaDominio[3][3];
	private PosicionDominio posicion;

	private RegionDominio(int codigo, CeldaDominio[][] celdas, PosicionDominio posicion) {
		setCodigo(codigo);
		setCeldas(celdas);
		setPosicion(posicion);
	}

	public static RegionDominio crear(int codigo, CeldaDominio[][] celdas, PosicionDominio posicion) {
		return new RegionDominio(codigo, celdas, posicion);
	}


	private void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	private void setCeldas(CeldaDominio[][] celdas) {
		this.celdas = celdas;
	}

	private void setPosicion(PosicionDominio posicion) {
		this.posicion = posicion;
	}

	public int getCodigo() {
		return codigo;
	}

	public CeldaDominio[][] getCeldas() {
		return celdas;
	}

	public PosicionDominio getPosicion() {
		return posicion;
	}

}
