package mk.coleccion.repositorio;

import mk.coleccion.modelo.PresupuestosManga;
import mk.coleccion.modelo.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepositorio extends JpaRepository<Serie, Integer> {  // Cambio aquí a Integer

    @Query(value = "SELECT " +
            "s.id_serie, " +                    // idSerie
            "s.serie_nom, " +                   // nombre
            "s.serie_tot, " +                   // totalTomos
            "mi.direccion_manga_img " +         // imagenPrimerTomo
            "FROM serie s " +
            "JOIN manga m ON s.id_serie = m.id_serie " +
            "JOIN manga_imagen mi ON m.id_manga_imagen = mi.id_manga_imagen " +
            "WHERE LOWER(s.serie_nom) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "AND m.manga_num = 1", nativeQuery = true)
    List<Object[]> buscarSeriesConImagenYTotalTomos(@Param("query") String query);




}

