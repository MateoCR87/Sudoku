package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.SudokuDatos;
import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;

public class SudokuDatosSQLServer implements SudokuDatos {

	private Connection conexion;
	
	 public SudokuDatosSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de un sudoku  para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}
	
	@Override
	public List<SudokuEntidad> consultar(SudokuEntidad entidad) {
		List<SudokuEntidad> sudokus = new ArrayList<>();
		List<Object> parametros = new ArrayList<>();
		
		
		String sentenciaSQL = "SELECT  codigo  FROM   SUDOKU ";

		if(!UtilObjeto.esNulo(entidad)) {
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
				sentenciaSQL = sentenciaSQL + " WHERE codigo = ? ";
				parametros.add(entidad.getCodigo());
			}
			
		}
		
		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {
			
			for(int indice = 0; indice < parametros.size(); indice ++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}
			
			try (ResultSet resultados = sentenciaPreparada.executeQuery()) {

				while (resultados.next()) {
					sudokus.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta del sudoku");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos del sudoku");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de un sudoku");
		}

		return sudokus;	
	}

	private SudokuEntidad ensamblar(ResultSet registro) {
		RegionPorSudokuSQLServer regiones = new RegionPorSudokuSQLServer(conexion);
		try {
			SudokuEntidad sudokuTemporal = SudokuEntidad.crear();

			sudokuTemporal.setCodigo(registro.getInt("codigo"));
			sudokuTemporal.setRegiones(regiones.matrizRegiones(registro.getInt("codigo")));

			return sudokuTemporal;
			
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de un sudoku");
		}
	}
	
	@Override
	public void crear(SudokuEntidad objeto) {
		String sentenciaSQL = "INSERT SUDOKU DEFAULT VALUES ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {
			
			try (ResultSet resultado = sentenciaPreparada.getGeneratedKeys()) {
				if (resultado.next()) {
					objeto.setCodigo(resultado.getInt(1));
				}
			}

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de registrar la informacion de la nueva celda");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion  de la nueva celda");
		}
	}

	@Override
	public void actualizar(SudokuEntidad objeto) {
		//  los codigos no se actualizan 
	}

	@Override
	public void eliminar(SudokuEntidad objeto) {
		String sentenciaSQL = "DELETE  FROM SUDOKU WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion del sudoku");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion del sudoku");
		}
	}

}
