package mk.coleccion.appHeramienta.servicio;

import mk.coleccion.appHeramienta.repositorio.SerieRepositorioAPP;
import mk.coleccion.modelo.Serie;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServicioAPP {
    private final SerieRepositorioAPP serieRepositorio;

    public SerieServicioAPP(SerieRepositorioAPP serieRepositorio) {
        this.serieRepositorio = serieRepositorio;
    }

    public List<Serie> listarSeries() {
        return serieRepositorio.findAll();
    }
}
