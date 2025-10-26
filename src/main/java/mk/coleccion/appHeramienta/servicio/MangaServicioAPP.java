package mk.coleccion.appHeramienta.servicio;

import mk.coleccion.appHeramienta.repositorio.MangaRepositorioAPP;
import mk.coleccion.modelo.Manga;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MangaServicioAPP {

    private final MangaRepositorioAPP mangaRepositorio;

    public MangaServicioAPP(MangaRepositorioAPP mangaRepositorioAPP) {
        this.mangaRepositorio = mangaRepositorioAPP;
    }

    public List<Manga> listarMangasPorSerie(Integer idSerie) {
        return mangaRepositorio.findBySerieIdSerie(idSerie);
    }

    public List<Manga> obtenerTodos() {
        return mangaRepositorio.findAll();
    }

    public Optional<Manga> obtenerPorId(Integer id) {
        return mangaRepositorio.findById(id);
    }

    public Manga guardar(Manga manga) {
        return mangaRepositorio.save(manga);
    }

    public void eliminar(Integer id) {
        mangaRepositorio.deleteById(id);
    }
}
