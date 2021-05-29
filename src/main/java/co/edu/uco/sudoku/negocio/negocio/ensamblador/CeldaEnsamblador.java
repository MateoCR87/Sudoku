package co.edu.uco.sudoku.negocio.negocio.ensamblador;

import co.edu.uco.sudoku.datos.entidad.CeldaEntidad;
import co.edu.uco.sudoku.dominio.CeldaDominio;
import co.edu.uco.sudoku.dto.CeldaDTO;

public interface CeldaEnsamblador extends Ensamblador<CeldaDominio, CeldaEntidad, CeldaDTO> {

	CeldaEntidad[][] celdasEntidad(CeldaDominio[][] matrizCeldas);

	CeldaDominio[][] celdasDominio(CeldaEntidad[][] matrizCeldas);

	CeldaDominio[][] celdasDominio(CeldaDTO[][] matrizCeldas);

	CeldaDTO[][] celdasDTO(CeldaDominio[][] matrizCeldas);
}
