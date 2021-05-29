package co.edu.uco.sudoku.datos.concreta.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.sudoku.datos.PlantillaDatos;
import co.edu.uco.sudoku.datos.entidad.NivelComplejidadEntidad;
import co.edu.uco.sudoku.datos.entidad.PlantillaEntidad;
import co.edu.uco.sudoku.transversal.exepcion.SudokuDatosExepcion;
import co.edu.uco.sudoku.transversal.utilitario.UtilNumerico;
import co.edu.uco.sudoku.transversal.utilitario.UtilObjeto;
import co.edu.uco.sudoku.transversal.utilitario.UtilSQL;

public class PlantillaDatosSQLServer implements PlantillaDatos {

	private Connection conexion;
	
	 public PlantillaDatosSQLServer(Connection conexion) {
		if(!UtilSQL.conexionEstaAbierta(conexion)) {
			throw new SudokuDatosExepcion("No es posible crear un acceso a datos de una plantilla para SQL Server con una coneccion cerrada ");
		}
		this.conexion = conexion;
	}
	
	@Override
	public List<PlantillaEntidad> consultar(PlantillaEntidad entidad) {
		List<PlantillaEntidad> plantillas = new ArrayList<>();
		boolean whereColocado = false;
		List<Object> parametros = new ArrayList<>();
		
		
		String sentenciaSQL = "SELECT codigo, nivelcomplejidad FROM   PLANTILLA ";

		if(!UtilObjeto.esNulo(entidad)) {
			
			if(UtilNumerico.numeroEsMayor(entidad.getCodigo(), 0)) {
				sentenciaSQL = sentenciaSQL + " WHERE codigo = ? ";
				whereColocado = true;
				parametros.add(entidad.getCodigo());
			}
			
			if(!UtilObjeto.esNulo(entidad.getNivelComplejidad())) {
				sentenciaSQL = sentenciaSQL + ((whereColocado) ? " AND " : " WHERE ");
				sentenciaSQL = sentenciaSQL + " nivelcomplejidad = ? ";
				whereColocado = true;
				parametros.add(entidad.getNivelComplejidad().getCodigo());
			}
			
		}
		
		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {
			
			for(int indice = 0; indice < parametros.size(); indice ++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}
			
			try (ResultSet resultados = sentenciaPreparada.executeQuery()) {

				while (resultados.next()) {
					plantillas.add(ensamblar(resultados));
				}
			} catch (SQLException excepcion) {
				throw new SudokuDatosExepcion(
						"se ha presentado un error, tratando de recuperar los datos de la consulta de  las plantillas");
			}

		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un error, preparando la de consultar los datos de las plantillas");
		} catch (SudokuDatosExepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado problemas inesperados, consultando la informacion de las plantillas");
		}

		return plantillas;
	}
	
	private PlantillaEntidad ensamblar(ResultSet registro) {
		NivelComplajidadDatosSQLServer nivelComplajidad = new NivelComplajidadDatosSQLServer(conexion);
		try {
			PlantillaEntidad plantillaTemporal = PlantillaEntidad.crear();
			int codigoNivelComplejidad = registro.getInt("nivelcomplejidad");

			plantillaTemporal.setCodigo(registro.getInt("codigo"));
			plantillaTemporal.setNivelComplejidad(nivelComplajidad.consultar(	NivelComplejidadEntidad.crear().setCodigo(codigoNivelComplejidad))
					.stream().filter(complejidad -> complejidad.getCodigo() ==codigoNivelComplejidad).findFirst().get() );
			

			return plantillaTemporal;
		} catch (SQLException excepcion) {
			throw new SudokuDatosExepcion("se ha presentado un problema tratando de recuperar los datos de una plantilla");
		}
	}

	@Override
	public void crear(PlantillaEntidad objeto) {
		String sentenciaSQL = "INSERT INTO PLANTILLA (nivelcomplejidad) VALUES (?)";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getNivelComplejidad().getCodigo());

			sentenciaPreparada.executeUpdate();
			
			try (ResultSet resultado = sentenciaPreparada.getGeneratedKeys()) {
				if (resultado.next()) {
					objeto.setCodigo(resultado.getInt(1));
				}
			}

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de registrar la informacion de la neva plantilla");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de registrar la informacion de la nueva paltilla");
		}
	}

	@Override
	public void actualizar(PlantillaEntidad objeto) {
		String sentenciaSQL = "UPDATE PLANTILLA SET nivelcomplejidad = ?  WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getNivelComplejidad().getCodigo());
			sentenciaPreparada.setInt(2, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();
			
		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de modificar la informacion de la plantilla");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de modificar la informacion de la plantilla");
		}
	}

	@Override
	public void eliminar(PlantillaEntidad objeto) {
		String sentenciaSQL = "DELETE  FROM PLANTILLA WHERE codigo = ? ";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL)) {

			sentenciaPreparada.setInt(1, objeto.getCodigo());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema, tartando de eliminar la informacion de la plantilla");
		} catch (Exception exepcion) {
			throw new SudokuDatosExepcion(
					"se ha presentado un problema inesperado, tartando de eliminar la informacion de la plantilla");
		}
	}

}
