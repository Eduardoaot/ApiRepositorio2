package mk.coleccion.controlador;

import mk.coleccion.dto.CrearMonetarioAhorrosRequestDTO;
import mk.coleccion.servicio.MonetarioAhorrosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/monetario")
public class MonetarioAhorrosControlador {

    @Autowired
    private MonetarioAhorrosServicio monetarioAhorrosServicio;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarMonetario(@RequestBody CrearMonetarioAhorrosRequestDTO requestDTO) {
        try {
            monetarioAhorrosServicio.guardarMonetarioAhorro(requestDTO);
            return ResponseEntity.ok("Datos de ahorro guardados exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar los datos de ahorro: " + e.getMessage());
        }
    }
}

