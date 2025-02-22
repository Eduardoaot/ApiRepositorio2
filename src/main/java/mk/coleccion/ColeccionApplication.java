package mk.coleccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;




@SpringBootApplication
@EntityScan(basePackages = "mk.coleccion.modelo")
public class ColeccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColeccionApplication.class, args);
	}

}
