package co.edu.uco.sudoku.negocio.negocio.concreta;

import static co.edu.uco.sudoku.negocio.negocio.ensamblador.concreta.SudokuEnsambladorImpl.obtenerSudokuEnsablador;

import java.util.List;

import co.edu.uco.sudoku.datos.entidad.SudokuEntidad;
import co.edu.uco.sudoku.datos.factoria.SudokuDatosFactoria;
import co.edu.uco.sudoku.dominio.SudokuDominio;
import co.edu.uco.sudoku.negocio.negocio.SudokuNegocio;
import co.edu.uco.sudoku.negocio.validador.concreta.SudokuValidador;
import co.edu.uco.sudoku.negocio.validador.enumerador.TipoValidacion;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.sudoku.ColumnasUnicasSudokuRegla;
import co.edu.uco.sudoku.negocio.validador.regla.concreta.sudoku.FilasUnicasSudokuRegla;
import co.edu.uco.sudoku.transversal.exepcion.SudokuNegocioExeption;;

public class SudokuNegocioImpl implements SudokuNegocio {

	private SudokuDatosFactoria sudokuDatos;

	public SudokuNegocioImpl(SudokuDatosFactoria factoriaDatos) {
		this.sudokuDatos = factoriaDatos;
	}

	@Override
	public List<SudokuDominio> consultar(SudokuEntidad entidad) {
		return obtenerSudokuEnsablador()
				.ensamblarDominiosDesdeEntidad(sudokuDatos.obtenerSudokuDatos().consultar(entidad));
	}

	@Override
	public void registrar(SudokuDominio sudoku) {
		SudokuValidador.obtenerInstancia().validar(sudoku, TipoValidacion.CREACION);
		FilasUnicasSudokuRegla.obtenerInstancia().validar(sudoku);
		ColumnasUnicasSudokuRegla.obtenerInstancia().validar(sudoku);
		ValidarSudokusRepetidos(sudoku);
		
		sudokuDatos.obtenerSudokuDatos().crear(obtenerSudokuEnsablador().ensamblarEntidad(sudoku).get());
	}

	private void ValidarSudokusRepetidos(SudokuDominio sudoku) {
		List<SudokuDominio> sudokus = consultar(SudokuEntidad.crear());
		
		sudokus.forEach( sud -> comparaSudokus(sudoku, sud) );
		
	}
	
	private void comparaSudokus(SudokuDominio sudokuNuevo, SudokuDominio sudokuComparado) {
		int contadorNumerosRepetidos = 0;
		
		for(int filaRegion = 0; filaRegion<3 ;filaRegion ++) {
			for(int columnaRegion = 0; columnaRegion<3 ;columnaRegion ++) {
				
				for(int filaCelda = 0; filaCelda<3 ;filaCelda ++) {
					for(int columnaCelda = 0; columnaCelda<3 ;columnaCelda ++) {
						if(sudokuNuevo.getRegiones()[filaRegion][columnaRegion].getCeldas()[filaCelda][columnaCelda].getNumero() == sudokuComparado.getRegiones()[filaRegion][columnaRegion].getCeldas()[filaCelda][columnaCelda].getNumero()) {
							contadorNumerosRepetidos++;
						}
						
					}
				}
				
			}
		}
		if(contadorNumerosRepetidos == 81) {
			throw new SudokuNegocioExeption("El sudoku que se quiere ingresar ya se encuentra repetido");
		}

	}
	

	@Override
	public void eliminar(SudokuDominio sudoku) {
		SudokuValidador.obtenerInstancia().validar(sudoku, TipoValidacion.ELIMINACION);
		asegurarQueSudokuExista(sudoku);
		
		sudokuDatos.obtenerSudokuDatos().eliminar(obtenerSudokuEnsablador().ensamblarEntidad(sudoku).get());				
	}
	
	private void asegurarQueSudokuExista(SudokuDominio sudokuDominio) {
		SudokuEntidad sudokuEntidad = SudokuEntidad.crear().setCodigo(sudokuDominio.getCodigo());

		if (consultar(sudokuEntidad).isEmpty()) {
			throw new SudokuNegocioExeption("No existe un sudoku con el codigo : " + sudokuDominio.getCodigo());
		}
	}

}
