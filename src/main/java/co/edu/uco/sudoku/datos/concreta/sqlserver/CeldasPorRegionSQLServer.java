package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.CeldaDatos;
import co.edu.uco.sudoku.datos.CeldaPorRegionDatos;
import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.relacion.muchos.muchos.CeldaPorRegion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuRelacionArchivoExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;

public class CeldasPorRegionSQLServer implements CeldaPorRegionDatos {

	private Connection conexion;
	
	 public CeldasPorRegionSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de  las celdas por region para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}

	@Override
	public CeldaEntidad[][] matrizCeldas(int codigoRegion) {

		CeldaDatos celda = SudokuDatosFactoria.obtenerFactoria().obtenerCeldaDatos();
		CeldaEntidad[][] matrizCeldas = new CeldaEntidad[3][3];

		for (CeldaPorRegion celdaPorRegion : consultar(CeldaPorRegion.crear().setCodigoRegion(codigoRegion))) {

			List<CeldaEntidad> listaCeldas = celda
					.consultar(CeldaEntidad.crear().setCodigo(celdaPorRegion.getCodigoCelda()));
			CeldaEntidad celdaEntidad = listaCeldas.stream()
					.filter(posicion -> posicion.getCodigo() == celdaPorRegion.getCodigoCelda()).findFirst()
					.orElse(CeldaEntidad.crear());

			if (UtilObjeto.esNulo(celdaEntidad)) {
				throw new SudokuRelacionArchivoExepcion("Se pierden datos haciendo la matriz de las celdas en celdas por region.");

			}

			matrizCeldas[celdaEntidad.getPosicion().getFila() - 1][celdaEntidad.getPosicion().getColumna()- 1] = celdaEntidad;
		}
		return matrizCeldas;
		
		
	}

	@Override
	public List<CeldaPorRegion> consultar(CeldaPorRegion entidad) {
		List<CeldaPorRegion> celdasPorRegions =  new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		
		String sentenciaSQL = "SELECT  celda, region FROM   CELDASPORREGION ";

		if(!UtilObjeto.esNulo(entidad)) {
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigoCelda(), 0)) {
				sentenciaSQL = sentenciaSQL + " WHERE celda = ? ";
				whereColocado = true;
				parametros.add(entidad.getCodigoCelda());
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
					celdasPorRegions.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta las celdas por region");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos de las celdas por region");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de las celdas por region ");
		}

		return celdasPorRegions;
	}

	private CeldaPorRegion ensamblar(ResultSet registro) {
		try {
			CeldaPorRegion celdasPorRegionTemporal = CeldaPorRegion.crear();

			celdasPorRegionTemporal.setCodigoCelda(registro.getInt("celda"));
			celdasPorRegionTemporal.setCodigoRegion(registro.getInt("region"));
			
			return celdasPorRegionTemporal;
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de la celda por region");
		}
	}
	
	@Override
	public void crear(CeldaPorRegion objeto) {
		String sentenciaSQL = "INSERT INTO CELDASPORREGION (celda, region) VALUES (?, ?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigoCelda());
			sentenciaPreparada.setInt(2, objeto.getCodigoRegion());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de registrar la informacion de la nueva celda por region");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion  de la nueva celda por region");
		}			
	}

	@Override
	public void actualizar(CeldaPorRegion objeto) {
		//  los codigos no se actualizan 
	}

	@Override
	public void eliminar(CeldaPorRegion objeto) {
		String sentenciaSQL = "DELETE  FROM CELDASPORREGION WHERE region = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigoRegion());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion de las celdas por region");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion de las celdas por region");
		}		
	}

}
