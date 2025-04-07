package mk.coleccion.servicio;

import lombok.RequiredArgsConstructor;
import mk.coleccion.dto.EstadisticasResponseDTO;
import mk.coleccion.dto.MejorAhorroDTO;
import mk.coleccion.modelo.MonetarioAhorros;
import mk.coleccion.repositorio.ColeccionMangaRepositorio;
import mk.coleccion.repositorio.MonetarioAhorrosRepositorio;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class EstadisticasServicio {

    private final ColeccionMangaRepositorio coleccionMangaRepositorio;
    private final MonetarioAhorrosRepositorio monetarioAhorrosRepositorio;

    public EstadisticasResponseDTO obtenerEstadisticasUsuario(Integer idUsuario) {
        int totalComprados = coleccionMangaRepositorio.contarPorUsuario(idUsuario);
        int totalCompradosAnio = coleccionMangaRepositorio.contarPorUsuarioEnAnio(idUsuario, Year.now().getValue());
        int totalMes = coleccionMangaRepositorio.contarPorUsuarioEnMes(idUsuario, YearMonth.now().getYear(), YearMonth.now().getMonthValue());

        Float valorTotal = coleccionMangaRepositorio.sumarPrecioMangasPorUsuario(idUsuario);
        Float ahorroTotal = monetarioAhorrosRepositorio.sumarTotalAhorrado(idUsuario);
        Float gastoTotal = (valorTotal != null ? valorTotal : 0) - (ahorroTotal != null ? ahorroTotal : 0);

        List<Object[]> mejoresAhorros = monetarioAhorrosRepositorio.obtenerMejoresAhorros(idUsuario);
        List<MejorAhorroDTO> listaMejoresAhorros = new ArrayList<>();
        int top = 1;

        // Formato de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Object[] obj : mejoresAhorros) {
            Integer numeroMangas = (Integer) obj[0];
            Float totalAhorrado = (Float) obj[1];
            Date fechaDeAhorro = (Date) obj[2];  // Obtener la fecha de ahorro

            // Formateamos la fecha
            String fechaFormateada = sdf.format(fechaDeAhorro);

            // Ahora creamos el DTO con la fecha formateada
            listaMejoresAhorros.add(new MejorAhorroDTO(top++, numeroMangas, totalAhorrado, fechaFormateada));
        }

        return new EstadisticasResponseDTO("success", totalComprados, totalCompradosAnio, totalMes, valorTotal, ahorroTotal, gastoTotal, listaMejoresAhorros);
    }
}

