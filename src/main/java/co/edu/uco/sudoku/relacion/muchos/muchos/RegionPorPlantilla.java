package co.edu.uco.sudoku.relacion.muchos.muchos;

public class RegionPorPlantilla {

	private int codigoRegion;
	private int codigoPlantilla;

	private RegionPorPlantilla(int codigoRegion, int codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
		this.codigoRegion = codigoRegion;
	}
	
	public static RegionPorPlantilla crear() {
		return new RegionPorPlantilla(0, 0);
	}

	public static RegionPorPlantilla crear(int codigoRegion, int codigoPlantilla) {
		return new RegionPorPlantilla(codigoRegion, codigoPlantilla);
	}

	
	public RegionPorPlantilla setCodigoRegion(int codigoRegion) {
		this.codigoRegion = codigoRegion;
		return this;
	}

	public RegionPorPlantilla setCodigoPlantilla(int codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
		return this;
	}

	public int getCodigoPlantilla() {
		return codigoPlantilla;
	}

	public int getCodigoRegion() {
		return codigoRegion;
	}

}
