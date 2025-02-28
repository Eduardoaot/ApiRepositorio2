package mk.coleccion.servicio;

import mk.coleccion.dto.MangaDetallesDTO;
import mk.coleccion.repositorio.MangaRepositorio;
import mk.coleccion.response.MangaEstadoLecturaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MangaServicio implements IMangaServicio {

    @Autowired
    private MangaRepositorio mangaRepositorio;

    public List<MangaDetallesDTO> obtenerDetallesManga(Integer idManga, Integer idUsuario) {
        List<Object[]> results = mangaRepositorio.buscarDetallesManga(idManga, idUsuario);
        return results.stream()
                .map(row -> new MangaDetallesDTO(
                        (String) row[0],  // tituloSerie
                        (Float) row[1],   // numeroManga
                        (String) row[2],   // estadoLectura
                        (String) row[3],   // descripcion
                        (String) row[4],   // nombreAutor
                        (String) row[5],   // imagenManga
                        (Long) row[6]   // estadoAgregado
                ))
                .collect(Collectors.toList());
    }

    public MangaEstadoLecturaResponse actualizarEstadoLectura(Integer idManga, Integer idUsuario, Integer estadoLectura) {
        int filasAfectadas = mangaRepositorio.actualizarEstadoLectura(estadoLectura, idManga, idUsuario);

        // Si la consulta afecta una fila, significa que la actualización fue exitosa
        if (filasAfectadas > 0) {
            return new MangaEstadoLecturaResponse("Estado de lectura actualizado exitosamente", estadoLectura);
        } else {
            return new MangaEstadoLecturaResponse("No se pudo actualizar el estado de lectura", estadoLectura);
        }
    }
}


