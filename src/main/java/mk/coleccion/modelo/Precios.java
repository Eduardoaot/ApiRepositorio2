package mk.coleccion.modelo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Precios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPrecios;

    Float precio;

    @OneToMany(mappedBy = "precios", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Manga> mangas;
}
