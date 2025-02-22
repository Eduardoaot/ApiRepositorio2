package mk.coleccion.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MangaImagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idMangaImagen;

    @OneToMany(mappedBy = "mangaImagen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Manga> mangas;

    String mangaImg;

    Blob mangaImagenArchivo;

    String direccionMangaImg;


}
