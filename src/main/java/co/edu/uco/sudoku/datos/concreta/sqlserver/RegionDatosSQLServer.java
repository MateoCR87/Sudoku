package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.RegionDatos;
import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;

public class RegionDatosSQLServer implements RegionDatos {

	private Connection conexion;
	
	 public RegionDatosSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de una region  para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}
	
	@Override
	public List<RegionEntidad> consultar(RegionEntidad entidad) {
		List<RegionEntidad> regiones = new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		
		String sentenciaSQL = "SELECT  codigo , posicion FROM   REGION ";

		if(!UtilObjeto.esNulo(entidad)) {
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
				sentenciaSQL = sentenciaSQL + " WHERE codigo = ? ";
				whereColocado = true;
				parametros.add(entidad.getCodigo());
			}
			
			if(!UtilObjeto.esNulo(entidad.getPosicion()) && UtilNumerico.numeroEsMayor(entidad.getPosicion().getCodigo(), 0)) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " posicion = ? ";
				whereColocado = true;
				parametros.add(entidad.getPosicion().getCodigo());
			}
		}
		
		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {
			
			for(int indice = 0; indice < parametros.size(); indice ++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}
			
			try (ResultSet resultados = sentenciaPreparada.executeQuery()) {

				while (resultados.next()) {
					regiones.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consultar al region");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos de la region");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de la region" + excepcion.getMessage());
		}

		return regiones;	
	}
	
	private RegionEntidad ensamblar(ResultSet registro) {
		PosicionDatosSQLServer posicion = new PosicionDatosSQLServer(conexion);
		CeldasPorRegionSQLServer celdas= new CeldasPorRegionSQLServer(conexion);
		try {
			int codigoPosicion = registro.getInt("posicion");
			RegionEntidad regionTemporal = RegionEntidad.crear();

			regionTemporal.setCodigo(registro.getInt("codigo"));
			regionTemporal.setCeldas(celdas.matrizCeldas(registro.getInt("codigo")));
			regionTemporal.setPosicion(
					posicion.consultar(PosicionEntidad.crear().setCodigo(codigoPosicion))
					.stream().filter(posiciones -> posiciones.getCodigo() ==codigoPosicion).findFirst().get());

			return regionTemporal;
			
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de una region");
		}
	}

	@Override
	public void crear(RegionEntidad objeto) {
		String sentenciaSQL = "INSERT INTO REGION (posicion) VALUES (?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getPosicion().getCodigo());

			sentenciaPreparada.executeUpdate();
			
			try (ResultSet resultado = sentenciaPreparada.getGeneratedKeys()) {
				if (resultado.next()) {
					objeto.setCodigo(resultado.getInt(1));
				}
			}

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de registrar la informacion de la nueva region");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion  de la nueva region");
		}
	}

	@Override
	public void actualizar(RegionEntidad objeto) {
		String sentenciaSQL = "UPDATE REGION SET posicion = ?  WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getPosicion().getCodigo());
			sentenciaPreparada.setInt(2, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();
			
		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de modificar la informacion de la region");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de modificar la informacion de la region");
		}
	}

	@Override
	public void eliminar(RegionEntidad objeto) {
		String sentenciaSQL = "DELETE  FROM REGION WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion de la region");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion de la region");
		}
	}

}
