package mk.coleccion.repositorio;

import mk.coleccion.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Usuario findByEmailOrUser(String email, String user);

    boolean existsByEmailOrUser(String email, String user);

    // Método para actualizar el campo 'meta' del usuario por su id
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.meta = :meta WHERE u.idUsuario = :idUsuario")
    void actualizarMeta(int idUsuario, String meta);

    @Query("SELECT u.meta FROM Usuario u WHERE u.id = :idUsuario")
    int getMetaById(int idUsuario);

    @Query(value = "SELECT " +
            "CASE WHEN id_estado_lectura = 3 THEN MONTH(fecha_leidos) END AS mes_leido, " +
            "CASE WHEN id_estado_lectura = 3 THEN YEAR(fecha_leidos) END AS anio_leido " +
            "FROM coleccion_manga " +
            "WHERE id_usuario = :idUsuario",
            nativeQuery = true)
    List<Object[]> obtenerMangasLecturaFecha(@Param("idUsuario") Integer idUsuario);


}

