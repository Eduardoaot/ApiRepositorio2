package mk.coleccion.appHeramienta.repositorio;

import mk.coleccion.modelo.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepositorioAPP extends JpaRepository<Manga, Integer> {
    List<Manga> findBySerieIdSerie(Integer idSerie);
}
