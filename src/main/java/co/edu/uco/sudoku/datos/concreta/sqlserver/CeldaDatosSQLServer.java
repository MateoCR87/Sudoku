package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.CeldaDatos;
import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.datos.entidad.PosicionEntidad;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;

public class CeldaDatosSQLServer implements CeldaDatos {

	private Connection conexion;
	
	 public CeldaDatosSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de celda para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}
	
	@Override
	public List<CeldaEntidad> consultar(CeldaEntidad entidad) {
		List<CeldaEntidad> celdas = new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		
		String sentenciaSQL = "SELECT  codigo ,numero, espista, posicion FROM   CELDA ";

		if(!UtilObjeto.esNulo(entidad)) {
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
				sentenciaSQL = sentenciaSQL + " WHERE codigo = ? ";
				whereColocado = true;
				parametros.add(entidad.getCodigo());
			}
			
			if(UtilNumerico.numeroEsMayor(entidad.getNumero(), 0)) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " numero = ? ";
				whereColocado = true;
				parametros.add(entidad.getNumero());
			}
			
			if(!entidad.isEsPista()) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " espista = ? ";
				whereColocado = true;
				parametros.add(entidad.isEsPista());
			}
			
			if(!UtilObjeto.esNulo(entidad.getPosicion())) {
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
					celdas.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta las celdas");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos de las celdas");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de las celdas");
		}

		return celdas;		
	}
	
	private CeldaEntidad ensamblar(ResultSet registro) {
		PosicionDatosSQLServer posicion = new PosicionDatosSQLServer(conexion);
		try {
			int codigoPosicion = registro.getInt("posicion");
			CeldaEntidad celdaTemporal = CeldaEntidad.crear();

			celdaTemporal.setCodigo(registro.getInt("codigo"));
			celdaTemporal.setNumero(registro.getInt("numero"));
			celdaTemporal.setEsPista(registro.getBoolean("espista"));
			celdaTemporal.setPosicion(
					posicion.consultar(PosicionEntidad.crear().setCodigo(codigoPosicion))
					.stream().filter(posiciones -> posiciones.getCodigo() ==codigoPosicion).findFirst().get());

			return celdaTemporal;
			
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de una celda");
		}
	}

	@Override
	public void crear(CeldaEntidad objeto) {
		String sentenciaSQL = "INSERT INTO CELDA (numero, espista, posicion) VALUES (?, ?, ?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getNumero());
			sentenciaPreparada.setBoolean(2, objeto.isEsPista());
			sentenciaPreparada.setInt(3, objeto.getPosicion().getCodigo());

			sentenciaPreparada.executeUpdate();
			
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
	public void actualizar(CeldaEntidad objeto) {
		String sentenciaSQL = "UPDATE CELDA SET numero = ?, espista = ?, posicion = ?  WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getNumero());
			sentenciaPreparada.setBoolean(2, objeto.isEsPista());
			sentenciaPreparada.setInt(3, objeto.getPosicion().getCodigo());
			sentenciaPreparada.setInt(4, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();
			
		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de modificar la informacion de la celda");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de modificar la informacion de la celdar");
		}
	}

	@Override
	public void eliminar(CeldaEntidad objeto) {
		String sentenciaSQL = "DELETE  FROM CELDA WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion de la celda");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion de la celda");
		}
	}

}
