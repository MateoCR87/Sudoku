package co.edu.uco.sudoku.datos.api.controlador;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.sudoku.dto.SudokuDTO;

@RestController
@RequestMapping("/api/v1/sudoku")
public class SudokuControlador {
	
	@GetMapping("ejemplo")
	public SudokuDTO obtenerSudokuEjemplo() {
		return SudokuDTO.crear();
	}
	
	@PostMapping
	public String crear (@RequestBody SudokuDTO sudoku) {
		return "Cree el sudoku";
	}
	
	@PutMapping("/{codigo}")
	public String modificar (@RequestBody SudokuDTO sudoku, @PathVariable int codigo) {
		return "Modifique  el sudoku " + codigo;
	}
	
	@DeleteMapping("/{codigo}")
	public String delete (@PathVariable int codigo) {
		return "Elimine el sudoku " + codigo;
	}
	
	@GetMapping
	public String consultar () {
		return "Consulta todos los sudokus ";
	}
	
}
