package mk.coleccion.repositorio;

import mk.coleccion.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {


    Usuario findByEmailOrUser(String email, String user);

    boolean existsByEmailOrUser(String email, String user);

}
