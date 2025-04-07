package mk.coleccion.controlador;

import lombok.RequiredArgsConstructor;
import mk.coleccion.dto.EstadisticasResponseDTO;
import mk.coleccion.servicio.EstadisticasServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/estadisticas")
@RequiredArgsConstructor
public class EstadisticasControlador {

    private final EstadisticasServicio estadisticasServicio;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<EstadisticasResponseDTO> obtenerEstadisticas(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(estadisticasServicio.obtenerEstadisticasUsuario(idUsuario));
    }
}

