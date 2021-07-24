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
import co.edu.uco.sudoku.dto.SudokuDTO;
import co.edu.uco.sudoku.negocio.fachada.RegionPorSudokuFachada;
import co.edu.uco.sudoku.negocio.fachada.SudokuFachada;
import co.edu.uco.sudoku.negocio.fachada.concreta.RegionPorSudokuFachadaImpl;
import co.edu.uco.sudoku.negocio.fachada.concreta.SudokuFachadaImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;

@RestController
@RequestMapping("/api/v1/sudoku")
public class SudokuControlador {
	
	@PostMapping
	public ResponseEntity<Respuesta<SudokuDTO>>  crear (@RequestBody SudokuDTO sudoku) {
		
		ResponseEntity<Respuesta<SudokuDTO>> entidadRespuesta;
		Respuesta<SudokuDTO> respuesta = new Respuesta<>();
		
		try {
			SudokuFachada sudokuFachada = new  SudokuFachadaImpl();
			sudokuFachada.registrar(sudoku);
			
			respuesta.adicionaMensage("El sudoku se creó sin problemas");
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de registrar la informacion de un nuevo sudoku");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta;
	}
	
	@DeleteMapping("/{codigo}")
	public  ResponseEntity<Respuesta<SudokuDTO>> delete (@PathVariable int codigo) {
		ResponseEntity<Respuesta<SudokuDTO>> entidadRespuesta;
		Respuesta<SudokuDTO> respuesta = new Respuesta<>();
		
		try {
			SudokuFachada sudokuFachada = new  SudokuFachadaImpl();
			sudokuFachada.eliminar(codigo);
			
			respuesta.adicionaMensage("El sudoku se elimino sin problemas");
			respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
		} catch (SudokuExepcion exception) {
			respuesta.adicionaMensage(exception.getMessage());
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		} catch(Exception exception) {
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de eliminar la informacion del sudoku");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta;
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Respuesta<SudokuDTO>> consultar (@PathVariable int codigo) {
		ResponseEntity<Respuesta<SudokuDTO>> entidadRespuesta;
		Respuesta<SudokuDTO> respuesta = new Respuesta<>();
		
		try {
			SudokuFachada sudokuFachada = new  SudokuFachadaImpl();
			RegionPorSudokuFachada regionesFachada = new RegionPorSudokuFachadaImpl();
			List<SudokuDTO> sudokus = sudokuFachada.consultar(regionesFachada.consultar(codigo));
			respuesta.setDatos(sudokus);
			
			if(respuesta.getDatos().isEmpty()) {
				respuesta.adicionaMensage("No existe un sudoku con el codigo" + codigo);
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
			respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de consultar la informacion del sudoku");
			respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);
			
			entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

			exception.printStackTrace();
		}
		
		return entidadRespuesta ;
	}
}
