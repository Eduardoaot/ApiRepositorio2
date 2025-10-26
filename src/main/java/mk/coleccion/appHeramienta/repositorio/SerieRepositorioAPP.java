package mk.coleccion.appHeramienta.repositorio;

import mk.coleccion.modelo.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepositorioAPP extends JpaRepository<Serie, Integer> {
}
