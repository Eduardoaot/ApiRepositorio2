package mk.coleccion.servicio;

import mk.coleccion.dto.MangaDetallesDTO;
import mk.coleccion.response.MangaEstadoLecturaResponse;

import java.util.List;

public interface IMangaServicio {

    public List<MangaDetallesDTO> obtenerDetallesManga(Integer idManga, Integer idUsuario);
    public MangaEstadoLecturaResponse actualizarEstadoLectura(Integer idManga, Integer idUsuario, Integer estadoLectura);
}
