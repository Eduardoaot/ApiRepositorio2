package mk.coleccion.servicio;

import mk.coleccion.dto.ColeccionMangaDetalleDTO;
import mk.coleccion.dto.SerieInfoDTO;

import java.util.List;

public interface IColeccionMangaServicio {
    List<ColeccionMangaDetalleDTO> obtenerDetallesColeccionManga(Integer idUsuario);
    List<SerieInfoDTO> obtenerDetallesColeccionSeries(Integer idUsuario);
}
