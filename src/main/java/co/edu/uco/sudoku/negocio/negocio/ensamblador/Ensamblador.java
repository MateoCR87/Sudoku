package co.edu.uco.sudoku.negocio.negocio.ensamblador;

import java.util.List;
import java.util.Optional;

public interface Ensamblador<D, E, T> {

	Optional<D> ensamblarDominioDesdeEntidad(E entidad);

	Optional<E> ensamblarEntidad(D dominio);

	List<D> ensamblarDominiosDesdeEntidad(List<E> entidades);

	D ensamblarDominioDesdeDTO(T dto);

	List<D> ensamblarDominiosDesdeDTO(List<T> dtos);

	T ensamblarDTO(D dominio);

	List<T> ensamblarDTOs(List<D> dominios);
}
