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
    public Usuario guardarUsuario(Usuario usuario) {
        // Verificar si el email o el usuario ya existen en la base de datos
        boolean existe = usuarioRepositorio.existsByEmailOrUser(usuario.getEmail(), usuario.getUser());

        if (existe) {
            throw new RuntimeException("El correo electrónico o el nombre de usuario ya están en uso.");
        }

        // Guarda el usuario y devuelve el usuario guardado con su ID generado
        return usuarioRepositorio.save(usuario);
    }


    public Usuario autenticarUsuario(String emailOrUser, String contrasena) {
        // Buscar al usuario por email o nombre de usuario
        Usuario usuario = usuarioRepositorio.findByEmailOrUser(emailOrUser, emailOrUser);

        // Verificar si el usuario existe y si la contraseña es correcta
        if (usuario != null && usuario.getPassword().equals(contrasena)) {
            return usuario; // Retorna el usuario si la contraseña es correcta
        }
        return null; // Retorna null si el usuario no existe o la contraseña es incorrecta
    }


}
