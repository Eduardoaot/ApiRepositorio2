package mk.coleccion.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonetarioAhorros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idMonetario;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    Usuario usuario4;

    Float TotalAhorradoPresupuesto;

    Integer NumeroMangasAhorrados;

    Date FechaDeAhorro;

    @PrePersist
    public void prePersist() {
        if (this.FechaDeAhorro == null) {
            this.FechaDeAhorro = new Date();  // Asigna la fecha actual si no está establecida
        }
    }
}
