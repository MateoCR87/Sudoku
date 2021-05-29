package co.edu.uco.sudoku.datos.api.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.sudoku.dto.CeldaDTO;

@RestController
@RequestMapping("/api/v1/celda")
public class CeldaControlador {
	
	@GetMapping("ejemplo")
	public CeldaDTO obtenerJugadorEjemplo() {
		return CeldaDTO.crear();
	}
	


}
