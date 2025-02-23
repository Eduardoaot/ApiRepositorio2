package mk.coleccion.servicio;

import mk.coleccion.dto.ColeccionMangaDetalleDTO;
import mk.coleccion.dto.SerieInfoDTO;
import mk.coleccion.dto.UsuarioColeccionDTO;

import java.util.List;

public interface IColeccionMangaServicio {
    List<ColeccionMangaDetalleDTO> obtenerDetallesColeccionManga(Integer idUsuario);
    List<SerieInfoDTO> obtenerDetallesColeccionSeries(Integer idUsuario);
    List<UsuarioColeccionDTO> obtenerDetallesColeccionDelUsuario(Integer idUsuario);
}
