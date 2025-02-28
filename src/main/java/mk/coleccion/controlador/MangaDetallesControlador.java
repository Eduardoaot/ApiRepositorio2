package mk.coleccion.controlador;

import mk.coleccion.dto.MangaDetallesDTO;
import mk.coleccion.response.ActualizarEstadoLecturaRequest;
import mk.coleccion.response.MangaDetallesResponse;
import mk.coleccion.response.MangaEstadoLecturaResponse;
import mk.coleccion.servicio.MangaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manga")
public class MangaDetallesControlador {

    @Autowired
    private MangaServicio mangaServicio;

    // Método POST para actualizar el estado de lectura
    @PostMapping("/actualizarEstadoLectura")
    public ResponseEntity<MangaEstadoLecturaResponse> actualizarEstadoLectura(@RequestBody ActualizarEstadoLecturaRequest request) {
        MangaEstadoLecturaResponse response = mangaServicio.actualizarEstadoLectura(request.getIdManga(), request.getIdUsuario(), request.getEstadoLectura());
        return ResponseEntity.ok(response);
    }

    // Método GET para obtener detalles del manga
    @GetMapping("/{idManga}/{idUsuario}/detalles")
    public MangaDetallesResponse obtenerDetallesManga(@PathVariable Integer idManga,
                                                      @PathVariable Integer idUsuario) {
        // Obtenemos los detalles del manga
        List<MangaDetallesDTO> detalles = mangaServicio.obtenerDetallesManga(idManga, idUsuario);
        String response = detalles.isEmpty() ? "No encontrado" : "Éxito";

        return new MangaDetallesResponse(response, detalles);
    }
}


