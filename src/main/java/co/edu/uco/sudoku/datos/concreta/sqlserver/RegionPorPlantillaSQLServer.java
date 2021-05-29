package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.RegionDatos;
import co.edu.uco.sudoku.datos.RegionPorPlantillaDatos;
import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.relacion.muchos.muchos.RegionPorPlantilla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuRelacionArchivoExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;

public class RegionPorPlantillaSQLServer implements RegionPorPlantillaDatos {

	private Connection conexion;
	
	 public RegionPorPlantillaSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de  las regiones por plantilla para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}


	@Override
	public RegionEntidad[][] matrizRegiones(int codigoSudoku) {
		RegionDatos region = SudokuDatosFactoria.obtenerFactoria().obtenerRegionDatos();

		RegionEntidad[][] matrizRegiones = new RegionEntidad[3][3];

		for (RegionPorPlantilla regionPorPlantilla : consultar(RegionPorPlantilla.crear().setCodigoPlantilla(codigoSudoku))) {

			List<RegionEntidad> listaCeldas = region
					.consultar(RegionEntidad.crear().setCodigo(regionPorPlantilla.getCodigoRegion()));
			RegionEntidad regionEntidad = listaCeldas.stream()
					.filter(posicion -> posicion.getCodigo() == regionPorPlantilla.getCodigoRegion()).findFirst()
					.orElse(RegionEntidad.crear());

			if (UtilObjeto.esNulo(regionEntidad)) {
				throw new SudokuRelacionArchivoExepcion("Se perden datos haciendo la matriz de las regiones de la plantilla.");

			}

			matrizRegiones[regionEntidad.getPosicion().getFila() - 1][regionEntidad.getPosicion().getColumna()
					- 1] = regionEntidad;
		}
		return matrizRegiones;
	}


	@Override
	public List<RegionPorPlantilla> consultar(RegionPorPlantilla entidad) {
		List<RegionPorPlantilla> regionesPorPlantilla =  new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		
		String sentenciaSQL = "SELECT  plantilla, region FROM   REGIONESPORPLANTILLA ";

		if(!UtilObjeto.esNulo(entidad)) {
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigoPlantilla(), 0)) {
				sentenciaSQL = sentenciaSQL + " WHERE plantilla = ? ";
				whereColocado = true;
				parametros.add(entidad.getCodigoPlantilla());
			}
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigoRegion(), 0)) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " region = ? ";
				whereColocado = true;
				parametros.add(entidad.getCodigoRegion());
			}
			
		}
		
		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {
			
			for(int indice = 0; indice < parametros.size(); indice ++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}
			
			try (ResultSet resultados = sentenciaPreparada.executeQuery()) {

				while (resultados.next()) {
					regionesPorPlantilla.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta las regiones por plantilla");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos de las regiones por plantilla");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de las regiones por plantilla ");
		}

		return regionesPorPlantilla;
	}
	
	private RegionPorPlantilla ensamblar(ResultSet registro) {
		try {
			RegionPorPlantilla celdasPorRegionTemporal = RegionPorPlantilla.crear();

			celdasPorRegionTemporal.setCodigoPlantilla(registro.getInt("plantilla"));
			celdasPorRegionTemporal.setCodigoRegion(registro.getInt("region"));
			
			return celdasPorRegionTemporal;
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de la region por plantilla");
		}
	}

	@Override
	public void crear(RegionPorPlantilla objeto) {
		String sentenciaSQL = "INSERT INTO REGIONESPORPLANTILLA (plantilla, region) VALUES (?, ?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigoPlantilla());
			sentenciaPreparada.setInt(3, objeto.getCodigoRegion());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
				"se ha presentado un problema, tartando de registrar la informacion de la nueva region por plantilla");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion  de la nueva region por plantilla");
		}			
	}


	@Override
	public void actualizar(RegionPorPlantilla objeto) {
		// los codigos no se actualiuzan
		
	}


	@Override
	public void eliminar(RegionPorPlantilla objeto) {
		String sentenciaSQL = "DELETE  FROM REGIONESPORPLANTILLA WHERE plantilla = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigoPlantilla());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion de las regiones por plantilla");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion de las regiones por plantilla");
		}				
	}
	
}
