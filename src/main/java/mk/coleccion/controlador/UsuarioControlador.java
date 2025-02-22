package mk.coleccion.controlador;

import mk.coleccion.modelo.Usuario;
import mk.coleccion.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Endpoint para guardar un usuario
    @PostMapping("/guardar")
    public void guardarUsuario(@RequestBody Usuario usuario) {
        usuarioServicio.guardarUsuario(usuario);
    }
}