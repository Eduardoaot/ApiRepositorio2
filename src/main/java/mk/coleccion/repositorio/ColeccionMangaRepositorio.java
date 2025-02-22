package mk.coleccion.repositorio;

import mk.coleccion.modelo.ColeccionManga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColeccionMangaRepositorio extends JpaRepository<ColeccionManga, Integer> {

    @Query(value = "SELECT " +
            "cm.id_usuario AS idUsuario, " +
            "m.id_manga AS idManga, " +
            "m.manga_date AS mangaDate, " +
            "mi.direccion_manga_img AS mangaImg, " +
            "m.manga_num AS mangaNum, " +
            "el.estado AS estado, " +
            "pr.precio AS precio, " +
            "s.serie_nom AS serieNom " +
            "FROM coleccion_manga cm " +
            "INNER JOIN manga m ON cm.id_manga = m.id_manga " +
            "INNER JOIN manga_imagen mi ON m.id_manga_imagen = mi.id_manga_imagen " +
            "INNER JOIN estado_lectura el ON cm.id_estado_lectura = el.id_estado_lectura " +
            "INNER JOIN precios pr ON m.id_precio = pr.id_precios " +
            "INNER JOIN serie s ON m.id_serie = s.id_serie " +
            "WHERE cm.id_usuario = :idUsuario " +
            "ORDER BY cm.fecha ASC;", nativeQuery = true)
    List<Object[]> findDetallesColeccionManga(@Param("idUsuario") Integer idUsuario);

    @Query(value = "SELECT " +
            "s.id_serie AS id_serie," +
            "s.serie_nom AS serieNom, " +
            "s.serie_tot AS serieTot, " +
            "COUNT(cm.id_manga) AS tomosUsuario, " +
            "(SELECT mi.direccion_manga_img " +
            " FROM manga m_img " +
            " INNER JOIN manga_imagen mi ON m_img.id_manga_imagen = mi.id_manga_imagen " +
            " WHERE m_img.id_serie = s.id_serie AND m_img.manga_num = 1 " +
            " LIMIT 1) AS serieImagen " +
            "FROM coleccion_manga cm " +
            "INNER JOIN manga m ON cm.id_manga = m.id_manga " +
            "INNER JOIN serie s ON m.id_serie = s.id_serie " +
            "WHERE cm.id_usuario = :idUsuario " +
            "GROUP BY s.id_serie, s.serie_nom, s.serie_tot",
            nativeQuery = true)
    List<Object[]> findSeriesAndTotalByUserId(@Param("idUsuario") Integer idUsuario);




}