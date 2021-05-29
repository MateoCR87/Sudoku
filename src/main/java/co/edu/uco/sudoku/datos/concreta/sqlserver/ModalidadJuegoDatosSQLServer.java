package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.ModalidadJuegoDatos;
import co.edu.uco.sudoku.datos.entidad.ModalidadJuegoEntidad;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class ModalidadJuegoDatosSQLServer implements ModalidadJuegoDatos {

	private Connection conexion;
	
	 public ModalidadJuegoDatosSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de modalidad de juego para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}
	
	@Override
	public List<ModalidadJuegoEntidad> consultar(ModalidadJuegoEntidad entidad) {
		List<ModalidadJuegoEntidad> modalidades = new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		String sentenciaSQL = "SELECT codigo, nombre FROM   MODALIDADJUEGO ";

		if(!UtilObjeto.esNulo(entidad)) {
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
				sentenciaSQL = sentenciaSQL + " WHERE codigo = ? ";
				whereColocado = true;
				parametros.add(entidad.getCodigo());
			}
			
			if(!UtilTexto.cadenaEstaVacia(entidad.getNombre())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " nombre = ? ";
				whereColocado = true;
				parametros.add(entidad.getNombre());
			}
		}
		
		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {
			
			for(int indice = 0; indice < parametros.size(); indice ++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}
			
			try (ResultSet resultados = sentenciaPreparada.executeQuery()) {

				while (resultados.next()) {
					modalidades.add(ensamblar(resultados));
				}
				
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta de la modalidad de juego");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos de la modalidad de juego");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de la modalidad de juego");
		}

		return modalidades;
	}
	
	private ModalidadJuegoEntidad ensamblar(ResultSet registro) {
		try {
			ModalidadJuegoEntidad modalidadTemporal = ModalidadJuegoEntidad.crear();
			
			modalidadTemporal.setCodigo(registro.getInt("codigo"));
			modalidadTemporal.setNombre(registro.getString("nombre"));
			
			return modalidadTemporal;
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de la modalidad de juego");
		}
	}

	@Override
	public void crear(ModalidadJuegoEntidad objeto) {
		String sentenciaSQL = "INSERT INTO MODALIDADJUEGO (nombre) VALUES (?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setString(1, objeto.getNombre());

			sentenciaPreparada.executeUpdate();
			
			try (ResultSet resultado = sentenciaPreparada.getGeneratedKeys()) {
				if (resultado.next()) {
					objeto.setCodigo(resultado.getInt(1));
				}
			}

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de registrar la informacion de la nueva modalidad de juego");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion de la nueva modalidad de juego");
		}
	}

	@Override
	public void actualizar(ModalidadJuegoEntidad objeto) {
		String sentenciaSQL = "UPDATE MODALIDADJUEGO SET nombre = ?  WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setString(1, objeto.getNombre());
			sentenciaPreparada.setInt(2, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();
			
		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de modificar la informacion de la modalidad de juego");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de modificar la informacion de la modalidad de juego");
		}
	}

	@Override
	public void eliminar(ModalidadJuegoEntidad objeto) {
		String sentenciaSQL = "DELETE  FROM MODALIDADJUEGO WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion de la modalidad de juego");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion de la modalidad de juegor");
		}
	}

}
