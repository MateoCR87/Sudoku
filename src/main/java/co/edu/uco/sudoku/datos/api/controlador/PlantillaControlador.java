package co.edu.uco.sudoku.datos.api.controlador;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uco.sudoku.datos.api.controlador.respuesta.EstadoRespuestaEnum;
import co.edu.uco.sudoku.datos.api.controlador.respuesta.Respuesta;
import co.edu.uco.sudoku.dto.PlantillaDTO;
import co.edu.uco.sudoku.negocio.fachada.PlantillaFachada;
import co.edu.uco.sudoku.negocio.fachada.concreta.PlantillaFachadaImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;

@RestController
@RequestMapping("/api/v1/plantilla")
public class PlantillaControlador {
	
	@PostMapping
	public ResponseEntity<Respuesta<PlantillaDTO>>  crear (@RequestBody PlantillaDTO plantilla) {
		
		ResponseEntity<Respuesta<PlantillaDTO>> entidadRespuesta;
		Respuesta<PlantillaDTO> respuesta = new Respuesta<>();
		
		try {
			PlantillaFachada plantillaFachada = new  PlantillaFachadaImpl();
			plantillaFachada.registrar(plantilla);
			
			respuesta.adicionaMensage("La plantilla se creó sin problemas");
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de registrar la informacion de la nueva plantilla");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta;
	}
	
	@DeleteMapping("/{codigo}")
	public  ResponseEntity<Respuesta<PlantillaDTO>> delete (@PathVariable int codigo) {
		ResponseEntity<Respuesta<PlantillaDTO>> entidadRespuesta;
		Respuesta<PlantillaDTO> respuesta = new Respuesta<>();
		
		try {
			PlantillaFachada plantillaFachada = new  PlantillaFachadaImpl();
			plantillaFachada.eliminar(codigo);
			
			respuesta.adicionaMensage("La plantilla se elimino sin problemas");
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de eliminar la informacion de la plantilla");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta;
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Respuesta<PlantillaDTO>> consultar (@PathVariable int codigo) {
		ResponseEntity<Respuesta<PlantillaDTO>> entidadRespuesta;
		Respuesta<PlantillaDTO> respuesta = new Respuesta<>();
		
		try {
			PlantillaFachada plantillaFachada = new  PlantillaFachadaImpl();
			List<PlantillaDTO> plantillas = plantillaFachada.consultar(PlantillaDTO.crear().setCodigo(codigo));
			respuesta.setDatos(plantillas);
			
			if(respuesta.getDatos().isEmpty()) {
				respuesta.adicionaMensage("No existe una plantilla con el codigo" + codigo);
			} else {
				respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			}
			
			respuesta.adicionaMensage("La plantilla se consulto de forma exitosa");
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de consultar la informacion de la plantilla");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta ;
	}

}
