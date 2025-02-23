package mk.coleccion.controlador;

import mk.coleccion.dto.*;
import mk.coleccion.servicio.ColeccionMangaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coleccion-manga")
public class ColeccionMangaControlador {

    @Autowired
    private ColeccionMangaServicio coleccionMangaServicio;

    @GetMapping("/detalles/{idUsuario}")
    public ResponseEntity<MangaResponse> obtenerDetallesColeccionManga(@PathVariable Integer idUsuario) {
        List<ColeccionMangaDetalleDTO> detalles = coleccionMangaServicio.obtenerDetallesColeccionManga(idUsuario);

        // Crear el objeto de respuesta
        MangaResponse response = new MangaResponse("success", detalles);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/series/{idUsuario}")
    public ResponseEntity<SerieResponse> obtenerSeriesDeColeccion(@PathVariable Integer idUsuario) {
        List<SerieInfoDTO> series = coleccionMangaServicio.obtenerSeriesDeColeccion(idUsuario);

        // Crear el objeto de respuesta
        SerieResponse response = new SerieResponse("success", series);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario-detalles/{idUsuario}")
    public ResponseEntity<UsuarioColeccionResponse> obtenerDetallesColeccionDelUsuario(@PathVariable Integer idUsuario) {
        UsuarioColeccionDTO detallesUsuario = coleccionMangaServicio.obtenerDetallesColeccionDelUsuario(idUsuario);

        // Crear el objeto de respuesta
        UsuarioColeccionResponse response = new UsuarioColeccionResponse("success", detallesUsuario);

        return ResponseEntity.ok(response);
    }
}