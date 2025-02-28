package mk.coleccion.servicio;

import mk.coleccion.dto.SerieDetallesDTO;
import mk.coleccion.repositorio.SerieRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieServicio {

    @Autowired
    private SerieRepositorio serieRepositorio;

    public List<SerieDetallesDTO> buscarSeriesConDescripcion(String query) {
        // Obtener los resultados desde el repositorio
        List<Object[]> results = serieRepositorio.buscarSeriesConImagenYTotalTomos(query);

        // Mapeamos los resultados a SerieDetallesDTO
        List<SerieDetallesDTO> seriesResponse = results.stream()
                .map(row -> new SerieDetallesDTO(
                        (Integer) row[0],  // idSerie
                        (String) row[1],   // nombre
                        (Integer) row[2],  // totalTomos
                        (String) row[3]    // imagenPrimerTomo (URL de la imagen)
                ))
                .collect(Collectors.toList());

        return seriesResponse;
    }



}



