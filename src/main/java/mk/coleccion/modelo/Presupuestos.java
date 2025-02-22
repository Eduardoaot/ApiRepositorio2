package mk.coleccion.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Presupuestos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPresupuesto;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    Usuario usuario;

    String nombrePresupuesto;

    Float descuento;

    @OneToMany(mappedBy = "presupuestos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PresupuestosManga> presupuestosMangas;
}
