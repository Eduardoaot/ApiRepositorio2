package mk.coleccion.servicio;

import jakarta.transaction.Transactional;
import mk.coleccion.dto.CrearMonetarioAhorrosRequestDTO;
import mk.coleccion.modelo.MonetarioAhorros;
import mk.coleccion.modelo.Usuario;
import mk.coleccion.repositorio.MonetarioAhorrosRepositorio;
import mk.coleccion.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonetarioAhorrosServicio {

    @Autowired
    private MonetarioAhorrosRepositorio monetarioAhorrosRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void guardarMonetarioAhorro(CrearMonetarioAhorrosRequestDTO requestDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(requestDTO.getIdUsuario());

        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado.");
        }

        MonetarioAhorros nuevoAhorro = new MonetarioAhorros();
        nuevoAhorro.setUsuario4(usuarioOptional.get());
        nuevoAhorro.setTotalAhorradoPresupuesto(requestDTO.getTotalAhorrado());
        nuevoAhorro.setNumeroMangasAhorrados(requestDTO.getTotalMangas());

        monetarioAhorrosRepositorio.save(nuevoAhorro);
    }
}

