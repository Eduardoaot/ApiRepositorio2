package mk.coleccion.servicio;

import mk.coleccion.modelo.Usuario;
import mk.coleccion.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements IUsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public void guardarUsuario(Usuario usuario) {
        this.usuarioRepositorio.save(usuario);
    }
}
