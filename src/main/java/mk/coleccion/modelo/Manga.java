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
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idManga;

    @ManyToOne
    @JoinColumn(name = "idSerie", nullable = false)
    Serie serie;

    Float mangaNum;

    @ManyToOne
    @JoinColumn(name = "idDesc", nullable = false)
    DescripcionManga descripcionManga;

    @ManyToOne
    @JoinColumn(name = "idPrecio", nullable = false)
    Precios precios;

    @ManyToOne
    @JoinColumn(name = "idMangaImagen", nullable = false)
    MangaImagen mangaImagen;

    Date mangaDate;


    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PresupuestosManga> presupuestosMangas;

    @OneToMany(mappedBy = "manga2", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ColeccionManga> coleccionMangas;
}
