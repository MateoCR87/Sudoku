package co.edu.uco.sudoku.negocio.negocio.ensamblador;

import co.edu.uco.sudoku.datos.entidad.RegionEntidad;
import co.edu.uco.sudoku.dominio.RegionDominio;
import co.edu.uco.sudoku.dto.RegionDTO;

public interface RegionEnsamblador extends Ensamblador<RegionDominio, RegionEntidad, RegionDTO> {

	RegionEntidad[][] regionesEntidad(RegionDominio[][] matrizRegiones);

	RegionDominio[][] regionesDominio(RegionEntidad[][] matrizRegiones);

	RegionDominio[][] regionesDominio(RegionDTO[][] matrizRegiones);

	RegionDTO[][] regionesDTO(RegionDominio[][] matrizRegiones);
}
