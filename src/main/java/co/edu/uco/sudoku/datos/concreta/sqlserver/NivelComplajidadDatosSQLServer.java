package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.NivelComplejidadDatos;
import co.edu.uco.sudoku.datos.entidad.NivelComplejidadEntidad;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;
import co.edu.uco.sudoku.transversal.utilitario.UtilTexto;

public class NivelComplajidadDatosSQLServer implements NivelComplejidadDatos {
	
	private Connection conexion;
	
	 public NivelComplajidadDatosSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de nivel de complejidad para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}

	@Override
	public List<NivelComplejidadEntidad> consultar(NivelComplejidadEntidad entidad) {
		List<NivelComplejidadEntidad> nivelesComplejidad = new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		
		String sentenciaSQL = "SELECT codigo, nombre, descripcion, tiempolimite FROM   NIVELCOMPLEJIDAD ";

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
			
			if(!UtilTexto.cadenaEstaVacia(entidad.getDescripcion())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " descripcion = ? ";
				whereColocado = true;
				parametros.add(entidad.getDescripcion());
			}
			
			if(UtilNumerico.numeroEsMayor(entidad.getTiempoLimiteSegundos(), 0)) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " tiempolimite = ? ";
				whereColocado = true;
				parametros.add(entidad.getTiempoLimiteSegundos());
			}
		}
		
		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {
			
			for(int indice = 0; indice < parametros.size(); indice ++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}
			
			try (ResultSet resultados = sentenciaPreparada.executeQuery()) {

				while (resultados.next()) {
					nivelesComplejidad.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta del nivel de complejidad");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la  consultar los datos del nivel de complejidad");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion del nivel de complejidad");
		}

		return nivelesComplejidad;		
	}
	
	private NivelComplejidadEntidad ensamblar(ResultSet registro) {
		try {
			NivelComplejidadEntidad nivelComplejidadTemporal = NivelComplejidadEntidad.crear();
			
			nivelComplejidadTemporal.setCodigo(registro.getInt("codigo"));
			nivelComplejidadTemporal.setNombre(registro.getString("nombre"));
			nivelComplejidadTemporal.setDescripcion(registro.getString("descripcion"));
			nivelComplejidadTemporal.setTiempoLimiteSegundos(registro.getInt("tiempolimite"));
			
			return nivelComplejidadTemporal;
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos del nivel de complejidad");
		}
	}

	@Override
	public void crear(NivelComplejidadEntidad objeto) {
		String sentenciaSQL = "INSERT INTO NIVELCOMPLEJIDAD (nombre, descripcion, tiempolimite) VALUES (?, ?, ?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setString(1, objeto.getNombre());
			sentenciaPreparada.setString(2, objeto.getDescripcion());
			sentenciaPreparada.setInt(3, objeto.getTiempoLimiteSegundos());

			sentenciaPreparada.executeUpdate();
			
			try (ResultSet resultado = sentenciaPreparada.getGeneratedKeys()) {
				if (resultado.next()) {
					objeto.setCodigo(resultado.getInt(1));
				}
			}

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de registrar la informacion del nuevo jugador");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion del nuevo jugador");
		}
	}

	@Override
	public void actualizar(NivelComplejidadEntidad objeto) {
		String sentenciaSQL = "UPDATE NIVELCOMPLEJIDAD SET nombre = ?, descripcion = ?, tiempolimite = ?  WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setString(1, objeto.getNombre());
			sentenciaPreparada.setString(2, objeto.getDescripcion());
			sentenciaPreparada.setInt(3, objeto.getTiempoLimiteSegundos());
			sentenciaPreparada.setInt(4, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();
			
		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de modificar la informacion del nivel de complejidad");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de modificar la informacion del nivel de complejidad");
		}
	}

	@Override
	public void eliminar(NivelComplejidadEntidad objeto) {
		String sentenciaSQL = "DELETE  FROM NIVELCOMPLEJIDAD WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion del nivel de dificultad");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion del nivel de dificultad");
		}
	}

}
