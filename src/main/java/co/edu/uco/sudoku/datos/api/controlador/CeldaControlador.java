package co.edu.uco.sudoku.datos.api.controlador;
 
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
 
import co.edu.uco.sudoku.datos.api.controlador.respuesta.EstadoRespuestaEnum;
import co.edu.uco.sudoku.datos.api.controlador.respuesta.Respuesta;
import co.edu.uco.sudoku.dto.CeldaDTO;
import co.edu.uco.sudoku.negocio.fachada.CeldaFachada;
import co.edu.uco.sudoku.negocio.fachada.concreta.CeldaFachadaImpl;
import co.edu.uco.sudoku.transversal.exepcion.SudokuExepcion;
 
public class CeldaControlador {

    public ResponseEntity<Respuesta<CeldaDTO>> consultar () {
        ResponseEntity<Respuesta<CeldaDTO>> entidadRespuesta;
        Respuesta<CeldaDTO> respuesta = new Respuesta<>();

        try {
            CeldaFachada celdaFachada = new CeldaFachadaImpl();
            List<CeldaDTO> celdas = celdaFachada.consultar(CeldaDTO.crear());
            respuesta.setDatos(celdas);

            respuesta.adicionaMensage("Las celdas se consultaron de forma exitosa" );
            respuesta.setEstado(EstadoRespuestaEnum.Exitosa);

            entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
        } catch (SudokuExepcion exception) {
            respuesta.adicionaMensage(exception.getMessage());
            respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);

            entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
 
            exception.printStackTrace();
        } catch(Exception exception) {
            respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de consultar la informacion de las celdas");
            respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);

            entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
 
            exception.printStackTrace();
        }

        return entidadRespuesta ;
    }

    public ResponseEntity<Respuesta<CeldaDTO>> consultar (@PathVariable int codigo) {
        ResponseEntity<Respuesta<CeldaDTO>> entidadRespuesta;
        Respuesta<CeldaDTO> respuesta = new Respuesta<>();

        try {
            CeldaFachada celdaFachada = new CeldaFachadaImpl();
            List<CeldaDTO> celdas = celdaFachada.consultar(CeldaDTO.crear().setCodigo(codigo));
            respuesta.setDatos(celdas);

            if(respuesta.getDatos().isEmpty()) {
                respuesta.adicionaMensage("No existe una celda con el codigo" + codigo);
            } else {
                respuesta.setEstado(EstadoRespuestaEnum.Exitosa);
            }

            respuesta.adicionaMensage("la celda se consulto de forma exitosa");
            respuesta.setEstado(EstadoRespuestaEnum.Exitosa);

            entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
        } catch (SudokuExepcion exception) {
            respuesta.adicionaMensage(exception.getMessage());
            respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);

            entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
 
            exception.printStackTrace();
        } catch(Exception exception) {
            respuesta.adicionaMensage("se ha presentado un problema inesperado tratando de consultar la informacion de las celdas");
            respuesta.setEstado(EstadoRespuestaEnum.No_Exitosa);

            entidadRespuesta = new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
 
            exception.printStackTrace();
        }

        return entidadRespuesta ;
    }

}