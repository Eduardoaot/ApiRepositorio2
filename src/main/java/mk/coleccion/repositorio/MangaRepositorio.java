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

@Repository
public interface MangaRepositorio extends JpaRepository<Manga, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE coleccion_manga SET id_estado_lectura = :estadoLectura WHERE id_manga = :idManga AND id_usuario = :idUsuario", nativeQuery = true)
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
            "LEFT JOIN coleccion_manga cm ON cm.id_manga = m.id_manga AND cm.id_usuario = :idUsuario " +
            "WHERE m.id_manga = :idManga " +
            "LIMIT 1", nativeQuery = true)
    List<Object[]> buscarDetallesManga(@Param("idManga") Integer idManga, @Param("idUsuario") Integer idUsuario);
}
