package co.edu.uco.sudoku.dto;

public class RegionDTO {

	private int codigo;
	private CeldaDTO[][] celdas = new CeldaDTO[3][3];
	private PosicionDTO posicion;

	private RegionDTO(int codigo, CeldaDTO[][] celdas, PosicionDTO posicion) {
		setCodigo(codigo);
		setCeldas(celdas);
		setPosicion(posicion);
	}

	public static RegionDTO crear() {
		return new RegionDTO(0, null, null);
	}

	public static RegionDTO crear(int codigo, CeldaDTO[][] celdas, PosicionDTO posicion) {
		return new RegionDTO(codigo, celdas, posicion);
	}

	public RegionDTO setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public RegionDTO setCeldas(CeldaDTO[][] celdas) {
		this.celdas = celdas;
		return this;
	}

	public RegionDTO setPosicion(PosicionDTO posicion) {
		this.posicion = posicion;
		return this;
	}

	public int getCodigo() {
		return codigo;
	}

	public CeldaDTO[][] getCeldas() {
		return celdas;
	}

	public PosicionDTO getPosicion() {
		return posicion;
	}
}
