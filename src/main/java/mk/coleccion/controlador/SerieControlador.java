package mk.coleccion.controlador;

import mk.coleccion.response.SerieDetallesResponse;
import mk.coleccion.dto.SerieDetallesDTO;
import mk.coleccion.servicio.SerieServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SerieControlador {

    @Autowired
    private SerieServicio serieServicio;

    @GetMapping("/buscar")
    public ResponseEntity<SerieDetallesResponse> buscarSeries(@RequestParam String query) {
        // Llamar al servicio para obtener las series
        List<SerieDetallesDTO> series = serieServicio.buscarSeriesConDescripcion(query);

        // Crear la respuesta envolvente
        SerieDetallesResponse response = new SerieDetallesResponse("success", series);

        // Si no hay resultados, devolver "no content" con una lista vacía
        if (series.isEmpty()) {
            return ResponseEntity.noContent().build(); // No content sin cuerpo, solo código 204
        } else {
            return ResponseEntity.ok(response); // Devuelve la respuesta con los resultados
        }
    }
}






