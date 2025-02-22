package mk.coleccion.repositorio;

import mk.coleccion.modelo.MangaImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MangaImagenRepositorio extends JpaRepository<MangaImagen, Integer> {
    Optional<MangaImagen> findByMangaImg(String mangaImg); // Buscar por nombre del archivo
}
