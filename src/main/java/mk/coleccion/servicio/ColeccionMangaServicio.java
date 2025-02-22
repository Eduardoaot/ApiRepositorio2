package mk.coleccion.servicio;

import mk.coleccion.dto.ColeccionMangaDetalleDTO;
import mk.coleccion.dto.SerieInfoDTO;
import mk.coleccion.repositorio.ColeccionMangaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColeccionMangaServicio {

    @Autowired
    private ColeccionMangaRepositorio coleccionMangaRepositorio;

    public List<ColeccionMangaDetalleDTO> obtenerDetallesColeccionManga(Integer idUsuario) {
        List<Object[]> results = coleccionMangaRepositorio.findDetallesColeccionManga(idUsuario);
        return results.stream()
                .map(row -> new ColeccionMangaDetalleDTO(
                        (Integer) row[0], // idUsuario
                        (Integer) row[1], // idManga
                        (Date) row[2],  // mangaDate
                        (String) row[3],  // mangaImg
                        (Float) row[4],   // mangaNum
                        (String) row[5],  // estado (cambiado de "descripcion" a "estado")
                        (Float) row[6],   // precio
                        (String) row[7]   // serieNom
                ))
                .collect(Collectors.toList());
    }

    public List<SerieInfoDTO> obtenerSeriesDeColeccion(Integer idUsuario) {
        List<Object[]> results = coleccionMangaRepositorio.findSeriesAndTotalByUserId(idUsuario);

        return results.stream()
                .map(row -> {
                    Integer id_serie = (Integer) row[0];
                    String serieNom = (String) row[1];
                    Integer serieTot = (Integer) row[2];
                    Integer tomosUsuario = (row[3] != null) ? ((Number) row[3]).intValue() : 0;
                    String serieImagen = (String) row[4]; // URL de la imagen

                    // Cálculo del porcentaje de tomos adquiridos
                    double seriePorcentaje = (serieTot != null && serieTot > 0)
                            ? ((double) tomosUsuario / serieTot) * 100
                            : 0;

                    // Determinar el estado de la colección
                    String serieEstado;
                    if (serieTot == null || serieTot == 0) {
                        serieEstado = "No completar";
                    } else if (serieTot == 1) {
                        serieEstado = "Tomo único";
                    } else if (tomosUsuario.equals(serieTot)) {
                        serieEstado = "¡Completa!";
                    } else if (tomosUsuario > 0) {
                        int faltantes = serieTot - tomosUsuario;
                        serieEstado = "Te faltan " + faltantes;
                    } else {
                        serieEstado = "No iniciar";
                    }

                    return new SerieInfoDTO(id_serie, serieNom, seriePorcentaje, serieEstado, serieImagen);
                })
                .collect(Collectors.toList());
    }


}