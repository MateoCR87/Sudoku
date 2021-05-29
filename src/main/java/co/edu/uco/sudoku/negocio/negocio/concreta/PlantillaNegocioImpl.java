package co.edu.uco.sudoku.negocio.negocio.concreta;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uco.sudoku.datos.PlantillaDatos;
import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.datos.entidad.PlantillaEntidad;
import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.PlantillaDominio;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.negocio.negocio.PlantillaNegocio;
import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.PlantillaEnsambladorImpl.obtenerPlantillaEnsablador;
import co.edu.uco.sudoku.negocio.validador.concreta.PlantillaValidador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDominioExepcion;

public class PlantillaNegocioImpl implements PlantillaNegocio {

	private PlantillaDatos plantillaDatos;

	public PlantillaNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.plantillaDatos = factoriaDatos.obtenerPlantillaDatos();
	}

	@Override
	public List<PlantillaDominio> consultar(PlantillaEntidad entidad) {
		return obtenerPlantillaEnsablador().ensamblarDominiosDesdeEntidad(plantillaDatos.consultar(entidad));
	}

	@Override
	public void registrarSudokuConPistas(PlantillaDominio plantilla) {
		PlantillaValidador.obtenerInstancia().validar(plantilla, TipoValidacion.CREACION);
		validarQuePlatillaNoExista(plantilla);
		
		plantillaDatos.crear(pasarCeldasAPistas(plantilla));
	} 
	
	private void validarQuePlatillaNoExista(PlantillaDominio platilla) {
		if(!consultar(PlantillaEntidad.crear().setCodigo(platilla.getCodigo())).isEmpty()) {
			throw new SudokuDominioExepcion("La plantilla a registrar ya existe ");
		}
	}
	
	private PlantillaEntidad pasarCeldasAPistas(PlantillaDominio plantilla) {
		PlantillaEntidad plantillaEntidad = obtenerPlantillaEnsablador().ensamblarEntidad(plantilla).get();
		for(int filaReg = 0; filaReg <3; filaReg++ ) {
			for(int columnaReg = 0; columnaReg <3; columnaReg++ ) {
				for(int filaCel = 0; filaCel <3; filaCel++ ) {
					for(int columnaCel = 0; columnaCel <3; columnaCel++ ) {
						plantillaEntidad.getRegiones()[filaReg][columnaReg].getCeldas()[filaCel][columnaCel]
								.setCodigo(SudokuDatosFactoria.obtenerFactoria().obtenerCeldaDatos().consultar(
										CeldaEntidad.crear().setEsPista(true)
										.setNumero(plantillaEntidad.getRegiones()[filaReg][columnaReg].getCeldas()[filaCel][columnaCel].getNumero())
										.setPosicion(plantillaEntidad.getRegiones()[filaReg][columnaReg].getCeldas()[filaCel][columnaCel].getPosicion())
										).stream().findFirst().get().getCodigo());
					}
				}
			}
		}
	
		return plantillaEntidad;
	}
	

	@Override
	public void registrarRegiones(List<RegionDominio> regiones) {
		validarRegionesNoRepetidas(regiones);

	}
	
	private void validarRegionesNoRepetidas(List<RegionDominio> lista) {
		List<Integer>  regiones = lista.stream().map(region -> region.getCodigo()).distinct().collect(Collectors.toList());

		if (regiones.size() < 9) {
			throw new SudokuDominioExepcion("En las regiones a registrar hay regiones repetidas");
		}
	}

	@Override
	public Optional<RegionDominio> obtenerRegionSoloPistas(PlantillaEntidad plantilaEntidad,
			PosicionEntidad posicionEntidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlantillaDominio> organizarPorDificultad() {
		return  (List<PlantillaDominio>) consultar(null).stream().sorted(Comparator.comparing(PlantillaDominio::getCodigo)) ;
	}

	@Override
	public boolean compararConSudoku(SudokuEntidad sudoku, PlantillaEntidad plantilla) {
		// TODO Auto-generated method stub
		return false;
	}

}
