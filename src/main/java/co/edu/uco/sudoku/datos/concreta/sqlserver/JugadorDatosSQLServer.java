package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.JugadorDatos;
import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class JugadorDatosSQLServer implements JugadorDatos {

	private Connection conexion;

	public JugadorDatosSQLServer(Connection conexion) {
		if (!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion(
					"No es posible crear un acceso a datos de jugadores para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}

	@Override
	public List<JugadorEntidad> consultar(JugadorEntidad entidad)  {
		List<JugadorEntidad> jugadores = new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		
		String sentenciaSQL = "SELECT codigo ,nombre ,documentoIdentificacion , correo  , clave  FROM  Jugador ";

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
			
			if(UtilNumerico.numeroEsMayor(entidad.getDocumentoIdentificacion(), 0)) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " documentoIdentificacion = ? ";
				whereColocado = true;
				parametros.add(entidad.getDocumentoIdentificacion());
			}
			
			if(!UtilTexto.cadenaEstaVacia(entidad.getCorreo())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " correo = ? ";
				whereColocado = true;
				parametros.add(entidad.getCorreo());
			}
			
			if(!UtilTexto.cadenaEstaVacia(entidad.getClave())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : "  WHERE ");
				sentenciaSQL = sentenciaSQL + " clave = ? ";
				whereColocado = true;
				parametros.add(entidad.getClave());
			}
		}
		
		
		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {
			
			for(int indice = 0; indice < parametros.size(); indice ++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}
			try (ResultSet resultados = sentenciaPreparada.executeQuery()) {
				while (resultados.next()) {	
					jugadores.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta de los jugadores");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos de los jugadores");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de jugadore");
		}

		return jugadores;
	}

	private JugadorEntidad ensamblar(ResultSet registro) {
		try {
			JugadorEntidad jugadorTemporal = JugadorEntidad.crear();

			jugadorTemporal.setCodigo(registro.getInt("codigo"));
			jugadorTemporal.setNombre(registro.getString("nombre"));
			jugadorTemporal.setDocumentoIdentificacion(registro.getInt("documentoIdentificacion"));
			jugadorTemporal.setCorreo(registro.getString("correo"));
			return jugadorTemporal;
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de un jugador");
		}
	}

	@Override
	public void crear(JugadorEntidad objeto) {
		String sentenciaSQL = "INSERT INTO JUGADOR (nombre, documentoIdentificacion, correo, clave) VALUES (?, ?, ?, ?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setString(1, objeto.getNombre());
			sentenciaPreparada.setInt(2, objeto.getDocumentoIdentificacion());
			sentenciaPreparada.setString(3, objeto.getCorreo());
			sentenciaPreparada.setString(4, objeto.getClave());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de registrar la informacion del nuevo jugador");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion del nuevo jugador");
		}
	}

	@Override
	public void actualizar(JugadorEntidad objeto) {
		String sentenciaSQL = "UPDATE JUGADOR SET nombre = ?, documentoIdentificacion = ?, correo = ?, clave = ?  WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setString(1, objeto.getNombre());
			sentenciaPreparada.setInt(2, objeto.getDocumentoIdentificacion());
			sentenciaPreparada.setString(3, objeto.getCorreo());
			sentenciaPreparada.setString(4, objeto.getClave());
			sentenciaPreparada.setInt(5, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();
			
		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de modificar la informacion del jugador");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de modificar la informacion del  jugador");
		}

	}

	@Override
	public void eliminar(JugadorEntidad objeto) {
		String sentenciaSQL = "DELETE  FROM JUGADOR WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion del jugador" + exepcion.getMessage());
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion del jugador");
		}

	}

}
