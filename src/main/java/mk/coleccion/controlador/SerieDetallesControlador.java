package mk.coleccion.controlador;

import mk.coleccion.dto.SerieDetallesTotalDTO;
import mk.coleccion.response.SerieDetallesResponse;
import mk.coleccion.response.SerieDetallesTotalResponse;
import mk.coleccion.servicio.SerieDetalleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seriesdetalles")
public class SerieDetallesControlador {

    @Autowired
    private SerieDetalleServicio serieDetalleServicio;

    @GetMapping("/detalles/{id_usuario}/{id_serie}")
    public ResponseEntity<SerieDetallesTotalResponse> obtenerDetallesSerie(
            @PathVariable Integer id_usuario,
            @PathVariable Integer id_serie) {

        // Llamamos al servicio para obtener los detalles de la serie con mangas
        SerieDetallesTotalDTO detalles = serieDetalleServicio.obtenerDetallesSerieConMangas(id_serie, id_usuario);

        // Creamos la respuesta envolvente
        SerieDetallesTotalResponse response = new SerieDetallesTotalResponse("success", detalles);

        // Si no hay detalles, devolver "no content"
        if (detalles == null) {
            return ResponseEntity.noContent().build(); // No content sin cuerpo, solo código 204
        } else {
            return ResponseEntity.ok(response); // Devuelve la respuesta con los resultados
        }
    }

}
