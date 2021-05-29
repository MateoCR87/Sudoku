package co.edu.uco.sudoku.datos.entidad;

public class RegionEntidad {

	private int codigo;
	private CeldaEntidad[][] celdas = new CeldaEntidad[3][3];
	private PosicionEntidad posicion;

	private RegionEntidad(int codigo, CeldaEntidad[][] celdas, PosicionEntidad posicion) {
		setCodigo(codigo);
		setCeldas(celdas);
		setPosicion(posicion);
	}

	public static RegionEntidad crear() {
		return new RegionEntidad(0, null, null);
	}

	public static RegionEntidad crear(int codigo, CeldaEntidad[][] celdas, PosicionEntidad posicion) {
		return new RegionEntidad(codigo, celdas, posicion);
	}

	public RegionEntidad setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public RegionEntidad setCeldas(CeldaEntidad[][] celdas) {
		this.celdas = celdas;
		return this;
	}

	public RegionEntidad setPosicion(PosicionEntidad posicion) {
		this.posicion = posicion;
		return this;
	}

	public int getCodigo() {
		return codigo;
	}

	public CeldaEntidad[][] getCeldas() {
		return celdas;
	}

	public PosicionEntidad getPosicion() {
		return posicion;
	}
}
