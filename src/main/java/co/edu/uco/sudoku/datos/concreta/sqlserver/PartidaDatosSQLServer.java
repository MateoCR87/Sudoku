package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.PartidaDatos;
import co.edu.uco.sudoku.datos.entidad.JugadorEntidad;
import co.edu.uco.sudoku.datos.entidad.ModalidadJuegoEntidad;
import co.edu.uco.sudoku.datos.entidad.PartidaEntidad;
import co.edu.uco.sudoku.datos.entidad.PlantillaEntidad;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;

public class PartidaDatosSQLServer implements PartidaDatos {

	private Connection conexion;
	
	 public PartidaDatosSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de una partida para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}
	
	@Override
	public List<PartidaEntidad> consultar(PartidaEntidad entidad) {
		List<PartidaEntidad> partidas = new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		String sentenciaSQL = "SELECT codigo, jugador , plantilla , modalidadjuego , fechainicial, fechafinal , juegocompletado FROM   PARTIDA ";

		if(!UtilObjeto.esNulo(entidad)) {
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
				sentenciaSQL = sentenciaSQL + " WHERE codigo = ? ";
				whereColocado = true;
				parametros.add(entidad.getCodigo());
			}
			
			if(!UtilObjeto.esNulo(entidad.getJugador())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " jugador = ? ";
				whereColocado = true;
				parametros.add(entidad.getJugador().getCodigo());
			}
			
			if(!UtilObjeto.esNulo(entidad.getPlantilla())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " plantilla = ? ";
				whereColocado = true;
				parametros.add(entidad.getPlantilla().getCodigo());
			}
			
			if(!UtilObjeto.esNulo(entidad.getModalidad())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " modalidadjuego = ? ";
				whereColocado = true;
				parametros.add(entidad.getModalidad().getCodigo());
			}
			
			if(!UtilObjeto.esNulo(entidad.getFechaInicial())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " fechainicial = ? ";
				whereColocado = true;
				parametros.add(entidad.getFechaInicial());
			}
			
			if(!UtilObjeto.esNulo(entidad.getFechaFinal())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + "  fechafinal = ? ";
				whereColocado = true;
				parametros.add(entidad.getFechaFinal());
			}
			
			if(entidad.isJuegoCompletado()) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + "  juegocompletado = ? ";
				whereColocado = true;
				parametros.add(entidad.isJuegoCompletado());
			}
			
			
		}
		
		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {
			
			for(int indice = 0; indice < parametros.size(); indice ++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}
			
			try (ResultSet resultados = sentenciaPreparada.executeQuery()) {

				while (resultados.next()) {
					partidas.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta de  las partidas");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos de las partidas");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de las partidas");
		}

		return partidas;
	}
	
	private PartidaEntidad ensamblar(ResultSet registro) {
		JugadorDatosSQLServer jugador = new JugadorDatosSQLServer(conexion);
		PlantillaDatosSQLServer platilla = new  PlantillaDatosSQLServer(conexion);
		ModalidadJuegoDatosSQLServer modalidadJuego = new ModalidadJuegoDatosSQLServer(conexion);
		
		try {
			PartidaEntidad partidaTemporal = PartidaEntidad.crear();
			int codigoJugador = registro.getInt("jugador");
			int codigoPlantilla = registro.getInt("plantilla");
			int codigoModalidadJuego = registro.getInt("modalidadjuego");
			
			partidaTemporal.setCodigo(registro.getInt("codigo"));
			
			partidaTemporal.setJugador( jugador.consultar(JugadorEntidad.crear().setCodigo(codigoJugador))
					.stream().filter(jugadores -> jugadores.getCodigo() == codigoJugador).findFirst().get());
			
			partidaTemporal.setPlantilla(platilla.consultar(PlantillaEntidad.crear().setCodigo(codigoPlantilla))
					.stream().filter(plantillas -> plantillas.getCodigo() == codigoPlantilla).findFirst().get());
			
			partidaTemporal.setModalidad(modalidadJuego.consultar(ModalidadJuegoEntidad.crear().setCodigo(codigoModalidadJuego))
					.stream().filter(modalidades -> modalidades.getCodigo() == codigoModalidadJuego).findFirst().get());
			
			partidaTemporal.setFechaInicial(registro.getDate("fechainicial"));
			partidaTemporal.setFechaFinal(registro.getDate("fechafinal"));
			partidaTemporal.setJuegoCompletado(registro.getBoolean("juegocompletado"));

			return partidaTemporal;
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de un jugador");
		}
	}

	@Override
	public void crear(PartidaEntidad objeto) {
		String sentenciaSQL = "INSERT INTO PARTIDA (jugador , plantilla , modalidadjuego , fechainicial, fechafinal , juegocompletado) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getJugador().getCodigo());
			sentenciaPreparada.setInt(2, objeto.getPlantilla().getCodigo());
			sentenciaPreparada.setInt(3, objeto.getModalidad().getCodigo());
			sentenciaPreparada.setDate(4, (Date) objeto.getFechaInicial());
			sentenciaPreparada.setDate(5, (Date) objeto.getFechaFinal());
			sentenciaPreparada.setBoolean(6, objeto.isJuegoCompletado());

			sentenciaPreparada.executeUpdate();
			
			try (ResultSet resultado = sentenciaPreparada.getGeneratedKeys()) {
				if (resultado.next()) {
					objeto.setCodigo(resultado.getInt(1));
				}
			}

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de registrar la informacion de la nueva partida");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion de la nueva partida");
		}
	}

	@Override
	public void actualizar(PartidaEntidad objeto) {
		String sentenciaSQL = "UPDATE PARTIDA SET jugador = ?, plantilla = ?, modalidadjuego = ?, fechainicial = ?, fechafinal = ? , juegocompletado = ?  WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getJugador().getCodigo());
			sentenciaPreparada.setInt(2, objeto.getPlantilla().getCodigo());
			sentenciaPreparada.setInt(3, objeto.getModalidad().getCodigo());
			sentenciaPreparada.setDate(4, (Date) objeto.getFechaInicial());
			sentenciaPreparada.setDate(5, (Date) objeto.getFechaFinal());
			sentenciaPreparada.setBoolean(6, objeto.isJuegoCompletado());
			sentenciaPreparada.setInt(7, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();
			
		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de modificar la informacion de la partida");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de modificar la informacion de la partida");
		}
	}

	@Override
	public void eliminar(PartidaEntidad objeto) {
		String sentenciaSQL = "DELETE  FROM PARTIDA WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion de una partida");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion de una partida");
		}
	}

}
