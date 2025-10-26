package mk.coleccion.appHeramienta.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mk.coleccion.appHeramienta.servicio.MangaServicioAPP;
import mk.coleccion.modelo.Manga;
import mk.coleccion.modelo.Serie;
import mk.coleccion.servicio.MangaServicio;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;

public class MangaVentanaControllerAPP {

    @FXML
    private TableView<Manga> tableMangas;
    @FXML
    private TableColumn<Manga, Integer> colId;
    @FXML
    private TableColumn<Manga, Float> colVolumen;
    @FXML
    private TableColumn<Manga, String> colDescripcion;
    @FXML
    private TableColumn<Manga, Float> colPrecio;

    private MangaServicioAPP mangaServicio;

    public void setMangaServicioAPP(MangaServicioAPP mangaServicio) {
        this.mangaServicio = mangaServicio;
    }

    @FXML
    public void initialize() {}

    public void cargarMangas(Serie serie) {
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getIdManga()));
        colVolumen.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getVolumeNumber()));
        colDescripcion.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getDescripcionManga().getDescriptionManga()));
        colPrecio.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getMangaPrice()));

        List<Manga> mangas = mangaServicio.listarMangasPorSerie(serie.getIdSerie());
        tableMangas.setItems(FXCollections.observableArrayList(mangas));
    }

    public static void abrirVentana(Serie serie, ApplicationContext ctx) throws IOException {
        FXMLLoader loader = new FXMLLoader(MangaVentanaControllerAPP.class.getResource("/templates/mangaVentana.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);

        MangaVentanaControllerAPP controller = loader.getController();
        controller.setMangaServicioAPP(ctx.getBean(MangaServicioAPP.class));
        controller.cargarMangas(serie);

        stage.setTitle("Mangas de la serie: " + serie.getSerieName());
        stage.show();
    }

}
