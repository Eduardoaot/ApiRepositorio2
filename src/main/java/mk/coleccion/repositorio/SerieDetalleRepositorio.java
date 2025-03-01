package mk.coleccion.repositorio;

import mk.coleccion.modelo.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SerieDetalleRepositorio extends JpaRepository<Serie, Integer> {

    @Query(value = "SELECT " +
            "COUNT(cm.id_manga) AS total_mangas_en_coleccion, " +            // Total mangas en la colección
            "COUNT(CASE WHEN cm.id_estado_lectura IN (1, 2) THEN 1 END) AS total_mangas_sin_leer, " + // Total mangas sin leer
            "s.serie_nom AS nombre_serie, " +                                // Nombre de la serie
            "s.serie_tot AS total_mangas_serie, " +                          // Total de mangas de la serie
            "m.id_manga, " +                                                 // id del manga
            "m.manga_num, " +                                                // número del manga
            "mi.direccion_manga_img, " +                                     // dirección de la imagen del manga
            "ds.descripcion_serie AS descripcion_serie, " +                  // Descripción de la serie
            "s.serie_aut AS autor_serie " +                                  // Autor de la serie
            "FROM manga m " +
            "JOIN serie s ON m.id_serie = s.id_serie " +
            "JOIN descripcion_serie ds ON s.id_descripcion_serie = ds.id_descripcion_serie " +
            "LEFT JOIN coleccion_manga cm ON m.id_manga = cm.id_manga AND cm.id_usuario = :idUsuario " + // LEFT JOIN para traer todos los mangas
            "LEFT JOIN manga_imagen mi ON m.id_manga_imagen = mi.id_manga_imagen " +
            "WHERE s.id_serie = :idSerie " +
            "GROUP BY s.id_serie, ds.descripcion_serie, s.serie_aut, s.serie_nom, s.serie_tot, m.id_manga, m.manga_num, mi.direccion_manga_img " +
            "ORDER BY m.manga_num ASC", nativeQuery = true)
    List<Object[]> obtenerDetallesSerieConMangas(
            @Param("idSerie") Integer idSerie,
            @Param("idUsuario") Integer idUsuario
    );
}




