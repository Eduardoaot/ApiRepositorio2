package mk.coleccion.servicio;

import mk.coleccion.modelo.Usuario;

public interface IUsuarioServicio {

    public Usuario guardarUsuario(Usuario usuario);

    public Usuario autenticarUsuario(String email, String password);
}
