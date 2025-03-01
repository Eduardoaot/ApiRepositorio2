package mk.coleccion.servicio;

import mk.coleccion.dto.MangaDTO;
import mk.coleccion.dto.SerieDetallesTotalDTO;
import mk.coleccion.repositorio.SerieDetalleRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieDetalleServicio implements ISerieDetalleServicio{

    @Autowired
    private SerieDetalleRepositorio serieDetalleRepositorio;

    // Dependencia de ObjectMapper para deserializar el JSON
    @Autowired
    private ObjectMapper objectMapper;

    public SerieDetallesTotalDTO obtenerDetallesSerieConMangas(Integer idSerie, Integer idUsuario) {
        // Obtener los resultados desde el repositorio
        List<Object[]> results = serieDetalleRepositorio.obtenerDetallesSerieConMangas(idSerie, idUsuario);

        // Inicializamos las variables necesarias
        Long totalMangasEnColeccion = 0L;
        Long totalMangasSinLeer = 0L;
        String nombreSerie = null;
        Integer totalMangaSerie = null;
        String descripcionSerie = null;
        String autorSerie = null;
        List<MangaDTO> listaMangas = new ArrayList<>();

        // Recorremos los resultados y procesamos los datos
        for (Object[] row : results) {
            // Verificamos que los valores se extraigan correctamente
            totalMangasEnColeccion += (Long) row[0]; // Total mangas en la colección
            totalMangasSinLeer += (Long) row[1]; // Total mangas sin leer

            // Aseguramos que solo asignamos valores a las propiedades una vez
            if (nombreSerie == null) {
                nombreSerie = (String) row[2];  // Serie nombre (String)
                totalMangaSerie = (Integer) row[3];  // Total mangas de la serie (Integer)
                descripcionSerie = (String) row[7];  // Descripción de la serie (String)
                autorSerie = (String) row[8];  // Autor de la serie (String)
            }

            // Convertimos correctamente los valores que corresponden a números
            Integer idManga = (Integer) row[4];  // ID del manga (Integer)
            Float mangaNum = (Float) row[5];  // Número del manga (Integer)
            String direccionImagen = (String) row[6];  // Dirección de la imagen del manga (String)
            Long EstadoManga = (Long) row[0];

            // Añadimos el manga a la lista
            MangaDTO mangaDTO = new MangaDTO(idManga, mangaNum, direccionImagen, EstadoManga);
            listaMangas.add(mangaDTO);
        }

        // Calculamos el porcentaje completado y el porcentaje por leer correctamente
        // Asegurándonos de que se manejen como Float
        Float porcentajeCompletado = (totalMangaSerie != null && totalMangaSerie != 0) ?
                (float) (totalMangasEnColeccion) / totalMangaSerie * 100 : 0f;

        Float porcentajePorLeer = (totalMangaSerie != null && totalMangaSerie != 0 && totalMangasEnColeccion != 0) ?
                (float) (totalMangasEnColeccion - totalMangasSinLeer) / totalMangasEnColeccion * 100 : 0f;

        // Crear el objeto SerieDetallesTotalDTO con los datos obtenidos
        SerieDetallesTotalDTO detallesTotalDTO = new SerieDetallesTotalDTO();
        detallesTotalDTO.setTotalMangasEnColeccion(totalMangasEnColeccion);
        detallesTotalDTO.setTotalMangasSinLeer(totalMangasSinLeer);
        detallesTotalDTO.setNombreSerie(nombreSerie);
        detallesTotalDTO.setTotalMangaSerie(totalMangaSerie);
        detallesTotalDTO.setDescripcionSerie(descripcionSerie);
        detallesTotalDTO.setAutorSerie(autorSerie);
        detallesTotalDTO.setListaMangas(listaMangas);
        detallesTotalDTO.setPorcentajeCompletado(porcentajeCompletado);
        detallesTotalDTO.setPorcentajePorLeer(porcentajePorLeer);

        return detallesTotalDTO;
    }





}
