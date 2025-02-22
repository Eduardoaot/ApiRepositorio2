package mk.coleccion.servicio;

import mk.coleccion.modelo.MangaImagen;
import mk.coleccion.repositorio.MangaImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.rowset.serial.SerialBlob;

@Service
public class MangaImagenServicio {

    @Autowired
    private MangaImagenRepositorio mangaImagenRepositorio;

    // Guardar imagen
    public MangaImagen guardarImagen(MultipartFile file) throws IOException, SQLException {
        MangaImagen imagen = new MangaImagen();
        imagen.setMangaImg(file.getOriginalFilename()); // Guarda el nombre del archivo
        imagen.setMangaImagenArchivo(new SerialBlob(file.getBytes())); // Convierte en BLOB
        imagen.setDireccionMangaImg("http://localhost:8080/api/mangaImagen/"+file.getOriginalFilename());
        return mangaImagenRepositorio.save(imagen);
    }

    // Obtener imagen por nombre
    public Optional<MangaImagen> obtenerImagenPorNombre(String nombreArchivo) {
        return mangaImagenRepositorio.findByMangaImg(nombreArchivo);
    }
}
