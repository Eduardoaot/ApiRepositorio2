package mk.coleccion.controlador;

import mk.coleccion.response.LoginRequest;
import mk.coleccion.response.UsuarioResponse;
import mk.coleccion.modelo.Usuario;
import mk.coleccion.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Endpoint para guardar un usuario
    @PostMapping("/guardar")
    public ResponseEntity<UsuarioResponse> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            // Guardamos al usuario
            Usuario usuarioGuardado = usuarioServicio.guardarUsuario(usuario);

            // Creamos la respuesta con el mensaje y el ID del usuario guardado
            UsuarioResponse response = new UsuarioResponse("Usuario guardado correctamente", usuarioGuardado.getIdUsuario());

            // Devolvemos la respuesta con el código HTTP 201 (creado)
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // En caso de error, devolvemos un mensaje de error y el código HTTP 500
            UsuarioResponse response = new UsuarioResponse("Error al guardar el usuario: " + e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity<UsuarioResponse> autenticarUsuario(@RequestBody LoginRequest loginRequest) {
        // Verificamos si el email y la contraseña son correctos
        Usuario usuario = usuarioServicio.autenticarUsuario(loginRequest.getEmail(), loginRequest.getContrasena());

        if (usuario != null) {
            // Si el usuario existe y la contraseña es correcta, devolvemos el userId
            UsuarioResponse response = new UsuarioResponse("Autenticación exitosa", usuario.getIdUsuario());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si no se encuentra el usuario o la contraseña es incorrecta, devolvemos un mensaje de error
            UsuarioResponse response = new UsuarioResponse("Email o contraseña incorrectos", null);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); // 401 Unauthorized
        }
    }

}