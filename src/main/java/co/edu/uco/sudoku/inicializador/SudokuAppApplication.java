package co.edu.uco.sudoku.inicializador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages  = {"co.edu.uco.sudoku"})
public class SudokuAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuAppApplication.class, args);
	}

}
