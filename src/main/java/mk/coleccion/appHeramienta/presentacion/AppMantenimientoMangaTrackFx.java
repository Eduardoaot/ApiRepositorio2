package mk.coleccion.appHeramienta.presentacion;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mk.coleccion.ColeccionApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class AppMantenimientoMangaTrackFx extends Application {

    private ConfigurableApplicationContext applicationContext;

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(ColeccionApplication.class).run();

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/index.fxml"));

        // Inyectamos el ApplicationContext al controlador
        loader.setControllerFactory(clazz -> {
            Object bean = applicationContext.getBean(clazz);
            if (bean instanceof mk.coleccion.appHeramienta.controlador.IndexControlador indexController) {
                indexController.setApplicationContext(applicationContext);
            }
            return bean;
        });

        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();
    }


    @Override
    public void stop(){
        applicationContext.close();
        Platform.exit();
    }
}
