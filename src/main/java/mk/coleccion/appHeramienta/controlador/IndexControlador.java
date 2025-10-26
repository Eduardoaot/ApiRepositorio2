package mk.coleccion.appHeramienta.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mk.coleccion.appHeramienta.servicio.SerieServicioAPP;
import mk.coleccion.modelo.Serie;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexControlador {

    @FXML
    private TableView<Serie> tableSeries;
    @FXML
    private TableColumn<Serie, Integer> colId;
    @FXML
    private TableColumn<Serie, String> colNombre;
    @FXML
    private TableColumn<Serie, String> colAutor;
    @FXML
    private TableColumn<Serie, Integer> colTotales;

    private final SerieServicioAPP serieServicio;

    private ApplicationContext applicationContext; // <-- Campo para el contexto

    public IndexControlador(SerieServicioAPP serieServicio) {
        this.serieServicio = serieServicio;
    }

    // <-- Setter público para inyección
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getIdSerie()));
        colNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getSerieName()));
        colAutor.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getAuthorName()));
        colTotales.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getSerieTotals()));

        List<Serie> series = serieServicio.listarSeries();
        tableSeries.setItems(FXCollections.observableArrayList(series));

        tableSeries.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableSeries.getSelectionModel().getSelectedItem() != null) {
                Serie serie = tableSeries.getSelectionModel().getSelectedItem();
                abrirVentanaMangas(serie);
            }
        });
    }

    private void abrirVentanaMangas(Serie serie) {
        try {
            MangaVentanaControllerAPP.abrirVentana(serie, applicationContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
