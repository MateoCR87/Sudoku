package co.edu.uco.sudoku.datos.api.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.uco.sudoku.datos.api.controlador.respuesta.EstadoRespuestaEnum;
import co.edu.uco.sudoku.datos.api.controlador.respuesta.Respuesta;
import co.edu.uco.sudoku.datos.concreta.sqlserver.CeldasPorRegionSQLServer;
import co.edu.uco.sudoku.dto.JugadorDTO;
import co.edu.uco.sudoku.dto.RegionDTO;
import co.edu.uco.sudoku.negocio.fachada.JugadorFachada;
import co.edu.uco.sudoku.negocio.fachada.concreta.JugadorFachadaImpl;
import co.edu.uco.sudoku.relacion.muchos.muchos.CeldaPorRegion;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;

public class CeldasPorRegionControlador {
	
	public void  crear (RegionDTO region) {
		
		try {
			
			for(int fila = 0; fila<3 ; fila++) {
				for(int columna = 0; columna<3 ; columna++) {
					CeldasPorRegionSQLServer sql = new CeldasPorRegionSQLServer(null);
					sql.crear(CeldaPorRegion.crear(region.getCeldas()[fila][columna].getCodigo(), region.getCodigo()));
				}
			}
		} catch (SudokuExepcion exception) {

			exception.printStackTrace();
		} catch(Exception exception) {

			exception.printStackTrace();
		}
		
	}
	
	@PutMapping("/{codigo}")
	public  ResponseEntity<Respuesta<JugadorDTO>> modificar (@RequestBody JugadorDTO jugadorDTO, @PathVariable int codigo) {
		ResponseEntity<Respuesta<JugadorDTO>> entidadRespuesta;
		Respuesta<JugadorDTO> respuesta = new Respuesta<>();
		
		try {	
			JugadorFachada jugadorFachada = new  JugadorFachadaImpl();
			jugadorFachada.actualizar(jugadorDTO.setCodigo(codigo));
			
			respuesta.adicionaMensage("El jugador se actualizo sin problemas");
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de modificar la informacion del  jugador");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta;
	}
	
	@DeleteMapping("/{codigo}")
	public  ResponseEntity<Respuesta<JugadorDTO>> delete (@PathVariable int codigo) {
		ResponseEntity<Respuesta<JugadorDTO>> entidadRespuesta;
		Respuesta<JugadorDTO> respuesta = new Respuesta<>();
		
		try {
			JugadorFachada jugadorFachada = new  JugadorFachadaImpl();
			jugadorFachada.eliminar(JugadorDTO.crear().setCodigo(codigo));
			
			respuesta.adicionaMensage("El jugador se elimino sin problemas");
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de eliminar la informacion del  jugador");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta;
	}
	
	@GetMapping
	public ResponseEntity<Respuesta<JugadorDTO>> consultar () {
		ResponseEntity<Respuesta<JugadorDTO>> entidadRespuesta;
		Respuesta<JugadorDTO> respuesta = new Respuesta<>();
		
		try {
			JugadorFachada jugadorFachada = new  JugadorFachadaImpl();
			List<JugadorDTO> jugadores = jugadorFachada.consultar(JugadorDTO.crear());
			respuesta.setDatos(jugadores);
			
			respuesta.adicionaMensage("Los jugadores se consultaron de forma exitosa" );
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de consultar la informacion de los jugadores");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta ;
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Respuesta<JugadorDTO>> consultar (@PathVariable int codigo) {
		ResponseEntity<Respuesta<JugadorDTO>> entidadRespuesta;
		Respuesta<JugadorDTO> respuesta = new Respuesta<>();
		
		try {
			JugadorFachada jugadorFachada = new  JugadorFachadaImpl();
			List<JugadorDTO> jugadores = jugadorFachada.consultar(JugadorDTO.crear().setCodigo(codigo));
			respuesta.setDatos(jugadores);
			
			if(respuesta.getDatos().isEmpty()) {
				respuesta.adicionaMensage("No existe un jugador con el codigo" + codigo);
			} else {
				respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			}
			
			respuesta.adicionaMensage("el jugador se consulto de forma exitosa");
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de consultar la informacion de los jugadores");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta ;
	}
}
