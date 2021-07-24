package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.RegionDatos;
import co.edu.uco.sudoku.datos.RegionPorSudokuDatos;
import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.relacion.muchos.muchos.RegionPorSudoku;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuRelacionArchivoExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;

public class RegionPorSudokuSQLServer implements RegionPorSudokuDatos {

	private Connection conexion;
	
	 public RegionPorSudokuSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de  las regiones por sudoku para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}


	@Override
	public RegionEntidad[][] matrizRegiones(int codigoSudoku) {
		RegionDatos region = SudokuDatosFactoria.obtenerFactoria().obtenerRegionDatos();

		RegionEntidad[][] matrizRegiones = new RegionEntidad[3][3];

		for (RegionPorSudoku regionPorPlantilla : consultar(RegionPorSudoku.crear().setCodigoSudoku(codigoSudoku))) {

			List<RegionEntidad> listaCeldas = region
					.consultar(RegionEntidad.crear().setCodigo(regionPorPlantilla.getCodigoRegion()));
			RegionEntidad regionEntidad = listaCeldas.stream()
					.filter(posicion -> posicion.getCodigo() == regionPorPlantilla.getCodigoRegion()).findFirst()
					.orElse(RegionEntidad.crear());

			if (UtilObjeto.esNulo(regionEntidad)) {
				throw new SudokuRelacionArchivoExepcion("Se perden datos haciendo la matriz de las regiones del sudoku.");

			}

			matrizRegiones[regionEntidad.getPosicion().getFila() - 1][regionEntidad.getPosicion().getColumna()
					- 1] = regionEntidad;
		}
		return matrizRegiones;
	}


	@Override
	public List<RegionPorSudoku> consultar(RegionPorSudoku entidad) {
		List<RegionPorSudoku> regionesPorSudoku =  new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		String sentenciaSQL = "SELECT sudoku, region FROM   REGIONESPORSUDOKU ";

		if(!UtilObjeto.esNulo(entidad)) {
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigoSudoku(), 0)) {
				sentenciaSQL = sentenciaSQL + " WHERE sudoku = ? ";
				whereColocado = true;
				parametros.add(entidad.getCodigoSudoku());
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
					regionesPorSudoku.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta las regiones por sudoku" + excepcion.getMessage());
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos de las regiones por sudoku");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de las regiones por sudoku ");
		}

		return regionesPorSudoku;
	}

	private RegionPorSudoku ensamblar(ResultSet registro) {
		try {
			RegionPorSudoku celdasPorRegionTemporal = RegionPorSudoku.crear();

			celdasPorRegionTemporal.setCodigoSudoku(registro.getInt("sudoku"));
			celdasPorRegionTemporal.setCodigoRegion(registro.getInt("region"));
			
			return celdasPorRegionTemporal;
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de la region por sudoku");
		}
	}
	
	@Override
	public void crear(RegionPorSudoku objeto) {
		String sentenciaSQL = "INSERT INTO REGIONESPORSUDOKU (sudoku, region) VALUES (?, ?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigoSudoku());
			sentenciaPreparada.setInt(3, objeto.getCodigoRegion());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
				"se ha presentado un problema, tartando de registrar la informacion de la nueva region por Sudoku");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion  de la nueva region por sudoku");
		}			
	}


	@Override
	public void actualizar(RegionPorSudoku objeto) {
		//no se actualixan los codigos		
	}


	@Override
	public void eliminar(RegionPorSudoku objeto) {
		String sentenciaSQL = "DELETE  FROM REGIONESPORSUDOKU WHERE plantilla = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigoSudoku());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion de las regiones por Sudoku");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion de las regiones por Sudoku");
		}		
	}
	
	
}
