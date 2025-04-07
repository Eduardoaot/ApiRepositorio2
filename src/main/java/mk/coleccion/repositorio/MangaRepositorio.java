package mk.coleccion.repositorio;

import jakarta.transaction.Transactional;
import mk.coleccion.modelo.Manga;
import mk.coleccion.modelo.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MangaRepositorio extends JpaRepository<Manga, Integer> {

        @Modifying
        @Transactional
        @Query(value = "UPDATE coleccion_manga SET id_estado_lectura = :estadoLectura, fecha_leidos = NOW() WHERE id_manga = :idManga AND id_usuario = :idUsuario; ", nativeQuery = true)
        int actualizarEstadoLectura(@Param("estadoLectura") Integer estadoLectura,
                                    @Param("idManga") Integer idManga,
                                    @Param("idUsuario") Integer idUsuario);


    // La consulta existente para obtener los detalles del manga
    @Query(value = "SELECT " +
            "s.serie_nom AS titulo_serie, " +
            "m.manga_num AS numero_manga, " +
            "IFNULL(el.estado_lectura, 'No definido') AS estado_lectura, " +
            "IFNULL(dm.descripcion_manga, 'Sin descripción') AS descripcion, " +
            "s.serie_aut AS nombre_autor, " +
            "mi.direccion_manga_img AS imagen_manga, " +
            "p.precio AS precio_manga, " +
            "CASE " +
            "WHEN cm.id_usuario IS NOT NULL THEN TRUE " +
            "ELSE FALSE " +
            "END AS EstadoAgregado " +
            "FROM manga m " +
            "JOIN serie s ON m.id_serie = s.id_serie " +
            "JOIN descripcion_manga dm ON m.id_desc = dm.id_descripcion_manga " +
            "LEFT JOIN estado_lectura el ON el.id_estado_lectura = " +
            "(SELECT id_estado_lectura FROM coleccion_manga WHERE id_manga = m.id_manga AND id_usuario = :idUsuario LIMIT 1) " +
            "LEFT JOIN manga_imagen mi ON m.id_manga_imagen = mi.id_manga_imagen " +
            "LEFT JOIN precios p ON m.id_precio = p.id_precios " + // Se une con la tabla precios
            "LEFT JOIN coleccion_manga cm ON cm.id_manga = m.id_manga AND cm.id_usuario = :idUsuario " +
            "WHERE m.id_manga = :idManga " +
            "LIMIT 1", nativeQuery = true)
    List<Object[]> buscarDetallesManga(@Param("idManga") Integer idManga, @Param("idUsuario") Integer idUsuario);


    @Query(value = "SELECT " +
            "m.id_manga, " +
            "s.serie_nom AS titulo_serie, " +
            "m.manga_num AS numero_manga, " +
            "mi.direccion_manga_img AS imagen_manga " +
            "FROM coleccion_manga cm " +
            "JOIN manga m ON cm.id_manga = m.id_manga " +
            "JOIN serie s ON m.id_serie = s.id_serie " +
            "JOIN manga_imagen mi ON m.id_manga_imagen = mi.id_manga_imagen " +
            "WHERE cm.id_usuario = :idUsuario AND cm.id_estado_lectura = 2", nativeQuery = true)
    List<Object[]> obtenerMangasSinLeer(@Param("idUsuario") Integer idUsuario);

    @Query(value = "SELECT " +
            "m.id_manga, " +
            "m.manga_num AS numero_manga, " +
            "mi.direccion_manga_img AS imagen_manga " +
            "FROM coleccion_manga cm " +
            "JOIN manga m ON cm.id_manga = m.id_manga " +
            "JOIN serie s ON m.id_serie = s.id_serie " +
            "JOIN manga_imagen mi ON m.id_manga_imagen = mi.id_manga_imagen " +
            "WHERE cm.id_usuario = :idUsuario AND cm.id_estado_lectura = 1 " +
            "ORDER BY cm.fecha_agregados ASC", nativeQuery = true)
    List<Object[]> obtenerMangasPendientes(@Param("idUsuario") Integer idUsuario);


    @Query(value = "SELECT " +
            "COUNT(CASE WHEN cm.id_estado_lectura = 3 THEN 1 END) AS total_mangas_estado_3, " +
            "COUNT(CASE WHEN cm.id_estado_lectura = 3 AND MONTH(cm.fecha_leidos) = MONTH(CURRENT_DATE) AND YEAR(cm.fecha_leidos) = YEAR(CURRENT_DATE) THEN 1 END) AS mangas_leidos_mes_actual, " +
            "COUNT(CASE WHEN cm.id_estado_lectura = 3 AND YEAR(cm.fecha_leidos) = YEAR(CURRENT_DATE) THEN 1 END) AS mangas_leidos_ano_actual " +
            "FROM coleccion_manga cm " +
            "WHERE cm.id_usuario = :idUsuario " +
            "AND cm.id_estado_lectura = 3", nativeQuery = true)
    List<Object[]> obtenerEstadisticasLecturaMangas(@Param("idUsuario") Integer idUsuario);

    @Query(value = "SELECT " +
            "MONTH(fecha_agregados) AS mes_agregado, " +
            "YEAR(fecha_agregados) AS anio_agregado, " +
            "CASE WHEN id_estado_lectura = 3 THEN MONTH(fecha_leidos) END AS mes_leido, " +
            "CASE WHEN id_estado_lectura = 3 THEN YEAR(fecha_leidos) END AS anio_leido " +
            "FROM coleccion_manga " +
            "WHERE id_usuario = :idUsuario",
            nativeQuery = true)
    List<Object[]> obtenerMangasFecha(@Param("idUsuario") Integer idUsuario);

    @Query(value = "SELECT " +
            "m.id_manga, " +
            "m.manga_num, " +
            "mi.direccion_manga_img, " +
            "p.precio, " +
            "s.serie_nom, " +  // Agregado el nombre de la serie desde la tabla `serie`
            "CASE " +
            "WHEN cm.id_usuario IS NOT NULL THEN TRUE " +
            "ELSE FALSE " +
            "END AS EstadoAgregado " +
            "FROM manga m " +
            "JOIN coleccion_serie cs ON m.id_serie = cs.id_serie " +
            "LEFT JOIN coleccion_manga cm ON m.id_manga = cm.id_manga AND cm.id_usuario = cs.id_usuario " +
            "JOIN manga_imagen mi ON m.id_manga_imagen = mi.id_manga_imagen " +
            "JOIN precios p ON m.id_precio = p.id_precios " +
            "JOIN serie s ON m.id_serie = s.id_serie " +  // Agregado el JOIN con la tabla `serie`
            "WHERE cs.id_usuario = :idUsuario " +
            "AND cm.id_manga IS NULL " +
            "ORDER BY m.manga_date DESC", nativeQuery = true)
    List<Object[]> pendienteBuscarMangasFaltantes(@Param("idUsuario") Integer idUsuario);

    @Query(value = "SELECT m.id_manga, m.manga_num, mi.direccion_manga_img, " +
            "s.serie_nom, p.precio " +
            "FROM manga m " +
            "JOIN serie s ON m.id_serie = s.id_serie " +
            "JOIN manga_imagen mi ON m.id_manga_imagen = mi.id_manga_imagen " +
            "JOIN precios p ON m.id_precio = p.id_precios " +
            "WHERE m.id_manga IN (:listaIds)", nativeQuery = true)
    List<Object[]> buscarMangasConListasDeId(@Param("listaIds") List<Integer> listaIds);

    Optional<Manga> findById(Integer idManga);

}
