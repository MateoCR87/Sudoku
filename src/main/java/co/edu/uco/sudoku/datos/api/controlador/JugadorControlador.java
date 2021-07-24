package co.edu.uco.sudoku.datos.api.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.sudoku.datos.api.controlador.respuesta.EstadoRespuestaEnum;
import co.edu.uco.sudoku.datos.api.controlador.respuesta.Respuesta;
import co.edu.uco.sudoku.dto.JugadorDTO;
import co.edu.uco.sudoku.negocio.fachada.JugadorFachada;
import co.edu.uco.sudoku.negocio.fachada.concreta.JugadorFachadaImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;

@RestController
@RequestMapping("/api/v1/jugador")
@CrossOrigin("*")
public class JugadorControlador {
	
	
	
	@PostMapping
	public ResponseEntity<Respuesta<JugadorDTO>>  crear (@RequestBody JugadorDTO jugador) {
		
		ResponseEntity<Respuesta<JugadorDTO>> entidadRespuesta;
		Respuesta<JugadorDTO> respuesta = new Respuesta<>();
		
		try {
			JugadorFachada jugadorFachada = new  JugadorFachadaImpl();
			jugadorFachada.registrar(jugador);
			
			respuesta.adicionaMensage("El jugador se creo sin problemas");
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de registrar la informacion del nuevo jugador");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta;
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
	public List<JugadorDTO> consultar () {
		List<JugadorDTO> respuesta = new ArrayList<>();
		
		try {
			JugadorFachada jugadorFachada = new  JugadorFachadaImpl();
			respuesta = jugadorFachada.consultar(JugadorDTO.crear());
		} catch (SudokuExepcion exception) {

			exception.printStackTrace();
		} catch(Exception exception) {

			exception.printStackTrace();
		}
		
		return respuesta ;
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
